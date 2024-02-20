package javassist.command;

import javassist.exception.JavAssistException;
import javassist.task.Task;
import javassist.task.Todo;
import javassist.util.JavAssistList;
import javassist.util.Storage;
import javassist.util.TaskList;
import javassist.util.Ui;

/**
 * Represents a command to add new Todo.
 */
public class TodoCommand implements Command {

    private String input;

    public TodoCommand(String input) {
        this.input = input;
    }

    /**
     * Creates a new Todo based on description extracted from input and adds to TaskList.
     *
     * @param javAssistList Holds the tasks added.
     * @param ui Display messages about executed operation.
     * @param storage Handles IO storage operation.
     * @return String of response of chatbot.
     * @throws JavAssistException Not thrown.
     */
    @Override
    public String execute(JavAssistList javAssistList, Ui ui, Storage storage) throws JavAssistException {
        TaskList list = (TaskList) javAssistList;
        Task t = new Todo(input.substring(5));
        list.add(t);
        storage.writeToFile(list);
        return ui.showAdded(t, list);
    }

    @Override
    public boolean equals(Object a) {
        TodoCommand tc = (TodoCommand) a;
        return this.input.equals(tc.input);
    }
}
