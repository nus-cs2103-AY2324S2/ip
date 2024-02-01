public class Events extends Task{
    private String from;
    private String to;
    public Events(String event, String from, String to) {
        super(event);
        this.from = from.trim();
        this.to = to.trim();
    }

    @Override
    public String getTag() {
        return "E";
    }

    @Override
    public String toStore() {
        return " E | " + (this.isDone ? "1" : "0") +  " | "  + this.description + " | " + this.from
                    + " | " + this.to + "\n";
    }

    @Override
    public void printTaskDesc(int num, boolean isLast){
        if (!isLast) {
            if (num == 1) {
                System.out.print("      ________________________________________________________\n");
                System.out.printf("      Here are the tasks in your list:\n      %d.[%s][%s] %s (from: %s to: %s)\n",
                        num, this.getTag(), this.getStatusIcon(), this.getDescription(), this.from, this.to);
            } else {
                System.out.printf("      %d.[%s][%s] %s (from: %s to: %s)\n",
                        num, this.getTag(), this.getStatusIcon(), this.getDescription(), this.from, this.to);
            }
        } else {
            if (num == 1) {
                System.out.print("      ________________________________________________________\n");
                System.out.printf("      Here are the tasks in your list:\n      %d.[%s][%s] %s (from: %s to: %s)\n",
                        num, this.getTag(), this.getStatusIcon(), this.getDescription(), this.from, this.to);
                System.out.print("      ________________________________________________________\n");

            } else {
                System.out.printf("      %d.[%s][%s] %s (from: %s to: %s)\n",
                        num, this.getTag(), this.getStatusIcon(), this.getDescription(), this.from, this.to);
                System.out.print("      ________________________________________________________\n");
            }
        }
    }

    @Override
    public void printFullDesc() {
        System.out.printf("         [%s][%s] %s (from: %s to: %s)\n",
                this.getTag(), this.getStatusIcon(), this.getDescription(), this.from, this.to);
    }
}
