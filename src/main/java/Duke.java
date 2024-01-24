import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        //Starting and Ending Text
        String start = "Hello! I'm Unknown \n"
                + "What can I do for you? \n";
        String end = "Bye. Hope to see you again soon!\n";

        //Boolean value to indicate whether the user has finished
        boolean finished = false;

        //Array to contain the task list
        String[] taskList = new String[100];

        //Printing of Start text
        PrintingString(start);

        Scanner in = new Scanner(System.in);

        for(int i = 0; !finished; i++) {
            String s = in.nextLine();

            if (s.equalsIgnoreCase("bye")) {
                finished = true;
            } else if (s.equalsIgnoreCase("list")) {
                PrintingList(taskList, i);
            } else {
                taskList[i] = s;
                PrintingString("added: " + s + "\n");
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

    public static void PrintingList(String[] lst, int size) {
        //Function to produce the string for the list to be printed
        String out = "";
        for(int i = 1; i < size + 1; i++) {
            out += i + ". " + lst[i - 1] + "\n";
        }
        PrintingString(out);
    }
}
