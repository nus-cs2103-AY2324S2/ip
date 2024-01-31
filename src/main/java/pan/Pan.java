package pan;

import java.util.Scanner;

/**
 * Pan - Represents the Main Class
 * @author Jerome Goh
 */
public class Pan {
    /**
     * Main Entrypoint into the Pan application.
     */
    public static void main(String[] args) {
        Parser parser = new Parser(new Ui(), new Scanner(System.in), new TaskList(new Storage()));
        parser.parseInput();
    }

}
