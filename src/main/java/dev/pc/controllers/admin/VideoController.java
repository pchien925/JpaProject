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
import dev.pc.entity.Video;
import dev.pc.service.IVideoService;
import dev.pc.service.impl.VideoServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

@MultipartConfig()
@WebServlet(urlPatterns = { "/admin/videos", "/admin/video/add", "/admin/video/insert", "/admin/video/edit",
		"/admin/video/update", "/admin/video/delete" })
public class VideoController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	IVideoService videoService = new VideoServiceImpl();
	private static final Cloudinary cloudinary = CloudinaryConfig.cloudinary();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();

		if (url.contains("/admin/videos")) {

			List<Video> list = videoService.findAll();
			req.setAttribute("listvideo", list);

			req.getRequestDispatcher("/views/admin/list-video.jsp").forward(req, resp);
		} else if (url.contains("/admin/video/add")) {

			req.getRequestDispatcher("/views/admin/add-video.jsp").forward(req, resp);
		} else if (url.contains("/admin/video/edit")) {

			String id = req.getParameter("id");
			try {
				Video video = videoService.findById(id);

				req.setAttribute("video", video);

				req.getRequestDispatcher("/views/admin/edit-video.jsp").forward(req, resp);
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {

			String id = (String) req.getParameter("id");

			try {
				videoService.delete(id);
				cloudinary.uploader().destroy(id, ObjectUtils.asMap("resource_type", "video"));

			} catch (Exception e) {
				e.printStackTrace();
			}
			resp.sendRedirect(req.getContextPath() + "/admin/videos");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();

		if (url.contains("/admin/video/insert")) {
			String title = req.getParameter("title");
			int status = Integer.parseInt(req.getParameter("status"));
			Part videoPart = req.getPart("video");
			File fileUpload = convertPartToFile(videoPart);
			Map<String, Object> uploadResult = cloudinary.uploader().upload(fileUpload,
					ObjectUtils.asMap("resource_type", "video"));
			String videoUrl = (String) uploadResult.get("url");
			String id = (String) uploadResult.get("public_id");
			String format = (String) uploadResult.get("format");

			videoService.create(
					Video.builder().videoId(id).title(title).status(status).videoUrl(videoUrl).format(format).build());
			resp.sendRedirect(req.getContextPath() + "/admin/videos");
		} else if (url.contains("/admin/video/update")) {

			try {
				Video video = videoService.findById((String) req.getParameter("videoId"));

				String title = req.getParameter("title");
				int status = Integer.parseInt(req.getParameter("status"));
				Part videoPart = req.getPart("video");
				if (videoPart != null && videoPart.getSize() > 0) {
					File fileUpload = convertPartToFile(videoPart);
					Map<String, Object> uploadResult = cloudinary.uploader().upload(fileUpload,
							ObjectUtils.asMap("resource_type", "video"));
					video.setVideoUrl((String) uploadResult.get("url"));
					video.setVideoId((String) uploadResult.get("public_id"));
					video.setFormat((String) uploadResult.get("format"));

				}
				video.setTitle(title);
				video.setStatus(status);
				videoService.update(video);

			} catch (Exception e) {
				e.printStackTrace();
			}

			resp.sendRedirect(req.getContextPath() + "/admin/videos");
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