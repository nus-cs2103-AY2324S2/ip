import java.util.ArrayList;
import java.util.Scanner;


public class Duke {

    public static void main(String[] args) {
        String sayHi = "Helloo! I'm LilyBot ;)\nWhat's up'?\n";
        String divider = "_____________________________________________";
        ArrayList<String> ls = new ArrayList<>();
        int counter = 0;

        //Scanner for getting user input
        Scanner myCom = new Scanner(System.in);
        System.out.println(sayHi + divider);
        String command = myCom.nextLine();

        while (!command.equals("bye")) {
            if (command.equals("list")) {
                for (String i : ls) {
                    counter++;
                    System.out.println(counter + ". " + i);
                }
                System.out.println(divider);
                command = myCom.nextLine();
            } else {
                ls.add(command);
                System.out.println("  added: "  + command + "\n" + divider);
                command = myCom.nextLine();
            }
        }


        if (command.equals("bye")) {
            String sayBye = "Bye Bye. See u later!\n";
            System.out.println(sayBye + divider);
        }

    }
}
