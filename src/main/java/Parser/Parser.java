package Parser;

import java.util.ArrayList;

public class Parser {
    private String input;

    public Parser() {
        this.input = "";
    }
    public Parser(String input) {
        this.input = input;
    }

    public void feed(String input) {
        this.input = input;
    }

    public String[] parse() {
        String[] notSplit = {this.input};
        String[] split = this.input.split(" ");
        if (split.length == 2 && (split[0].equals("mark") || split[0].equals("unmark"))) {
            return split;
        }
        return notSplit;
    }

}
