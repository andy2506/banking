package com.banking.banking.event;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PublishResponse {
    private String response;

    @JsonCreator
    public PublishResponse(@JsonProperty("response") String response) {
        this.response = response;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public static PublishResponseBuilder builder() {
        return new PublishResponseBuilder();
    }

    public static class PublishResponseBuilder {
        private String response;

        public PublishResponseBuilder response(String response) {
            this.response = response;
            return this;
        }

        public PublishResponse build() {
            return new PublishResponse(response);
        }
    }
}