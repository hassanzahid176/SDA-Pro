package events;
// PATTERN: Observer
public class NotificationDispatcher implements Observer {
    public void update(DomainEvent event) { System.out.println("[NotificationDispatcher] Analyst notification dispatched for event: " + event.getEventType()); }
    public String getObserverId() { return "NotificationDispatcher"; }
}
