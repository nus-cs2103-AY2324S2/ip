package tiny.lists;

import java.util.ArrayList;

import tiny.extensions.Trivia;

public class TriviaList {
    protected ArrayList<Trivia> trivias = new ArrayList<>();

    public void add(Trivia trivia) {
        trivias.add(trivia);
    }

    public void delete(Integer ind) {
        trivias.remove(trivias.get(ind));
    }

    public Trivia get(Integer ind) {
        return trivias.get(ind);
    }

    public Integer size() {
        return trivias.size();
    }

    /**
     * Lists out all the trivias in the list.
     *
     * @return String of all of the trivias.
     */
    public String list() {
        if (trivias.size() == 0) {
            return "You don't have any trivias!";
        }
        String output = "";
        for (int i = 0; i < trivias.size(); i++) {
            output += (i + 1) + ". " + trivias.get(i);
            output += "\n";
        }
        return output;
    }


    /**
     * Formats all the trivias into the correct format to save.
     *
     * @return ArrayList of trivias in the correct format to save.
     */
    public ArrayList<String> formatToSave() {
        ArrayList<String> toSave = new ArrayList<>();
        for (int i = 0; i < trivias.size(); i++) {
            toSave.add(trivias.get(i).formatToSave());
        }
        return toSave;
    }       
}
