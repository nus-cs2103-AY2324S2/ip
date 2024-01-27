import java.util.*;
public class Duke {
    public static void main(String[] args) throws DukeException{
        Scanner scanner = new Scanner(System.in);
        List<Task> todo = new ArrayList<>();
        String divider = "---------------------------------------------------------------";
        String chat_name = "Dwight Schrute";
        System.out.printf("%s\nHello! I'm %s\nWhat Can I do for you?\n%s\n"
                , divider, chat_name, divider);
        String input, task_name, start, end, first_string, second_string, third_string; // for easy processing
        Task task;
        int idx;
        while (scanner.hasNext()) {
            try {
                input = scanner.nextLine();
                System.out.printf("\t%s\n", divider);
                switch (input.split(" ")[0]) {
                    case ("bye"):
                        System.out.println("\tBye. Hope to see you again soon!");
                        scanner.close();
                        return;
                    case ("list"):
                        for (int i = 0; i < todo.size(); i++) {
                            System.out.printf("\t%d. %s\n", i + 1, todo.get(i));
                        }
                        break;
                    case ("mark"):
                        idx = Integer.parseInt(input.split(" ")[1]);
                        todo.get(idx - 1).mark();
                        System.out.println("\tNice! I've marked this task as done:");
                        System.out.printf("\t\t%s\n", todo.get(idx - 1));
                        break;
                    case ("unmark"):
                        idx = Integer.parseInt(input.split(" ")[1]);
                        todo.get(idx - 1).unmark();
                        System.out.println("\tOK, I've marked this task as not done yet:");
                        System.out.printf("\t\t%s\n", todo.get(idx - 1));
                        break;
                    case ("todo"):
                        task_name = String.join(" ", Arrays.copyOfRange(input.split(" "), 1, input.split(" ").length));
                        try {
                            // calling the method
                            task = new TodoTask(task_name);
                            todo.add(task);
                            System.out.println("\tGot it. I've added this task:");
                            System.out.printf("\t\t%s\n", task);
                            System.out.printf("\tNow you have %d tasks in the list.", todo.size());
                            break;
                        } catch (DukeException err) {
                            System.out.println(err.getMessage());
                            break;
                        }


                    case ("deadline"):
                        first_string = input.split(" /")[0];
                        second_string = input.split(" /")[1];
                        task_name = String.join(" ", Arrays.copyOfRange(first_string.split(" "), 1, first_string.split(" ").length));
                        end = String.join(" ", Arrays.copyOfRange(second_string.split(" "), 1, second_string.split(" ").length));
                        try {
                            task = new DeadlineTask(task_name, end);
                            todo.add(task);
                            System.out.println("\tGot it. I've added this task:");
                            System.out.printf("\t\t%s\n", task);
                            System.out.printf("\tNow you have %d tasks in the list.", todo.size());
                            break;
                        } catch (DukeException err) {
                            System.out.println(err.getMessage());
                            break;
                        }
                    case ("event"):
                        first_string = input.split(" /")[0];
                        second_string = input.split(" /")[1];
                        third_string = input.split(" /")[2];
                        task_name = String.join(" ", Arrays.copyOfRange(first_string.split(" "), 1, first_string.split(" ").length));
                        start = String.join(" ", Arrays.copyOfRange(second_string.split(" "), 1, second_string.split(" ").length));
                        end = String.join(" ", Arrays.copyOfRange(third_string.split(" "), 1, third_string.split(" ").length));
                        try {
                            task = new EventTask(task_name, start, end);
                            todo.add(task);
                            System.out.println("\tGot it. I've added this task:");
                            System.out.printf("\t\t%s\n", task);
                            System.out.printf("\tNow you have %d tasks in the list.", todo.size());
                            break;
                        } catch (DukeException err) {
                            System.out.println(err.getMessage());
                            break;
                        }
                    default:
                        throw new DukeException("\tSorry, I did not understand the command!");

                }
                System.out.printf("\t%s\n", divider);
            } catch (DukeException err) {
                System.out.println(err.getMessage());
                System.out.printf("\t%s\n", divider);
            }
        }

    }
}
