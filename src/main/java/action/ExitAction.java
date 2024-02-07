package action;

import enums.SavedString;
import handler.DataHandler;
import handler.PrintHandler;

public class ExitAction implements Action{
    @Override
    public void execute() {
        PrintHandler.printWithDivider(SavedString.GOODBYE.getContent());
        DataHandler.save();
    }
}
