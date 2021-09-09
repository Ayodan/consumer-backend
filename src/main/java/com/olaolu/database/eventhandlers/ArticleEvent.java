package com.olaolu.database.eventhandlers;

import org.springframework.context.ApplicationEvent;

/**
 * @author akano.olanrewaju  @on 22/05/2020
 */
public class ArticleEvent extends ApplicationEvent {
    public ArticleEvent(Object source, Object object) {
        super(source);
    }
}
