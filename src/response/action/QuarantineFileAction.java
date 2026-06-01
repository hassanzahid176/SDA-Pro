package response.action;
import shared.enums.ResponseActionType;
import shared.model.ActionOutcome;

// PATTERN: Factory Method (product)
public class QuarantineFileAction implements ResponseAction {
    public ActionOutcome execute(String target) {
        System.out.println("[Action] Quarantining malicious file on: " + target);
        return new ActionOutcome("QuarantineFileAction", true, "File quarantined on " + target);
    }
    public ActionOutcome rollback(String target) {
        return new ActionOutcome("QuarantineFileAction-Rollback", true, "File restored on " + target);
    }
    public ResponseActionType getType() { return ResponseActionType.QUARANTINE_FILE; }
}
