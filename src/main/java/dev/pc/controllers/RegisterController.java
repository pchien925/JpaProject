package dev.pc.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Random;

import org.mindrot.jbcrypt.BCrypt;

import dev.pc.dto.request.RegisterRequest;
import dev.pc.entity.Role;
import dev.pc.entity.RoleName;
import dev.pc.service.IEmailService;
import dev.pc.service.IUserService;
import dev.pc.service.impl.EmailService;
import dev.pc.service.impl.UserServiceImpl;

@WebServlet(urlPatterns = "/register")
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	IUserService service = new UserServiceImpl();
	IEmailService emailService = new EmailService();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/views/web/register.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		response.setContentType("text/html");
//		response.setCharacterEncoding("UTF-8");
//		request.setCharacterEncoding("UTF-8");
//
//		String username = request.getParameter("username");
//		String password = request.getParameter("password");
//		String fullname = request.getParameter("fullname");
//		String email = request.getParameter("email");
//		String phone = request.getParameter("phone");
//
//		String alertMes = "";
//		if (service.checkExistEmail(email)) {
//			alertMes = "Email đã tồn tại";
//			request.setAttribute("message", alertMes);
//			request.getRequestDispatcher("/views/web/register.jsp").forward(request, response);
//			return;
//		} else if (service.checkExistPhone(email)) {
//			alertMes = "SĐT đã tồn tại";
//			request.setAttribute("message", alertMes);
//			request.getRequestDispatcher("/views/web/register.jsp").forward(request, response);
//			return;
//		} else if (service.checkExistUsername(email)) {
//			alertMes = "Tên đăng nhập đã tồn tại";
//			request.setAttribute("message", alertMes);
//			request.getRequestDispatcher("/views/web/register.jsp").forward(request, response);
//			return;
//		} else {
//
//			Random rd = new Random();
//			int number = rd.nextInt(9999999);
//			String code = String.format("%06d", number);
//
////			String username;
////			String password;
////			String email;
////			String Code;
////			String phone;
////			String fullname;
////			String images;
////			boolean active;
////			Role role;
//
//			RegisterRequest registerRequest = RegisterRequest.builder().username(username)
//					.password(BCrypt.hashpw(password, BCrypt.gensalt())).email(email).active(false).code(code)
//					.phone(phone).fullname(fullname).images(null).role(null).build();
//
//			boolean sendMail = emailService.comfirmRegistered(registerRequest);
//
//			if (sendMail) {
//				HttpSession session = request.getSession();
//				session.setAttribute("userAccount", registerRequest);
////				boolean isRegister = service.register(user.getUsername(), user.getPassword(), user.getEmail(),
////						user.getFullname(), user.getPhone(), user.getStatus(), user.getCode(), user.getRoleid(),
////						user.getCreateDate());
////				if (isRegister) {
////					response.sendRedirect(request.getContextPath() + "/verifyCode");
////				} else {
////					alertMes = "Lỗi đăng kí";
////					request.setAttribute("message", alertMes);
////					request.getRequestDispatcher("/views/web/register.jsp").forward(request, response);
////					return;
////				}
//
//			} else {
//				PrintWriter out = response.getWriter();
//				out.println("Lỗi gửi mail! Vui lòng kiểm tra lại!");
//			}
//		}
//	}
	}
}
