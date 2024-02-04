
import java.util.Scanner;
import Exception.DukeUnknownCommandException;
import Exception.DukeException;

public class Duke {
    public static void main(String[] args) {
        String logo = "CMU_bot";
        Task[] TaskList = new Task[100];
        int NumOfTask = 0;
        System.out.println("Hello! Boss I'm your" + logo);
        System.out.println("What can I do for you?");

        Scanner sc = new Scanner(System.in);
        boolean isExit = true;
        while (isExit) {
            try {
                String commandString = sc.nextLine();
                String[] command = commandString.split(" ", 2);

                if(command[0].equals("bye")) {
                    System.out.println("Bye. CMU_bot is always here for you, see you again!");
                    isExit = false;
                } else if (command[0].equals("List")) {
                    for (int i = 0; i < NumOfTask; i ++) {
                        System.out.println(i + 1 + ". " +  TaskList[i].toString());
                    }
                }  else if (command[0].equals("mark")) {
                    int NumToMark = Integer.parseInt(command[1]) - 1;
                    TaskList[NumToMark].setStatusIcon(true);

                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println((NumToMark + 1) + ". " +  TaskList[NumToMark].toString());

                } else if (command[0].equals("unmark")) { // error need to be paid attention
                    int NumToUnmark = Integer.parseInt(command[1]) - 1;
                    TaskList[NumToUnmark].setStatusIcon(false);

                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println((NumToUnmark + 1) + ". " +  TaskList[NumToUnmark].toString());

                } else if (command[0].equals("todo")){
                    Todo todo = new Todo(command[1]);
                    TaskList[NumOfTask] = todo;
                    NumOfTask ++;
                    System.out.println("Got it, I have added this task");
                    System.out.println((NumOfTask) + ". " + todo.toString());
                    System.out.println("Now you have " + (NumOfTask) + " tasks in the list");

                } else if (command[0].equals("deadline")) {
                    String[] tasktime = command[1].split("/", 2);
                    if (tasktime.length == 1) {
                        throw new DukeException("Missing deadline time");
                    }
                    String des = tasktime[0];
                    String by = tasktime[1].substring(3);
                    Deadline deadline = new Deadline(des, by);
                    TaskList[NumOfTask] = deadline;
                    NumOfTask ++;
                    System.out.println("Got it, I have added this task");
                    System.out.println((NumOfTask) + ". " + deadline.toString());
                    System.out.println("Now you have " + (NumOfTask) + " tasks in the list");

                } else if (command[0].equals("event")) {
                    String[] tasktime = command[1].split("/", 3);
                    if (tasktime.length < 3) {
                        throw new DukeException("the date is not complete");
                    }
                    String des = tasktime[0];
                    String from = tasktime[1].substring(5);
                    String to = tasktime[1].substring(3);
                    Event event = new Event(des, from, to);
                    TaskList[NumOfTask] = event;
                    NumOfTask ++;
                    System.out.println("Got it, I have added this task");
                    System.out.println((NumOfTask) + ". " + event.toString());
                    System.out.println("Now you have " + (NumOfTask) + " tasks in the list");

                } else {
                    throw new DukeUnknownCommandException();
                }

            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }


        }
    }
}
