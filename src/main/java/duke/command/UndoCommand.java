package duke.command;

import duke.DukeException;
import duke.TaskList;
import duke.Ui;
import duke.task.HistoryTask;
import duke.task.Task;

public class UndoCommand extends Command {
    private String input;
    private Task task;

    public UndoCommand(HistoryTask historyTask) {
        input = historyTask.getCommand();
        task = historyTask.getTask();
    }

    @Override
    public String execute(TaskList taskList, Ui ui) throws DukeException {
        String[] inputContent = input.split(" ", 2);
        String mainCommand = inputContent[0];
        int taskNum; //for mark, unmark and delete
        switch (mainCommand) {
        case "mark":
            taskNum = Integer.parseInt(inputContent[1]) - 1;
            taskList.get(taskNum).markAsNotDone();
            return ui.showUnDo(input);
        case "unmark":
            taskNum = Integer.parseInt(inputContent[1]) - 1;
            taskList.get(taskNum).markAsDone();
            return ui.showUnDo(input);
        case "todo":
        case "deadline":
        case "event":
            taskList.remove(taskList.size() - 1);
            return ui.showUnDo(input);
        case "delete":
            taskNum = Integer.parseInt(inputContent[1]) - 1;
            taskList.add(taskNum, task);
            return ui.showUnDo(input);
        }
        return ui.showError("Hey! Your task list is empty! qwq");
    }
}
