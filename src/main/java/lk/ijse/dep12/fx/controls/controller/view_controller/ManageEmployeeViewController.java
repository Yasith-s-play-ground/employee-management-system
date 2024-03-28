package lk.ijse.dep12.fx.controls.controller.view_controller;

import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import lk.ijse.dep12.fx.controls.controller.model_controller.EmployeeController;
import lk.ijse.dep12.fx.controls.model.Employee;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

public class ManageEmployeeViewController {
    public Label lblEmployeeId;
    public TextField txtName;
    public TextField txtAddress;
    public TextField txtCountry;
    public RadioButton rdButtonMale;
    public ToggleGroup rdBtnGroupGender;
    public RadioButton rdButtonFemale;
    public VBox vBoxContacts;
    public TextField txtMainContact;
    public Button btnAddAnotherContact;
    public CheckBox chkBoxCertificate;
    public TextField txtCertificate;
    public CheckBox chkDiploma;
    public TextField txtDiploma;
    public CheckBox chkBSc;
    public TextField txtBSc;
    public CheckBox chkMSc;
    public TextField txtMSc;
    public DatePicker dtPckrBirthDay;
    public Button btnSave;
    public Label lblName;
    public Label lblAddress;
    public Label lblCountry;
    public Label lblGender;
    public Label lblContact;
    public Label lblQualifications;
    public Label lblDateOfBirth;
    public Label lblEmployeeIsNotAnAdult;
    public Label lblId;
    private ArrayList<TextField> contactTexts = new ArrayList<>();

    private boolean onceTriedToSave = false;

    public void initialize() {
        clearTheForm();
        contactTexts.add(txtMainContact);
    }

    private boolean checkEmployeeIsAnAdult() throws ParseException {
        if (dtPckrBirthDay.getValue() != null) {
            LocalDate birthDate = dtPckrBirthDay.getValue();
            LocalDate currentDate = LocalDate.now();
            Period period = Period.between(birthDate, currentDate);
            int noOfYears = period.getYears();
            if (noOfYears < 18) lblEmployeeIsNotAnAdult.setVisible(true);
            else lblEmployeeIsNotAnAdult.setVisible(false);
            return noOfYears >= 18;
        }
        return false;
    }

    private void enableRequiredControls() {

        rdButtonMale.setDisable(false);
        rdButtonFemale.setDisable(false);
        for (CheckBox checkBox : new CheckBox[]{chkBoxCertificate, chkDiploma, chkBSc, chkMSc}) {
            checkBox.setDisable(false);
        }
        for (TextField textField : new TextField[]{txtName, txtCountry, txtAddress, txtMainContact, txtCertificate, txtDiploma, txtBSc, txtMSc}) {
            textField.setDisable(false);
        }
        btnSave.setDisable(false);
        dtPckrBirthDay.setDisable(false);
        txtName.requestFocus();

    }

