import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Duke {

    // confirmation comment after adding a task
    public static String addComment (String task) {
        return "____________________________________________________________\r\n" +
                " Added: " + task + "\n" +
                "____________________________________________________________\r\n";
    }

    // to list the tasks
    public static void listTasks(Task[] taskList, int count) {
        for (int i = 0; i < count; i++ ) {
            int number = i + 1;
            String result = "   " + number + ". " + taskList[i].toString();
            System.out.println(result);
        }
    }

    public static void main(String[] args) {
        // bot image
        String mickey = "(\\_/)\n" +
                "( •,•)\n" +
                "(\")_(\")";
        System.out.println("RAWR!\n" + mickey);
        // intro
        String intro = "____________________________________________________________\r\n" +
                " Hello! I'm Mickey\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\r\n";
        // outro
        String outro = "____________________________________________________________\r\n" +
                " Bye. See you soon!\n" +
                "____________________________________________________________\r\n";
        System.out.println(intro);

        Scanner sc = new Scanner(System.in);
        String userInput;
        Task[] tasks = new Task[100];
        int count = 0;

        while(true) {
            userInput = sc.nextLine();
            Pattern markPattern = Pattern.compile("Mark \\d+");
            Matcher markMatcher = markPattern.matcher(userInput);
            Pattern unmarkPattern = Pattern.compile("Unmark \\d+");
            Matcher unmarkMatcher = unmarkPattern.matcher(userInput);

            char lastChar = userInput.charAt(userInput.length() - 1);
             if (userInput.equals("bye")) {
                 System.out.println(outro);
                 break;
             } else if (userInput.equals("list")) {
                 System.out.print("____________________________________________________________\r\n");
                 System.out.println("Here are the tasks in your list:");
                 listTasks(tasks, count);
                 System.out.println("____________________________________________________________\r\n");
             } else if (markMatcher.matches()) {
                 int lastNumber = Character.getNumericValue(lastChar);
                 tasks[lastNumber-1].finishTask();
                 String markMessage = "____________________________________________________________\r\n"
                         + "Nice! I have marked this task as done:\n" + "   " + tasks[lastNumber-1].toString() + "\n"
                         + "____________________________________________________________\n";
                 System.out.println(markMessage);
             } else if (unmarkMatcher.matches()) {
                 int lastNumber = Character.getNumericValue(lastChar);
                 tasks[lastNumber-1].redoTask();
                 String unmarkMessage = "____________________________________________________________\r\n"
                         + "OK, I have marked this task as not done yet:\n" + "   " + tasks[lastNumber-1].toString() + "\n"
                         + "____________________________________________________________\n";
                 System.out.println(unmarkMessage);
             } else {
                 tasks[count] = new Task(userInput, 0);
                 count++;
                 System.out.println(addComment(userInput));
            }
        }

    }
}
