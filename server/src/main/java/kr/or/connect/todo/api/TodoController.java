package kr.or.connect.todo.api;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import kr.or.connect.todo.domain.Todo;
import kr.or.connect.todo.service.TodoService;

@RestController
@RequestMapping("/api/todos")
public class TodoController {
	private final TodoService service;

	@Autowired
	public TodoController(TodoService service) {
		this.service = service;
	}

	// select
	@GetMapping
	Collection<Todo> readList() {
		return service.findAll();
	}

	@GetMapping("/{completed}")
	Collection<Todo> readListByCompleted(@PathVariable Integer completed) {
		return service.findByCompleted(completed);
	}

	// insert
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	Integer insert(@RequestBody Todo todo) {
		return service.insert(todo);
	}

	// update
	@PutMapping("/{id}/{completed}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	void update(@PathVariable Integer id, @PathVariable Integer completed) {
		service.update(id, completed);
	}

	// delete
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	void delete(@PathVariable Integer id) {
		service.delete(id);
	}

	@DeleteMapping
	@ResponseStatus(HttpStatus.NO_CONTENT)
	void deleteByCompeleted() {
		service.deleteByCompleted();
	}
}

/*
 * rest란? HTTP URI + HTTP Method URI를 통해 제어할 자원을 명시 Method를 통해 get, post, put,
 * delete를 통해 자원을 제어하는 아키텍쳐 http 프로토콜에 정의된 4개의 메서드들이 자원에 대한 CRUD를 정의함. Http
 * Method // CRUD POST // Create(Insert) GET // Read(Select) PUT // Update or
 * Create DELETE // Delete
 */
/*
 * 기존의 게시판 글 읽기는 get방식으로 쿼리스트링을 이용하였음. 하지만 Restful API를 이용하면 서버로 쿼리스트링이 아닌
 * URI형식으로 보냄. 즉 기존의 게시판은 get과 post만으로 자원에 대한 CRUD를 처리했으며 URI는 액션을 나타냈다. Restful
 * 게시판은 4가지 메서드를 모두 사영하여 CRUD를 처리하며, URI는 제어하려는 자원을 나타낸다.
 */
/*
 * 클라이언트에서 XML이나 JSON으로 데이터 전송 JSON(javascript object notation) : 경량 데이터의 데이터 교환
 * 형식 javascript에서 객체를 만들 때 사용하는 표현식을 의미. 사람과 기계 모두 쉽게 이해할 수 있고 용량이 작음. JSON이
 * XML을 대체하는 트렌드. 특정 언어에 종속되지 않고 대부분의 언어에서 JSON 포맷을 다룰 수 있는 라이브러리를 제공함. 형식1 :
 * name-value 형식의 pair ex) { "firstName":"Brett", "lastName":"McLaughlin" } 형식2
 * : 값들의 순서화된 리스트 형식도 가능 ex) ex) { "firstName":"Brett", "lastName":"McLaughlin",
 * "hobby":["puzzles", "swimming"] ///////이 부분 } 이런식으로 pair로 사용.(string:value)
 * 
 * JSON 라이브러리 - jackson : json 형태 -> java 객체 혹은 java 객체 -> json형태로 변환해주는 java용
 * 라이브러리
 */
/*
 * XML과 HTML의 차이 XML : data를 전달하는 것에 포커스를 맞춘 언어, 사용자가 마음대로 Tag를 정의할 수 있다. Html :
 * data를 표현하는 것에 포커스를 맞춘 언어, 미리 정의된 Tag만 사용할 수 있다.
 */
/*
 * Spring MVC Restful 웹서비스 구현 절차 1. 웹서비스를 처리할 Restful컨트롤러 작성 및 스프링 빈으로 등록 2. 요청을
 * 처리할 메서드에 @RequestMapping, @RequestBody, @ResponseBody 어노테이션 선언 3. Rest 클라이언트
 * 툴을 사용하여 각각의 메서드 테스트 4. ajax 통신을 하여 Restful 웹서비스를 호출하는 html 페이지 작성.
 */
/*
 * 어노테이션
 * 
 * @RequestBody : XML, JSON데이터를 JAVA객체로 변환해줌.
 * 
 * @ResponseBody : java객체를 XML, JSON으로 변환해서 보냄.
 */
