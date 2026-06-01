package enrichment.factory;
import enrichment.pipeline.*;
import threatintel.adapter.VirusTotalAdapter;
import threatintel.proxy.CachingThreatIntelProxy;

// PATTERN: Abstract Factory (Concrete Factory — Premium Tier)
public class PremiumEnrichmentFactory implements EnrichmentProviderFactory {
    public EnrichmentHandler createGeoIPHandler() {
        System.out.println("[AbstractFactory] PremiumEnrichmentFactory: creating Premium GeoIPHandler");
        return new GeoIPHandler();
    }
    public EnrichmentHandler createThreatIntelHandler() {
        System.out.println("[AbstractFactory] PremiumEnrichmentFactory: creating ThreatIntelHandler with VirusTotal + CachingProxy");
        return new ThreatIntelHandler(new CachingThreatIntelProxy(new VirusTotalAdapter()));
    }
    public EnrichmentHandler createAssetContextHandler() {
        System.out.println("[AbstractFactory] PremiumEnrichmentFactory: creating Premium AssetContextHandler");
        return new AssetContextHandler();
    }
}
