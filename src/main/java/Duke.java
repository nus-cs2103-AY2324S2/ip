import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm fakegpt\nWhat can I do for you?\n");

        Scanner scanner = new Scanner(System.in);
        TaskList taskList = new TaskList();
        String userInput = scanner.nextLine();

        while (!userInput.toLowerCase().equals("bye")) {
            if (userInput.toLowerCase().equals("list")) { //print list command
                System.out.print(taskList);
            } else {
                String command = userInput.split(" ")[0].toLowerCase();

                switch (command) {
                    case "mark":
                        int taskId = Integer.parseInt(userInput.split(" ")[1]);
                        Task done = taskList.getTask(taskId);
                        done.setDone();
                        break;

                    case "unmark":
                        int taskId1 = Integer.parseInt(userInput.split(" ")[1]);
                        Task notDone = taskList.getTask(taskId1);
                        notDone.setNotDone();
                        break;

                    case "todo":
                        ToDos newThing = new ToDos(userInput.split(" ", 2)[1].trim());
                        taskList.add(newThing);
                        break;

                    case "deadline":
                        String[] info = userInput.substring(9).split("/by");
                        Deadlines newThing2 = new Deadlines(info[0].trim(), info[1].trim());
                        taskList.add(newThing2);
                        break;

                    case "event":
                        String[] description_rest = userInput.substring(6).split("/from");
                        String[] from_to = description_rest[1].split(("/to"));
                        Events newEvent = new Events(description_rest[0].trim(), from_to[0].trim(), from_to[1].trim());
                        taskList.add(newEvent);
                        break;

                    default:
                        System.out.println("I dont know what you are saying, try again");

                }
            }
            userInput = scanner.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}
