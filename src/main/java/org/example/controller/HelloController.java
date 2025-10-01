package org.example.controller;

import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.example.pojo.Department;
import org.example.pojo.Employee;
import org.example.repository.DepartmentRepo;
import org.example.repository.EmployeeRepo;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;

public class HelloController {
    @FXML
    public TableView<Employee> tableView;
    @FXML
    public TableColumn<Employee, Integer> colId;
    @FXML
    public TableColumn<Employee, String> colName;
    @FXML
    public TableColumn<Employee, String> colDepartment;
    @FXML
    public TableColumn<Employee, Double> colSalary;
    @FXML
    public TableColumn<Employee, String> colImage;

    @FXML
    public ComboBox<Department> comboBox;


    @FXML
    private TextField t1;

    @FXML
    private TextField t3;
    @FXML
    public ImageView imageView;

    @FXML
    private Label welcomeText;

    private String selectedImagePath; // giữ đường dẫn ảnh đã chọn
    private final EmployeeRepo repo = new EmployeeRepo();

    private void clearForm() {
        t1.clear();
        comboBox.getSelectionModel().clearSelection();
        t3.clear();
        imageView.setImage(null);
        selectedImagePath = null;
    }

    @FXML
    public void saveEmployee(ActionEvent actionEvent) {
        Employee emp = new Employee();
        emp.setName(t1.getText());
        String department = comboBox.getValue().toString();
        if(department == null){
            department = "none";
        }
        // set department
        emp.setSalary(Double.parseDouble(t3.getText()));
        emp.setImg(selectedImagePath);
        EmployeeRepo empRepo = new EmployeeRepo();
        empRepo.addEmployee(emp);
        tableView.getItems().add(emp);
        clearForm();
    }


    private DepartmentRepo departmentRepo = new DepartmentRepo();
    @FXML
    public void initialize() {

        List<Department> departments = departmentRepo.getAllDepartments();

        comboBox.setItems(FXCollections.observableList(departments));

        // Hiển thị tên phòng ban thay vì object.toString()
        comboBox.setCellFactory(lv -> new javafx.scene.control.ListCell<>() {
            @Override
            protected void updateItem(Department item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? "" : item.getDepartmentName());
            }
        });

        comboBox.setButtonCell(new javafx.scene.control.ListCell<>() {
            @Override
            protected void updateItem(Department item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? "" : item.getDepartmentName());
            }
        });

        // Gắn field của Employee với cột trong bảng
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        colImage.setCellValueFactory(new PropertyValueFactory<>("img"));
        colDepartment.setCellValueFactory(cellData -> {
            Department dept = cellData.getValue().getDepartment();
            return new SimpleObjectProperty<>(dept != null ? dept.getDepartmentName() : "");
        });

        colImage.setCellFactory(param -> new TableCell<Employee, String>() {
            private final ImageView imgView = new ImageView();

            @Override
            protected void updateItem(String path, boolean empty) {
                super.updateItem(path, empty);
                if (empty || path == null) {
                    setGraphic(null);
                } else {
                    File file = new File(path);
                    if (file.exists()) {
                        imgView.setImage(new Image(file.toURI().toString()));
                        imgView.setFitWidth(50);
                        imgView.setFitHeight(50);
                        imgView.setPreserveRatio(true);
                        setGraphic(imgView);
                    } else {
                        setGraphic(null);
                    }
                }
            }
        });
        loadEmployees();
    }

    private void loadEmployees() {
        List<Employee> employees = repo.getAllEmployees();
        ObservableList<Employee> list = FXCollections.observableArrayList(employees);
        tableView.setItems(list);
    }

    @FXML
    public void uploadImage(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg"));
        Stage stage = (Stage) tableView.getScene().getWindow();
        File file = fileChooser.showOpenDialog(stage);

        if (file != null) {
            try{
                File destDir = new File("images");
                if (!destDir.exists()) destDir.mkdirs();

                File destFile = new File(destDir, file.getName());
                Files.copy(file.toPath(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

                selectedImagePath = destFile.getPath();

                Image img = new Image(destFile.toURI().toString());
                imageView.setImage(img);
                imageView.setFitWidth(120);
                imageView.setFitHeight(120);
                imageView.setPreserveRatio(true);
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }
    @FXML
    public void updateEmployee(ActionEvent actionEvent) {
    }
    @FXML
    public void deleteEmployee(ActionEvent actionEvent) {
    }
}
