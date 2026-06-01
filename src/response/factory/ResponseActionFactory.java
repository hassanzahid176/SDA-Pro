package response.factory;
import response.action.*;
import shared.enums.ResponseActionType;

// PATTERN: Factory Method
public class ResponseActionFactory {
    public ResponseAction createAction(ResponseActionType type) {
        switch (type) {
            case BLOCK_IP:           return new BlockIPAction();
            case ISOLATE_ENDPOINT:   return new IsolateEndpointAction();
            case DISABLE_USER:       return new DisableUserAction();
            case QUARANTINE_FILE:    return new QuarantineFileAction();
            case ESCALATE:           return new EscalateAction();
            default: throw new IllegalArgumentException("Unsupported action: " + type);
        }
    }
}
