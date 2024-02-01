package ui;
import java.util.ArrayList;
import parser.Parser;
import storage.Storage;

public class Output {
    private Parser parser;
    private Storage storage;

    public Output(Parser parser, Storage storage) {
        this.parser = parser;
        this.storage = storage;
    }

    public static final String LOGO = " _____   _____  _    _ \n"
    + "|  __ \\ / ____|| |  | |\n"
    + "| |__) | (___  | |__| |\n"
    + "|  _  / \\___ \\ |  __  |\n"
    + "| | \\ \\ ____) || |  | |\n"
    + "|_|  \\_|_____/ |_|  |_|\n";


    public static String layer(String s) {
        String line = "____________________________________________________________";
        return line + "\n" + s + "\n" + line; 
    }

    public String welcome() {
        return layer("Hello! I'm \n" + LOGO + "What can I do for you?");
    }

    public String bye() {
        return layer("Bye. Hope to see you again soon!");
    }

    public String execute(String input) {
        if (input.equalsIgnoreCase("bye")) {
            return this.bye();
        } else {
            return layer(this.parser.parse(input));
        }

    }

}
