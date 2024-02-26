package gandalf.tasktypes;

/**
 * Tasks of type expenses
 */
public class Expenses extends Task {

    private double price;

    public Expenses(String taskName, String price) {
        super(taskName);
        this.price = Double.parseDouble(price);
    }

    public double getPrice() {
        return this.price;
    }

    @Override
    public void markStatus(boolean status) {
        System.out.println(getNameOfTask() + " is of type expenses and does not track marking or unmarking.");
    }

    @Override
    public String toString() {
        return "expenses " + getNameOfTask() + " " + price;
    }
}
