package duke.action;

import duke.action.Action;
public class Farewell implements Action {
    @Override
    public String response() {
        return " Bye. Hope to see you again soon!";
    }
    @Override
    public boolean isExit() {
        return true;
    }
}
