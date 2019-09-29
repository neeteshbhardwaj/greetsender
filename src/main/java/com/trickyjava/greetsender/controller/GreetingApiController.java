package com.trickyjava.greetsender.controller;

import com.trickyjava.greetsender.model.GreetingCard;
import com.trickyjava.greetsender.repository.GreetingCardRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class GreetingApiController {

    private static final Logger LOGGER = LoggerFactory.getLogger(GreetingApiController.class);

    @Autowired
    private GreetingCardRepository repository;

    @GetMapping({"", "/", "/index"})
    public List<GreetingCard> all(){
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public GreetingCard findById(@PathVariable("id") String id){
        LOGGER.info("[START] findById : {}", id);
        Optional<GreetingCard> greetingCard = repository.findById(id);
        GreetingCard card = greetingCard.orElseGet(GreetingCard::new);
        LOGGER.info("[END] findById : {}", id);
        return card;
    }

    @PostMapping({"", "/", "/index"})
    public GreetingCard createOrUpdate(@RequestBody GreetingCard greetingCard){
        return repository.save(greetingCard);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") String id){
        repository.deleteById(id);
    }
}
