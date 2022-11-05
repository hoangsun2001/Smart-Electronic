/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package subsc.smart_electronic.controllers;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.util.StringConverter;
import subsc.smart_electronic.connectViews.electronicConnectViews;
import subsc.smart_electronic.db.database;
import subsc.smart_electronic.models.ReceiptData;
import subsc.smart_electronic.models.customerData;
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
    private TextField text_productModel;

    @FXML
    private TextField text_productName;

    @FXML
    private TextField text_catergory;

    @FXML
    private TextField text_price;

    @FXML
    private TextField text_quanlity;

    @FXML
    private Button btn_addProduct;

    @FXML
    private Button btn_UpadateProduct;

    @FXML
    private Button btn_clearProduct;

    @FXML
    private Button btn_deleteProduct;

    @FXML
    private TextField text_productType;

    @FXML
    private TextField text_productBrand;

    @FXML
    private DatePicker text_porductdatePicker;

    @FXML
    private TextField text_productInsur;

    @FXML
    private TextField text_productcontent;

    @FXML
    private TextField text_productColor;

    @FXML
    private TableView<productData> product_tableVew;

    @FXML
    private TableColumn<productData, String> tableViewPro_colunm_cate;

    @FXML
    private TableColumn<productData, String> tableViewPro_colunm_proName;

    @FXML
    private TableColumn<productData, String> tableViewPro_colunm_model;

    @FXML
    private TableColumn<productData, String> tableViewPro_colunm_quanlity;

    @FXML
    private TableColumn<productData, String> tableView_colunm_price;

    @FXML
    private TableColumn<productData, String> tableViewPro_colunm_type;

    @FXML
    private TableColumn<productData, String> tableViewPro_colunm_brand;

    @FXML
    private TableColumn<productData, String> tableViewPro_colunm_date;

    @FXML
    private TableColumn<productData, String> tableViewPro_colunm_insur;

    @FXML
    private TableColumn<productData, String> tableViewPro_colunm_content;

    @FXML
    private TableColumn<productData, String> tableViewPro_colunm_color;

    @FXML
    private TableColumn<productData, String> tableViewPro_colunm_image;
    @FXML
    private AnchorPane AnchonPaneImage;

    @FXML
    private ImageView productImage;

    @FXML
    private Button btn_productImage;
    @FXML
    private AnchorPane employee_form;

    @FXML
    private TextField text_employeeSearch;

    @FXML
    private TextField text_employeeName;

    @FXML
    private TextField text_employee_Id;

    @FXML
    private Button btn_addEmployee;

    @FXML
    private Button btn_updateEmployee;

    @FXML
    private Button btn_clearEmployee;

    @FXML
    private Button btn_deleteEmployee;

    @FXML
    private TextField text_employee_dept;

    @FXML
    private PasswordField text_employee_password;

    @FXML
    private ComboBox<?> text_employee_combochoose_gender;

    @FXML
    private DatePicker text_employee_stardatePicker;

    @FXML
    private DatePicker text_employeeBirthday_datePicker;

    @FXML
    private TextField text_employee_address;

    @FXML
    private TextField text_employee_salary;

    @FXML
    private TextField text_employee_phone;

    @FXML
    private TextField text_employee_email;

    @FXML
    private TableView<employeeData> employee_tableview;

    @FXML
    private TableColumn<employeeData, String> employee_colunmId;

    @FXML
    private TableColumn<employeeData, String> employee_colunm_password;

    @FXML
    private TableColumn<employeeData, String> employee_colunm_Name;

    @FXML
    private TableColumn<employeeData, String> employee_colunm_birthday;

    @FXML
    private TableColumn<employeeData, String> employee_colunm_gender;

    @FXML
    private TableColumn<employeeData, String> employee_colunm_address;

    @FXML
    private TableColumn<employeeData, String> employee_colunm_dept;

    @FXML
    private TableColumn<employeeData, String> employee_colunm_startdate;

    @FXML
    private TableColumn<employeeData, String> employee_colunm_salary;

    @FXML
    private TableColumn<employeeData, String> employee_colunm_phone;

    @FXML
    private TableColumn<employeeData, String> employee_colunm_email;

    @FXML
    private AnchorPane customer_form;

    @FXML
    private TableView<customerData> tableView_customer;

    @FXML
    private TableColumn<customerData, String> tableView_ColunmCustomerID;

    @FXML
    private TableColumn<customerData, String> tableView_ColunmCustomerName;

    @FXML
    private TableColumn<customerData, String> tableView_ColunmCustomerAddress;

    @FXML
    private TableColumn<customerData, String> tableView_ColunmCustomerphone;

    @FXML
    private TableColumn<customerData, String> tableView_ColunmCustomerEmail;

    @FXML
    private TableColumn<customerData, String> tableView_ColunmCustomerCate;

    @FXML
    private TableColumn<customerData, String> tableView_ColunmCustomerProName;

    @FXML
    private TableColumn<customerData, String> tableView_ColunmCustomerQuanlity;

    @FXML
    private TableColumn<customerData, String> tableView_ColunmCustomerTPrice;

    @FXML
    private TableColumn<customerData, String> tableView_ColunmCustomerRank;

    @FXML
    private TextField text_customerId;

    @FXML
    private TextField text_customerName;

    @FXML
    private TextField text_customerAddress;

    @FXML
    private TextField text_customerPhone;

    @FXML
    private TextField text_customerEmail;

    @FXML
    private TextField text_customerTPrice;

    @FXML
    private TextField text_customerSearch;

    @FXML
    private Button btn_CustomerSave;

    @FXML
    private Button btn_CustomerClear;

    @FXML
    private Button btn_CustomerDelete;

    @FXML
    private TextField text_customerCate;

    @FXML
    private TextField text_customerProName;

    @FXML
    private TextField text_customerQuanlity;

    @FXML
    private TextField text_customerRank;

    @FXML
    private AnchorPane receipt_form;

    @FXML
    private TableView<ReceiptData> tableView_Receipt;

    @FXML
    private TableColumn<ReceiptData, String> tableView_ColumnReceiptID;

    @FXML
    private TableColumn<ReceiptData, String> tableView_ColumnReceiptCustID;

    @FXML
    private TableColumn<ReceiptData, String> tableView_ColumnDate;

    @FXML
    private TableColumn<ReceiptData, String> tableView_ColumnTPrice;

    @FXML
    private TableColumn<ReceiptData, String> tableView_ColumnDiscount;

    @FXML
    private TableColumn<ReceiptData, String> tableView_ColumnPaymentTpye;

    @FXML
    private TextField text_receiptId;

    @FXML
    private TextField text_receiptCustomerId;

    @FXML
    private TextField text_receiptSearch;

    @FXML
    private Button btn_receiptUpdate;

    @FXML
    private Button btn_receiptCrear;

    @FXML
    private Button btn_receiptDelete;

    @FXML
    private TextField text_receiptTPrice;

    @FXML
    private TextField text_receiptDiscount;

    @FXML
    private TextField text_receiptPayment;
    @FXML
    private DatePicker text_receiptDate;

    private Connection conn;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;
    private Statement statement;
    private Image image;

    public void addProduct() {
        String insertInto = "insert into products"
                + "(product_cate,product_name, product_model,product_quantity,product_price,"
                + "product_type,product_brand,product_date_up,product_insurance,product_content,product_color,product_image)"
                + "values(?,?,?,?,?,?,?,?,?,?,?,?)";
        conn = database.ConnectDB();
        try {
            if (text_catergory.getText().isEmpty()
                    || text_productName.getText().isEmpty()
                    || text_productModel.getText().isEmpty()
                    || text_quanlity.getText().isEmpty()
                    || text_price.getText().isEmpty()
                    || text_productType.getText().isEmpty()
                    || text_productBrand.getText().isEmpty()
                    || text_productInsur.getText().isEmpty()
                    || text_productColor.getText().isEmpty()
                    || text_productcontent.getText().isEmpty()
                    || text_porductdatePicker.getEditor().getText().isEmpty()
                    || getData.path == null || getData.path == "") {

                InforError("Please fill all blank fields", null, "Error message");

            } else {
                String check = "select * from products where product_model='" + text_productModel.getText() + "'";
                statement = conn.createStatement();
                resultSet = statement.executeQuery(check);

                if (resultSet.next()) {
                    InforError("The product_model " + text_productModel.getText() + " already exist!", null, "Error message");
                } else {
                    preparedStatement = conn.prepareStatement(insertInto);
                    preparedStatement.setString(1, text_catergory.getText());
                    preparedStatement.setString(2, text_productName.getText());
                    preparedStatement.setString(3, text_productModel.getText());
                    preparedStatement.setString(4, text_quanlity.getText());
                    preparedStatement.setString(5, text_price.getText());
                    preparedStatement.setString(6, text_productType.getText());
                    preparedStatement.setString(7, text_productBrand.getText());
                    preparedStatement.setString(8, text_porductdatePicker.getEditor().getText());
                    preparedStatement.setString(9, text_productInsur.getText());
                    preparedStatement.setString(10, text_productcontent.getText());
                    preparedStatement.setString(11, text_productColor.getText());
                    String url = getData.path;
                    url = url.replace("\\", "/");

                    preparedStatement.setString(12, url);

                    preparedStatement.executeUpdate();

                    InforBox("Added successfully!", null, "Information");
                    productclear();
                    productShowData();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void productInsertImage() {
        FileChooser openFile = new FileChooser();
        File file = openFile.showOpenDialog(main_form.getScene().getWindow());
        if (file != null) {
            getData.path = file.getAbsolutePath();

            image = new Image(file.toURI().toString(), 310, 200, false, true);
            productImage.setImage(image);
        }
    }

    public void deleteProduct() {
        String deleteproduct = "delete from products where product_model='" + text_productModel.getText() + "'";
        conn = database.ConnectDB();
        try {
            if (text_catergory.getText().isEmpty()
                    || text_productModel.getText().isEmpty()
                    || text_productName.getText().isEmpty()
                    || text_quanlity.getText().isEmpty()
                    || text_price.getText().isEmpty()
                    || text_productBrand.getText().isEmpty()
                    || text_productColor.getText().isEmpty()
                    || text_productType.getText().isEmpty()
                    || text_productInsur.getText().isEmpty()
                    || text_productcontent.getText().isEmpty()
                    || text_porductdatePicker.getEditor().getText().isEmpty()) {

                InforError("Please fill all blank fields", null, "Error message");

            } else {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure that you want to delete the product_model " + text_productModel.getText() + "?");
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
        String url = getData.path;
        url = url.replace("\\", "/");

        String updateProduct = "update products set product_cate='" + text_catergory.getText()
                + "',product_name='" + text_productName.getText()
                + "',product_quantity='" + text_quanlity.getText()
                + "',product_price='" + text_price.getText()
                + "',product_type='" + text_productType.getText()
                + "',product_brand='" + text_productBrand.getText()
                + "',product_date_up='" + text_porductdatePicker.getEditor().getText()
                + "',product_insurance='" + text_productInsur.getText()
                + "',product_content='" + text_productcontent.getText()
                + "',product_color='" + text_productColor.getText()
                + "',product_image='" + url
                + "'where product_model='" + text_productModel.getText() + "'";
        conn = database.ConnectDB();
        try {
            if (text_catergory.getText().isEmpty()
                    || text_productModel.getText().isEmpty()
                    || text_productName.getText().isEmpty()
                    || text_quanlity.getText().isEmpty()
                    || text_price.getText().isEmpty()
                    || text_productBrand.getText().isEmpty()
                    || text_productColor.getText().isEmpty()
                    || text_productType.getText().isEmpty()
                    || text_productInsur.getText().isEmpty()
                    || text_productcontent.getText().isEmpty()
                    || text_porductdatePicker.getEditor().getText().isEmpty()) {

                InforError("Please fill all blank fields", null, "Error message");

            } else {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure that you want to update the " + text_productModel.getText() + "?");
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
        text_productModel.setText("");
        text_productName.setText("");
        text_quanlity.setText("");
        text_price.setText("");
        text_productBrand.setText("");
        text_productInsur.setText("");
        text_productType.setText("");
        text_porductdatePicker.getEditor().setText("");
        text_productcontent.setText("");
        text_productColor.setText("");
        productImage.setImage(null);
        getData.path = "";
    }
    private String[] statusList = {"Available", "Not available"};

//    public void productListStatusList() {
//        List<String> listS = new ArrayList<>();
//        for (String data : statusList) {
//            listS.add(data);
//        }
//        ObservableList sattusData = FXCollections.observableArrayList(listS);
////        product_status_choose.setItems(sattusData);
//    }
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

                } else if (predicateProductData.getProductModel().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateProductData.getProductName().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateProductData.getQuanlity().toString().contains(searchKey)) {
                    return true;
                } else if (predicateProductData.getPrice().toString().contains(searchKey)) {
                    return true;
                } else if (predicateProductData.getColor().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateProductData.getType().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateProductData.getDate().toString().contains(searchKey)) {
                    return true;
                } else if (predicateProductData.getInsurance().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateProductData.getBrand().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateProductData.getContent().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateProductData.getImage().toLowerCase().contains(searchKey)) {
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
        String employeeDelete = "delete from employees where emp_id='"
                + text_employee_Id.getText() + "'";
        conn = database.ConnectDB();
        try {
            if (text_employee_password.getText().isEmpty()
                    || text_employee_password.getText().isEmpty()
                    || text_employeeName.getText().isEmpty()
                    || text_employeeBirthday_datePicker.getEditor().getText().isEmpty()
                    || text_employee_combochoose_gender.getSelectionModel().getSelectedItem() == null
                    || text_employee_address.getText().isEmpty()
                    || text_employee_dept.getText().isEmpty()
                    || text_employee_stardatePicker.getEditor().getText().isEmpty()
                    || text_employee_salary.getText().isEmpty()
                    || text_employee_phone.getText().isEmpty()
                    || text_employee_email.getText().isEmpty()) {
                InforError("Please fill all the blank fields!", null, "Error message");

            } else {

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure that you want to delete the employee_Id " + text_employee_Id.getText() + "?");
                Optional<ButtonType> optional = alert.showAndWait();
                if (optional.get().equals(ButtonType.OK)) {
                    statement = conn.createStatement();
                    statement.executeUpdate(employeeDelete);

                    InforBox("Deleted Successfully", null, "Information");
                    employeeDatashow();
                    employeeReset();

                }
            }
        } catch (Exception e) {
        }
    }

    public void employeeUpdate() {
        String upadateEmployee = "update employees set emp_pwd='" + text_employee_password.getText()
                + "',emp_name='" + text_employeeName.getText()
                + "',emp_birthday='" + text_employeeBirthday_datePicker.getEditor().getText()
                + "',emp_gender='" + text_employee_combochoose_gender.getSelectionModel().getSelectedItem()
                + "',emp_address='" + text_employee_address.getText()
                + "',emp_dept='" + text_employee_dept.getText()
                + "',emp_startdate='" + text_employee_stardatePicker.getEditor().getText()
                + "',emp_salary='" + text_employee_salary.getText()
                + "',emp_contact='" + text_employee_phone.getText()
                + "',emp_email='" + text_employee_email.getText()
                + "'where emp_id='" + text_employee_Id.getText() + "'";
        conn = database.ConnectDB();
        try {
            if (text_employee_password.getText().isEmpty()
                    || text_employee_password.getText().isEmpty()
                    || text_employeeName.getText().isEmpty()
                    || text_employeeBirthday_datePicker.getEditor().getText().isEmpty()
                    || text_employee_combochoose_gender.getSelectionModel().getSelectedItem() == null
                    || text_employee_address.getText().isEmpty()
                    || text_employee_dept.getText().isEmpty()
                    || text_employee_stardatePicker.getEditor().getText().isEmpty()
                    || text_employee_salary.getText().isEmpty()
                    || text_employee_phone.getText().isEmpty()
                    || text_employee_email.getText().isEmpty()) {
                InforError("Please fill all the blank fields!", null, "Error message");

            } else {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure that you want to update the employee_Id " + text_employee_Id.getText() + "?");
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
//        Date date = new Date();
//        java.sql.Date sqlDate = new java.sql.Date(date.getTime());

        String insertEmployee = "insert into employees(emp_id,emp_pwd,emp_name,emp_birthday,emp_gender,emp_address,emp_dept,emp_startdate,emp_salary,emp_contact,emp_email)"
                + "values(?,?,?,?,?,?,?,?,?,?,?)";
        conn = database.ConnectDB();
        try {
            if (text_employee_password.getText().isEmpty()
                    || text_employee_Id.getText().isEmpty()
                    || text_employee_password.getText().isEmpty()
                    || text_employeeName.getText().isEmpty()
                    || text_employeeBirthday_datePicker.getEditor().getText().isEmpty()
                    || text_employee_combochoose_gender.getSelectionModel().getSelectedItem() == null
                    || text_employee_address.getText().isEmpty()
                    || text_employee_dept.getText().isEmpty()
                    || text_employee_stardatePicker.getEditor().getText().isEmpty()
                    || text_employee_salary.getText().isEmpty()
                    || text_employee_phone.getText().isEmpty()
                    || text_employee_email.getText().isEmpty()) {
                InforError("Please fill all the blank fields!", null, "Error message");

            } else {

                String check = "select *from employees where emp_id='" + text_employee_Id.getText() + "'";
                statement = conn.createStatement();
                resultSet = statement.executeQuery(check);

                if (resultSet.next()) {
                    InforError("The employee_Id " + text_employee_Id.getText() + " already exist!", null, "Error message");

                } else {
                    preparedStatement = conn.prepareStatement(insertEmployee);
                    preparedStatement.setString(1, text_employee_Id.getText());
                    preparedStatement.setString(2, text_employee_password.getText());
                    preparedStatement.setString(3, text_employeeName.getText());
                    preparedStatement.setString(4, text_employeeBirthday_datePicker.getEditor().getText());
                    preparedStatement.setString(5, String.valueOf(text_employee_combochoose_gender.getSelectionModel().getSelectedItem()));
                    preparedStatement.setString(6, text_employee_address.getText());
                    preparedStatement.setString(7, text_employee_dept.getText());
                    preparedStatement.setString(8, text_employee_stardatePicker.getEditor().getText());
                    preparedStatement.setString(9, text_employee_salary.getText());
                    preparedStatement.setString(10, text_employee_phone.getText());
                    preparedStatement.setString(11, text_employee_email.getText());

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
        String sql = "select*from employees";
        conn = database.ConnectDB();
        try {
            employeeData employeeD;
            preparedStatement = conn.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                employeeD = new employeeData(resultSet.getString("emp_id"),
                        resultSet.getString("emp_pwd"),
                        resultSet.getString("emp_name"),
                        resultSet.getDate("emp_birthday"),
                        resultSet.getString("emp_gender"),
                        resultSet.getString("emp_address"),
                        resultSet.getString("emp_dept"),
                        resultSet.getDate("emp_startdate"),
                        resultSet.getDouble("emp_salary"),
                        resultSet.getString("emp_contact"),
                        resultSet.getString("emp_email")
                );

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
                } else if (predicateEmployeeData.getFullName().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateEmployeeData.getDateOfBirth().toString().contains(searchKey)) {
                    return true;
                } else if (predicateEmployeeData.getGender().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateEmployeeData.getAddress().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateEmployeeData.getDept().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateEmployeeData.getStartDate().toString().contains(searchKey)) {
                    return true;
                } else if (predicateEmployeeData.getSalary().toString().contains(searchKey)) {

                } else if (predicateEmployeeData.getPhoneNumber().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateEmployeeData.getEmail().toLowerCase().contains(searchKey)) {
                    return true;
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
        text_employee_combochoose_gender.setItems(statusData);
    }

    public void employeeReset() {
        text_employee_Id.setText("");
        text_employee_password.setText("");
        text_employeeName.setText("");
        text_employeeBirthday_datePicker.getEditor().setText("");
        text_employee_combochoose_gender.getSelectionModel().clearSelection();
        text_employee_address.setText("");
        text_employee_dept.setText("");
        text_employee_stardatePicker.getEditor().setText("");
        text_employee_salary.setText("");
        text_employee_phone.setText("");
        text_employee_email.setText("");
    }

    private ObservableList<employeeData> employeeList;

    public void employeeDatashow() {
        employeeList = employeeListData();
        employee_colunmId.setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        employee_colunm_password.setCellValueFactory(new PropertyValueFactory<>("password"));
        employee_colunm_Name.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        employee_colunm_birthday.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));
        employee_colunm_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        employee_colunm_address.setCellValueFactory(new PropertyValueFactory<>("address"));
        employee_colunm_dept.setCellValueFactory(new PropertyValueFactory<>("dept"));
        employee_colunm_startdate.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        employee_colunm_salary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        employee_colunm_phone.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        employee_colunm_email.setCellValueFactory(new PropertyValueFactory<>("email"));
        employee_tableview.setItems(employeeList);
    }

    public void employeeSelect() {
        employeeData employeeD = employee_tableview.getSelectionModel().getSelectedItem();
        int num = employee_tableview.getSelectionModel().getSelectedIndex();
        if ((num - 1) < -1) {
            return;
        }
        text_employee_Id.setText(employeeD.getEmployeeId());
        text_employee_password.setText(employeeD.getPassword());
        text_employeeName.setText(employeeD.getFullName());
        text_employeeBirthday_datePicker.getEditor().setText(String.valueOf(employeeD.getDateOfBirth()));
        text_employee_combochoose_gender.getSelectionModel().getSelectedIndex();
        text_employee_address.setText(employeeD.getAddress());
        text_employee_dept.setText(employeeD.getDept());
        text_employee_stardatePicker.getEditor().setText(String.valueOf(employeeD.getStartDate()));
        text_employee_salary.setText(String.valueOf(employeeD.getSalary()));
        text_employee_phone.setText(employeeD.getPhoneNumber());
        text_employee_email.setText(employeeD.getEmail());
    }

    public void deleteCustomer() {
        String deleteCust = "delete from customers where customer_id='" + text_customerId.getText() + "'";
        conn = database.ConnectDB();
        try {
            if (text_customerName.getText().isEmpty()
                    || text_customerAddress.getText().isEmpty()
                    || text_customerPhone.getText().isEmpty()
                    || text_customerPhone.getText().isEmpty()
                    || text_customerEmail.getText().isEmpty()
                    || text_customerCate.getText().isEmpty()
                    || text_customerProName.getText().isEmpty()
                    || text_customerQuanlity.getText().isEmpty()
                    || text_customerTPrice.getText().isEmpty()
                    || text_customerRank.getText().isEmpty()
                    || text_customerId.getText().isEmpty()) {
                InforError("Please fill all the blank fields!", null, "Error message");
            } else {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure that you want to delete the customer_Id " + text_customerId.getText() + "?");
                Optional<ButtonType> optional = alert.showAndWait();
                if (optional.get().equals(ButtonType.OK)) {
                    statement = conn.createStatement();
                    statement.executeUpdate(deleteCust);

                    InforBox("Deleted Successfully", null, "Information");
                    showListCustomer();
                    customerResset();

                }
            }
        } catch (Exception e) {
        }
    }

    public void customerResset() {
        text_customerId.setText("");
        text_customerName.setText("");
        text_customerAddress.setText("");
        text_customerPhone.setText("");
        text_customerEmail.setText("");
        text_customerCate.setText("");
        text_customerProName.setText("");
        text_customerQuanlity.setText("");
        text_customerTPrice.setText("");
        text_customerRank.setText("");
    }

    public void customerSearch() {
        FilteredList<customerData> filter = new FilteredList<>(addListCustomer, e -> true);
        text_customerSearch.textProperty().addListener((observable, oldValue, newValue) -> {

            filter.setPredicate(predicateEmployeeData -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String searchKey = newValue.toLowerCase();
                if (predicateEmployeeData.getCustomerId().toLowerCase().contains(searchKey)) {
                    return true;

                } else if (predicateEmployeeData.getCustomerName().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateEmployeeData.getAddress().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateEmployeeData.getCustomerPhone().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateEmployeeData.getEmail().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateEmployeeData.getCatergory().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateEmployeeData.getProductName().toLowerCase().contains(searchKey)) {
                    return true;
                } else if (predicateEmployeeData.getQuanlity().toString().contains(searchKey)) {
                    return true;
                } else if (predicateEmployeeData.getPrice().toString().contains(searchKey)) {
                    return true;
                } else if (predicateEmployeeData.getRank().toString().contains(searchKey)) {
                    return true;
                }
                return false;
            });
            SortedList<customerData> sortedLists = new SortedList<>(filter);
            sortedLists.comparatorProperty().bind(tableView_customer.comparatorProperty());
            tableView_customer.setItems(sortedLists);
        });

    }

    public void customerUpdate() {
        String sqlcustomerUpdate = "update customers set customer_name='" + text_customerName.getText()
                + "',customer_address='" + text_customerAddress.getText()
                + "',customer_contact='" + text_customerPhone.getText()
                + "',customer_email='" + text_customerEmail.getText()
                + "',customer_cate='" + text_customerCate.getText()
                + "',customer_prodName='" + text_customerProName.getText()
                + "',customer_quanlity='" + text_customerQuanlity.getText()
                + "',customer_totalPrice='" + text_customerTPrice.getText()
                + "',customer_rank='" + text_customerRank.getText()
                + "'where customer_id='" + text_customerId.getText() + "'";
        conn = database.ConnectDB();
        try {
            if (text_customerName.getText().isEmpty()
                    || text_customerAddress.getText().isEmpty()
                    || text_customerPhone.getText().isEmpty()
                    || text_customerPhone.getText().isEmpty()
                    || text_customerEmail.getText().isEmpty()
                    || text_customerCate.getText().isEmpty()
                    || text_customerProName.getText().isEmpty()
                    || text_customerQuanlity.getText().isEmpty()
                    || text_customerTPrice.getText().isEmpty()
                    || text_customerRank.getText().isEmpty()
                    || text_customerId.getText().isEmpty()) {
                InforError("Please fill all the blank fields!", null, "Error message");
            } else {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure that you want to update the customer_Id " + text_customerId.getText() + "?");
                Optional<ButtonType> optional = alert.showAndWait();
                if (optional.get().equals(ButtonType.OK)) {
                    statement = conn.createStatement();
                    statement.executeUpdate(sqlcustomerUpdate);
                    InforBox("Updated Successfully", null, "Information");
                    showListCustomer();
                    customerListData();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void customerSelect() {
        customerData custSelect = tableView_customer.getSelectionModel().getSelectedItem();
        int num = tableView_customer.getSelectionModel().getSelectedIndex();
        if ((num - 1) < -1) {
            return;
        }
        text_customerId.setText(custSelect.getCustomerId());
        text_customerName.setText(custSelect.getCustomerName());
        text_customerAddress.setText(custSelect.getAddress());
        text_customerPhone.setText(custSelect.getCustomerPhone());
        text_customerEmail.setText(custSelect.getEmail());
        text_customerCate.setText(custSelect.getCatergory());
        text_customerProName.setText(custSelect.getProductName());
        text_customerQuanlity.setText(String.valueOf(custSelect.getQuanlity()));
        text_customerTPrice.setText(String.valueOf(custSelect.getPrice()));
        text_customerRank.setText(String.valueOf(custSelect.getRank()));
    }

    public void ressetReceipt() {
        text_receiptId.setText("");
        text_receiptCustomerId.setText("");
        text_receiptDate.getEditor().setText("");
        text_receiptTPrice.setText("");
        text_receiptDiscount.setText("");
        text_receiptPayment.setText("");
    }

    public void receiptDelete() {
        String deleteReceipt = "delete from bills where bill_id='" + text_receiptId.getText() + "'";
        conn = database.ConnectDB();
        try {
            if (text_receiptId.getText().isEmpty()
                    || text_receiptCustomerId.getText().isEmpty()
                    || text_receiptDate.getEditor().getText().isEmpty()
                    || text_receiptTPrice.getText().isEmpty()
                    || text_receiptDiscount.getText().isEmpty()
                    || text_receiptPayment.getText().isEmpty()) {
                InforError("Please fill all the blank fields!", null, "Error message");
            } else {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure that you want to delete the receipt_id " + text_receiptId.getText() + "?");
                Optional<ButtonType> optional = alert.showAndWait();
                if (optional.get().equals(ButtonType.OK)) {
                    statement = conn.createStatement();
                    statement.executeUpdate(deleteReceipt);
                    InforBox("Deleted Successfully", null, "Information");
                    showReceiptList();
                    ressetReceipt();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void receiptSearch() {
        FilteredList<ReceiptData> filter = new FilteredList<>(addListReceipt, e -> true);
        text_receiptSearch.textProperty().addListener((observable, oldValue, newValue) -> {

            filter.setPredicate(predicateReceiptData -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String searchKey = newValue.toLowerCase();
                if (predicateReceiptData.getBillId().toString().contains(searchKey)) {
                    return true;
                } else if (predicateReceiptData.getCustomerId().toString().contains(searchKey)) {
                    return true;
                } else if (predicateReceiptData.getDate().toString().contains(searchKey)) {
                    return true;
                } else if (predicateReceiptData.getDiscount().toString().contains(searchKey)) {
                    return true;
                } else if (predicateReceiptData.getTotalTprice().toString().contains(searchKey)) {
                    return true;
                } else if (predicateReceiptData.getPaymentTpye().toString().contains(searchKey)) {
                    return true;
                }
                return false;
            });
            SortedList<ReceiptData> sorted = new SortedList<>(filter);
            sorted.comparatorProperty().bind(tableView_Receipt.comparatorProperty());
            tableView_Receipt.setItems(sorted);
        });

    }

    public void receiptUpdate() {

        String sqlReceipt = "update bills set bill_date='" + text_receiptDate.getEditor().getText()
                + "',bill_total_payment='" + text_receiptTPrice.getText()
                + "',bill_discount='" + text_receiptDiscount.getText()
                + "',payment_type='" + text_receiptPayment.getText()
                + "'where bill_id='" + text_receiptId.getText()
                + "'and bill_customer_id='" + text_receiptCustomerId.getText() + "'";
        conn = database.ConnectDB();
        try {

            if (text_receiptId.getText().isEmpty()
                    || text_receiptCustomerId.getText().isEmpty()
                    || text_receiptDate.getEditor().getText().isEmpty()
                    || text_receiptTPrice.getText().isEmpty()
                    || text_receiptDiscount.getText().isEmpty()
                    || text_receiptPayment.getText().isEmpty()) {
                InforError("Please fill all the blank fields!", null, "Error message");
            } else {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation");
                alert.setHeaderText(null);
                alert.setContentText("Are you sure that you want to update receipt_id " + text_receiptId.getText() + "?");
                Optional<ButtonType> optional = alert.showAndWait();
                if (optional.get().equals(ButtonType.OK)) {
                    statement = conn.createStatement();
                    statement.executeUpdate(sqlReceipt);
                    InforBox("Updated Successfully", null, "Information");
                    showReceiptList();
                    ressetReceipt();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void receiptSelect() {
        ReceiptData receiptD = tableView_Receipt.getSelectionModel().getSelectedItem();
        int num = tableView_Receipt.getSelectionModel().getSelectedIndex();
        if ((num - 1) < -1) {
            return;
        }
        text_receiptId.setText(String.valueOf(receiptD.getBillId()));
        text_receiptCustomerId.setText(String.valueOf(receiptD.getCustomerId()));
        text_receiptDate.getEditor().setText(String.valueOf(receiptD.getDate()));
        text_receiptTPrice.setText(String.valueOf(receiptD.getTotalTprice()));
        text_receiptDiscount.setText(String.valueOf(receiptD.getDiscount()));
        text_receiptPayment.setText(String.valueOf(receiptD.getPaymentTpye()));
    }

    public ObservableList<ReceiptData> receiptListData() {
        ObservableList<ReceiptData> receiptList = FXCollections.observableArrayList();
        String sql = "select *from bills";
        conn = database.ConnectDB();
        try {
            ReceiptData receiptD;
            preparedStatement = conn.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                receiptD = new ReceiptData(resultSet.getInt("bill_id"),
                        resultSet.getInt("bill_customer_id"),
                        resultSet.getDate("bill_date"),
                        resultSet.getDouble("bill_total_payment"),
                        resultSet.getDouble("bill_discount"),
                        resultSet.getInt("payment_type"));
                receiptList.add(receiptD);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return receiptList;
    }
    private ObservableList<ReceiptData> addListReceipt;

    public void showReceiptList() {
        addListReceipt = receiptListData();
        tableView_ColumnReceiptID.setCellValueFactory(new PropertyValueFactory<>("billId"));
        tableView_ColumnReceiptCustID.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        tableView_ColumnDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        tableView_ColumnTPrice.setCellValueFactory(new PropertyValueFactory<>("totalTprice"));
        tableView_ColumnDiscount.setCellValueFactory(new PropertyValueFactory<>("discount"));
        tableView_ColumnPaymentTpye.setCellValueFactory(new PropertyValueFactory<>("paymentTpye"));
        tableView_Receipt.setItems(addListReceipt);
    }

    public ObservableList<customerData> customerListData() {
        ObservableList<customerData> custList = FXCollections.observableArrayList();
        String sql = "select *from customers";
        conn = database.ConnectDB();
        try {
            customerData customer;
            preparedStatement = conn.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                customer = new customerData(resultSet.getString("customer_id"),
                        resultSet.getString("customer_name"),
                        resultSet.getString("customer_address"),
                        resultSet.getString("customer_contact"),
                        resultSet.getString("customer_email"),
                        resultSet.getString("customer_cate"),
                        resultSet.getString("customer_prodName"),
                        resultSet.getInt("customer_quanlity"),
                        resultSet.getDouble("customer_totalPrice"),
                        resultSet.getInt("customer_rank")
                );
                custList.add(customer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return custList;
    }
    private ObservableList<customerData> addListCustomer;

    public void showListCustomer() {
        addListCustomer = customerListData();
        tableView_ColunmCustomerID.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        tableView_ColunmCustomerName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        tableView_ColunmCustomerAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        tableView_ColunmCustomerphone.setCellValueFactory(new PropertyValueFactory<>("customerPhone"));
        tableView_ColunmCustomerEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        tableView_ColunmCustomerCate.setCellValueFactory(new PropertyValueFactory<>("catergory"));
        tableView_ColunmCustomerProName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        tableView_ColunmCustomerQuanlity.setCellValueFactory(new PropertyValueFactory<>("quanlity"));
        tableView_ColunmCustomerTPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        tableView_ColunmCustomerRank.setCellValueFactory(new PropertyValueFactory<>("rank"));
        tableView_customer.setItems(addListCustomer);
    }

    public ObservableList<productData> productListData() {
        ObservableList<productData> productList = FXCollections.observableArrayList();
        String sql = "select*from products";
        conn = database.ConnectDB();
        try {
            productData product;
            preparedStatement = conn.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                product = new productData(resultSet.getString("product_cate"),
                        resultSet.getString("product_name"),
                        resultSet.getString("product_model"),
                        resultSet.getInt("product_quantity"),
                        resultSet.getDouble("product_price"),
                        resultSet.getString("product_type"),
                        resultSet.getString("product_brand"),
                        resultSet.getDate("product_date_up"),
                        resultSet.getString("product_insurance"),
                        resultSet.getString("product_content"),
                        resultSet.getString("product_color"),
                        resultSet.getString("product_image"));

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

        tableViewPro_colunm_cate.setCellValueFactory(new PropertyValueFactory<>("catergory"));
        tableViewPro_colunm_proName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        tableViewPro_colunm_model.setCellValueFactory(new PropertyValueFactory<>("productModel"));
        tableViewPro_colunm_quanlity.setCellValueFactory(new PropertyValueFactory<>("quanlity"));
        tableView_colunm_price.setCellValueFactory(new PropertyValueFactory<>("price"));
        tableViewPro_colunm_type.setCellValueFactory(new PropertyValueFactory<>("type"));
        tableViewPro_colunm_brand.setCellValueFactory(new PropertyValueFactory<>("brand"));
        tableViewPro_colunm_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        tableViewPro_colunm_insur.setCellValueFactory(new PropertyValueFactory<>("insurance"));
        tableViewPro_colunm_content.setCellValueFactory(new PropertyValueFactory<>("content"));
        tableViewPro_colunm_color.setCellValueFactory(new PropertyValueFactory<>("color"));
        tableViewPro_colunm_image.setCellValueFactory(new PropertyValueFactory<>("image"));
        product_tableVew.setItems(addproductList);
    }

    public void productSelect() {
        productData product = product_tableVew.getSelectionModel().getSelectedItem();
        int num = product_tableVew.getSelectionModel().getSelectedIndex();
        if ((num - 1) < -1) {

        }
        text_catergory.setText(product.getCatergory());
        text_productModel.setText(product.getProductModel());
        text_productName.setText(product.getProductName());
        text_quanlity.setText(String.valueOf(product.getQuanlity()));
        text_price.setText(String.valueOf(product.getPrice()));
        text_productBrand.setText(product.getBrand());
        text_productType.setText(product.getType());
        text_productInsur.setText(product.getInsurance());
        text_productColor.setText(product.getColor());
        text_productcontent.setText(product.getContent());
        text_porductdatePicker.getEditor().setText(String.valueOf(product.getDate()));
        getData.path = product.getImage();
        String url = "file:" + product.getImage();
        image = new Image(url, 310, 200, false, true);
        productImage.setImage(image);
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
            customer_form.setVisible(false);
            receipt_form.setVisible(false);
        } else if (event.getSource() == btn_product) {
            dashboar_form.setVisible(false);
            product_form.setVisible(true);
            employee_form.setVisible(false);
            customer_form.setVisible(false);
            receipt_form.setVisible(false);
            productclear();
            productShowData();
            productSearch();

        } else if (event.getSource() == btn_employee) {
            dashboar_form.setVisible(false);
            product_form.setVisible(false);
            employee_form.setVisible(true);
            customer_form.setVisible(false);
            receipt_form.setVisible(false);
            employeeDatashow();
            employeeListStatusList();
            employeeSearch();
            employeeReset();
        } else if (event.getSource() == btn_customer) {
            dashboar_form.setVisible(false);
            product_form.setVisible(false);
            employee_form.setVisible(false);
            customer_form.setVisible(true);
            receipt_form.setVisible(false);
            showListCustomer();
            customerResset();
        } else if (event.getSource() == btn_reciept) {
            dashboar_form.setVisible(false);
            product_form.setVisible(false);
            employee_form.setVisible(false);
            customer_form.setVisible(false);
            receipt_form.setVisible(true);
//            receiptSearch();
            ressetReceipt();
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
//        productListStatusList();
        displayUsername();
        productSearch();
        employeeDatashow();
        employeeSearch();
        employeeListStatusList();
        showListCustomer();
        showReceiptList();
        receiptSearch();

    }

}
