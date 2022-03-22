/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import jdbc.MSSQLConnection;
import entity.Account;
import entity.Order;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Shado
 */
public class OrderModel {

    private Connection connection = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    public int addOrder(Order order) {
        String query = "INSERT INTO [Order](Account_Id, Total_Price, Note, Status, SellBy) VALUES(?, ?, ?, ?, ?)";
        try {
            connection = MSSQLConnection.getConnection();
            ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, order.getAccountId());
            ps.setDouble(2, order.getTotalPrice());
            ps.setString(3, order.getNote());
            ps.setInt(4, order.getStatus());
            ps.setInt(5, order.getSellBy());
            int isCheck = ps.executeUpdate();
            if (isCheck > 0) {
                rs = ps.getGeneratedKeys();
                rs.next();
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            MSSQLConnection.closeResultSet(rs);
            MSSQLConnection.closePreparedStatement(ps);
            MSSQLConnection.closeConnection(connection);
        }
        return 0;
    }

    public void UpdateOrder(String status, String id) {
        String query = "Update [Order] set Status = ? where Id = ? ";

        try {

            connection = MSSQLConnection.getConnection();
            ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, status);
            ps.setString(2, id);
            int isCheck = ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            MSSQLConnection.closeResultSet(rs);
            MSSQLConnection.closePreparedStatement(ps);
            MSSQLConnection.closeConnection(connection);
        }
    }

    public ArrayList<Order> getOrderById(int accountId) {
        ArrayList<Order> list = new ArrayList<>();
        String query = "SELECT * FROM [Order] WHERE Account_Id = ? AND Status <> -1 AND Status <> 4 AND Status <> 6 ORDER BY status";
        try {
            connection = MSSQLConnection.getConnection();
            ps = connection.prepareStatement(query);
            ps.setInt(1, accountId);
            rs = ps.executeQuery();
            while (rs.next()) {
                Order order = new Order(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getInt(6)
                );
                list.add(order);
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

    public ArrayList<Order> getOrder() {
        ArrayList<Order> list = new ArrayList<>();
        String query = "SELECT a.*, c.Name, c.Phone_Number, c.Address FROM dbo.[Order] a \n"
                + "INNER JOIN dbo.Account b ON a.Account_Id = b.Id\n"
                + "INNER JOIN dbo.Account_Detail c ON b.Account_Detail_Id = c.Id order by a.status";
        try {
            connection = MSSQLConnection.getConnection();
            ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                Order order = new Order(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10)
                );
                list.add(order);
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

    public ArrayList<Order> getOrderForSeller(int accId) {
        ArrayList<Order> list = new ArrayList<>();
        String query = "SELECT a.*, c.Name, c.Phone_Number, c.Address FROM dbo.[Order] a \n"
                + "INNER JOIN dbo.Account b ON a.Account_Id = b.Id\n"
                + "INNER JOIN dbo.Account_Detail c ON b.Account_Detail_Id = c.Id WHERE a.SellBy = ? AND a.Status <> 1 ORDER BY a.status";
        try {
            connection = MSSQLConnection.getConnection();
            ps = connection.prepareStatement(query);
            ps.setInt(1, accId);
            rs = ps.executeQuery();
            while (rs.next()) {
                Order order = new Order(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getInt(6),
                        rs.getString(8),
                        rs.getString(9),
                        rs.getString(10)
                );
                list.add(order);
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

    public ArrayList<Order> getOrderHistoryById(int accountId) {
        ArrayList<Order> list = new ArrayList<>();
        String query = "SELECT * FROM [Order] WHERE Account_Id = ? AND Status = ? OR Account_Id = ? AND Status = ? OR Account_Id = ? AND Status = ? ORDER BY status";
        try {
            connection = MSSQLConnection.getConnection();
            ps = connection.prepareStatement(query);
            ps.setInt(1, accountId);
            ps.setInt(2, 7);
            ps.setInt(3, accountId);
            ps.setInt(4, 4);
            ps.setInt(5, accountId);
            ps.setInt(6, 6);
            rs = ps.executeQuery();
            while (rs.next()) {
                Order order = new Order(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getDouble(4),
                        rs.getString(5),
                        rs.getInt(6)
                );
                list.add(order);
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
}
