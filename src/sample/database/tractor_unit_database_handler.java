package sample.database;

import sample.model.tractor_unit_model;

import java.sql.*;
import java.util.ArrayList;

import static sample.database.configuration.*;

public class tractor_unit_database_handler {
    public static Connection get_database_connection() throws SQLException
    {
        return DriverManager.getConnection(DBURL, USER, PASSWORD);
    }

    public static ArrayList<tractor_unit_model> getAllTractorUnit() throws SQLException {
        Connection conn=get_database_connection();
        Statement stm;
        stm = conn.createStatement();
        String sql = "Select * From tractor_unit";
        ResultSet rst;
        rst = stm.executeQuery(sql);
        ArrayList<tractor_unit_model> tractorList = new ArrayList<>();
        while (rst.next()) {
            tractor_unit_model tractor = new tractor_unit_model(rst.getInt("idTractor_unit"), rst.getString("registration_number"), rst.getDate("status"), rst.getDate("technical_inspection"), rst.getDate("insurance"), rst.getDate("tachograph_legalization"));
            tractorList.add(tractor);
        }
        return tractorList;
    }
    public void insert_tractor_unit(String registration_number, Date status, Date technical_inspection, Date insurance, Date tachograph_legalization) throws SQLException, ClassNotFoundException
    {
        Connection conn=get_database_connection();
        Statement stm;
        stm = conn.createStatement();
        String sql = "INSERT INTO tractor_unit(registration_number,status,technical_inspection,insurance,tachograph_legalization) VALUES(?,?,?,?,?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, registration_number);
        pstmt.setDate(2, status);
        pstmt.setDate(3, technical_inspection);
        pstmt.setDate(4, insurance);
        pstmt.setDate(5, tachograph_legalization);
        pstmt.executeUpdate();
    }
    public void change_tractor_unit (Integer id, String registration_number, Date status, Date technical_inspection, Date insurance, Date tachograph_legalization) throws SQLException, ClassNotFoundException
    {
        Connection conn=get_database_connection();
        Statement stm;
        stm = conn.createStatement();
        String sql = "UPDATE tractor_unit SET registration_number = ?, status = ?, technical_inspection = ?, insurance = ?, tachograph_legalization = ? WHERE idTractor_unit5 = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, registration_number);
        pstmt.setDate(2, status);
        pstmt.setDate(3, technical_inspection);
        pstmt.setDate(4, insurance);
        pstmt.setDate(5, tachograph_legalization);
        pstmt.setInt(6, id);
        pstmt.executeUpdate();
    }
}
