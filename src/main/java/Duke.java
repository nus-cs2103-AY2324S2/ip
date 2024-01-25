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
        switch (arg1) {
            case "bye":
                this.isActive = false;
                break;
            case "list":
                showList();
                break;
            case "mark":
                int i1 = Integer.parseInt(st.nextToken());
                mark(i1);
                break;
            case "unmark":
                int i2 = Integer.parseInt(st.nextToken());
                unmark(i2);
                break;
            case "todo":
                addToDoTask(st);
                break;
            case "deadline":
                addDeadlineTask(st);
                break;
            case "event":
                addEventTask(st);
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
                + "    Completed. I've added this task: \n    "
                + task.toString()
                + "\n    Now you have " + myList.size() + " tasks in the list.\n"
                + "    ____________________________________________________________\n");
    }

    private void addToDoTask(StringTokenizer st) {
        StringBuilder name = new StringBuilder();
        while (st.hasMoreTokens()) {
            String token = st.nextToken().trim();
            name.append(" ").append(token);
        }
        ToDo myToDo = new ToDo(name.toString());
        addTask(myToDo);
    }

    private void addDeadlineTask(StringTokenizer st) {
        StringBuilder name = new StringBuilder();
        StringBuilder by = new StringBuilder();
        while (st.hasMoreTokens()) {
            String token = st.nextToken().trim();
            if (token.equals("/by")) {
                while (st.hasMoreTokens()) {
                    by.append(" ").append(st.nextToken());
                }
                break;
            } else {
            name.append(" ").append(token);
            }
        }
        Deadline myDeadline = new Deadline(name.toString(), by.toString());
        addTask(myDeadline);
    }

    private void addEventTask(StringTokenizer st) {
        StringBuilder name = new StringBuilder();
        StringBuilder from = new StringBuilder();
        StringBuilder to = new StringBuilder();
        while (st.hasMoreTokens()) {
            String token = st.nextToken().trim();
            if (token.equals("/from")) {
                while (st.hasMoreTokens()) {
                    String curr = st.nextToken().trim();
                    if (curr.equals("/to")) {
                        while (st.hasMoreTokens()) {
                            to.append(" ").append(st.nextToken());
                        }
                    } else {
                        from.append(" ").append(curr);
                    }
                }
            } else {
                name.append(" ").append(token);
            }
        }
        Event myEvent = new Event(name.toString(), from.toString(), to.toString());
        addTask(myEvent);
    }
    private void showList() {
        System.out.println("    ____________________________________________________________\n"
                + "Here are the tasks in your list:");
        for (int i = 1; i < myList.size() + 1; i++) {
            Task task = myList.get(i-1);
            if (task.isDone()) {
                System.out.println("    " + i + "." + task.toString());
            } else {
                System.out.println("    " + i + "." + task.toString());
            }
        }
        System.out.println("    ____________________________________________________________\n");
    }

    private void mark(int i) {
        Task task = myList.get(i - 1);
        task.mark();
        System.out.println("    ____________________________________________________________\n"
                + "    Nice! I've marked this task as done:\n"
                + "      " + task.toString()
                + "\n    ____________________________________________________________\n");
    }

    private void unmark(int i) {
        Task task = myList.get(i - 1);
        task.unmark();
        System.out.println("    ____________________________________________________________\n"
                + "    OK, I've marked this task as not done yet:\n"
                + "      " + task.toString()
                + "\n    ____________________________________________________________\n");
    }
}
