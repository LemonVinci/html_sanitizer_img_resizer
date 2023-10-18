package org.farmu.controller;

import java.security.SecureRandom;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.farmu.model.Url;
import org.farmu.repository.URLRepository;
import org.springframework.web.bind.annotation.*;

@RestController
public class URLShortenerController {

    private static final int SHORT_URL_LENGTH = 7;
    private static final String BASE62_CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static SecureRandom random = new SecureRandom();

    @Autowired
    private URLRepository repository;

    @GetMapping("/urls")
    public List<Url> getUrls() {
        return repository.findAll();
    }

    @GetMapping("/url/expand/{shorturl}")
    public String getUrl(@PathVariable String shorturl) {
        Url retrievedUrl = repository.findByShortUrl(shorturl);
        return retrievedUrl.getUrl();
    }

    @PostMapping("/url/short")
    public Url getUrls(@RequestBody String url) {
        return repository.save(Url.builder()
                .url(url)
                .shortUrl(createShorterURL())
                .build());
    }

    private static String createShorterURL() {
        StringBuilder shortURL = new StringBuilder(SHORT_URL_LENGTH);
        for (int i = 0; i < SHORT_URL_LENGTH; i++) {
            int randomIndex = random.nextInt(BASE62_CHARACTERS.length());
            shortURL.append(BASE62_CHARACTERS.charAt(randomIndex));
        }
        return shortURL.toString();
    }
}