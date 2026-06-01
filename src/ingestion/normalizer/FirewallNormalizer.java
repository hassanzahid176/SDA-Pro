package ingestion.normalizer;
import shared.enums.*;
import shared.model.*;

public class FirewallNormalizer implements AlertNormalizer {
    public CanonicalAlert normalize(RawAlert rawAlert) {
        System.out.println("[Factory Method] Firewall normalizer mapped network event to CanonicalAlert.");
        return new CanonicalAlert("SENT-ALERT-FW-204", rawAlert.getSourceType(), rawAlert.getSourceIp(), rawAlert.getDestinationIp(), rawAlert.getUsername(), Severity.MEDIUM, rawAlert.getRawMessage(), rawAlert.getTimestamp());
    }
    public boolean supports(AlertSourceType type) { return type == AlertSourceType.FIREWALL; }
}
