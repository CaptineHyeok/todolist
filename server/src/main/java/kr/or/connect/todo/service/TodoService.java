package kr.or.connect.todo.service;

import java.util.Collection;

import org.springframework.stereotype.Service;

import kr.or.connect.todo.domain.Todo;
import kr.or.connect.todo.persistence.TodoDao;

@Service
public class TodoService {
	private TodoDao dao;

	public TodoService(TodoDao dao) {
		this.dao = dao;
	}

	public Collection<Todo> findAll() {
		return dao.selectAll();
	}

	public Collection<Todo> findByCompleted(Integer completed) {
		return dao.selectByCompleted(completed);
	}
	
	public Integer insert(Todo todo) {
		// TODO Auto-generated method stub
		return dao.insert(todo);
	}

	public boolean update(Integer id, Integer completed) {
		int affected = dao.update(id, completed);
		return affected == 1;
	}

	public boolean delete(Integer id) {
		int affected = dao.deleteById(id);
		return affected == 1;
	}

	public boolean deleteByCompleted() {
		int affected = dao.deleteByCompleted();
		return affected == 1;
	}


	
	

}