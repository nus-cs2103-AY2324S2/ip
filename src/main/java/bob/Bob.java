package bob;

import java.util.Scanner;

public class Bob {
    private static final String FILE_PATH = "./data/tasks.txt";
    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;
    private final Parser parser;

    /*
     * A constructor for the chatbot Bob.
     */
    public Bob(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(FILE_PATH);
        this.tasks = storage.loadFile();
        this.parser = new Parser(ui);
    }

    /*
     * A method that signals the chatbot to start its processes.
     */
    public void run() {
        ui.showGreetMessage();

        Scanner scanner = new Scanner(System.in);
        TaskList taskList = tasks;

        while (true) {
            String input = scanner.nextLine();

            if (input.equals("bye")) {
                parser.parseExit(storage, tasks);
                break;
            } else if (input.equals("list")) {
                parser.parseList(taskList);
            } else if (input.equals("clear")) {
                parser.parseClear(taskList);
            } else if (input.trim().matches("mark|unmark|deadline|todo|event|delete")) {
                ui.showIncompleteEntryMessage();
            } else if (input.startsWith("mark ")) {
                parser.parseMark(input, taskList);
            } else if (input.startsWith("unmark ")) {
                parser.parseUnmark(input, taskList);
            } else if (input.startsWith("deadline ")) {
                parser.parseDeadline(input, taskList);
            } else if (input.startsWith("todo ")) {
                parser.parseTodo(input, taskList);
            } else if (input.startsWith("event ")) {
                parser.parseEvent(input, taskList);
            } else if (input.startsWith("delete ")) {
                parser.parseDelete(input, taskList);
            } else if (input.startsWith("find ")) {
                parser.parseFind(input, taskList);
            } else {
                ui.showUnknownCommandMessage();
            }
        }

        scanner.close();
    }

    public static void main(String[] args) {
        new Bob(FILE_PATH).run();
    }
}