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
            Parser Parser = new Parser(userInput);

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
                int taskNumber = Parser.markParser();
                tasks[taskNumber].markAsDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println("  " +  tasks[taskNumber].toString());

            } else if (words[0].equals("unmark")) {
                int taskNum = Parser.unmarkParser();
                tasks[taskNum].uncheckingTask();
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println("  " +  tasks[taskNum].toString());
            } else {
                System.out.println("\n" + "Got it. I've added this task:");
                if (words[0].equals("todo")) {
                    ToDos todo = Parser.todoParser();
                    tasks[index] = todo;
                    System.out.println("  " + todo.toString());
                } else if (words[0].equals("deadline")) {
                    Deadline deadlineTask = Parser.deadlineParser();
                    tasks[index] = deadlineTask;
                    System.out.println("  " + deadlineTask.toString());
                } else if (words[0].equals("event")){
                    Events events = Parser.eventsParser();
                    tasks[index] = events;
                    System.out.println("  " + events.toString());
                }

                index++;
                System.out.println("Now you have " + index + " tasks in the list.");
            }
        }
    }
}
