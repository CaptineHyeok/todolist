package kr.or.connect.todo;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import kr.or.connect.todo.domain.Todo;
import kr.or.connect.todo.persistence.TodoDao;

@RunWith(SpringRunner.class)
@SpringBootTest
//@Transactional
public class TodoDaoTests {

	@Autowired
	private TodoDao dao;
	
	@Test
	public void shouldSelectAll()
	{
		dao.selectAll();
	}
	
	@Test
	public void shouldDelete()
	{
		dao.deleteById(164);
	}
	
	@Test
	public void shouldUpdate()
	{
		dao.update(165, 0);
	}
	
	@Test
	public void shouldDeleteByCompleted()
	{
		dao.deleteByCompleted();
	}

//	@Test
//	public void test() {
//		fail("Not yet implemented");
//	}

}







