package github.davids13.mypetfinderapp.commons.health;

import org.eclipse.microprofile.health.HealthCheck;
import org.eclipse.microprofile.health.HealthCheckResponse;
import org.eclipse.microprofile.health.Liveness;

import static java.lang.Runtime.getRuntime;

@Liveness
public class ServiceLiveHealthCheck implements HealthCheck {

    private static final int MB = 1024 * 1024;

    @Override
    public HealthCheckResponse call() {
        return HealthCheckResponse
                .named("memory (MBytes)")
                .withData("heap.total", getRuntime().totalMemory() / MB)
                .withData("heap.free", getRuntime().freeMemory() / MB)
                .withData("heap.used", (getRuntime().totalMemory() - getRuntime().freeMemory()) / MB)
                .withData("heap.max", getRuntime().maxMemory() / MB)
                .up()
                .build();
    }
}
