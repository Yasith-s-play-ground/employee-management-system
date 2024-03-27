package lk.ijse.dep12.fx.controls.controller.model_controller;

import lk.ijse.dep12.fx.controls.model.Employee;

import java.util.ArrayList;

public class EmployeeController {
    private static ArrayList<Employee> employeeArrayList = new ArrayList<>();

    public static String getNextEmployeeId() {
        if (employeeArrayList.isEmpty()) {
            return "E001"; // return first id
        } else {
            //Employee lastEmployee = employeeArrayList.getLast();
            int lastIdNum = Integer.parseInt(employeeArrayList.getLast().getEmployeeId().substring(1)); // get integer past of last id
            return String.format("E%03d", lastIdNum + 1); // return next id
        }

    }

    public static void addEmployeeToList(Employee employee) {
        employeeArrayList.add(employee);
    }

    public static ArrayList<Employee> getAllEmployees() {
        return employeeArrayList;
    }
}
