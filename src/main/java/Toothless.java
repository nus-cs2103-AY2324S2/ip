import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Toothless {
    private static String splitLine = "____________________________________________________________";
    private static String chatBotName = "Toothless";
    private static String greetingString = "Hello! I'm " + chatBotName + "!\n"
                            + "What can I do for you?\n" + splitLine;
    private static String exitString = "Bye. Hope to see you again soon!\n" + splitLine;

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.println(splitLine + "\n" + greetingString);

        String input;
        Command command;
        boolean isDone = false;

        while(!isDone){
            input = sc.nextLine();
            System.out.println(splitLine);

            int detailIndex = input.indexOf(" ");
            String detail = input.substring(detailIndex + 1);

            if(input.startsWith("unmark")){
                command = Command.UnMark;
            }
            else if(input.startsWith("mark")){
                command = Command.Mark;
            }
            else if (input.startsWith("todo")){
                command = Command.Todo;
            }
            else if (input.startsWith("deadline")){
                command = Command.Deadline;
            }
            else if (input.startsWith("event")){
                command = Command.Event;
            }
            else if (input.startsWith("list")){
                command = Command.List;
            }
            else if(input.startsWith("bye")){
                command = Command.Bye;
            } else{
                command = Command.Invalid;
            }

            isDone = Command.handleCommand(command, detail);

        }
    }
}
