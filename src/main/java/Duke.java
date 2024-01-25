import java.io.IOException;
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
                                + tasklist.get(i).marked() + " "
                                + tasklist.get(i).getTask() + tasklist.get(i).getDetails());
                    }
// can i have a function that does mark/unmark?
                } else if(echo.getTask().startsWith("mark")) {
                    String str = echo.getTask().substring(5);
                    int number = Integer.parseInt(str) - 1;
                    String name = tasklist.get(number).getTask();
                    tasklist.get(number).mark();
                    System.out.println(echo.mark(number, name, tasklist.get(number)));

                } else if (echo.getTask().startsWith("unmark")) {
                    String str = echo.getTask().substring(7);
                    int number = Integer.parseInt(str) - 1;
                    String name = tasklist.get(number).getTask();
                    tasklist.get(number).unmark();
                    System.out.println(echo.unmark(number, name, tasklist.get(number)));
                } else {
//                    if (echo.getTask().length() > 4) {
                        if (echo.getTask().startsWith("todo")) {
//                        try {
                            String str = echo.getTask().substring(5);
//                            if (str == "") {
//                                throw new StringIndexOutOfBoundsException();
//                            } else {
                            Todo todo = new Todo(str);
                            tasklist.add(todo);
                            System.out.println(todo.addTodo());
//                            }
////                            }
//                        } catch (StringIndexOutOfBoundsException e) {
//                            System.out.println("ENTER A TASK TODO!");
//                        }

//                        try {
//                            String str = echo.getTask();
//                            if (!str.isEmpty() && str.length() >= 5) {
//                                String substr = str.substring(5);
//                                Todo todo = new Todo(substr);
//                                tasklist.add(todo);
//                                System.out.println(todo.addTodo());
//                            } else {
//                                throw new IOException("ENTER A TASK TODO!");
//                            }
//                        } catch (IOException e) {
//                            // Handle the IOException
//                            e.printStackTrace(); // or log the exception
//                        }


                        } else if (echo.getTask().startsWith("deadline")) {
                            String str = echo.getTask().substring(9);
                            String[] arr = str.split("/");
                            String task = arr[0];
                            String ddl = arr[1].substring(3);
                            Deadline deadline = new Deadline(ddl, task);
                            tasklist.add(deadline);
                            System.out.println(deadline.addDeadline());

                        } else if (echo.getTask().startsWith("event")) {
                            String str = echo.getTask().substring(6);
                            String[] arr = str.split("/");
                            String task = arr[0];
                            String start = arr[1].substring(5);
                            String end = arr[2].substring(3);
                            Event event = new Event(start, end, task);
                            tasklist.add(event);
                            System.out.println(event.addEvent());
                        } else if (echo.getTask().startsWith("delete")) {
                            String str = echo.getTask().substring(7);
                            int number = Integer.parseInt(str) - 1;
                            Task name = tasklist.get(number);
                            tasklist.remove(number);
                            System.out.println(echo.delete(name));
                        }
                        System.out.println("Now you have " + tasklist.size() + " tasks in the list.");
//                    }
                }
                echo = new Task (user.nextLine());
        }


        System.out.println("    byeee ttyl ok!");

    }
}
