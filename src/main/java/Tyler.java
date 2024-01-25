import java.util.*;

public class Tyler {
    private static Task[] taskList = new Task[100];
    private static int curr = 0;

    public static void main(String[] args) throws EmptyNameException, UndefinedActionException, MarkNothingException {
        Tyler.greet();
        Scanner sc = new Scanner(System.in);

          while (true) {
              try {
                  System.out.println("    --------------------------------------------------");
                  String input = sc.nextLine();
                  String[] arr = input.split(" ");
                  if (arr[0].equals("bye")) {
                      System.out.println("    Bye. Hope to see you again");
                      return ;
                  } else if (arr[0].equals("list")) {
                      Tyler.list();

                  } else if (arr[0].equals("mark")) {
                      if (arr.length < 2) {
                          throw new MarkNothingException();
                      }
                      for (int i = 1; i < arr.length; i++) {
                          int taskNumber = Integer.parseInt(arr[i]);
                          taskList[taskNumber - 1].mark();
                      }

                  } else if (arr[0].equals("unmark")) {
                      if (arr.length < 2) {
                          throw new MarkNothingException();
                      }
                      for (int i = 1; i < arr.length; i++) {
                          int taskNumber = Integer.parseInt(arr[i]);
                          taskList[taskNumber - 1].unmark();
                      }

                  } else if (arr[0].equals("todo")) {
                      if (arr.length < 2) {
                          throw new EmptyNameException();
                      }
                      String[] rest = input.split(" ", 2);
                      Task newTask = new Todo(rest[1]);
                      taskList[curr] = newTask;
                      System.out.println("    Got it! I've added this task:");
                      System.out.println("      " + newTask.toString());
                      curr++;
                      System.out.println("    Now you have " + curr + " tasks in the list");

                  } else if (arr[0].equals("deadline")) {
                      if (arr.length < 2) {
                          throw new EmptyNameException();
                      }
                      String[] rest1 = input.split(" ", 2);
                      String[] rest2 = rest1[1].split(" /by ");
                      //String end = sc.nextLine();
                      Task newTask = new Deadline(rest2[0], rest2[1]);
                      taskList[curr] = newTask;
                      System.out.println("    Got it! I've added this task:");
                      System.out.println("      " + newTask.toString());
                      curr++;
                      System.out.println("    Now you have " + curr + " tasks in the list");

                  } else if (arr[0].equals("event")) {
                      if (arr.length < 2) {
                          throw new EmptyNameException();
                      }
                      String[] rest1 = input.split(" ", 2);
                      String[] rest2 = rest1[1].split(" /from ");
                      String[] rest3 = rest2[1].split(" /to ");
                      //String start = sc.nextLine();
                      //String end = sc.nextLine();
                      Task newTask = new Event(rest2[0], rest3[0], rest3[1]);
                      taskList[curr] = newTask;
                      System.out.println("    Got it! I've added this task:");
                      System.out.println("      " + newTask.toString());
                      curr++;
                      System.out.println("    Now you have " + curr + " tasks in the list");
                  } else {
                      throw new UndefinedActionException();
                  }
                  System.out.println("    --------------------------------------------------");
              } catch (MarkNothingException e) {
                  System.out.println("    " + e.getMessage());
              } catch (UndefinedActionException e) {
                  System.out.println("    " + e.getMessage());
              } catch (EmptyNameException e) {
                  System.out.println("    " + e.getMessage());
              }
              continue;
          }
    }

    public static void list() {
        for(int i = 1; i < 100; i++) {
            if (taskList[i - 1] == null) {
                break;
            }
            String task = taskList[i - 1].toString();
            System.out.println("    " + i + ". " + task);
        }
    }

    public static void greet() {
        System.out.println("    Hello from Tyler");
        System.out.println("    What can I do for you?");
        System.out.println("    list, todo, deadline, event, mark, unmark, bye");
        System.out.println("    --------------------------------------------------");
    }
}
