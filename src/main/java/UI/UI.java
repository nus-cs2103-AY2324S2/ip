package UI;

import Storage.Storage;

public class UI {
    private static final String lines = "    ____________________________________________________________";
    private static final String name = "Wang";

    private final Storage storage;

    public UI() {
       this.storage = new Storage();
    }

    public static void greeting() {
        System.out.printf("    Hello! I'm %s\n", name);
        System.out.println("    What can I do for you?");
        System.out.println(lines);
    }

    public static void goodbye() {
        System.out.println(lines);
        System.out.println("    Bye. Hope to see you again soon!");
        System.out.println(lines);
    }

    public void addItem(String input) {
        this.storage.add(input);
        System.out.println(lines);
        System.out.println("    " + "added: " + input);
        System.out.println(lines);
    }

    public void markTaskUI(int input) {
        System.out.println(lines);
        System.out.println("    Nice! I've marked this task as done:");
        this.storage.markTask(input);
        System.out.println(lines);
    }
    public void unMarkTask(int input) {
        System.out.println(lines);
        System.out.println("    OK, I've marked this task as not done yet:");
        this.storage.unMarkTask(input);
        System.out.println(lines);
    }

    public void listItems() {
        System.out.println(storage);
    }


}
