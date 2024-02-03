import javax.swing.*;
import javax.swing.plaf.synth.SynthSpinnerUI;
import java.io.*;

public class Duke {
    protected static boolean initialize = true;

    private Database database;
    private Ui ui;
    private TaskList taskList;

    public Duke(String filePath) {
        database = new Database(filePath, this);
        ui = new Ui();
        taskList = new TaskList(database);
        database.createFile();
        database.loadData(database.readFile());
        initialize = false;
    }

    public void run() throws IOException {
        ui.greet();
        taskList.listTask();

        Parser parser = new Parser();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        boolean isRunning = true;

        while (isRunning) {
            String description = br.readLine();
            String command = parser.parseCommand(description);
            ui.line();
            try {
                switch (command) {
                    case "bye":
                        isRunning = false;
                        ui.bye();
                        break;
                    case "list":
                        taskList.listTask();
                        break;
                    case "mark":
                        taskList.setDone(parser.indexParser(description));
                        break;
                    case "unmark":
                        taskList.setNotDone(parser.indexParser(description));
                        break;
                    case "delete":
                        taskList.removeTask(description);
                        break;
                    default:
                        addingTask(command, description);
                        break;
                    }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            ui.line();
        }
    }


    public void addingTask(String command, String description) throws Exception {
        Parser parser = new Parser();
        switch (command) {
            case "todo":
                String[] parsedTodo = parser.todoParser(description);
                database.writeFile(description);
                taskList.addTask(new Todo(parsedTodo[0]));
                break;
            case "deadline":
                String[] parsedDeadline = parser.deadlineParser(description);
                database.writeFile(description);
                taskList.addTask(new Deadline(parsedDeadline[0], parsedDeadline[1]));
                break;
            case "event":
                String[] parsedEvent = parser.eventParser(description);
                database.writeFile(description);
                taskList.addTask(new Event(parsedEvent[0], parsedEvent[1], parsedEvent[2]));
                break;
            default:
                throw new IllegalArgumentException("Please enter a valid command.");
        }

    }

    public static void main(String[] args) throws IOException {
        new Duke("./data/saved-data").run();
    }
}
