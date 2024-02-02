public class Parser {
    public static Command parse(String userInput) throws PandaException {
        if(userInput.equals("bye")) {
            return new ExitCommand();
        }
        if(userInput.equals(("list"))) {
            return new PrintListCommand();
        }
        if(userInput.split(" ")[0].equals("mark")) {
            int target;
            try {
                target = Integer.parseInt(userInput.split(" ", 2)[1]);
            }
            catch (NumberFormatException e) {
                throw new InvalidFormatException();
            }
            return new AlterMarkCommand(target, true);
        }
        if(userInput.split(" ")[0].equals("unmark")) {
            int target;
            try {
                target = Integer.parseInt(userInput.split(" ", 2)[1]) - 1;
            }
            catch (NumberFormatException e) {
                throw new InvalidFormatException();
            }
            return new AlterMarkCommand(target, false);
        }
        if(userInput.split(" ")[0].equals("delete")) {
            int target;
            try {
                target = Integer.parseInt(userInput.split(" ", 2)[1]) - 1;
            }
            catch (NumberFormatException e) {
                throw new InvalidFormatException();
            }
            return new DeleteCommand(target);
        }
        if(userInput.split(" ")[0].equals("todo")) {
            String[] splitted = userInput.trim().split(" ", 2);
            if(splitted.length < 2) {
                throw new EmptyTodoException();
            }
            return new NewTaskCommand(new Todo(splitted[1].trim()));
        }
        if(userInput.split(" ")[0].equals("deadline")) {
            String[] splitted = userInput.trim().split(" ", 2);
            if(splitted.length < 2) {
                throw new EmptyDeadlineException("desc");
            }
            String[] args = splitted[1].split("/by");
            if(args.length < 2) {
                throw new EmptyDeadlineException("date");
            }
            return new NewTaskCommand(new Deadline(args[0].trim(), args[1].trim()));
        }
        if(userInput.split(" ")[0].equals("event")) {
            String[] splitted = userInput.trim().split(" ", 2);
            if(splitted.length < 2) {
                throw new EmptyEventException("desc");
            }
            String[] args = splitted[1].split("/from");
            if(args.length < 2 || args[1].split("/to").length < 2) {
                throw new EmptyEventException("date");
            }
            return new NewTaskCommand(new Event(args[0].trim(), args[1].split("/to")[0].trim(), args[1].split("/to")[1].trim()));
        }
        throw new UnknownCommandException();
    } 
}
