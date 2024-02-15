package tiny.lists;

import java.util.ArrayList;

import tiny.exceptions.TinyException;
import tiny.extensions.Client;
import tiny.extensions.Contact;
import tiny.extensions.Expense;
import tiny.extensions.LoanGiven;
import tiny.extensions.LoanTaken;
import tiny.extensions.Merchandise;
import tiny.extensions.Note;
import tiny.extensions.Place;
import tiny.extensions.Trivia;
import tiny.tasks.Deadline;
import tiny.tasks.Event;
import tiny.tasks.Todo;

/**
 * Manages all the lists.
 */
public class ControlList {
    protected ContactList contactList = new ContactList();
    protected ClientList clientList = new ClientList();
    protected ExpenseList expenseList = new ExpenseList();
    protected LoanGivenList loanGivenList = new LoanGivenList();
    protected LoanTakenList loanTakenList = new LoanTakenList();
    protected MerchandiseList merchandiseList = new MerchandiseList();
    protected NoteList noteList = new NoteList();
    protected PlaceList placeList = new PlaceList();
    protected TaskList taskList = new TaskList();
    protected TriviaList triviaList = new TriviaList();

    /**
     * Processes the saved data.
     *
     * @param data Data to load from the save file.
     */
    public void processData(ArrayList<String> data) throws TinyException {
        for (int i = 0; i < data.size(); i++) {
            String[] entry = data.get(i).split(" \\| ");
            String inputType = entry[0];
            if (isCategory(inputType, "T")) {
                Todo todo = new Todo(entry[2], !entry[1].equals("0"));
                taskList.add(todo);
            } else if (isCategory(inputType, "D")) {
                Deadline deadline = new Deadline(entry[2], !entry[1].equals("0"), entry[3]);
                taskList.add(deadline);
            } else if (isCategory(inputType, "E")) {
                Event event = new Event(entry[2], !entry[1].equals("0"), entry[3], entry[4]);
                taskList.add(event);
            } else if (isCategory(inputType, "CL")) {
                Client client = new Client(entry[1], entry[2]);
                clientList.add(client);
            } else if (isCategory(inputType, "CO")) {
                Contact contact = new Contact(entry[1], entry[2]);
                contactList.add(contact);
            } else if (isCategory(inputType, "EX")) {
                Expense expense = new Expense(entry[1], Double.parseDouble(entry[2]), entry[3]);
                expenseList.add(expense);
            } else if (isCategory(inputType, "LG")) {
                LoanGiven loanGiven = new LoanGiven(entry[1], Double.parseDouble(entry[2]), entry[3]);
                loanGivenList.add(loanGiven);
            } else if (isCategory(inputType, "LT")) {
                LoanTaken loanTaken = new LoanTaken(entry[1], Double.parseDouble(entry[2]), entry[3]);
                loanTakenList.add(loanTaken);
            } else if (isCategory(inputType, "ME")) {
                Merchandise merchandise = new Merchandise(entry[1], Integer.parseInt(entry[2]),
                        Integer.parseInt(entry[3]));
                merchandiseList.add(merchandise);
            } else if (isCategory(inputType, "NO")) {
                Note note = new Note(entry[1], entry[2]);
                noteList.add(note);
            } else if (isCategory(inputType, "PL")) {
                Place place = new Place(entry[1], entry[2]);
                placeList.add(place);
            } else if (isCategory(inputType, "TR")) {
                Trivia trivia = new Trivia(entry[1]);
                triviaList.add(trivia);
            }
        }
    }

    public ClientList getClientList() {
        return clientList;
    }

    public ContactList getContactList() {
        return contactList;
    }

    public ExpenseList getExpenseList() {
        return expenseList;
    }

    public LoanGivenList getLoanGivenList() {
        return loanGivenList;
    }

    public LoanTakenList getLoanTakenList() {
        return loanTakenList;
    }

    public MerchandiseList getMerchandiseList() {
        return merchandiseList;
    }

    public NoteList getNoteList() {
        return noteList;
    }

    public PlaceList getPlaceList() {
        return placeList;
    }

    public TaskList getTaskList() {
        return taskList;
    }

    public TriviaList getTriviaList() {
        return triviaList;
    }

    /**
     * Formats the data to be saved.
     */
    public ArrayList<String> formatToSave() {
        ArrayList<String> dataToSave = new ArrayList<>();
        dataToSave.addAll(clientList.formatToSave());
        dataToSave.addAll(contactList.formatToSave());
        dataToSave.addAll(expenseList.formatToSave());
        dataToSave.addAll(loanGivenList.formatToSave());
        dataToSave.addAll(loanTakenList.formatToSave());
        dataToSave.addAll(merchandiseList.formatToSave());
        dataToSave.addAll(noteList.formatToSave());
        dataToSave.addAll(placeList.formatToSave());
        dataToSave.addAll(taskList.formatToSave());
        dataToSave.addAll(triviaList.formatToSave());
        return dataToSave;
    }

    private boolean isCategory(String input, String category) {
        return input.equals(category);
    }
}
