package com.acme.nutrimove.subscriptionservices.backend.subscriptions.application.internal.commandservices;

import com.acme.nutrimove.subscriptionservices.backend.subscriptions.domain.model.aggregates.Subscription;
import com.acme.nutrimove.subscriptionservices.backend.subscriptions.domain.model.commands.CreateSubscriptionCommand;
import com.acme.nutrimove.subscriptionservices.backend.subscriptions.domain.services.SubscriptionsCommandService;
import com.acme.nutrimove.subscriptionservices.backend.subscriptions.infrastructure.persistence.jpa.SubscriptionRepository;
import com.acme.nutrimove.subscriptionservices.backend.external.clients.UserFeignClient;
import com.acme.nutrimove.subscriptionservices.backend.external.dto.UserDTO;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SubscriptionsCommandServiceImpl implements SubscriptionsCommandService {

    private final SubscriptionRepository subscriptionRepository;


    public SubscriptionsCommandServiceImpl(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;

    }

    @Override
    public Optional<Subscription> handle(CreateSubscriptionCommand command) {
        Subscription subscription = new Subscription(command);
        return Optional.of(subscriptionRepository.save(subscription));
    }

}
