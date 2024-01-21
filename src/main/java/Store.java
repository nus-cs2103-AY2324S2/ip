import java.lang.StringBuilder;
public class Store {
    private String[] tasks = new String[100];
    private int size = 0;
    Store() {}
    public void add(String s) {
        this.tasks[this.size] = s;
        this.size++;
    }

    public void delete(int i) {
        for (int j = i; j < this.size; j++) {
            this.tasks[j] = this.tasks[j + 1];
        }
        this.size--;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.size; i++) {
            sb.append(i + 1).append(". ").append(this.tasks[i]);
            if (i != this.size - 1) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }
}
