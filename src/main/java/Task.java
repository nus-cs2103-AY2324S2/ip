public class Task {
    String name;
    boolean done;

    public Task(String name) {
        this.name = name;
        this.done = false;
    }

    public Task() {
        this.name = "";
        this.done = false;
    }

    public void mark() {};

    public void unmark() {};


    @Override
    public String toString() {
        if (this.done) {
            return "[X] " + this.name;
        } else {
            return "[ ] " + this.name;
        }
    }

    public String getInput() {
        if (this.done) {
            return "1 ";
        } else {
            return "0 ";
        }
    }

    public Task parseFromData(String input) throws DukeException {
        Task task = null;
        boolean done;
        String[] arr = input.split(":", 5);
        if (arr[0].equals("1")) {
            done = true;
        } else {
            done = false;
        }
        String command = arr[1];

        if (command.equals("todo")) {
            task = new Todo(arr[2], done);
        } else if (command.equals("deadline")) {

            try {
                String name = arr[2];
                String by = arr[3];
                task = new Deadline(name, by, done);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Invalid format");
            }

        } else if (command.equals("event")) {
            try {
                String name = arr[2];
                String from = arr[3];
                String to = arr[4];
                task = new Event(name, from, to, done);
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Invalid format");
            }

        } else {
            throw new DukeException("I don't know what that means.");
        }
        return task;
    }

    public Task parseFromInput(String input) throws DukeException {
        Task task = null;
        String[] arr = input.split(" ", 2);
        if (arr.length <= 1) {
            throw new DukeException("Please use the format: deadline <task> /by <date/time>\n" +
                    "todo <task>\n" +
                    "event <task> /from <date/time> /to <date/time>");
        }
        String cmd = arr[0];
        switch (cmd) {
        case "todo":
            task = new Todo(arr[1]);
            break;
        case "deadline":
            String[] dlarr = arr[1].split("/by", 2);
            if (dlarr.length <= 1) {
                throw new DukeException("Please use the format: deadline <task> /by <deadline>.");
            }
            String name = dlarr[0];
            String by = dlarr[1];
            task = new Deadline(name, by);
            break;
        case "event":
            String[] frarr = arr[1].split("/from", 2);
            if (frarr.length <= 1) {
                throw new DukeException("Please use the format: event /from <date/time> /to <date/time>");
            }
            String[] toarr = frarr[1].split("/to", 2);
            if (toarr.length <= 1) {
                throw new DukeException("Please use the format: event /from <date/time> /to <date/time>");
            }
            String name1 = frarr[0];
            String from = toarr[0];
            String to = toarr[1];
            task = new Event(name1, from, to);
            break;
        default:
            throw new DukeException("Invalid format");
        }

        return task;
    }
}
