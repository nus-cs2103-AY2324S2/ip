import java.util.Scanner;
import java.util.*;
public class GPT {
    public static void main(String[] args) {
          abstract class Task {
            boolean done;
            String taskName;

            public Task(String taskName) {
                this.taskName = taskName;
                this.done = false;
            }

            public void mark() {
                this.done = true;
            }

            public void unmark() {
                this.done = false;
            }

            public abstract String getType();

            @Override
            public String toString() {
                return String.format("[%s][%s] %s", getType(), done ? "X" : " ", taskName);
            }
        }

          class ToDoTask extends Task {
            public ToDoTask(String taskName) {
                super(taskName);
            }

            @Override
            public String getType() {
                return "T";
            }
        }

          class DeadlineTask extends Task {
            String date;

            public DeadlineTask(String taskName, String date) {
                super(taskName);
                this.date = date;
            }

            @Override
            public String getType() {
                return "D";
            }

            @Override
            public String toString() {
                return String.format("[%s][%s] %s (by: %s)", getType(), done ? "X" : " ", taskName, date);
            }
        }

          class EventTask extends Task {
            String startDate;
            String endDate;

            public EventTask(String taskName, String startDate, String endDate) {
                super(taskName);
                this.startDate = startDate;
                this.endDate = endDate;
            }

            @Override
            public String getType() {
                return "E";
            }

            @Override
            public String toString() {
                return String.format("[%s][%s] %s (from: %s to: %s)", getType(), done ? "X" : " ", taskName, startDate, endDate);
            }
        }

        Scanner scn = new Scanner(System.in);
        String name = " GPT";
        String secLine = "What can I do for you?\n\n";

        System.out.println("Hello! I'm" + name);
        System.out.println(secLine);
        String s = scn.nextLine();
        ArrayList<Task> tl = new ArrayList<>();
//        ArrayList<String> sl = new ArrayList<>();
//        ArrayList<Boolean> done = new ArrayList<>();
        while (!s.equals("bye")) {
            if (s.equals("list")) {
                for (int i = 1; i <= tl.size(); i++) {
                    System.out.println(i +  ". " + tl.get(i - 1).toString());
                }

            } else if (s.startsWith("todo")) {
                String todoName = s.substring(5).trim();
                System.out.println("Got it. I've added this task:");
                Task todoTask = new ToDoTask(todoName);
                tl.add(todoTask);
                System.out.println("  " + todoTask.toString());
                System.out.println("Now you have " + tl.size() + " tasks in the list.");
            } else if (s.startsWith("deadline")) {
                String[] splitInput = s.split("/by");
                String deadlineName = splitInput[0].substring(9).trim();
                String deadlineDate = splitInput[1].trim();
                System.out.println("Got it. I've added this task:");
                Task deadlineTask = new DeadlineTask(deadlineName, deadlineDate);
                tl.add(deadlineTask);
                System.out.println("  " + deadlineTask.toString());
                System.out.println("Now you have " + tl.size() + " tasks in the list.");
            }else if (s.startsWith("event")) {
                String[] splitInput = s.split("/from|/to");
                String eventName = splitInput[0].substring(6).trim();
                String eventStartDate = splitInput[1].trim();
                String eventEndDate = splitInput[2].trim();
                System.out.println("Got it. I've added this task:");
                Task eventTask = new EventTask(eventName, eventStartDate, eventEndDate);
                tl.add(eventTask);
                System.out.println("  " + eventTask.toString());
                System.out.println("Now you have " + tl.size() + " tasks in the list.");
            }else if (s.startsWith("unmark")) {
                String[] splitInput = s.split("\\s+");
                if (splitInput[0].equals("unmark") && Integer.valueOf(splitInput[1]) <= tl.size()) {
                    tl.get(Integer.valueOf(splitInput[1]) - 1).unmark();
                }
            } else if (s.startsWith("mark")) {
                String[] splitInput = s.split("\\s+");
                if (splitInput[0].equals("mark") && Integer.valueOf(splitInput[1]) <= tl.size()) {
                    tl.get(Integer.valueOf(splitInput[1]) - 1).mark();
                }
            } else {
                System.out.println("Sorry, I don't understand that command.");
            }
            s = scn.nextLine();


            }


            System.out.println("ByeBye. Hope to see you soon");
        }
    }

