import java.util.*;
public class Duke {
    public static void main(String[] args) {

        String line = "_____________________________________________";
        String greeting = "Hello! I'm Donald.\nWhat can I do for you?\n";
        System.out.println(line);
        System.out.println(greeting + line + "\n");

        Scanner sc = new Scanner(System.in);
        String input;
        List<Task> list = new ArrayList<Task>();

        while (sc.hasNextLine()) {
            input = sc.nextLine();
            System.out.println(line);
            if (input.equals("bye")) {
                break;
            }

            String[] split = input.split(" ");
            String command = split[0];
            Integer index;
            Task t;
            switch(command) {
                case "list":
                    System.out.println("Here are the tasks in your list:");
                    index = 1;
                    for (Task s : list) {
                        System.out.println(index + "." + s);
                        index++;
                    }
                    break;
                case "mark":
                    System.out.println("Nice! I've marked this task as done:");
                    index = Integer.parseInt(split[1]) - 1;
                    t = list.get(index);
                    t.mark();
                    System.out.println(t.toString());
                    list.set(index, t);
                    break;
                case "unmark":
                    System.out.println("OK, I've marked this task as not done yet:");
                    index = Integer.parseInt(split[1]) - 1;
                    t = list.get(index);
                    t.unmark();
                    System.out.println(t.toString());
                    list.set(index, t);
                    break;
                default:
                    t = new Task(input);
                    list.add(t);
                    System.out.println("added: " + input);
            }
            System.out.println(line + "\n");
        }

        String closing = "Bye. Hope to see you again soon!\n";
        System.out.println(closing + line);
    }
}
