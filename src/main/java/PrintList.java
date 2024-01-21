import java.util.ArrayList;

public class PrintList {
    private final String BORDER = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
    private ArrayList<String> printList;


    public PrintList() {
        this.printList = new ArrayList<String>();
    }
    
    public void add(String print) {
        this.printList.add(print);
    }

    public void print() {
        System.out.println(this.toString());
    }

    @Override
    public String toString() {
        String finalString = "";
        for (int i = 0; i < this.printList.size(); i++) {
            if (i == this.printList.size() - 1) {
                finalString += String.format("\t%s", this.printList.get(i));
            } else {
                finalString += String.format("\t%s\n", this.printList.get(i));
            }
        }
        this.printList.clear();
        return String.format("\t%s\n%s\n\t%s",
            this.BORDER,
            finalString,
            this.BORDER);
    }
}
