package ie.cit.cloudapp;

import java.util.List;
import java.util.ArrayList;

public class TodoRepository {
	private List<Todo> todos = new ArrayList<Todo>();

	public List<Todo> getTodos() {
		return todos;
	}

	public void addTodo(Todo todo) {
		todos.add(todo);
	}
}
