package duke;
import java.util.List;
import java.util.Scanner;

public class Duke {
    /**
     * The main method for the Duke program.
     * Reads user commands and actions until the user enters "bye"
     *
     * @param args The command-line
     */
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    /**
     * Constructs a Duke instance.
     *
     * @param filePath The file path for storing and saving the data.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
        parser = new Parser();
    }

    /**
     * Runs the Duke program according to the user command line.
     */
    public void run() {
        ui.welcome();
        while(true) {
            Scanner sc = new Scanner(System.in);        
            String order = sc.nextLine();
            String[] orders = order.split(" "); 
            if (orders[0].equals("bye")) {
                ui.bye();
                System.exit(0);
            } else if (orders[0].equals("list")) {
                ui.list(tasks.getTasks());
            } else if (orders[0].equals("unmark")) {
                try {
                    int number = parser.parseUnmark(order, tasks.getSize());
                    tasks.unmark(number);
                    ui.unmark(tasks.getTask(number));
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
            } else if (orders[0].equals("mark")) {
                try {
                    int number = parser.parseMark(order, tasks.getSize());
                    tasks.mark(number);
                    ui.mark(tasks.getTask(number));
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
            } else if (orders[0].equals("todo")) {  
                try {
                    Task task = parser.parseTodo(order);
                    tasks.addTask(task);
                    ui.addedMessage(task);
                    ui.totalTask(tasks.getSize());
                    storage.save(tasks.getTasks());
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
            } else if (orders[0].equals("deadline")) {  
                try {
                    Task task = parser.parseDeadline(order);
                    tasks.addTask(task);
                    ui.addedMessage(task);
                    ui.totalTask(tasks.getSize());
                    storage.save(tasks.getTasks());
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
            } else if (orders[0].equals("event")) {  
                try {
                    Task task = parser.parseEvent(order);
                    tasks.addTask(task);
                    ui.addedMessage(task);
                    ui.totalTask(tasks.getSize());
                    storage.save(tasks.getTasks());
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
            } else if (orders[0].equals("delete")) {
                try {
                    int number = parser.parseDelete(order, tasks.getSize());
                    ui.deletedMessage(tasks.getTask(number));
                    tasks.deleteTask(number);
                    ui.totalTask(tasks.getSize());
                    storage.save(tasks.getTasks());
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
            } else if (orders[0].equals("find")) {
                try {
                    String word = parser.parseFind(order);
                    TaskList newtasks = new TaskList(tasks.findTasks(word));
                    ui.findList(newtasks.getTasks());
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                ui.dontUnderstand();
            }
        }
    }
    
    /**
     * The entry point(the file path) for the program. 
     *
     * @param args The command-line arguments.
     */
    public static void main(String[] args) {
        new Duke("/Users/leedoye/ip/src/data/duke_tasks.txt").run();
    }
}




