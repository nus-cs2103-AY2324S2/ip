import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Duke {
    private boolean isActive;
    private List<Task> myList;

    public Duke() {
        this.isActive = true;
        this.myList = new ArrayList<>();
    }
    public static void main(String[] args) {
        Duke ft = new Duke();
        ft.init();
    }
    public void init() {
        System.out.println("    ____________________________________________________________\n"
                + "    Hello! I'm FriendlyTool\n"
                + "    What can I do for you?\n"
                + "    ____________________________________________________________"
        );
        Scanner sc = new Scanner(System.in);
        while (this.isActive) {
            String input = sc.nextLine();
            nextAction(input);
        }
        System.out.println("    ____________________________________________________________\n"
                + "    Bye. Hope to see you again soon!\n"
                + "    ____________________________________________________________" );
    }

    private void nextAction(String input) {
        StringTokenizer st = new StringTokenizer(input);
        String arg1 = st.nextToken();
        String arg2 = st.hasMoreTokens() ? st.nextToken().toLowerCase() : "";
        switch (arg1) {
            case "bye":
                this.isActive = false;
                break;
            case "list":
                showList();
                break;
            case "mark":
                mark(Integer.parseInt(arg2));
                break;
            case "unmark":
                unmark(Integer.parseInt(arg2));
                break;
            default:
                Task myTask = new Task(arg1);
                addTask(myTask);
                break;

        }
    }

    private void addTask(Task task) {
        myList.add(task);
        System.out.println("    ____________________________________________________________\n"
                + "    added: "
                + task.getName()
                + "\n    ____________________________________________________________\n");
    }

    private void showList() {
        System.out.println("    ____________________________________________________________\n");
        for (int i = 1; i < myList.size() + 1; i++) {
            Task task = myList.get(i-1);
            if (task.isDone()) {
                System.out.println("    " + i + "." + "[X] " +task.getName());
            } else {
                System.out.println("    " + i + "." + "[ ] "+ task.getName());
            }
        }
        System.out.println("    ____________________________________________________________\n");
    }

    private void mark(int i) {
        Task task = myList.get(i - 1);
        task.mark();
        System.out.println("    ____________________________________________________________\n"
                + "    Nice! I've marked this task as done:\n"
                + "      " + "[X] " +task.getName()
                + "\n    ____________________________________________________________\n");
    }

    private void unmark(int i) {
        Task task = myList.get(i - 1);
        task.unmark();
        System.out.println("    ____________________________________________________________\n"
                + "    OK, I've marked this task as not done yet:\n"
                + "      " + "[ ] " +task.getName()
                + "\n    ____________________________________________________________\n");
    }
}
