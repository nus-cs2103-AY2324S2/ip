import java.util.*;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm PeWPeWPeW");

        ArrayList<Task> task_arr = new ArrayList<>();
        int index = 0;

        Scanner scanner = new Scanner(System.in);

        System.out.println("What can I do for you?");
        String userInput = scanner.nextLine();

        while (!Objects.equals(userInput.toLowerCase(), "bye")) {

            if (Objects.equals(userInput.toLowerCase(), "pewpewpew")) {
                task_arr.add(new Task(index, userInput));
                System.out.println("PeWPeWPeWPeWPeWPeWPeWPeWPeWPeWPeWPeW");
                index++;
            } else if (Objects.equals(userInput.toLowerCase(), "list")) {
                for (Task task : task_arr) {
                    System.out.println(task.getTask());
                }
            } else {
                task_arr.add(new Task(index, userInput));
                System.out.println(userInput);
                index++;
            }



            System.out.println("What else can I do for you? (try typing my name 3 times with no space in between)" );
            userInput = scanner.nextLine();

        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}