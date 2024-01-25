import java.util.ArrayList;

public class TaskList extends ArrayList<Task>{
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < this.size(); i++) {
            str.append(String.format("%d. %s", i + 1, this.get(i).toString()));
            if (i != this.size() - 1) {
                str.append("\n");
            }
        }

        return str.toString();
    }
}
