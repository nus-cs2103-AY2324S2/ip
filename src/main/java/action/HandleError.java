package action;
import exception.NarutoException;
import util.PrintUtil;

public class HandleError implements Action {
    private NarutoException err;
    public HandleError(NarutoException err) {
        this.err = err;
    }
    @Override
    public void execute() {
        switch (this.err.getErrorType()) {
            case EMPTY_TODO:
                PrintUtil.print(this.err.getMessage());
                break;
            case EMPTY_DEADLINE:
                PrintUtil.print(this.err.getMessage());
                break;
            case EMPTY_EVENT:
                PrintUtil.print(this.err.getMessage());
                break;
            case INVALID_DEADLINE:
                PrintUtil.print(this.err.getMessage());
                break;
            case INVALID_EVENT:
                PrintUtil.print(this.err.getMessage());
                break;
            case INVALID_COMMAND:
                PrintUtil.print(this.err.getMessage());
                break;
            case INVALID_INDEX:
                PrintUtil.print(this.err.getMessage());
                break;
            default:
                PrintUtil.print(this.err.getMessage());
                break;
        }
    }
}
