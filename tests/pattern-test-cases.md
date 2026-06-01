# Pattern Test Cases

| Pattern | What to Check | Expected Result |
|---|---|---|
| Singleton | Call `getInstance()` on `EventBusPublisher`, `ThreatIntelCache`, and `IngestionConfigManager` more than once. | Same shared object is used. |
| Factory Method | Request normalizers/actions by type. | Correct concrete class is returned without changing client code. |
| Abstract Factory | Switch between `PremiumEnrichmentFactory` and `StandardEnrichmentFactory`. | Different families of enrichment handlers are created. |
| Composite | Add multiple `SingleAlert` objects to `AlertCampaign` and `IncidentCluster`. | Group is processed like a single `AlertComponent`. |
| Facade | Run `IncidentResponseFacade.assessAndRespond(...)`. | Complex response workflow is executed through one simple entry point. |
| Adapter | Fetch alerts from Splunk, Firewall, and CrowdStrike mock adapters. | External-style input becomes internal `RawAlert`. |
| Decorator | Execute response action with approval, audit, rollback, and metrics decorators. | Extra behavior is added without changing the action class. |
| Proxy | Use threat intel and response authorization proxies. | Access/caching/rate-limit behavior is applied before real work. |
| State | Move incident from New to Closed through lifecycle methods. | Only valid transitions are allowed. |
| Chain of Responsibility | Run enrichment pipeline. | Deduplication, GeoIP, threat intel, asset context, and classification run in order. |
| Observer | Publish domain events. | Dashboard, audit, notification, and metrics subscribers react independently. |
| Strategy | Select response strategy by severity/context. | Correct response plan is chosen at runtime. |
