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
import java.util.ArrayList;
import java.util.Arrays;

public class HelloController {
    int column;
    ToggleGroup toggleGroup;
    DataSet data = new DataSet();
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
    protected void shellSort() {
//        dataTable.getColumns().clear();
        dataTable.getItems().clear();
        ArrayList<DataSet.SingleDataFormat> result = data.shellSort(column);

        for(int i = 0; i < result.size(); i++){
//            System.out.println(data.data.get(result.get(i).index).value[column]);
            ObservableList<String> row = FXCollections.observableArrayList(Arrays.asList(data.data.get(result.get(i).index).value));
            dataTable.getItems().add(row);
        }
    } @FXML
    protected void mergeSort() {
//        dataTable.getColumns().clear();
        dataTable.getItems().clear();
        ArrayList<DataSet.SingleDataFormat> result = data.mergeSort(column);

        for(int i = 0; i < result.size(); i++){
//            System.out.println(data.data.get(result.get(i).index).value[column]);
            ObservableList<String> row = FXCollections.observableArrayList(Arrays.asList(data.data.get(result.get(i).index).value));
            dataTable.getItems().add(row);
        }
    } @FXML
    protected void quickSort() {
//        dataTable.getColumns().clear();
        dataTable.getItems().clear();
        ArrayList<DataSet.SingleDataFormat> result = data.quickSort(column);

        for(int i = 0; i < result.size(); i++){
//            System.out.println(data.data.get(result.get(i).index).value[column]);
            ObservableList<String> row = FXCollections.observableArrayList(Arrays.asList(data.data.get(result.get(i).index).value));
            dataTable.getItems().add(row);
        }
    } @FXML
    protected void heapSort() {
//        dataTable.getColumns().clear();
        dataTable.getItems().clear();
        ArrayList<DataSet.SingleDataFormat> result = data.heapSort(column);

        for(int i = 0; i < result.size(); i++){
//            System.out.println(data.data.get(result.get(i).index).value[column]);
            ObservableList<String> row = FXCollections.observableArrayList(Arrays.asList(data.data.get(result.get(i).index).value));
            dataTable.getItems().add(row);
        }
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
                int x=0;
                while ((line = br.readLine()) != null) {
                    String[] rowValues = line.split(",");
                    data.data.add(new Data(x, rowValues));
                    x++;

                    if(firstLine){
                        for(int i = 0; i < rowValues.length; i++){
                            try{
                                Double.parseDouble(rowValues[i]);
                                RadioButton radBtn = new RadioButton(headers[i]);
                                int finalI = i;
                                radBtn.setOnAction(event -> {
                                    column= finalI;
                                    System.out.println(column);
                                });
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