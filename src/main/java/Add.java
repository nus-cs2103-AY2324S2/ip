import java.util.ArrayList;

public class Add implements Command{

    private String text;

    public Add(String text) {
        this.text=text;
        Task.task_list.add(new Task(text));
    }
    @Override
    public void reply() {
        System.out.printf("    added: %s\n",this.text);
    }
}
