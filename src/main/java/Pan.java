import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Pan {
    public static List<Task> tasks = new ArrayList<Task>();
    public static void main(String[] args) {
        hello();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("");
            String instruction = scanner.nextLine();
            System.out.println("");

            // regex to test mark and unmark
            String testMarkRegexString= "(mark) \\d+";
            Pattern testMarkRegexPattern = Pattern.compile(testMarkRegexString);

            String testUnMarkRegexString= "(unmark) \\d+";
            Pattern testUnMarkRegexPattern = Pattern.compile(testUnMarkRegexString);

            if (instruction.equals("list")) {
                list();
                continue;
            } else if (instruction.equals("bye")) {
                bye();
                break;
            } else if (testMarkRegexPattern.matcher(instruction).matches()) {
                String index = instruction.split(" ")[1];
                mark(Integer.parseInt(index));
            } else if (testUnMarkRegexPattern.matcher(instruction).matches()) {
                String index = instruction.split(" ")[1];
                unmark(Integer.parseInt(index));
            } else {
                tasks.add(new Task(instruction, false));
                add(instruction);
                continue;
            }
        }
        scanner.close();
    }

    public static void hello() {
        System.out.println("Hello! I'm Pan\nWhat can I do for you?\n");
    }

    public static void bye() {
        System.out.println("Bye. Hope to see you again soon!\n");
    }

    public static void add(String instruction) {
        System.out.println("added: " + instruction + "\n");
    }

    public static void list() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i+1) + "." + tasks.get(i).toString());
        }
    }

    public static void mark(int index) {
        Task task = tasks.get(index - 1);
        task.setIsDone(true);
        System.out.println("Nice! I've marked this task as done:\n\t" + task.toString());
    }

    public static void unmark(int index) {
        Task task = tasks.get(index - 1);
        task.setIsDone(false);
        System.out.println("OK, I've marked this task as not done yet:\n\t" + task.toString());
    }
}
