package com.baeldung.mongodb.daos;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.baeldung.mongodb.models.User;

@Repository("userAutoGeneratedFieldRepository")
public interface UserRepository extends MongoRepository<User, Long> {

}
