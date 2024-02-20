package duke.main;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.parser.Parser;
import duke.ui.Ui;
import duke.tasklist.TaskList;
import duke.task.Task;

import java.io.IOException;

public class Command {
    public String getListResponse(Ui ui, TaskList list) {
        return ui.listing(list);
    }
    
    public String getMarkResponse(Ui ui, Parser parser, Storage storage, TaskList list, String input)
            throws DukeException, IOException {
        ui.handleMarkError(input, list);
        Task taskToMark = parser.getTaskTobeMarked(input, list);
        taskToMark.markDone();
        storage.changeFileContent(list);
        return ui.marking(taskToMark);
    }
    
    public String getUnmarkResponse(Ui ui, Parser parser, Storage storage, TaskList list, String input)
            throws DukeException, IOException {
        ui.handleUnmarkError(input, list);
        Task taskToUnmark = parser.getTaskToBeUnmarked(input, list);
        taskToUnmark.markUndone();
        storage.changeFileContent(list);
        return ui.unmarking(taskToUnmark);
    }
    
    public String getTodoResponse(Ui ui, Parser parser, Storage storage, TaskList list, String input)
            throws DukeException, IOException {
        ui.handleTodoError(input);
        Task todoTask = parser.createToDo(input);
        storage.addTaskToFile(todoTask);
        return ui.echo(todoTask, list);
    }
    
    public String getDeadlineResponse(Ui ui, Parser parser, Storage storage, TaskList list, String input)
            throws DukeException, IOException {
        ui.handleDeadlineError(input);
        Task deadlineTask = parser.createDeadline(input);
        storage.addTaskToFile(deadlineTask);
        return ui.echo(deadlineTask, list);
    }
    
    public String getEventResponse(Ui ui, Parser parser, Storage storage, TaskList list, String input)
            throws DukeException, IOException {
        ui.handleEventError(input);
        Task eventTask = parser.createEvent(input);
        storage.addTaskToFile(eventTask);
        return ui.echo(eventTask, list);
    }
    
    public String getDeleteResponse(Ui ui, Parser parser, Storage storage, TaskList list, String input)
            throws DukeException, IOException {
        int index = Integer.parseInt(input.substring(7));
        ui.handleDeleteError(list, index);
        Task taskDelete = parser.getTaskToDelete(input, list);
        String result = ui.deleting(taskDelete, list);
        list.delete(taskDelete);
        storage.changeFileContent(list);
        return result;
    }
    
    public String getFindResponse(Ui ui, Parser parser, TaskList list, String input) {
        String keyword = parser.getKeywordForFind(input);
        return ui.finding(list, keyword);
    }
}
