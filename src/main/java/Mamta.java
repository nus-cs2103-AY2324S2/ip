import java.io.FileNotFoundException;

public class Mamta {

    public static String greet() {
        return "Hello! I'm Mamta\nWhat can I do for you?";
    }

    public static String exit() {
        return "Bye. Hope to see you again soon!";
    }

    public static String echo(String taskType, String command, int taskNum, String deadline, String endTime) {
        String output = "";
        switch (command) {
            case "bye":
                return String.format("------------------------------------------\n%s\n------------------------------------------", Mamta.exit());
            case "list":
                StringBuilder returnOutput = new StringBuilder();
                int count = 0;
                returnOutput.append("------------------------------------------\n");
                for (Task item : TaskList.getHistory()) {
                    count += 1;
                    returnOutput.append(String.format("%d. %s\n", count, TaskList.getHistory().get(count - 1)));
                }
                returnOutput.append("------------------------------------------");
                return returnOutput.toString();
            case "mark":
                TaskList.getHistory().set(taskNum - 1, TaskList.getHistory().get(taskNum - 1).markDone());
                return String.format("------------------------------------------\nNice! I've marked this task as done\n%s\n------------------------------------------", TaskList.getHistory().get(taskNum - 1));
            case "unmark":
                TaskList.getHistory().set(taskNum - 1, TaskList.getHistory().get(taskNum - 1).unmarkTask());
                return String.format("------------------------------------------\nOK, I've marked this task as not done yet:\n%s\n------------------------------------------", TaskList.getHistory().get(taskNum - 1));
            case "delete":
                Task objToRemove = TaskList.getHistory().get(taskNum - 1);
                TaskList.removeTask(objToRemove);
                return String.format("------------------------------------------\nNoted. I've removed this task:\n%s\nNow you have %d tasks in the list\n------------------------------------------", objToRemove, TaskList.getHistory().size());
            default:
                //handle case where there is no command

                if (taskType.equals("todo")) {
                    Todo myTodo = new Todo(command);
                    if (!command.isEmpty()) {
                        TaskList.addTask(myTodo);
                        output = String.format("------------------------------------------\nGot it. I've added this task: \n%s\nNow you have %d tasks in the list\n------------------------------------------", myTodo, TaskList.getHistory().size());
                    } else {
                        output = String.valueOf(MamtaException.incompleteTaskDescription());
                    }

                } else if (taskType.equals("deadline")) {
                    Deadline myDead = new Deadline(command, deadline);
                    if (!command.isEmpty()) {
                        TaskList.addTask(myDead);
                        output = String.format("------------------------------------------\nGot it. I've added this task: \n%s\nNow you have %d tasks in the list\n------------------------------------------", myDead, TaskList.getHistory().size());
                    } else {
                        output = String.valueOf(MamtaException.incompleteTaskDescription());
                    }
                } else if (taskType.equals("event")) {
                    Event myEve = new Event(command, deadline, endTime);
                    if (!command.isEmpty()) {
                        TaskList.addTask(myEve);
                        output = String.format("------------------------------------------\nGot it. I've added this task: \n%s\nNow you have %d tasks in the list\n------------------------------------------", myEve, TaskList.getHistory().size());
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

    public static void run(String loadTasksFile, String inputNewTasksFile) {
        System.out.println(Ui.printBotLogo());
        Ui.loadTasksFromFile(loadTasksFile);
        Ui.handleInputFiles(inputNewTasksFile);
        Ui.callSaveTasksIntoFile(loadTasksFile);

    }

    public static void main(String[] args) throws FileNotFoundException {
        Mamta.run("./data/mamtainput.txt", "./text-ui-test/input.txt");
    }
}
