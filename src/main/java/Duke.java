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
            try {
                nextAction(input);
            } catch (ftException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("    ____________________________________________________________\n"
                + "    Bye. Hope to see you again soon!\n"
                + "    ____________________________________________________________" );
    }

    private void nextAction(String input) throws ftException {
        if (input.isEmpty()) {
            throw new ftException("Error: Please Type Command");
        }
        StringTokenizer st = new StringTokenizer(input);
        try {
            CommandTypes command = CommandTypes.valueOf(st.nextToken().toUpperCase());
            switch (command) {
                case BYE:
                    this.isActive = false;
                    break;
                case LIST:
                    showList();
                    break;
                case MARK:
                    mark(st);
                    break;
                case UNMARK:
                    unmark(st);
                    break;
                case TODO:
                    addTask(st, CommandTypes.TODO);
                    break;
                case DEADLINE:
                    addTask(st, CommandTypes.DEADLINE);
                    break;
                case EVENT:
                    addTask(st, CommandTypes.EVENT);
                    break;
                case DELETE:
                    deleteTask(st);
                    break;
                default:
                    throw new ftException("Unknown Command: Please use a correct command");
            }
        }catch (IllegalArgumentException e) {
            throw new ftException("Unknown Command: Please use a correct command");
        }
    }

    private void addTask(StringTokenizer st, CommandTypes ct) throws ftException {
        Task task = null;
        switch (ct) {
            case TODO:
                StringBuilder sbTD = new StringBuilder();
                while (st.hasMoreTokens()) {
                    String token = st.nextToken().trim();
                    sbTD.append(" ").append(token);
                }
                String todo = sbTD.toString();
                if (!todo.isEmpty()) {
                    task = new ToDo(todo);
                } else {
                    throw new ftException("Error: Please tell me what you have TO DO");
                }
                break;

            case DEADLINE:
                StringBuilder sbDL = new StringBuilder();
                StringBuilder sbBy = new StringBuilder();
                while (st.hasMoreTokens()) {
                    String token = st.nextToken().trim();
                    if (token.equals("/by")) {
                        while (st.hasMoreTokens()) {
                            sbBy.append(" ").append(st.nextToken());
                        }
                        break;
                    } else {
                        sbDL.append(" ").append(token);
                    }
                }
                String dt = sbDL.toString();
                String by = sbBy.toString();
                if (!dt.isEmpty() && !by.isEmpty()) {
                   task = new Deadline(dt, by);
                } else {
                    throw new ftException("Error: Please tell me your task and its deadline");
                }
                break;

            case EVENT:
                StringBuilder sb = new StringBuilder();
                StringBuilder sbFrom = new StringBuilder();
                StringBuilder sbTo = new StringBuilder();
                while (st.hasMoreTokens()) {
                    String token = st.nextToken().trim();
                    if (token.equals("/from")) {
                        while (st.hasMoreTokens()) {
                            String curr = st.nextToken().trim();
                            if (curr.equals("/to")) {
                                while (st.hasMoreTokens()) {
                                    sbTo.append(" ").append(st.nextToken());
                                }
                            } else {
                                sbFrom.append(" ").append(curr);
                            }
                        }
                    } else {
                        sb.append(" ").append(token);
                    }
                }
                String name = sb.toString();
                String from = sbFrom.toString();
                String to = sbTo.toString();
                if (!name.isEmpty() && !from.isEmpty() && !to.isEmpty()) {
                    task = new Event(name, from, to);
                } else {
                    throw new ftException("Error: Please tell me your event and its from/to dates");
                }
                break;
            default:
                throw new ftException("Error: Invalid Task Type");

        }
        myList.add(task);
        System.out.println("    ____________________________________________________________\n"
                + "    Completed. I've added this task: \n    "
                + task.toString()
                + "\n    Now you have " + myList.size() + " tasks in the list.\n"
                + "    ____________________________________________________________\n");
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

    private void mark(StringTokenizer st) throws ftException {
        if (!st.hasMoreTokens()) {
            throw new ftException("Error: No index provided");
        }
        int i = Integer.parseInt(st.nextToken());
        if ((0 < i) && (i <= myList.size())) {
            Task task = myList.get(i - 1);
            task.mark();
            System.out.println("    ____________________________________________________________\n"
                    + "    Nice! I've marked this task as done:\n"
                    + "      " + task.toString()
                    + "\n    ____________________________________________________________\n");
        } else {
            throw new ftException("Error: Please provide valid index");
        }
    }

    private void unmark(StringTokenizer st) throws ftException {
        if (!st.hasMoreTokens()) {
            throw new ftException("Error: No index provided");
        }
        int i = Integer.parseInt(st.nextToken());
        if ((0 < i) && (i <= myList.size())) {
            Task task = myList.get(i - 1);
            task.unmark();
            System.out.println("    ____________________________________________________________\n"
                    + "    OK, I've marked this task as not done yet:\n"
                    + "      " + task.toString()
                    + "\n    ____________________________________________________________\n");
        } else {
            throw new ftException("Error: Please provide valid index");
        }
    }

    private void deleteTask(StringTokenizer st) throws ftException {
        if (!st.hasMoreTokens()) {
            throw new ftException("Error: No index provided");
        }
        int i = Integer.parseInt(st.nextToken());
        if ((0 < i) && (i <= myList.size())) {
            String task = myList.remove(i - 1).toString();
            System.out.println("    ____________________________________________________________\n"
                    + "    Great!, You have completed the task:\n"
                    + "      " + task
                    + "\n    Now you have " + myList.size() + " tasks in the list.\n"
                    + "    ____________________________________________________________\n");
        } else {
            throw new ftException("Error: Please provide valid index");
        }
    }
}
