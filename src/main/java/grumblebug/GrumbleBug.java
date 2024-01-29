package grumblebug;

import java.util.Scanner;

/**
 * Represents the overall chatbot object, with text input/output capabilities.
 */
public class GrumbleBug {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private static Parser parserInput;
    public static String filePath = "./tasks.txt";

    public GrumbleBug() {
        this.storage = new Storage();
        this.taskList = new TaskList();
        this.ui = new Ui();
        this.parserInput = new Parser("yyyy-MM-dd");
    }

    public static void main(String[] args) {
        GrumbleBug bot = new GrumbleBug();
        bot.runBot();
    }

    /**
     * Runs the GrumbleBug chatbot interactions with the user, in a loop until it is terminated by user.
     */
    public void runBot() {
        this.storage.loadFromFile(filePath, taskList);
        this.ui.start();
        Scanner sc = new Scanner(System.in);

        while (true) {
            String input1 = sc.nextLine();
            if (input1.equals("byebye")) {
                ui.bye();
                System.exit(0);
            } else if (input1.equals("list")) { // show the list!
                ui.list(taskList);
            } else if (input1.equals("mark")) {
                ui.mark(taskList);
            } else if (input1.equals("unmark")) {
                ui.unmark(taskList);
            } else if (input1.equals("todo")) { // add to list
                ui.addTodo(taskList);
            } else if (input1.equals("deadline")) { // add to list
                ui.addDeadline(taskList, parserInput);
            } else if (input1.equals("event")) { // add to list
                ui.addEvent(taskList, parserInput);
            } else if (input1.equals("delete")) {
                ui.delete(taskList);
            } else {
                // error, cannot understand
                ui.badInput();
            }
            this.storage.writeToFile(filePath, taskList);
        }
    }
}
