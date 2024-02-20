package javassist.command;

import javassist.exception.JavAssistException;
import javassist.task.Task;
import javassist.util.JavAssistList;
import javassist.util.Storage;
import javassist.util.TaskList;
import javassist.util.Ui;

/**
 * Represents a command to delete specified task.
 */
public class DeleteCommand implements Command {
    private String input;

    public DeleteCommand(String input) {
        this.input = input;
    }

    /**
     * Removes task in TaskList where index is indicated in input.
     *
     * @param javAssistList Holds the existing tasks including task to be removed.
     * @param ui Displays messages about executed operation.
     * @param storage Handles IO storage operation.
     * @return String of response of chatbot.
     * @throws JavAssistException If index is not n range.
     */
    @Override
    public String execute(JavAssistList javAssistList, Ui ui, Storage storage) throws JavAssistException {
        TaskList list = (TaskList) javAssistList;
        String[] s = input.split("\\s");
        assert (s.length == 2) : "Delete command cannot be split at space";
        int num = Integer.parseInt(s[1]);
        if (num > list.getSize() || num < 1) {
            throw new JavAssistException("Task (" + num + ") not found.\n" + list.print());
        }
        Task t = list.delete(num - 1);
        storage.writeToFile(list);
        return ui.showDeleted(t, list);
    }

    @Override
    public boolean equals(Object a) {
        DeleteCommand dc = (DeleteCommand) a;
        return this.input.equals(dc.input);
    }
}
