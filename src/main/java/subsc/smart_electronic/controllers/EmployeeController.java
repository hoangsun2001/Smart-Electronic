/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package subsc.smart_electronic.controllers;

import java.lang.module.ModuleDescriptor;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import subsc.smart_electronic.db.database;
import subsc.smart_electronic.models.orderData;
import subsc.smart_electronic.models.productData;

/**
 *
 * @author ADMIN
 */
public class EmployeeController implements Initializable {

    @FXML
    private ComboBox<String> brand;

    @FXML
    private Button btn_add;

    @FXML
    private Button btn_checkout;

    @FXML
    private Button btn_reset;

    @FXML
    private Button btn_close;

    @FXML
    private Button btn_minimize;

    @FXML
    private Button btn_delete;

    @FXML
    private Button btn_reprint;

    @FXML
    private ComboBox<String> category;

    @FXML
    private TableColumn<orderData, String> column_customer_address;

    @FXML
    private TableColumn<orderData, String> column_customer_brand;

    @FXML
    private TableColumn<orderData, String> column_customer_cate;

    @FXML
    private TableColumn<orderData, String> column_customer_contact;

    @FXML
    private TableColumn<orderData, String> column_customer_date;

    @FXML
    private TableColumn<orderData, String> column_customer_delivery;

    @FXML
    private TableColumn<orderData, String> column_customer_discount;

    @FXML
    private TableColumn<orderData, String> column_customer_email;

    @FXML
    private TableColumn<orderData, String> column_customer_id;

    @FXML
    private TableColumn<orderData, String> column_customer_model;

    @FXML
    private TableColumn<orderData, String> column_customer_name;

    @FXML
    private TableColumn<orderData, String> column_customer_prodName;

    @FXML
    private TableColumn<orderData, String> column_customer_quantity;

    @FXML
    private TableColumn<orderData, String> column_customer_totalPrice;

    @FXML
    private AnchorPane employeeform;

    @FXML
    private AnchorPane main_form;

    @FXML
    private ComboBox<String> model;

    @FXML
    private ComboBox<String> productname;

    @FXML
    private Spinner<Integer> quantity;

    @FXML
    private CheckBox radio_delivery;

    @FXML
    private TableView<orderData> tbl_order;

    @FXML
    private TextField text_addresscustomer;

    @FXML
    private TextField text_discountcode;

    @FXML
    private TextField text_discountmoney;

    @FXML
    private TextField text_namecustomer;

    @FXML
    private TextField text_note;

    @FXML
    private TextField text_phonecustomer;

    @FXML
    private TextField text_total;

    @FXML
    private TextField text_vat;

    private Connection conn;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;
    private Statement statement;

    private SpinnerValueFactory spinner;

    public void spinner() {
        spinner = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 20, 0);

