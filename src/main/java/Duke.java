
import java.util.Scanner;

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
            String commandString = sc.nextLine();
            String[] command = commandString.split(" ");

            if(command[0].equals("bye")) {
                System.out.println("Bye. CMU_bot is always here for you, see you again!");
                isExit = false;
            } else if (command[0].equals("List")) {
                for (int i = 0; i < NumOfTask; i ++) {
                    System.out.println(Integer.toString(i + 1) + ". " +  TaskList[i].toString());
                }
            }  else if (command[0].equals("mark")) {
                int NumToMark = Integer.parseInt(command[1]) - 1;
                TaskList[NumToMark].setStatusIcon(true);

                System.out.println("Nice! I've marked this task as done:");
                System.out.println(Integer.toString(NumToMark + 1) + ". " +  TaskList[NumToMark].toString());

            } else if (command[0].equals("unmark")) { // error need to be paid attention
                int NumToUnmark = Integer.parseInt(command[1]) - 1;
                TaskList[NumToUnmark].setStatusIcon(false);

                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(Integer.toString(NumToUnmark + 1) + ". " +  TaskList[NumToUnmark].toString());

            } else {
                Task task = new Task(commandString);
                TaskList[NumOfTask] = task;
                NumOfTask ++;
                System.out.println("added: " + task.description);
            }

        }
    }
}
