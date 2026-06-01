package events;
// PATTERN: Observer
public class MetricsCollector implements Observer {
    public void update(DomainEvent event) { System.out.println("[MetricsCollector] SOC metric counter updated for: " + event.getEventType()); }
    public String getObserverId() { return "MetricsCollector"; }
}
