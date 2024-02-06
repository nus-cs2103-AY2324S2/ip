import java.time.LocalDate;
import java.util.ArrayList;

class TaskList extends ArrayList<Task>{

    TaskList() {
        super();
    }

    // TODO: Is this a maintainable way of doing things?
    public void add(String task) {
        super.add(new Todo(task, TaskStatus.UNMARKED));
    }

    public void add(String task, LocalDate by) {
        super.add(new Deadline(task, TaskStatus.UNMARKED, by));
    }

    public void add(String task, LocalDate from, LocalDate to) {
        super.add(new Event(task, TaskStatus.UNMARKED, from, to));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.size(); i++) {
            sb.append(i + 1).append(". ").append(this.get(i)).append("\n");
        }
        return sb.toString();
    }
}