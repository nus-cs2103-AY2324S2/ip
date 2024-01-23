import Exceptions.InvalidInputException;
import Tasks.DeadlineTask;
import Tasks.Task;
import Tasks.TodoTask;
import Tasks.EventTask;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Scanner;
public class Duke {
    private ArrayList<Task> tasks;
    private Scanner scanner;
    private TextReader textReader;

    public Duke() {
        this.tasks = new ArrayList<>();
        this.scanner = new Scanner(System.in);
        this.textReader = new TextReader();
    }

    private void sendSystemMessage(String... strs) {
        for (String s: strs) {
            System.out.println("\t" + s);
        }
    }
    private void greet() {
        this.sendSystemMessage(TextTemplate.LINE_BREAK, TextTemplate.GREETING, TextTemplate.LINE_BREAK);
    }

    private void exit() {
        this.sendSystemMessage(TextTemplate.EXIT, TextTemplate.LINE_BREAK);
        this.scanner.close();
    }

    private void addTodo(String s) {
        String[] parts = s.split(" ", 2);
        TodoTask t = new TodoTask(parts[1]);
        this.tasks.add(t);

        String taskCounterMsg = String.format(TextTemplate.TASK_COUNT, this.tasks.size());
        this.sendSystemMessage(TextTemplate.ADD_TASK, t.toString(), taskCounterMsg, TextTemplate.LINE_BREAK);
    }

    private void addDeadline(String s) throws InvalidInputException {
        String[] parts = s.split(" /by ", 2);
        String[] firstPart = parts[0].split(" ", 2);
        String desc = firstPart[1];
        String end = parts[1];

        DeadlineTask d = new DeadlineTask(desc, end);
        this.tasks.add(d);

        String taskCounterMsg = String.format(TextTemplate.TASK_COUNT, this.tasks.size());
        this.sendSystemMessage(TextTemplate.ADD_TASK, d.toString(), taskCounterMsg, TextTemplate.LINE_BREAK);
    }

    public void addEvent(String s) {
        String[] parts = s.split(" /from ", 2);
        String desc = parts[0].split(" ", 2)[1];
        String[] duration = parts[1].split(" /to ", 2);

        EventTask e = new EventTask(desc, duration[0], duration[1]);
        this.tasks.add(e);

        String taskCounterMsg = String.format(TextTemplate.TASK_COUNT, this.tasks.size());
        this.sendSystemMessage(TextTemplate.ADD_TASK, e.toString(), taskCounterMsg, TextTemplate.LINE_BREAK);
    }

    private void listTasks() {
        if (this.tasks.size() == 0) {
            this.sendSystemMessage("There are no tasks currently :)");
            return;
        }
        this.sendSystemMessage("Here are the tasks in your list:");
        for (int i = 0; i < this.tasks.size(); ++i) {
            Task t = this.tasks.get(i);
            String msg = String.valueOf(i+1) + ". " + t.toString();
            this.sendSystemMessage(msg);
        }
        this.sendSystemMessage(TextTemplate.LINE_BREAK);
    }

    private void markTask(String s) throws InvalidInputException {
        String[] parts = s.split("\\s+");
        int taskNum = Integer.parseInt(parts[1]) - 1;
        if (taskNum >= this.tasks.size()) {
            throw new InvalidInputException(TextTemplate.TASK_DOES_NOT_EXIST);
        }
        Task t = this.tasks.get(taskNum);
        t.maskAsDone();
        this.sendSystemMessage(TextTemplate.MARK_TASK, t.toString(), TextTemplate.LINE_BREAK);
    }

    private void unmarkTask(String s) throws InvalidInputException {
        String[] parts = s.split("\\s+", 2);
        int taskNum = Integer.parseInt(parts[1]) - 1;
        if (taskNum >= this.tasks.size()) {
            throw new InvalidInputException(TextTemplate.TASK_DOES_NOT_EXIST);
        }
        Task t = this.tasks.get(taskNum);
        t.unmark();
        this.sendSystemMessage(TextTemplate.UNMARK_TASK, t.toString(), TextTemplate.LINE_BREAK);
    }

    private void deleteTask(String s) throws InvalidInputException {
        String[] parts = s.split("\\s+", 2);
        int taskNum = Integer.parseInt(parts[1]) - 1;
        if (taskNum >= this.tasks.size()) {
            throw new InvalidInputException(TextTemplate.TASK_DOES_NOT_EXIST);
        }
        String msg = this.tasks.get(taskNum).toString();
        this.tasks.remove(taskNum);
        this.sendSystemMessage(TextTemplate.DELETE_TASK, msg, TextTemplate.LINE_BREAK);
    }

    public void run() {
        this.greet();
        while (textReader.isActive()) {
            String input = scanner.nextLine().trim();
            this.sendSystemMessage(TextTemplate.LINE_BREAK);
            try {
                Actions act = textReader.getAction(input);
                switch (act) {
                    case BYE:
                        this.textReader.exit();
                        break;
                    case LIST:
                        this.listTasks();
                        break;
                    case MARK:
                        this.markTask(input);
                        break;
                    case UNMARK:
                        this.unmarkTask(input);
                        break;
                    case DELETE:
                        this.deleteTask(input);
                        break;
                    case TODO:
                        this.addTodo(input);
                        break;
                    case EVENT:
                        this.addEvent(input);
                        break;
                    case DEADLINE:
                        this.addDeadline(input);
                        break;
                    case INVALID:
                        throw new InvalidInputException();
                }
            } catch (InvalidInputException e) {
                this.sendSystemMessage(e.getMessage(), TextTemplate.LINE_BREAK);
            }
        }
        this.exit();
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}
