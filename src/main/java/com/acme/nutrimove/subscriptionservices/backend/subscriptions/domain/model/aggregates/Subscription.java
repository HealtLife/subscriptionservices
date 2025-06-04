package com.acme.nutrimove.subscriptionservices.backend.subscriptions.domain.model.aggregates;

import com.acme.nutrimove.subscriptionservices.backend.subscriptions.domain.model.commands.CreateSubscriptionCommand;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Configuration
@Getter
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "subscriptions")
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Column(nullable = false)
    private String description;

    @Setter
    @Column(nullable = false)
    private Double price;

    @Setter
    @Column(nullable = false)
    private Integer monthDuration;

    @Setter
    @Column(nullable = false)
    private Boolean trial;

    // En lugar de la entidad User, mantenemos solo el ID del usuario
    @Setter
    @Column(name = "user_id", nullable = false)
    private Long userId;

    // Constructor vacío para JPA
    protected Subscription() {
    }

    // Constructor que recibe el comando de creación
    public Subscription(CreateSubscriptionCommand command) {
        this.description = command.description();
        this.price = command.price();
        this.monthDuration = command.monthDuration();
        this.trial = command.trial();
        this.userId = command.userId();  // Solo guardamos el ID de usuario
    }