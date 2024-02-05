import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.ArrayList;

public class Tyler {
    private static ArrayList<Task> taskList = new ArrayList<>();
    private static int curr = 0;
    private static final String FILE_PATH = Paths.get(".",  "data", "Tyler.txt").toString();

    public static void main(String[] args) throws IOException {
        Tyler.loadTask();
        Tyler.greet();
        Scanner sc = new Scanner(System.in);

          while (true) {
              try {
                  System.out.println("    --------------------------------------------------");
                  String input = sc.nextLine();
                  String[] arr = input.split(" ");
                  if (arr[0].equals("bye")) {
                      Tyler.saveTask();
                      System.out.println("    Bye. Hope to see you again");
                      return ;
                  } else if (arr[0].equals("list")) {
                      Tyler.list();

                  } else if (arr[0].equals("mark")) {
                      if (arr.length < 2) {
                          throw new MarkNothingException();
                      }
                      int taskNumber = Integer.parseInt(arr[1]);
                      taskList.get(taskNumber - 1).mark();
                  } else if (arr[0].equals("unmark")) {
                      if (arr.length < 2) {
                          throw new MarkNothingException();
                      }
                      int taskNumber = Integer.parseInt(arr[1]);
                      taskList.get(taskNumber - 1).unmark();
                  } else if (arr[0].equals("todo")) {
                      if (arr.length < 2) {
                          throw new EmptyNameException();
                      }
                      String[] rest = input.split(" ", 2);
                      Task newTask = new Todo(rest[1]);
                      taskList.add(newTask);
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
                      taskList.add(newTask);
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
                      Task newTask = new Event(rest2[0], rest3[0], rest3[1]);
                      taskList.add(newTask);
                      System.out.println("    Got it! I've added this task:");
                      System.out.println("      " + newTask.toString());
                      curr++;
                      System.out.println("    Now you have " + curr + " tasks in the list");
                  } else if (arr[0].equals("delete")) {
                      if (arr.length < 2) {
                          throw new MarkNothingException();
                      }
                      int taskNumber = Integer.parseInt(arr[1]);
                      Task removeTask = taskList.get(taskNumber - 1);
                      taskList.remove(taskNumber - 1);
                      curr--;
                      System.out.println("    Noted! I've deleted this task:");
                      System.out.println("      " + removeTask.toString());
                      System.out.println("    Now you have " + curr + " tasks in the list");


                  } else {
                      throw new UndefinedActionException();
                  }
                  System.out.println("    --------------------------------------------------");
              } catch (MarkNothingException | UndefinedActionException | EmptyNameException e) {
                  System.out.println("    " + e.getMessage());
              }
          }
    }

    public static void list() {
        for(int i = 1; i < taskList.size() + 1; i++) {
            if (taskList.get(i - 1) == null) {
                break;
            }
            String task = taskList.get(i - 1).toString();
            System.out.println("    " + i + ". " + task);
        }
    }

    public static void greet() {
        System.out.println("    Hello from Tyler");
        System.out.println("    What can I do for you?");
        System.out.println("    list, todo, deadline, event, mark, unmark, bye");
        System.out.println("    --------------------------------------------------");
    }

    public static void loadTask() throws IOException {
        try {
            File file = new File(FILE_PATH);
            File parentD = file.getParentFile();
            if (!parentD.exists()) {
                parentD.mkdirs();
            }
            Scanner load = new Scanner(file);
            while (load.hasNextLine()) {
                String line = load.nextLine();
                String[] input = line.split(" \\| ");
                String type = input[0];
                boolean isDone = input[1].equals("1");
                if (type.equals("T")) {
                    taskList.add(new Todo(input[2], isDone));
                } else if (type.equals("D")) {
                    taskList.add(new Deadline(input[2], input[3], isDone));
                } else if (type.equals("E")) {
                    taskList.add(new Event(input[2], input[3], input[4], isDone));
                }
                curr++;
            }
        } catch (FileNotFoundException e) {
            File file = new File(FILE_PATH);
            file.createNewFile();
        }
    }

    public static String toFileString(Task task) {
        String name = task.name;
        boolean isDone = task.isDone;
        int isDoned = isDone ? 1 : 0;
        if (task instanceof Todo) {
            return "T | " + isDoned + " | " + name;
        } else if (task instanceof Deadline) {
            Deadline dtask = (Deadline) task;
            return "D | " + isDoned + " | " + name + " | " + dtask.end;
        } else {
            Event etask = (Event) task;
            return "E | " + isDoned + " | " + name + " | " + etask.start + " | " + etask.end;
        }
    }

    public static void saveTask() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH));
            for (Task task : taskList) {
                bw.write(Tyler.toFileString(task));
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
