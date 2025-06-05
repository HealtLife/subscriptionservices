package com.acme.nutrimove.subscriptionservices.backend.subscriptions.domain.model.aggregates;

import com.acme.nutrimove.subscriptionservices.backend.subscriptions.domain.model.commands.CreateSubscriptionCommand;
import jakarta.persistence.*;
import lombok.Getter;

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



    // Constructor vacío para JPA
    public Subscription() {
    }

    // Constructor que recibe el comando de creación
    public Subscription(CreateSubscriptionCommand command) {
        this.description = command.description();
        this.price = command.price();
        this.monthDuration = command.monthDuration();

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




}