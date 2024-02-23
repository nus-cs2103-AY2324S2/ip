package javassist.command;

import java.util.ArrayList;

import javassist.exception.JavAssistException;
import javassist.task.Task;
import javassist.util.JavAssistList;
import javassist.util.Storage;
import javassist.util.TaskList;
import javassist.util.Ui;

/**
 * Represents a command to find keyword.
 */
public class FindCommand implements Command {

    private String[] keywords;

    public FindCommand(String... input) {
        this.keywords = input;
    }

    /**
     * Searches through the list of tasks for matching keyword given in input.
     *
     * @param javAssistList Holds all tasks added.
     * @param ui Displays message of operation.
     * @param storage Handles IO operations.
     * @return String of response of chatbot.
     * @throws JavAssistException No thrown.
     */
    @Override
    public String execute(JavAssistList javAssistList, Ui ui, Storage storage) throws JavAssistException {
        TaskList list = (TaskList) javAssistList;
        ArrayList<Task> arr = findTasks(list, keywords);
        return ui.showMatchedTasks(new TaskList(arr));
    }

    private ArrayList<Task> findTasks(TaskList list, String... keywords) {
        ArrayList<Task> arr = new ArrayList<>();
        for (String keyword : keywords) {
            for (Task t : list.getList()) {
                if (t.getTask().contains(keyword) && !arr.contains(t)) {
                    arr.add(t);
                }
            }
        }
        return arr;
    }

    @Override
    public boolean equals(Object a) {
        FindCommand fc = (FindCommand) a;
        if (fc.keywords.length != this.keywords.length) {
            return false;
        }
        for (int i = 1; i < fc.keywords.length; i++) {
            if (!this.keywords[i].equals(fc.keywords[i])) {
                return false;
            }
        }
        return true;
    }
}
