package duke;

import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * Represents the ui for the mainframe.
 */
public class Ui {
final static String bingus_logo = " B   i   n   g   u   s ";
    final static  String name = "Bingus";
    /**
     * Prints intro.
     */
    public static void intro() {
        System.out.println("Hello from\n" + bingus_logo);
        System.out.println("Hello! I'm \n" + name);
        System.out.println("What can I do for you?\n");// initial introductory message

    }
    /**
     * Prints outro.
     */
    public static void bye(Storage storage) throws DukeException{
        System.out.println("Bye. Hope to see you again soon!\n");
        storage.save();
        System.exit(1);// if keyword is bye, exit the program
    }
    /**
     * gives commands to parser to process.
     * @param parser parser used to process commands.
     */
    public static void read_commands(Parser parser) throws DukeException{
        Scanner bfn = new Scanner(
                new InputStreamReader(System.in));// scanner to read user input

        String str = bfn.nextLine();
        while (true) {
            try {
               parser.parse(str);



                str = bfn.nextLine();
            }catch(DukeException ex){
                System.out.println("Exception occured: " + ex);
                str = bfn.nextLine();


            }
        }
    }
}
