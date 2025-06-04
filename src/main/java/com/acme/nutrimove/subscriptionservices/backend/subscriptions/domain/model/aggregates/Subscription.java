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

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private Integer monthDuration;

    @Column(nullable = false)
    private Boolean trial;

    // En lugar de la entidad User, mantenemos solo el ID del usuario

    @Column(name = "user_id", nullable = false)
    private Long userId;

    // Constructor vacío para JPA
    public Subscription() {
    }

    // Constructor que recibe el comando de creación
    public Subscription(CreateSubscriptionCommand command) {
        this.description = command.description();
        this.price = command.price();
        this.monthDuration = command.monthDuration();
        this.trial = command.trial();
        this.userId = command.userId();  // Solo guardamos el ID de usuario
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getMonthDuration() {
        return monthDuration;
    }

    public void setMonthDuration(Integer monthDuration) {
        this.monthDuration = monthDuration;
    }

    public Boolean getTrial() {
        return trial;
    }

    public void setTrial(Boolean trial) {
        this.trial = trial;
    }

    public Long getUserId() {
        return userId;
    }
}