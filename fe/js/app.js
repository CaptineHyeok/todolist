(function(window) {
  'use strict';

  var todoListClass = '<li class="%completed%"><div class="view"><input class="toggle" type="checkbox" %checked%><label>%todo%</label><button class="destroy"></button></div></li>';
  var todoView = $('.todo-list');
  var selected = 'All';
  var todoArray = [];

  function toggle() {
    var li = $(this).parent().parent();
    var index = li.index();
    var id = todoArray[index].id;
    var completed = todoArray[index].completed;

    var url = '/api/todos/' + id + '/' + completed;

    $.ajax({
      url: url,
      method: 'put',
      success: function(ret) {
        todoArray[index].completed = (completed + 1) % 2;

        if (todoArray[index].completed == 1) {
          li.attr('class', 'completed');
        } else {
          li.attr('class', 'view');
        }
      },
      error: function(xhr, status, error) {
        console.log(error);
      }
    });
  }

  function eraseList() {
    var url = "/api/todos/";

    $.ajax({
      method: 'delete',
      url: url,
      success: function(ret) {
        selectList(selected);
      }
    });
  }

  function erase() {
    var li = $(this).parent().parent();
    var index = li.index();
    var id = todoArray[index].id;
    var url = '/api/todos/';

    $.ajax({
      url: url + id,
      method: "delete",
      success: function(ret) {
        li.remove();
        todoArray.splice(index, 1);
        $('strong').text(todoArray.length);
      },
      error: function(xhr, status, error) {
        console.log(error);
      }
    });
  }

  function selectList(state) {
    var selectURL = '/api/todos';
    if (state == 'Active') selectURL += '/0';
    else if (state == 'Completed') selectURL += '/1';

    $.ajax({
      method: 'GET',
      url: selectURL,
      dataType: 'json',
      success: function(ret) {
        todoView.children().remove();
        todoArray = ret;
        for (var i = 0; i < todoArray.length; i++) {
          var completed = todoArray[i].completed;
          var todoList = todoListClass.replace('%todo%', todoArray[i].todo);

          if (completed == 0) {
            todoList = todoList.replace('%completed%', 'view');
            todoList = todoList.replace('%checked%', '');
          } else if (completed == 1) {
            todoList = todoList.replace('%completed%', 'completed');
            todoList = todoList.replace('%checked%', 'checked');
          }

          todoView.append(todoList);
        }
        $('strong').text(todoArray.length);
        $('.toggle').click(toggle);
        $('.clear-completed').click(eraseList);
        $('.destroy').click(erase);
      },
      error: function(xhr, status, error) {
        console.log(error);
      }
    });

  }

  selectList('All');

  $('a').click(function(evt) {
    evt.preventDefault();
    selected = $(this).text();
    $('a').removeAttr('class');
    $(this).attr('class', 'selected');

    selectList(selected);
  });

  $('.new-todo').keydown(function(key) {
    if (key.keyCode == 13 && $(this).val() != '') {
      var todoObject = {};
      todoObject.todo = $(this).val();
      todoObject.completed = 0;

      var insertList = todoListClass.replace('%todo%', $(this).val());
      insertList = insertList.replace('%completed%', 'view');
      insertList = insertList.replace('%checked%', '');

      $.ajax({
        url: '/api/todos',
        type: 'POST',
        dataType: 'json',
        data: JSON.stringify(todoObject),
        contentType: 'application/json',
        success: function(ret) {
          var ul = todoView.prepend(insertList);
          var newli = ul.children().first();

          newli.children('div').children('.toggle').click(toggle);
          newli.children('div').children('.destroy').click(erase);

          todoObject.id = ret;
          todoArray.unshift(todoObject);
          $('strong').text(todoArray.length);
        },
        error: function(xhr, status, error) {
          console.log(error);
        }

      });

      $(this).val('');
    }
  });

})(window);
