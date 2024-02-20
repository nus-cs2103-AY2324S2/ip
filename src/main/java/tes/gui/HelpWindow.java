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
    private TableColumn<UserGuideItem, String> formatColumn;

    private final ObservableList<UserGuideItem> guideItems = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        commandColumn.setCellValueFactory(new PropertyValueFactory<>("command"));
        formatColumn.setCellValueFactory(new PropertyValueFactory<>("description"));

        guideItems.add(new UserGuideItem("/help", "Show help window"));
        guideItems.add(new UserGuideItem("/bye", "Exit the chatbot"));

        userGuideTable.setItems(guideItems);
    }
}
