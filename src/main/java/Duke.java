import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        //Starting and Ending Text
        String start = "Hello! I'm Unknown \n"
                + "What can I do for you? \n";
        String end = "Bye. Hope to see you again soon!\n";

        //Boolean value to indicate whether the user has finished
        boolean finished = false;

        //Printing of Start text
        PrintingString(start);

        Scanner in = new Scanner(System.in);

        while(!finished) {
            String s = in.nextLine();

            if (s.equalsIgnoreCase("bye")) {
                finished = true;
            } else {
                PrintingString(s + "\n");
            }
        }

        //Printing of End text
        PrintingString(end);
    }

    public static void PrintingString(String str) {
        //Function to add the line in front and behind the text
        String lnBreak = "_______________________________________________________________\n";
        System.out.println(lnBreak + str + lnBreak);
    }
}
