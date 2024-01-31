import java.util.Scanner;


public class Duke {

    // confirmation comment after adding a task
    public static String addComment (Task task, int n) {
        if (n == 1) {
            return "____________________________________________________________\r\n" +
                    " Got it, I have added this task:\n" + "    "+ task + "\n" + " Now you have " + n + " task in the list.\n" +
                    "____________________________________________________________\r\n";
        }
        return "____________________________________________________________\r\n" +
                " Got it, I have added this task:\n" + "    "+ task + "\n" + " Now you have " + n + " tasks in the list.\n" +
                "____________________________________________________________\r\n";
    }

    // to list the tasks
    public static void listTasks(Task[] taskList, int count) {
        System.out.print("____________________________________________________________\r\n");
        System.out.println(" Here are the tasks in your list:");
        for (int i = 0; i < count; i++ ) {
            int number = i + 1;
            String result = "   " + number + ". " + taskList[i].toString();
            System.out.println(result);
        }
        System.out.println("____________________________________________________________\r\n");
    }

    // to send a mark message
    public static String markMessage(Task task) {
        String message;
        if (task.isDone()) {
            message = "____________________________________________________________\r\n"
                    + " Nice! I have marked this task as done:\n" + "   " + task.toString() + "\n"
                    + "____________________________________________________________\n";
        } else {
            message = "____________________________________________________________\r\n"
                    + " OK, I have marked this task as not done yet:\n" + "   " + task.toString() + "\n"
                    + "____________________________________________________________\n";
        }
        return message;
    }

    public static String introMessage() {
        String greeting = "RAWR!!!";
        String mickey = "(\\_/)\n" +
                "( •,•)\n" +
                "(\")_(\")\n";
        String intro = "____________________________________________________________\r\n" +
                " Hello! I'm Mickey\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\r\n";
        return greeting + mickey + intro;
    }

    public static String outroMessage() {
        String outro = "____________________________________________________________\r\n" +
                " Bye. See you soon!\n" +
                "____________________________________________________________\r\n";
        return outro;
    }

    public static String replacer(String input) {
        String replacedString = input.replaceAll("/(\\w+)", "$1:");
        return replacedString;
    }

    public static String getTask(String input) {
        int index = input.indexOf("/");
        int secondWord = input.indexOf(" ");
        String task = input.substring(secondWord + 1, index);
        return task;
    }

    public static void main(String[] args) {
        System.out.println(introMessage());

        Scanner sc = new Scanner(System.in);
        String userInput;
        Task[] tasks = new Task[100];
        int count = 0;

        while(true) {
            userInput = sc.nextLine();
            String[] userInputArray = userInput.split("\\s+", 2);

            boolean isTodo = userInputArray[0].equals("todo");
            boolean isDeadline = userInputArray[0].equals("deadline");
            boolean isEvent = userInputArray[0].equals("event");
            boolean isMark = userInputArray[0].equals("mark");
            boolean isUnmark = userInputArray[0].equals("unmark");
            char lastChar = userInput.charAt(userInput.length() - 1);


             if (userInput.equals("bye")) {
                 System.out.println(outroMessage());
                 break;
             } else if (userInputArray[0].equals("list")) {
                 listTasks(tasks, count);
             } else if (isTodo || isEvent || isDeadline) {
                if (isTodo) {
                    tasks[count] = new Todo(userInputArray[1], false);
                    count++;
                    System.out.println(addComment(tasks[count - 1], count));
                }
                if (isEvent) {
                    String task = getTask(userInput);
                    int index = userInput.indexOf("/");
                    String when = replacer(userInput).substring(index);
                    tasks[count] = new Event(task, false, when);
                    count++;
                    System.out.println(addComment(tasks[count-1], count));
                }
                if (isDeadline) {
                    int index = userInput.indexOf("/");
                    String when = userInput.substring(index + 3);
                    String task = getTask(userInput);
                    tasks[count] = new Deadline(task, false, when);
                    count++;
                    System.out.println(addComment(tasks[count-1], count));
                }

             } else if (isMark || isUnmark) {
                 if (isMark) {
                     int lastNumber = Character.getNumericValue(lastChar);
                     tasks[lastNumber-1].finishTask();
                     System.out.println(markMessage(tasks[lastNumber - 1]));
                 } else {
                     int lastNumber = Character.getNumericValue(lastChar);
                     tasks[lastNumber-1].redoTask();
                     System.out.println(markMessage(tasks[lastNumber - 1]));
                 }
             } else {
                 tasks[count] = new Task(userInput, false);
                 count++;
                 System.out.println(addComment(tasks[count - 1], count));
            }
        }

    }
}
