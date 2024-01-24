import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {

        Scanner user = new Scanner(System.in);
        System.out.println("Hello! I'm EUEU!!");
        System.out.println("how was your day??");
        Task echo = new Task (user.nextLine());
        ArrayList<Task> tasklist = new ArrayList<Task>();
        while (!echo.getTask().equals("bye")) {
                if (echo.getTask().equals("list")) {
                    System.out.println("    Here are the tasks in your list:");
                    for (int i = 0; i < tasklist.size(); i++) {
                        int j = i + 1;
                        System.out.println("        " + j + ". " + tasklist.get(i).marked()
                                + tasklist.get(i).getTask());
                    }

                } else if(echo.getTask().substring(0,4).equals("mark")) {
                    String str = echo.getTask().substring(5);
                    int number = Integer.parseInt(str) - 1;
//                    System.out.println(number);
                    tasklist.get(number).mark();
//                    System.out.println(tasklist.get(number).isDone());
                    System.out.println("    Nice! I've marked this task as done:");
                    System.out.println("        " + tasklist.get(number).marked()
                            + tasklist.get(number).getTask());
                } else if (echo.getTask().substring(0,6).equals("unmark")) {
                    String str = echo.getTask().substring(7);
                    int number = Integer.parseInt(str) - 1;
//                    System.out.println(number);
                    tasklist.get(number).unmark();
                    System.out.println("    Ok, I've marked this task as not done yet:");
                    System.out.println("        " + tasklist.get(number).marked()
                            + tasklist.get(number).getTask());
                } else {
//                    System.out.println(echo.getTask().substring(0,4));
                    System.out.println("    added: " + echo.getTask());
                    tasklist.add(echo);
                }
            echo = new Task (user.nextLine());
        }

        System.out.println("    byeee ttyl ok!");

    }
}
