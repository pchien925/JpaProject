package dev.pc.configs;

import org.mindrot.jbcrypt.BCrypt;

import dev.pc.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

public class Test {
	public static void main(String[] args) {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();
		try {
//			trans.begin();
//			enma.persist(
//					User.builder().username("PhamChien").active(true).password(BCrypt.hashpw("123", BCrypt.gensalt()))
//							.fullname("Phạm Công Chiến").email("pcchien250904@gmail.com").build());
//			// Commit the transaction
//			trans.commit();

			String username = "PhamChien";
			String jpql = "select u from User u where u.username = :username";
			TypedQuery<User> query = enma.createQuery(jpql, User.class);
			query.setParameter("username", username);
			User user = query.getSingleResult();
			System.out.print(user.toString());
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		} finally {
			enma.close();
		}
	}
}
