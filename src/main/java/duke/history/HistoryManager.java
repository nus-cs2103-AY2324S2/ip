package duke.history;

import duke.exceptions.ProcessingException;
import duke.exceptions.StartUpException;
import duke.storage.Storage;



/**
 * placeholder
 */
public class HistoryManager {
    private History history;

    /**
     * @param startState placeholder
     */
    public HistoryManager(State startState) throws StartUpException {
        history = new History(startState);
    }

    /**
     * @return placeholder
     * @throws ProcessingException placeholder
     */
    public String undo(Storage storage) throws ProcessingException {
        State prevState = history.getCurrState();
        history.rollBackState();
        State currState = history.getCurrState();
        storage.restoreState(currState);
        return String.format("Your %s command was undone!\nThis is your current list\n%s",
                prevState.getCommand(),
                storage.displayList());
    }

    /**
     * @return placeholder
     * @throws ProcessingException placeholder
     */
    public String redo(Storage storage) throws ProcessingException {
        history.rollForwardState();
        State currState = history.getCurrState();
        storage.restoreState(currState);
        return String.format("Your %s command was redone!\nThis is your current list\n%s",
                currState.getCommand(),
                storage.displayList());
    }
    /**
     * @param state placeholder
     * @throws ProcessingException placeholder
     */
    public void updateHistory(State state) throws ProcessingException {
        if (state.isIgnoredHistory()) {
            return;
        }
        history.addState(state);
    }
}
