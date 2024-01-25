public class Events extends Task{
    private String from;
    private String to;
    public Events(String event, String from, String to) {
        super(event);
        this.from = from;
        this.to = to;
    }

    @Override
    public String getTag() {
        return "[E]";
    }

    @Override
    public void printTaskDesc(int num, boolean isLast){
        if (!isLast) {
            if (num == 1) {
                System.out.print("      ________________________________________________________\n");
                System.out.printf("      Here are the tasks in your list: \n      %d.%s[%s] %s(from: %sto:%s)\n",
                        num, this.getTag(), this.getStatusIcon(), this.getDescription(), this.from, this.to);
            } else {
                System.out.printf("      %d.%s[%s] %s(from: %sto:%s)\n",
                        num, this.getTag(), this.getStatusIcon(), this.getDescription(), this.from, this.to);
            }
        } else {
            System.out.printf("      %d.%s[%s] %s(from: %sto:%s)\n",
                    num, this.getTag(), this.getStatusIcon(), this.getDescription(), this.from, this.to);
            System.out.print("      ________________________________________________________\n");
        }
    }

    @Override
    public void printFullDesc() {
        System.out.printf("         %s[%s] %s(from: %sto:%s)\n",
                this.getTag(), this.getStatusIcon(), this.getDescription(), this.from, this.to);
    }
}
