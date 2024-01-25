import java.util.Scanner;

public class Duke {
    static final String NAME = "jun jie";
    static final String INTRO_MSG = "hi bro, im " + NAME + "\nwhat you want me to do?";
    static final String EXIT_MSG = "ok see you bro";

    private TaskList taskList;

    public Duke() {
        Scanner scanner = new Scanner(System.in);
        taskList = new TaskList();

        print(INTRO_MSG);
        while (true) {
            String input = scanner.nextLine();
            boolean exitFlag = handleInput(input);
            if (exitFlag) break;
        }
    }

    /**
     * Prints the given string surrounded by a border of dashes.
     * This method adds a decorative border to the printed output for visual emphasis.
     *
     * @param str The string to be printed.
     */
    public static void print(String str) {
        System.out.println("-------------------------");
        System.out.println(str);
        System.out.println("-------------------------");
    }

    /**
     * Handles user input and performs corresponding actions
     *
     * @param input
     * @return true if exit command is given, otherwise false
     */
    public boolean handleInput(String input) {
        String[] splitInput = input.split(" ", 2);
        String keyword = splitInput[0];
        String content = splitInput.length > 1 ? splitInput[1] : "";

        switch(keyword){
            case "list":
                print(taskList.toString());
                break;
            case "bye":
                print(EXIT_MSG);
                return true;
            case "mark": {
                Task task = taskList.get(Integer.parseInt(content) - 1);
                task.markDone(true);
                print("good job bro, marked this task as done:\n" + task.toString());
                break;
            }
            case "unmark": {
                Task task = taskList.get(Integer.parseInt(content) - 1);
                task.markDone(false);
                print("ok i help you unmark this task ah:\n" + task.toString());
                break;
            }
            case "delete": {
                Task task = taskList.remove(Integer.parseInt(content) - 1);
                print("ok deleted this one:\n" + task.toString());
                break;
            }
            case "todo": {
                try {
                    Task task = new TodoTask(content);
                    taskList.add(task);
                    print("added this task for you liao:\n" + task.toString());
                } catch (DukeException e) {
                    print(e.toString());
                }

                break;
            }
            case "deadline": {
                String[] splitContent = content.split(" /by ");
                String eventName = splitContent[0];
                String deadline = splitContent.length > 1 ? splitContent[1] : "";
                try {
                    Task task = new DeadlineTask(eventName, deadline);
                    taskList.add(task);
                    print("added this task for you liao:\n" + task.toString());
                } catch (DukeException e) {
                    print(e.toString());
                }
                break;
            }
            case "event": {
                String[] splitContent = content.split(" /from ");
                String eventName = splitContent[0];
                String temp = splitContent.length > 1 ? splitContent[1] : "";

                splitContent = temp.split(" /to ");
                String from = splitContent[0];
                String to = splitContent.length > 1 ? splitContent[1] : "";

                try {
                    Task task = new EventTask(eventName, from, to);
                    taskList.add(task);
                    print("added this task for you liao:\n" + task.toString());
                } catch (DukeException e) {
                    print(e.toString());
                }
                break;
            }
            default:
                print("what are u saying");
                break;
        }
        return false;
    }

    public static void main(String[] args) {
        new Duke();
    }
}
