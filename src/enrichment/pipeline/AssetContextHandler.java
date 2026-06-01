package enrichment.pipeline;
import alert.composite.AlertComponent;
public class AssetContextHandler extends EnrichmentHandler {
    protected void doHandle(AlertComponent alert) { System.out.println("[Chain] AssetContextHandler: affected asset marked BUSINESS-CRITICAL."); }
}
