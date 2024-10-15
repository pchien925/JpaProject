package dev.pc.service;

import java.util.List;

import dev.pc.entity.Video;

public interface IVideoService {
	void create(Video video);

	void update(Video video);

	void delete(String id);

	Video findById(String id) throws Exception;

	List<Video> findAll();
}
