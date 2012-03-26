package ie.cit.cloudapp;

import java.util.ArrayList;
import java.util.List;

public class ToDoRepository {
	private List<ToDo> todos = new ArrayList<ToDo>();

	public List<ToDo> getToDos() {
		return todos;
	}

	public void addToDo(ToDo todo) {
		todos.add(todo);
	}
}
