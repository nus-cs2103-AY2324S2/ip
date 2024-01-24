import java.util.Scanner;

public class Youdon {
    public static void main(String[] args) {
        // initialise array and index
        String[] taskList = new String[100];
        int index = 0;

        // string constants
        String line = "----------------------------------------------------------------";
        String welcomeMsg = "Hello! I'm Youdon!\nWhat can I do for you?\n";
        String byeMsg = "Bye. Hope to see you again soon!";

        // chatbot welcome message
        System.out.println(line);
        System.out.println(welcomeMsg);
        System.out.println(line);

        // scan input
        Scanner input = new Scanner(System.in);
        String data = input.nextLine();

        // when there is input present
        while(!(data.isEmpty())) {
            // if input == "bye", print chatbot bye message
            if (data.equals("bye")) {
                System.out.println(line);
                System.out.println(byeMsg);
                System.out.println(line);
                break;
            }

            // if input == "list", return tasklist
            if (data.equals("list")) {
                System.out.println(line);
                for(int i = 0; taskList[i] != null; i++) {
                    System.out.println((i + 1) + ". " + taskList[i]);
                }
                System.out.println(line);
                data = input.nextLine();
                continue;
            }

            // if input not "bye" or "list", add to tasklist
            taskList[index] = data;
            index++;

            // print that task has been added
            System.out.println(line);
            System.out.println("Task added: " + data);
            System.out.println(line);
            data = input.nextLine();
        }
    }
}
