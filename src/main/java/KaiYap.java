import java.util.Scanner;
import java.util.ArrayList;
public class KaiYap {

    ArrayList<Task> taskList;

    public KaiYap() {
        taskList = new ArrayList<>();
    }
    public void sayHello() {
        System.out.println("\t____________________________________________________________\n" +
                "\tHello! I'm KaiYap.\n" +
                "\tWhat can I do for you?\n" +
                "\t____________________________________________________________\n"
        );
    }

    public void sayBye() {
        System.out.println("\t____________________________________________________________\n" +
                "\tBye. Hope to see you again soon!\n" +
                "\t____________________________________________________________");
    }

    public void printError(String error) {
        System.out.println("\t____________________________________________________________\n" +
                "\t" + error +
                "\n\t____________________________________________________________\n");
    }

    public void startChat() {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        input = input.strip();
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                this.listInputs();
            } else if ((input.length() > 5 && input.startsWith("mark ")) || input.equals("mark")) {
                this.markAsDone(input.substring(4).strip());
            } else if (input.length() > 7 && input.startsWith("unmark ") || input.equals("unmark")) {
                this.markAsUndone(input.substring(6).strip());
            } else {
                String type = input.split(" ")[0];
                Task task = null;
                switch (type) {
                    case "todo":
                        if (input.equals("todo")) {
                            printError("Your todo needs a description. Please try again! UwU :3");
                            break;
                        } else {
                            task = new Todo(input.substring(input.indexOf(' ') + 1));
                            break;
                        }
                    case "deadline":
                        if (input.equals("deadline")) {
                            printError("Your deadline needs a description. Please try again! UwU :3");
                            break;
                        } else {
                            task = new Deadline(input.substring(input.indexOf(' ') + 1));
                            break;
                        }
                    case "event":
                        if (input.equals("event")) {
                            printError("Your event needs a description. Please try again! UwU :3");
                            break;
                        } else {
                            task = new Event(input.substring(input.indexOf(' ') + 1));
                            break;
                        }
                    default:
                        printError("That was an invalid input. Please try again! UwU :3");
                        break;
                }
                if (task != null) {
                    this.echo(task);
                }
            }
            input = sc.nextLine();
        }
    }

    public void listInputs() {
        System.out.println("\t____________________________________________________________\n\tHere are the tasks in your list:");
        for (int i = 0; i < this.taskList.size(); i++) {
            System.out.println("\t" + (i + 1) + ". " + taskList.get(i).toString());
        }
        System.out.println("\t____________________________________________________________");
    }
    public void echo(Task task) {
        this.taskList.add(task);
        System.out.println("\t____________________________________________________________\n" +
                "\tGot it. I've added this task:\n\t\t" + task.toString() +
                "\n\tYou now have " + this.taskList.size() + (this.taskList.size() == 1 ? " task" : " tasks") + " in the list.\n" +
                "\t____________________________________________________________\n");
    }

    public void markAsDone(String index) {
        System.out.println("\t____________________________________________________________");
        try {
            int numericIndex = Integer.parseInt(index) - 1;
            if (numericIndex >= this.taskList.size()) {
                System.out.println("\tSorry, this task does not exist. Please try again! UwU :3");
            } else if (taskList.get(numericIndex).isTaskDone()) {
                System.out.println("\tThis task has already been marked as done. Great job!");
            } else {
                taskList.get(numericIndex).setTaskDone(true);
                System.out.println("\tNice! I've marked this task as done:\n" +
                        "\t\t[X] " + taskList.get(numericIndex).getListItem()
                );
            }

        } catch (Exception e) {
            System.out.println("That was an invalid input. Please try again! UwU :3");
        }
        System.out.println("\t____________________________________________________________");
    }

    public void markAsUndone(String index) {
        System.out.println("\t____________________________________________________________");
        try {
            int numericIndex = Integer.parseInt(index) - 1;
            if (numericIndex >= taskList.size()) {
                System.out.println("\tSorry, this task does not exist. Please try again! UwU :3");
            } else if (!taskList.get(numericIndex).isTaskDone()) {
                System.out.println("\tThis task has already been marked as undone. Good luck!");
            } else {
                taskList.get(numericIndex).setTaskDone(false);
                System.out.println("\tOK, I've marked this task as not done yet:\n" +
                        "\t\t[ ] " + taskList.get(numericIndex).getListItem()
                );
            }
        } catch (Exception e) {
            System.out.println("\tThat was an invalid input. Please try again! UwU :3");
        }
        System.out.println("\t____________________________________________________________");
    }

    public static void main(String[] args) {
        KaiYap yap = new KaiYap();
        yap.sayHello();
        yap.startChat();
        yap.sayBye();
    }
}
