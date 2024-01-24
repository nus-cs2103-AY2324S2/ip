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
                        System.out.println("        " + j + ". " + tasklist.get(i).getCat()
                                + tasklist.get(i).marked()
                                + tasklist.get(i).getTask() + tasklist.get(i).getDetails());
                    }
// can i have a function that does mark/unmark?
                } else if(echo.getTask().substring(0,4).equals("mark")) {
                    String str = echo.getTask().substring(5);
                    int number = Integer.parseInt(str) - 1;
                    String name = tasklist.get(number).getTask();
                    tasklist.get(number).mark();
                    System.out.println(echo.mark(number, name));

                } else if (echo.getTask().substring(0,6).equals("unmark")) {
                    String str = echo.getTask().substring(7);
                    int number = Integer.parseInt(str) - 1;
                    String name = tasklist.get(number).getTask();
                    tasklist.get(number).unmark();
                    System.out.println(echo.unmark(number, name));
                } else {
                    if (echo.getTask().substring(0,4).equals("todo")) {
                        String str = echo.getTask().substring(5);
                        Todo todo = new Todo(str);
                        tasklist.add(todo);
                        System.out.println(todo.addTodo());
                    } else if (echo.getTask().substring(0,8).equals("deadline")) {
                        String str = echo.getTask().substring(9);
                        String[] arr = str.split("/");
                        String task = arr[0];
                        String ddl = arr[1].substring(3);
                        Deadline deadline = new Deadline(ddl, task);
                        tasklist.add(deadline);
                        System.out.println(deadline.addDeadline());

                    } else if (echo.getTask().substring(0,5).equals("event")) {
                        String str = echo.getTask().substring(6);
                        String[] arr = str.split("/");
                        String task = arr[0];
                        String start = arr[1].substring(5);
                        String end = arr[2].substring(3);
                        Event event = new Event(start, end, task);
                        tasklist.add(event);
                        System.out.println(event.addEvent());

                    }
                    System.out.println("Now you have " + tasklist.size() + " tasks in the list.");
                }
            echo = new Task (user.nextLine());
        }

        System.out.println("    byeee ttyl ok!");

    }
}
