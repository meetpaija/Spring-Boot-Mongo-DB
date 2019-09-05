package com.meetpaija.SptingBootMongoDB.ServiceImpl;

import java.io.IOException;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.meetpaija.SptingBootMongoDB.Modal.VideoModel;
import com.meetpaija.SptingBootMongoDB.Service.IVideoService;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.client.gridfs.model.GridFSFile;

@Service
public class VideoServiceImpl implements IVideoService{
	
	@Autowired
    private GridFsTemplate gridFsTemplate;
 
    @Autowired
    private GridFsOperations operations;
 
    public String addVideo(String title, MultipartFile file) throws IOException { 
        DBObject metaData = new BasicDBObject(); 
        metaData.put("type", "video"); 
        metaData.put("title", title); 
        ObjectId id = gridFsTemplate.store(
          file.getInputStream(), file.getName(), file.getContentType(), metaData); 
        return id.toString(); 
    }
 
    public VideoModel getVideo(String id) throws IllegalStateException, IOException { 
        GridFSFile file = gridFsTemplate.findOne(new Query(Criteria.where("_id").is(id))); 
        VideoModel video = new VideoModel();
        video.setVideoId(id);
        video.setTitle(file.getMetadata().get("title").toString()); 
        video.setStream(operations.getResource(file.getFilename()).getInputStream());
        video.setLength(file.getLength());
        video.setContentType(file.getMetadata().get("_contentType").toString());
        return video; 
    }

	@Override
	public void deleteVideo(String id) {
		gridFsTemplate.delete(new Query(Criteria.where("_id").is(id)));
	}

}
