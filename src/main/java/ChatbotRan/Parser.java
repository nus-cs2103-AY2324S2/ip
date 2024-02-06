package ChatbotRan;

public class Parser {
    boolean isComplete = false;

    public boolean running() {
        return !isComplete;
    }

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

    private Task handleTaskNo(String line, int space, TaskList taskList) throws TaskException {
        if (space == -1) {
            throw new TaskException("Missing task number.");
        }
        Integer taskNo = Util.parseNumber(line, space);
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
