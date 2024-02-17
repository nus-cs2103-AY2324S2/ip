package tasklist.gui;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import tasklist.TaskList;
import tasklist.tasks.Deadline;
import tasklist.tasks.Event;
import tasklist.tasks.Task;

public class ScheduleWindow {
    @FXML
    private Text year;
    @FXML
    private Text month;
    @FXML
    private FlowPane calendar;
    @FXML
    private GridPane calendarGrid;

    private Stage stage; 
    private TaskList taskList;

    private LocalDateTime dateFocus;

    @FXML
    public void initialize() {
        dateFocus = LocalDateTime.now();
    }


     @FXML
    void backOneMonth(ActionEvent event) {
        dateFocus = dateFocus.minusMonths(1);
        calendarGrid.getChildren().clear();
        drawCalendar();
    }

    @FXML
    void forwardOneMonth(ActionEvent event) {
        dateFocus = dateFocus.plusMonths(1);
        calendarGrid.getChildren().clear();
        drawCalendar();
    }

    public void setStage(Stage stage, TaskList taskList) {
        this.stage = stage;
        this.taskList = taskList;
        drawCalendar();
    }

    private void drawCalendar() {
        int currentYear = dateFocus.getYear();
        int currentMonth = dateFocus.getMonthValue();
        year.setText(String.valueOf(currentYear));
        month.setText(String.valueOf(dateFocus.getMonth()));

        LocalDate firstDayofMonth = LocalDate.of(currentYear,currentMonth,1);
        int startDayOfWeek = firstDayofMonth.getDayOfWeek().getValue();
        int daysInMonth = firstDayofMonth.lengthOfMonth();

        calendarGrid.getChildren().clear();

        int row = 0;
        int col = startDayOfWeek;

        for (int day = 1; day <= daysInMonth; day++) {
            Label dayLabel = new Label(String.valueOf(day));
            dayLabel.setAlignment(Pos.TOP_CENTER); 
            dayLabel.setMaxHeight(Double.MAX_VALUE); 
            dayLabel.setMaxWidth(Double.MAX_VALUE); 
            dayLabel.setPadding(new Insets(15.0, 0.0, 0.0, 0.0)); 

            VBox newVBox = new VBox();
            newVBox.getChildren().add(dayLabel);

            calendarGrid.add(newVBox, col, row);

            col++;
            
            if (col == 7) { 
                col = 0;
                row++;
            }
        }
        
        if (this.taskList==null) {
            return;
        }
        ArrayList<Deadline> deadlines = this.taskList.getDeadlines();
        ArrayList<Event> events = this.taskList.getEvents();
        
        for (int i = 0; i < deadlines.size(); i++) { 
            LocalDateTime byDate = deadlines.get(i).getDeadlineDate();
            if (byDate.getMonth() != dateFocus.getMonth()) {
                continue;
            }
            Label deadlineLabel = new Label(deadlines.get(i).toString());
            int deadlineDayOfWeek = (byDate.getDayOfWeek().getValue()) % 7;
            int deadlineRow = getRowNumber(byDate);
            
            if (deadlines.get(i).getStatus()) {
                deadlineLabel.setStyle("-fx-background-color: #AFE1AF;");
            } else {
                deadlineLabel.setStyle("-fx-background-color: #FF474C;");
            }

            VBox node = getVBoxFromGridPane(calendarGrid, deadlineDayOfWeek, deadlineRow);
            node.getChildren().add(deadlineLabel);
        }
    
        

        for (int i = 0; i < events.size(); i++) { 
            Event theEvent = events.get(i);
            LocalDateTime fromDate = theEvent.getFromDate();
            LocalDateTime toDate = theEvent.getToDate();
            if (fromDate.getMonthValue() != dateFocus.getMonthValue() && toDate.getMonthValue() != dateFocus.getMonthValue()) {
                continue;
            }

            if (fromDate.getMonth() == dateFocus.getMonth()) {
                int fromDayOfWeek = (fromDate.getDayOfWeek().getValue()) % 7;
                int fromDateRow = getRowNumber(fromDate);
                VBox node = getVBoxFromGridPane(calendarGrid, fromDayOfWeek, fromDateRow);
                Label fromEventLabel = new Label(theEvent.toString());
                fromEventLabel.setStyle("-fx-background-color: #ADDFFF;");
                node.getChildren().add(fromEventLabel);
                }
            
            if (toDate.getMonth() == dateFocus.getMonth()) {
                int toDayOfWeek = toDate.getDayOfWeek().getValue() % 7;
                int toDateRow = getRowNumber(toDate);
                VBox node = getVBoxFromGridPane(calendarGrid, toDayOfWeek, toDateRow);
                Label toEventLabel = new Label(theEvent.toString());
                toEventLabel.setStyle("-fx-background-color: #ADDFFF;");
                node.getChildren().add(toEventLabel);
            }
               
        }
         
    }
    
    private VBox getVBoxFromGridPane(GridPane gridPane, int col, int row) {
        for (Node node : gridPane.getChildren()) {
            if (GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row && node instanceof VBox) {
                return (VBox) node;
            }
        }
        return null;
    }

    
    public static int getRowNumber(LocalDateTime givenDate) {
        int rowNo = (givenDate.toLocalDate().getDayOfMonth()-1) / 7;
        LocalDate firstDayOfMonth = LocalDate.of(givenDate.getYear(), givenDate.getMonthValue(), 1);
        int firstDayOfWeek = firstDayOfMonth.getDayOfWeek().getValue();
        int weekday = givenDate.getDayOfWeek().getValue();
        if (weekday == 7) {
            weekday = 0;
        }
        if (weekday < firstDayOfWeek) {
            rowNo++;
        }

        return rowNo;
    }
}
    
