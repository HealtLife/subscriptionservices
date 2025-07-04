package com.acme.nutrimove.subscriptionservices.backend.subscriptions.interfaces.rest;

import com.acme.nutrimove.subscriptionservices.backend.subscriptions.domain.model.aggregates.Subscription;
import com.acme.nutrimove.subscriptionservices.backend.subscriptions.domain.model.aggregates.UserSuscription;
import com.acme.nutrimove.subscriptionservices.backend.subscriptions.domain.model.commands.CreateSubscriptionCommand;
import com.acme.nutrimove.subscriptionservices.backend.subscriptions.domain.model.queries.GetSubscriptionByIdQuery;
import com.acme.nutrimove.subscriptionservices.backend.subscriptions.domain.services.SubscriptionsCommandService;
import com.acme.nutrimove.subscriptionservices.backend.subscriptions.domain.services.SubscriptionsQueryService;
import com.acme.nutrimove.subscriptionservices.backend.subscriptions.infrastructure.persistence.jpa.UserSubscriptionRepository;
import com.acme.nutrimove.subscriptionservices.backend.subscriptions.interfaces.rest.resources.CreateSubscriptionResource;
import com.acme.nutrimove.subscriptionservices.backend.subscriptions.interfaces.rest.resources.SubscriptionResource;
import com.acme.nutrimove.subscriptionservices.backend.subscriptions.interfaces.rest.resources.UserSubscriptionResource;
import com.acme.nutrimove.subscriptionservices.backend.subscriptions.interfaces.rest.transform.CreateSubscriptionCommandFromResourceAssembler;
import com.acme.nutrimove.subscriptionservices.backend.subscriptions.interfaces.rest.transform.SubscriptionResourceFromEntityAssembler;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "api/v1/subscriptions", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Subscriptions", description = "Operation related to Subscriptions")
@CrossOrigin(origins = "http://localhost:4200, http://localhost:4200/access" )
public class SubscriptionController {
    private final SubscriptionsQueryService subscriptionsQueryService;
    private final SubscriptionsCommandService subscriptionsCommandService;
    private final UserSubscriptionRepository userSubscriptionRepository;


    private final WebClient webClient;


    public SubscriptionController(SubscriptionsQueryService subscriptionsQueryService, SubscriptionsCommandService subscriptionsCommandService, UserSubscriptionRepository userSubscriptionRepository) {
        this.subscriptionsQueryService = subscriptionsQueryService;
        this.subscriptionsCommandService = subscriptionsCommandService;
        this.userSubscriptionRepository = userSubscriptionRepository;
        this.webClient = WebClient.builder().build();
    }


    @Operation(summary = "Create an subscription", description = "Create an subscription source with the provided API")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Activity created"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
    })



    @GetMapping("{id}")
    public ResponseEntity<SubscriptionResource> getSubscriptionById(@PathVariable Long id) {
        Optional<Subscription> subscription = subscriptionsQueryService.handle(new GetSubscriptionByIdQuery(id));
        return subscription.map(source -> ResponseEntity.ok(SubscriptionResourceFromEntityAssembler.toResourceFromEntity(source)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("")
    public ResponseEntity<List<SubscriptionResource>> getAllActivities() {
        var subscriptions = subscriptionsQueryService.getAllActivities();
        if (subscriptions.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var activityResources = subscriptions.stream()
                .map(SubscriptionResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(activityResources);
    }

    @PostMapping("/user-subscription")
    public ResponseEntity<String> createUserSubscription(@RequestBody UserSubscriptionResource resource) {
        if (resource.getUserId() == null || resource.getSubscriptionId() == null) {
            return ResponseEntity.badRequest().body("Error: userId and subscriptionId are required.");
        }

        UserSuscription userSubscription = new UserSuscription();
        userSubscription.setUserId(resource.getUserId().toString());
        userSubscription.setSubscriptionId(resource.getSubscriptionId().toString());
        try {
            userSubscriptionRepository.save(userSubscription);
            // POST a otro microservicio
            String otherServiceUrl = "http://localhost:8081/api/v1/users/update-subscription";
            String result = webClient.put()
                    .uri(otherServiceUrl)
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(userSubscription)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }


        return ResponseEntity.status(CREATED).body("Subscription assigned to user successfully.");
    }


}
