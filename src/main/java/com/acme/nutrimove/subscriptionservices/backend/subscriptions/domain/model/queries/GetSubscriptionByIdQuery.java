package com.acme.nutrimove.subscriptionservices.backend.subscriptions.domain.model.queries;

public record GetSubscriptionByIdQuery(Long id) {
    public GetSubscriptionByIdQuery {
        if (id == null) throw new NullPointerException("id is null");
    }
}
