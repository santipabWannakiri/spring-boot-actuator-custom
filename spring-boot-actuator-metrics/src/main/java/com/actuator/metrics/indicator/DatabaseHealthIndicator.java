package com.actuator.metrics.indicator;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component("h2")
public class DatabaseHealthIndicator implements HealthIndicator {

    @Override
    public Health health() {
        int errorCode = check();
        if (errorCode != 0) {
            return Health.down().withDetail("Error code", errorCode).build();
        }
        return Health.up()
                .withDetail("database", "h2")
                .withDetail("indicatorType", "custom")
                .build();
    }

    public int check() {
        try (Connection connection = DriverManager.getConnection("jdbc:h2:mem:demo", "sa", "zxcv")) {
            connection.createStatement().execute("SELECT 1");
        } catch (SQLException exception) {
            System.out.println(exception.toString());
            return 1;
        }
        return 0;
    }
}
