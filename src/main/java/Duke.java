public class Duke {
    private static final String logo = " ____        _        \n"
                                + "|  _ \\ _   _| | _____ \n"
                                + "| | | | | | | |/ / _ \\\n"
                                + "| |_| | |_| |   <  __/\n"
                                + "|____/ \\__,_|_|\\_\\___|\n";

    private static final Ui ui = new Ui();
    private TaskList taskList = new TaskList();
    private Storage storage;

    Duke() {
        this.taskList = new TaskList();
        this.storage = new Storage();
        try {
            this.storage.loadData(this.taskList);
        } catch (Exception e) {
            ui.showErrorMessage(e);
        }
    }

    // private void add(Task task) {
    //     this.taskList.add(task);
    //     ui.showAddedTask(task, this.taskList.size());
    // }

    // private void list() {
    //     ui.showList(this.taskList.getList());
    // }

    // private void mark(String[] input) throws IncompleteCommandException, InvalidArgumentException {
    //     if (input.length == 1 || input[1].equals("")) {
    //         throw new IncompleteCommandException(input[0]);
    //     } 
    //     try {
    //         int index = Integer.parseInt(input[1]) - 1;
    //         Task task = this.taskList.mark(index);
    //         ui.showMarkedTask(task);
    //     } catch (IndexOutOfBoundsException e) {
    //         throw new InvalidArgumentException();
    //     }
    // }

    // private void unmark(String[] input) throws IncompleteCommandException, InvalidArgumentException {
    //     if (input.length == 1 || input[1].equals("")) {
    //         throw new IncompleteCommandException(input[0]);
    //     } 
    //     try {
    //         int index = Integer.parseInt(input[1]) - 1;
    //         Task task = this.taskList.unMark(index);
    //         task.markNotDone();
    //         ui.showUnmarkedTask(task);
    //     } catch (IndexOutOfBoundsException e) {
    //         throw new InvalidArgumentException();
    //     }
    // }

    // private void addTodo(String[] input) throws IncompleteCommandException {
    //     if (input.length == 1 || input[1].equals("")) {
    //         throw new IncompleteCommandException(input[0]);
    //     } 
    //     String description = input[1];
    //     this.add(new Todo(description));
    // }

    // private void addDeadline(String[] input) throws IncompleteCommandException, NoDeadlineException {
    //     if (input.length == 1 || input[1].equals("")) {
    //         throw new IncompleteCommandException(input[0]);
    //     } 
    //     input = input[1].split(" /by ", 2);
    //     if (input.length == 1) {
    //         throw new NoDeadlineException();
    //     }
    //     String description = input[0];
    //     String by = input[1];
    //     this.add(new Deadline(description, by));
    // }

    // private void addEvent(String[] input) throws IncompleteCommandException, NoPeriodException {
    //     if (input.length == 1 || input[1].equals("")) {
    //         throw new IncompleteCommandException(input[0]);
    //     } 
    //     input = input[1].split(" /from ", 2);
    //     if (input.length == 1) {
    //         throw new NoPeriodException();
    //     }
    //     String description = input[0];
    //     String[] arr = input[1].split(" /to ", 2);
    //     if (arr.length == 1) {
    //         throw new NoPeriodException();
    //     }
    //     this.add(new Event(description, arr[0], arr[1]));
    // }

    // private void delete(String[] input) throws IncompleteCommandException, InvalidArgumentException {
    //     if (input.length == 1 || input[1].equals("")) {
    //         throw new IncompleteCommandException(input[0]);
    //     } 
    //     try {
    //         int index = Integer.parseInt(input[1]) - 1;
    //         Task task = this.taskList.delete(index);
    //         ui.showDeletedTask(task, this.taskList.size());
    //     } catch (IndexOutOfBoundsException e) {
    //         throw new InvalidArgumentException();
    //     }
    // }

    public static void main(String[] args) {
        Duke duke = new Duke();
        ui.sayGreetings();
        boolean isExit = false;
        while (!isExit) {
            try {
                String userInput = ui.readCommand();
                Command command = Parser.parseCommand(userInput);
                command.execute(duke.taskList, ui);
                isExit = command.isExit();
            } catch (IllegalArgumentException e) {
                if (e.getMessage().contains("No enum constant Duke.Command.")) {
                    ui.showErrorMessage(new UnknownCommandException());
                } else {
                    ui.showErrorMessage(e);
                }
            } catch (Exception e) {
                ui.showErrorMessage(e);
            }
        }
        try {
            duke.storage.saveData(duke.taskList);
        } catch (Exception e) {
            ui.showErrorMessage(e);
        }
    }
}
