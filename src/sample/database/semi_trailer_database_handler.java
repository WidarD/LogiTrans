package sample.database;

import sample.model.semi_trailer_model;

import java.sql.*;
import java.util.ArrayList;

import static sample.database.configuration.*;

public class semi_trailer_database_handler {
    public static Connection get_database_connection() throws SQLException
    {
        return DriverManager.getConnection(DBURL, USER, PASSWORD);
    }

    public static ArrayList<semi_trailer_model> getAllTSemiTrailer() throws SQLException {
        Connection conn=get_database_connection();
        Statement stm;
        stm = conn.createStatement();
        String sql = "Select * From semi_trailer";
        ResultSet rst;
        rst = stm.executeQuery(sql);
        ArrayList<semi_trailer_model> trailerList = new ArrayList<>();
        while (rst.next()) {
            semi_trailer_model trailer = new semi_trailer_model(rst.getInt("idSemi_trailer"), rst.getString("registration_number"), rst.getDate("technical_inspection"), rst.getDate("insurance"), rst.getDate("veterinary approval"), rst.getDate("status"));
            trailerList.add(trailer);
        }
        return trailerList;
    }
    public void insert_semi_trailer(String registration_number, Date technical_inspection, Date insurance, Date veterinary_approval, Date status) throws SQLException, ClassNotFoundException
    {
        Connection conn=get_database_connection();
        Statement stm;
        stm = conn.createStatement();
        String sql = "INSERT INTO semi_trailer(registration_number,technical_inspection,insurance,veterinary approval,status) VALUES(?,?,?,?,?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, registration_number);
        pstmt.setDate(2, technical_inspection);
        pstmt.setDate(3, insurance);
        pstmt.setDate(4, veterinary_approval);
        pstmt.setDate(5, status);
        pstmt.executeUpdate();
    }
    public void change_semi_trailer (Integer id, String registration_number, Date technical_inspection, Date insurance, Date veterinary_approval, Date status) throws SQLException, ClassNotFoundException
    {
        Connection conn=get_database_connection();
        Statement stm;
        stm = conn.createStatement();
        String sql = "UPDATE semi_trailer SET registration_number = ?, status = ?, technical_inspection = ?, insurance = ?, tachograph_legalization = ? WHERE idTractor_unit5 = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, registration_number);
        pstmt.setDate(2, technical_inspection);
        pstmt.setDate(3, insurance);
        pstmt.setDate(4, veterinary_approval);
        pstmt.setDate(5, status);
        pstmt.setInt(6, id);
        pstmt.executeUpdate();
    }
}
