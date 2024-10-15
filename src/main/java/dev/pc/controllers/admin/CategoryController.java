package dev.pc.controllers.admin;

import java.io.IOException;

import java.util.List;
import dev.pc.entity.Category;
import dev.pc.service.ICategoryService;
import dev.pc.service.impl.CategoryServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@MultipartConfig()
@WebServlet(urlPatterns = { "/admin/categories", "/admin/category/add", "/admin/category/insert",
		"/admin/category/edit", "/admin/category/update", "/admin/category/delete" })
public class CategoryController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	ICategoryService cateService = new CategoryServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String url = req.getRequestURI();
		System.out.print(url);

		if (url.contains("/admin/categories")) {

			List<Category> list = cateService.findAll();
			req.setAttribute("listcate", list);

			req.getRequestDispatcher("/views/admin/list-category.jsp").forward(req, resp);

		} else if (url.contains("/admin/category/add")) {

			req.getRequestDispatcher("/views/admin/add-category.jsp").forward(req, resp);

		} else if (url.contains("/admin/category/edit")) {

			int id = Integer.parseInt(req.getParameter("id"));

			Category category = cateService.findById(id);

			req.setAttribute("cate", category);

			req.getRequestDispatcher("/views/admin/category-edit.jsp").forward(req, resp);

		} else {

			int id = Integer.parseInt(req.getParameter("id"));

			try {

				cateService.delete(id);

			} catch (Exception e) {

				// TODO Auto-generated catch block

				e.printStackTrace();
			}
			resp.sendRedirect(req.getContextPath() + "/admin/categories");
		}
	}

	@Override

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//		String url = req.getRequestURI();
//
//		if (url.contains("/admin/category/insert")) {
//
//			// lấy dữ liệu từ form
//
//			String categoryname = req.getParameter("categoryname");
//
//			int status = Integer.parseInt(req.getParameter("status"));
//
//			String images = req.getParameter("images");
//
//			// đưa dữ liệu vào model
//
//			Category category = new Category();
//
//			category.setCategoryname(categoryname);
//
//			category.setStatus(status);
//
//			String fname = "";
//
//
//			try {
//
//				Part part = req.getPart("images1");
//
//				if (part.getSize() > 0) {
//
//					String filename = Paths.get(part.getSubmittedFileName()).getFileName().toString();
//
//					int index = filename.lastIndexOf(".");
//
//					String ext = filename.substring(index + 1);
//
//					fname = System.currentTimeMillis() + "." + ext;
//
//					part.write(uploadPath + "/" + fname);
//
//					category.setImages(fname);
//
//				} else if (images != null) {
//
//					category.setImages(images);
//
//				} else {
//
//					category.setImages("avatar.png");
//
//				}
//
//			} catch (FileNotFoundException fne) {
//
//				fne.printStackTrace();
//
//			}
//
//			// đưa model vào phương thức insert
//
//			cateService.insert(category);
//
//			// chuyển trang
//
//			resp.sendRedirect(req.getContextPath() + "/admin/categories");
	}
}
