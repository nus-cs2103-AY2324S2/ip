public class DeadlineCommand extends Commands {
    private String[] words;
    public DeadlineCommand(String[] words) {
        super();
        this.words = words;
    }
    @Override
    public boolean execute(TaskList tasks, UI ui, Storage storage) throws DukeException {
        if (words.length == 1) {
            throw new EmptyDescriptionException("deadline");
        }
        int deadlineStartIdx = 0;
        boolean foundTime = false;
        while (deadlineStartIdx < words[1].length()) {
            if (words[1].charAt(deadlineStartIdx) != '/') {
                deadlineStartIdx++;
            } else {
                foundTime = true;
                break;
            }
        }
        if (!foundTime) {
            throw new InvalidDeadlineException();
        }
        Task newTask = new Deadline(words[1].substring(0, deadlineStartIdx),
                words[1].substring(deadlineStartIdx + 4));
        ui.displayAdd(tasks.addTask(newTask), tasks.list().size());
        storage.addToWriteFile(newTask);
        return false;
    }
}
