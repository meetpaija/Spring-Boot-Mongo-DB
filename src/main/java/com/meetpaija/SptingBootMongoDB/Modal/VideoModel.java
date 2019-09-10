package com.meetpaija.SptingBootMongoDB.Modal;

import java.io.InputStream;
import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "VideoCollection")
public class VideoModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4176755406398032584L;
	
	@Id
	private String videoId;
	@Field("objectId")
	private String objectId;
	@Field("stream")
    private InputStream stream;
	private long length;
	private String contentType;
	
	public long getLength() {
		return length;
	}
	public void setLength(long length) {
		this.length = length;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public String getObjectId() {
		return objectId;
	}
	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}
	public InputStream getStream() {
		return stream;
	}
	public void setStream(InputStream stream) {
		this.stream = stream;
	}
	public String getVideoId() {
		return videoId;
	}
	public void setVideoId(String videoId) {
		this.videoId = videoId;
	}

}
