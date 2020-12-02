package com.traanite.plumfish.commons.events.exception;

import lombok.Getter;

@Getter
public class ResourceFileNotFoundException extends RuntimeException {

    private final String filePath;

    public ResourceFileNotFoundException(Throwable cause, String filePath) {
        super(cause);
        this.filePath = filePath;
    }
}
