package com.acme.nutrimove.subscriptionservices.backend.subscriptions.domain.model.queries;

public record GetAllSubscriptionByDescriptionQuery(String description) {
    public GetAllSubscriptionByDescriptionQuery {
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("Description cannot be null or empty");
        }
    }
}
