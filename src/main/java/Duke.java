import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;
public class Duke {
    public static class Task {
        String action;
        Boolean isDone;



        public Task(String action, Boolean isDone) {
            this.action = action;
            this.isDone = isDone;
        }
        @Override
        public String toString() {
            if (isDone) {
                return " [X] " + action;
            } else {
                return " [ ] " + action;
            }
        }
        public void mark() {
            isDone = true;
        }
        public void unmark() {
            isDone = false;
        }
    }
    public static class Event extends Task {
        String from;
        String to;
        public Event(String input, boolean isDone, String from, String to) {
            super(input, isDone);
            this.from = from;
            this.to = to;
        }
        @Override
        public String toString() {
            String s = super.toString();
            return "[E]" + s + "(from " + from + " to " + to + " ).";
        }
    }
    public static class Todo extends Task {
        public Todo(String input, boolean isDone) {
            super(input, isDone);
        }
        @Override
        public String toString() {
            String s = super.toString();
            return "[T]" + s;
        }
    }
    public static class Deadline extends Task {
        String by;
        public Deadline(String input, boolean isDone, String by) {
            super(input, isDone);
            this.by = by;
        }
        @Override
        public String toString() {
            String s = super.toString();
            return "[D]" + s + " (by: " + by + ")";
        }

    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayList<Task> List = new ArrayList<>();
        int length = 0;
        System.out.println("Hello! I'm Dukey.");
	    System.out.println("What can I do for you?");

        String input ;
        String mark = "mark (\\d+)";
        String unmark = "unmark (\\d+)";
        String delete = "delete (\\d+)";
        String todo = "todo";
        String deadline = "deadline";
        String event =  "event";
        String by = "/by";
        String from = "/from";
        String to = "/to";
        Pattern pMark = Pattern.compile(mark);
        Pattern pUnmark = Pattern.compile(unmark);
        Pattern pTodo = Pattern.compile(todo);
        Pattern pDeadline = Pattern.compile(deadline);
        Pattern pEvent = Pattern.compile(event);
        Pattern pBy = Pattern.compile(by);
        Pattern pFrom = Pattern.compile(from);
        Pattern pTo = Pattern.compile(to);
        Pattern pDelete = Pattern.compile(delete);

        while (true) {
            input = scanner.nextLine();
            Matcher mMark = pMark.matcher(input);
            Matcher mUnmark = pUnmark.matcher(input);
            Matcher mTodo = pTodo.matcher(input);
            Matcher mDeadline = pDeadline.matcher(input);
            Matcher mEvent = pEvent.matcher(input);
            Matcher mBy = pBy.matcher(input);
            Matcher mFrom = pFrom.matcher(input);
            Matcher mTo = pTo.matcher(input);
            Matcher mDelete = pDelete.matcher(input);
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (input.equals("list")) {
                if (length == 0) {
                    System.out.println("You have no tasks in your list!");
                } else {
                    System.out.println("Here are your tasks in your list:");
                    for (int x = 0; x < length; x++) {
                        Task item = List.get(x);
                        int numeric = x + 1;
                        System.out.println(numeric + "." + item.toString());
                    }
                }
            } else if (mDelete.find()) {
                String captured = mDelete.group(1);
                int number = Integer.parseInt(captured);
                if (number > 0 && number < length + 1 ) {
                    Task t = List.get(number - 1);
                    List.remove(t);
                    length = length - 1;
                    System.out.println("OK! I have deleted this task:");
                    System.out.println(t);
                } else {
                    System.out.println("Please input a valid number.");
                }

            } else if (mUnmark.find()) {
                String captured = mUnmark.group(1);
                int number = Integer.parseInt(captured);
                Task t;
                if (number > 0 && number < length + 1) {
                    t = List.get(number - 1);
                    t.unmark();
                    System.out.println("Oh no! I have marked this as not done:");
                    System.out.println(t);
                } else {
                    System.out.println("Please input a valid number.");
                }

            } else if (mMark.find()) {
                String captured = mMark.group(1);
                int number = Integer.parseInt(captured);
                Task t;
                if (number > 0 && number < length + 1) {
                    t = List.get(number -1);
                    t.mark();
                    System.out.println("Nice! I have marked this as done:");
                    System.out.println(t);
                } else {
                    System.out.println("Please input a valid number.");
                }
            } else if (mTodo.find()) {
                String newInput = input.replace(todo, "");
                Todo n = new Todo(newInput, false);
                if (newInput.trim().equals("")) {
                    System.out.println("Task cannot be empty!");
                } else {

                    List.add(n);
                    length = length + 1;
                    System.out.println("OK, I have added this task :");
                    System.out.println(n);
                    System.out.println("You now have " + length + " items in the list.");
                }
            } else if (mEvent.find()) {
                if (mFrom.find() && mTo.find()) {
                    int startIndex = input.indexOf(from);
                    int startIndexTo = input.indexOf(to);
                    String subFrom = input.substring(startIndex + from.length(), startIndexTo);
                    String subTo = input.substring(startIndexTo + to.length());
                    String newInput = input.substring(input.indexOf(event) + event.length(), startIndex);
                    if (newInput.trim().equals("")) {
                        System.out.println("Task cannot be empty!");
                    } else {
                        Event n = new Event(newInput, false, subFrom, subTo);
                        List.add(n);
                        length = length + 1;
                        System.out.println("OK, I have added this task :");
                        System.out.println(n);
                        System.out.println("You now have " + length + " items in the list.");
                    }
                } else {
                    System.out.println("pls input your start and end of the event.");
                }

            } else if (mDeadline.find()) {
                if (mBy.find()) {
                    int finalIndex = input.indexOf(by) + by.length();
                    String dL = input.substring(finalIndex);
                    String newInput = input.substring(input.indexOf(deadline) + deadline.length(), input.indexOf(by));
                    if (newInput.trim().equals("")) {
                        System.out.println("Task cannot be empty!");
                    } else {
                        Deadline n = new Deadline(newInput, false, dL);
                        List.add(n);
                        length = length + 1;
                        System.out.println("OK, I have added this task :");
                        System.out.println(n);
                        System.out.println("You now have " + length + " items in the list.");
                    }
                } else {
                    System.out.println("please include a deadline");
                }

            } else {
                System.out.println("Sorry, no idea what u talking about lulz");
            }

        }

    }
}
