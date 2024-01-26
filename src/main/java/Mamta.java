import java.util.Scanner;
import java.util.ArrayList;

public class Mamta {
    private static final ArrayList<Task> history = new ArrayList<Task>();


    public static String greet() {
        return "Hello! I'm Mamta\nWhat can I do for you?";
    }

    public static String exit() {
        return "Bye. Hope to see you again soon!";
    }

    public static String echo(String command, int taskNum) {
        String output = String.format("------------------------------------------\nadded: %s\n------------------------------------------", command);
        if (command.equals("bye")) {
            return String.format("------------------------------------------\n%s\n------------------------------------------", Mamta.exit());
        } else if (command.equals("list")) {
            StringBuilder returnOutput = new StringBuilder();
            int count = 0;
            returnOutput.append("------------------------------------------\n");
            for (Task item : history) {
                count += 1;
                returnOutput.append(String.format("%d. %s\n", count, history.get(count - 1)));
            }
            returnOutput.append("------------------------------------------");
            return returnOutput.toString();
        } else if (command.equals("mark")) {
            history.set(taskNum-1, history.get(taskNum-1).markDone());
            return String.format("------------------------------------------\nNice! I've marked this task as done\n%s\n------------------------------------------", history.get(taskNum-1));
        } else if (command.equals("unmark")) {
            history.set(taskNum-1, history.get(taskNum-1).unmarkTask());
            return String.format("------------------------------------------\nOK, I've marked this task as not done yet:\n%s\n------------------------------------------", history.get(taskNum-1));
        } else {
            history.add(new Task(command));
        }
        return output;

    }


    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(Mamta.greet());

        String userOutput = "";
        while (!userOutput.equals("bye")) {
            Scanner scanner = new Scanner(System.in);
            userOutput = scanner.nextLine();
            String[] splitOutput = userOutput.split(" ");

            String word = "";
            int taskNum = -1;

            //in the case user wants to mark/unmark
            if(splitOutput[0].equals("mark") || splitOutput[0].equals("unmark")) {
                word = splitOutput[0];
                taskNum = Integer.parseInt(splitOutput[1]);
                System.out.println(Mamta.echo(word, taskNum));
            } else {
                System.out.println(Mamta.echo(userOutput, taskNum));
            }



        }
    }
}
