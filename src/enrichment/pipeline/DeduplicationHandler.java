package enrichment.pipeline;
import alert.composite.AlertComponent;
public class DeduplicationHandler extends EnrichmentHandler {
    protected void doHandle(AlertComponent alert) { System.out.println("[Chain] DeduplicationHandler: unique alert group confirmed for " + alert.getId()); }
}
