package sample.database;
import sample.model.worker_model;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;

public class worker_database_handler extends configuration
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
    public static void main(String[] args) throws SQLException, ClassNotFoundException{


        worker_database_handler app = new worker_database_handler();


        //app.insert_worker("nowy","pracownik","nowy","pracownik","12021","000000000", "2020.03.03","2020.03.03", "2020.03.03", true, 2, 2);
        app.change_worker("zmieniony","zmieniony","zmieniony" ,"zmieniony" ,"zmieniony", "003030030", "zmieniony", "zmieniony","zmieniony" , true,1,1,1);
        //app.delete_worker(3);

        JTable table = new JTable();
        DefaultTableModel model = new DefaultTableModel();
        Object[] columnsName = new Object[13];
        columnsName[0] = "Id";
        columnsName[1] = "Imię";
        columnsName[2] = "Nazwisko";
        columnsName[3] = "Login";
        columnsName[4] = "Hasło";
        columnsName[5] = "Nr dowodu";
        columnsName[6] = "Nr telefonu";
        columnsName[7] = "Ważność prawa jazdy";
        columnsName[8] = "Ważność książeczki";
        columnsName[9] = "Ważnośc karty kierowcy";
        columnsName[10] = "BHP";
        columnsName[11] = "Naczepa";
        columnsName[12] = "Ciągnik";

        model.setColumnIdentifiers(columnsName);
        Object[] rowData = new Object[13];
        for(int i = 0; i < getAllWorkers().size(); i++) {
            rowData[0] = getAllWorkers().get(i).getIdWorker();
            rowData[1] = getAllWorkers().get(i).getName();
            rowData[2] = getAllWorkers().get(i).getSurname();
            rowData[3] = getAllWorkers().get(i).getLogin();
            rowData[4] = getAllWorkers().get(i).getPassword();
            rowData[5] = getAllWorkers().get(i).getID_number();
            rowData[6] = getAllWorkers().get(i).getPhone_number();
            rowData[7] = getAllWorkers().get(i).getDriving_license();
            rowData[8] = getAllWorkers().get(i).getSanitary_book();
            rowData[9] = getAllWorkers().get(i).getDriver_card();
            rowData[10] = getAllWorkers().get(i).getOHS();
            rowData[11] = getAllWorkers().get(i).getId_semi_trailer();
            rowData[12] = getAllWorkers().get(i).getId_tractor_unit();
            model.addRow(rowData);
        }
        table.setModel(model);
        counterparty_database_handler.Work window = new counterparty_database_handler.Work();
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        JScrollPane pane = new JScrollPane(table);
        panel.add(pane,BorderLayout.CENTER);
        window.setContentPane(panel);
    }

    public static Connection get_database_connection() throws SQLException
    {
    return DriverManager.getConnection(DBURL, USER, PASSWORD);
    }

    public static ArrayList<worker_model> getAllWorkers() throws SQLException {
        Connection conn=get_database_connection();
        Statement stm;
        stm = conn.createStatement();
        String sql = "Select * From worker";
        ResultSet rst;
        rst = stm.executeQuery(sql);
        ArrayList<worker_model> workerList = new ArrayList<>();
        while (rst.next()) {
            worker_model worker = new worker_model(rst.getInt("idWorker"), rst.getString("name"), rst.getString("surname"), rst.getString("login"), rst.getString("password"), rst.getString("ID_number"), rst.getString("phone_number"), rst.getString("driving_license"), rst.getString("sanitary_book"), rst.getString("driver_card"), rst.getBoolean("OHS"), rst.getInt("id_semi_trailer"), rst.getInt("id_tractor_unit"));
            workerList.add(worker);
        }
        return workerList;
    }
    public void insert_worker(String name, String surname, String login, String password, String ID_number, String phone_number, String driving_license, String sanitary_book, String driver_card, Boolean OHS, Integer id_semi_trailer, Integer id_tractor_unit) throws SQLException
    {
        Connection conn=get_database_connection();
        Statement stm;
        stm = conn.createStatement();
        String sql = "INSERT INTO worker(name,surname,login,password,ID_number,phone_number,driving_license, sanitary_book, driver_card, OHS, id_semi_trailer, id_tractor_unit) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, name);
        pstmt.setString(2, surname);
        pstmt.setString(3, login);
        pstmt.setString(4, password);
        pstmt.setString(5, ID_number);
        pstmt.setString(6, phone_number);
        pstmt.setString(7, driving_license);
        pstmt.setString(8, sanitary_book);
        pstmt.setString(9, driver_card);
        pstmt.setBoolean(10, OHS);
        pstmt.setInt(11, id_semi_trailer);
        pstmt.setInt(12, id_tractor_unit);
        pstmt.executeUpdate();
    }
//    public int get_all_workers() throws SQLException, ClassNotFoundException
//    {
//
//    }

//    public ResultSet get_workers_list() throws SQLException, ClassNotFoundException
//    {
//
//    }


    public void delete_worker(Integer id) throws SQLException, ClassNotFoundException
    {
        Connection conn=get_database_connection();
        Statement stm;
        stm = conn.createStatement();
        String sql = "delete from worker where idWorker=?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, id);
        pstmt.executeUpdate();
    }
    public void change_worker (String name, String surname, String login, String password, String ID_number, String phone_number, String driving_license, String sanitary_book, String driver_card, Boolean OHS, Integer id_semi_trailer, Integer id_tractor_unit, Integer id) throws SQLException, ClassNotFoundException
    {
        Connection conn=get_database_connection();
        Statement stm;
        stm = conn.createStatement();
        String sql = "UPDATE worker SET name = ?, surname = ?, login = ?, password = ?, ID_number = ?, phone_number = ?, driving_license = ?, sanitary_book = ?, driver_card = ?, OHS = ?, id_semi_trailer = ?, id_tractor_unit = ? WHERE idWorker = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, name);
        pstmt.setString(2, surname);
        pstmt.setString(3, login);
        pstmt.setString(4, password);
        pstmt.setString(5, ID_number);
        pstmt.setString(6, phone_number);
        pstmt.setString(7, driving_license);
        pstmt.setString(8, sanitary_book);
        pstmt.setString(9, driver_card);
        pstmt.setBoolean(10, OHS);
        pstmt.setInt(11, id_semi_trailer);
        pstmt.setInt(12, id_tractor_unit);
        pstmt.setInt(13, id);
        pstmt.executeUpdate();
    }
}
