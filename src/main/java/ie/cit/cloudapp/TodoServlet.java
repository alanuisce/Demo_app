package ie.cit.cloudapp;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class TodoServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		TodoRepository repo = getRepo(req);
		req.setAttribute("todos", repo.getTodos());
		doForward(req, resp);
	}

	private void doForward(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				"/todo_list.jsp");
		rd.forward(req, resp);
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		TodoRepository repo = getRepo(req);
		repo.getTodos().remove(Integer.valueOf(req.getParameter("todoId")) - 1);
		req.setAttribute("todos", repo.getTodos());
		doForward(req, resp);
	}
	
	
	protected void doUpdate(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		TodoRepository repo = getRepo(req);
		Todo todo = repo.getTodos().get(Integer.valueOf(req.getParameter("todoId")) - 1);
		todo.setDone(!todo.isDone());
		req.setAttribute("todos", repo.getTodos());
		doForward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String methodParam = req.getParameter("_method");
		if(methodParam != null && methodParam.equals("delete"))
		{
			doDelete(req, resp);
		}
		if(methodParam != null && methodParam.equals("put"))
		{
			doUpdate(req, resp);
		}
		if(methodParam != null && methodParam != "delete" && methodParam != "put")
		{
			TodoRepository repo = getRepo(req);
			Todo todo = new Todo();
			todo.setText(req.getParameter("text"));
			repo.addTodo(todo);
			req.setAttribute("todos", repo.getTodos());
			doForward(req, resp);
		}
	}

	private TodoRepository getRepo(HttpServletRequest req) {
		HttpSession session = req.getSession(true);
		TodoRepository repo = (TodoRepository) session.getAttribute("repo");
		if (repo == null) {
			repo = new TodoRepository();
			session.setAttribute("repo", repo);
		}
		return repo;
	}
}
