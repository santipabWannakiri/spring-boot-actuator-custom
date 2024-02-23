package com.actuator.metrics.indicator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component("order-service")
public class PingServiceHealthIndicator implements HealthIndicator {
    private final RestTemplate restTemplate;
    private String orderServiceEndpoint;

    @Autowired
    public PingServiceHealthIndicator(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Value("${custom.actuator.ping.order.service}")
    public void setOrderServiceEndpoint(String orderServiceEndpoint) {
        this.orderServiceEndpoint = orderServiceEndpoint;
    }

    @Override
    public Health health() {
        try {
            String response = restTemplate.getForObject(orderServiceEndpoint, String.class);
            if (response != null && !response.isEmpty()) {
                return Health.up().withDetail("Service", "Order-Service").withDetail("message", response).build();
            } else {
                return Health.down().withDetail("Service", "Order-Service").withDetail("error", "Unable to reach out order-service").build();
            }
        } catch (Exception exception) {
            return Health.down().withDetail("Service", "Order-Service").withDetail("error", exception.getMessage()).build();
        }


    }
}
