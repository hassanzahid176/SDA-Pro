package threatintel.proxy;
import shared.model.ReputationResult;
import threatintel.adapter.ThreatIntelProvider;

// PATTERN: Proxy
public class AccessControlThreatIntelProxy implements ThreatIntelProxy {
    private ThreatIntelProvider realProvider;
    private String allowedRole;

    public AccessControlThreatIntelProxy(ThreatIntelProvider realProvider, String allowedRole) {
        this.realProvider = realProvider;
        this.allowedRole = allowedRole;
    }

    public ReputationResult checkReputation(String indicator) {
        System.out.println("[Proxy][AccessControl] Access verified for role: " + allowedRole);
        return realProvider.checkReputation(indicator);
    }

    public ReputationResult checkReputation(String indicator, String requestingRole) {
        if (!allowedRole.equals(requestingRole)) {
            System.out.println("[Proxy][AccessControl] Access DENIED for role: " + requestingRole);
            return new ReputationResult(indicator, 0, "ACCESS_DENIED");
        }
        System.out.println("[Proxy][AccessControl] Access granted for role: " + requestingRole);
        return realProvider.checkReputation(indicator);
    }
}
