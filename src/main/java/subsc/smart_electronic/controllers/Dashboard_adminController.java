/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package subsc.smart_electronic.controllers;

import java.io.FileFilter;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import subsc.smart_electronic.connectViews.electronicConnectViews;
import subsc.smart_electronic.db.database;
import subsc.smart_electronic.models.employeeData;
import subsc.smart_electronic.models.getData;
import subsc.smart_electronic.models.productData;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class Dashboard_adminController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private AnchorPane main_form;

    @FXML
    private Button btn_minimize;

    @FXML
    private Button btn_close;

    @FXML
    private Button btn_dashboard;

    @FXML
    private Label display_username;

    @FXML
    private Button btn_product;

    @FXML
    private Button btn_employee;

    @FXML
    private Button btn_customer;

    @FXML
    private Button btn_reciept;

    @FXML
    private Button btn_InventoryManangerment;

    @FXML
    private Button btn_signout;

    @FXML
    private AnchorPane dashboar_form;

    @FXML
    private Label display_employeeActive;

    @FXML
    private Label display_todayIcome;

    @FXML
    private Label display_totalIcome;

    @FXML
    private AreaChart<?, ?> data_chart;

    @FXML
    private AnchorPane product_form;

    @FXML
    private TextField product_search;

    @FXML
    private TextField text_productName;

    @FXML
    private TextField text_productId;

    @FXML
    private TextField text_catergory;

    @FXML
    private TextField text_price;

    @FXML
    private TextField text_quanlity;
    @FXML
    private ComboBox<?> product_status_choose;
    @FXML
    private Button btn_addProduct;

    @FXML
    private Button btn_UpadateProduct;

    @FXML
    private Button btn_clearProduct;

    @FXML
    private Button btn_deleteProduct;

    @FXML
    private TableView<productData> product_tableVew;

    @FXML
    private TableColumn<productData, String> tableView_colunm_catergory;

    @FXML
    private TableColumn<productData, String> tableView_colunm_productID;

    @FXML
    private TableColumn<productData, String> tableView_colunm_productName;

    @FXML
    private TableColumn<productData, String> tableView_colunm_quanlity;

    @FXML
    private TableColumn<productData, String> tableView_colunm_price;

    @FXML
    private TableColumn<productData, String> tableView_colunm_status;

    @FXML
    private AnchorPane employee_form;

    @FXML
    private TextField text_employeeSearch;

    @FXML
    private TextField text_employeeFirstName;

    @FXML
    private TextField text_employeeId;

    @FXML
    private DatePicker text_datePicker;

    @FXML
    private Button btn_addEmployee;

    @FXML
    private Button btn_updateEmployee;

    @FXML
    private Button btn_clearEmployee;

    @FXML
    private Button btn_deleteEmployee;

    @FXML
    private TextField text_employee_lastName;

    @FXML
    private TextField text_employee_salary;

    @FXML
    private PasswordField text_employee_password;

    @FXML
    private ComboBox<?> combochoose_gender;

    @FXML
    private TextField text_employee_phone;

    @FXML
    private DatePicker picker_date;

    @FXML
    private TableView<employeeData> employee_tableview;

    @FXML
    private TableColumn<employeeData, String> employee_colunmId;

    @FXML
    private TableColumn<employeeData, String> employee_colunm_password;

    @FXML
    private TableColumn<employeeData, String> employee_colunm_firstName;

    @FXML
    private TableColumn<employeeData, String> employee_colunm_lastName;

    @FXML
    private TableColumn<employeeData, String> employee_colunm_dateOfbirth;

    @FXML
    private TableColumn<employeeData, String> employee_colunm_gender;

    @FXML
    private TableColumn<employeeData, String> employee_colunm_startDate;

    @FXML
    private TableColumn<employeeData, String> employee_colunm_salary;

    @FXML
    private TableColumn<employeeData, String> employee_colunm_phone;
    private Connection conn;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;
    private Statement statement;

    public void addProduct() {
        String insertInto = "insert into product"
                + "(catergory,product_id, product_name,quanlity,price,status)"
                + "values(?,?,?,?,?,?)";
        conn = database.ConnectDB();
        try {
            if (text_catergory.getText().isEmpty()
                    || text_productId.getText().isEmpty()
                    || text_productName.getText().isEmpty()
                    || text_quanlity.getText().isEmpty()
                    || text_price.getText().isEmpty()
                    || product_status_choose.getSelectionModel().getSelectedItem() == null) {

                InforError("Please fill all blank fields", null, "Error message");

            } else {
                String check = "select * from product where product_id='" + text_productId.getText() + "'";
                statement = conn.createStatement();
                resultSet = statement.executeQuery(check);

                if (resultSet.next()) {
                    InforError("The product_Id " + text_productId.getText() + " already exist!", null, "Error message");
                } else {
                    preparedStatement = conn.prepareStatement(insertInto);
                    preparedStatement.setString(1, text_catergory.getText());
                    preparedStatement.setString(2, text_productId.getText());
                    preparedStatement.setString(3, text_productName.getText());
                    preparedStatement.setString(4, text_quanlity.getText());
                    preparedStatement.setString(5, text_price.getText());
                    preparedStatement.setString(6, (String) product_status_choose.getSelectionModel().getSelectedItem());

                    preparedStatement.executeUpdate();

                    InforBox("Added successfully!", null, "Information");
                    productclear();
                    productShowData();
                }
            }
        } catch (Exception e) {
        }
    }

    public void deleteProduct() {
        String deleteproduct = "delete from product where product_id='" + text_productId.getText() + "'";
        conn = database.ConnectDB();
        try {
            if (text_catergory.getText().isEmpty()
                    || text_productId.getText().isEmpty()
                    || text_productName.getText().isEmpty()
                    || text_quanlity.getText().isEmpty()
                    || text_price.getText().isEmpty()
                    || product_status_choose.getSelectionModel().getSelectedItem() == null) {

                InforError("Please fill all blank fields", null, "Error message");

            } else {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure that you want to delete the product_id " + text_productId.getText() + "?");
                Optional<ButtonType> optional = alert.showAndWait();

                if (optional.get().equals(ButtonType.OK)) {
                    statement = conn.createStatement();
                    statement.executeUpdate(deleteproduct);

                    InforBox("Deleted Succesfully!", null, "Information");
                    productShowData();
                    productclear();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void producUpdate() {
        String updateProduct = "update product set catergory='"
                + text_catergory.getText() + "',product_name='"
                + text_productName.getText() + "',quanlity='"
                + text_quanlity.getText() + "',price='"
                + text_price.getText() + "',status='"
                + product_status_choose.getSelectionModel().getSelectedItem() + "'"
                + "where product_id='" + text_productId.getText() + "'";
        conn = database.ConnectDB();
        try {
            if (text_catergory.getText().isEmpty()
                    || text_productId.getText().isEmpty()
                    || text_productName.getText().isEmpty()
                    || text_quanlity.getText().isEmpty()
                    || text_price.getText().isEmpty()
                    || product_status_choose.getSelectionModel().getSelectedItem() == null) {

                InforError("Please fill all blank fields", null, "Error message");

            } else {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure that you want to update the product_id" + text_productId.getText() + "?");
                Optional<ButtonType> optional = alert.showAndWait();

                if (optional.get().equals(ButtonType.OK)) {
                    statement = conn.createStatement();
                    statement.executeUpdate(updateProduct);

                    InforBox("Update Succesfully!", null, "Information");
                    productShowData();
                    productclear();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void productclear() {
        text_catergory.setText("");
        text_productId.setText("");
        text_productName.setText("");
        text_quanlity.setText("");
        text_price.setText("");
        product_status_choose.getSelectionModel().clearSelection();
    }
    private String[] statusList = {"Available", "Not available"};

    public void productListStatusList() {
        List<String> listS = new ArrayList<>();
        for (String data : statusList) {
            listS.add(data);
        }
        ObservableList sattusData = FXCollections.observableArrayList(listS);
        product_status_choose.setItems(sattusData);
    }

    public void productSearch() {
        FilteredList<productData> filter = new FilteredList<>(addproductList, e -> true);
        product_search.textProperty().addListener((observable, oldValue, newValue) -> {

            filter.setPredicate(predicateProductData -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String searchKey = newValue.toLowerCase();
                if (predicateProductData.getCatergory().toLowerCase().contains(searchKey)) {
                    return true;

                } else if (predicateProductData.getProductId().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateProductData.getProductName().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateProductData.getQuanlity().toString().contains(searchKey)) {
                    return true;
                } else if (predicateProductData.getPrice().toString().contains(searchKey)) {
                    return true;
                } else if (predicateProductData.getStatus().toLowerCase().contains(searchKey)) {
                    return true;
                }
                return false;
            });
            SortedList<productData> sortedList = new SortedList<>(filter);
            sortedList.comparatorProperty().bind(product_tableVew.comparatorProperty());
            product_tableVew.setItems(sortedList);
        });

    }

    public void employeeDelete() {
        String employeeDelete = "delete from employee where employee_Id='"
                + text_employeeId.getText() + "'";
        conn = database.ConnectDB();
        try {
            if (text_employee_password.getText().isEmpty()
                    || text_employeeFirstName.getText().isEmpty()
                    || text_employee_lastName.getText().isEmpty()
                    || text_datePicker.getEditor().getText().isEmpty()
                    || combochoose_gender.getSelectionModel().getSelectedItem() == null
                    || text_employee_salary.getText().isEmpty()
                    || text_employee_phone.getText().isEmpty()) {
                InforError("Please fill all the blank fields!", null, "Error message");

            } else {
                String check = "select *from employee where gender='" + combochoose_gender.getSelectionModel().getSelectedItem() + "'";
                statement = conn.createStatement();
                resultSet = statement.executeQuery(check);

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure that you want to delete the employee_Id " + text_employeeId.getText() + "?");
                Optional<ButtonType> optional = alert.showAndWait();
                if (optional.get().equals(ButtonType.OK)) {
                    statement = conn.createStatement();
                    statement.executeUpdate(employeeDelete);

                    InforBox("Deleted Successfully", null, "Information");
                    employeeDatashow();
                    employeeReset();

                } else {
                    InforError("Thess", null, "ss");
                }
            }
        } catch (Exception e) {
        }
    }

    public void employeeUpdate() {
        String upadateEmployee = "update employee set password='" + text_employee_password.getText()
                + "',firstname='" + text_employeeFirstName.getText()
                + "',lastname='" + text_employee_lastName.getText()
                + "',dateofbirth='" + text_datePicker.getEditor().getText()
                + "',gender='" + combochoose_gender.getSelectionModel().getSelectedItem()
                + "',salary='" + text_employee_salary.getText()
                + "',phoneNumber='" + text_employee_phone.getText()
                + "'where employee_Id='" + text_employeeId.getText() + "'";
        conn = database.ConnectDB();
        try {
            if (text_employee_password.getText().isEmpty()
                    || text_employeeFirstName.getText().isEmpty()
                    || text_employee_lastName.getText().isEmpty()
                    || text_datePicker.getEditor().getText().isEmpty()
                    || combochoose_gender.getSelectionModel().getSelectedItem() == null
                    || text_employee_salary.getText().isEmpty()
                    || text_employee_phone.getText().isEmpty()) {
                InforError("Please fill all the blank fields!", null, "Error message");

            } else {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure that you want to update the employee_Id " + text_employeeId.getText() + "?");
                Optional<ButtonType> optional = alert.showAndWait();
                if (optional.get().equals(ButtonType.OK)) {
                    statement = conn.createStatement();
                    statement.executeUpdate(upadateEmployee);

                    InforBox("Updated Successfully", null, "Information");
                    employeeDatashow();
                    employeeReset();
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void employeeSave() {
        Date date = new Date();
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());

        String insertEmployee = "insert into employee(employee_Id,password,firstname,lastname,dateofbirth,gender,startdate,salary,phoneNumber)"
                + "values(?,?,?,?,?,?,?,?,?)";
        conn = database.ConnectDB();
        try {
            if (text_employeeId.getText().isEmpty()
                    || text_employee_password.getText().isEmpty()
                    || text_employeeFirstName.getText().isEmpty()
                    || text_employee_lastName.getText().isEmpty()
                    || combochoose_gender.getSelectionModel().getSelectedItem() == null
                    || text_employee_salary.getText().isEmpty()
                    || text_employee_phone.getText().isEmpty()) {
                InforError("Please fill all the blank fields!", null, "Error message");

            } else {

                String check = "select *from employee where employee_Id='" + text_employeeId.getText() + "'";
                statement = conn.createStatement();
                resultSet = statement.executeQuery(check);

                if (resultSet.next()) {
                    InforError("The employee_Id " + text_employeeId.getText() + "already exist!", null, "Error message");

                } else {
                    preparedStatement = conn.prepareStatement(insertEmployee);

                    preparedStatement.setString(1, text_employeeId.getText());
                    preparedStatement.setString(2, text_employee_password.getText());
                    preparedStatement.setString(3, text_employeeFirstName.getText());
                    preparedStatement.setString(4, text_employee_lastName.getText());
                    preparedStatement.setString(5, text_datePicker.getEditor().getText());
                    preparedStatement.setString(6, String.valueOf(combochoose_gender.getSelectionModel().getSelectedItem()));
                    preparedStatement.setString(7, String.valueOf(sqlDate));
                    preparedStatement.setString(8, text_employee_salary.getText());
                    preparedStatement.setString(9, text_employee_phone.getText());

                    preparedStatement.executeUpdate();
                    InforBox("Saved successfully", null, "Information");
                    employeeListData();
                    employeeDatashow();
                    employeeReset();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ObservableList<employeeData> employeeListData() {
        ObservableList<employeeData> employeeData = FXCollections.observableArrayList();
        String sql = "select*from employee";
        conn = database.ConnectDB();
        try {
            employeeData employeeD;
            preparedStatement = conn.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                employeeD = new employeeData(resultSet.getString("employee_Id"),
                        resultSet.getString("password"),
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        resultSet.getDate("dateofbirth"),
                        resultSet.getString("gender"),
                        resultSet.getDate("startdate"),
                        resultSet.getDouble("salary"),
                        resultSet.getString("phoneNumber"));
                employeeData.add(employeeD);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return employeeData;

    }

    public void employeeSearch() {
        FilteredList<employeeData> filter = new FilteredList<>(employeeList, e -> true);
        text_employeeSearch.textProperty().addListener((observable, oldValue, newValue) -> {

            filter.setPredicate(predicateEmployeeData -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String searchKey = newValue.toLowerCase();
                if (predicateEmployeeData.getEmployeeId().toLowerCase().contains(searchKey)) {
                    return true;

                } else if (predicateEmployeeData.getPassword().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateEmployeeData.getFirstName().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateEmployeeData.getLastName().contains(searchKey)) {
                    return true;
                } else if (predicateEmployeeData.getDateofBirth().toString().contains(searchKey)) {
                    return true;
                } else if (predicateEmployeeData.getGender().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateEmployeeData.getStartDate().toString().contains(searchKey)) {
                    return true;

                } else if (predicateEmployeeData.getSalary().toString().contains(searchKey)) {
                    return true;
                } else if (predicateEmployeeData.getPhoneNumber().contains(searchKey)) {

                }
                return false;
            });
            SortedList<employeeData> sortedList = new SortedList<>(filter);
            sortedList.comparatorProperty().bind(employee_tableview.comparatorProperty());
            employee_tableview.setItems(sortedList);
        });

    }

    private String[] statusListEmployee = {"Male", "Female", "Other"};

    public void employeeListStatusList() {
        List<String> listE = new ArrayList<>();
        for (String data : statusListEmployee) {
            listE.add(data);
        }
        ObservableList statusData = FXCollections.observableArrayList(listE);
        combochoose_gender.setItems(statusData);
    }

    public void employeeReset() {
        text_employeeId.setText("");
        text_employee_password.setText("");
        text_employeeFirstName.setText("");
        text_employee_lastName.setText("");
        text_datePicker.getEditor().setText("");
        combochoose_gender.getSelectionModel().clearSelection();
        text_employee_salary.setText("");
        text_employee_phone.setText("");
    }

    private ObservableList<employeeData> employeeList;

    public void employeeDatashow() {
        employeeList = employeeListData();
        employee_colunmId.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        employee_colunm_password.setCellValueFactory(new PropertyValueFactory<>("password"));
        employee_colunm_firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        employee_colunm_lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        employee_colunm_dateOfbirth.setCellValueFactory(new PropertyValueFactory<>("dateofBirth"));
        employee_colunm_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        employee_colunm_startDate.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        employee_colunm_salary.setCellValueFactory(new PropertyValueFactory<>("Salary"));
        employee_colunm_phone.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        employee_tableview.setItems(employeeList);
    }

    public void employeeSelect() {
        employeeData employeeD = employee_tableview.getSelectionModel().getSelectedItem();
        int num = employee_tableview.getSelectionModel().getSelectedIndex();
        if ((num - 1) < -1) {
            return;
        }
        text_employeeId.setText(employeeD.getEmployeeId());
        text_employee_password.setText(employeeD.getPassword());
        text_employeeFirstName.setText(employeeD.getFirstName());
        text_employee_lastName.setText(employeeD.getLastName());
        text_datePicker.getEditor().setText(String.valueOf(employeeD.getDateofBirth()));
//        combochoose_gender.getSelectionModel().select((int) (Object) String.valueOf(employeeD.getGender()));
        text_employee_salary.setText(String.valueOf(employeeD.getSalary()));
        text_employee_phone.setText(employeeD.getPhoneNumber());
    }

    public ObservableList<productData> productListData() {
        ObservableList<productData> productList = FXCollections.observableArrayList();
        String sql = "select*from product";
        conn = database.ConnectDB();
        try {
            productData product;
            preparedStatement = conn.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                product = new productData(resultSet.getString("catergory"),
                        resultSet.getString("product_id"),
                        resultSet.getString("product_name"),
                        resultSet.getInt("quanlity"),
                        resultSet.getDouble("price"),
                        resultSet.getString("status"));
                productList.add(product);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return productList;

    }

    private ObservableList<productData> addproductList;

    public void productShowData() {
        addproductList = productListData();

        tableView_colunm_catergory.setCellValueFactory(new PropertyValueFactory<>("catergory"));
        tableView_colunm_productID.setCellValueFactory(new PropertyValueFactory<>("productId"));
        tableView_colunm_productName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        tableView_colunm_quanlity.setCellValueFactory(new PropertyValueFactory<>("quanlity"));
        tableView_colunm_price.setCellValueFactory(new PropertyValueFactory<>("price"));
        tableView_colunm_status.setCellValueFactory(new PropertyValueFactory<>("status"));

        product_tableVew.setItems(addproductList);
    }

    public void productSelect() {
        productData product = product_tableVew.getSelectionModel().getSelectedItem();
        int num = product_tableVew.getSelectionModel().getSelectedIndex();
        if ((num - 1) < -1) {

        }
        text_catergory.setText(product.getCatergory());
        text_productId.setText(product.getProductId());
        text_productName.setText(product.getProductName());
        text_quanlity.setText(String.valueOf(product.getQuanlity()));
        text_price.setText(String.valueOf(product.getPrice()));
    }

    private double x = 0;
    private double y = 0;

    public void signout() throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Are you really want to sign out?");
        alert.setHeaderText(null);
        alert.setTitle("Confirmation");

        Optional<ButtonType> optional = alert.showAndWait();
        if (optional.get().equals(ButtonType.OK)) {
            btn_signout.getScene().getWindow().hide();
            Parent root = FXMLLoader.load(getClass().getResource(electronicConnectViews.Login));
            Stage stage = new Stage();
            Scene scene = new Scene(root);

            root.setOnMousePressed((event) -> {
                x = event.getSceneX();
                y = event.getSceneY();
            });
            root.setOnMouseDragged((event) -> {
                stage.setX(event.getScreenX() - x);
                stage.setY(event.getScreenY() - y);

                stage.setOpacity(.8);
            });
            root.setOnMouseReleased((event) -> {
                stage.setOpacity(1);
            });
            stage.initStyle(StageStyle.TRANSPARENT);
            stage.setScene(scene);
            stage.show();

        }
    }

    public void displayUsername() {
        display_username.setText(getData.AdminUsername);
    }

    public void switchForm(ActionEvent event) {
        if (event.getSource() == btn_dashboard) {
            dashboar_form.setVisible(true);
            product_form.setVisible(false);
            employee_form.setVisible(false);

        } else if (event.getSource() == btn_product) {
            dashboar_form.setVisible(false);
            product_form.setVisible(true);
            employee_form.setVisible(false);
            productclear();
            productShowData();
            productSearch();

        } else if (event.getSource() == btn_employee) {
            dashboar_form.setVisible(false);
            product_form.setVisible(false);
            employee_form.setVisible(true);
            employeeDatashow();
            employeeListStatusList();
            employeeSearch();
        }
    }

    public void close() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Are you really want to exit?");
        alert.setHeaderText(null);
        alert.setTitle("Confirmation");

        Optional<ButtonType> optional = alert.showAndWait();
        if (optional.get().equals(ButtonType.OK)) {
            System.exit(0);
        }

    }

    public void minimize() {
        Stage stage = (Stage) main_form.getScene().getWindow();
        stage.setIconified(true);
    }

    public void InforBox(String message, String headerText, String title) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(message);
        alert.setHeaderText(headerText);
        alert.setTitle(title);
        alert.showAndWait();

    }

    public void InforError(String message, String headerText, String title) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(message);
        alert.setHeaderText(headerText);
        alert.setTitle(title);
        alert.showAndWait();

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        productShowData();
        productListStatusList();
        displayUsername();
        productSearch();
        employeeDatashow();
        employeeSearch();
        employeeListStatusList();
    }

}
