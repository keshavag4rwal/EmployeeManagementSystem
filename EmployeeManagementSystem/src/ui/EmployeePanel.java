package ui;

import dao.EmployeeDAO;
import model.Employee;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EmployeePanel extends JPanel {
    private JTextField nameField;
    private JTextField positionField;
    private JTextField departmentField;
    private JTextField salaryField;
    private JButton addButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton viewButton;
    private EmployeeDAO employeeDAO;

    public EmployeePanel() {
        setLayout(new GridLayout(6, 2));

        employeeDAO = new EmployeeDAO();

        nameField = new JTextField();
        positionField = new JTextField();
        departmentField = new JTextField();
        salaryField = new JTextField();

        addButton = new JButton("Add");
        updateButton = new JButton("Update");
        deleteButton = new JButton("Delete");
        viewButton = new JButton("View");

        add(new JLabel("Name:"));
        add(nameField);
        add(new JLabel("Position:"));
        add(positionField);
        add(new JLabel("Department:"));
        add(departmentField);
        add(new JLabel("Salary:"));
        add(salaryField);

        add(addButton);
        add(updateButton);
        add(deleteButton);
        add(viewButton);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Employee employee = new Employee();
                employee.setName(nameField.getText());
                employee.setPosition(positionField.getText());
                employee.setDepartment(departmentField.getText());
                employee.setSalary(Double.parseDouble(salaryField.getText()));
                employeeDAO.addEmployee(employee);
                clearFields();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Employee employee = new Employee();
                employee.setId(Integer.parseInt(JOptionPane.showInputDialog("Enter ID to update:")));
                employee.setName(nameField.getText());
                employee.setPosition(positionField.getText());
                employee.setDepartment(departmentField.getText());
                employee.setSalary(Double.parseDouble(salaryField.getText()));
                employeeDAO.updateEmployee(employee);
                clearFields();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(JOptionPane.showInputDialog("Enter ID to delete:"));
                employeeDAO.deleteEmployee(id);
                clearFields();
            }
        });

        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(JOptionPane.showInputDialog("Enter ID to view:"));
                Employee employee = employeeDAO.getEmployee(id);
                if (employee != null) {
                    nameField.setText(employee.getName());
                    positionField.setText(employee.getPosition());
                    departmentField.setText(employee.getDepartment());
                    salaryField.setText(String.valueOf(employee.getSalary()));
                } else {
                    JOptionPane.showMessageDialog(null, "Employee not found!");
                }
            }
        });
    }

    private void clearFields() {
        nameField.setText("");
        positionField.setText("");
        departmentField.setText("");
        salaryField.setText("");
    }
}
