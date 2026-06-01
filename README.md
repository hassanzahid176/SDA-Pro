# SDA-Pro — Security Incident Response & Threat Mitigation Platform

**Software Design & Architecture — Semester Project**

| Field | Detail |
|---|---|
| **Project Name** | SDA-Pro — Security Incident Response & Threat Mitigation Platform |
| **Team Members** | Hassan Zahid (54481) · Malik Hamza Shahid (54523) |
| **Submission Date** | 29th May 2026 |
| **Language** | Java (plain — no framework dependencies) |

---

## Main End-to-End Flow

```
Mock Splunk / Firewall / CrowdStrike Alert
→ Adapter converts external format into RawAlert
→ Factory Method selects AlertNormalizer
→ RawAlert becomes CanonicalAlert
→ Composite groups alerts into AlertCampaign / IncidentCluster
→ Abstract Factory builds enrichment pipeline (Premium or Standard tier)
→ Chain of Responsibility enriches alert (Dedup → GeoIP → ThreatIntel → Asset → Classify)
→ Caching Proxy + Rate Limit Proxy + Access Control Proxy wrap the ThreatIntel adapter
→ Incident is created and published via Singleton EventBusPublisher (Observer)
→ State Pattern controls incident lifecycle (New → Triage → Containment → Eradication → Recovery → Review → Closed)
→ Strategy selects response actions (Aggressive / Balanced / Conservative / WatchAndWait)
→ Factory Method creates actions (BlockIP, IsolateEndpoint, DisableUser, QuarantineFile, Escalate)
→ Decorator adds Audit / ApprovalGate / Rollback / Metrics layers
→ Proxy checks authorization before execution
→ Facade orchestrates the entire response workflow
→ Observer / EventBus updates dashboard, audit, notification, and metrics
→ MVC Dashboard renders real-time analyst view
```

---

## How to Run

### Linux / macOS / Git Bash

```bash
javac -d out $(find src -name "*.java")
java -cp out MainDemo
```

### Windows PowerShell

```powershell
Get-ChildItem -Recurse src -Filter *.java | ForEach-Object { $_.FullName } > sources.txt
javac -d out @sources.txt
java -cp out MainDemo
```


### Docker Compose

```bash
docker compose up --build
```

### Makefile

```bash
make run
make smoke
```

---

## Design Patterns (All 12)

| # | Pattern | Main Classes | Purpose |
|---|---|---|---|
| 1 | **Singleton** | `IngestionConfigManager`, `ThreatIntelCache`, `EventBusPublisher` | Shared config, cache, and event bus |
| 2 | **Factory Method** | `AlertNormalizerFactory`, `ResponseActionFactory` | Create normalizers and response actions |
| 3 | **Abstract Factory** | `EnrichmentProviderFactory`, `PremiumEnrichmentFactory`, `StandardEnrichmentFactory`, `NotificationFactory` | Create families of enrichment providers and notification channels |
| 4 | **Composite** | `AlertComponent`, `SingleAlert`, `AlertCampaign`, `IncidentCluster` | Treat single and grouped alerts uniformly |
| 5 | **Facade** | `IncidentResponseFacade` | Simplify response orchestration |
| 6 | **Adapter** | `SplunkAdapter`, `FirewallAdapter`, `CrowdStrikeAdapter`, `VirusTotalAdapter`, `MISPAdapter` | Convert external formats into internal contracts |
| 7 | **Decorator** | `AuditLogDecorator`, `ApprovalGateDecorator`, `RollbackDecorator`, `MetricsDecorator` | Add behaviour to response actions dynamically |
| 8 | **Proxy** | `CachingThreatIntelProxy`, `RateLimitThreatIntelProxy`, `AccessControlThreatIntelProxy`, `AuthorizedResponseActionProxy` | Cache lookups, rate limit, and authorise |
| 9 | **State** | `NewState`, `UnderTriageState`, `ContainmentState`, `EradicationState`, `RecoveryState`, `PostIncidentReviewState`, `ClosedState` | Control incident lifecycle behaviour |
| 10 | **Chain of Responsibility** | `EnrichmentHandler` + 5 handlers | Process alerts through enrichment stages |
| 11 | **Observer** | `EventBusPublisher`, `DashboardUpdater`, `AuditLogger`, `NotificationDispatcher`, `MetricsCollector` | Notify modules about domain events |
| 12 | **Strategy** | `AggressiveContainmentStrategy`, `BalancedStrategy`, `ConservativeStrategy`, `WatchAndWaitStrategy` | Select response algorithm by severity / context |

---

## Architecture Styles (All 4)

| Style | Evidence |
|---|---|
| **SOA** | Separate service packages: `ingestion`, `enrichment`, `incident`, `response`, `threatintel`, `notification`, `audit` |
| **MVC** | `dashboard/controllers/`, `dashboard/models/`, `dashboard/views/` |
| **Layered** | adapter → normalizer → service → domain separation within each module |
| **Event-Driven** | `EventBusPublisher` with typed `DomainEvent`, pub/sub via `EventType`, 4 observer subscribers |

---

## Documentation

- `docs/adr/` — Architecture Decision Records (ADR-001 through ADR-005)
- `docs/api/` — API contracts and event schemas
- `docs/uml/` — PlantUML source files (class, component, sequence diagrams)
- `demo-output.txt` — Sample console output from a full run
- `screenshots/` — Final screenshot evidence
- `tests/` — Smoke test and documented test cases
- `docs/evidence/` — Screenshot checklist and submission evidence guide

---

## Project Notes

This prototype uses mock adapters and in-memory storage to remain runnable with plain `javac`, Makefile, or Docker Compose. The architecture is fully documented and designed
to be extended with real APIs, PostgreSQL, Redis, RabbitMQ/Kafka, and WebSocket/SSE dashboard updates.
