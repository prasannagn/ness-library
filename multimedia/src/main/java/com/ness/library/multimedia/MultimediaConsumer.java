package com.ness.library.multimedia;

import com.ness.library.api.model.Book;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@EnableBinding(Sink.class)
public class MultimediaConsumer {

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Resource
    private MultimediaRepository repository;

    @Resource
    private RestTemplateBuilder builder;

    @Value("${library.service.url}")
    private String libraryServiceUrl;

    @Value("${multimedia.service.url}")
    private String multimediaServiceUrl;

    @StreamListener(target = Sink.INPUT)
    @HystrixCommand
    public void save(Multimedia multimedia) {
        Multimedia document = new Multimedia();
        BeanUtils.copyProperties(multimedia, document);
        Multimedia media = repository.save(document);

        RestTemplate template = builder.build();
        Book book = new Book();
        book.setImageUrl(multimediaServiceUrl + "/v1/" + media.get_id());
        template.patchForObject(libraryServiceUrl + "/books/" + multimedia.getBookId(), book, Object.class);
    }
}
