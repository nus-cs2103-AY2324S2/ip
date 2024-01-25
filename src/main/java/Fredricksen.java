import java.util.ArrayList;
import java.util.Scanner;
public class Fredricksen {
    public static void greeting(String line) {
        System.out.println(line);
        System.out.println("Hello! I'm Fredricksen");
        System.out.println("What can I do for you?");
        System.out.println(line);
    }
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<>();
        // ArrayList<Boolean> taskType = new ArrayList();
        String line = "____________________________________________________________";
        greeting(line);
        boolean loop = true;
        while (loop) {
            System.out.println("");
            String s = in.nextLine();
            String[] split = s.split(" ");
            String first = split[0].toLowerCase();
            switch (first) {
                case "mark":
                    System.out.println(line);
                    System.out.println("Nice! I've marked this task as done:");
                    Task t1 = list.get(Integer.parseInt(split[1]) - 1);
                    t1.setDone();
                    System.out.println("    " + t1.printTask(t1.getType(), t1.getDone(), t1.getTask()));
                    System.out.println(line);
                    break;
                case "unmark":
                    System.out.println(line);
                    System.out.println("OK, I've marked this task as not done yet:");
                    Task t2 = list.get(Integer.parseInt(split[1]) - 1);
                    t2.setUndone();
                    System.out.println("    " + t2.printTask(t2.getType(), t2.getDone(), t2.getTask()));
                    System.out.println(line);
                    break;
                case "bye":
                    System.out.println(line);
                    System.out.println("Bye. Hope to see you again soon!");
                    System.out.println(line);
                    loop = false;
                    break;
                case "":
                    System.out.println(line);
                    System.out.println("Please specify a task.");
                    System.out.println(line);
                    break;
                case "list":
                    System.out.println(line);
                    System.out.println("Here are the tasks in your list:");
                    if (list.size() > 0) {
                        Task.printList(list);
                    }
                    System.out.println(line);
                    break;
                case "todo":
                case "event":
                case "deadline":
                    System.out.println(line);
                    System.out.println("Got it. I've added this task: ");
                    String res = "";
                    switch(first) {
                        case "todo":
                            res = "T";
                            break;
                        case "event":
                            res = "E";
                            break;
                        case "deadline":
                            res = "D";
                            break;
                    }
                    int startInd1 = s.indexOf("/by");
                    int startInd2 = s.indexOf("/from");
                    Task newTask = null;
                    String ss = s.substring(first.length() + 1);
                    if (startInd2 == -1 && startInd1 == -1) {
                        newTask = new Task(ss, res, false);
                    } else {
                        if (startInd1 != -1) {
                            // dl = deadline
                            String dl = s.substring(first.length() + 1, startInd1) + "(" + s.substring(startInd1 + 1, startInd1 + 3) + ": " + s.substring(startInd1 + 4) + ")";
                            newTask = new Task(dl, res, false);
                        } else {
                            // e = event
                            int startTo = s.indexOf("/to");
                            String e = s.substring(first.length() + 1, startInd2) +
                                    "(" + s.substring(startInd2 + 1, startInd2 + 5) + ": " + s.substring(startInd2 + 6, startTo) + s.substring(startTo + 1, startTo + 3) +
                                    ": " + s.substring(startTo + 4) + ")";
                            newTask = new Task(e, res, false);

                        }
                    }
                    list.add(newTask);
                    String t = newTask.printTask(res, false, newTask.getTask());
                    // String task = "  [" + res + "]" + "[] " + s.substring(first.length() + 1, s.length());
                    System.out.println("    " + t);
                    String single = list.size() == 1 ? "task" : "tasks";
                    System.out.println("Now you have " + list.size() + " " + single + " in the list.");
                    System.out.println(line);
                    break;
            }
        }
        in.close();
    }
}
