package tes.gui;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class HelpWindow {
    @FXML
    private TableView<UserGuideItem> userGuideTable;
    @FXML
    private TableColumn<UserGuideItem, String> commandColumn;
    @FXML
    private TableColumn<UserGuideItem, String> usageColumn;

    private final ObservableList<UserGuideItem> guideItems = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        commandColumn.setCellValueFactory(new PropertyValueFactory<>("command"));
        usageColumn.setCellValueFactory(new PropertyValueFactory<>("usage"));

        guideItems.add(new UserGuideItem("help", "Call this user guide.\n" + "help"));
        guideItems.add(new UserGuideItem("todo", "Add a todo task.\n"
                + "todo TASK_DESCRIPTION\n"
                + "e.g., todo mop the floor"));
        guideItems.add(new UserGuideItem("deadline", "Add a deadline task.\n"
                + "deadline TASK_DESCRIPTION /by DEADLINE\n"
                + "e.g., deadline return book /by 2024-02-12 1900"));
        guideItems.add(new UserGuideItem("event", "Add a event task.\n"
                + "event TASK_DESCRIPTION /from STARTING_TIME /to ENDING_TIME\n"
                + "e.g., event prom night /from 2024-02-19 1900 /to 2024-02-19 2300"));
        guideItems.add(new UserGuideItem("list", "List the tasks stored.\n" + "list"));
        guideItems.add(new UserGuideItem("mark", "Mark a task as done.\n"
                + "mark INDEX\n"
                + "e.g., mark 1"));
        guideItems.add(new UserGuideItem("unmark", "Unmark a task to show incomplete status.\n"
                + "unmark INDEX\n"
                + "e.g., unmark 1"));
        guideItems.add(new UserGuideItem("delete", "Delete task.\n"
                + "delete INDEX\n"
                + "delete 1"));
        guideItems.add(new UserGuideItem("find", "Find a task with a keyword.\n"
                + "find KEYWORD\n"
                + "find mop"));
        guideItems.add(new UserGuideItem("bye", "Exit the chatbot.\n" + "bye"));

        userGuideTable.setItems(guideItems);
    }
}
