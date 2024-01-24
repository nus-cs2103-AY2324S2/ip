import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import CONSTANTS.MESSAGES;
import CONSTANTS.EXCEPTIONS;
import CONSTANTS.REGEX;
import CONSTANTS.CORRECT_USAGE;

public class Duke {

    private static class Tasks {
        private static ArrayList<Task> arr;

        public static int size() {
            return arr.size();
        }

        public static Task get(int i) {
            return arr.get(i);
        }
        public static void add(Task task) throws DuplicateTaskNameException {
            long dupes = arr.stream().filter(task1 -> task1.task.equals(task.task)).count();
            if (dupes != 0) {
                throw new DuplicateTaskNameException(
                        String.format(EXCEPTIONS.DUPLICATE_TASK_NAME, task.task));
            }
            arr.add(task);
        }

        public static Task delete(String index) throws IncorrectIndexException, NotEnoughInputsException {
            int i = 0;
            Task deleted;
            try {
                i = Integer.parseInt(index.strip()) - 1;
                deleted = arr.get(i);
            } catch (Exception e) {
                throw new IncorrectIndexException(
                        String.format(EXCEPTIONS.INCORRECT_INDEX,CORRECT_USAGE.DELETE));
            }
            arr.remove(i);
            echo(String.format(MESSAGES.DELETE, index, deleted));
            Tasks.list();
            return deleted;
        }

        public static void list() {
            for (int i = 0; i < Tasks.size(); i++) {
                Task currTask = Tasks.get(i);
                System.out.printf("%d: %s%n", i + 1, currTask);
            }
        }

        public Tasks() {
            arr = new ArrayList<>();
        }


    }
    private static abstract class Task {
        protected String task;
        protected boolean completed;

        public void setCompleted(boolean completed) {
            this.completed = completed;
        }

        public String completedIcon() {
            return this.completed ? "X" : " ";
        }

        public Task(String task) {
            this.task = task;
            this.completed = false;
        }

        public abstract String getType();

        public abstract String getAdditionalInfo();

        public abstract String toString();
    }

    private static class Todo extends Task {
        public Todo(String task) {
            super(task);
        }

        @Override
        public String getType() {
            return "[T]";
        }

        @Override
        public String getAdditionalInfo() {
            return "";
        }

        @Override
        public String toString() {
            return String.format("%s[%s]: %s", getType(), completedIcon(), task);
        }
    }

    private static class Deadline extends Task {
        private Date deadline;

        public Deadline(String task, Date deadline) {
            super(task);
            this.deadline = deadline;
        }

        @Override
        public String getType() {
            return "[D]";
        }

        @Override
        public String getAdditionalInfo() {
            return String.format(MESSAGES.DEADLINE_TO_STRING, deadline);
        }

        @Override
        public String toString() {
            return String.format("%s[%s]: %s%s", getType(), completedIcon(), task, getAdditionalInfo());
        }
    }

    private static class Event extends Task {
        private Date from;
        private Date to;

        public Event(String task, Date from, Date to) {
            super(task);
            this.from = from;
            this.to = to;
        }

        @Override
        public String getType() {
            return "[E]";
        }

        @Override
        public String getAdditionalInfo() {
            return String.format(MESSAGES.EVENT_TO_STRING, from, to);
        }

        @Override
        public String toString() {
            return String.format("%s[%s]: %s%s", getType(), completedIcon(), task, getAdditionalInfo());
        }
    }

    private abstract static class DukeException extends Exception {
        public DukeException(String message) {
            super(message);
        }
    }

    private static class IncorrectInputException extends DukeException {
        public IncorrectInputException(String message) {
            super(message);
        }
    }

    private static class NotEnoughInputsException extends IncorrectInputException {
        public NotEnoughInputsException(String message) {
            super(message);
        }
    }

    private static class NotEnoughDatesException extends NotEnoughInputsException {
        public NotEnoughDatesException(String message) {
            super(message);
        }
    }

    private static class DuplicateTaskNameException extends IncorrectInputException {

        public DuplicateTaskNameException(String message) {
            super(message);
        }
    }

    private static class IncorrectIndexException extends IncorrectInputException {
        public IncorrectIndexException(String message) {
            super(message);
        }
    }

    private static class Date {
        private String date;
        private String formattedDate;
        private void formatDate() {
            this.formattedDate = this.date;
        }
        public Date(String date) {
            this.date = date;
            formatDate();
        }

        public String toString() {
            return this.formattedDate;
        }
    }

    private static void Squid() {
        new Tasks();
    }

    private static void list() throws NotEnoughInputsException {
        echo(MESSAGES.LIST);
        Tasks.list();
    }

    private static void hello() throws NotEnoughInputsException {
        System.out.println(MESSAGES.LINE_BREAK);
        echo(CONSTANTS.MESSAGES.HELLO);
        System.out.println(MESSAGES.LINE_BREAK);
    }

    private static void bye() throws NotEnoughInputsException {
        String message = MESSAGES.BYE;
        echo(message);
    }

    private static void echo(String message, boolean isFromUser) throws NotEnoughInputsException {
        String[] params = message.split(" ", 2);
        if (params.length <= 1) {
            throw new NotEnoughInputsException(
                    String.format(
                            EXCEPTIONS.NOT_ENOUGH_INPUTS, "echo", CORRECT_USAGE.ECHO));
        }
        echo(params[1]);
    }

