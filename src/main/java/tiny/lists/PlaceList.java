package tiny.lists;

import java.util.ArrayList;

import tiny.extensions.Place;

public class PlaceList {
    protected ArrayList<Place> places = new ArrayList<>();

    public void add(Place place) {
        places.add(place);
    }

    public void delete(Integer ind) {
        places.remove(places.get(ind));
    }

    public Place get(Integer ind) {
        return places.get(ind);
    }

    public Integer size() {
        return places.size();
    }

    /**
     * Lists out all the places in the list.
     *
     * @return String of all of the places.
     */
    public String list() {
        if (places.size() == 0) {
            return "You don't have any places!";
        }
        String output = "";
        for (int i = 0; i < places.size(); i++) {
            output += (i + 1) + ". " + places.get(i);
            output += "\n";
        }
        return output;
    }


    /**
     * Formats all the places into the correct format to save.
     *
     * @return ArrayList of places in the correct format to save.
     */
    public ArrayList<String> formatToSave() {
        ArrayList<String> toSave = new ArrayList<>();
        for (int i = 0; i < places.size(); i++) {
            toSave.add(places.get(i).formatToSave());
        }
        return toSave;
    }       
}
