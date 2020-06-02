package sample.database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.model.route_model;

import java.sql.*;

public class route_database_handler extends configuration{
    public static ObservableList<route_model> getAllRoutes() throws ClassNotFoundException, SQLException {
        Connection conn=get_database_connection();
        Statement stm;
        stm = conn.createStatement();
        String sql = "Select idRoute,loading_date,loading_hour,unloading_date,unloading_hour,id_warehouse,id_worker From route";
        ResultSet rst;
        rst = stm.executeQuery(sql);
        ObservableList<route_model> list  = FXCollections.observableArrayList();
        while (rst.next()) {
            list.add(new route_model(rst.getInt("idRoute"), rst.getDate("loading_date"), rst.getTime("loading_hour"), rst.getDate("unloading_date"), rst.getTime("unloading_hour")));
        }return list;
    }

    public static Connection get_database_connection() throws SQLException, ClassNotFoundException
    {
        return DriverManager.getConnection(DBURL, USER, PASSWORD);
    }


    public int get_all_routes() throws SQLException, ClassNotFoundException
    {
        return getAllRoutes().size();
    }


    public void insert_route (Date loading_date, Time loading_hour, Date unloading_date, Time unloading_hour) throws SQLException, ClassNotFoundException {
        Connection conn=get_database_connection();
        Statement stm;
        stm = conn.createStatement();
        String sql = "INSERT INTO route(loading_date,loading_hour,unloading_date,unloading_hour) VALUES(?,?,?,?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setDate(1, loading_date);
        pstmt.setTime(2, loading_hour);
        pstmt.setDate(3, unloading_date);
        pstmt.setTime(4, unloading_hour);
        pstmt.executeUpdate();
    }

    public void delete_route(int id) throws SQLException, ClassNotFoundException
    {
        Connection conn=get_database_connection();
        Statement stm;
        stm = conn.createStatement();
        String sql = "delete from route where idRoute=?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, id);
        pstmt.executeUpdate();
    }
    public void change_route (Date loading_date, Time loading_hour, Date unloading_date, Time unloading_hour, int id) throws SQLException, ClassNotFoundException
    {
        Connection conn=get_database_connection();
        Statement stm;
        stm = conn.createStatement();
        String sql = "UPDATE route SET loading_date = ?, loading_hour = ?, unloading_date = ?, unloading_hour = ? WHERE idRoute = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setDate(1, loading_date);
        pstmt.setTime(2, loading_hour);
        pstmt.setDate(3, unloading_date);
        pstmt.setTime(4, unloading_hour);
        pstmt.setInt(5, id);
        pstmt.executeUpdate();
    }
}
