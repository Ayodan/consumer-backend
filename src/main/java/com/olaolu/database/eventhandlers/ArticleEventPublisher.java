package com.olaolu.database.eventhandlers;

import org.apache.catalina.core.ApplicationPushBuilder;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

/**
 * @author akano.olanrewaju  @on 22/05/2020
 */
@Component
public class ArticleEventPublisher {
    private final ApplicationEventPublisher publisher;
    ArticleEventPublisher(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    void publishEvent(final String name) {
        // Publishing event created by extending ApplicationEvent
        publisher.publishEvent(new ArticleEvent(this, name));
    }

}
