package dev.pc.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import dev.pc.dto.request.LoginRequest;
import dev.pc.entity.User;
import dev.pc.service.IUserService;
import dev.pc.service.impl.UserServiceImpl;
import dev.pc.utils.Constant;

@WebServlet(urlPatterns = "/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	IUserService service = new UserServiceImpl();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if (session != null && session.getAttribute("userAccount") != null) {
			response.sendRedirect(request.getContextPath() + "/home");
			return;
		}

		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("username")) {
					session = request.getSession(true);
					session.setAttribute("username", cookie.getValue());
					response.sendRedirect(request.getContextPath() + "/home");
					return;
				}
			}
		}
		request.getRequestDispatcher("views/web/login.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		boolean isRememberMe = false;
		String remember = request.getParameter("remember");
		if ("on".equals(remember)) {
			isRememberMe = true;
		}

		String alertMes = "";
		if (username.isEmpty() || password.isEmpty()) {
			alertMes = "Sai tài khoản hoặc mật khẩu";
			request.setAttribute("message", alertMes);

			request.getRequestDispatcher("/views/web/login.jsp").forward(request, response);
			return;
		}

		try {
			User user = service.Login(LoginRequest.builder().username(username).password(password).build());

			if (user != null) {
				if (user.isActive() == true) {
					HttpSession session = request.getSession(true);
					session.setAttribute("userAccount", user);
					if (isRememberMe) {
						SaveRememberMe(response, username);
					}

					request.getRequestDispatcher("/views/web/home.jsp").forward(request, response);
				} else {
					alertMes = "Tài khoản đã bị khóa";
					request.setAttribute("error", alertMes);
					request.getRequestDispatcher("/views/web/login.jsp").forward(request, response);
				}

			} else {
				alertMes = "Sai tài khoản hoặc mật khẩu";
				request.setAttribute("message", alertMes);
				request.getRequestDispatcher("/views/web/login.jsp").forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void SaveRememberMe(HttpServletResponse response, String username) {
		Cookie cookie = new Cookie(Constant.COOKIE_REMEMBER, username);
		cookie.setMaxAge(1800);
		cookie.setHttpOnly(true);
		cookie.setSecure(true);
		cookie.setPath("/");
		response.addCookie(cookie);
	}

}
