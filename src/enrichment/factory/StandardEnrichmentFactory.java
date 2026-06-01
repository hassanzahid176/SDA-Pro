package enrichment.factory;
import enrichment.pipeline.*;
import threatintel.adapter.MISPAdapter;
import threatintel.proxy.CachingThreatIntelProxy;

// PATTERN: Abstract Factory (Concrete Factory — Standard Tier)
public class StandardEnrichmentFactory implements EnrichmentProviderFactory {
    public EnrichmentHandler createGeoIPHandler() {
        System.out.println("[AbstractFactory] StandardEnrichmentFactory: creating Standard GeoIPHandler");
        return new GeoIPHandler();
    }
    public EnrichmentHandler createThreatIntelHandler() {
        System.out.println("[AbstractFactory] StandardEnrichmentFactory: creating ThreatIntelHandler with MISP + CachingProxy");
        return new ThreatIntelHandler(new CachingThreatIntelProxy(new MISPAdapter()));
    }
    public EnrichmentHandler createAssetContextHandler() {
        System.out.println("[AbstractFactory] StandardEnrichmentFactory: creating Standard AssetContextHandler");
        return new AssetContextHandler();
    }
}
