package ingestion.normalizer;
import shared.enums.*;
import shared.model.*;

public class SplunkNormalizer implements AlertNormalizer {
    public CanonicalAlert normalize(RawAlert rawAlert) {
        System.out.println("[Factory Method] SIEM normalizer mapped raw login data to CanonicalAlert.");
        return new CanonicalAlert("SENT-ALERT-SIEM-204", rawAlert.getSourceType(), rawAlert.getSourceIp(), rawAlert.getDestinationIp(), rawAlert.getUsername(), Severity.HIGH, rawAlert.getRawMessage(), rawAlert.getTimestamp());
    }
    public boolean supports(AlertSourceType type) { return type == AlertSourceType.SPLUNK; }
}
