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
            case "todo": {
                Task task = new TodoTask(content);
                taskList.addTask(task);
                print("added this task for you liao:\n" + task.toString());
                break;
            }
            case "deadline": {
                String[] splitContent = content.split(" /by ");
                String eventName = splitContent[0];
                String deadline = splitContent[1];
                Task task = new DeadlineTask(eventName, deadline);

                taskList.addTask(task);
                print("added this task for you liao:\n" + task.toString());
                break;
            }
            case "event": {
                String[] splitContent = content.split(" /from ");
                String eventName = splitContent[0];
                splitContent = splitContent[1].split(" /to ");
                String from = splitContent[0];
                String to = splitContent[1];

                Task task = new EventTask(eventName, from, to);
                taskList.addTask(task);
                print("added this task for you liao:\n" + task.toString());
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
