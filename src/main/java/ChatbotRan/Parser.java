package ChatbotRan;

import java.util.ArrayList;

/**
 * Parses and executes commands
 */
public class Parser {
    boolean isComplete = false;

    /**
     * Returns whether the user has input a quit command
     *
     * @return True if quit
     */
    public boolean running() {
        return !isComplete;
    }

    /**
     * Parses and executes a given line, acting on the tasks and printing to the UI.
     *
     * @param line     line of text
     * @param taskList
     * @param ui
     */

    public void exec(String line, TaskList taskList, RanUi ui) {
        int space = line.indexOf(' ');
        String command = space == -1 ? line : line.substring(0, space);
        Task task;
        switch (command) {
        case "mark":
            task = this.handleTaskNo(line, space, taskList);
            ui.mark(task.isCompleted());
            task.setCompleted(true);
            taskList.updateTasks();
            ui.printTask(task);
            break;
        case "unmark":

            task = this.handleTaskNo(line, space, taskList);
            ui.unmark(task.isCompleted());
            task.setCompleted(false);
            taskList.updateTasks();
            ui.printTask(task);
            break;
        case "delete":
            task = this.handleTaskNo(line, space, taskList);
            taskList.remove(task);
            ui.delete(task);
            ui.printNumber(taskList.size());
            break;
        case "deadline":
            Deadline deadline = Deadline.parse(line, space);
            this.addTask(deadline, taskList, ui);
            break;
        case "todo":
            Todo todo = Todo.parse(line, space);
            this.addTask(todo, taskList, ui);
            break;
        case "event":
            Event event = Event.parse(line, space);
            this.addTask(event, taskList, ui);
            break;
        case "find":
            if (space < 0) {
                throw new TaskException("You forgot to include the search input.");
            }
            ArrayList<Task> tasks = taskList.find(line.substring(space).strip());
            ui.found(tasks);
            break;
        default:
            switch (line) {
            case "bye":
                this.isComplete = true;
                break;
            case "list":
                ui.printTasks(taskList);
                break;
            default:
                ui.unknown();
            }


        }
    }

    private Task handleTaskNo(String line, int posOfSpace, TaskList taskList) throws TaskException {
        if (posOfSpace == -1) {
            throw new TaskException("Missing task number.");
        }
        Integer taskNo = Util.parseNumber(line, posOfSpace);
        if (taskNo == null || taskNo < 1) {
            throw new TaskException("Invalid task number.");
        } else if (taskNo > taskList.size()) {
            throw new TaskException("No task by that number.");
        } else {
            return taskList.get(taskNo - 1);
        }
    }

    private void addTask(Task task, TaskList taskList, RanUi ui) {
        taskList.add(task);
        ui.addTask(task);
        ui.printNumber(taskList.size());

    }
}
