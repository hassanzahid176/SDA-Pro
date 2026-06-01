package ingestion.adapter;
import shared.enums.AlertSourceType;
import shared.model.RawAlert;

// PATTERN: Adapter
public class SplunkAdapter implements ExternalAlertAdapter {
    public RawAlert fetchAlert() {
        System.out.println("[Adapter] Pulling SIEM alert from SDA-Pro Splunk mock connector...");
        return new RawAlert("RAW-SPL-204", AlertSourceType.SPLUNK, "Brute-force login attempts detected on privileged account", "203.0.113.45", "172.16.4.15", "finance.admin", "2026-05-30T09:15:00Z");
    }
    public String getSourceName() { return "Splunk SIEM"; }
}
