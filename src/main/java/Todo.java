import java.time.LocalDateTime;

public class Todo extends Task{
    public Todo(String content) {
        super(content);
    }

    public String getType(){
        return "T";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    public String saveFormat(){
        return this.getType() + " | " + (this.getStatus() ? "1" : "0") + " | " + this.getDescription();
    }
}
