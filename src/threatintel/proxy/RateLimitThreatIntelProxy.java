package threatintel.proxy;
import shared.model.ReputationResult;
import threatintel.adapter.ThreatIntelProvider;

// PATTERN: Proxy
public class RateLimitThreatIntelProxy implements ThreatIntelProxy {
    private ThreatIntelProvider realProvider;
    private int callCount = 0;
    private static final int MAX_CALLS_PER_MINUTE = 60;

    public RateLimitThreatIntelProxy(ThreatIntelProvider realProvider) {
        this.realProvider = realProvider;
    }

    public ReputationResult checkReputation(String indicator) {
        if (callCount >= MAX_CALLS_PER_MINUTE) {
            System.out.println("[Proxy][RateLimit] Rate limit reached. Returning degraded result for: " + indicator);
            return new ReputationResult(indicator, 0, "UNKNOWN");
        }
        callCount++;
        System.out.println("[Proxy][RateLimit] API call #" + callCount + " for: " + indicator);
        return realProvider.checkReputation(indicator);
    }
}
