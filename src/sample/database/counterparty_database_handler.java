package sample.database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.model.counterparty_model;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class counterparty_database_handler extends configuration
{
    public static class Work extends JFrame {

        public Work() {

            super("Bind JTable From MySQL DataBase");

            setLocationRelativeTo(null);

            setSize(600, 400);

            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            setVisible(true);
        }
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        counterparty_database_handler app = new counterparty_database_handler();

        app.insert_counterparty("kolejna firma","Gniezno" ,"11122211","147852963");

       //app.delete_counetrparty(9);
        //app.change_counetrparty(1,"nowa firma","Gdańsk","00000000","000000000");

        JTable table = new JTable();

        DefaultTableModel model = new DefaultTableModel();

        Object[] columnsName = new Object[5];

        columnsName[0] = "Id";
        columnsName[1] = "Nazwa";
        columnsName[2] = "Adres";
        columnsName[3] = "NIP";
        columnsName[4] = "Nr telefonu";

        model.setColumnIdentifiers(columnsName);

        Object[] rowData = new Object[5];

        for(int i = 0; i < getAllCounterparty().size(); i++){
            rowData[0] = getAllCounterparty().get(i).getIdCounterparty();
            rowData[1] = getAllCounterparty().get(i).getName();
            rowData[2] = getAllCounterparty().get(i).getAddress();
            rowData[3] = getAllCounterparty().get(i).getTax_number();
            rowData[4] = getAllCounterparty().get(i).getPhone_number();
            model.addRow(rowData);
        }

        table.setModel(model);
        Work window = new Work();
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        JScrollPane pane = new JScrollPane(table);
        panel.add(pane,BorderLayout.CENTER);
        window.setContentPane(panel);
    }



    public static ObservableList<counterparty_model> getAllCounterparty() throws ClassNotFoundException, SQLException {
        Connection conn=get_database_connection();
        Statement stm;
        stm = conn.createStatement();
        String sql = "Select * From counterparty";
        ResultSet rst;
        rst = stm.executeQuery(sql);
        ObservableList<counterparty_model> counterpartyList = FXCollections.observableArrayList();
        while (rst.next()) {
            counterpartyList.add(new counterparty_model(rst.getInt("idCounterparty"), rst.getString("name"), rst.getString("address"), rst.getString("tax_number"), rst.getString("phone_number")));
            //counterpartyList.add(counterparty);
        }
        return counterpartyList;
    }

    public static Connection get_database_connection() throws SQLException, ClassNotFoundException
    {
        return DriverManager.getConnection(DBURL, USER, PASSWORD);
    }


    public int get_all_counterparties() throws SQLException, ClassNotFoundException
    {
        return getAllCounterparty().size();
    }

//    public ResultSet get_counterparty_list() throws SQLException, ClassNotFoundException
//    {
//
//    }
public void insert_counterparty(String name, String address, String tax_number,String phone_number) throws SQLException, ClassNotFoundException {
    Connection conn=get_database_connection();
    Statement stm;
    stm = conn.createStatement();
    String sql = "INSERT INTO counterparty(name,address,tax_number,phone_number) VALUES(?,?,?,?)";
    PreparedStatement pstmt = conn.prepareStatement(sql);
    pstmt.setString(1, name);
    pstmt.setString(2, address);
    pstmt.setString(3, tax_number);
    pstmt.setString(4, phone_number);
    pstmt.executeUpdate();
}

    public void delete_counetrparty(int id) throws SQLException, ClassNotFoundException
    {
        Connection conn=get_database_connection();
        Statement stm;
        stm = conn.createStatement();
        String sql = "delete from counterparty where idCounterparty=?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, id);
        pstmt.executeUpdate();
    }
    public void change_counetrparty (Integer id, String name, String  address, String tax_number, String phone_number) throws SQLException, ClassNotFoundException
    {
        Connection conn=get_database_connection();
        Statement stm;
        stm = conn.createStatement();
        String sql = "UPDATE counterparty SET name = ?, address = ?, tax_number = ?, phone_number = ? WHERE idCounterparty = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, name);
        pstmt.setString(2, address);
        pstmt.setString(3, tax_number);
        pstmt.setString(4, phone_number);
        pstmt.setInt(5, id);
        pstmt.executeUpdate();
    }


}



