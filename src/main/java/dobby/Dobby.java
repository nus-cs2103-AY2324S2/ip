package dobby;

import java.util.Scanner;

public class Dobby {
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

    private static String filePath = "data/dobby_tasks.txt";

    /**
     * Constructs a Duke instance.
     *
     * @param filePath The file path for storing and saving the data.
     */
    public Dobby(String filePath) {
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

    public Dobby() {
        this(filePath);
    }

    /**
     * Runs the Duke program according to the user command line.
     */
    public void run() {
        ui.welcome();
        while(true) {
            Scanner sc = new Scanner(System.in);        
            String order = sc.nextLine();
            String response = getResponse(order);
            System.out.println(response);
            if (order.equals("bye")) {
                System.exit(0);
            }
        }
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String input) {
        String response;

        String[] orders = input.split(" ");

        if (orders[0].equals("bye")) {
            response = ui.bye();
            System.exit(0);
        } else if (orders[0].equals("list")) {
            response = ui.list(tasks.getTasks());
        } else if (orders[0].equals("unmark")) {
            try {
                int number = parser.parseUnmark(input, tasks.getSize());
                tasks.unmark(number);
                response = ui.unmark(tasks.getTask(number));
            } catch (DukeException e) {
                response = e.getMessage();
            }
        } else if (orders[0].equals("mark")) {
            try {
                int number = parser.parseMark(input, tasks.getSize());
                tasks.mark(number);
                response = ui.mark(tasks.getTask(number));
            } catch (DukeException e) {
                response = e.getMessage();
            }
        } else if (orders[0].equals("todo")) { 
            try {
                Task task = parser.parseTodo(input);
                tasks.addTask(task);
                response = ui.addedMessage(task) + "\n" +ui.totalTask(tasks.getSize());
                storage.save(tasks.getTasks());
            } catch (DukeException e) {
                response = e.getMessage();
            }
        } else if (orders[0].equals("deadline")) { 
            try {
                Task task = parser.parseDeadline(input);
                tasks.addTask(task);
                response = ui.addedMessage(task) + "\n" + ui.totalTask(tasks.getSize());
                storage.save(tasks.getTasks());
            } catch (DukeException e) {
                response = e.getMessage();
            }
        } else if (orders[0].equals("event")) { 
            try {
                Task task = parser.parseEvent(input);
                tasks.addTask(task);
                response = ui.addedMessage(task) + "\n" + ui.totalTask(tasks.getSize());
                storage.save(tasks.getTasks());
            } catch (DukeException e) {
                response = e.getMessage();
            }
        } else if (orders[0].equals("delete")) {
            try {
                int number = parser.parseDelete(input, tasks.getSize());
                response = ui.deletedMessage(tasks.getTask(number));
                tasks.deleteTask(number);
                response += "\n" + ui.totalTask(tasks.getSize());
                storage.save(tasks.getTasks());
            } catch (DukeException e) {
                response = e.getMessage();
            }
        } else if (orders[0].equals("find")) {
            try {
                String word = parser.parseFind(input);
                TaskList newtasks = new TaskList(tasks.findTasks(word));
                response = "\n" + ui.findList(newtasks.getTasks());
            } catch (DukeException e) {
                response = e.getMessage();
            }
        } else {
            response = ui.dontUnderstand();
        }
        return response;
    }
        
    /**
     * Specifies the entry point for the program and provides the file path for task data.
     *
     * @param args The command-line arguments.
     */
    public static void main(String[] args) {
        String filePath = "data/dobby_tasks.txt"; // Default file path
        new Dobby(filePath).run();
    }
}