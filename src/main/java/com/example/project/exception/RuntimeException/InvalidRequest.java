package com.example.project.exception.RuntimeException;

import lombok.Getter;

/**
 * status->400
 */
@Getter
public class InvalidRequest extends ProjectException{
    private static final String MESSAGE = "Invalid Request";

    public InvalidRequest() {
        super(MESSAGE);
    }

    public InvalidRequest(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public int getStatusCode() {
        return 400;
    }

}
