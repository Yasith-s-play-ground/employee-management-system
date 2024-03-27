package lk.ijse.dep12.fx.controls.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Employee {
    private String employeeId;
    private String name;
    private String adress;
    private String country;
    private String gender;
    ArrayList<String> contactNumbers;

    ArrayList<String> qualifications;
    LocalDate dateOfBirth;

    public Employee(String employeeId, String name, String adress, String country, String gender, ArrayList<String> contactNumbers, ArrayList<String> qualifications, LocalDate dateOfBirth) {
        this.employeeId = employeeId;
        this.name = name;
        this.adress = adress;
        this.country = country;
        this.gender = gender;
        this.contactNumbers = contactNumbers;
        this.qualifications = qualifications;
        this.dateOfBirth = dateOfBirth;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public String getAdress() {
        return adress;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public String getGender() {
        return gender;
    }

    public ArrayList<String> getContactNumbers() {
        return contactNumbers;
    }

    public ArrayList<String> getQualifications() {
        return qualifications;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }
}
