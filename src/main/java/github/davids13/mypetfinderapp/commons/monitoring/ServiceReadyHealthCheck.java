package github.davids13.mypetfinderapp.commons.health;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Readiness;

@Readiness
public class ServiceReadyHealthCheck implements HealthCheck {

    @Override
    public HealthCheckResponse call() {
        return HealthCheckResponse
                .named(ServiceReadyHealthCheck.class.getSimpleName())
                .withData("ready", true)
                .up()
                .build();
    }
}
