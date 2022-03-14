/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import jdbc.MSSQLConnection;
import entity.OrderDetail;
import entity.OtherAddress;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Shado
 */
public class OtherAddressModel {

    private Connection connection = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    public boolean addOtherAddress(OtherAddress otherAddress) {
        int isCheck = 0;
        String query = "INSERT INTO Other_Address(Name, Phone_Number, Address, Order_Id) VALUES(?, ?, ?, ?)";
        try {
            connection = MSSQLConnection.getConnection();
            ps = connection.prepareStatement(query);
            ps.setString(1, otherAddress.getName());
            ps.setString(2, otherAddress.getPhoneNumber());
            ps.setString(3, otherAddress.getAddress());
            ps.setInt(4, otherAddress.getOrderId());
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
    
    public OtherAddress getOtherAddressByOrderId(int orderId) {
        String query = "SELECT * FROM Other_Address WHERE Order_Id = ?";
        try {
            connection = MSSQLConnection.getConnection();
            ps = connection.prepareStatement(query);
            ps.setInt(1, orderId);
            rs = ps.executeQuery();
            if (rs.next()) {
                OtherAddress otherAddress = new OtherAddress(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getInt(5)
                );
                return otherAddress;
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
}
