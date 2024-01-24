public class Task {
    private String desc;
    private int status; // 0 incomplete, 1 complete

    public Task(String d, int s) {
        this.desc = d;
        this.status = s;
    }

    public void update(int i) {
        this.status = i;
    }

    public int getStatus() {
        return this.status;
    }

    public String getDesc() {
        return this.desc;
    }

    @Override
    public String toString() {
        if (this.status == 0) {
            return "[ ] " + this.desc;
        } else {
            return "[X] " + this.desc;
        }
    }
}
