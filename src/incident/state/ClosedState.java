package incident.state;
import incident.domain.Incident;

// PATTERN: State
public class ClosedState implements IncidentState {
    public void beginTriage(Incident incident) { System.out.println("[State] Incident already closed."); }
    public void initiateContainment(Incident incident) { System.out.println("[State] Incident already closed."); }
    public void beginEradication(Incident incident) { System.out.println("[State] Incident already closed."); }
    public void beginRecovery(Incident incident) { System.out.println("[State] Incident already closed."); }
    public void beginPostIncidentReview(Incident incident) { System.out.println("[State] Incident already closed."); }
    public void close(Incident incident) { System.out.println("[State] Incident already closed."); }
    public String getName() { return "Closed"; }
}
