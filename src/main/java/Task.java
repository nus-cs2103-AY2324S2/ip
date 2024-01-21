class Task {

    private boolean done = false;
    private String name = "";
    private static int totaltask = 0;
    public Task(String name, boolean done) {
        this.name = name;
        this.done = done;
        totaltask +=1;
    }

    public String getName() {
        return this.name;
    }

    public String isDone() {
        if (this.done) {
            return "X";
        } else {
            return " ";
        }
    }

    public void dotask() {
        this.done = true;
    }

    public void undotask() {
        this.done = false;
    }

    @Override
    public String toString() {
        return " [" + isDone() + "] "+ this.name ;
    }

    public static String getTotalTask() {
        return Integer.toString(totaltask);
    }

    public static void subtractTotal() {
        totaltask -= 1;
    }
}
