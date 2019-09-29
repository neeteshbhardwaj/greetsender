package com.trickyjava.greetsender.repository;

import com.trickyjava.greetsender.model.Recording;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RecordingRepository extends MongoRepository<Recording, String> {
}
