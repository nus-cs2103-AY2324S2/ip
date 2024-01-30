import java.util.Scanner;

public class Duke {

    public static String addComment (String task) {
        return "____________________________________________________________\r\n" +
                " Added: " + task + "\n" +
                "____________________________________________________________\r\n";
    }

    public static void listTasks(String[] taskList, int count) {
        for (int i = 0; i < count; i++ ) {
            int number = i + 1;
            String result = number + ". " + taskList[i];
            System.out.println(result);
        }
    }

    public static void main(String[] args) {
        String mickey = "(\\_/)\n" +
                "( •,•)\n" +
                "(\")_(\")";
        System.out.println("RAWR!\n" + mickey);
        String intro = "____________________________________________________________\r\n" +
                " Hello! I'm Mickey\n" +
                " What can I do for you?\n" +
                "____________________________________________________________\r\n";

        String outro = "____________________________________________________________\r\n" +
                " Bye. See you soon!\n" +
                "____________________________________________________________\r\n";


        System.out.println(intro);

        Scanner sc = new Scanner(System.in);
        String userInput;
        String[] tasks = new String[100];
        int count = 0;

        while(true) {
            userInput = sc.nextLine();
             if (userInput.equals("bye")) {
                 System.out.println(outro);
                 break;
             } else if (userInput.equals("list")) {
                 System.out.print("____________________________________________________________\r\n");
                 listTasks(tasks, count);
                 System.out.println("____________________________________________________________\r\n");
             } else {
                 tasks[count] = userInput;
                 count++;
                 System.out.println(addComment(userInput));
            }
        }

    }
}
