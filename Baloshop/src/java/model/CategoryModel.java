/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.Account;
import jdbc.MSSQLConnection;
import entity.Category;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Shado
 */
public class CategoryModel {

    private Connection connection = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    public ArrayList<Category> getAllCategory() {
        ArrayList<Category> list = new ArrayList<>();
        String query = "SELECT * FROM Categories";
        try {
            connection = MSSQLConnection.getConnection();
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                Category category = new Category(
                        rs.getInt(1),
                        rs.getString(2)
                );
                list.add(category);
            }
            return list;
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            MSSQLConnection.closeResultSet(rs);
            MSSQLConnection.closePreparedStatement(ps);
            MSSQLConnection.closeConnection(connection);
        }
        return null;
    }

    public Category getOneCategory(int id) {
        String query = "SELECT * FROM Categories WHERE Id = ?";
        try {
            connection = MSSQLConnection.getConnection();
            ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                Category category = new Category(
                        rs.getInt(1),
                        rs.getString(2)
                );
                return category;
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            MSSQLConnection.closeResultSet(rs);
            MSSQLConnection.closePreparedStatement(ps);
            MSSQLConnection.closeConnection(connection);
        }
        return null;
    }

    public boolean updateCategory(Category c, int id) {
        int isCheck = 0;
        String query = "UPDATE Categories SET category = ? WHERE id = ?";
        try {
            connection = MSSQLConnection.getConnection();
            ps = connection.prepareStatement(query);
            ps.setString(1, c.getCategory());
            ps.setInt(2, id);
            isCheck = ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            MSSQLConnection.closeResultSet(rs);
            MSSQLConnection.closePreparedStatement(ps);
            MSSQLConnection.closeConnection(connection);
        }
        return isCheck > 0;
    }
    
    public boolean addCategory(Category c) {
        int isCheck = 0;
        String query = "INSERT INTO Categories(category) VALUES(?)";
        try {
            connection = MSSQLConnection.getConnection();
            ps = connection.prepareStatement(query);
            ps.setString(1, c.getCategory());
            isCheck = ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            MSSQLConnection.closeResultSet(rs);
            MSSQLConnection.closePreparedStatement(ps);
            MSSQLConnection.closeConnection(connection);
        }
        return isCheck > 0;
    }
}
