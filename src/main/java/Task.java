public class Task {
    private boolean isDone;
    private String content;

    public Task(String content){
        this.content = content;
        this.isDone = false;
    }

    public void mark(){
        this.isDone = true;
    }

    public void unmark(){
        this.isDone = false;
    }

    @Override
    public String toString(){
        String mark = isDone ? "X" : " ";
        return "[" + mark + "] " + this.content;
    }
}