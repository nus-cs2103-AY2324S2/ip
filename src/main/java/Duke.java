import java.util.Scanner;
public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // This is the fixed storage of all the tasks
        Task[] tasks = new Task[100];
        int index = 0;


        String greetingMsg = "Hello! I'm PingMeBot\n" + "What can I do for you?";
        String exitMsg = "Bye. Hope to see you again soon!";
        System.out.println(greetingMsg);

        while (true) {
            String userInput = sc.nextLine();
            String[] words = userInput.split(" ");
            if (userInput.equals("bye")) {
                System.out.println("\n" + exitMsg);
                break;
            } else if (userInput.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < tasks.length; i++) {
                    if (tasks[i] == null) {
                        break;
                    }
                    int taskNumber = i + 1;
                    System.out.println(taskNumber + "." + tasks[i].toString());
                }
            } else if (words[0].equals("mark")) {
                int taskNumber = Integer.parseInt(words[1]) - 1;
                tasks[taskNumber].markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("  " +  tasks[taskNumber].toString());

            } else if (words[0].equals("unmark")) {
                int taskNum = Integer.parseInt(words[1]) - 1;
                tasks[taskNum].uncheckingTask();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println("  " +  tasks[taskNum].toString());
            } else {
                System.out.println("\n" + "added: " + userInput);
                Task t = new Task(userInput);
                tasks[index] = t;
                index++;
            }
        }
    }
}
