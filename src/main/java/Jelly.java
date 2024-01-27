import java.util.*;

public class Jelly {

    private static TaskList list = new TaskList();
    private static String line = "\n-------------------------------------------";

    private static String welcome = "(ᵔ_ᵔ) Hello! I'm Jelly\nWhat can I do for you?";
    private static String farewell = "(•́︿•̀) Bye. Hope to see you again soon!";
    public static void main(String[] args) {

        System.out.println(line);
        System.out.println(welcome);
        System.out.println(line);

        Scanner scanner = new Scanner(System.in);

        while(!Read(scanner).equals("bye"));

        System.out.println(farewell);
        System.out.println(line);
    }

    public static String Read(Scanner scanner){

        String message = scanner.nextLine();
        String[] lines = message.split("\\s+");
        String command = lines[0];

        String argument = "";

        if(lines.length > 1) {
        argument = message.substring(command.length() + 1);
        }

        System.out.println(line);

        switch(command){

            case "bye":
                return command;

            case "list":
                System.out.println(list);
                break;

            case "mark":
                list.markTask(Integer.parseInt(lines[1]));
                break;

            case "unmark":
                list.unmarkTask(Integer.parseInt(lines[1]));
                break;

            case "todo":

                if(argument.length() == 0){

                    System.out.println("(X_x) Formatting error! Task name missing");
                }
                list.addTodo(argument);
                break;

            case "deadline":

                Integer deadlineIndex = argument.indexOf("/by ");

                if(deadlineIndex.equals(-1)){ //formatting error

                    System.out.println("(X_x) Formatting error! /by is missing");
                    break;
                }

                String deadline = argument.substring(deadlineIndex + 3);

                if(deadline.length()==1){

                    System.out.println("(X_x) Formatting error! nothing after /by");
                    break;
                }

                deadline = deadline.substring(1);

                argument = argument.substring(0, argument.indexOf("/"));

                if(!argument.endsWith(" ")){

                    System.out.println("(X_x) Formatting error! you need a space before any '/'");
                    break;
                }

                list.addDeadline(argument, deadline);

                break;

            case "event":

                Integer startIndex = argument.indexOf("/from ");

                if(startIndex.equals(-1)){

                    System.out.println("(X_x) Formatting error! /from is missing");
                    break;
                }

                String timeframe = argument.substring(startIndex+1);

                String start = timeframe.substring(4);

                Integer endIndex = start.indexOf("/to ");

                if(endIndex.equals(-1)){

                    System.out.println("(X_x) Formatting error! /to is missing");
                    break;
                }

                String end = start.substring(endIndex+3);
                start = start.substring(0, endIndex);

                if(start.length() == 1){

                    System.out.println("(X_x) Formatting error! nothing after /from ");
                    break;
                }

                start = start.substring(1);

                if(!start.endsWith(" ")){

                    System.out.println("(X_x) Formatting error! you need a space before any '/'");
                    break;
                }

                start = start.substring(0, start.length()-1);

                if(end.length() == 1){

                    System.out.println("(X_x) Formatting error! nothing after /to");
                    break;
                }

                end = end.substring(1);

                argument = argument.substring(0, argument.indexOf("/"));

                if(!argument.endsWith(" ")){

                    System.out.println("(X_x) Formatting error! you need a space before any '/'");
                    break;
                }

                list.addEvent(argument, start, end);

                break;

            case "delete":

                if(argument.length() == 0){

                    System.out.println("(X_x) Formatting error! no index received");
                    break;
                }

                list.deleteTask(Integer.parseInt(lines[1]));

                break;

            default:

                System.out.println("(O_o) Huh? What does that even mean?");
        }

        System.out.println(line);
        return command;
    }
}
