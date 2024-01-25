import java.io.IOException;
import java.text.NumberFormat;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {

        Scanner user = new Scanner(System.in);
        System.out.println("Hi babyyy! It's your EUEU!!");
        System.out.println("What are you doing today??");
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
                } else if (echo.getTask().startsWith("mark")) {
                    try {
                        String str = echo.getTask().substring(5);
                        int number = Integer.parseInt(str) - 1;
                        String name = tasklist.get(number).getTask();
                        tasklist.get(number).mark();
                        System.out.println(echo.mark(number, name, tasklist.get(number)));
                    } catch (StringIndexOutOfBoundsException e) {
                        System.out.println("Enter task to mark done: e.g. mark 1");
                    } catch (NumberFormatException e) {
                        System.out.println("Enter task to mark done: e.g. mark 1");
                    }
                } else if (echo.getTask().startsWith("unmark")) {
                    try {
                        String str = echo.getTask().substring(7);
                        int number = Integer.parseInt(str) - 1;
                        String name = tasklist.get(number).getTask();
                        tasklist.get(number).unmark();
                        System.out.println(echo.unmark(number, name, tasklist.get(number)));
                    } catch (StringIndexOutOfBoundsException e) {
                        System.out.println("Enter task to unmark: e.g. unmark 1");
                    } catch (NumberFormatException e) {
                        System.out.println("Enter task to mark done: e.g. mark 1");
                    }
                } else if (echo.getTask().startsWith("delete")) {
                    try {
                        String str = echo.getTask().substring(7);
                        int number = Integer.parseInt(str) - 1;
                        Task name = tasklist.get(number);
                        tasklist.remove(number);
                        System.out.println(echo.delete(name));
                        System.out.println("Now you have " + tasklist.size() + " tasks in the list.");
                    } catch (StringIndexOutOfBoundsException e) {
                        System.out.println("Enter task to delete: e.g. delete 1");
                    } catch (NumberFormatException e) {
                        System.out.println("Enter task to delete: e.g. delete 1");
                    }

                    } else {
                        try {

                            if (echo.getTask().startsWith("todo")) {
                                String str = echo.getTask().substring(5);
                                Todo todo = new Todo(str);
                                tasklist.add(todo);
                                System.out.println(todo.addTodo());
                                System.out.println("Now you have " + tasklist.size() + " tasks in the list.");

                            } else if (echo.getTask().startsWith("deadline")) {
                                try {
                                    String str = echo.getTask().substring(9);
                                    String[] arr = str.split("/");
                                    String task = arr[0];
                                    String ddl = arr[1].substring(3);
                                    Deadline deadline = new Deadline(ddl, task);
                                    tasklist.add(deadline);
                                    System.out.println(deadline.addDeadline());
                                    System.out.println("Now you have " + tasklist.size() + " tasks in the list.");
                                } catch (ArrayIndexOutOfBoundsException e) {
                                    System.out.println("Let me know your deadlines babe: e.g. deadline <deadline> /by <ddl>");
                                }

                            } else if (echo.getTask().startsWith("event")) {
                                try {
                                    String str = echo.getTask().substring(6);
                                    String[] arr = str.split("/");
                                    String task = arr[0];
                                    String start = arr[1].substring(5);
                                    String end = arr[2].substring(3);
                                    Event event = new Event(start, end, task);
                                    tasklist.add(event);
                                    System.out.println(event.addEvent());
                                    System.out.println("Now you have " + tasklist.size() + " tasks in the list.");
                                } catch(ArrayIndexOutOfBoundsException e) {
                                    System.out.println("Let me know when this event is bb: e.g. event <event> /from <when>/to <when>");
                                }
                            } else {
                                System.out.println("Baby, what are you saying? Tell me what your TODOs, DEADLINEs and EVENTs are!");
                            }
                        } catch (StringIndexOutOfBoundsException e){
                            System.out.println("ENTER INSTRUCTION");
                        }

                }
                try {
                    echo = new Task(user.nextLine());
                } catch (NoSuchElementException e) {
                    System.out.println("Say something I'm giving up on you ");
                }

        }


        System.out.println("    byeee love uu ttyl ok!");

    }
}
