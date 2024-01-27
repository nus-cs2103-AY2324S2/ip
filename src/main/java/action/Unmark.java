package action;

import util.PrintUtil;
import util.TaskList;

import java.io.*;

public class Unmark implements Action {
    private TaskList taskList;
    private int i;

    public Unmark(TaskList taskList, int i) {
        this.taskList = taskList;
        this.i = i;
    }

    @Override
    public void execute() throws IOException {
        PrintUtil.print("You know, sometimes things don't go as planned, but that's okay! " +
                "\nThe important part is to keep moving forward. " +
                "\nUnmarking a task is just a step in the journey. Believe it! " +
                "\nWe'll get there, one task at a time! ᕙ(⇀‸↼‶)ᕗ");
        PrintUtil.print(this.taskList.unmark(i));
    }
}
