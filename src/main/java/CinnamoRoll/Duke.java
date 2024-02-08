package CinnamoRoll;

import java.util.Scanner;

public class Duke {
    private Ui ui;
    private Storage storage;
    private TaskList tasklist;

    public Duke() {
        this.ui = new Ui();
        storage = new Storage();
        this.tasklist = new TaskList();
    }

    /**
     * Running the main part of the code to start the Chatbot Cinnamo
     */
    public void run() throws Exception {
        this.tasklist = new TaskList(this.storage.loadData());
        Scanner sc = new Scanner(System.in);
        this.ui.greetUser();
        while (true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                break;
            }
            ui.respondUser(this.tasklist, input);
        }
        ui.exitChat();
    }

    public String getResponse(String input) throws Exception {
        try {
            Parser parse = new Parser();
            String[] userInput = parse.parseInput(input);
            switch (User.valueOf(userInput[0])) {
                case MARK:
                    return this.tasklist.markTask(userInput);

                case UNMARK:
                    return this.tasklist.unmarkTask(userInput);

                case LIST:
                    return this.tasklist.listTask();

                case DELETE:
                    return this.tasklist.deleteTask(userInput);

                case TODO:
                    return this.tasklist.executeTask(userInput);

                case DEADLINE:
                    return this.tasklist.executeTask(userInput);

                case EVENT:
                    return this.tasklist.executeTask(userInput);

                case FIND:
                    return this.tasklist.findTask(userInput[1]);

                default:
                    throw new CinnamoException();
            }
        } catch (CinnamoException cin) {
            return cin.toString();
        } catch (IllegalArgumentException e) {
            CinnamoException cin = new CinnamoException();
            return cin.toString();
        }
    }


    public static void main(String[] args) throws Exception {
        new Duke().run();
    }
}