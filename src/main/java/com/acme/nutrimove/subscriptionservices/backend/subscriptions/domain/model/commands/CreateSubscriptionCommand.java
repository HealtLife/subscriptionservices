package com.acme.nutrimove.subscriptionservices.backend.subscriptions.domain.model.commands;

public record CreateSubscriptionCommand(
        String description,
        Double price,
        Integer monthDuration,
        Boolean trial
) {
    public CreateSubscriptionCommand {
        if (description == null || description.isBlank())
            throw new IllegalArgumentException("description cannot be blank");
        if (price == null || price < 0)
            throw new IllegalArgumentException("price cannot be negative");
        if (monthDuration == null || monthDuration < 0)
            throw new IllegalArgumentException("monthDuration cannot be negative");
        if (trial == null)
            throw new IllegalArgumentException("trial cannot be null");
    }
}
