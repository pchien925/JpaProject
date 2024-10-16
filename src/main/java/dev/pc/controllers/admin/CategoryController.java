package dev.pc.controllers.admin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import dev.pc.configs.CloudinaryConfig;
import dev.pc.entity.Category;
import dev.pc.service.ICategoryService;
import dev.pc.service.impl.CategoryServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@MultipartConfig()
@WebServlet(urlPatterns = { "/admin/categories", "/admin/category/add", "/admin/category/insert",
		"/admin/category/edit", "/admin/category/update", "/admin/category/delete" })
public class CategoryController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final Cloudinary cloudinary = CloudinaryConfig.cloudinary();
	ICategoryService cateService = new CategoryServiceImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String url = req.getRequestURI();

		if (url.contains("/admin/categories")) {

			List<Category> list = cateService.findAll();
			req.setAttribute("listcate", list);

			req.getRequestDispatcher("/views/admin/list-category.jsp").forward(req, resp);

		} else if (url.contains("/admin/category/add")) {

			req.getRequestDispatcher("/views/admin/add-category.jsp").forward(req, resp);

		} else if (url.contains("/admin/category/edit")) {

			String id = req.getParameter("id");

			Category category = cateService.findById(id);

			req.setAttribute("cate", category);

			req.getRequestDispatcher("/views/admin/edit-category.jsp").forward(req, resp);

		} else {

			String id = (String) req.getParameter("id");

			try {
				cateService.delete(id);

			} catch (Exception e) {
				e.printStackTrace();
			}
			resp.sendRedirect(req.getContextPath() + "/admin/categories");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();
		if (url.contains("/admin/category/insert")) {
			// lấy dữ liệu từ form
			String categoryname = req.getParameter("categoryname");
			int status = Integer.parseInt(req.getParameter("status"));
			Part imagePart = req.getPart("images");
			File fileUpload = convertPartToFile(imagePart);
			Map<String, Object> uploadResult = cloudinary.uploader().upload(fileUpload, ObjectUtils.emptyMap());
			String images = (String) uploadResult.get("url");

			cateService.create(Category.builder().categoryName(categoryname).images(images).status(status).build());

			resp.sendRedirect(req.getContextPath() + "/admin/categories");
		}

		if (url.contains("/admin/category/update")) {
			Category category = cateService.findById((String) req.getParameter("categoryId"));
			category.setCategoryName(req.getParameter("categoryname"));
			int status = Integer.parseInt(req.getParameter("status"));
			category.setStatus(status);
			Part imagePart = req.getPart("images");

			if (imagePart != null && imagePart.getSize() > 0) {
				File fileUpload = convertPartToFile(imagePart);
				Map<String, Object> uploadResult = cloudinary.uploader().upload(fileUpload, ObjectUtils.emptyMap());
				String images = (String) uploadResult.get("url");
				category.setImages(images);
			}

			cateService.update(category);
			resp.sendRedirect(req.getContextPath() + "/admin/categories");
		}
	}

	private File convertPartToFile(Part filePart) throws IOException {
		// Tạo tệp tạm thời
		File tempFile = File.createTempFile("upload_", ".tmp");

		try (InputStream inputStream = filePart.getInputStream();
				FileOutputStream outputStream = new FileOutputStream(tempFile)) {

			byte[] buffer = new byte[1024];
			int bytesRead;

			while ((bytesRead = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, bytesRead);
			}
		}

		return tempFile;
	}
}
