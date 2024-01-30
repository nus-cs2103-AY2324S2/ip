//package commands;
//
//import exception.InvalidCommandException;
//import exception.InvalidDeadlineException;
//import exception.InvalidEventException;
//import objects.Events;
//import objects.Task;
//import objects.TaskList;
//
//public class CreateEvent implements Command {
//    private final TaskList tasks;
//    private final String input;
//
//    public CreateEvent(TaskList tasks, String input) {
//        this.tasks = tasks;
//        this.input = input;
//    }
//
//    @Override
//    public void execute() throws InvalidEventException {
//        if (!input.contains("/from") || !input.contains("/to")) {
//            throw new InvalidEventException();
//        }
//
//        String[] partsFrom = input.split("/from", 2);
//        String name = partsFrom[0].trim();
//
//        String[] partsTo = partsFrom[1].split("/to", 2);
//        String from = partsTo[0].trim();
//        String to = partsTo[1].trim();
//
//        Task t = new Events(name, from, to);
//        tasks.addTask(t);
//    }
//}
