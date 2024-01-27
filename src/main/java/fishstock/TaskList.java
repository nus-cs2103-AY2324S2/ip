package fishstock;

import java.util.ArrayList;
import fishstock.FishStock.Keyword;

class TaskList {
    protected final ArrayList<Task> list;
    protected TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    protected TaskList() {
        this.list = new ArrayList<>();
    }

    protected void printTasks() {
        for (int i = 0; i < list.size(); i++) {
            Ui.printMsg((i + 1) + "." + list.get(i));
        }
    }

    protected void changeMark(Keyword keyword, String input) {
        Integer idx = Parser.getTaskFromIndex(input);
        if (idx == null) { // could not parse
            return;
        }
        try {
            Task task = list.get(idx);
            if (keyword == Keyword.MARK) {
                task.markAsDone();
                Ui.printMsg("Did you actually finish this? \uD83E\uDD14:\n" +
                        "  " + task);
            } else if (keyword == Keyword.UNMARK){
                task.markAsUndone();
                Ui.printMsg("I knew you didn't finish it! \uD83D\uDE0F:\n" +
                        "  " + task);
            }
        } catch (IndexOutOfBoundsException e) {
            Ui.printError("OH NOSE! Task number must be in valid range..");
        }
    }

    protected void deleteTask(String input) {
        Integer idx = Parser.getTaskFromIndex(input);
        if (idx == null) { // could not parse
            return;
        }
        try {
            Task task = list.get(idx);
            list.remove(task);
            Ui.printMsg("This task has been removed:\n  " + task +
                    "\n" + "Now you have " + list.size() +
                    " task(s) in total.");

        } catch (IndexOutOfBoundsException e) {
            Ui.printError("OH NOSE! Task number must be in valid range..");
        }
    }

    protected void addTask(Task task) {
        if (task != null) {
            list.add(task);
            Ui.printMsg("This task has been added:\n  " + task +
                    "\n" + "Now you have " + list.size() +
                    " task(s) in total.");
        }
    }
}
