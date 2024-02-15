package tiny.lists;

import java.util.ArrayList;

import tiny.extensions.Merchandise;

/**
 * Manages all the merchandises.
 */
public class MerchandiseList {
    protected ArrayList<Merchandise> merchandises = new ArrayList<>();

    public void add(Merchandise merchandise) {
        merchandises.add(merchandise);
    }

    public void delete(Integer ind) {
        merchandises.remove(merchandises.get(ind));
    }

    public Merchandise get(Integer ind) {
        return merchandises.get(ind);
    }

    public Integer size() {
        return merchandises.size();
    }

    /**
     * Lists out all the merchandises in the list.
     *
     * @return String of all of the merchandises.
     */
    public String list() {
        if (merchandises.size() == 0) {
            return "You don't have any merchandises!";
        }
        String output = "";
        for (int i = 0; i < merchandises.size(); i++) {
            output += (i + 1) + ". " + merchandises.get(i);
            output += "\n";
        }
        return output;
    }


    /**
     * Formats all the merchandises into the correct format to save.
     *
     * @return ArrayList of merchandises in the correct format to save.
     */
    public ArrayList<String> formatToSave() {
        ArrayList<String> toSave = new ArrayList<>();
        for (int i = 0; i < merchandises.size(); i++) {
            toSave.add(merchandises.get(i).formatToSave());
        }
        return toSave;
    }
}
