import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.*;

public class Duke {
    private boolean isActive;
    private TaskList tasks;
    private UI ui;

    public Duke() {
        this.isActive = true;
        this.tasks = new TaskList();
        this.ui = new UI();
    }
    public static void main(String[] args) {
        Duke ft = new Duke();
        ft.init();
    }
    public void init() {
        ui.initMsg();
        try {
            tasks.loadTask();
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
        ui.byeMsg();
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
                    ui.showList(tasks);
                    break;
                case MARK:
                    tasks.mark(st);
                    break;
                case UNMARK:
                    tasks.unmark(st);
                    break;
                case TODO:
                    tasks.addTask(st, CommandTypes.TODO);
                    break;
                case DEADLINE:
                    tasks.addTask(st, CommandTypes.DEADLINE);
                    break;
                case EVENT:
                    tasks.addTask(st, CommandTypes.EVENT);
                    break;
                case DELETE:
                    tasks.deleteTask(st);
                    break;
                default:
                    throw new ftException("Unknown Command: Please use a correct command");
            }
        }catch (IllegalArgumentException e) {
            throw new ftException("Unknown Command: Please use a correct command");
        }
    }
}

