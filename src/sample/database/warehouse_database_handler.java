package sample.database;

import sample.model.warehouse_model;

import java.sql.*;
import java.util.ArrayList;

import static sample.database.configuration.*;


public class warehouse_database_handler {


    public static Connection get_database_connection() throws SQLException
    {
        return DriverManager.getConnection(DBURL, USER, PASSWORD);
    }

    public static ArrayList<warehouse_model> getAllWorkers() throws SQLException {
        Connection conn=get_database_connection();
        Statement stm;
        stm = conn.createStatement();
        String sql = "Select * From warehouse";
        ResultSet rst;
        rst = stm.executeQuery(sql);
        ArrayList<warehouse_model> warehouseList = new ArrayList<>();
        while (rst.next()) {
            warehouse_model warehouse = new warehouse_model(rst.getInt("idWarehouse"), rst.getString("street"), rst.getString("postal_code"), rst.getString("location"), rst.getInt("id_counterparty"));
            warehouseList.add(warehouse);
        }
        return warehouseList;
    }
    public void insert_warehouse(String street, String postal_code, String location) throws SQLException, ClassNotFoundException
    {
        Connection conn=get_database_connection();
        Statement stm;
        stm = conn.createStatement();
        String sql = "INSERT INTO warehouse(street,postal_code,location) VALUES(?,?,?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, street);
        pstmt.setString(2, postal_code);
        pstmt.setString(3, location);
        pstmt.executeUpdate();
    }
    public void change_warehouse (Integer id, String street, String postal_code, String location) throws SQLException, ClassNotFoundException
    {
        Connection conn=get_database_connection();
        Statement stm;
        stm = conn.createStatement();
        String sql = "UPDATE warehouse SET street = ?, postal_code = ?, location = ? WHERE idWarehouse = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, street);
        pstmt.setString(2, postal_code);
        pstmt.setString(3, location);
        pstmt.setInt(4, id);
        pstmt.executeUpdate();
    }
}
