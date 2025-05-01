package ui;

import dao.PayrollDAO;
import model.Payroll;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PayrollPanel extends JPanel {
    private JTextField employeeIdField;
    private JTextField payDateField;
    private JTextField amountField;
    private JButton addButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton viewButton;
    private PayrollDAO payrollDAO;

    public PayrollPanel() {
        setLayout(new GridLayout(5, 2));

        payrollDAO = new PayrollDAO();

        employeeIdField = new JTextField();
        payDateField = new JTextField();
        amountField = new JTextField();

        addButton = new JButton("Add");
        updateButton = new JButton("Update");
        deleteButton = new JButton("Delete");
        viewButton = new JButton("View");

        add(new JLabel("Employee ID:"));
        add(employeeIdField);
        add(new JLabel("Pay Date (yyyy-mm-dd):"));
        add(payDateField);
        add(new JLabel("Amount:"));
        add(amountField);

        add(addButton);
        add(updateButton);
        add(deleteButton);
        add(viewButton);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Payroll payroll = new Payroll();
                payroll.setEmployeeId(Integer.parseInt(employeeIdField.getText()));
                payroll.setPayDate(parseDate(payDateField.getText()));
                payroll.setAmount(Double.parseDouble(amountField.getText()));
                payrollDAO.addPayroll(payroll);
                clearFields();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Payroll payroll = new Payroll();
                payroll.setId(Integer.parseInt(JOptionPane.showInputDialog("Enter ID to update:")));
                payroll.setEmployeeId(Integer.parseInt(employeeIdField.getText()));
                payroll.setPayDate(parseDate(payDateField.getText()));
                payroll.setAmount(Double.parseDouble(amountField.getText()));
                payrollDAO.updatePayroll(payroll);
                clearFields();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(JOptionPane.showInputDialog("Enter ID to delete:"));
                payrollDAO.deletePayroll(id);
                clearFields();
            }
        });

        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(JOptionPane.showInputDialog("Enter ID to view:"));
                Payroll payroll = payrollDAO.getPayroll(id);
                if (payroll != null) {
                    employeeIdField.setText(String.valueOf(payroll.getEmployeeId()));
                    payDateField.setText(formatDate(payroll.getPayDate()));
                    amountField.setText(String.valueOf(payroll.getAmount()));
                } else {
                    JOptionPane.showMessageDialog(null, "Payroll not found!");
                }
            }
        });
    }

    private Date parseDate(String dateStr) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private String formatDate(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }

    private void clearFields() {
        employeeIdField.setText("");
        payDateField.setText("");
        amountField.setText("");
    }
}
