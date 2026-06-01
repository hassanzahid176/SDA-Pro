package enrichment.factory;
import enrichment.pipeline.EnrichmentHandler;

// PATTERN: Abstract Factory
public interface EnrichmentProviderFactory {
    EnrichmentHandler createGeoIPHandler();
    EnrichmentHandler createThreatIntelHandler();
    EnrichmentHandler createAssetContextHandler();
}
