package ui;

import dao.AttendanceDAO;
import model.Attendance;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AttendancePanel extends JPanel {
    private JTextField employeeIdField;
    private JTextField dateField;
    private JTextField statusField;
    private JButton addButton;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton viewButton;
    private AttendanceDAO attendanceDAO;

    public AttendancePanel() {
        setLayout(new GridLayout(5, 2));

        attendanceDAO = new AttendanceDAO();

        employeeIdField = new JTextField();
        dateField = new JTextField();
        statusField = new JTextField();

        addButton = new JButton("Add");
        updateButton = new JButton("Update");
        deleteButton = new JButton("Delete");
        viewButton = new JButton("View");

        add(new JLabel("Employee ID:"));
        add(employeeIdField);
        add(new JLabel("Date (yyyy-mm-dd):"));
        add(dateField);
        add(new JLabel("Status (Present/Absent):"));
        add(statusField);

        add(addButton);
        add(updateButton);
        add(deleteButton);
        add(viewButton);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Attendance attendance = new Attendance();
                attendance.setEmployeeId(Integer.parseInt(employeeIdField.getText()));
                attendance.setDate(parseDate(dateField.getText()));
                attendance.setStatus(statusField.getText());
                attendanceDAO.addAttendance(attendance);
                clearFields();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Attendance attendance = new Attendance();
                attendance.setId(Integer.parseInt(JOptionPane.showInputDialog("Enter ID to update:")));
                attendance.setEmployeeId(Integer.parseInt(employeeIdField.getText()));
                attendance.setDate(parseDate(dateField.getText()));
                attendance.setStatus(statusField.getText());
                attendanceDAO.updateAttendance(attendance);
                clearFields();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(JOptionPane.showInputDialog("Enter ID to delete:"));
                attendanceDAO.deleteAttendance(id);
                clearFields();
            }
        });

        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(JOptionPane.showInputDialog("Enter ID to view:"));
                Attendance attendance = attendanceDAO.getAttendance(id);
                if (attendance != null) {
                    employeeIdField.setText(String.valueOf(attendance.getEmployeeId()));
                    dateField.setText(formatDate(attendance.getDate()));
                    statusField.setText(attendance.getStatus());
                } else {
                    JOptionPane.showMessageDialog(null, "Attendance not found!");
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
        dateField.setText("");
        statusField.setText("");
    }
}
