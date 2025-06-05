package com.acme.nutrimove.subscriptionservices.backend.subscriptions.infrastructure.persistence.jpa;

import com.acme.nutrimove.subscriptionservices.backend.subscriptions.domain.model.aggregates.UserSuscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSubscriptionRepository extends JpaRepository<UserSuscription, Long> {
}