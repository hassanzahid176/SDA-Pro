package threatintel.adapter;
import shared.model.ReputationResult;

// PATTERN: Adapter
public class MISPAdapter implements ThreatIntelProvider {
    public ReputationResult checkReputation(String indicator) {
        System.out.println("[Adapter] MISPAdapter reputation lookup for: " + indicator);
        if (indicator.contains("118")) return new ReputationResult(indicator, 84, "MALICIOUS");
        return new ReputationResult(indicator, 30, "UNKNOWN");
    }
}
