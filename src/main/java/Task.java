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

    public void flip() {
        if (this.status == 0) {
            this.status = 1;
        } else {
            this.status = 0;
        }
    }

    public int getStatus() {
        return this.status;
    }

    public String getDesc() {
        return this.desc;
    }

    public String statusMessage() {
        return "";
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
