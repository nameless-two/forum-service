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
import telran.java52.security.model.User;

@Component
@RequiredArgsConstructor
@Order(30)
public class UserUpdateFilter implements Filter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;

		if (checkEndpoint(request.getMethod(), request.getServletPath())) {
				User user = (User) request.getUserPrincipal();
				if (!user.getName().equalsIgnoreCase(getLoginFromPath(request.getServletPath()))) {
					response.sendError(403);
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
