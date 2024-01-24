import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
public class Duke {
    private static List<String> tasks = new ArrayList<>();
    private static void addTask(String task) {
        tasks.add(task);
    }
    private static String printTasks() {
        StringBuilder sb = new StringBuilder();
        if (!tasks.isEmpty()) {
            sb.append(1).append(". ").append(tasks.get(0));
        }
        for (int i = 1; i < tasks.size(); i++) {
            sb.append("\n").append("\t").append(i + 1)
                    .append(". ")
                    .append(tasks.get(i));
        }
        return sb.toString();
    }
    private static String format(String text){
        return "\t____________________________________________________________\n" +
                "\t" +
                text +
                "\n" +
                "\t____________________________________________________________";

    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(format("Heyo, how u doing, Im Quacky. How can I help you?"));
        String command = scanner.nextLine();
        while(!command.equals("bye")){
            if(command.equals("list")) {
                System.out.println(format(printTasks()));
            } else {
                addTask(command);
                System.out.println(format("added: " + command));
            }
            command = scanner.nextLine();
        }
        System.out.println(format("bye bye"));
        scanner.close();
    }
}
