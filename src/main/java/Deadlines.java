public class Deadlines extends Task{
    private String date;
    public Deadlines(String task, String date) {
        super(task);
        this.date = date;
    }

    @Override
    public String getTag() {
        return "[D]";
    }

    @Override
    public void printTaskDesc(int num, boolean isLast){
        if (!isLast) {
            if (num == 1) {
                System.out.print("      ________________________________________________________\n");
                System.out.printf("      Here are the tasks in your list: \n      %d.%s[%s] %s(by: %s)\n",
                        num, this.getTag(), this.getStatusIcon(), this.getDescription(), this.date);
            } else {
                System.out.printf("      %d.%s[%s] %s(by: %s)\n",
                        num, this.getTag(), this.getStatusIcon(), this.getDescription(), this.date);
            }
        } else {
            System.out.printf("      %d.%s[%s] %s(by: %s)\n",
                    num, this.getTag(), this.getStatusIcon(), this.getDescription(), this.date);
            System.out.print("      ________________________________________________________\n");
        }
    }

    @Override
    public void printFullDesc() {
        System.out.printf("         %s[%s] %s(by: %s)\n",
                this.getTag(), this.getStatusIcon(), this.getDescription(), this.date);
    }
}