        quantity.setValueFactory(spinner);
    }
    private int qty;

    public void getSpnnerValues() {
        qty = quantity.getValue();
    }

    public void GetOrder() {
        category.getItems().add("đồ điện tử");
        productname.getItems().add("chuột quang");
        brand.getItems().add("logitech");
        model.getItems().add("pro");
    }

    public void RequiredFieldValidator() {

    }

    public ObservableList<orderData> orderListData() {
        ObservableList<orderData> orderList = FXCollections.observableArrayList();
        String sql = "select*from customers";
        conn = database.ConnectDB();
        try {
            orderData order;
            preparedStatement = conn.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                order = new orderData(resultSet.getInt("customer_id"),
                        resultSet.getString("customer_name"),
                        resultSet.getString("customer_address"),
                        resultSet.getInt("customer_contact"),
                        resultSet.getDouble("customer_email"),
                        resultSet.getString("customer_cate"),
                        resultSet.getString("customer_prodName"),
                        resultSet.getString("customer_model"),
                        resultSet.getString("customer_brand"),
                        resultSet.getInt("customer_quatity"),
                        resultSet.getDouble("customer_totalPrice"),
                        resultSet.getDouble("customer_discount"));
                resultSet.getDate("customer_date");
                resultSet.getInt("customer_delivery");

                orderList.add(order);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return orderList;

    }
    private ObservableList<orderData> orderList;

    public void orderDataShow() {
        orderList = orderListData();

        column_customer_id.setCellValueFactory(new PropertyValueFactory<>("customer_id"));
        column_customer_name.setCellValueFactory(new PropertyValueFactory<>("customer_name"));
        column_customer_address.setCellValueFactory(new PropertyValueFactory<>("customer_address"));
        column_customer_contact.setCellValueFactory(new PropertyValueFactory<>("customer_contact"));
        column_customer_email.setCellValueFactory(new PropertyValueFactory<>("customer_email"));
        column_customer_cate.setCellValueFactory(new PropertyValueFactory<>("customer_cate"));
        column_customer_prodName.setCellValueFactory(new PropertyValueFactory<>("customer_prodName"));
        column_customer_model.setCellValueFactory(new PropertyValueFactory<>("customer_model"));
        column_customer_brand.setCellValueFactory(new PropertyValueFactory<>("customer_brand"));
        column_customer_quantity.setCellValueFactory(new PropertyValueFactory<>("customer_quantity"));
        column_customer_totalPrice.setCellValueFactory(new PropertyValueFactory<>("customer_totalPrice"));
        column_customer_discount.setCellValueFactory(new PropertyValueFactory<>("Discount"));
        column_customer_date.setCellValueFactory(new PropertyValueFactory<>("Date"));
        column_customer_delivery.setCellValueFactory(new PropertyValueFactory<>("Delivery"));
        tbl_order.setItems(orderList);
    }

    public void orderAdd() {
        String insertcustomers = "insert into customers(customer_id,customer_name,customer_address,customer_contact,customer_email,customer_cate,customer_prodName,customer_model,customer_brand,customer_quantity,customer_totalPrice,customer_discount,customer_date,customer_delivery)"
                + "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        conn = database.ConnectDB();
        try {
            if (category.getSelectionModel().getSelectedItem() == null
                    || productname.getSelectionModel().getSelectedItem() == null
                    || brand.getSelectionModel().getSelectedItem() == null
                    || model.getSelectionModel().getSelectedItem() == null
                    || quantity.getEditor().getText() == null
                    || text_namecustomer.getText().isEmpty()
                    || text_phonecustomer.getText().isEmpty()
                    || text_addresscustomer.getText().isEmpty()) {
                InforError("Please fill all the blank fields!", null, "Error message");

            } else {

                preparedStatement = conn.prepareStatement(insertcustomers);
                preparedStatement.setString(1, category.getEditor().getText());
                preparedStatement.setString(2, productname.getEditor().getText());
                preparedStatement.setString(3, brand.getEditor().getText());
                preparedStatement.setString(4, model.getEditor().getText());
                preparedStatement.setString(5, quantity.getEditor().getText());
                preparedStatement.setString(6, text_discountcode.getText());
                preparedStatement.setString(7, text_namecustomer.getText());
                preparedStatement.setString(8, text_phonecustomer.getText());
                preparedStatement.setString(9, text_addresscustomer.getText());
                preparedStatement.setString(10, text_note.getText());

                preparedStatement.executeUpdate();
                InforBox("Add successfully", null, "Information");
                orderListData();
                orderDataShow();
                orderReset();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void orderReset() {
        category.getSelectionModel().clearSelection();
        productname.getSelectionModel().clearSelection();
        brand.getSelectionModel().clearSelection();
        model.getSelectionModel().clearSelection();
        quantity.getEditor().setText("");
        text_discountcode.setText("");
        text_namecustomer.setText("");
        text_phonecustomer.setText("");
        text_addresscustomer.setText("");
        text_note.setText("");
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

    public void InforError(String message, String headerText, String title) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(message);
        alert.setHeaderText(headerText);
        alert.setTitle(title);
        alert.showAndWait();

    }

    public void InforBox(String message, String headerText, String title) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(message);
        alert.setHeaderText(headerText);
        alert.setTitle(title);
        alert.showAndWait();

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        spinner();
        GetOrder();
        RequiredFieldValidator();
    }

}
