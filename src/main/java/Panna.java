import java.util.Scanner;

public class Panna {

    private static String command = "";

    public static void main(String[] args) {
        System.out.println("----------------------------------------------------------\n"
        + "Hello! I'm Panna.\n" +
                "What can I do for you?\n\n" +
                "----------------------------------------------------------");


            Scanner s = new Scanner(System.in);

            Panna.command = s.nextLine();

        while (!Panna.command.equals("bye")) {

            Task t = new Task(Panna.command);

            System.out.println(Panna.command);

            if (Panna.command.equals("list")) {
                t.display();
            }

            else if (Panna.command.equals("mark")) {
                t.mark();


            }

            else if (Panna.command.equals("unmark")) {
                t.unmark();


            }

            else if (Panna.command.startsWith("event")) {
                t.markEvent();
            }





            else {
                t.add();
            }

            Panna.command = s.nextLine();
        }



        System.out.println("----------------------------------------------------------\n"  +
                "Bye. Hope to see you again soon!\n\n" +
                "----------------------------------------------------------");
    }



}
