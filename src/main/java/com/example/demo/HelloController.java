package com.example.demo;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class HelloController {
    ToggleGroup toggleGroup;
    @FXML
    private Label welcomeText;

    @FXML
    private TableView<ObservableList<String>> dataTable;

    @FXML
    private FlowPane colNameContainer;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    @FXML
    protected void handleFileSelection() {
        toggleGroup = new ToggleGroup();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open CSV File");
        File selectedFile = fileChooser.showOpenDialog(welcomeText.getScene().getWindow());
        if (selectedFile != null) {
            try (BufferedReader br = new BufferedReader(new FileReader(selectedFile))) {
                String line;
                String headersLine = br.readLine();
                String[] headers = headersLine.split(",");
                dataTable.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
                for (int i = 0; i < headers.length; i++) {

                    final int colIndex = i;
                    javafx.scene.control.TableColumn<ObservableList<String>, String> column = new javafx.scene.control.TableColumn<>(headers[i]);
                    column.setCellValueFactory(cellData ->
                            new SimpleStringProperty(cellData.getValue().get(colIndex))
                    );
                    column.setSortable(false);
                    dataTable.getColumns().add(column);
                }
                boolean firstLine = true;
                while ((line = br.readLine()) != null) {
                    System.out.println(line);
                    System.out.println("hello");
                    String[] rowValues = line.split(",");

                    if(firstLine){
                        for(int i = 0; i < rowValues.length; i++){
                            try{
                                Double.parseDouble(rowValues[i]);
                                RadioButton radBtn = new RadioButton(headers[i]);
                                radBtn.setToggleGroup(toggleGroup);
                                colNameContainer.getChildren().add(radBtn);
                            }catch(NumberFormatException e){
                            }
                        }
                        firstLine = false;
                    }
                    ObservableList<String> row = FXCollections.observableArrayList(Arrays.asList(rowValues));
                    dataTable.getItems().add(row);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}