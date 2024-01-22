public class Task {
    private boolean status;
    private String desc;

    public Task(String desc) {
        this.status = false;
        this.desc = desc;
    }

    public String getDesc() {
        return this.desc;
    }
}
