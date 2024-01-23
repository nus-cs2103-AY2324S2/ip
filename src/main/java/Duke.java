import java.util.Scanner;
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
                return "[X] " + action;
            } else {
                return "[ ] " + action;
            }
        }
        public void mark() {
            isDone = true;
        }
        public void unmark() {
            isDone = false;
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Task[] List = new Task[100];
        int length = 0;
        System.out.println("Hello! I'm Dukey.");
	    System.out.println("What can I do for you?");

        String input ;

        while (true) {
            input = scanner.nextLine();
            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (input.equals("list")) {
                if (length == 0) {
                    System.out.println("You have no tasks in your list!");
                    continue;
                } else {
                    System.out.println("Here are your tasks in your list:");
                    for (int x = 0; x < length; x++) {
                        Task item = List[x];
                        int numeric = x + 1;
                        System.out.println(numeric + "." + item.toString());
                    }
                    continue;
                }
            } else if (input.contains("unmark ")) {
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
                    continue;
                }
            } else if (input.contains("mark ")) {
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
                    continue;
                }
            }
            Task n = new Task(input, false);
            List[length] = n;
            length = length + 1;
            System.out.println("added: " + input);
        }

    }
}