    private boolean validation() {
        boolean validation = true;

        for (Control control : new Control[]{
                lblDateOfBirth, lblEmployeeId, lblName, txtName, lblAddress, txtAddress, lblCountry, txtCountry, rdButtonFemale, rdButtonMale, lblGender, lblContact, txtMainContact, lblQualifications, chkBoxCertificate, txtCertificate, chkDiploma, txtDiploma, chkBSc, txtBSc, chkMSc, txtMSc, dtPckrBirthDay
        }) {
            control.getStyleClass().remove("error"); // remove error class
        }


        //check date
        try {
            if (!checkEmployeeIsAnAdult()) {
                lblDateOfBirth.getStyleClass().add("error");
                dtPckrBirthDay.requestFocus();
                validation = false;
            }
        } catch (ParseException e) {
            System.out.println("Error in date");
        }

        if (chkMSc.isSelected()) { // MSc
            if (txtMSc.getText().isBlank()) {
                txtMSc.getStyleClass().add("error");
                lblQualifications.getStyleClass().add("error");
                txtMSc.requestFocus();
                validation = false;
            }
        }

        if (chkBSc.isSelected()) { // BSc
            if (txtBSc.getText().isBlank()) {
                lblQualifications.getStyleClass().add("error");
                txtBSc.getStyleClass().add("error");
                txtBSc.requestFocus();
                validation = false;
            }
        }

        if (chkDiploma.isSelected()) { // diploma
            if (txtDiploma.getText().isBlank()) {
                lblQualifications.getStyleClass().add("error");
                txtDiploma.getStyleClass().add("error");
                txtDiploma.requestFocus();
                validation = false;
            }
        }

        //check qualifications ok
        if (chkBoxCertificate.isSelected()) { // certificate
            if (txtCertificate.getText().isBlank()) {
                lblQualifications.getStyleClass().add("error");
                txtCertificate.getStyleClass().add("error");
                txtCertificate.requestFocus();
                validation = false;
            }
        }
//check contact is ok
        for (TextField contactText : contactTexts) {
            if (contactText.getText().isBlank()) {
                lblContact.getStyleClass().add("error");
                contactText.getStyleClass().add("error");
                validation = false;
                contactText.requestFocus();
            }
        }

        //check gender is ok
        if (rdBtnGroupGender.getSelectedToggle() == null) {
            lblGender.getStyleClass().add("error");
            rdButtonFemale.getStyleClass().add("error");
            rdButtonMale.getStyleClass().add("error");
            rdButtonMale.requestFocus();
            validation = false;
        }

        //check country is ok
        if (txtCountry.getText().isBlank()) {
            lblCountry.getStyleClass().add("error");
            txtCountry.getStyleClass().add("error");
            txtCountry.requestFocus();
            validation = false;
        }


        //check address is ok
        if (txtAddress.getText().isBlank()) {
            lblAddress.getStyleClass().add("error");
            txtAddress.getStyleClass().add("error");
            txtAddress.requestFocus();
            validation = false;

        }

        //check name is ok
        if (txtName.getText().isBlank()) {
            lblName.getStyleClass().add("error");
            txtName.getStyleClass().add("error");
            txtName.requestFocus();
            validation = false;

        }

        //check employee id is generated
        if (lblEmployeeId.getText().isBlank()) {
            lblEmployeeId.getStyleClass().add("error");
            validation = false;

        }

        return validation;
    }


    private void clearTheForm() {
        onceTriedToSave = false;
        lblEmployeeIsNotAnAdult.setVisible(false);
        lblEmployeeId.setText("");
        btnSave.setDisable(true);
        txtName.setDisable(true);
        txtName.clear();

        txtAddress.setDisable(true);
        txtAddress.clear();

        txtCountry.setDisable(true);
        txtCountry.clear();

        rdButtonFemale.setSelected(false);
        rdButtonFemale.setDisable(true);
        rdButtonMale.setSelected(false);
        rdButtonMale.setDisable(true);

//        txtMainContact.setDisable(true);
//        txtMainContact.clear();
        btnAddAnotherContact.setDisable(true);
        btnAddAnotherContact.setVisible(true);
        for (TextField contactText : contactTexts) {
            if (contactText.equals(txtMainContact)) continue;
            vBoxContacts.getChildren().remove(contactText.getParent());
        }

        for (CheckBox checkBox : new CheckBox[]{chkBoxCertificate, chkDiploma, chkBSc, chkMSc}) {
            checkBox.setSelected(false);
            checkBox.setDisable(true);
        }
        for (TextField textField : new TextField[]{txtName, txtCountry, txtAddress, txtMainContact, txtCertificate, txtDiploma, txtBSc, txtMSc}) {
            textField.clear();
            textField.setDisable(true);
        }
        for (TextField textField : new TextField[]{txtCertificate, txtDiploma, txtBSc, txtMSc}) {
            textField.setVisible(false);
        }
        dtPckrBirthDay.setDisable(true);
        dtPckrBirthDay.setValue(null);

    }

