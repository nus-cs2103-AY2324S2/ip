package Gluti.helpers;

import Gluti.utils.GlutiException;

import java.util.Scanner;

/**
 * Represents the control hub for user input and filestorage for user
 */
public class Ui {
    private Parser parser;
    private boolean isExit ;
    private Scanner sc;

    /**
     * Initializes a Ui instance and sets the status to "working"
     * @param fStorage the filestorage object that is going to be used in the program
     */
    public Ui(FileStorage fStorage){
        this.parser = new Parser(fStorage);
        isExit  = false;
        sc = new Scanner(System.in);
    }

    /**
     * Runs the program loop and calls the Parser to parse user inputs
     * @throws GlutiException Exceptions caught during parsing
     */
    public void run() throws GlutiException {
        parser.parse(sc.nextLine());
        while(!isExit) {
            parser.parse(sc.nextLine());
            isExit = parser.isLooping();
        }
    }
}
