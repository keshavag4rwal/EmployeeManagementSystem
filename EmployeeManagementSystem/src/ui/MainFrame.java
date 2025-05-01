package ui;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    public MainFrame() {
        setTitle("Employee Management System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane tabbedPane = new JTabbedPane();
        
        JPanel employeePanel = new EmployeePanel();
        JPanel payrollPanel = new PayrollPanel();
        JPanel attendancePanel = new AttendancePanel();
        
        tabbedPane.addTab("Employee Management", employeePanel);
        tabbedPane.addTab("Payroll Management", payrollPanel);
        tabbedPane.addTab("Attendance Management", attendancePanel);

        add(tabbedPane);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainFrame().setVisible(true);
        });
    }
}
