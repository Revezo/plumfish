package com.traanite.plumfish.commons.events.exception;

import lombok.Getter;

@Getter
public class NullResponseException extends Exception {

    private final String endpoint;
    private final String body;

    public NullResponseException(String endpoint, String body) {
        super("Null response when sending request for endpoint: " + endpoint);
        this.endpoint = endpoint;
        this.body = body;
    }

    public NullResponseException(String endpoint) {
        super("Null response when sending request for endpoint: " + endpoint);
        this.endpoint = endpoint;
        this.body = "";
    }
}