    private void focusOnRequiredFields() {

//        //check employee id is generated
//        if (lblEmployeeId.getText().isBlank()) {
//            lblEmployeeId.setBackground(Background.fill(Color.RED));
//            emptyFieldsAvailable = true;
//        } else {
//            lblEmployeeId.setBackground(Background.fill(Color.TRANSPARENT));
//        }

        //check name is ok
        if (txtName.getText().isBlank()) {
            txtName.requestFocus();
        }

        //check address is ok
        else if (txtAddress.getText().isBlank()) {
            txtAddress.requestFocus();
        }

        //check country is ok
        else if (txtCountry.getText().isBlank()) {
            txtCountry.requestFocus();
        }

        //check gender is ok
        else if (!(rdButtonMale.isSelected() || rdButtonFemale.isSelected())) {
            rdButtonMale.requestFocus();
        } else if (contactTexts.size() > 0) {
            //check contact is ok
            for (TextField contactText : contactTexts) {
                if (contactText.getText().isBlank()) {
                    contactText.requestFocus();
                    break;
                }
            }
        }


        //check qualifications ok
        else if (chkBoxCertificate.isSelected()) { // certificate
            if (txtCertificate.getText().isBlank()) {
                txtCertificate.requestFocus();
            }
        } else if (chkDiploma.isSelected()) { // diploma
            if (txtDiploma.getText().isBlank()) {
                txtDiploma.requestFocus();
            }
        } else if (chkBSc.isSelected()) { // BSc
            if (txtBSc.getText().isBlank()) {
                txtBSc.requestFocus();
            }
        } else if (chkMSc.isSelected()) { // MSc
            if (txtMSc.getText().isBlank()) {
                txtMSc.requestFocus();
            }
        } else {
            //check date
            try {
                if (!checkEmployeeIsAnAdult()) dtPckrBirthDay.requestFocus();
            } catch (ParseException e) {
                System.out.println("Error in date");
            }
        }
    }

    public void btnNewEmployeeOnAction(ActionEvent actionEvent) {
        lblEmployeeId.setText(EmployeeController.getNextEmployeeId());
//        for (TextField contactText : contactTexts) {
//            if (contactText.equals(txtMainContact)) continue;
//            contactTexts.remove(contactText);
//        }
        enableRequiredControls();
        //btnSave.setDisable(false);
    }


    public void txtNameOnAction(ActionEvent actionEvent) {
//        if (onceTriedToSave) focusOnRequiredFields();
//        else txtAddress.requestFocus();

    }

    public void txtCountryOnAction(ActionEvent actionEvent) {
//        if (onceTriedToSave) focusOnRequiredFields();
//        else rdButtonMale.requestFocus();
    }

    public void rdButtonFemaleOnAction(ActionEvent actionEvent) {
        //enableSaveButton();
//        if (onceTriedToSave) focusOnRequiredFields();
//        else txtMainContact.requestFocus();

    }

