package com.trickyjava.greetsender.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trickyjava.greetsender.model.GreetingCard;
import com.trickyjava.greetsender.repository.GreetingCardRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
public class GreetingController {

    private static final Logger LOGGER = LoggerFactory.getLogger(GreetingController.class);

    @Autowired
    private GreetingCardRepository repository;

    private ObjectMapper objectMapper = new ObjectMapper();

    @GetMapping("/a/{id}")
    public ModelAndView greetingA(@PathVariable("id") String id, @RequestParam Map<String, String> params) {
        GreetingCard greetingCard = repository.findByIdAndEnabled(id, true);
        ModelAndView modelAndView = new ModelAndView(greetingCard.getView());
        modelAndView.addObject("greetingCard", greetingCard);
        modelAndView.addAllObjects(params);
        return modelAndView;
    }

}
