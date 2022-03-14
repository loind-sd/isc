/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import jdbc.MSSQLConnection;
import entity.AccountDetail;
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
public class AccountDetailModel {

    private Connection connection = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    public ArrayList<AccountDetail> getAllAccountDetail() {
        return null;
    }

    public AccountDetail getOneAccountDetail(int id) {
        String query = "SELECT * FROM Account_Detail WHERE Id = ?";
        try {
            connection = MSSQLConnection.getConnection();
            ps = connection.prepareStatement(query);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                AccountDetail accountDetail = new AccountDetail(
                        rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getString(5)
                );
                return accountDetail;
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

    public int addAccountDetail(AccountDetail accountDetail) {
        String query = "INSERT INTO Account_Detail(Name, Phone_Number, Gender, Address) VALUES(?, ?, ?, ?)";
        try {
            connection = MSSQLConnection.getConnection();
            ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, accountDetail.getName());
            ps.setString(2, accountDetail.getMobile());
            ps.setInt(3, accountDetail.getGender());
            ps.setString(4, accountDetail.getAddress());
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

    public boolean update(AccountDetail accountDetail, int id) {
        return false;
    }
}
