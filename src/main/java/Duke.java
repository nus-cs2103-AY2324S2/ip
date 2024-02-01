import java.io.File;
import java.io.IOException;
import java.util.*;
public class Duke {
    private static final String line = "_________________________________________________________________________\n";
    private static final String[] catchphrases = {
            "Yee-haw!",
            "So long, partner.",
            "To infinity and beyond!",
            "Reach for the sky!",
            "There's a snake in my boot!"
    };
    private static final String greeting = line + "   Hello! I'm Woody\n"
            + "   What can I do for you?\n" + line;
    private static final String[] terminateKeywords = {"bye", "BYE", "Bye"};
    private static final List<String> exitProgramme = Arrays.asList(terminateKeywords);

    public static void main(String[] args) {
        ArrayList<Task> userTasks = new ArrayList<>();

        // load previous file
        try {
            parseFile("./data", "data.txt");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        // programme start
        System.out.print(greeting);

        Scanner input = new Scanner(System.in);
        String[] currInput = input.nextLine().split(" ", 2);

        while (!exitProgramme.contains(currInput[0])) {
            dukeProgramme(userTasks, currInput);
            currInput = input.nextLine().split(" ", 2);
        }

        // save and exit
        userTasks.forEach((task) -> task.writeTask("./data/data.txt"));

        Random random = new Random();
        System.out.println("   Bye! " + catchphrases[random.nextInt(catchphrases.length)] + "\n" + line);
    }

    public static void dukeProgramme(ArrayList<Task> userTasks, String[] currInput) {
        try {
            String cmd = currInput[0];
            Boolean isAdded = false;

            // list tasks
            if (cmd.equals("list")) {
                System.out.println("    Here are the items in your list: ");
                for (int i = 0; i < userTasks.size(); i++) {
                    String listIdx = i + 1 + ". ";
                    Task currTask = userTasks.get(i);
                    System.out.println("    " + listIdx + currTask);
                }

            // mark tasks
            } else if (cmd.contains("mark")) {
                if (currInput.length < 2) {
                    throw new DukeException(String.format(DukeException.NON_EMPTY_DESC, cmd));
                }
                Integer task = Integer.parseInt(currInput[1]);
                int numTasks = userTasks.size();
                if (task > numTasks) {
                    throw new DukeException("you have " + numTasks
                            + " tasks. Please fill in a number lower than or equal to it!"
                    );
                }

                int taskIdx = task - 1;
                Task currTask = userTasks.get(taskIdx);

                if (cmd.equals("mark")) {
                    currTask.markAsDone();
                } else if (cmd.equals("unmark")) {
                    currTask.markAsUndone();
                }

            // delete tasks
            } else if (cmd.equals("delete")) {
                if (currInput.length < 2) {
                    throw new DukeException(String.format(DukeException.NON_EMPTY_DESC, cmd));
                }
                Integer task = Integer.parseInt(currInput[1]);
                int numTasks = userTasks.size();
                if (task > numTasks) {
                    throw new DukeException("you have " + numTasks
                            + " tasks. Please fill in a number lower than or equal to it!"
                    );
                }
                int taskIdx = task - 1;

                Task delTask = userTasks.remove(taskIdx);
                System.out.println("    Noted. I've removed this task:\n"
                        + "        " + delTask + "\n"
                        + "    Now you have " + (numTasks - 1) + " tasks in the list."
                );

            // add tasks
            } else if (cmd.equals("deadline")) {
                if (currInput.length < 2) {
                    throw new DukeException(String.format(DukeException.NON_EMPTY_DESC, cmd));
                }
                String[] task = currInput[1].split(" /by ");
                Deadline newDL = new Deadline(task[0], task[1]);
                userTasks.add(newDL);
                isAdded = true;
            } else if (cmd.equals("event")) {
                if (currInput.length < 2) {
                    throw new DukeException(String.format(DukeException.NON_EMPTY_DESC, cmd));
                }
                String[] task = currInput[1].split(" /from ", 2);
                String[] period = task[1].split(" /to ", 2);
                Event newEvt = new Event(task[0], period[0], period[1]);
                userTasks.add(newEvt);
                isAdded = true;
            } else if (cmd.equals("todo")) {
                if (currInput.length < 2) {
                    throw new DukeException(String.format(DukeException.NON_EMPTY_DESC, cmd));
                }
                Todo newTd = new Todo(currInput[1]);
                userTasks.add(newTd);
                isAdded = true;

            // unknown commands
            } else {
                throw new DukeException(String.format(DukeException.UNKNOWN_CMD, cmd));
            }

            // Check if task is added to add appropriate syntax.
            if (isAdded) {
                int numTasks = userTasks.size();
                Task addedTask = userTasks.get(numTasks - 1);
                System.out.println("    Got it. I've added this task:\n"
                        + "        " + addedTask + "\n"
                        + "    Now you have " + numTasks + " tasks in the list."
                );
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println(line);
        }
    }

    public static void parseFile(String dir, String fName) throws IOException {
        File directory = new File(dir);
        if (!directory.exists()) {
            directory.mkdir();
        }

        File file = new File(dir, fName);
        if (file.exists()) {
            Scanner s = new Scanner(file);
            while (s.hasNextLine()) {
                String taskType = s.nextLine();
                System.out.println(taskType);
            }
            s.close();
        } else {
            file.createNewFile();
        }
    }


}
