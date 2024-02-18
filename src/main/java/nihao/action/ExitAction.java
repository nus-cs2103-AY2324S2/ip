package nihao.action;

import nihao.enums.SavedString;
import nihao.handler.DataHandler;
import nihao.handler.PrintHandler;

/**
 * Represents the exit action which is invoked by "bye" command.
 */
public class ExitAction implements Action {
    /**
     * Prints the goodbye message.
     */
    @Override
    public void execute() {
        PrintHandler.printWithDivider(SavedString.GOODBYE.getContent());
        DataHandler.save();
    }

    /**
     * Two ExitAction objects are always equivalent.
     */
    @Override
    public boolean equals(Object obj) {
        return obj instanceof ExitAction;
    }
}
