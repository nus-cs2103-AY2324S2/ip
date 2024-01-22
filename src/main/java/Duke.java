import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static String EXITKEY = "bye";
    private static String LISTKEY = "list";
    private static String MARKKEY = "mark";
    private static String UNMARKKEY = "unmark";
    private ArrayList<Task> inputArr = new ArrayList<>();

    public static void main(String[] args) {
        String userInput = "";
        System.out.println("Hello! I'm Plaudern\nWhat can I do for you?");
        Scanner scanner = new Scanner(System.in);
        Duke duke = new Duke();
        while(!userInput.equals(EXITKEY)) {
            userInput = scanner.nextLine();
            String[] userInputSplit = userInput.split(" ");
            String userInputKey = userInputSplit[0];
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
                duke.addTask(userInput);
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

    public void addTask(String detail) {
        Task task = new Task(false, detail);
        inputArr.add(task);
        System.out.println("added: " + detail);
    }

    public Task markTaskById(Integer id, Boolean status) {
        this.inputArr.get(id).setStatus(status);
        return this.inputArr.get(id);
    }
}
