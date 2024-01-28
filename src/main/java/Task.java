import java.time.LocalDateTime;

public abstract class Task {
    private boolean isDone;
    private String content;

    public Task(String content){
        this.content = content;
        this.isDone = false;
    }

    public void mark(){
        this.isDone = true;
    }

    public LocalDateTime getDeadline(){
        return null;
    }

    public void unmark(){
        this.isDone = false;
    }

    public String getDescription(){
        return this.content;
    }

    abstract public String getType();

    public boolean getStatus() {
        return this.isDone;
    }

    @Override
    public String toString(){
        String mark = isDone ? "X" : " ";
        return "[" + mark + "] " + this.content;
    }
}