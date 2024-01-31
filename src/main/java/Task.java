public class Task {
    protected String description;
    protected boolean isDone;
    
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }
    
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }
    
    public String getDescription() {
        return this.description;
    }
    
    public void markDone() {
        this.isDone = true;
    }
    
    public void markUndone() {
        this.isDone = false;
    }
    
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.getDescription();
    }
    
    public static Task parseStringToTask(String line) {
        char typeOfTask = line.charAt(1);
        char status = line.charAt(4);
        Task t = null;
        if (typeOfTask == 'T') {
            String description = line.substring(7);
            t = new ToDos(description);
        } else if (typeOfTask == 'D') {
            int indexOfTime = line.indexOf("(by: ");
            String description = line.substring(7, indexOfTime - 1);
            String by = line.substring(indexOfTime + 5, line.indexOf(")"));
            t = new Deadlines(description, by);
        } else if (typeOfTask == 'E') {
            int indexOfStartTime = line.indexOf("(from");
            int indexOfEndTime = line .indexOf("to");
            String description = line.substring(7, indexOfStartTime - 1);
            String start = line.substring(indexOfStartTime + 6, indexOfEndTime - 1);
            String end = line.substring(indexOfEndTime + 3, line.indexOf(")"));
            t = new Events(description, start, end);
        }
        t.isDone = status == 'X';
        return t;
    }
}
