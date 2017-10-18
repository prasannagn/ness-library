package com.ness.library.multimedia;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;

import javax.annotation.Resource;


@EnableBinding(Source.class)
public class MultimediaPublisher {

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Resource
    private Source source;

    public void publishMessage(Multimedia multimedia) {
        //send message to channel
        source.output().send(MessageBuilder.withPayload(multimedia).build());
    }

}
