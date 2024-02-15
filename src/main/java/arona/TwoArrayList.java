package arona;

import java.util.ArrayList;

public class TwoArrayList {
    private ArrayList<String> list1;
    private ArrayList<Boolean> list2;

    public TwoArrayList(ArrayList<String> list1, ArrayList<Boolean> list2) {
        this.list1 = list1;
        this.list2 = list2;
    }

    public ArrayList<String> getList1() {
        return list1;
    }

    public ArrayList<Boolean> getList2() {
        return list2;
    }
}
