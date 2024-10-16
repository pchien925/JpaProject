package dev.pc.dao;

import java.util.List;

import dev.pc.entity.Video;

public interface IVideoDao {
	void create(Video video);

	void update(Video video);

	void delete(String id);

	Video findById(String id) throws Exception;

	List<Video> findByTitle(String title) throws Exception;

	List<Video> findAll();
}
