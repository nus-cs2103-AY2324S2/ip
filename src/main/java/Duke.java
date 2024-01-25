import java.util.*;
import java.util.Random;
public class Duke {
    public static void main(String[] args) {
        String line = "____________________________________________________________\n";
        Random random = new Random();

        String[] catchphrases = {
                "Yee-haw!",
                "So long, partner.",
                "To infinity and beyond!",
                "Reach for the sky!",
                "There's a snake in my boot!"
        };

        String greeting =
                line +
                "   Hello! I'm Woody\n" +
                "   What can I do for you?\n" +
                line;

        ArrayList<Task> userTasks = new ArrayList<>();

        String[] terminateKeywords = {"bye", "BYE", "Bye"};
        List<String> exitProgramme = Arrays.asList(terminateKeywords);

        // Programme start
        System.out.print(greeting);

        Scanner input = new Scanner(System.in);
        String[] currInput = input.nextLine().split(" ", 2);

        while (!exitProgramme.contains(currInput[0])) {
            String cmd = currInput[0];

            if (cmd.equals("list")) {               // list tasks
                System.out.println("    Here are the items in your list: ");
                for (int i = 0; i < userTasks.size(); i++) {
                    String listIdx = i + 1 + ". ";
                    Task currTask = userTasks.get(i);
                    System.out.println("    " + listIdx + currTask);
                }
            } else if (cmd.contains("mark")) {     // mark tasks
                String task = currInput[1];
                int taskIdx = Integer.parseInt(task) - 1;
                Task currTask = userTasks.get(taskIdx);

                if (cmd.equals("mark")) {
                    currTask.markAsDone();
                } else if (cmd.equals("unmark")) {
                    currTask.markAsUndone();
                }
            } else {                                // add tasks
                Boolean added = false;
                if (cmd.equals("deadline")) {
                    String[] task = currInput[1].split(" /by ");
                    Deadline newDL = new Deadline(task[0], task[1]);
                    userTasks.add(newDL);
                    added = true;
                } else if (cmd.equals("event")){
                    String[] task = currInput[1].split(" /from ", 2);
                    String[] period = task[1].split(" /to ", 2);
                    Event newEvt = new Event(task[0], period[0], period[1]);
                    userTasks.add(newEvt);
                    added = true;
                } else if (cmd.equals("todo")) {
                    Todo newTd = new Todo(currInput[1]);
                    userTasks.add(newTd);
                    added = true;
                }

                if (added) {
                    int numTasks = userTasks.size();
                    Task addedTask = userTasks.get(numTasks - 1);
                    System.out.println("    Got it. I've added this task:\n" +
                            "        " + addedTask + "\n" +
                            "    Now you have " + numTasks + " tasks in the list."
                            );
                }

            }

            System.out.println(line);
            currInput = input.nextLine().split(" ", 2);
        }

        // System.out.print("   Bye! " + catchphrases[random.nextInt(catchphrases.length)] + "\n" + line);
        System.out.print("   Bye! " + catchphrases[0] + "\n" + line);
    }
}
