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
        PrintUtil.print(this.err.getMessage());
    }
}
