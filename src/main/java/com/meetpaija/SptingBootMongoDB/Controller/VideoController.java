package com.meetpaija.SptingBootMongoDB.Controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.meetpaija.SptingBootMongoDB.Modal.VideoModel;
import com.meetpaija.SptingBootMongoDB.Service.IVideoService;

@RestController
@RequestMapping("/api")
public class VideoController {
	
	@Autowired
	IVideoService videoService;
	
	@PostMapping("/videos/add")
	public ResponseEntity<Object> addVideo(@RequestParam("title") String title, 
	  @RequestParam("file") MultipartFile file) throws IOException {
	    String id = videoService.addVideo(title, file);
	    return new ResponseEntity<Object>(id,HttpStatus.OK);
	}
	
	@GetMapping("/videos/{id}")
	public ResponseEntity<Object> getVideo(@PathVariable String id) throws Exception {
	    VideoModel video = videoService.getVideo(id);
	    return ResponseEntity.ok()
        .contentLength(video.getLength())
        .contentType(MediaType.parseMediaType(video.getContentType()))
        .body(new InputStreamResource(video.getStream())); 
	}
	
	@GetMapping("/videos/stream/{id}")
	public void streamVideo(@PathVariable String id, HttpServletResponse response) throws Exception {
	    VideoModel video = videoService.getVideo(id);
	    FileCopyUtils.copy(video.getStream(), response.getOutputStream());        
	}
	
	@DeleteMapping("/videos/{id}")
	public void deleteVideo(@PathVariable String id) throws Exception {
		videoService.deleteVideo(id);
	}

}
