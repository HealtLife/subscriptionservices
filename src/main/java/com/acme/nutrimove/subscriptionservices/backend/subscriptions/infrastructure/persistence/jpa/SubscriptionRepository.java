package com.acme.nutrimove.subscriptionservices.backend.subscriptions.infrastructure.persistence.jpa;

import com.acme.nutrimove.subscriptionservices.backend.subscriptions.domain.model.aggregates.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

    List<Subscription> findAllByDescription(String name);

    boolean existsByPriceAndDescription(Double price, String description);

}
