package ChatbotRan;

public class Todo extends Task{
    public Todo(String contents) {
        super(contents);
    }
    public static Todo parse(String line, int space) {
        String[] texts = Task.parse(line, space);
        if (texts != null) {
            return new Todo(texts[0]);
        }
        return null;
    }
    @Override
    String getType() {
        return "T";
    }
}
