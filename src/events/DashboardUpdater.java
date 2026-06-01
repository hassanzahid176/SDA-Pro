package events;
// PATTERN: Observer
public class DashboardUpdater implements Observer {
    public void update(DomainEvent event) { System.out.println("[DashboardUpdater] Live dashboard refresh: " + event.getMessage()); }
    public String getObserverId() { return "DashboardUpdater"; }
}
