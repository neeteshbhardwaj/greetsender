package com.trickyjava.greetsender.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trickyjava.greetsender.model.GreetingCard;
import com.trickyjava.greetsender.model.Recording;
import com.trickyjava.greetsender.repository.GreetingCardRepository;
import com.trickyjava.greetsender.repository.RecordingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.activation.FileTypeMap;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.nio.file.Files;
import java.util.Date;
import java.util.Map;

@Controller
public class GreetingController {

    private static final Logger LOGGER = LoggerFactory.getLogger(GreetingController.class);

    @Autowired
    private GreetingCardRepository repository;

    @Autowired
    private RecordingRepository recordingRepository;

    private ObjectMapper objectMapper = new ObjectMapper();

    @GetMapping("/a/{id}")
    public ModelAndView greetingA(@PathVariable("id") String id, @RequestParam Map<String, String> params) {
        GreetingCard greetingCard = repository.findByIdAndEnabled(id, true);
        ModelAndView modelAndView = new ModelAndView(greetingCard.getView());
        modelAndView.addObject("greetingCard", greetingCard);
        modelAndView.addObject("id", id);
        modelAndView.addAllObjects(params);
        return modelAndView;
    }

    @GetMapping("/rec/{id}")
    public ResponseEntity<byte[]> recorder(@PathVariable("id") String id, @RequestParam Map<String, String> params, HttpServletRequest httpServletRequest) throws Exception{
        Recording recording = new Recording();
        recording.setGreetingKey(id);
        recording.setUser(params.get("n"));
        recording.setBrowserDetails(httpServletRequest.getHeader("User-Agent"));
        recording.setIp(clientIp(httpServletRequest));
        recording.setTimestamp(new Date());
        recordingRepository.save(recording);

        File img = new File("src/main/resources/static/img/img_trans.gif");
        return ResponseEntity.ok().contentType(MediaType.valueOf(FileTypeMap.getDefaultFileTypeMap().getContentType(img))).body(Files.readAllBytes(img.toPath()));
    }

    private static String clientIp(HttpServletRequest request) {

        String remoteAddr = "";

        if (request != null) {
            remoteAddr = request.getHeader("X-FORWARDED-FOR");
            if (remoteAddr == null || "".equals(remoteAddr)) {
                remoteAddr = request.getRemoteAddr();
            }
        }

        return remoteAddr;
    }
}
