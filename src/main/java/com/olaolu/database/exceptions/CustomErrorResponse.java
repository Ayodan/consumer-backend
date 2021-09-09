package com.olaolu.database.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.thymeleaf.spring5.SpringTemplateEngine;

import java.time.LocalDateTime;

/**
 * @author akano.olanrewaju  @on 02/10/2019
 */
public class CustomErrorResponse {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss")
    private LocalDateTime timestamp;
    private int status;
    private String error;

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
