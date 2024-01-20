import java.util.ArrayList;

class Task {
    public static ArrayList<Task> task_list= new ArrayList<>();
    private String text;

    public Task(String text) {
        this.text=text;
    }

    @Override
    public String toString() {
        return text;
    }
}
