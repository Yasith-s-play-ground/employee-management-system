package lk.ijse.dep12.fx.controls.controller.view_controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

public class LoginViewController {
    public AnchorPane root;
    public TextField txtUserName;
    public PasswordField txtPassword;
    public Button btnLogin;
    public Label lblInvalidLoginText;

    public void initialize() {
        lblInvalidLoginText.setText("");
        btnLogin.setDisable(true);
    }

    public void txtUserNameOnAction(ActionEvent actionEvent) {
        txtPassword.requestFocus();
    }

    public void txtPasswordOnAction(ActionEvent actionEvent) {
        btnLogin.requestFocus();
    }

    public void btnLoginOnAction(ActionEvent actionEvent) throws IOException {
        if ((txtUserName.getText().equals("admin") && txtPassword.getText().equals("admin")) || (txtUserName.getText().equals("user") && txtPassword.getText().equals("user"))) {

            //setting access level
            if (txtUserName.getText().equals("admin")) MainViewController.access = 1;
            else MainViewController.access = 2;

            lblInvalidLoginText.setText("");
            Stage mainStage = new Stage();
            mainStage.centerOnScreen();
            mainStage.setTitle("Main Form");

            //to disable closing program from main window
            mainStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent windowEvent) {
                    windowEvent.consume();
                }
            });

            mainStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/MainView.fxml"))));
            mainStage.show();

            //closing login window
            ((Stage) root.getScene().getWindow()).close();

        } else {
            lblInvalidLoginText.setText("Invalid login details. Please Enter valid details!");
            txtUserName.requestFocus();
        }
    }

    public void txtUserNameOnKeyReleased(KeyEvent keyEvent) {
        btnLogin.setDisable(txtUserName.getText().isEmpty() || txtPassword.getText().isEmpty());
    }

    public void txtPasswordOnKeyReleased(KeyEvent keyEvent) {
        btnLogin.setDisable(txtUserName.getText().isEmpty() || txtPassword.getText().isEmpty());

    }
}
