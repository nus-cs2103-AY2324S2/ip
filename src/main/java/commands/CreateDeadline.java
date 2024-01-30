//package commands;
//
//import exception.InvalidDeadlineException;
//import objects.Deadlines;
//import objects.Task;
//import objects.TaskList;
//
//public class CreateDeadline implements Command {
//    private final TaskList tasks;
//    private final String input;
//
//    public CreateDeadline(TaskList tasks, String input) {
//        this.tasks = tasks;
//        this.input = input;
//    }
//
//    @Override
//    public void execute() throws InvalidDeadlineException {
//        if (!input.contains("/by")) {
//            throw new InvalidDeadlineException();
//        }
//
//        String[] parts = input.split("/by", 2);
//        String name = parts[0].trim();
//        String by = parts.length > 1 ? parts[1].trim() : "";
//
//        Task t = new Deadlines(name, by);
//        tasks.addTask(t);
//    }
//}
