// @author Tan Qin Yong
import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        Greeting.print();

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
                    System.out.println("------------------------------------");
                    taskList.printAllTasks();
                    System.out.println("------------------------------------");
                    break;
                }
                case "mark": {
                    System.out.println("------------------------------------");
                    System.out.println("Great job agent 47. I've marked this task as DONE: ");

                    int taskNo = Integer.parseInt(commandArr[1]);
                    taskList.markDoneAtInd(taskNo);
                    System.out.println("------------------------------------");
                    break;
                }
                case "unmark": {
                    System.out.println("------------------------------------");
                    System.out.println("Alright, marking this task as NOT DONE :( : ");

                    int taskNo = Integer.parseInt(commandArr[1]);
                    taskList.markNotDoneAtInd(taskNo);

                    System.out.println("------------------------------------");
                    break;
                }
                default: {
                    Task newTask = new Task(fullCommand);
                    taskList.addTask(newTask);
                    System.out.println("------------------------------------");
                    System.out.println("Added: " + newTask.getDescription());
                    System.out.println("------------------------------------");
                }
            }

        }


    }
}
