package awex;

import java.io.IOException;

public class Awex {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for an Awex object
     */
    public Awex() {
        this.storage = new Storage("./list.txt");
        this.ui = new Ui();
        try {
            this.tasks = new TaskList(storage.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public String sendGreeting() {
        return this.ui.greeting();
    }

    public String getResponse(String input) {
        String[] arr = input.split(" ", 2);
        if (input.equals("bye")) {
            return Parser.byeParser(this.storage, this.tasks, this.ui);
        } else if (input.equals("help")) {
            return Parser.helpParser(this.ui);
        } else if (input.equals("list")) {
            return Parser.listParser(arr, this.tasks, this.ui);
        } else if (arr[0].equals("find")) {
            return Parser.findParser(arr, this.tasks, this.ui);
        } else if (arr[0].equals("mark") || arr[0].equals("unmark") || arr[0].equals("delete")) {
            return Parser.markAndDeleteParser(input, arr, this.tasks, this.ui);
        } else {
            return Parser.taskParser(input, arr, this.tasks, this.ui);
        }
    }
}