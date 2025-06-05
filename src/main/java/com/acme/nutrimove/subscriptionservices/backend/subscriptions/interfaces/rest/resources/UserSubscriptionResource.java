package com.acme.nutrimove.subscriptionservices.backend.subscriptions.interfaces.rest.resources;

public class UserSubscriptionResource {
    private Long userId;
    private Long subscriptionId;

    public Long userId() {
        return userId;
    }

    public Long subscriptionId() {
        return subscriptionId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(Long subscriptionId) {
        this.subscriptionId = subscriptionId;
    }
}