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
            String insertHeadmasterQuery = "INSERT INTO Headmaster (user_id, professional_email, protel) VALUES (?, ?, ?)";
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
                String name = resultSet.getString("name");
                String userRole = resultSet.getString("role");
                users.add(new User(id, name, userRole));
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle or log the exception as needed
        }
        return users;
    }

}
