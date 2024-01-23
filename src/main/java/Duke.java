import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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

        Task[] List = new Task[100];
        int length = 0;
        System.out.println("Hello! I'm Dukey.");
	    System.out.println("What can I do for you?");

        String input ;
        String mark = "mark (\\d+)";
        String unmark = "unmark (\\d+)";
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
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (input.equals("list")) {
                if (length == 0) {
                    System.out.println("You have no tasks in your list!");
                } else {
                    System.out.println("Here are your tasks in your list:");
                    for (int x = 0; x < length; x++) {
                        Task item = List[x];
                        int numeric = x + 1;
                        System.out.println(numeric + "." + item.toString());
                    }
                }
            } else if (mUnmark.find()) {
                int len = input.length();
                char last = input.charAt(len - 1);
                if (Character.isDigit(last)) {
                    int num = Character.getNumericValue(last);
                    Task t = List[num - 1];
                    if (t == null) {
                        System.out.println("You have no tasks at number " + num);
                    } else {
                        t.unmark();
                        System.out.println("Oh no! I have marked this as not done:");
                        System.out.println(t);
                    }
                }
            } else if (mMark.find()) {
                int len = input.length();
                char last = input.charAt(len - 1);
                if (Character.isDigit(last)) {
                    int num = Character.getNumericValue(last);
                    Task t = List[num - 1];
                    if (t == null) {
                        System.out.println("You have no tasks at number " + num);
                    } else {
                        t.mark();
                        System.out.println("Noice! I have marked this as done:");
                        System.out.println(t);
                    }
                }
            } else if (mTodo.find()) {
                String newInput = input.replace(todo, "");
                Todo n = new Todo(newInput, false);
                List[length] = n;
                length = length + 1;
                System.out.println("OK, I have added this task :");
                System.out.println(n);
                System.out.println("You now have " + length + " items in the list.");
            } else if (mEvent.find()) {
                if (mFrom.find() && mTo.find()) {
                    int startIndex = input.indexOf(from);
                    int startIndexTo = input.indexOf(to);
                    String subFrom = input.substring(startIndex + from.length(), startIndexTo);
                    String subTo = input.substring(startIndexTo + to.length());
                    String newInput = input.substring(input.indexOf(event) + event.length(), startIndex);

                    Event n = new Event(newInput, false, subFrom ,subTo);
                    List[length] = n;
                    length = length + 1;
                    System.out.println("OK, I have added this task :");
                    System.out.println(n);
                    System.out.println("You now have " + length + " items in the list.");

                } else {
                    System.out.println("pls input your start and end of the event.");
                }

            } else if (mDeadline.find()) {
                if (mBy.find()) {
                    int finalIndex = input.indexOf(by) + by.length();
                    String dL = input.substring(finalIndex);
                    String newInput = input.substring(input.indexOf(deadline) + deadline.length(), input.indexOf(by));
                    Deadline n = new Deadline(newInput, false, dL);
                    List[length] = n;
                    length = length + 1;
                    System.out.println("OK, I have added this task :");
                    System.out.println(n);
                    System.out.println("You now have " + length + " items in the list.");
                } else {
                    System.out.println("pls include a deadline");
                }

            } else {
                System.out.println("Sorry, no idea what u talking about lulz");
            }

        }

    }
}