    public void btnAddAnotherContactOnAction(ActionEvent actionEvent) {
        btnAddAnotherContact.setVisible(false);

        TextField txt1 = new TextField();
        Button btnRemove1 = new Button("-");
        Button btnAdd1 = new Button("+");
        btnAdd1.setDisable(true);
        HBox hBox1 = new HBox(txt1, btnAdd1, btnRemove1);
        contactTexts.add(txt1);
        vBoxContacts.getChildren().add(hBox1);
        txt1.requestFocus();
        txt1.setOnKeyReleased(keyEvent -> {
            btnAdd1.setDisable(txt1.getText().isBlank());
            //if (onceTriedToSave) showAndReturnWhetherUnfilledTextFieldsAndLabelsAvailable();
        });

        btnRemove1.setOnAction(actionEvent1 -> {
            vBoxContacts.getChildren().remove(hBox1);
            btnAddAnotherContact.setVisible(true);
            contactTexts.remove(txt1);
//            if (onceTriedToSave) {
//                showAndReturnWhetherUnfilledTextFieldsAndLabelsAvailable();
//                focusOnRequiredFields();
//            }
        });

        btnAdd1.setOnAction(actionEvent1 -> {
            btnRemove1.setVisible(false);
            btnAdd1.setVisible(false);
            TextField txt2 = new TextField();
            Button btnRemove2 = new Button("-");
            HBox hBox2 = new HBox(txt2, btnRemove2);
            vBoxContacts.getChildren().add(hBox2);
            contactTexts.add(txt2);
            txt2.requestFocus();
            btnRemove2.setOnAction(actionEvent2 -> {
                vBoxContacts.getChildren().remove(hBox2);
                btnAdd1.setVisible(true);
                btnRemove1.setVisible(true);
                contactTexts.remove(txt2);

//                if (onceTriedToSave) {
//                    showAndReturnWhetherUnfilledTextFieldsAndLabelsAvailable();
//                    focusOnRequiredFields();
//                }
            });

//            txt2.setOnKeyReleased(keyEvent -> {
//                if (onceTriedToSave) showAndReturnWhetherUnfilledTextFieldsAndLabelsAvailable();
//            });

        });
    }

    public void dtPckrBirthDayOnAction(ActionEvent actionEvent) {
        btnSave.requestFocus();
//        if (onceTriedToSave) {
//            showAndReturnWhetherUnfilledTextFieldsAndLabelsAvailable();
//            focusOnRequiredFields();
//        }
    }

    public void chkMScOnAction(ActionEvent actionEvent) {
        // enableSaveButton();
        if (chkMSc.isSelected()) {
            txtMSc.setVisible(true);
            txtMSc.requestFocus();
        } else txtMSc.setVisible(false);

//        if (onceTriedToSave) {
//            focusOnRequiredFields();
//            showAndReturnWhetherUnfilledTextFieldsAndLabelsAvailable();
//        }
    }

    public void chkBScOnAction(ActionEvent actionEvent) {
        if (chkBSc.isSelected()) {
            txtBSc.setVisible(true);
            txtBSc.requestFocus();
        } else txtBSc.setVisible(false);
//        if (onceTriedToSave) {
//            focusOnRequiredFields();
//            showAndReturnWhetherUnfilledTextFieldsAndLabelsAvailable();
//        }

    }

    public void chkDiplomaOnAction(ActionEvent actionEvent) {
        if (chkDiploma.isSelected()) {
            txtDiploma.setVisible(true);
            txtDiploma.requestFocus();
        } else txtDiploma.setVisible(false);
//        if (onceTriedToSave) {
//            focusOnRequiredFields();
//            showAndReturnWhetherUnfilledTextFieldsAndLabelsAvailable();
//        }

    }

    public void chkBoxCertificateOnAction(ActionEvent actionEvent) {
        if (chkBoxCertificate.isSelected()) {
            txtCertificate.setVisible(true);
            txtCertificate.requestFocus();
        } else txtCertificate.setVisible(false);
//        if (onceTriedToSave) {
//            focusOnRequiredFields();
//            showAndReturnWhetherUnfilledTextFieldsAndLabelsAvailable();
//        }

    }

    public void btnSaveOnAction(ActionEvent actionEvent) throws ParseException {
        onceTriedToSave = true;
        if (validation()) {
            String employeeId = lblEmployeeId.getText();
            String name = txtName.getText();
            String address = txtAddress.getText();
            String country = txtCountry.getText();

            String gender = "";
            if (rdButtonMale.isSelected()) gender = "Male";
            else if (rdButtonFemale.isSelected()) gender = "Female";

            ArrayList<String> contacts = new ArrayList<>();
            for (TextField contactText : contactTexts) {
                contacts.add(contactText.getText());
            }

            ArrayList<String> qualifications = new ArrayList<>();
            if (chkBoxCertificate.isSelected()) qualifications.add("Certificate : " + txtCertificate.getText());
            if (chkDiploma.isSelected()) qualifications.add("Diploma : " + txtDiploma.getText());
            if (chkBSc.isSelected()) qualifications.add("BSc : " + txtBSc.getText());
            if (chkMSc.isSelected()) qualifications.add("MSc : " + txtMSc.getText());

            LocalDate dateOfBirth = dtPckrBirthDay.getValue();

            Employee employee = new Employee(employeeId, name, address, country, gender, contacts, qualifications, dateOfBirth);
            EmployeeController.addEmployeeToList(employee);

            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Employee successfully added to the list");
            alert.show();

            clearTheForm();
        } else {
            focusOnRequiredFields();
        }

    }

