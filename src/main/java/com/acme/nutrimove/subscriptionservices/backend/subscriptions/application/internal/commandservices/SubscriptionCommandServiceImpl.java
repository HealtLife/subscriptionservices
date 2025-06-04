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
    private final UserFeignClient userFeignClient;

    public SubscriptionsCommandServiceImpl(SubscriptionRepository subscriptionRepository,
                                           UserFeignClient userFeignClient) {
        this.subscriptionRepository = subscriptionRepository;
        this.userFeignClient = userFeignClient;
    }

    @Override
    public Optional<Subscription> handle(CreateSubscriptionCommand command) {
        // Llamada a user-service para verificar existencia del usuario
        UserDTO userDTO;
        try {
            userDTO = userFeignClient.getUserById(command.userId());
        } catch (Exception e) {
            return Optional.empty();
        }

        if (userDTO == null) return Optional.empty();

        Subscription subscription = new Subscription();
        subscription.setUserId(command.userId());
        subscription.setPlan(command.plan());

        return Optional.of(subscriptionRepository.save(subscription));
    }
}
