public class ToDoCommand extends Commands {
    private String[] words;
    public ToDoCommand(String[] words) {
        super();
        this.words = words;
    }
    @Override
    public boolean execute(TaskList tasks, UI ui, Storage storage) throws DukeException {
        if (words.length == 1) {
            throw new EmptyDescriptionException("todo");
        }
        Task newTask = new ToDo(words[1]);
        ui.displayAdd(tasks.addTask(newTask), tasks.list().size());
        storage.addToWriteFile(newTask);
        return false;
    }
}
