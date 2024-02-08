import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.*;

public class Duke {
    private boolean isActive;
    private TaskList tasks;

    public Duke() {
        this.isActive = true;
        this.tasks = new TaskList();
    }
    public static void main(String[] args) {
        Duke ft = new Duke();
        ft.init();
    }
    public void init() {
        UI.initMsg();
        try {
            Storage.loadTask(tasks);
        } catch (ftException e) {
            System.out.println(e.getMessage());
        }
        Scanner sc = new Scanner(System.in);
        while (this.isActive) {
            String input = sc.nextLine();
            try {
                nextAction(input);
            } catch (ftException e) {
                System.out.println(e.getMessage());
            }
        }
        UI.byeMsg();
    }

    private void nextAction(String input) throws ftException {
        if (input.isEmpty()) {
            throw new ftException("Error: Please Type Command");
        }
        StringTokenizer st = new StringTokenizer(input);
        try {
            CommandTypes command = CommandTypes.valueOf(st.nextToken().toUpperCase());
            switch (command) {
                case BYE:
                    this.isActive = false;
                    break;
                case LIST:
                    UI.showList(tasks);
                    break;
                case MARK:
                    tasks.mark(st);
                    Storage.updateTask(tasks);
                    break;
                case UNMARK:
                    tasks.unmark(st);
                    Storage.updateTask(tasks);
                    break;
                case TODO:
                    tasks.addTask(st, CommandTypes.TODO);
                    Storage.updateTask(tasks);
                    break;
                case DEADLINE:
                    tasks.addTask(st, CommandTypes.DEADLINE);
                    Storage.updateTask(tasks);
                    break;
                case EVENT:
                    tasks.addTask(st, CommandTypes.EVENT);
                    Storage.updateTask(tasks);
                    break;
                case DELETE:
                    tasks.deleteTask(st);
                    Storage.updateTask(tasks);
                    break;
                default:
                    throw new ftException("Unknown Command: Please use a correct command");
            }
        }catch (IllegalArgumentException e) {
            throw new ftException("Unknown Command: Please use a correct command");
        }
    }
}

