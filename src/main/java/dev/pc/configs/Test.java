package dev.pc.configs;

import java.time.LocalDateTime;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

import dev.pc.entity.Category;
import dev.pc.entity.User;
import dev.pc.entity.Video;
import dev.pc.service.ICategoryService;
import dev.pc.service.IVideoService;
import dev.pc.service.impl.CategoryServiceImpl;
import dev.pc.service.impl.VideoServiceImpl;
import jakarta.persistence.Column;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.TypedQuery;

public class Test {
	public static void main(String[] args) {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {

			IVideoService service = new VideoServiceImpl();
			trans.begin();
			
			service.create(Video.builder()
					.videoId("samples/elephants")
					.title("Voi")
					.thumbnailUrl("https://res.cloudinary.com/dh0jqp0gf/image/upload/v1727231768/samples/landscapes/architecture-signs.jpg")
					.format(".mp4")
					.videoUrl("https://res.cloudinary.com/dh0jqp0gf/video/upload/v1727231770/samples/elephants.mp4")
					.build());
			trans.commit();
			
//			private String videoId;
//
//			@Column(name = "Title")
//			private String title;
//			
//			@Column(name = "Format")
//			private String format;
//
//			@Column(name = "Thumbnail_url")
//			private String thumbnailUrl;
//
//			@Column(name = "Created_at")
//			private LocalDateTime createdAt;
//
//			@Column(name = "Video_url")
//			private String videoUrl;
//
//			@ManyToOne
//			@JoinColumn(name = "CategoryId")
//			private Category category;
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
		} finally {
			enma.close();
		}
	}
}
