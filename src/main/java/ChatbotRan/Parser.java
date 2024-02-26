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
        if (!this.running()) {
            return;
        }
        ui.displayInput(line);

        int space = line.indexOf(' ');
        String command = space == -1 ? line : line.substring(0, space);
        Task taskRetrieved;
        Task[] tasksRetrieved;
        switch (command) {
        case "mark":
            tasksRetrieved = this.handleTaskNos(line, taskList);
            for (Task t: tasksRetrieved) {
                ui.mark(t.isCompleted());
                t.setCompleted(true);
                taskList.updateTasks();
                ui.printTask(t);
            }
            break;
        case "unmark":
            tasksRetrieved = this.handleTaskNos(line, taskList);
            for (Task t: tasksRetrieved) {
                ui.unmark(t.isCompleted());
                t.setCompleted(false);
                taskList.updateTasks();
                ui.printTask(t);
            }
            break;
        case "delete":
            tasksRetrieved = this.handleTaskNos(line, taskList);
            for (Task t: tasksRetrieved) {
                taskList.remove(t);
                ui.delete(t);
            }
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
                ui.bye();
                this.isComplete = true;
                break;
            case "list":
                ui.printTasks(taskList);
                break;
            default:
                ui.unknown();
            }
        }

        ui.displayBuiltOutput();
    }

    private Task[] handleTaskNos(String line, TaskList taskList) throws TaskException {
        String[] args = line.split(" ");
        if (args.length < 2) {
            throw new TaskException("Missing task number.");
        }
        Task[] tasks = new Task[args.length-1];
        for (int i = 1; i < args.length; i++) {
            try {
                int currTask = Integer.parseInt(args[i]);
                if (currTask < 1 || currTask > taskList.size()) {
                    throw new TaskException("No task by that number: " + args[i]);
                }
                tasks[i-1] = taskList.get(currTask);
            } catch (NumberFormatException exception) {
                throw new TaskException("Invalid task number: " + args[i]);
            }
        }
        return tasks;
    }

    private void addTask(Task task, TaskList taskList, RanUi ui) {
        taskList.add(task);
        ui.addTask(task);
        ui.printNumber(taskList.size());

    }
}
