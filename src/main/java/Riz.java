import java.util.*;
public class Riz {
    public static void main(String[] args) throws Exception {
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
            try {
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
                    if (token[0].equals("todo")) {
                        if (token.length != 2) {
                            throw new RizException("Indecisive aren't we...");
                        } else {
                            Task task = new ToDo(token[1]);
                            tasks.add(task);
                            System.out.println("added: " + task + "...");
                        }
                    } else if (token[0].equals("deadline")) {
                        String[] details = token[1].split(" /by ");
                        Task task = new Deadline(details[0], details[1]);
                        tasks.add(task);
                        System.out.println("added: " + task + "...");
                    } else if (token[0].equals("event")) {
                        String[] details = token[1].split(" /from |\\ /to ");
                        Task task = new Event(details[0], details[1], details[2]);
                        tasks.add(task);
                        System.out.println("added: " + task + "...");
                    } else {
                        throw new RizException("Are you speaking Yapanese?...");
                    }
                    int size = tasks.size();
                    System.out.println("You currently have " + size + " things to do...");
                    System.out.println(dotted);
                }
            } catch (RizException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}