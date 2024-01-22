import java.util.*;
public class Riz {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();
        String dotted = "-----------------------------------";
        //greetings
        String greetings = "Hello... I'm Riz...\n"
                + "What can I help you with today?\n"
                + dotted;
        System.out.println(greetings);
        boolean running = true;

        while (running) {
            String input = scanner.nextLine();
            String[] token = input.split(" ", 2);
            if (token[0].equals("bye")) {
                running = false;
                System.out.println("Bye... Hope to see you again...\n" + dotted);
            } else if (token[0].equals("mark")) {
                int curr = Integer.parseInt(token[1]) - 1;
                Task task = tasks.get(curr);
                task.mark();
                System.out.println("Awesome..., I've marked this task as completed...");
                System.out.println(task);
                System.out.println(dotted);
            } else if (token[0].equals("unmark")) {
                int curr = Integer.parseInt(token[1]) - 1;
                Task task = tasks.get(curr);
                task.unmark();
                System.out.println("Oops... Guess it's not done yet...");
                System.out.println(task);
                System.out.println(dotted);
            } else if (token[0].equals("list")) {
                int size = tasks.size();
                System.out.println("Here are the items in your To-Do List...");
                for (int i = 0; i < size; i++) {
                    int curr = i + 1;
                    String result = curr + ". " + tasks.get(i).toString() + "...";
                    System.out.println(result);
                }
                System.out.println(dotted);
            } else {
                Task task = new Task(input);
                tasks.add(task);
                System.out.println("added: " + task.add() + "...");
                System.out.println(dotted);
            }
        }
    }
}
