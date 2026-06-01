package events;
// PATTERN: Observer
public class AuditLogger implements Observer {
    public void update(DomainEvent event) { System.out.println("[AuditLogger] Immutable audit entry saved: " + event); }
    public String getObserverId() { return "AuditLogger"; }
}
