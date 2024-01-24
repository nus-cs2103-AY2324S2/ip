package chatbot;

public class TodoTask extends Task{

    public TodoTask(String desc) {
        super(desc);
    }
    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
