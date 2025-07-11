package com.acme.nutrimove.subscriptionservices.backend.subscriptions.interfaces.rest.resources;

public record CreateSubscriptionResource(
        String description,
        Double price,
        Integer monthDuration,
        Boolean trial
) {
    public CreateSubscriptionResource {
        if (description == null || description.isBlank())
            throw new IllegalArgumentException("description cannot be null or empty");
        if (price == null || price < 0)
            throw new IllegalArgumentException("price cannot be negative");
        if (monthDuration == null || monthDuration < 0)
            throw new IllegalArgumentException("monthDuration cannot be negative");
        if (trial == null)
            throw new IllegalArgumentException("trial cannot be null");
    }
}
