package com.olaolu.database.eventhandlers;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;

/**
 * @author akano.olanrewaju  @on 22/05/2020
 */
@Component
public class ArticlePublishedListener {
    @EventListener
    void handleUserRemovedEvent(ArticleEvent event) {

    }
}
