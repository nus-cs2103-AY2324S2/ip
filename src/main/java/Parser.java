public class Parser {
    public static String[] parseSavedTask(String taskString) {
        return taskString.split(" \\| ");
    }
}
