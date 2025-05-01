package dao;

import db.DBConnection;
import model.Attendance;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AttendanceDAO {
    public void addAttendance(Attendance attendance) {
        String sql = "INSERT INTO attendance (employee_id, date, status) VALUES (?, ?, ?)";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, attendance.getEmployeeId());
            stmt.setDate(2, new java.sql.Date(attendance.getDate().getTime()));
            stmt.setString(3, attendance.getStatus());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Attendance getAttendance(int id) {
        String sql = "SELECT * FROM attendance WHERE id = ?";
        Attendance attendance = null;

        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                attendance = new Attendance();
                attendance.setId(rs.getInt("id"));
                attendance.setEmployeeId(rs.getInt("employee_id"));
                attendance.setDate(rs.getDate("date"));
                attendance.setStatus(rs.getString("status"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return attendance;
    }

    public List<Attendance> getAllAttendances() {
        String sql = "SELECT * FROM attendance";
        List<Attendance> attendances = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection(); Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Attendance attendance = new Attendance();
                attendance.setId(rs.getInt("id"));
                attendance.setEmployeeId(rs.getInt("employee_id"));
                attendance.setDate(rs.getDate("date"));
                attendance.setStatus(rs.getString("status"));
                attendances.add(attendance);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return attendances;
    }

    public void updateAttendance(Attendance attendance) {
        String sql = "UPDATE attendance SET employee_id = ?, date = ?, status = ? WHERE id = ?";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, attendance.getEmployeeId());
            stmt.setDate(2, new java.sql.Date(attendance.getDate().getTime()));
            stmt.setString(3, attendance.getStatus());
            stmt.setInt(4, attendance.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAttendance(int id) {
        String sql = "DELETE FROM attendance WHERE id = ?";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
