// @author Tan Qin Yong
import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        Greeting.print();
        String line = "------------------------------------";

        Scanner sc = new Scanner(System.in);

        // initialise tasks array
        TaskList taskList = new TaskList();
        boolean exit = false;

        while (!exit) {
            String fullCommand = sc.nextLine();
            String[] commandArr = fullCommand.split(" ");
            String command = commandArr[0].toLowerCase();

            switch (command) {
                case "bye": {
                    System.out.println("Goodbye! Till we meet again ~");
                    exit = true;
                    break;
                }
                case "list": {
                    System.out.println(line);
                    taskList.printAllTasks();
                    System.out.println(line);
                    break;
                }
                case "mark": {
                    System.out.println(line);
                    System.out.println("Great job agent 47. I've marked this task as DONE: ");

                    int taskNo = Integer.parseInt(commandArr[1]);
                    taskList.markDoneAtInd(taskNo);
                    System.out.println(line);
                    break;
                }
                case "unmark": {
                    System.out.println(line);
                    System.out.println("Alright, marking this task as NOT DONE :( : ");

                    int taskNo = Integer.parseInt(commandArr[1]);
                    taskList.markNotDoneAtInd(taskNo);

                    System.out.println(line);
                    break;
                }
                case "deadline": {
                    System.out.println(line);

                    fullCommand = fullCommand.replace("deadline", "")
                                             .replace("/by", "/");
                    String[] splitCommand = fullCommand.split("/");
                    String taskDescription = splitCommand[0].trim();
                    String byDate = splitCommand[1].trim();

                    Deadline dl = new Deadline(taskDescription, byDate);
                    taskList.addTask(dl);

                    System.out.println(line);
                    break;
                }
                case "event": {
                    System.out.println(line);

                    fullCommand = fullCommand.replace("event", "")
                                             .replace("/from", "/")
                                             .replace("/to", "/");
                    String[] splitCommand = fullCommand.split("/");
                    String taskDescription = splitCommand[0].trim();
                    String from = splitCommand[1].trim();
                    String to = splitCommand[2].trim();

                    Event event = new Event(taskDescription, from, to);
                    taskList.addTask(event);

                    System.out.println(line);
                    break;
                }
                case "todo": {
                    System.out.println(line);

                    fullCommand = fullCommand.replace("todo", "").trim();

                    ToDo toDo = new ToDo(fullCommand);
                    taskList.addTask(toDo);

                    System.out.println(line);
                    break;
                }
                default: {
                    System.out.println("Sorry, unknown command given. Please try again.");
                }
            }

        }


    }
}
