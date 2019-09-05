package com.meetpaija.SptingBootMongoDB.Repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.meetpaija.SptingBootMongoDB.Modal.User;

@Repository
public interface IUserRepository extends MongoRepository<User, String>{

	@Query("{ 'fname' : ?0 }")
	public List<User> getByFname(String fname);
}
