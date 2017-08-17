package security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class DataSecurityInterceptor implements HandlerInterceptor {

	// used to intercept the request before it’s handed over to the handler
	// method. This method should return ‘true’ to send it to handler.

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 1
		if (request.getSession().getAttribute("sessionUser") != null) {
			return true;
		}
		// 2
		response.sendRedirect("/ParkREST/api/auth/unauthorized");
		return false;
	}

	// below necessary to stub but not used

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub

	}

}
