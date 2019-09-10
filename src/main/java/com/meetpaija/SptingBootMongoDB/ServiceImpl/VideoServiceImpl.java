package com.meetpaija.SptingBootMongoDB.ServiceImpl;

import java.io.IOException;
import java.io.InputStream;

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
 
    public String addVideo(String objectId, MultipartFile file) throws IOException { 
    	GridFSFile existingFile = gridFsTemplate.findOne(new Query(Criteria.where("metadata.objectId").is(objectId)));
    	if(existingFile!=null){
    		return existingFile.getMetadata().get("objectId").toString();
    	}
        DBObject metaData = new BasicDBObject(); 
        metaData.put("type", "video"); 
        metaData.put("objectId", objectId); 
        ObjectId id = gridFsTemplate.store(
          file.getInputStream(), file.getOriginalFilename(), file.getContentType(), metaData); 
        return id.toString(); 
    }
 
    public VideoModel getVideo(String id) throws IllegalStateException, IOException { 
        GridFSFile file = gridFsTemplate.findOne(new Query(Criteria.where("metadata.objectId").is(id))); 
        VideoModel video = new VideoModel();
        video.setObjectId(file.getMetadata().get("objectId").toString()); 
        video.setStream(operations.getResource(file.getFilename()).getInputStream());
        video.setLength(file.getLength());
        video.setContentType(file.getMetadata().get("_contentType").toString());
        return video; 
    }

	@Override
	public void deleteVideo(String id) {
		gridFsTemplate.delete(new Query(Criteria.where("_id").is(id)));
	}

	@Override
	public String addBinaryData(String objectId, InputStream stream) {
		GridFSFile existingFile = gridFsTemplate.findOne(new Query(Criteria.where("metadata.objectId").is(objectId)));
    	if(existingFile!=null){
    		return existingFile.getMetadata().get("objectId").toString();
    	}
        DBObject metaData = new BasicDBObject(); 
        metaData.put("type", "video"); 
        metaData.put("objectId", objectId); 
        ObjectId id = gridFsTemplate.store(
        		stream, metaData); 
        return objectId.toString(); 
	}

}
