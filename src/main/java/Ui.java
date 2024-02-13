public class Ui {
    private String input;

    public Ui (String input) {
        this.input = input;
    }

    public boolean parse(TaskList tasks) {
        if (this.input.equals("bye")) {
            return true;
        }
        if (this.input.equals("list")) {
            tasks.outputTasks();
            return false;
        }

        String instruction[] = this.input.split(" ", 2); // It only splits the first " "
        if (instruction[0].equals("mark")) {
            this.mark(instruction, tasks);
        } else if (instruction[0].equals("unmark")){
            this.unmark(instruction, tasks);
        } else if (instruction[0].equals("delete")) {
            this.delete(instruction, tasks);
        } else if (instruction[0].equals("todo")) {
            this.addToDo(instruction, tasks);
        } else if (instruction[0].equals("deadline")) {
            this.addDeadline(instruction, tasks);
        } else if (instruction[0].equals("event")) {
            this.addEvent(instruction, tasks);
        } else {
            LiteException.InvalidInput();
        }
        return false;
    }


    private void delete(String instruction[], TaskList tasks) {
        try {
            int index = Integer.parseInt(instruction[1]) - 1;
            Printer.printHorizontalLine();
            Printer.removeTask(tasks, index);
            tasks.remove(index);
            Printer.printHorizontalLine();
        } catch (NullPointerException | ArrayIndexOutOfBoundsException e){
            Printer.printHorizontalLine();
            Printer.deleteException(tasks);
            Printer.printHorizontalLine();
        }
    }

    private void addToDo(String instruction[], TaskList tasks) {
        try {
            String description = instruction[1];
            Printer.printHorizontalLine();
            Task todo = new Todo(description);
            tasks.add(todo);
            Printer.printTask(tasks, todo);
            Printer.printHorizontalLine();
        } catch (ArrayIndexOutOfBoundsException e) {
            Printer.printHorizontalLine();
            Printer.toDoException();
            Printer.printHorizontalLine();
        }
    }

    private void addDeadline(String instruction[], TaskList tasks) {
        try {
            String splits[] = instruction[1].split("/");
            String description = splits[0];
            String due = splits[1];
            Printer.printHorizontalLine();
            Task deadline = new Deadline(description, due);
            tasks.add(deadline);
            Printer.printTask(tasks, deadline);
            Printer.printHorizontalLine();
        } catch (ArrayIndexOutOfBoundsException e) {
            Printer.printHorizontalLine();
            Printer.deadlineException();
            Printer.printHorizontalLine();
        }
    }

    private void addEvent(String[] instruction, TaskList tasks) {
        try {
            String splits[] = instruction[1].split("/");
            String description = splits[0];
            String start = splits[1];
            String end = splits[2];
            Printer.printHorizontalLine();
            Task event = new Event(description, start, end);
            tasks.add(event);
            Printer.printTask(tasks, event);
            Printer.printHorizontalLine();
        } catch (ArrayIndexOutOfBoundsException e) {
            Printer.printHorizontalLine();
            Printer.eventException();
            Printer.printHorizontalLine();
        }
    }
    private void unmark(String instruction[], TaskList tasks) {
        try {
            int index = Integer.parseInt(instruction[1]) - 1;
            Printer.printHorizontalLine();
            Printer.printUnmark(tasks.get(index));
            Printer.printHorizontalLine();
        } catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
            Printer.printHorizontalLine();
            Printer.unmarkException(tasks);
            Printer.printHorizontalLine();
        }
    }

    private void mark(String instruction[], TaskList tasks) {
        try {
            int index = Integer.parseInt(instruction[1]) - 1;
            Printer.printHorizontalLine();
            Printer.printMark(tasks.get(index));
            Printer.printHorizontalLine();
        } catch (NullPointerException | ArrayIndexOutOfBoundsException e) {
            Printer.printHorizontalLine();
            Printer.markException(tasks);
            Printer.printHorizontalLine();
        }
    }
}
