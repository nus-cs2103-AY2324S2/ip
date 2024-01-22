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
            switch (input) {
                case "bye":
                    running = false;
                    System.out.println("Bye... Hope to see you again...\n" + dotted);
                    break;
                case "list":
                    int size = tasks.size();
                    for (int i = 0; i < size; i++) {
                        int curr = i + 1;
                        String result = curr + ". " + tasks.get(i).toString() +"...";
                        System.out.println(result);
                    }
                    System.out.println(dotted);
                    break;
                default:
                    Task task = new Task(input);
                    tasks.add(task);
                    System.out.println("added: " + task + "...");
                    System.out.println(dotted);
            }
        }
    }
}
