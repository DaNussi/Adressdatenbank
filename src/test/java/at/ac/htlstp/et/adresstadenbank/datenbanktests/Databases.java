package at.ac.htlstp.et.adresstadenbank.datenbanktests;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Databases {

    public static final String DB_URL = "jdbc:mysql://localhost:1306";

    public static final String USER = "root";

    public static final String PWD = "htl";


    public static Vector<Vector<String>> executeQuery(Connection con, String sqlStatement) throws SQLException {
        Vector<Vector<String>> ret = new Vector<Vector<String>>();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery( sqlStatement );
        ResultSetMetaData metaData = rs.getMetaData();
        int columns = metaData.getColumnCount();
        // Spaltennamen ermitteln
        Vector<String> row = new Vector<String>();
        for (int c=1; c<=columns; c++)
            row.add(metaData.getColumnName(c));
        ret.add(row);
        while (rs.next()) {
            row = new Vector<String>();
            for (int c=1; c<=columns; c++)
                row.add(rs.getString(c));
            ret.add(row);
        }
        return ret;
    }

    public static void execute(Connection con, String sqlStatement) throws SQLException {
        Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                                             ResultSet.CONCUR_UPDATABLE);
        stmt.execute( sqlStatement );
    }

    public static void main(String[] args) {
        try {
            Connection con = DriverManager.getConnection(DB_URL,USER,PWD);
            /*for(Vector<String> line:executeQuery(con,"show databases"))
                System.out.println(line);*/
            Vector<Vector<String>> data = executeQuery(con,"show databases");
            for (int i=0; i<data.size(); i++){
                Vector<String> line = data.get(i);
                System.out.println(line);
            }
            execute(con,"use eulaender");

            data = executeQuery(con,"show tables");
            List<String> tables = new ArrayList<>();
            for (int i=1; i<data.size(); i++) {
                String tabelname = data.get(i).get(0);
                tables.add(tabelname);
            }
            System.out.println("TABLES:"+tables);

            /*

            for (Vector<String> line:executeQuery(con,"describe Personen"))
                System.out.println(line);   */


        } catch (SQLException e) {
            System.out.println("Database connection failed!");
        }


    }

}
