package dev.pc.dao.impl;

import java.util.List;
import dev.pc.configs.JPAConfig;
import dev.pc.dao.IVideoDao;
import dev.pc.entity.Video;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

public class VideoDaoImpl implements IVideoDao {

	@Override
	public void create(Video video) {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();

		try {
			trans.begin();
			enma.persist(video);
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		} finally {
			enma.close();
		}
	}

	@Override
	public void update(Video video) {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();

		try {
			trans.begin();
			enma.merge(video);
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
			throw e;
		} finally {
			enma.close();
		}
	}

	@Override
	public void delete(String id) {
		EntityManager enma = JPAConfig.getEntityManager();
		EntityTransaction trans = enma.getTransaction();

		try {
			Video video = enma.find(Video.class, id);
			if (video == null) {
				throw new Exception("Video not existed");
			}

			trans.begin();
			enma.remove(video);
			trans.commit();
		} catch (Exception e) {
			e.printStackTrace();
			trans.rollback();
		} finally {
			enma.close();
		}
	}

	@Override
	public Video findById(String id) throws Exception {
		EntityManager enma = JPAConfig.getEntityManager();
		Video video = enma.find(Video.class, id);
		if (video == null)
			throw new Exception("Video not exitsted");
		return video;
	}

	@Override
	public List<Video> findAll() {
		EntityManager enma = JPAConfig.getEntityManager();
		TypedQuery<Video> query = enma.createNamedQuery("Video.findAll", Video.class);

		return query.getResultList();
	}

}
