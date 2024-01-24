public abstract class Item {
    private boolean isDone = false;
    private String name;

    public abstract void markDone();

    public abstract void markUndone();

    String doneMessage() {
        return "Nice! I've marked this task as done:\n     " +
                this.toString();
    }

    String undoneMessage() {
        return "OK, I've marked this task as not done yet:\n     " +
                this.toString();
    }

    String printChecked(boolean b) {
        return b ? "X" : " ";
    }

    String addMessage(int num) {
        return "Got it. I've added this task:\n" +
                "       " + this.toString() +
                "\n     Now you have " + num +  " tasks in the list.";
    }

    String removeMessage(int num) {
        return "Noted. I've removed this task:\n" +
                "       " + this.toString() +
                "\n     Now you have " + num +  " tasks in the list.";
    }


    @Override
    public abstract String toString();
}
