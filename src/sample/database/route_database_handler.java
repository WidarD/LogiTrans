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
        String sql = "Select idRoute, city_start, city_stop, km From route";
        ResultSet rst;
        rst = stm.executeQuery(sql);
        ObservableList<route_model> list  = FXCollections.observableArrayList();
        while (rst.next()) {
            list.add(new route_model(rst.getInt("idRoute"), rst.getString("city_start"), rst.getString("city_stop"), rst.getInt("km")));
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


    public void insert_route (String city_start, String city_stop, int km) throws SQLException, ClassNotFoundException {
        Connection conn=get_database_connection();
        Statement stm;
        stm = conn.createStatement();
        String sql = "INSERT INTO route(city_start,city_stop,km) VALUES(?,?,?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, city_start);
        pstmt.setString(2, city_stop);
        pstmt.setInt(3, km);
        pstmt.executeUpdate();
    }

    public void delete_route(int idRoute) throws SQLException, ClassNotFoundException
    {
        Connection conn=get_database_connection();
        Statement stm;
        stm = conn.createStatement();
        String sql = "delete from route where idRoute=?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, idRoute);
        pstmt.executeUpdate();
    }
    public void change_route (String city_start, String city_stop, int km, int id) throws SQLException, ClassNotFoundException
    {
        Connection conn=get_database_connection();
        Statement stm;
        stm = conn.createStatement();
        String sql = "UPDATE route SET city_start = ?, city_stop = ?, km = ? WHERE idRoute = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, city_start);
        pstmt.setString(2, city_stop);
        pstmt.setInt(3, km);
        pstmt.setInt(4, id);
        pstmt.executeUpdate();
    }
}
