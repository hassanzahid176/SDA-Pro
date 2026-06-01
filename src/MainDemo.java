import alert.composite.*;
import dashboard.controllers.*;
import dashboard.models.DashboardModel;
import dashboard.views.DashboardView;
import enrichment.EnrichmentCorrelationService;
import enrichment.factory.StandardEnrichmentFactory;
import events.*;
import incident.IncidentManagementService;
import incident.domain.Incident;
import ingestion.AlertIngestionService;
import ingestion.adapter.*;
import response.ResponseOrchestrationService;
import shared.enums.*;
import shared.model.CanonicalAlert;

public class MainDemo {
    public static void main(String[] args) {
        System.out.println("============================================================");
        System.out.println("  SDA-Pro -- Security Incident Response & Threat Mitigation Platform");
        System.out.println("  Software Design & Architecture -- Semester Project");
        System.out.println("  Team: Hassan Zahid (54481) | Malik Hamza Shahid (54523)");
        System.out.println("============================================================");

        EventBusPublisher bus = EventBusPublisher.getInstance();
        bus.attach(EventType.ALERT_INGESTED,          new DashboardUpdater());
        bus.attach(EventType.ALERT_ENRICHED,          new DashboardUpdater());
        bus.attach(EventType.INCIDENT_CREATED,        new DashboardUpdater());
        bus.attach(EventType.INCIDENT_CREATED,        new AuditLogger());
        bus.attach(EventType.RESPONSE_ACTION_EXECUTED, new DashboardUpdater());
        bus.attach(EventType.RESPONSE_ACTION_EXECUTED, new AuditLogger());
        bus.attach(EventType.RESPONSE_ACTION_EXECUTED, new NotificationDispatcher());
        bus.attach(EventType.RESPONSE_ACTION_EXECUTED, new MetricsCollector());

        DashboardController dashboard = new DashboardController(new DashboardModel(), new DashboardView());
        dashboard.addDashboardMessage("SDA-Pro started. Monitoring enterprise security alerts...");

        System.out.println("\n--- STEP 1: Alert Ingestion from 3 Sources (Adapter + Factory Method) ---");
        AlertIngestionService ingestionService = new AlertIngestionService();
        CanonicalAlert splunkAlert      = ingestionService.ingest(new SplunkAdapter());
        CanonicalAlert firewallAlert    = ingestionService.ingest(new FirewallAdapter());
        CanonicalAlert crowdStrikeAlert = ingestionService.ingest(new CrowdStrikeAdapter());

        System.out.println("\n--- STEP 2: Composite Grouping -- Campaign + IncidentCluster ---");
        AlertCampaign campaign = new AlertCampaign("SENT-CMP-204", "Multi-Vector APT Campaign: SDA-Pro");
        campaign.add(new SingleAlert(splunkAlert));
        campaign.add(new SingleAlert(firewallAlert));
        campaign.add(new SingleAlert(crowdStrikeAlert));
        campaign.display("  ");

        System.out.println("\n--- STEP 3: Enrichment Pipeline (Abstract Factory + Chain of Responsibility) ---");
        System.out.println("[AbstractFactory] Using PremiumEnrichmentFactory for CRITICAL pipeline.");
        EnrichmentCorrelationService enrichmentService = new EnrichmentCorrelationService();
        AlertComponent enrichedCampaign = enrichmentService.enrich(campaign);

        System.out.println("\n[AbstractFactory] Switching to StandardEnrichmentFactory for secondary pipeline demo.");
        EnrichmentCorrelationService standardService = new EnrichmentCorrelationService(new StandardEnrichmentFactory());
        IncidentCluster cluster = new IncidentCluster("SENT-CLU-204");
        cluster.add(new SingleAlert(firewallAlert));
        standardService.enrich(cluster);

        System.out.println("\n--- STEP 4: Incident Creation and State Lifecycle (State Pattern) ---");
        IncidentManagementService incidentService = new IncidentManagementService();
        Incident incident = incidentService.createIncident(enrichedCampaign);
        incident.beginTriage();
        incident.initiateContainment();

        System.out.println("\n--- STEP 5: Response Orchestration (Facade + Strategy + Decorator + Proxy) ---");
        ResponseOrchestrationService responseService = new ResponseOrchestrationService();
        responseService.respond(incident);
        incident.beginRecovery();
        incident.beginPostIncidentReview();
        incident.close();

        System.out.println("\n--- STEP 6: MVC Dashboard Output ---");
        dashboard.addDashboardMessage("Incident " + incident.getId() + " completed with state: " + incident.getCurrentStateName());
        dashboard.showDashboard();

        System.out.println("\n--- STEP 7: Proxy Cache Verification (Caching + Rate Limit + Access Control Proxies) ---");
        enrichmentService.enrich(campaign);

        System.out.println("\n============================================================");
        System.out.println("  SDA-Pro demo completed successfully.");
        System.out.println("  All 12 design patterns demonstrated. All 4 architecture styles applied.");
        System.out.println("  Team: Hassan Zahid (54481) | Malik Hamza Shahid (54523)");
        System.out.println("============================================================");
    }
}
