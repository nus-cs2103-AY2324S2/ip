import java.io.File;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;


public class Atlas {
    private static final String DATA_PATH = "./data/Atlas.txt";

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Atlas() {
        this.ui = new Ui();
        this.tasks = new TaskList();
        this.storage = new Storage(tasks, DATA_PATH);
    }

    public static void main(String[] args) {
        Atlas atlas = new Atlas();
        atlas.run();
    }

    private static void greet() {
        System.out.println("Hello! I'm Atlas");
        System.out.println("What can I do for you?");
    }

    private static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }



    public void run() {
        ui.showGreeting();
        storage.load();
        boolean isExit = false;

        while (!isExit) {
            try {
                String input = ui.readCommand();
                Command cmd = Parser.parse(input);

                switch (cmd.getCommandType()) {
                case EXIT:
                    isExit = true;
                    break;
                case LIST:
                    ArrayList<Task> allTasks = tasks.getAllTasks();
                    ui.showAllTasks(allTasks);
                    break;
                case MARK:
                    tasks.markTask(cmd.getTaskIndex());
                    ui.showMark(tasks, cmd.getTaskIndex());
                    break;
                case UNMARK:
                    tasks.unmarkTask(cmd.getTaskIndex());
                    ui.showunMark(tasks, cmd.getTaskIndex());
                    break;
                case DELETE:
                    ui.showTaskDeleted(tasks, cmd.getTaskIndex());
                    tasks.deleteTask(cmd.getTaskIndex());
                    break;
                case ADD_TODO:
                    ToDo todo = new ToDo(cmd.getCommandData()[0]);
                    tasks.addTask(todo);
                    ui.showTaskAdded(tasks);
                    break;
                case ADD_DEADLINE:
                    String[] deadlineParts = cmd.getCommandData();
                    LocalDateTime deadlineTime = LocalDateTime.parse(deadlineParts[1], DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
                    Deadline deadline = new Deadline(deadlineParts[0], deadlineTime);
                    tasks.addTask(deadline);
                    ui.showTaskAdded(tasks);
                    break;
                case ADD_EVENT:
                    String[] eventParts = cmd.getCommandData();
                    LocalDateTime startTime = LocalDateTime.parse(eventParts[1], DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
                    LocalDateTime endTime = LocalDateTime.parse(eventParts[2], DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
                    Event event = new Event(eventParts[0], startTime, endTime);
                    tasks.addTask(event);
                    ui.showTaskAdded(tasks);
                    break;
                case TASKS_ON_DATE:
                    LocalDate date = LocalDate.parse(cmd.getCommandData()[0], DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    ui.showTasksOnDate(tasks, date);
                    break;
                case INVALID:
                    ui.showError("Invalid command.");
                    break;
                }
            } catch (AtlasException e) {
                ui.showError(e.getMessage());
            }
        }

        storage.save(tasks);
        ui.showGoodbye();
    }

}
