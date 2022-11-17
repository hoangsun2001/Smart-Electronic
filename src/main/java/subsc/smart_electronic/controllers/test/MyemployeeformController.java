///*
// * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
// * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
// */
//package subsc.smart_electronic.controllers;
//
//import java.net.URL;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.Statement;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Optional;
//import java.util.ResourceBundle;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.fxml.FXML;
//import javafx.fxml.Initializable;
//import javafx.scene.control.Alert;
//import javafx.scene.control.Button;
//import javafx.scene.control.ButtonType;
//import javafx.scene.control.ComboBox;
//import javafx.scene.control.Label;
//import javafx.scene.control.Spinner;
//import javafx.scene.control.SpinnerValueFactory;
//import javafx.scene.control.TableColumn;
//import javafx.scene.control.TableView;
//import javafx.scene.control.TextField;
//import javafx.scene.control.cell.PropertyValueFactory;
//import net.sf.jasperreports.engine.JasperCompileManager;
//import net.sf.jasperreports.engine.JasperFillManager;
//import net.sf.jasperreports.engine.JasperPrint;
//import net.sf.jasperreports.engine.JasperReport;
//import net.sf.jasperreports.engine.design.JasperDesign;
//import net.sf.jasperreports.engine.xml.JRXmlLoader;
//import net.sf.jasperreports.view.JasperViewer;
//import subsc.smart_electronic.db.database;
//import subsc.smart_electronic.models.empCusData;
//
///**
// * FXML Controller class
// *
// * @author Admin
// */
//public class MyemployeeformController implements Initializable {
//
//    @FXML
//    private TableView<empCusData> TableView_customer;
//
//    @FXML
//    private TableColumn<empCusData, String> columnID;
//
//    @FXML
//    private TableColumn<empCusData, String> columnName;
//
//    @FXML
//    private TableColumn<empCusData, String> columnAddress;
//
//    @FXML
//    private TableColumn<empCusData, String> columncontact;
//
//    @FXML
//    private TableColumn<empCusData, String> columnEmail;
//
//    @FXML
//    private TableColumn<empCusData, String> columncate;
//
//    @FXML
//    private TableColumn<empCusData, String> columnproName;
//
//    @FXML
//    private TableColumn<empCusData, String> columnQuanlity;
//
//    @FXML
//    private TableColumn<empCusData, String> columnPrice;
//
//    @FXML
//    private TableColumn<empCusData, String> columnRank;
//
//    @FXML
//    private TextField text_cate;
//
//    @FXML
//    private ComboBox<?> text_chooseProduct;
//
//    @FXML
//    private Spinner<Integer> text_chooseQuanlity;
//
//    @FXML
//    private Button btn_add;
//
//    @FXML
//    private Button btn_pay;
//
//    @FXML
//    private Button btn_cancel;
//    @FXML
//    private Label label_total;
//
//    private Connection conn;
//    private ResultSet resultSet;
//    private PreparedStatement preparedStatement;
//    private Statement statement;
//
//    public void receipt() {
//        purchaseCustomerId();
//        HashMap hash = new HashMap();
//        hash.put("electricR", customerId);
//        try {
//            JasperDesign jDesign = JRXmlLoader.load("D:\\Java-work\\JavaProgram\\Smart_Electronic\\src\\main\\resources\\subsc\\smart_electronic\\report\\report1.jrxml");
//            JasperReport jReport = JasperCompileManager.compileReport(jDesign);
//            JasperPrint jPrint = JasperFillManager.fillReport(jReport, hash, conn);
//            JasperViewer.viewReport(jPrint, false);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void purchasePay() {
//        purchaseCustomerId();
//        displayTotal();
//        String sql = "insert into bills(bill_customer_id,bill_total_payment,bill_date)"
//                + "values(?,?,?)";
//        conn = database.ConnectDB();
//        try {
//            if (TableView_customer.getItems().isEmpty()) {
//                InforBox("Choose???", null, "????");
//            } else {
//                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//                alert.setTitle("?");
//                alert.setHeaderText(null);
//                alert.setContentText("You sure??");
//                Optional<ButtonType> optional = alert.showAndWait();
//                if (optional.get().equals(ButtonType.OK)) {
//                    Date date = new Date();
//                    java.sql.Date sqlDate = new java.sql.Date(date.getTime());
//                    preparedStatement = conn.prepareStatement(sql);
//                    preparedStatement.setString(1, String.valueOf(customerId));
//                    preparedStatement.setString(2, String.valueOf(totalPP));
//                    preparedStatement.setString(3, String.valueOf(sqlDate));
//
//                    preparedStatement.executeUpdate();
//                    InforBox("Successfully Paid!", null, "OK");
//                    purchaseShowList();
//                    displayTotal();
//                    receipt();
//
//                }
//
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    public void cancelPurchae() {
//        purchaseCustomerId();
//        String sqlCancel = "delete from customers where customer_id='" + customerId + "'";
//        conn = database.ConnectDB();
//        try {
//            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//            alert.setTitle("cancel");
//            alert.setHeaderText(null);
//            alert.setContentText("???");
//            Optional<ButtonType> optional = alert.showAndWait();
//            if (optional.get().equals(ButtonType.OK)) {
//                statement = conn.createStatement();
//                statement.executeUpdate(sqlCancel);
//                InforBox("OK bro", null, "OK");
//                text_cate.setText("");
//                text_chooseProduct.getSelectionModel().clearSelection();
//                purchaseSpinner();
//                label_total.setText("$0.0");
//                purchaseShowList();
//
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//    private double totalPP = 0;
//
//    public void displayTotal() {
////        purchaseCustomerId();
//        String sql = "select sum(customer_totalPrice) as totalPrice from customers where customer_id='" + customerId + "'";
//        conn = database.ConnectDB();
//        try {
//            statement = conn.createStatement();
//            resultSet = statement.executeQuery(sql);
//            while (resultSet.next()) {
//                totalPP = resultSet.getDouble("totalPrice");
//            }
//            label_total.setText("$" + String.valueOf(totalPP));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void addPurchase() {
////        purchaseCustomerId();
//        getSpnnerValues();
//        getPrice();
//        String sqlAdd = "insert into customers(customer_id,customer_cate,customer_prodName,customer_quanlity,"
//                + "customer_totalPrice)"
//                + "values(?,?,?,?,?)";
//        conn = database.ConnectDB();
//        try {
//
//            if (text_cate.getText().isEmpty()
//                    || text_chooseProduct.getSelectionModel().getSelectedItem() == null
//                    || qty == 0) {
//                InforError("?????", null, "?");
//
//            } else {
//                preparedStatement = conn.prepareStatement(sqlAdd);
//                preparedStatement.setString(1, String.valueOf(customerId));
//                preparedStatement.setString(2, text_cate.getText());
//                preparedStatement.setString(3, (String) text_chooseProduct.getSelectionModel().getSelectedItem());
//                preparedStatement.setString(4, String.valueOf(qty));
//                totalP = (price * qty);
//                preparedStatement.setString(5, String.valueOf(totalP));
//                preparedStatement.executeUpdate();
//                purchaseShowList();
//                displayTotal();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//    private double totalP = 0;
//    private double price = 0;
//
//    public void getPrice() {
//        String sqlgetPrice = "select product_price from products where product_name='"
//                + text_chooseProduct.getSelectionModel().getSelectedItem() + "'";
//        conn = database.ConnectDB();
//        try {
//            statement = conn.createStatement();
//            resultSet = statement.executeQuery(sqlgetPrice);
//
//            if (resultSet.next()) {
//                price = resultSet.getDouble("product_price");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    public void searchCatergory() {
//        String sqlSearch = "select *from products where product_cate='" + text_cate.getText()
//                + "'and product_type='1'";
//        conn = database.ConnectDB();
//        try {
//            preparedStatement = conn.prepareStatement(sqlSearch);
//            resultSet = preparedStatement.executeQuery();
//            ObservableList listProduct = FXCollections.observableArrayList();
//            while (resultSet.next()) {
//                listProduct.add(resultSet.getString("product_name"));
//                text_chooseProduct.setItems(listProduct);
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//    private SpinnerValueFactory spinner;
//
//    public void purchaseSpinner() {
//        spinner = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 20, 0);
//
//        text_chooseQuanlity.setValueFactory(spinner);
//    }
//    private int qty;
//
//    public void getSpnnerValues() {
//        qty = text_chooseQuanlity.getValue();
//    }
//    private int customerId;
//
//    public void purchaseCustomerId() {
//        String sqlCId = "select customer_id from customers";
//        conn = database.ConnectDB();
//
//        try {
//
//            preparedStatement = conn.prepareStatement(sqlCId);
//            resultSet = preparedStatement.executeQuery();
//            while (resultSet.next()) {
//                customerId = resultSet.getInt("customer_id");
//            }
//            int checkId = 0;
//            String sqlcheck = "select bill_customer_id from bills";
//            statement = conn.createStatement();
//            resultSet = statement.executeQuery(sqlcheck);
//            while (resultSet.next()) {
//                checkId = resultSet.getInt("bill_customer_id");
//            }
//
//            if (customerId == 0) {
//                customerId+=1;
//            } else if (checkId == customerId) {
//                customerId +=1;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public ObservableList<empCusData> purchaseListData() {
//        purchaseCustomerId();
//        ObservableList<empCusData> custPurchaseList = FXCollections.observableArrayList();
//        String sql = "select *from customers where customer_id='" + customerId + "'";
//        conn = database.ConnectDB();
//        try {
//            empCusData cusData;
//            preparedStatement = conn.prepareStatement(sql);
//            resultSet = preparedStatement.executeQuery();
//            while (resultSet.next()) {
//                cusData = new empCusData(resultSet.getInt("customer_id"),
//                        resultSet.getString("customer_name"),
//                        resultSet.getString("customer_address"),
//                        resultSet.getString("customer_contact"),
//                        resultSet.getString("customer_email"),
//                        resultSet.getString("customer_cate"),
//                        resultSet.getString("customer_prodName"),
//                        resultSet.getInt("customer_quanlity"),
//                        resultSet.getDouble("customer_totalPrice"),
//                        resultSet.getInt("customer_rank"));
//                custPurchaseList.add(cusData);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return custPurchaseList;
//
//    }
//    private ObservableList<empCusData> purchaseList;
//
//    public void purchaseShowList() {
//        purchaseList = purchaseListData();
//
//        columnID.setCellValueFactory(new PropertyValueFactory<>("customerId"));
//        columnName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
//        columnAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
//        columncontact.setCellValueFactory(new PropertyValueFactory<>("customerPhone"));
//        columnEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
//        columncate.setCellValueFactory(new PropertyValueFactory<>("catergory"));
//        columnproName.setCellValueFactory(new PropertyValueFactory<>("productName"));
//        columnQuanlity.setCellValueFactory(new PropertyValueFactory<>("quanlity"));
//        columnPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
//        columnRank.setCellValueFactory(new PropertyValueFactory<>("rank"));
//        TableView_customer.setItems(purchaseList);
//    }
//
//    public void InforBox(String message, String headerText, String title) {
//        Alert alert = new Alert(Alert.AlertType.INFORMATION);
//        alert.setContentText(message);
//        alert.setHeaderText(headerText);
//        alert.setTitle(title);
//        alert.showAndWait();
//
//    }
//
//    public void InforError(String message, String headerText, String title) {
//        Alert alert = new Alert(Alert.AlertType.ERROR);
//        alert.setContentText(message);
//        alert.setHeaderText(headerText);
//        alert.setTitle(title);
//        alert.showAndWait();
//
//    }
//
//    @Override
//    public void initialize(URL url, ResourceBundle rb) {
//        // TODO
//        purchaseShowList();
//        purchaseSpinner();
//        searchCatergory();
//        displayTotal();
//
//    }
//
//}
