package kr.or.connect.todo.persistence;

public class TodoSqls {
	static String SELECT_BY_ID = "SELECT * FROM todo WHERE id= :id ORDER BY DATE DESC";
	static String SELECT_ALL = "SELECT * FROM todo ORDER BY DATE DESC";
	static String SELECT_BY_COMPLETED = "SELECT * FROM todo WHERE completed= :completed ORDER BY DATE DESC";
	 
	static String DELETE_BY_ID = "DELETE FROM todo WHERE id= :id";
	static String DELETE_BY_COMPLETED = "DELETE FROM todo WHERE completed = 1";

	static String UPDATE = "UPDATE todo SET\n" + "completed = :completed\n" + "WHERE id = :id";
}
