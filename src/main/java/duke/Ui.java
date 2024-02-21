package duke;

import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * Represents the ui for the mainframe.
 */
public class Ui {
final static String BINGUS_LOGO = " B   i   n   g   u   s ";
    final static  String NAME = "Bingus";
    /**
     * Prints intro.
     */
    public static void intro() {
        System.out.println("Hello from\n" + BINGUS_LOGO);
        System.out.println("Hello! I'm " + NAME);
        System.out.println("What can I do for you?\n");// initial introductory message

    }

    public static void read_commands(Parser parser) throws DukeException{
        Scanner bfn = new Scanner(
                new InputStreamReader(System.in));// scanner to read user input

        String str = bfn.nextLine();
        while (true) {
            try {
               parser.parse(str);



                str = bfn.nextLine();
            } catch(DukeException ex){
                System.out.println("Exception occured: " + ex);
                str = bfn.nextLine();


            }
        }
    }
}
