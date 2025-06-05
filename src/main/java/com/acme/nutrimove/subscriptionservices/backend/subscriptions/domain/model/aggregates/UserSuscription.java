package com.acme.nutrimove.subscriptionservices.backend.subscriptions.domain.model.aggregates;


import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Configuration
@Getter
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "user_subscription")
public class UserSuscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id",nullable = false)
    private String userId;

    @Column(name = "subscription_id",nullable = false)
    private String subscriptionId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
    }
}
