package UI;

import Storage.Storage;

public class UI {
    private static final String lines = "    ____________________________________________________________";
    private static final String name = "Wang";

    private Storage storage;

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
        storage.add(input);
        System.out.println(lines);
        System.out.println("    " + "added: " + input);
        System.out.println(lines);
    }

    public void listItems() {
        System.out.println(storage);
    }


}
