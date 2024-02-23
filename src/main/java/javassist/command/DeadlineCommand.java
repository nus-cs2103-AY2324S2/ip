package javassist.command;

import java.time.format.DateTimeParseException;

import javassist.exception.JavAssistException;
import javassist.task.Deadline;
import javassist.task.Task;
import javassist.util.JavAssistList;
import javassist.util.Storage;
import javassist.util.TaskList;
import javassist.util.Ui;

/**
 * Represents a command to add new deadline.
 */
public class DeadlineCommand implements Command {
    private String input;

    public DeadlineCommand(String input) {
        this.input = input;
    }

    /**
     * Creates a new Deadline based on description and date time extracted from input and adds to TaskList.
     *
     * @param javAssistList Holds the tasks added.
     * @param ui Displays messages about executed operation.
     * @param storage Handles IO storage operation.
     * @return String of response of chatbot.
     * @throws JavAssistException If date time format is not valid.
     */
    @Override
    public String execute(JavAssistList javAssistList, Ui ui, Storage storage) throws JavAssistException {
        TaskList list = (TaskList) javAssistList;
        String[] task = input.split("/by");
        assert (task.length == 2) : "Deadline command cannot be split at /by";
        try {
            Task t = createTask(task);
            list.add(t);
            storage.writeToFile(list);
            return ui.showAdded(t, list);
        } catch (DateTimeParseException e) {
            throw new JavAssistException(e.getMessage());
        }
    }
    private Task createTask(String[] task) {
        Task t = new Deadline(task[0].substring(9).trim(), task[1].trim());
        return t;
    }

    @Override
    public boolean equals(Object a) {
        DeadlineCommand dc = (DeadlineCommand) a;
        return this.input.equals(dc.input);
    }

}
