public class EventCommand extends Commands {
    private String[] words;
    public EventCommand(String[] words) {
        super();
        this.words = words;
    }
    @Override
    public boolean execute(TaskList tasks, UI ui, Storage storage) throws DukeException {
        if (words.length == 1) {
            throw new EmptyDescriptionException("event");
        }
        int startIdx = 0;
        boolean haveTime = false;
        while (startIdx < words[1].length()) {
            if (words[1].charAt(startIdx) != '/') {
                startIdx++;
            } else {
                haveTime = true;
                break;
            }
        }
        if (!haveTime) {
            throw new InvalidEventException();
        }
        String[] dates = words[1].substring(startIdx).split("/from | /to ");
        if (dates.length != 3) {
            throw new InvalidEventException();
        }
        Task newTask = new Event(words[1].substring(0, startIdx),
                dates[1],
                dates[2]);
        ui.displayAdd(tasks.addTask(newTask), tasks.list().size());
        storage.addToWriteFile(newTask);
        return false;
    }
}
