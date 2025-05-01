package dao;

import db.DBConnection;
import model.Payroll;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PayrollDAO {
    public void addPayroll(Payroll payroll) {
        String sql = "INSERT INTO payroll (employee_id, pay_date, amount) VALUES (?, ?, ?)";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, payroll.getEmployeeId());
            stmt.setDate(2, new java.sql.Date(payroll.getPayDate().getTime()));
            stmt.setDouble(3, payroll.getAmount());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Payroll getPayroll(int id) {
        String sql = "SELECT * FROM payroll WHERE id = ?";
        Payroll payroll = null;

        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                payroll = new Payroll();
                payroll.setId(rs.getInt("id"));
                payroll.setEmployeeId(rs.getInt("employee_id"));
                payroll.setPayDate(rs.getDate("pay_date"));
                payroll.setAmount(rs.getDouble("amount"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return payroll;
    }

    public List<Payroll> getAllPayrolls() {
        String sql = "SELECT * FROM payroll";
        List<Payroll> payrolls = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection(); Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Payroll payroll = new Payroll();
                payroll.setId(rs.getInt("id"));
                payroll.setEmployeeId(rs.getInt("employee_id"));
                payroll.setPayDate(rs.getDate("pay_date"));
                payroll.setAmount(rs.getDouble("amount"));
                payrolls.add(payroll);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return payrolls;
    }

    public void updatePayroll(Payroll payroll) {
        String sql = "UPDATE payroll SET employee_id = ?, pay_date = ?, amount = ? WHERE id = ?";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, payroll.getEmployeeId());
            stmt.setDate(2, new java.sql.Date(payroll.getPayDate().getTime()));
            stmt.setDouble(3, payroll.getAmount());
            stmt.setInt(4, payroll.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deletePayroll(int id) {
        String sql = "DELETE FROM payroll WHERE id = ?";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
