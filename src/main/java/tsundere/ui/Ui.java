package tsundere.ui;

import java.util.Scanner;

import tsundere.exception.GeneralException;

public class Ui {
    private static final Scanner sc = new Scanner(System.in);

    public Ui() {
    }

//    private void exit() {
//        System.out.println("_________________________________________________________________________________\n"
//                + "Don't forget about me!\n" + "You better come back soon!\n"
//                +"_________________________________________________________________________________");
//    }

    /**
     * Initializes Parser to read commands from command line for execution.
     * Exits program when "bye" command is given.
     */
    public String chat(String input) {

        String response;

        Parser parser = new Parser(input);
        try {
            response = parser.execute();
        } catch (GeneralException e) {
            response = e.toString();
        }
        return response;
    }
}
