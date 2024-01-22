import exceptions.AlreadyExistsException;
import exceptions.InvalidInputException;
import exceptions.KaiYapException;
import exceptions.MissingInputException;

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
                error +
                "\n\t____________________________________________________________\n");
    }

    public void startChat() throws KaiYapException {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine().strip();
        while (!input.equals("bye")) {
            if (input.equals("list")) {
                this.listInputs();
            } else if (input.startsWith("mark ") || input.equals("mark")) {
                try {
                    this.markAsDone(input);
                } catch (KaiYapException e) {
                    printError(e.getMessage());
                }
            } else if (input.startsWith("unmark ") || input.equals("unmark")) {
                try {
                    this.unmarkDone(input);
                } catch (KaiYapException e) {
                    printError(e.getMessage());
                }
            } else {
                try {
                    String type = input.split(" ")[0];
                    Task task = null;
                    switch (type) {
                        case "todo":
                            try {
                                task = createTodo(input);
                            } catch (KaiYapException e) {
                                printError(e.getMessage());
                            }
                            break;
                        case "deadline":
                            try {
                                task = createDeadline(input);
                            } catch (KaiYapException e) {
                                printError(e.getMessage());
                            }
                            break;
                        case "event":
                            try {
                                task = createEvent(input);
                            } catch (KaiYapException e) {
                                printError(e.getMessage());
                            }
                            break;
                        default:
                            throw new InvalidInputException("I don't quite understand what you mean. Please try again! UwU :3");
                    }
                    if (task != null) {
                        this.echo(task);
                    }
                } catch (KaiYapException e) {
                    printError(e.getMessage());
                }
            }
            input = sc.nextLine().strip();
        }
    }

    public Todo createTodo(String input) throws KaiYapException {
        if (input.equals("todo")) {
            throw new MissingInputException("Your todo needs a description. Please try again! UwU :3");
        } else {
            return new Todo(input.substring(input.indexOf(' ') + 1));
        }
    }
    public Deadline createDeadline(String input) throws KaiYapException {
        if (input.equals("deadline")) {
            throw new MissingInputException("Your deadline needs a description. Please try again! UwU :3");
        } else {
            try {
                return new Deadline(
                        input.substring(input.indexOf("/by")).strip(),
                        input.substring(input.indexOf("/by") + 3).strip()
                );
            } catch (Exception e) {
                throw new MissingInputException("Your deadline is missing some important information. Please try again! UwU :3");
            }
        }
    }

    public Event createEvent(String input) throws KaiYapException {
        if (input.equals("event")) {
            throw new MissingInputException("Your event needs a description. Please try again! UwU :3");
        } else {
            try {
                return new Event(
                        input.substring(input.indexOf("/from")).strip(),
                        input.substring(input.indexOf("/from") + 5, input.indexOf("/to")).strip(),
                        input.substring(input.indexOf("/to") + 3).strip()
                );
            } catch (Exception e) {
                throw new MissingInputException("Your event is missing some important information. Please try again! UwU :3");
            }

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

    public void markAsDone(String index) throws KaiYapException {
        if (index.length() <= 5) {
            throw new MissingInputException("\tSorry, it seems like there is some missing input. Please try again! UwU :3");
        }
        int numericIndex = Integer.parseInt(index.substring(5).strip()) - 1;
        if (numericIndex >= this.taskList.size()) {
            throw new InvalidInputException("\tSorry, this task does not exist. Please try again! UwU :3");
        } else if (taskList.get(numericIndex).isTaskDone()) {
            throw new AlreadyExistsException("\tThis task has already been marked as done. Great job!");
        } else {
            taskList.get(numericIndex).setTaskDone(true);
            System.out.println(
                    "\t____________________________________________________________\n" +
                            "\tNice! I've marked this task as done:\n" +
                            "\t\t[X] " + taskList.get(numericIndex).getListItem() +
                            "\n\t____________________________________________________________"
            );
        }
    }

    public void unmarkDone(String index) throws KaiYapException {
        if (index.length() <= 7) {
            throw new MissingInputException("Sorry, it seems like there is some missing input. Please try again! UwU :3");
        }
        int numericIndex = Integer.parseInt(index.substring(7).strip()) - 1;
        if (numericIndex >= taskList.size()) {
            throw new InvalidInputException("Sorry, this task does not exist. Please try again! UwU :3");
        } else if (!taskList.get(numericIndex).isTaskDone()) {
            throw new AlreadyExistsException("\tThis task has already been marked as undone. Good luck!");
        } else {
            taskList.get(numericIndex).setTaskDone(false);
            System.out.println(
                    "\t____________________________________________________________\n" +
                            "\tOK, I've marked this task as not done yet:\n" +
                            "\t\t[ ] " + taskList.get(numericIndex).getListItem() +
                            "\n\t____________________________________________________________"
            );
        }
    }

    public static void main(String[] args) {
        KaiYap yap = new KaiYap();
        yap.sayHello();
        try {
            yap.startChat();
        } catch (KaiYapException e) {
            System.out.println(e.getMessage());
        }
        yap.sayBye();
    }
}
