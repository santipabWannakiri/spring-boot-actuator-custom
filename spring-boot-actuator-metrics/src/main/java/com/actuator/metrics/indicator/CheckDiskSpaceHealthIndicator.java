package com.actuator.metrics.indicator;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.boot.actuate.system.DiskSpaceHealthIndicator;
import org.springframework.stereotype.Component;
import org.springframework.util.unit.DataSize;

import java.io.File;

@Component("disk")
public class CheckDiskSpaceHealthIndicator implements HealthIndicator {
    private DiskSpaceHealthIndicator disk;
    private String path;
    private int threshold;

    @Value("${custom.actuator.disk.path}")
    public void setPath(String path) {
        this.path = path;
    }

    @Value("${custom.actuator.disk.threshold.gb}")
    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }

    @Override
    public Health health() {
        try {
            this.disk = new DiskSpaceHealthIndicator(new File(path), DataSize.ofGigabytes(threshold));
            Health originalHealth = disk.health();
            if (originalHealth.getStatus().equals(Status.UP)) {
                return Health.up().withDetail("details", originalHealth.getDetails()).build();
            } else {
                return Health.down().withDetail("error", originalHealth.getDetails()).build();
            }
        } catch (Exception e) {
            return Health.down().withDetail("error", "Failed to check disk space").build();
        }
    }


}
