package com.acme.nutrimove.subscriptionservices.backend.subscriptions.interfaces.rest.resources;

public record SubscriptionResource(Long id, String description, Double price, Integer monthDuration, Boolean trial, Long userId) {
}
