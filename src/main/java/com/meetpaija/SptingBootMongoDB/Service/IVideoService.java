package com.meetpaija.SptingBootMongoDB.Service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.meetpaija.SptingBootMongoDB.Modal.VideoModel;

public interface IVideoService {

	String addVideo(String title, MultipartFile file) throws IOException;

	VideoModel getVideo(String id) throws IllegalStateException, IOException;

	void deleteVideo(String id);

}
