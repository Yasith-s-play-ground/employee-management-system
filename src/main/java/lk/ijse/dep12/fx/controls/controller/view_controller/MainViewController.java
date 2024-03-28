package lk.ijse.dep12.fx.controls.controller.view_controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.dep12.fx.controls.controller.model_controller.EmployeeController;
import lk.ijse.dep12.fx.controls.model.Employee;

import java.io.IOException;
import java.util.ArrayList;

public class MainViewController {
    public AnchorPane root;
    public Button btnLogout;
    public AnchorPane windowDisplayPane;
    public Button btnAddEmployee;
    public Button btnPrintEmployees;

    public static int access;

    public void initialize() {
        if (access == 2) {
            btnAddEmployee.setDisable(true);
        }
    }

    public void btnLogoutOnAction(ActionEvent actionEvent) throws IOException {
        Stage loginStage = new Stage();
        loginStage.setResizable(false);
        loginStage.centerOnScreen();
        loginStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/LoginView.fxml"))));
        loginStage.show();

        ((Stage) root.getScene().getWindow()).close();
    }

    public void btnAddEmployeeOnAction(ActionEvent actionEvent) throws IOException {
        // AnchorPane container = FXMLLoader.load(getClass().getResource("/view/AddEmployeeView.fxml"));
        AnchorPane container = FXMLLoader.load(getClass().getResource("/view/ManageEmployeeView.fxml"));
        windowDisplayPane.getChildren().clear();
        windowDisplayPane.getChildren().add(container);
        AnchorPane.setLeftAnchor(container, 0.0);
        AnchorPane.setRightAnchor(container, 0.0);
        AnchorPane.setTopAnchor(container, 0.0);
        AnchorPane.setBottomAnchor(container, 0.0);

    }

    public void btnPrintEmployessOnAction(ActionEvent actionEvent) {
        ArrayList<Employee> allEmployees = EmployeeController.getAllEmployees();
        for (Employee employee : allEmployees) {
            System.out.print("Employee id : " + employee.getEmployeeId() +
                    "\nName : " + employee.getName() + "\nAddress : " + employee.getAdress()
                    + "\nCountry : " + employee.getCountry());

            System.out.print("\nContacts : ");
            for (String contactNumber : employee.getContactNumbers()) {
                System.out.print(contactNumber + (contactNumber.equals(employee.getContactNumbers().getLast()) ? " " : ", "));
            }

            System.out.print("\nQualifications : ");
            for (String qualification : employee.getQualifications()) {
                System.out.print(qualification + (qualification.equals(employee.getQualifications().getLast()) ? " " : ", "));
            }

            System.out.println("\nDate of Birth : " + employee.getDateOfBirth());
            System.out.println("---------------------------------------");
        }

    }
}
