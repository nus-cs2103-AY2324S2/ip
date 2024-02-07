import java.util.Scanner;

public class Damon {
    public static void main(String[] args) {
        String logo = " ____\n"
                + "|  _  \\\n"
                + "| | | |\n"
                + "| |_| |\n"
                + "|____/ \n";
        System.out.println("Hello from\n" + logo);

        System.out.println("__________________________________________________________\n"
                + "Hello! Damon is here!\n"
                + "What can I do for you\n"
                + "__________________________________________________________\n");

        while (true) {
            //Solution below adapted from https://stackoverflow.com/a/16252290
            Scanner scanner = new Scanner(System.in);
            String inputString = scanner.nextLine();

            if (inputString.equals("exit")) {
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
}
