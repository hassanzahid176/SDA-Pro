package response.strategy;
import incident.domain.Incident;
import java.util.*;
import shared.enums.ResponseActionType;

// PATTERN: Strategy
public class AggressiveContainmentStrategy implements ResponseStrategy {
    public List<ResponseActionType> determineActions(Incident incident) {
        System.out.println("[Strategy] Aggressive Containment selected for " + incident.getSeverity() + " incident.");
        return Arrays.asList(
            ResponseActionType.BLOCK_IP,
            ResponseActionType.ISOLATE_ENDPOINT,
            ResponseActionType.QUARANTINE_FILE
        );
    }
    public String getName() { return "Aggressive Containment"; }
}
