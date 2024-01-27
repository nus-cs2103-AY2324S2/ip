package kaiyap;

import exceptions.KaiYapException;
import java.util.Scanner;

public class Parser {

    public String decideAction(String input) {
            if (input.equals("list")) {
                return "listInputs";
            } else if (input.startsWith("mark ") || input.equals("mark")) {
                return "mark";
            } else if (input.startsWith("unmark ") || input.equals("unmark")) {
                return "unmark";
            } else if (input.startsWith("delete ") || input.equals("delete")) {
                return "delete";
            } else {
                return "default";
            }
    }
}
