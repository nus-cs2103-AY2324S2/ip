package seedu.mamta;
import java.io.FileNotFoundException;


/**
 * Main class for the Mamta task manager application.
 */
public class Mamta {

    /**
     * Generates a greeting message.
     * @return A greeting message.
     */
    public static String greet() {
        return "Hello! I'm Mamta\nWhat can I do for you?";
    }

    /**
     * Generates a farewell message.
     * @return A farewell message.
     */
    public static String exit() {
        return "Bye. Hope to see you again soon!";
    }


    /**
     * Echoes the input command based on its type and parameters.
     * @param taskType The type of the task.
     * @param command The command to be executed.
     * @param taskNum The task number (if applicable).
     * @param deadline The deadline (if applicable).
     * @param endTime The end time (if applicable).
     * @return A response based on the input command.
     */
    public static String echo(String taskType, String command, int taskNum, String deadline, String endTime) {
        String output = "";

        switch (command) {
        case "bye":
            return String.format("------------------------------------------\n%s\n------------------------------------------", Mamta.exit());
        case "list":
            StringBuilder returnOutput = new StringBuilder();
            int count = 0;
            returnOutput.append("------------------------------------------\n");
            for (Task item : TaskList.getTasks()) {
                count += 1;
                returnOutput.append(String.format("%d. %s\n", count, TaskList.getTasks().get(count - 1)));
            }
            returnOutput.append("------------------------------------------");
            return returnOutput.toString();
        case "mark":
            TaskList.getTasks().set(taskNum - 1, TaskList.getTasks().get(taskNum - 1).markDone());
            return String.format("------------------------------------------\nNice! I've marked this task as done\n%s\n------------------------------------------", TaskList.getTasks().get(taskNum - 1));
        case "unmark":
            TaskList.getTasks().set(taskNum - 1, TaskList.getTasks().get(taskNum - 1).unmarkTask());
            return String.format("------------------------------------------\nOK, I've marked this task as not done yet:\n%s\n------------------------------------------", TaskList.getTasks().get(taskNum - 1));
        case "delete":
            Task objToRemove = TaskList.getTasks().get(taskNum - 1);
            TaskList.removeTask(objToRemove);
            return String.format("------------------------------------------\nNoted. I've removed this task:\n%s\nNow you have %d tasks in the list\n------------------------------------------", objToRemove, TaskList.getTasks().size());
        default:
            //handle case where there is no command
            if (taskType.equals("todo")) {
                Todo myTodo = new Todo(command);
                if (!command.isEmpty()) {
                    TaskList.addTask(myTodo);
                    output = String.format("------------------------------------------\nGot it. I've added this task: \n%s\nNow you have %d tasks in the list\n------------------------------------------", myTodo, TaskList.getTasks().size());
                } else {
                    output = String.valueOf(MamtaException.incompleteTaskDescription());
                }
            } else if (taskType.equals("deadline")) {
                Deadline myDead = new Deadline(command, deadline);
                if (!command.isEmpty()) {
                    TaskList.addTask(myDead);
                    output = String.format("------------------------------------------\nGot it. I've added this task: \n%s\nNow you have %d tasks in the list\n------------------------------------------", myDead, TaskList.getTasks().size());
                } else {
                    output = String.valueOf(MamtaException.incompleteTaskDescription());
                }
            } else if (taskType.equals("event")) {
                Event myEve = new Event(command, deadline, endTime);
                if (!command.isEmpty()) {
                    TaskList.addTask(myEve);
                    output = String.format("------------------------------------------\nGot it. I've added this task: \n%s\nNow you have %d tasks in the list\n------------------------------------------", myEve, TaskList.getTasks().size());
                } else {
                    output = String.valueOf(MamtaException.incompleteTaskDescription());
                }
            } else {
                //handling the default case where its not a tracked task type, throw an errors
                output = String.format("------------------------------------------\n%s\n------------------------------------------", MamtaException.invalidTaskType());
            }
            break;
        }
        return output;

    }

    /**
     * Runs the Mamta application.
     * @param loadTasksFile The file path for loading tasks.
     * @param inputNewTasksFile The file path for inputting new tasks.
     */
    public static void run(String loadTasksFile, String inputNewTasksFile) {
        System.out.println(Ui.printBotLogo());
        Ui.loadTasksFromFile(loadTasksFile);
        Ui.handleInputFiles(inputNewTasksFile);
        Ui.callSaveTasksIntoFile(loadTasksFile);

    }

    /**
     * Main method to run the Mamta application.
     * @param args Command-line arguments.
     * @throws FileNotFoundException If the specified file is not found.
     */
    public static void main(String[] args) throws FileNotFoundException {
        Mamta.run("./data/mamtainput.txt", "./text-ui-test/input.txt");
    }
}
