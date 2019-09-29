package com.trickyjava.greetsender.repository;

import com.trickyjava.greetsender.model.GreetingCard;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GreetingCardRepository extends MongoRepository<GreetingCard, String> {

    GreetingCard findByIdAndEnabled(String id, boolean enabled);
}
