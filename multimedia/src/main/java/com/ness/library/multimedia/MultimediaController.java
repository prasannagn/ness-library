package com.ness.library.multimedia;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;


@RestController
@RequestMapping(value = "/v1")
public class MultimediaController {

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Resource
    private MultimediaRepository repository;

    @Resource
    private MultimediaPublisher publisher;

    @RequestMapping(value = "/", method = POST)
    public void create(@RequestParam("data") MultipartFile file, @RequestParam("bookId") String id) throws IOException {
        Multimedia multimedia = new Multimedia();
        multimedia.setName(file.getOriginalFilename());
        multimedia.setContentType(file.getContentType());
        multimedia.setSize(file.getSize());
        multimedia.setContent(file.getBytes());
        multimedia.setBookId(id);
        publisher.publishMessage(multimedia);
    }

    @RequestMapping(value = "/{id}", method = GET)
    public ResponseEntity<byte[]> findOne(@PathVariable("id") String id) {
        Multimedia multimedia = repository.findBy_id(id);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.valueOf(multimedia.getContentType()));
        return new ResponseEntity<>(multimedia.getContent(), headers, HttpStatus.OK);
    }
}