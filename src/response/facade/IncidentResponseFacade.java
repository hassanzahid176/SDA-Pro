package response.facade;
import incident.domain.Incident;
import response.action.ResponseAction;
import response.decorator.*;
import response.factory.ResponseActionFactory;
import response.proxy.*;
import response.strategy.*;
import shared.enums.*;
import shared.model.ActionOutcome;
import events.*;
import java.util.*;

// PATTERN: Facade
public class IncidentResponseFacade {
    private ResponseStrategySelector selector = new ResponseStrategySelector();
    private ResponseActionFactory factory = new ResponseActionFactory();
    private ResponseActionProxy proxy = new AuthorizedResponseActionProxy();

    public void assessAndRespond(Incident incident) {
        System.out.println("[Facade] Coordinating SDA-Pro response workflow for " + incident.getId());
        ResponseStrategy strategy = selector.selectStrategy(incident);
        List<ResponseActionType> actionTypes = strategy.determineActions(incident);
        String target = incident.getAffectedAlert().getRepresentativeIp();
        for (ResponseActionType type : actionTypes) {
            ResponseAction action = factory.createAction(type);
            ResponseAction decorated = new MetricsDecorator(new ApprovalGateDecorator(new AuditLogDecorator(new RollbackDecorator(action))));
            ActionOutcome outcome = proxy.execute(decorated, target, "SOC_MANAGER");
            incident.recordActionOutcome(outcome);
            EventBusPublisher.getInstance().publish(new DomainEvent(EventType.RESPONSE_ACTION_EXECUTED, "Response action executed: " + outcome, outcome));
        }
        incident.beginEradication();
    }
}
