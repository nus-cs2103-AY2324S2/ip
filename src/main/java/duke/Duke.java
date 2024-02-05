package duke;

import java.nio.file.Paths;
import java.util.Scanner;  // Import the Scanner class
import java.nio.file.Path;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    public Duke(Path filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadTasksFromFile());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }
    public static String currentDir = System.getProperty("user.dir");
    public static final Path filePath = Paths.get(currentDir, "src", "main", "java", "duke", "data", "data.txt");

    public void run() {
        this.ui.showWelcome();
        boolean isExit = false;

        Scanner scanner = new Scanner(System.in); // Create scanner
        String command = scanner.nextLine();  // Read user command

        String[] tempArr;
        Task currTask;

        while (!command.equals("bye")) {

            try {
                Duke.checkCommand(command);
            }
            catch (DukeException e) {
                System.err.println(e.getMessage());
                command = scanner.nextLine(); // Read next command
                continue;
            }

            if (command.equals("list")) {
                this.ui.showDivider();
                this.ui.showListMessage();
                this.tasks.showTasks();
                this.ui.showDivider();
                command = scanner.nextLine(); // Read next command
                continue;
            }

            if (command.startsWith("delete")) {
                tempArr = command.split(" ");
                currTask = this.tasks.getTask(Integer.parseInt(tempArr[1]) - 1);
                this.tasks.deleteTask(Integer.parseInt(tempArr[1]) - 1);
                this.ui.showDivider();
                this.ui.showDeleteMessage(currTask, tasks);
                this.ui.showDivider();
                this.storage.updateStorageFile(this.tasks);
                command = scanner.nextLine(); // Read next command
                continue;
            }

            if (command.contains("todo") || command.contains("deadline") || command.contains("event")) {
                tempArr = command.split(" ");
                switch (tempArr[0]) {
                    case ("todo"):
                        try {
                            this.ui.showDivider();
                            Todo currTodo = new Todo(command.substring(5));
                            this.tasks.addTask(currTodo);
                            this.ui.showToDoMessage(currTodo, this.tasks);
                            this.ui.showDivider();
                            this.storage.updateStorageFile(this.tasks);
                        } catch (TodoException e) {
                            System.err.println(e.getMessage());
                        }
                        break;

                    case ("deadline"):
                        this.ui.showDivider();
                        tempArr = (command.substring(9)).split("/");
                        Deadline currDeadline = new Deadline(tempArr[0], tempArr[1].substring(3));
                        this.tasks.addTask(currDeadline);
                        this.ui.showDeadlineMessage(currDeadline,this.tasks);
                        this.ui.showDivider();
                        this.storage.updateStorageFile(this.tasks);
                        break;

                    case ("event"):
                        this.ui.showDivider();
                        tempArr = (command.substring(6)).split("/");
                        Event currEvent = new Event(tempArr[0], tempArr[1].substring(5), tempArr[2].substring(3));
                        this.tasks.addTask(currEvent);
                        this.ui.showEventMessage(currEvent, this.tasks);
                        this.ui.showDivider();
                        this.storage.updateStorageFile(this.tasks);
                        break;
                }
                command = scanner.nextLine(); // Read next command
                continue;
            }

            if (command.contains("mark")) {
                tempArr = command.split(" ");
                currTask = this.tasks.getTask(Integer.parseInt(tempArr[1]) - 1);
                switch (tempArr[0]) {
                    case ("mark"):
                        this.ui.showDivider();
                        this.tasks.markTask(Integer.parseInt(tempArr[1]) - 1);
                        this.ui.showMarkMessage(currTask);
                        this.ui.showDivider();
                        this.storage.updateStorageFile(this.tasks);
                        break;

                    case ("unmark"):
                        this.ui.showDivider();
                        this.tasks.unMarkTask(Integer.parseInt(tempArr[1]) - 1);
                        this.ui.showUnmarkMessage(currTask);
                        this.ui.showDivider();
                        this.storage.updateStorageFile(this.tasks);
                        break;

                    default:
                        this.ui.showWrongCommand();
                }
                command = scanner.nextLine(); // Read next command
                continue;
            }

            command = scanner.nextLine(); // Read next command
        }

    }

    public static void main(String[] args){
        new Duke(filePath).run();
    }

    public static void checkCommand (String command) throws DukeException {
        String line = "____________________________________________________________";
        if (!(command.startsWith("todo") || command.startsWith("deadline") || command.startsWith("event") || command.startsWith("list") || command.startsWith("bye") || command.startsWith("delete") || command.contains("mark"))) {
            throw new DukeException("\n" + line + "\nOPPS!!! I'm sorry, but I don't know what that means :-(\n" + line);
        }
    }

}