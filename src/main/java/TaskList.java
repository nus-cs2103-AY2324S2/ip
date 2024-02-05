public class TaskList {

    private String[] list;
    private int count;

    public TaskList(){
        list = new String[100];
        count = 0;
    }

    public String add_task(String msg){

        if (this.count >= 100) {
            return "\t-----------------------------------\n" +
                    "\tSorry, the list is full. Max size is 100.\n" +
                    "\t-----------------------------------";
        }

        list[count] = msg;
        count++;

        return "\t-----------------------------------\n" +
                "\tadded: " + msg + "\n" +
                "\t-----------------------------------";
    }


    @Override
    public String toString() {
        String result = "\t-----------------------------------\n";
        for (int i = 0; i < count; i++) {
            result += "\t" + (i + 1) + ". " + list[i] + "\n";
        }
        result += "\t-----------------------------------";
        return result;
    }

}
