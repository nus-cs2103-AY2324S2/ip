import exceptions.DukeException;
import storage.Storage;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.Todo;
import ui.Parser;
import ui.Ui;

import java.util.HashSet;
import java.util.List;

public class Duke {

    private final Storage storage;
    private final Ui ui;
    private final Parser parser;
    private final List<Task> tasks;


    public Duke() {
        storage = new Storage();
        ui = new Ui();
        parser = new Parser();
        tasks = storage.loadTasks();
    }
    public String getResponse(String input) throws DukeException {
        StringBuilder response = new StringBuilder();
        int index;
        String[] inputs = input.split(" ", 2);
        Parser.Command command = parser.parse(inputs[0]);
        try {
            switch (command) {
            case BYE:
                response.append(ui.printGoodbye());
                break;
            case LIST:
                response.append(ui.printList(tasks));
                break;
            case MARK:
                index = Integer.parseInt(inputs[1]) - 1;
                tasks.get(index).setMark();
                response.append(ui.printMark(tasks.get(index)));
                storage.saveTasks(tasks);
                break;
            case UNMARK:
                index = Integer.parseInt(inputs[1]) - 1;
                tasks.get(index).setUnMark();
                response.append(ui.printUnmark(tasks.get(index)));
                storage.saveTasks(tasks);
                break;
            case TODO:
                Todo newTodo = new Todo(inputs[1]);
                if (!checkDuplicateTask(newTodo,tasks)) {
                    tasks.add(newTodo);
                    response.append(ui.printTodo(newTodo, tasks));
                    storage.saveTasks(tasks);
                } else {
                    response.append("Task already exist");
                }
                break;
            case DEADLINE:
                String[] deadlineSplit = inputs[1].split(" /by ");
                Deadline newDeadline = new Deadline(deadlineSplit[0], deadlineSplit[1]);
                if (!checkDuplicateTask(newDeadline,tasks)) {
                    tasks.add(newDeadline);
                    response.append(ui.printDeadline(newDeadline, tasks));
                    storage.saveTasks(tasks);
                } else {
                    response.append("Task already exist");
                }
                break;
            case EVENT:
                String[] eventSplit = inputs[1].split(" /from | /to ");
                Event newEvent = new Event(eventSplit[0], eventSplit[1], eventSplit[2]);
                if (!checkDuplicateTask(newEvent,tasks)) {
                    tasks.add(newEvent);
                    response.append(ui.printEvent(newEvent, tasks));
                    storage.saveTasks(tasks);
                } else {
                    response.append("Task already exist");
                }
                break;
            case DELETE:
                index = Integer.parseInt(inputs[1]) - 1;
                Task toDelete = tasks.get(index);
                tasks.remove(toDelete);
                response.append(ui.printDelete(toDelete, tasks));
                storage.saveTasks(tasks);
                break;
            case FIND:
                response.append(ui.findTasks(inputs[1], tasks));
                break;
            default:
                response.append(ui.printError("Command not found!"));
                break;
            }

        } catch (ArrayIndexOutOfBoundsException e) {
            ui.printError("Error! Description to command not found!");
        }
        return response.toString();
    }


    public static void main(String[] args) throws DukeException {
        Duke duke = new Duke();


    }

    private boolean checkDuplicateTask(Task newTask, List<Task> tasks) {
        for (Task task : tasks) {
            if (task.toString().equals(newTask.toString())) {
                return true; // Duplicate found
            }
        }
        return false; // No duplicate found
    }
}


