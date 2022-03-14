/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import jdbc.MSSQLConnection;
import entity.Cart;
import entity.OrderDetail;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Shado
 */
public class OrderDetailModel {

    private Connection connection = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    public boolean addOrderDetail(List<Cart> list, int orderId) {
        String query = "INSERT INTO Order_Detail(Order_Id, Product_Id, Product_Name, "
                + "Product_Quantity, Product_Price) VALUES(?, ?, ?, ?, ?)";
        try {
            connection = MSSQLConnection.getConnection();
            ps = connection.prepareStatement(query);
            connection.setAutoCommit(false);
            for(Cart c : list){
                ps.setInt(1, orderId);
                ps.setInt(2, c.getProductId());
                ps.setString(3, c.getName());
                ps.setInt(4, c.getQuantity());
                ps.setDouble(5, c.getUnitPrice());
                ps.addBatch();
            }
            ps.executeBatch();
            connection.commit();
            System.out.println("Record is inserted into Order_Detail table!");
            return true;
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            MSSQLConnection.closeResultSet(rs);
            MSSQLConnection.closePreparedStatement(ps);
            MSSQLConnection.closeConnection(connection);
        }
        return false;
    }
    
    public ArrayList<OrderDetail> getOrderDetailByOrderId(int orderId){
        ArrayList<OrderDetail> list = new ArrayList<>();
        String query = "SELECT * FROM [Order_Detail] WHERE Order_Id = ?";
        try {
            connection = MSSQLConnection.getConnection();
            ps = connection.prepareStatement(query);
            ps.setInt(1, orderId);
            rs = ps.executeQuery();
            while (rs.next()) {
                OrderDetail orderDetail = new OrderDetail(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getString(4),
                        rs.getInt(5),
                        rs.getDouble(6)
                );
                list.add(orderDetail);
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
