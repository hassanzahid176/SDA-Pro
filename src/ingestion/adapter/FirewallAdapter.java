package ingestion.adapter;
import shared.enums.AlertSourceType;
import shared.model.RawAlert;

// PATTERN: Adapter
public class FirewallAdapter implements ExternalAlertAdapter {
    public RawAlert fetchAlert() {
        System.out.println("[Adapter] Pulling perimeter alert from SDA-Pro firewall mock connector...");
        return new RawAlert("RAW-FW-204", AlertSourceType.FIREWALL, "Repeated blocked connection attempt against finance database", "91.240.118.23", "172.16.4.20", "unknown", "2026-05-30T09:17:00Z");
    }
    public String getSourceName() { return "Palo Alto Firewall"; }
}
