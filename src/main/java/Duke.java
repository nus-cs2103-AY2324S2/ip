
import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        String logo = "CMU_bot";
        String[] TaskList = new String[100];
        int NumOfTask = 0;
        System.out.println("Hello! Boss I'm your" + logo);
        System.out.println("What can I do for you?");

        Scanner sc = new Scanner(System.in);
        boolean isExit = true;
        while (isExit) {
            String command = sc.nextLine();
            if(command.equals("bye")) {
                System.out.println("Bye. CMU_bot is always here for you!");
                isExit = false;
            } else if (command.equals("List")) {
                for (int i = 0; i < NumOfTask; i ++) {
                    System.out.println(TaskList[i]);
                }

            } else {
                TaskList[NumOfTask] = Integer.toString(NumOfTask+1)+". " + command;
                NumOfTask ++;
                System.out.println("added: " + command);
            }

        }
    }
}
