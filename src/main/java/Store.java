import java.lang.StringBuilder;
public class Store {
    private Task[] tasks = new Task[100];
    private int size = 0;
    Store() {}
    public void add(Task t) {
        this.tasks[this.size] = t;
        this.size++;
    }

    public void delete(int i) {
        for (int j = i; j < this.size; j++) {
            this.tasks[j] = this.tasks[j + 1];
        }
        this.size--;
    }

    public int getSize() {
        return this.size;
    }

    public Task mark(int i) {
        return this.tasks[i - 1].mark();
    }

    public Task unmark(int i) {
        return this.tasks[i - 1].unmark();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.size; i++) {
            sb.append(i + 1).append(".").append(this.tasks[i].toString());
            if (i != this.size - 1) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }
}
