package ingestion.adapter;
import shared.enums.AlertSourceType;
import shared.model.RawAlert;

// PATTERN: Adapter
public class CrowdStrikeAdapter implements ExternalAlertAdapter {
    public RawAlert fetchAlert() {
        System.out.println("[Adapter] Pulling EDR alert from SDA-Pro CrowdStrike mock connector...");
        return new RawAlert(
            "RAW-CS-204",
            AlertSourceType.CROWDSTRIKE,
            "Ransomware process detected on endpoint: workstation-42",
            "10.10.5.42",
            "10.10.5.1",
            "workstation-42",
            "2026-05-30T09:20:00Z"
        );
    }
    public String getSourceName() { return "CrowdStrike EDR"; }
}
