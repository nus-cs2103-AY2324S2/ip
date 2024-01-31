public class Task {
    protected String description;
    protected boolean isDone;

    protected int indexToMarkOrUnmark;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public boolean isMark() {
        String[] items = this.description.split(" ");
        if (items[0].equals("mark")) {
            indexToMarkOrUnmark = Integer.parseInt(items[1]) - 1;
            return true;
        } else {
            return false;
        }
    }

    public boolean isUnmark() {
        String[] items = this.description.split(" ");
        if (items[0].equals("unmark")) {
            indexToMarkOrUnmark = Integer.parseInt(items[1]) - 1;
            return true;
        } else {
            return false;
        }
    }

    public void markComplete() {
        this.isDone = true;
    }

    public void markIncomplete() {
        this.isDone = false;
    }
//
//    @Override
//    public String toString() {
//        String str;
//        if (this.isList()) {
//            System.out.println("____________________________________________________________\n");
//            for (Integer i = 1; i <= listOfObj.size(); i++) {
//                System.out.println(i.toString() + ".[" + listOfObj.get(i-1).getStatusIcon() + "] " + listOfObj.get(i-1).description);
//            }
//            System.out.println("____________________________________________________________\n");
//        } else if (this.isMark()) {
////                String[] items = task.description.split(" ");
////                int indToMark = Integer.parseInt(items[1]);
//            listOfObj.get(task.indexToMarkOrUnmark).markComplete();
//            System.out.println("____________________________________________________________\n");
//            System.out.println("Nice! I've marked this task as done:");
//            System.out.println("[" + listOfObj.get(task.indexToMarkOrUnmark).getStatusIcon() + "] "
//                    + listOfObj.get(task.indexToMarkOrUnmark).description);
//            System.out.println("____________________________________________________________\n");
//        } else if (task.isUnmark()) {
////                String[] items = task.description.split(" ");
////                int indToMark = Integer.parseInt(items[1]);
//            listOfObj.get(task.indexToMarkOrUnmark).markIncomplete();
//            System.out.println("____________________________________________________________\n");
//            System.out.println("OK, I've marked this task as not done yet:");
//            System.out.println("[" + listOfObj.get(task.indexToMarkOrUnmark- 1).getStatusIcon() + "] "
//                    + listOfObj.get(task.indexToMarkOrUnmark).description);
//            System.out.println("____________________________________________________________\n");
//        } else {
//            listOfObj.add(task);
//            System.out.println("____________________________________________________________\n"
//                    + "added: " + task.description
//                    + "\n____________________________________________________________\n");
//        }
//    }


}