    private static void echo(String message) throws NotEnoughInputsException {
        if (message.isBlank()) {
            throw new NotEnoughInputsException(
                    String.format(
                            EXCEPTIONS.NOT_ENOUGH_INPUTS, "echo", CORRECT_USAGE.ECHO));
        }
        System.out.println(MESSAGES.ECHO + message);
    }

    private static void todo(String message) throws NotEnoughInputsException, DuplicateTaskNameException {
        String[] params = message.split(" ", 2);
        if (params.length <= 1) {
            throw new NotEnoughInputsException(
                    String.format(
                            EXCEPTIONS.NOT_ENOUGH_INPUTS, "todo", CORRECT_USAGE.TODO));

        }

        Task t = new Todo(params[1]);
        Tasks.add(t);
        echo(String.format(MESSAGES.TODO, t));
    }

    private static void deadline(String message) throws NotEnoughInputsException, DuplicateTaskNameException {
        String[] params = message.split(" ", 2);
        if (params.length <= 1) {
            throw new NotEnoughInputsException(
                    String.format(
                            EXCEPTIONS.NOT_ENOUGH_INPUTS,
                            "deadline",
                            CORRECT_USAGE.DEADLINE));
        }
        String[] arguments = params[1].split(REGEX.DEADLINE);

        if (arguments.length == 1) {
            throw new NotEnoughDatesException(
                    String.format(
                            EXCEPTIONS.NOT_ENOUGH_DATES,
                            1,
                            "deadline",
                            CORRECT_USAGE.DEADLINE));
        }
        String task = arguments[0];
        Date date = new Date(arguments[1]);
        Task t = new Deadline(task, date);
        Tasks.add(t);
        echo(String.format(MESSAGES.DEADLINE, t));
    }

    private static void event(String message) throws NotEnoughInputsException, DuplicateTaskNameException {
        String[] params = message.split(" ", 2);
        if (params.length <= 1) {
            throw new NotEnoughInputsException(
                    String.format(
                            EXCEPTIONS.NOT_ENOUGH_INPUTS,
                            "event",
                            CORRECT_USAGE.EVENT));
        }
        params = params[1].split(REGEX.EVENT_FROM);
        if (params.length != 2 || params[1].split(REGEX.EVENT_TO).length != 2) {
            throw new NotEnoughDatesException(
                    String.format(
                            EXCEPTIONS.NOT_ENOUGH_DATES,
                            2,
                            "event",
                            CORRECT_USAGE.EVENT));
        }
        String[] dates = params[1].split(REGEX.EVENT_TO);
        Date from = new Date(dates[0]);
        Date to = new Date(dates[1]);
        Task t = new Event(params[0], from, to);
        Tasks.add(t);
        echo(String.format(MESSAGES.EVENT,  t));
    }


    private static void mark(String task, boolean completed) throws NotEnoughInputsException {
        // Find the task entry.
        Task found = null;
        for (int i = 0; i < Tasks.size(); i++) {
            if (Tasks.get(i).task.equals(task)) {
                found = Tasks.get(i);
            }
        }
        if (found != null) {
            found.setCompleted(completed);
            echo(String.format(completed
                    ? MESSAGES.MARK_COMPLETE
                    : MESSAGES.MARK_INCOMPLETE, found));


        } else {
            echo(MESSAGES.MARK_NOT_FOUND);
        }
    }

    private static void delete(String input) throws NotEnoughInputsException, IncorrectIndexException {
        String[] params = input.split(" ", 2);
        if (params.length == 1) {
            throw new NotEnoughInputsException(
                    String.format(EXCEPTIONS.NOT_ENOUGH_INPUTS,
                            "delete",
                            CORRECT_USAGE.DELETE));
        }
        Tasks.delete(params[1]);
    }

    private static boolean parseInput(boolean loop, String input) throws DukeException {
        System.out.println(MESSAGES.LINE_BREAK);
        String[] messages = input.split(" ", 2);
        String command = messages[0];
        String params = "";
        if (messages.length > 1) {
            params = messages[1];
        }

        try {
            switch (command) {
                case ("bye"):
                    loop = false;
                    bye();
                    break;
                case ("echo"):
                    echo(input, true);
                    break;
                case ("list"):
                    list();
                    break;
                case ("mark"):
                    mark(input, true);
                    break;
                case ("unmark"):
                    mark(input, false);
                    break;
                case ("todo"):
                    todo(input);
                    break;
                case ("deadline"):
                    deadline(input);
                    break;
                case ("event"):
                    event(input);
                    break;
                case ("delete"):
                    delete(input);
                    break;
                default:
                    throw new IncorrectInputException(EXCEPTIONS.INCORRECT_INPUT);
            }
        } catch (IncorrectInputException e) {
            echo(e.getMessage());
        }

        return loop;
    }

    public static void main(String[] args) throws DukeException {
        Squid();
        hello();

        Scanner scanner = new Scanner(System.in);
        boolean loop = true;

        while (loop) {
            String input = scanner.nextLine().strip();
            loop = parseInput(loop, input);
            System.out.println(MESSAGES.LINE_BREAK);
        }

    }


}
