import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;


public class Jerry {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    private String filePath;

    public Jerry() {
        filePath = "./data/jerry.txt";
        ui = new Ui();
        parser = new Parser();
        storage = new Storage(filePath);
    }


    public static void main(String[] args) {
        new Jerry().run();
    }

    public void run() {
//        File directory = new File("./data");
//        if (!directory.exists()) {
//            directory.mkdirs();
//        }

        ui.showWelcome();
        tasks = new TaskList(Storage.loadTasks(filePath));
        boolean isExit = false;

        while (!isExit) {
            String input = ui.readCommand();
            Command command = parser.parse(input);

            switch (command.getCommandType()) {
                case BYE:
                    isExit = true;
                    break;

                case LIST:
                    ui.showList(tasks);
                    break;

                case MARK:
                    tasks.mark(command.getTaskIndex());
                    ui.showMark(tasks, command.getTaskIndex());
                    break;

                case UNMARK:
                    tasks.unmark(command.getTaskIndex());
                    ui.showUnmark(tasks, command.getTaskIndex());
                    break;

                case DELETE:
                    tasks.deleteTask(command.getTaskIndex());
                    ui.showDelete(tasks, command.getTaskIndex());
                    break;

                case ADD_TODO:
                    ToDo todo = new ToDo(command.getParts());
                    tasks.addTask(todo);
                    ui.showAdded(todo, tasks);
                    break;

                case ADD_DEADLINE:
                    String[] deadlineParts = command.getParts().split(" /by ", 2);
                    Deadline deadline = new Deadline(deadlineParts[0], deadlineParts[1]);
                    if (!deadline.byIsNull()) {
                        tasks.addTask(deadline);
                        ui.showAdded(deadline, tasks);
                    }
                    break;

                case ADD_EVENT:
                    String[] eventParts = command.getParts().split(" /from ", 2);
                    String[] fromTo = eventParts[1].split(" /to ", 2);
                    Event event = new Event(eventParts[0], fromTo[0], fromTo[1]);
                    if (!event.dateTimeIsNull()) {
                        tasks.addTask(event);
                        ui.showAdded(event, tasks);
                    }
                    break;

                case INVALID:
                    ui.showMessage("Invalid Command");
                    break;
            }

            Storage.saveTasks(tasks.getTasks(), "./data/jerry.txt");
        }
        ui.showGoodbye();
    }
}




