package nihao.action;

import nihao.enums.SavedString;
import nihao.handler.DataHandler;
import nihao.handler.PrintHandler;

public class ExitAction implements Action{
    @Override
    public void execute() {
        PrintHandler.printWithDivider(SavedString.GOODBYE.getContent());
        DataHandler.save();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof ExitAction;
    }
}
