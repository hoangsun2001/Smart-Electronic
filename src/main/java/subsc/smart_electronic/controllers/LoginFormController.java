/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package subsc.smart_electronic.controllers;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import subsc.smart_electronic.db.database;
import subsc.smart_electronic.connectViews.electronicConnectViews;
import subsc.smart_electronic.models.getData;

/**
 * FXML Controller class
 *
 * @author Admin
 */
public class LoginFormController implements Initializable {

    @FXML
    private AnchorPane mainForm;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private Button bnt_signIn;

    @FXML
    private Button btn_close;

    @FXML
    private Hyperlink optionLogin;
    private Connection conn;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;

    private double x = 0;
    private double y = 0;

    public void adminLogin() {
        String adminData = "select *from admin where username=? and password=?";

        conn = database.ConnectDB();
        try {
            if (username.getText().isEmpty() || password.getText().isEmpty()) {
                infoError("Please fill all the blank fields!", null, "Error message");
            } else {
                preparedStatement = conn.prepareStatement(adminData);
                preparedStatement.setString(1, username.getText());
                preparedStatement.setString(2, password.getText());

                resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    getData.AdminUsername = username.getText();
                    infoBox("Login Successfully!", null, "Infromation");
                    bnt_signIn.getScene().getWindow().hide();
                    Parent root = FXMLLoader.load(getClass().getResource(electronicConnectViews.admin_dashboard));
                    Stage stage = new Stage();
                    Scene scene = new Scene(root);

                    root.setOnMousePressed((MouseEvent event) -> {
                        x = event.getSceneX();
                        y = event.getSceneY();
                    });
                    root.setOnMouseDragged((MouseEvent event) -> {
                        stage.setX(event.getScreenX() - x);
                        stage.setY(event.getScreenY() - y);
                        stage.setOpacity(.8);
                    });
                    root.setOnMouseReleased((MouseEvent event) -> {
                        stage.setOpacity(1);
                    });
                    stage.initStyle(StageStyle.TRANSPARENT);
                    stage.setScene(scene);
                    stage.show();

                } else {
                    infoError("The user name or password is wrong!", null, "Error message");
                    username.setText("");
                    password.setText("");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void close() {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Do you want to close?");
        alert.setHeaderText(null);
        alert.setTitle("Confirmation");

        Optional<ButtonType> optional = alert.showAndWait();
        if (optional.get().equals(ButtonType.OK)) {
            System.exit(0);
        }

    }

    public static void infoBox(String infoMessage, String headerText, String title) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }

    public static void infoError(String infoMessage, String headerText, String title) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
