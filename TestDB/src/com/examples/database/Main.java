package com.examples.database;

import java.io.File;
import java.sql.*;

public class Main {
    public static final String DB_NAME = "testJava.db";
    public static final String CONNECTION_STRING = "jdbc:sqlite:src\\databases\\" + DB_NAME;
    public static final String TABLE_CONTACTS = "contacts";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_PHONE = "phone";
    public static final String COLUMN_EMAIL = "email";

    public static void main(String[] args) {
        String separator = File.separator;
        try (Connection connection = DriverManager.getConnection(CONNECTION_STRING);
             Statement statement = connection.createStatement();) {
//            connection.setAutoCommit(false);
            statement.execute("drop table if exists " + TABLE_CONTACTS);
            statement.execute("create table if not exists " + TABLE_CONTACTS +
                    " (" + COLUMN_NAME + " text," + COLUMN_PHONE + " integer," + COLUMN_EMAIL + " text)");

            // Insert Operations
            insertContact(statement, "demo", 123456, "demo@email.com");
            insertContact(statement, "joe", 68356, "joe@email.com");
            insertContact(statement, "jane", 84838, "jane@email.com");

            // Update Operations
//            statement.execute("update " + TABLE_CONTACTS + " set " + COLUMN_NAME + "='jim' where " + COLUMN_NAME + "='demo'");
            updateName(statement,"jim","demo");

            // Delete Operations(delete all data so no problem in inserting again)
//            statement.execute("delete from contacts");

            // .execute return a boolean while .executeQuery returns a resultSet
//            statement.execute("Select * from contacts");
//            ResultSet result = statement.getResultSet();

            ResultSet result = statement.executeQuery("Select * from contacts");

            while (result.next()) {
                // using column name
                System.out.println(result.getString(COLUMN_NAME) + " " + result.getInt(COLUMN_PHONE) +
                        " " + result.getString(COLUMN_EMAIL));
                // using column index based on select query
                System.out.println(result.getString(1) + " " + result.getInt(2) +
                        " " + result.getString(3));
                System.out.println(result.getRow());
            }
            result.close();


//            statement.close();
//            connection.close();
        } catch (SQLException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    private static void insertContact(Statement statement, String name, int phone, String email) throws SQLException {
        statement.execute("insert into " + TABLE_CONTACTS + " values ('" + name + "'," + phone + ",'" + email + "')");
    }

    private static void updateName(Statement statement, String newName, String oldName) throws SQLException {
        statement.execute("update " + TABLE_CONTACTS + " set " + COLUMN_NAME + "='" + newName +
                "' where " + COLUMN_NAME + "='" + oldName + "'");
    }
}
