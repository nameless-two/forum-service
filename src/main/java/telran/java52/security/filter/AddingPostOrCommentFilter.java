package telran.java52.security.filter;

import java.io.IOException;

import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import telran.java52.accounting.dao.AccountingRepository;
import telran.java52.accounting.model.UserAccount;

@Component
@RequiredArgsConstructor
@Order(50)
public class AddingPostOrCommentFilter implements Filter {

	final AccountingRepository accountingRepository;

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;

		if (checkEndpoint(request.getMethod(), request.getServletPath())) {
			String login = request.getUserPrincipal().getName();
			UserAccount userAccount = accountingRepository.findById(login).get();

			if (!userAccount.getLogin().equalsIgnoreCase(getUserFromPath(request.getServletPath()))) {
				throw new RuntimeException();
			}
		}

		chain.doFilter(request, response);

	}

	private String getUserFromPath(String servletPath) {
		String[] res = servletPath.split("/");
		return res[res.length-1];
	}

	private boolean checkEndpoint(String method, String path) {
		return (HttpMethod.POST.matches(method) && path.matches("/forum/post/\\w+"))
				|| (HttpMethod.PUT.matches(method) && path.matches("/forum/post/\\w+/comment/\\w+"));
	}

}
