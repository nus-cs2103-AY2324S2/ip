import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final String EXITKEY = "bye";
    private static final String LISTKEY = "list";
    private static final String MARKKEY = "mark";
    private static final String UNMARKKEY = "unmark";
    private static final String E_KEY = "event";
    private static final String D_KEY = "deadline";
    private static final String T_KEY = "todo";
    private ArrayList<Task> inputArr = new ArrayList<>();

    public Integer getNumOfTasks() {
        return inputArr.size();
    }

    public static void main(String[] args) {
        String userInput = "";
        System.out.println("Hello! I'm Plaudern\nWhat can I do for you?");
        Scanner scanner = new Scanner(System.in);
        Duke duke = new Duke();
        while(!userInput.equals(EXITKEY)) {
            userInput = scanner.nextLine();
            // process the userInput
            String[] userInputSplit = userInput.split(" ");
            String userInputKey = userInputSplit[0];
            String inputDetail = "";
            String from = "";
            String to = "";
            if (userInputKey.equals(D_KEY)) {
                inputDetail = userInput.substring(9, userInput.indexOf("/by"));
                to = userInput.substring(userInput.indexOf("/by")+3);
            } else if (userInputKey.equals(T_KEY)) {
                inputDetail = userInput.substring(5);
            } else if (userInputKey.equals(E_KEY)){
                inputDetail = userInput.substring(6, userInput.indexOf("/from"));
                from = userInput.substring(userInput.indexOf("/from")+5, userInput.indexOf("/to"));
                to = userInput.substring(userInput.indexOf("/to")+3);
            }

            if (LISTKEY.equals(userInputKey)) {
                duke.listTask();
            } else if (MARKKEY.equals(userInputKey)){
                Integer index = new Integer(userInputSplit[1]) - 1;
                Task markedTask = duke.markTaskById(index, true);
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(markedTask);
            } else if (UNMARKKEY.equals(userInputKey)){
                Integer index = new Integer(userInputSplit[1]) - 1;
                Task unMarkedTask = duke.markTaskById(index, false);
                System.out.println("OK, I've marked this task as not done yet:");
                System.out.println(unMarkedTask);
            }else{
                // add different type of tasks
                Task task = duke.addTask(userInputKey, inputDetail, from, to);
                System.out.println("Got it. I've added this task:");
                System.out.println(task);
                System.out.println("Now you have "+ duke.getNumOfTasks() +" tasks in the list." );
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void listTask() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < inputArr.size(); i++) {
            System.out.println((i+1)+". " + inputArr.get(i));
        }
    }

    public Task addTask(String key, String detail, String from, String to) {
        Task task = null;
//        System.out.println("---");
//        System.out.println(key);
//        System.out.println("---");
        switch (key) {
            case D_KEY:
                task = new Deadline(false, detail, to);
                break;
            case T_KEY:
                task = new Todo(false, detail);
                break;
            case E_KEY:
                task = new Event(false, detail, from, to);
                break;
        }
        inputArr.add(task);
        return task;
    }

    public Task markTaskById(Integer id, Boolean status) {
        this.inputArr.get(id).setStatus(status);
        return this.inputArr.get(id);
    }
}
