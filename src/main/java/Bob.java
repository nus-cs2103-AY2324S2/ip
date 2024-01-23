import java.util.Scanner;
public class Bob {
    public static boolean startsWith(String keyword, String input) {
        return input.length() >= keyword.length() &&
                keyword.equals(input.substring(0, keyword.length()));
    }

    private static Task getTaskFromIndex(String keyword, String input, Task[] list) {
        try {
            int num = Integer.parseInt(input.substring(keyword.length() + 1));
            Task task = list[num - 1];
            if (task == null) {
                throw new BobException("OH NOSE! Task number has not yet been assigned..");
            }
            return task;

        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("OH NOSE! Task number cannot be empty..");
        } catch (NumberFormatException e) {
            System.out.println("OH NOSE! Task number has to be an integer..");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("OH NOSE! Task number must be in valid range..");
        } catch (BobException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    private static boolean addTask(Task[] list, int nxtIdx, Task task) {
        if (task != null) {
            list[nxtIdx] = task;
            System.out.println("This task has been added:\n  " + task +
                               "\n" + "Now you have " + (nxtIdx + 1) +
                               " task(s) in total.");
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        String line = "____________________________________________________________\n";
        Scanner sc = new Scanner(System.in);
        Task[] list = new Task[100];
        int nxtIdx = 0;
        String input;

        System.out.println(line + "Hello, I'm Bob.\nI might help if I feel like it.");

        while (true) {
            System.out.println(line);
            input = sc.nextLine();
            System.out.print(line);

            if ("bye".equals(input)) {
                break;
            }

            // list all Tasks
            if ("list".equals(input)) {
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < nxtIdx; i++) {
                    System.out.println((i + 1) + "." + list[i]);
                }

            // mark Task as done
            } else if (startsWith("mark", input)) {
                Task task = getTaskFromIndex("mark", input, list);
                if (task != null) {
                    task.markAsDone();
                    System.out.println("Did you actually finish this? \uD83E\uDD14:\n" +
                                       "  " + task);
                }

            // mark Task as undone
            } else if (startsWith("unmark", input)) {
                Task task = getTaskFromIndex("unmark", input, list);
                if (task != null) {
                    task.markAsUndone();
                    System.out.println("I knew you didn't finish it! \uD83D\uDE0F:\n" +
                                       "  " + task);
                }

            } else if (startsWith(Todo.keyword, input)) {
                if (addTask(list, nxtIdx, Todo.of(input))) {
                    nxtIdx++;
                }

            } else if (startsWith(Deadline.keyword, input)) {
                if (addTask(list, nxtIdx, Deadline.of(input))) {
                    nxtIdx++;
                }

            } else if (startsWith(Event.keyword, input)) {
                if (addTask(list, nxtIdx, Event.of(input))) {
                    nxtIdx++;
                }

            } else {
                System.out.println("OH NOSE! Wakaranai... :(");
            }
        }

        System.out.println("Bye! You'll be back ;)\n" + line);
    }
}
