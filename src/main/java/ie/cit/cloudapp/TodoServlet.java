package ie.cit.cloudapp;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class TodoServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		TodoRepository repo = getRepo(req);
		req.setAttribute("todos", repo.getTodos());
		doForward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		TodoRepository repo = getRepo(req);
		Todo todo = new Todo();
		todo.setText(req.getParameter("text"));
		repo.addTodo(todo);

		req.setAttribute("todos", repo.getTodos());
		doForward(req, resp);
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Integer index = Integer.valueOf(req.getParameter("todoId"));
		TodoRepository repo = getRepo(req);
		Todo todo = repo.getTodos().get(index - 1);
		todo.setDone(!todo.isDone());
		req.setAttribute("todos", repo.getTodos());
		doForward(req, resp);
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		Integer index = Integer.valueOf(req.getParameter("todoId"));
		TodoRepository repo = getRepo(req);
		repo.getTodos().remove(index - 1);
		req.setAttribute("todos", repo.getTodos());
		doForward(req, resp);
	}

	private TodoRepository getRepo(HttpServletRequest req) {
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(getServletContext());
		return ctx.getBean("repo", TodoRepository.class);
	}

	private void doForward(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		RequestDispatcher rd = getServletContext().getRequestDispatcher(
				"/todo_list.jsp");
		rd.forward(req, resp);
	}
}
