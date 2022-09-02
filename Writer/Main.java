package sample;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.Button;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.List;
import java.util.ArrayList;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class Main extends Application {

    private TableView<Person> table;
    private ObservableList<Person> data;
    private Text actionStatus;

    public static void main(String [] args) {

        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("Таблица Писатели");

        // Books label
        Label label = new Label("Писатели");
        label.setTextFill(Color.DARKBLUE);
        label.setFont(Font.font("Calibri", FontWeight.BOLD, 36));
        HBox labelHb = new HBox();
        labelHb.setAlignment(Pos.CENTER);
        labelHb.getChildren().add(label);

        // Table view, data, columns and properties

        table = new TableView<>();
        data = getInitialTableData();
        table.setItems(data);
        table.setEditable(true);

        TableColumn titleCol = new TableColumn("Имя");
        titleCol.setCellValueFactory(new PropertyValueFactory<Person, String>("firstName"));
        titleCol.setCellFactory(TextFieldTableCell.forTableColumn());
        titleCol.setOnEditCommit(new EventHandler<CellEditEvent<Person, String>>() {
            @Override
            public void handle(CellEditEvent<Person, String> t) {

                ((Person) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                ).setFirstName(t.getNewValue());
            }
        });

        TableColumn authorCol = new TableColumn("Фамилия");
        authorCol.setCellValueFactory(new PropertyValueFactory<Person, String>("lastName"));
        authorCol.setCellFactory(TextFieldTableCell.forTableColumn());
        authorCol.setOnEditCommit(new EventHandler<CellEditEvent<Person, String>>() {
            @Override
            public void handle(CellEditEvent<Person, String> t) {

                ((Person) t.getTableView().getItems().get(
                        t.getTablePosition().getRow())
                ).setLastName(t.getNewValue());
            }
        });

        table.getColumns().setAll(titleCol, authorCol);
        table.setPrefWidth(450);
        table.setPrefHeight(300);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        table.getSelectionModel().selectedIndexProperty().addListener(
                new RowSelectChangeListener());

        // Add and delete buttons
        Button addbtn = new Button("Добавить");
        addbtn.setOnAction(new AddButtonListener());
        Button delbtn = new Button("Удалить");
        delbtn.setOnAction(new DeleteButtonListener());
        HBox buttonHb = new HBox(10);
        buttonHb.setAlignment(Pos.CENTER);
        buttonHb.getChildren().addAll(addbtn, delbtn);

        // Status message text
        actionStatus = new Text();
        actionStatus.setFill(Color.FIREBRICK);

        // Vbox
        VBox vbox = new VBox(20);
        vbox.setPadding(new Insets(25, 25, 25, 25));;
        vbox.getChildren().addAll(labelHb, table, buttonHb, actionStatus);

        // Scene
        Scene scene = new Scene(vbox, 500, 550); // w x h
        primaryStage.setScene(scene);
        primaryStage.show();

        // Select the first row
        table.getSelectionModel().select(0);
        Person book = table.getSelectionModel().getSelectedItem();
        actionStatus.setText(book.toString());

    } // start()

    private class RowSelectChangeListener implements ChangeListener<Number> {

        @Override
        public void changed(ObservableValue<? extends Number> ov,
                            Number oldVal, Number newVal) {

            int ix = newVal.intValue();
            if ((ix < 0) || (ix >= data.size())) {
                return;
            }

            Person book = data.get(ix);
            actionStatus.setText(book.toString());
        }
    }

    private ObservableList<Person> getInitialTableData() {

        List<Person> list = new ArrayList<>();

        list.add(new Person("Антон", "Чехов"));
        list.add(new Person("Николай", "Гоголь"));
        list.add(new Person("Александр", "Пушкин"));
        list.add(new Person("Михаил", "Лермонтов"));
        list.add(new Person("Лев", "Толстой"));
        list.add(new Person("Максим", "Горький"));
        list.add(new Person("Сергей", "Довлатов"));
        list.add(new Person("Валентин", "Пикуль"));

        ObservableList<Person> data = FXCollections.observableList(list);

        return data;
    }

    private class AddButtonListener implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent e) {

            // Create a new row after last row
            Person person = new Person (".", ".");
            data.add(person);
            int row = data.size() - 1;

            // Select the new row
            table.requestFocus();
            table.getSelectionModel().select(row);
            table.getFocusModel().focus(row);

            actionStatus.setText("Новый писатель: Введите имя и фамилию. Нажмите <Enter>.");
        }
    }

    private class DeleteButtonListener implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent e) {

            // Get selected row and delete
            int ix = table.getSelectionModel().getSelectedIndex();
            Person person = (Person) table.getSelectionModel().getSelectedItem();
            data.remove(ix);
            actionStatus.setText("Deleted: " + person.toString());

            // Select a row

            if (table.getItems().size() == 0) {
                actionStatus.setText("No data in table !");
                return;
            }

            if (ix != 0) ix -= 1;

            table.requestFocus();
            table.getSelectionModel().select(ix);
            table.getFocusModel().focus(ix);
        }
    }
}
