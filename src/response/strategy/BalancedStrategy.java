package response.strategy;
import incident.domain.Incident;
import java.util.*;
import shared.enums.ResponseActionType;

// PATTERN: Strategy
public class BalancedStrategy implements ResponseStrategy {
    public List<ResponseActionType> determineActions(Incident incident) {
        System.out.println("[Strategy] Balanced Response selected for " + incident.getSeverity() + " incident.");
        return Arrays.asList(ResponseActionType.BLOCK_IP, ResponseActionType.DISABLE_USER);
    }
    public String getName() { return "Balanced Response"; }
}
