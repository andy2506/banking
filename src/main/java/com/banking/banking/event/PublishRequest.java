package com.banking.banking.event;

public class PublishRequest {
    private String message;
    private String topicArn;

    public PublishRequest() {}

    public PublishRequest(String message, String topicArn) {
        this.message = message;
        this.topicArn = topicArn;
    }

    public static PublishRequestBuilder builder() {
        return new PublishRequestBuilder();
    }

    public static class PublishRequestBuilder {
        private String message;
        private String topicArn;

        public PublishRequestBuilder message(String message) {
            this.message = message;
            return this;
        }

        public PublishRequestBuilder topicArn(String topicArn) {
            this.topicArn = topicArn;
            return this;
        }

        public PublishRequest build() {
            return new PublishRequest(message, topicArn);
        }
    }
}
