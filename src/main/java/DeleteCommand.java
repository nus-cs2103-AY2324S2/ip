public class DeleteCommand extends Commands {
    private String[] words;
    private static boolean isNumeric(String s) {
        if (s == null) {
            return false;
        }
        try {
            Integer.parseInt(s);
        } catch(NumberFormatException e) {
            return false;
        }
        return true;
    }
    public DeleteCommand(String[] words) {
        super();
        this.words = words;
    }
    @Override
    public boolean execute(TaskList tasks, UI ui, Storage s) throws DukeException {
        int currentIdx = tasks.list().size();
        if (words.length == 1 || !isNumeric(words[1])) {
            throw new InvalidTaskIndexException(currentIdx);
        }
        int taskIdx2 = Integer.parseInt(words[1]) - 1;
        if (taskIdx2 >= currentIdx || taskIdx2 < 0) {
            throw new InvalidTaskIndexException(currentIdx);
        }
        currentIdx--;
        ui.displayDelete(tasks.delete(taskIdx2), currentIdx);
        s.rewriteFile(tasks.list());
        return false;
    }
}
