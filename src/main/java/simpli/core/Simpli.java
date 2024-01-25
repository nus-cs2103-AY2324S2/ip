package simpli.core;

import simpli.configs.SimpliConfiguration;
import simpli.exceptions.ActionException;
import simpli.exceptions.TaskException;
import simpli.interpreter.Interpreter;
import simpli.parser.Parser;
import simpli.tasks.Deadline;
import simpli.tasks.Event;
import simpli.tasks.Task;
import simpli.tasks.Todo;

import java.util.ArrayList;
import java.util.Scanner;

public class Simpli {
    private final ArrayList<Task> tasks = new ArrayList<>();

    public void start() {
        // welcome message
        this.greet();

        Parser parser = new Parser();
        Interpreter intrpr = new Interpreter();

        Scanner scanner = new Scanner(System.in);
        String userIn = scanner.nextLine();

        // Performing actions from user input
        while (!userIn.equals("bye")) {
            String[] tokens = parser.parse(userIn);

            try {
                intrpr.interpret(tokens);
            } catch (TaskException e) {
                this.respond("Invalid task parameters, cannot simp :(");
            } catch (ActionException e) {
                this.respond("No such command to simp for :(");
            }

            userIn = scanner.nextLine();
        }

        // goodbye message
        this.bye();
    }

    public void list() {
        StringBuilder strItems = new StringBuilder();
        for (int i = 0; i < this.tasks.size() - 1; i++) {
            strItems.append(String.format("%d. %s\n", i + 1, this.tasks.get(i)));
        }
        strItems.append(String.format("%d. %s", this.tasks.size(), this.tasks.get(this.tasks.size() - 1)));

        this.respond(strItems.toString());
    }

    public void mark(int taskNum) {
        int itemIndex = taskNum - 1;
        this.tasks.get(itemIndex).done();

        this.respond(
                "Nice! I've marked this task as done:\n\t" +
                        this.tasks.get(itemIndex)
        );
    }

    public void unmark(int taskNum) {
        int itemIndex = taskNum - 1;
        this.tasks.get(itemIndex).undone();

        this.respond(
                "OK, I've marked this task as not done yet:\n\t" +
                        this.tasks.get(itemIndex)
        );
    }

    public void addTodo(String taskName) {
        Todo todo = new Todo(taskName);
        this.tasks.add(todo);
        this.respond(
                "Got it. I've added this task:\n\t" +
                        todo + "\n" +
                        "Now you have " + this.tasks.size() + " task(s) in the list."
        );
    }

    public void addDeadline(String taskName, String dueDate) {
        Deadline deadline = new Deadline(taskName, dueDate);
        this.tasks.add(deadline);
        this.respond(
                "Got it. I've added this task:\n\t" +
                        deadline + "\n" +
                        "Now you have " + this.tasks.size() + " task(s) in the list."
        );
    }

    public void addEvent(String taskName, String start, String end) {
        Event event = new Event(taskName, start, end);
        this.tasks.add(event);
        this.respond(
                "Got it. I've added this task:\n\t" +
                        event + "\n" +
                        "Now you have " + this.tasks.size() + " task(s) in the list."
        );
    }

    public void deleteTask(int taskNum) {
        Task removedTask = this.tasks.get(taskNum - 1);
        this.tasks.remove(taskNum - 1);
        this.respond(
                "Noted. I've removed this task:\n" +
                        removedTask + "\n" +
                        "Now you have " + this.tasks.size() + " task(s) in the list."
        );
    }

    public void respond(String content) {
        String msg = String.format(SimpliConfiguration.PLACEHOLDER, content.replace("\n", "\n\t\t"));

        System.out.println(msg);
    }

    public void greet() {
        this.respond(
                "Hello! I'm SIMP-LI\n" +
                        "How can I simp-lify your life?"
        );
    }

    public void bye() {
        this.respond("Bye. Hope to simp for you again soon!");
    }

    @Override
    public String toString() {
        return "SIMP-LI";
    }
}
