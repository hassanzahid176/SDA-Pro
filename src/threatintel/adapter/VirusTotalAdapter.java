package threatintel.adapter;
import shared.model.ReputationResult;

// PATTERN: Adapter
public class VirusTotalAdapter implements ThreatIntelProvider {
    public ReputationResult checkReputation(String indicator) {
        System.out.println("[Adapter] VirusTotalAdapter reputation lookup for: " + indicator);
        if (indicator.startsWith("203") || indicator.startsWith("91")) return new ReputationResult(indicator, 92, "MALICIOUS");
        return new ReputationResult(indicator, 10, "CLEAN");
    }
}
