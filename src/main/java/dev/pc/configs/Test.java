package dev.pc.configs;

import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

import dev.pc.entity.Category;
import dev.pc.entity.User;
import dev.pc.entity.Video;
import dev.pc.service.ICategoryService;
import dev.pc.service.impl.CategoryServiceImpl;
import jakarta.persistence.Column;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.OneToMany;
import jakarta.persistence.TypedQuery;

public class Test {
	public static void main(String[] args) {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
			trans.begin();
			ICategoryService service = new CategoryServiceImpl();
			List<Category> list = service.findAll();
			
			for(var l : list)
			{
				System.out.print(l.getImages() + "/n");
			}
			trans.commit();

//			private int categoryId;
//
//			@Column(name = "Categoryname", columnDefinition = "NVARCHAR(50) NOT NULL")
//			private String categoryname;
//
//			@Column(name = "Images", columnDefinition = "VARCHAR(3000) NULL")
//			private String images;
//
//			@Column(name = "Status")
//			private int status;
//
//			@OneToMany(mappedBy = "category")
//			private List<Video> videos;
//			String username = "PhamChien";
//			String jpql = "select u from User u where u.username = :username";
//			TypedQuery<User> query = enma.createQuery(jpql, User.class);
//			query.setParameter("username", username);
//			User user = query.getSingleResult();
//			System.out.print(user.toString());
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		} finally {
			enma.close();
		}
	}
}