    public void txtAddressOnAction(ActionEvent actionEvent) {
        //txtCountry.requestFocus();
        // if (onceTriedToSave) focusOnRequiredFields();
    }

    public void rdButtonMaleOnAction(ActionEvent actionEvent) {
//        txtMainContact.requestFocus();
//        if (onceTriedToSave) focusOnRequiredFields();
    }

    public void txtMainContactOnAction(ActionEvent actionEvent) {
        if (onceTriedToSave) focusOnRequiredFields();
        else {
            if (contactTexts.size() > 1) {
                for (TextField contactText : contactTexts) {
                    if (contactText.equals(txtMainContact)) continue;
                    contactText.requestFocus();
                    break;
                }
            } else {
                chkBoxCertificate.requestFocus();
            }
        }
    }

    public void txtMainContactOnKeyReleased(KeyEvent keyEvent) {
        btnAddAnotherContact.setDisable(txtMainContact.getText().isBlank());
        //if (onceTriedToSave) showAndReturnWhetherUnfilledTextFieldsAndLabelsAvailable();

    }

    public void txtNameOnKeyReleased(KeyEvent keyEvent) {
        // if (onceTriedToSave) showAndReturnWhetherUnfilledTextFieldsAndLabelsAvailable();
    }

    public void txtAddressOnKeyReleased(KeyEvent keyEvent) {
        // if (onceTriedToSave) showAndReturnWhetherUnfilledTextFieldsAndLabelsAvailable();
    }

    public void txtCountryOnKeyReleased(KeyEvent keyEvent) {
        // if (onceTriedToSave) showAndReturnWhetherUnfilledTextFieldsAndLabelsAvailable();
    }

    public void txtCertificateOnKeyReleased(KeyEvent keyEvent) {
        // if (onceTriedToSave) showAndReturnWhetherUnfilledTextFieldsAndLabelsAvailable();
    }

    public void txtDiplomaOnKeyReleased(KeyEvent keyEvent) {
        //if (onceTriedToSave) showAndReturnWhetherUnfilledTextFieldsAndLabelsAvailable();
    }

    public void txtBScOnKeyReleased(KeyEvent keyEvent) {
        // if (onceTriedToSave) showAndReturnWhetherUnfilledTextFieldsAndLabelsAvailable();
    }

    public void txtMScOnKeyReleased(KeyEvent keyEvent) {
        //if (onceTriedToSave) showAndReturnWhetherUnfilledTextFieldsAndLabelsAvailable();
    }

    public void txtCertificateOnAction(ActionEvent actionEvent) {
        //if (onceTriedToSave) focusOnRequiredFields();
        //else chkDiploma.requestFocus();
    }

    public void txtDiplomaOnAction(ActionEvent actionEvent) {
//        if (onceTriedToSave) focusOnRequiredFields();
//        else chkBSc.requestFocus();

    }

    public void txtBScOnAction(ActionEvent actionEvent) {
//        if (onceTriedToSave) focusOnRequiredFields();
//        else chkMSc.requestFocus();
    }

    public void txtMScOnAction(ActionEvent actionEvent) {
//        if (onceTriedToSave) focusOnRequiredFields();
//        else dtPckrBirthDay.requestFocus();
    }
}

