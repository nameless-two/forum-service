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
import telran.java52.accounting.dto.exception.UserNotFoundException;
import telran.java52.accounting.model.UserAccount;

@Component
@RequiredArgsConstructor
@Order(30)
public class UserUpdateFilter implements Filter {

	final AccountingRepository accountingRepository;

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;

		if (checkEndpoint(request.getMethod(), request.getServletPath())) {
			try {
				String login = request.getUserPrincipal().getName();
				UserAccount userAccount = accountingRepository.findById(login).orElseThrow(UserNotFoundException::new);

				if (!userAccount.getLogin().equalsIgnoreCase(getLoginFromPath(request.getServletPath()))) {
					throw new RuntimeException();
				}

			} catch (Exception e) {
				response.sendError(401);
				return;
			}
		}

		chain.doFilter(request, response);

	}

	private String getLoginFromPath(String servletPath) {
		return servletPath.split("/")[3];
	}

	private boolean checkEndpoint(String method, String path) {
		return HttpMethod.PUT.matches(method) && path.matches("/account/user/\\w+");
	}

}
