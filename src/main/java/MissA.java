import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
public class MissA {
    // greet, exit sentences
    private String emptyLine = "____________________________________________________________\n";
    private String greeting = "What can I do for you?\n"
            + emptyLine
            + "I can record 3 types of tasks now (●ﾟωﾟ●)\n"
            + "ToDos: e.g. todo clean my room\n"
            + "Deadlines: e.g. deadline submit homework /by 7pm\n"
            + "Events: e.g. event lecture /from 1pm /to 3pm\n"
            + emptyLine
            + "Here are the commands that I can understand:)\n"
            + "\"list\": I will display the latest task list |●´∀`|σ \n"
            + "\"mark/unmark + number\": I will mark/unmark task in the list!\n"
            + "\"delete + number\": I will remove task from the list (*`ェ´*) \n"
            + "\"bye\": this program will be closed (O_O)\n"
            + emptyLine;
    private String goodBye = "Bye ┭┮﹏┭┮. Have a nice day!\n"
            + emptyLine;
    // store existing tasks in list
    private ArrayList<Task> taskList = new ArrayList<>(100);

    /*
     * Display items in task list.
     *
     * @return A string containing all tasks added by user.
     */
    public String getTasks() {
        String str = emptyLine + "Here are your tasks <(￣︶￣)>: \n";
        for (int i = 0; i < taskList.size(); i++) {
            int index = i + 1;
            str += index + ". " + taskList.get(i) + "\n";
        }
        str += emptyLine;
        return str;
    }

    /*
     * Mark task as done.
     *
     * @param idx Index of task to be marked as done.
     */
    public void markTask(int idx) {
        Task t = taskList.get(idx);
        t.mark();
    }

    /*
     * Mark task as not done.
     *
     * @param idx Index of task to be marked as not done.
     */
    public void unmarkTask(int idx) {
        Task t = taskList.get(idx);
        t.unmark();
    }

    /*
     * Remove task.
     *
     * @param idx Index of task to be removed from the list.
     */
    public void deleteTask(int idx) {
        taskList.remove(idx);
    }
    public static void main(String[] args) throws NoSuchTaskException {
        MissA missA = new MissA();

        // greet users when first enter the program
        System.out.println("Hello from Miss A (●´∀｀●)ﾉ\n" + missA.greeting);

        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();

        while (!userInput.equals("bye")) {
            try {
                // show task list
                if (userInput.equals("list")) {
                    System.out.println(missA.getTasks());

                    // mark task as done
                } else if (userInput.startsWith("mark")) {
                    String[] input = userInput.split(" ");
                    if (input.length < 2) {
                        throw new NoSuchTaskException();
                    }
                    int idx = Integer.valueOf(input[1]);
                    if (idx > missA.taskList.size()) {
                        throw new NoSuchTaskException();
                    }
                    missA.markTask(idx - 1);
                    System.out.println(missA.emptyLine
                            + "Great! You have completed this task!\n"
                            + missA.emptyLine);

                    // unmark task
                } else if (userInput.startsWith("unmark")) {
                    String[] input = userInput.split(" ");
                    if (input.length < 2) {
                        throw new NoSuchTaskException();
                    }
                    int idx = Integer.valueOf(input[1]);
                    if (idx > missA.taskList.size()) {
                        throw new NoSuchTaskException();
                    }
                    missA.unmarkTask(idx - 1);
                    System.out.println(missA.emptyLine
                            + "Ok, this task is still in progress :-(\n"
                            + missA.emptyLine);

                    // add task to task list
                } else if (userInput.startsWith("delete")) {
                    String[] input = userInput.split(" ");
                    if (input.length < 2) {
                        throw new NoSuchTaskException();
                    }
                    int idx = Integer.valueOf(input[1]);
                    if (idx > missA.taskList.size()) {
                        throw new NoSuchTaskException();
                    }
                    System.out.println(missA.emptyLine
                            + "Noted. I will remove this task:\n"
                            + "  " + missA.taskList.get(idx - 1)
                            + "\n"
                            + "Now you have " + (missA.taskList.size() - 1) + " tasks!\n"
                            + missA.emptyLine);
                    missA.deleteTask(idx - 1);

                    // add task to task list
                } else {
                    String[] task = userInput.split(" ", 2);
                    String taskType = task[0];
                    Task nextTask = null;

                    // check if the task type is todo
                    if (taskType.equals("todo")) {
                        if (task.length < 2) {
                            throw new NoContentException();
                        }
                        String content = task[1];
                        nextTask = new ToDo(content);
                        missA.taskList.add(nextTask);

                        // check if the task type is deadline
                    } else if (taskType.equals("deadline")) {
                        if (task.length < 2) {
                            throw new NoContentException();
                        }
                        if (!task[1].contains("/by")) {
                            throw new NoTimingException();
                        }
                        String[] content = task[1].split("/by");
                        nextTask = new Deadline(content[0], content[1]);
                        missA.taskList.add(nextTask);

                        // check if the task type is event
                    } else if (taskType.equals("event")) {
                        if (task.length < 2) {
                            throw new NoContentException();
                        }
                        if (!task[1].contains("/from") || !task[1].contains("/to")) {
                            throw new NoTimingException();
                        }
                        String[] content = task[1].split("/from");
                        String text = content[0];
                        String[] interval = content[1].split("/to");
                        nextTask = new Event(text, interval[0], interval[1]);
                        missA.taskList.add(nextTask);
                    } else {
                        throw new IncorrectTaskTypeException();
                    }
                    System.out.println(missA.emptyLine
                            + "Ok, I will add in this task: \n"
                            + "  " + nextTask
                            + "\n"
                            + "Now there are " + missA.taskList.size() + " tasks in the list. (｡♥‿♥｡)\n"
                            + missA.emptyLine);
                }
            } catch (IncorrectTaskTypeException
                     | NoSuchTaskException
                     | NoTimingException
                     | NoContentException e) {
                System.out.println(missA.emptyLine + e.getMessage() + "\n" + missA.emptyLine);
            } finally {
                userInput = scanner.nextLine();
            }
        }

        // exit program
        System.out.println(missA.emptyLine + missA.goodBye);
    }
}
