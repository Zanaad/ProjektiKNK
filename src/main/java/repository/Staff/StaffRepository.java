package repository.Staff;

import database.DatabaseUtil;
import model.User;
import model.dto.StaffDto.CreateStaffDto;
import model.dto.StaffDto.CreateDoctorDto;
import model.dto.StaffDto.DoctorDto;
import model.dto.StaffDto.StaffDto;
import service.CountStaffService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StaffRepository {
    public static boolean createStaff(CreateStaffDto staffData, String query) {
        Connection conn = DatabaseUtil.getConnection();
        PreparedStatement pst = null;
        try {
            pst = conn.prepareStatement(query);
            pst.setString(1, staffData.getId());
            pst.setString(2, staffData.getFirstName());
            pst.setString(3, staffData.getLastName());
            pst.setDate(4, staffData.getBirthdate());
            pst.setString(5, staffData.getPhone());
            pst.setString(6, staffData.getEmail());
            pst.setString(7, staffData.getHashPassword());
            pst.setString(8, staffData.getSalt());
            pst.setString(9, staffData.getAddress());
            pst.setString(10, staffData.getDepartment());
            pst.setString(11, staffData.getUniversity());

            if (staffData instanceof CreateDoctorDto) {
                CreateDoctorDto doctorData = (CreateDoctorDto) staffData;
                pst.setString(12, doctorData.getSpecialty());
                pst.setDate(13, doctorData.getStartDate());
                pst.setDate(14, doctorData.getEndDate());
                pst.setString(15, doctorData.getBankName());
                pst.setString(16, doctorData.getBankAccount());
                pst.setString(17, doctorData.getRoutingNumber());
            } else {
                pst.setDate(12, staffData.getStartDate());
                pst.setDate(13, staffData.getEndDate());
                pst.setString(14, staffData.getBankName());
                pst.setString(15, staffData.getBankAccount());
                pst.setString(16, staffData.getRoutingNumber());
            }
            pst.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static User getStaffByEmail(String email, String tableName) {
        String query = "select * from " + tableName + " where email=?";
        Connection conn = DatabaseUtil.getConnection();
        try {
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, email);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                return getFromResultSet(rs);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static User getFromResultSet(ResultSet rs) {
        try {
            String id = rs.getString("id");
            String firstName = rs.getString("firstName");
            String lastName = rs.getString("lastName");
            String email = rs.getString("email");
            String salt = rs.getString("salt");
            String hashPassword = rs.getString("hashPassword");
            String address = rs.getString("address");
            return new User(id, firstName, lastName, email, salt, hashPassword, address);
        } catch (Exception e) {
            return null;
        }
    }

    public static String generateID(String prefix, String tableName) {
        try {
            Connection conn = DatabaseUtil.getConnection();
            String query = "SELECT MAX(CAST(SUBSTRING(id, 5) AS UNSIGNED)) AS max_id FROM " + tableName;
            Statement pst = conn.createStatement();
            ResultSet result = pst.executeQuery(query);
            int maxID = 0;
            if (result.next()) {
                maxID = result.getInt("max_id");
            }
            prefix += (maxID + 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return prefix;
    }

    public static String generatePassword(String firstName, String password) {
        if (firstName != null && !firstName.isEmpty()) {
            password += "-" + firstName;
        }
        return password;
    }

    public static List<String> getStaffNames(String query) {
        List<String> staff = new ArrayList<>();
        try {
            Connection connection = DatabaseUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                staff.add(resultSet.getString("firstName"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return staff;
    }

    public static boolean deleteStaff(String query, String id) {
        try {
            Connection conn = DatabaseUtil.getConnection();
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, id);
            int rowsAffected = pst.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean updateStaff(StaffDto staff, String query) {
        try {
            Connection connection = DatabaseUtil.getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, staff.getFirstName());
            statement.setString(2, staff.getLastName());
            statement.setString(3, staff.getDepartment());
            statement.setString(4, staff.getPhone());
            statement.setString(5, staff.getEmail());
            statement.setString(6, staff.getUniversity());
            statement.setString(7, staff.getAddress());
            statement.setString(8, staff.getRoutingNumber()); //me ndreq qitu
            statement.setString(9, staff.getId());
            int rowsUpdated = statement.executeUpdate();
            return rowsUpdated > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
