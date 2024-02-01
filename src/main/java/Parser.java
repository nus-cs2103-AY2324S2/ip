public class Parser {
    protected String command;

    public Parser (String command) {
        this.command = command;
    }

    public Task parseAddToDo() throws DukeException {
        if (command.toUpperCase().startsWith(Duke.Command.TODO.name())) {
            return this.parseTodo();
        } else if (command.toUpperCase().startsWith(Duke.Command.DEADLINE.name())) {
            return this.parseDueDate();
        } else if (command.toUpperCase().startsWith(Duke.Command.EVENT.name())) {
            return this.parseSchedule();
        } else {
            throw new DukeException("Invalid format. Please use 'todo', 'deadline', or 'event'.");
        }
    }

    public int parseDeleteToDo() throws DukeException{
        String[] part = command.split(" ");

        if (part.length == 2) {
            String index = part[1].trim();

            try {
                return Integer.parseInt(index) - 1;
            } catch (NumberFormatException e) {
                throw new DukeException("Invalid format. Please use integers only.");
            }
        } else {
            throw new DukeException("Invalid format. Please use 'delete <index>'.");
        }
    }

    public int parseMarkToDo() throws DukeException{
        String[] part = command.split(" ");

        if (part.length == 2) {
            String index = part[1].trim();
            try {
                return Integer.parseInt(index) - 1;
            } catch (NumberFormatException e) {
                throw new DukeException("Invalid format. Please use integers only.");
            }

        } else {
            throw new DukeException("Invalid format. Please use 'mark <index>'.");
        }
    }

    public int parseUnMarkToDo() throws DukeException{
        String[] part = command.split(" ");

        if (part.length == 2) {
            String index = part[1].trim();
            try {
                return Integer.parseInt(index) - 1;
            } catch (NumberFormatException e) {
                throw new DukeException("Invalid format. Please use integers only.");
            }

        } else {
            throw new DukeException("Invalid format. Please use 'unmark <index>'.");
        }
    }


    public Event parseSchedule() throws DukeException {
        String input = command.substring(Duke.Command.EVENT.name().length()).trim();
        int byIndex = input.indexOf(" /from ");
        if (byIndex != 0 && byIndex != -1) {
            String description = input.substring(0, byIndex).trim();
            String fromTo = input.substring(byIndex + 6).trim();
            String[] part = fromTo.split(" /to ", 2);
            if (part.length == 2) {
                String from = part[0].trim();
                String to = part[1].trim();
                return new Event(description, from, to);
            }
        }
        throw new DukeException("Invalid format. Please use 'event <description> /from <datetime> /to <datetime>'.\n" +
                " Datetime format: <yyyy-MM-dd HH:mm>.");
    }

    public Todo parseTodo() throws DukeException{
        String input = command.substring(Duke.Command.TODO.name().length()).trim();
        String description = input.trim();

        if (input.isEmpty()) {
            throw new DukeException("Invalid format. Please use 'todo <description>'.");
        }

        return new Todo(description);
    }

    public Deadline parseDueDate() throws DukeException{
        String input = command.substring(Duke.Command.DEADLINE.name().length()).trim();
        int byIndex = input.indexOf(" /by ");
        if ( byIndex != 0 && byIndex != -1) {
            String description = input.substring(0, byIndex).trim();
            String by = input.substring(byIndex + 4).trim();
            return new Deadline(description, by);
        } else {
            throw new DukeException("Invalid format. Please use 'deadline <description> /by <datetime>'.\n" +
                    " Datetime format: <yyyy-MM-dd HH:mm>.");
        }
    }
}
