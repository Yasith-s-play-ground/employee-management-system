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
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class AddEmployeeViewController {
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
    private ArrayList<TextField> contactTexts = new ArrayList<>();

    private boolean onceTriedToSave = false;

    public void initialize() {
        clearTheForm();
        contactTexts.add(txtMainContact);
    }

    private boolean checkEmployeeIsAnAdult() throws ParseException {
        if (dtPckrBirthDay.getValue() != null) {
            lblDateOfBirth.setBackground(Background.fill(Color.TRANSPARENT));

            LocalDate birthDate = dtPckrBirthDay.getValue();
            LocalDate currentDate = LocalDate.now();
            Period period = Period.between(birthDate, currentDate);
            int noOfYears = period.getYears();
            //  System.out.println("No of years : " + noOfYears);
            if (noOfYears < 18) lblEmployeeIsNotAnAdult.setVisible(true);
            else lblEmployeeIsNotAnAdult.setVisible(false);
            return noOfYears >= 18;
        } else {
            lblDateOfBirth.setBackground(Background.fill(Color.RED));
        }
        return false;
    }

    private void enableRequiredControls() {
        txtName.setDisable(false);
        txtAddress.setDisable(false);
        txtCountry.setDisable(false);
        txtMainContact.setDisable(false);

    }

    private boolean showAndReturnWhetherUnfilledTextFieldsAndLabelsAvailable() {
        boolean emptyFieldsAvailable = false;

        //check employee id is generated
        if (lblEmployeeId.getText().isBlank()) {
            lblEmployeeId.setBackground(Background.fill(Color.RED));
            emptyFieldsAvailable = true;
        } else {
            lblEmployeeId.setBackground(Background.fill(Color.TRANSPARENT));
        }

        //check name is ok
        if (txtName.getText().isBlank()) {
            lblName.setBackground(Background.fill(Color.RED));
            txtName.setBorder(Border.stroke(Color.RED));
            emptyFieldsAvailable = true;
        } else {
            lblName.setBackground(Background.fill(Color.TRANSPARENT));
            txtName.setBorder(Border.stroke(Color.TRANSPARENT));
        }

        //check address is ok
        if (txtAddress.getText().isBlank()) {
            lblAddress.setBackground(Background.fill(Color.RED));
            txtAddress.setBorder(Border.stroke(Color.RED));
            emptyFieldsAvailable = true;
        } else {
            lblAddress.setBackground(Background.fill(Color.TRANSPARENT));
            txtAddress.setBorder(Border.stroke(Color.TRANSPARENT));

        }

        //check country is ok
        if (txtCountry.getText().isBlank()) {
            lblCountry.setBackground(Background.fill(Color.RED));
            txtCountry.setBorder(Border.stroke(Color.RED));
            emptyFieldsAvailable = true;
        } else {
            lblCountry.setBackground(Background.fill(Color.TRANSPARENT));
            txtCountry.setBorder(Border.stroke(Color.TRANSPARENT));

        }

        //check gender is ok
        if (!(rdButtonMale.isSelected() || rdButtonFemale.isSelected())) {
            lblGender.setBackground(Background.fill(Color.RED));
            rdButtonFemale.setBorder(Border.stroke(Color.RED));
            rdButtonMale.setBorder(Border.stroke(Color.RED));
            emptyFieldsAvailable = true;
        } else {
            lblGender.setBackground(Background.fill(Color.TRANSPARENT));
            rdButtonFemale.setBorder(Border.stroke(Color.TRANSPARENT));
            rdButtonMale.setBorder(Border.stroke(Color.TRANSPARENT));
        }

        //check contact is ok
        for (TextField contactText : contactTexts) {
            if (contactText.getText().isBlank()) {
                lblContact.setBackground(Background.fill(Color.RED));
                contactText.setBorder(Border.stroke(Color.RED));
                emptyFieldsAvailable = true;
            } else {
                lblContact.setBackground(Background.fill(Color.TRANSPARENT));
                contactText.setBorder(Border.stroke(Color.TRANSPARENT));

            }
        }

        //check qualifications ok
        if (chkBoxCertificate.isSelected()) { // certificate
            if (txtCertificate.getText().isBlank()) {
                lblQualifications.setBackground(Background.fill(Color.RED));
                txtCertificate.setBorder(Border.stroke(Color.RED));
                emptyFieldsAvailable = true;
            } else {
                lblQualifications.setBackground(Background.fill(Color.TRANSPARENT));
                txtCertificate.setBorder(Border.stroke(Color.TRANSPARENT));
            }
        }

        if (chkDiploma.isSelected()) { // diploma
            if (txtDiploma.getText().isBlank()) {
                lblQualifications.setBackground(Background.fill(Color.RED));
                txtDiploma.setBorder(Border.stroke(Color.RED));
                emptyFieldsAvailable = true;
            } else {
                lblQualifications.setBackground(Background.fill(Color.TRANSPARENT));
                txtDiploma.setBorder(Border.stroke(Color.TRANSPARENT));
            }
        }

        if (chkBSc.isSelected()) { // BSc
            if (txtBSc.getText().isBlank()) {
                lblQualifications.setBackground(Background.fill(Color.RED));
                txtBSc.setBorder(Border.stroke(Color.RED));
                emptyFieldsAvailable = true;
            } else {
                lblQualifications.setBackground(Background.fill(Color.TRANSPARENT));
                txtBSc.setBorder(Border.stroke(Color.TRANSPARENT));
            }
        }

        if (chkMSc.isSelected()) { // MSc
            if (txtMSc.getText().isBlank()) {
                lblQualifications.setBackground(Background.fill(Color.RED));
                txtMSc.setBorder(Border.stroke(Color.RED));
                emptyFieldsAvailable = true;
            } else {
                lblQualifications.setBackground(Background.fill(Color.TRANSPARENT));
                txtMSc.setBorder(Border.stroke(Color.TRANSPARENT));
            }
        }

        //check date
        try {
            if (!checkEmployeeIsAnAdult()) emptyFieldsAvailable = true;
        } catch (ParseException e) {
            System.out.println("Error in date");
        }

        return emptyFieldsAvailable;
    }

    private void enableSaveButton() {
//        if (!txtName.getText().isBlank()) {
//            lblName.setBackground(Background.fill(Color.TRANSPARENT));
//            if (!txtAddress.getText().isBlank()) {
//                lblAddress.setBackground(Background.fill(Color.TRANSPARENT));
//                if (!txtCountry.getText().isBlank()) {
//
//                } else {
//                    lblCountry.setBackground(Background.fill(Color.RED));
//
//                }
//            } else {
//                lblAddress.setBackground(Background.fill(Color.RED));
//
//            }
//        } else {
//            lblName.setBackground(Background.fill(Color.RED));
//
//        }
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
        rdButtonMale.setSelected(false);

        txtMainContact.setDisable(true);
        txtMainContact.clear();
        btnAddAnotherContact.setDisable(true);
        btnAddAnotherContact.setVisible(true);
        for (TextField contactText : contactTexts) {
            if (contactText.equals(txtMainContact)) continue;
            vBoxContacts.getChildren().remove(contactText.getParent());
        }

        chkBoxCertificate.setSelected(false);
        txtCertificate.setVisible(false);
        txtCertificate.clear();

        chkDiploma.setSelected(false);
        txtDiploma.setVisible(false);
        txtDiploma.clear();

        chkBSc.setSelected(false);
        txtBSc.setVisible(false);
        txtBSc.clear();

        chkMSc.setSelected(false);
        txtMSc.setVisible(false);
        txtMSc.clear();

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
        btnSave.setDisable(false);
    }


    public void txtNameOnAction(ActionEvent actionEvent) {
        if (onceTriedToSave) focusOnRequiredFields();
        else txtAddress.requestFocus();

    }

    public void txtCountryOnAction(ActionEvent actionEvent) {
        if (onceTriedToSave) focusOnRequiredFields();
        else rdButtonMale.requestFocus();
    }

    public void rdButtonFemaleOnAction(ActionEvent actionEvent) {
        //enableSaveButton();
        if (onceTriedToSave) focusOnRequiredFields();
        else txtMainContact.requestFocus();

    }

    public void btnAddAnotherContactOnAction(ActionEvent actionEvent) {
        btnAddAnotherContact.setVisible(false);
        enableSaveButton();

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
            if (onceTriedToSave) showAndReturnWhetherUnfilledTextFieldsAndLabelsAvailable();
        });

        btnRemove1.setOnAction(actionEvent1 -> {
            vBoxContacts.getChildren().remove(hBox1);
            btnAddAnotherContact.setVisible(true);
            contactTexts.remove(txt1);
            if (onceTriedToSave) {
                showAndReturnWhetherUnfilledTextFieldsAndLabelsAvailable();
                focusOnRequiredFields();
            }
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

                if (onceTriedToSave) {
                    showAndReturnWhetherUnfilledTextFieldsAndLabelsAvailable();
                    focusOnRequiredFields();
                }
            });

            txt2.setOnKeyReleased(keyEvent -> {
                enableSaveButton();
                if (onceTriedToSave) showAndReturnWhetherUnfilledTextFieldsAndLabelsAvailable();
            });

        });
    }

    public void dtPckrBirthDayOnAction(ActionEvent actionEvent) {
        btnSave.requestFocus();
        if (onceTriedToSave) {
            showAndReturnWhetherUnfilledTextFieldsAndLabelsAvailable();
            focusOnRequiredFields();
        }
    }

    public void chkMScOnAction(ActionEvent actionEvent) {
        // enableSaveButton();
        if (chkMSc.isSelected()) {
            txtMSc.setVisible(true);
            txtMSc.requestFocus();
        } else txtMSc.setVisible(false);

        if (onceTriedToSave) {
            focusOnRequiredFields();
            showAndReturnWhetherUnfilledTextFieldsAndLabelsAvailable();
        }
    }

    public void chkBScOnAction(ActionEvent actionEvent) {
        if (chkBSc.isSelected()) {
            txtBSc.setVisible(true);
            txtBSc.requestFocus();
        } else txtBSc.setVisible(false);
        if (onceTriedToSave) {
            focusOnRequiredFields();
            showAndReturnWhetherUnfilledTextFieldsAndLabelsAvailable();
        }

    }

    public void chkDiplomaOnAction(ActionEvent actionEvent) {
        if (chkDiploma.isSelected()) {
            txtDiploma.setVisible(true);
            txtDiploma.requestFocus();
        } else txtDiploma.setVisible(false);
        if (onceTriedToSave) {
            focusOnRequiredFields();
            showAndReturnWhetherUnfilledTextFieldsAndLabelsAvailable();
        }

    }

    public void chkBoxCertificateOnAction(ActionEvent actionEvent) {
        enableSaveButton();
        if (chkBoxCertificate.isSelected()) {
            txtCertificate.setVisible(true);
            txtCertificate.requestFocus();
        } else txtCertificate.setVisible(false);
        if (onceTriedToSave) {
            focusOnRequiredFields();
            showAndReturnWhetherUnfilledTextFieldsAndLabelsAvailable();
        }

    }

    public void btnSaveOnAction(ActionEvent actionEvent) throws ParseException {
        onceTriedToSave = true;
        if (!showAndReturnWhetherUnfilledTextFieldsAndLabelsAvailable()) {
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
        txtCountry.requestFocus();
        if (onceTriedToSave) focusOnRequiredFields();
    }

    public void rdButtonMaleOnAction(ActionEvent actionEvent) {
        txtMainContact.requestFocus();
        if (onceTriedToSave) focusOnRequiredFields();
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
        if (onceTriedToSave) showAndReturnWhetherUnfilledTextFieldsAndLabelsAvailable();

    }

    public void txtNameOnKeyReleased(KeyEvent keyEvent) {
        enableSaveButton();
        if (onceTriedToSave) showAndReturnWhetherUnfilledTextFieldsAndLabelsAvailable();
    }

    public void txtAddressOnKeyReleased(KeyEvent keyEvent) {
        enableSaveButton();
        if (onceTriedToSave) showAndReturnWhetherUnfilledTextFieldsAndLabelsAvailable();
    }

    public void txtCountryOnKeyReleased(KeyEvent keyEvent) {
        enableSaveButton();
        if (onceTriedToSave) showAndReturnWhetherUnfilledTextFieldsAndLabelsAvailable();
    }

    public void txtCertificateOnKeyReleased(KeyEvent keyEvent) {
        enableSaveButton();
        if (onceTriedToSave) showAndReturnWhetherUnfilledTextFieldsAndLabelsAvailable();
    }

    public void txtDiplomaOnKeyReleased(KeyEvent keyEvent) {
        enableSaveButton();
        if (onceTriedToSave) showAndReturnWhetherUnfilledTextFieldsAndLabelsAvailable();
    }

    public void txtBScOnKeyReleased(KeyEvent keyEvent) {
        enableSaveButton();
        if (onceTriedToSave) showAndReturnWhetherUnfilledTextFieldsAndLabelsAvailable();
    }

    public void txtMScOnKeyReleased(KeyEvent keyEvent) {
        enableSaveButton();
        if (onceTriedToSave) showAndReturnWhetherUnfilledTextFieldsAndLabelsAvailable();
    }

    public void txtCertificateOnAction(ActionEvent actionEvent) {
        if (onceTriedToSave) focusOnRequiredFields();
        else chkDiploma.requestFocus();
    }

    public void txtDiplomaOnAction(ActionEvent actionEvent) {
        if (onceTriedToSave) focusOnRequiredFields();
        else chkBSc.requestFocus();

    }

    public void txtBScOnAction(ActionEvent actionEvent) {
        if (onceTriedToSave) focusOnRequiredFields();
        else chkMSc.requestFocus();
    }

    public void txtMScOnAction(ActionEvent actionEvent) {
        if (onceTriedToSave) focusOnRequiredFields();
        else dtPckrBirthDay.requestFocus();
    }
}

