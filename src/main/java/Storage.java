import java.util.ArrayList;

/* Handles storages */
public class Storage {
    // ArrayList of all strings stored
    private ArrayList<String> mList;

    // Return the strings stored as an ArrayList
    public ArrayList<String> Get() {
        if (mList == null) mList = new ArrayList<String>();
        return mList;
    }

    // Return the strings stored as an ArrayList
    public void Store(String _string) {
        if (mList == null) mList = new ArrayList<String>();
        mList.add(_string);
    }
}
