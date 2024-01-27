package action;

import util.PrintUtil;
import util.TaskList;

import java.io.*;

public class Mark implements Action {
    private TaskList taskList;
    private int i;

    public Mark(TaskList taskList, int i) {
        this.taskList = taskList;
        this.i = i;
    }

    @Override
    public void execute() throws IOException {
        PrintUtil.print("Great job! You marked that task off your list! That's the way to go! " +
                "\nKeep pushing yourself, and remember, every completed task brings you " +
                "\none step closer to your goals. Believe it! ᕕ( ᐛ )ᕗ");
        PrintUtil.print(this.taskList.mark(i));
    }
}
