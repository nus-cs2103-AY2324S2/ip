import java.util.Scanner;
import java.util.ArrayList;

public class Damon {
    private ArrayList<String> storage;

    Damon() {
        this.storage = new ArrayList<String>();
    }


    public static void main(String[] args) {
        Damon damon = new Damon();
        String logo = " ____\n"
                + "|  _  \\\n"
                + "| | | |\n"
                + "| |_| |\n"
                + "|____/ \n";
        System.out.println("Hello from\n" + logo);

        damon.storeInput();
    }

    void echo() {
        //Solution below adapted from https://stackoverflow.com/a/16252290

        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            String inputString = scanner.nextLine();
            if (inputString.equals("bye")) {
                System.out.println("__________________________________________________________\n"
                        + "Bye. Hope to see you again soon!\n"
                        + "__________________________________________________________\n");
                break;
            }
            System.out.println("__________________________________________________________\n"
                    + inputString + "\n"
                    + "__________________________________________________________\n");
        }
    }
    void storeInput() {
        //Solution below adapted from https://stackoverflow.com/a/16252290
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            String inputString = scanner.nextLine();
            if (inputString.equals("bye")) {
                System.out.println("__________________________________________________________\n"
                        + "Bye. Hope to see you again soon!\n"
                        + "__________________________________________________________\n");
                break;
            }
            this.storage.add(inputString);
            System.out.println("__________________________________________________________\n"
                    + "add: " + inputString + "\n"
                    + "__________________________________________________________\n");
        }
    }

}
