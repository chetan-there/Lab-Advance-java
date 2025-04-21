package com.ct.Demo_connection;

import java.sql.*;

public class TestCars {
    public static void main(String[] args) {
        Connection con = null;
        Statement stmt = null;
        ResultSet rSet = null;

        try {
            // 1. Load Oracle JDBC Driver (recommended for older Java versions)
            Class.forName("oracle.jdbc.driver.OracleDriver");

            // 2. Connect to Oracle Database
            con = DriverManager.getConnection(
                "jdbc:oracle:thin:@localhost:1521:XE", "chetan", "oracle24");

            // 3. Create statement and execute query
            stmt = con.createStatement();
            rSet = stmt.executeQuery("SELECT * FROM CAR_DETAILS ");

            System.out.println("🚗 Details of Cars:");
            while (rSet.next()) {
                System.out.println("Brand Name: " + rSet.getString(1)
                    + "\tModel: " + rSet.getString(2)
                    + "\tYOM: " + rSet.getInt(3)
                    + "\tType: " + rSet.getString(4)
                    + "\tMileage: " + rSet.getInt(5));
            }
        } catch (ClassNotFoundException e) {
            System.out.println("❌ Oracle JDBC Driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("❌ SQL Exception occurred:");
            e.printStackTrace();
        } finally {
            // Always close in reverse order of creation
            try { if (rSet != null) rSet.close(); } catch (SQLException e) {}
            try { if (stmt != null) stmt.close(); } catch (SQLException e) {}
            try { if (con != null) con.close(); } catch (SQLException e) {}
        }
    }
}


// Output : 🚗 Details of Cars:
// Brand Name: Mahindra	Model: Thar	YOM: 2024	Type: Petrol	Mileage: 13
// Brand Name: Mahindra	Model: ScorpioN	YOM: 2025	Type: Diesel	Mileage: 9
// Brand Name: Tata	Model: Safari	YOM: 2023	Type: Petrol	Mileage: 12
// Brand Name: Tata	Model: Nexon	YOM: 2017	Type: Petrol	Mileage: 15
// Brand Name: Nexa	Model: Baleno	YOM: 2019	Type: CNG	Mileage: 18
