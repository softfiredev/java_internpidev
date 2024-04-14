package com.erpinterns.java_erpinterns.services;

import com.erpinterns.java_erpinterns.interfaces.HeadMasterService;
import com.erpinterns.java_erpinterns.models.HeadMasterDepartment;
import com.erpinterns.java_erpinterns.models.User;
import com.erpinterns.java_erpinterns.utils.MyDataBase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HeadmasterServiceImpl implements HeadMasterService {

    @Override
    public void addHeadmaster(HeadMasterDepartment headmaster) {
        try {
            Connection connection = MyDataBase.getInstance().getCnx();
            String insertHeadmasterQuery = "INSERT INTO dep_head_master (user_id, professionalemail, protel) VALUES (?, ?, ?)";
            PreparedStatement headmasterStatement = connection.prepareStatement(insertHeadmasterQuery);
            headmasterStatement.setInt(1, headmaster.getUserId());
            headmasterStatement.setString(2, headmaster.getProfessionalEmail());
            headmasterStatement.setString(3, headmaster.getProtel());
            headmasterStatement.executeUpdate();

            String updateRoleQuery = "UPDATE User SET role = 'Headmaster' WHERE id = ?";
            PreparedStatement updateRoleStatement = connection.prepareStatement(updateRoleQuery);
            updateRoleStatement.setInt(1, headmaster.getUserId());
            updateRoleStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void updateRoleToHeadmaster(int userId) {
        try {
            Connection connection = MyDataBase.getInstance().getCnx();
            String updateRoleQuery = "UPDATE User SET role = 'Headmaster' WHERE id = ?";
            PreparedStatement updateRoleStatement = connection.prepareStatement(updateRoleQuery);
            updateRoleStatement.setInt(1, userId);
            updateRoleStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Handle or log the exception as needed
        }
    }


    @Override
    public List<User> getUsersByRole(String role) {
        List<User> users = new ArrayList<>();
        try {
            Connection connection = MyDataBase.getInstance().getCnx();
            String query = "SELECT * FROM User WHERE role = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, role);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("firstname");
                String userRole = resultSet.getString("role");
                users.add(new User(id, name, userRole));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }


    @Override
    public List<String> getInternFirstNames() {
        List<String> firstNames = new ArrayList<>();
        try {
            Connection connection = MyDataBase.getInstance().getCnx();
            String query = "SELECT firstname FROM User WHERE role = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, "intern");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String firstName = resultSet.getString("firstname");
                firstNames.add(firstName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return firstNames;
    }

    @Override
    public int getUserIdByFirstName(String firstName) {
        int userId = -1;
        try {
            Connection connection = MyDataBase.getInstance().getCnx();
            String query = "SELECT id FROM User WHERE firstname = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, firstName);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                userId = resultSet.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userId;
    }

    @Override
    public List<HeadMasterDepartment> getAllHeadmasters() {
        List<HeadMasterDepartment> headmasters = new ArrayList<>();
        try {
            Connection connection = MyDataBase.getInstance().getCnx();
            String query = "SELECT * FROM dep_head_master";
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int userId = resultSet.getInt("user_id");
                String professionalEmail = resultSet.getString("professionalemail");
                String protel = resultSet.getString("protel");
                headmasters.add(new HeadMasterDepartment(userId, professionalEmail, protel));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return headmasters;
    }

    @Override
    public void deleteHeadmaster(int userId) {
        try {
            Connection connection = MyDataBase.getInstance().getCnx();
            String deleteQuery = "DELETE FROM dep_head_master WHERE user_id = ?";
            PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery);
            deleteStatement.setInt(1, userId);
            deleteStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Handle or log the exception as needed
        }
    }


    @Override
    public void updateRoleToIntern(int userId) {
        // Implementation to update the role of a user to Intern in the database
    }

}
