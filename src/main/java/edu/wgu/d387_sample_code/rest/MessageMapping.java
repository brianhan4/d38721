package edu.wgu.d387_sample_code.rest;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class MessageMapping {

    private final edu.wgu.d387_sample_code.service.Translation translation;

    public MessageMapping(edu.wgu.d387_sample_code.service.Translation translation) {
        this.translation = translation;
    }

    @GetMapping("/welcome")
    public String[]getWelcomeMessages() {
        try {
            return translation.getWelcomeMessages();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}



