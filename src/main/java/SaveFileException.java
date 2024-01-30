public class SaveFileException extends CheckbotException {
    public SaveFileException() {
        super("I am unable to save your todo list as a file. Your changes were not saved.");
    }
}
