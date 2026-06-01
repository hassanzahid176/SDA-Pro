package ingestion.normalizer;
import shared.enums.AlertSourceType;

// PATTERN: Factory Method
public class AlertNormalizerFactory {
    public AlertNormalizer createNormalizer(AlertSourceType type) {
        if (type == AlertSourceType.SPLUNK) return new SplunkNormalizer();
        if (type == AlertSourceType.FIREWALL) return new FirewallNormalizer();
        if (type == AlertSourceType.CROWDSTRIKE) return new CrowdStrikeNormalizer();
        throw new IllegalArgumentException("Unsupported alert source: " + type);
    }
}
