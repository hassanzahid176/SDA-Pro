package enrichment;
import alert.composite.AlertComponent;
import enrichment.factory.*;
import enrichment.pipeline.*;
import events.*;
import shared.enums.EventType;

public class EnrichmentCorrelationService {

    private EnrichmentProviderFactory providerFactory;

    public EnrichmentCorrelationService() {
        // PATTERN: Abstract Factory
        this.providerFactory = new PremiumEnrichmentFactory();
    }

    public EnrichmentCorrelationService(EnrichmentProviderFactory factory) {
        this.providerFactory = factory;
    }

    public AlertComponent enrich(AlertComponent alert) {
        // PATTERN: Chain of Responsibility
        EnrichmentHandler h1 = new DeduplicationHandler();
        EnrichmentHandler h2 = providerFactory.createGeoIPHandler();
        EnrichmentHandler h3 = providerFactory.createThreatIntelHandler();
        EnrichmentHandler h4 = providerFactory.createAssetContextHandler();
        EnrichmentHandler h5 = new ClassificationHandler();
        h1.setNext(h2).setNext(h3).setNext(h4).setNext(h5);
        System.out.println("[Chain] Starting enrichment pipeline...");
        h1.handle(alert);
        EventBusPublisher.getInstance().publish(
            new DomainEvent(EventType.ALERT_ENRICHED, "Alert enrichment completed", alert));
        return alert;
    }
}
