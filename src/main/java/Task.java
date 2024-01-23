public class Task {
    private final static String hRULER = "____________________________________________________________\n";
    private final String description;
    private String status = "[ ]";


    public Task(String token) {
        this.description = token;
    }

    public void markDone() {
        this.status = "[X]";
        System.out.printf("%s Nice! I've marked this task as done:\n   %s\n%s",
                hRULER, this.toString(), hRULER);
    }

    public void unMarkDone() {
        this.status = "[ ]";
        System.out.printf("%s OK, I've marked this task as not done yet:\n   %s\n%s",
                hRULER, this.toString(), hRULER);
    }
    @Override
    public String toString() {
        return String.format("%s %s", this.status, this.description);
    }
}
