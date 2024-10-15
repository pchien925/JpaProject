package dev.pc.service.impl;

import java.util.List;

import dev.pc.dao.IVideoDao;
import dev.pc.dao.impl.VideoDaoImpl;
import dev.pc.entity.Video;
import dev.pc.service.IVideoService;

public class VideoServiceImpl implements IVideoService {

	IVideoDao dao = new VideoDaoImpl();

	@Override
	public void create(Video video) {
		dao.create(video);
	}

	@Override
	public void update(Video video) {
		dao.update(video);
	}

	@Override
	public void delete(String id) {
		dao.delete(id);
	}

	@Override
	public Video findById(String id) throws Exception {
		return dao.findById(id);
	}

	@Override
	public List<Video> findAll() {
		return dao.findAll();
	}

}
