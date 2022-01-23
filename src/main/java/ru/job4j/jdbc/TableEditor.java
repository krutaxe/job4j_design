package ru.job4j.jdbc;


import ru.job4j.io.Config;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private Connection connection;

    private Properties properties;

    public TableEditor(Properties properties) throws ClassNotFoundException, SQLException {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        String url = properties.getProperty("url");
        String login = properties.getProperty("login");
        String password = properties.getProperty("password");
        connection = DriverManager.getConnection(url, login, password);
    }

    public void sqlQuery(String query) throws SQLException {
        try (Statement statement = connection.createStatement()) {
            statement.execute(query);
        }
    }

    public void createTable(String tableName) throws Exception {
               String sql = "create table " + tableName
                       + "(id serial primary key);";
               sqlQuery(sql);
    }

    public void dropTable(String tableName) throws Exception {
                String sql = "DROP TABLE " + tableName;
                sqlQuery(sql);
    }

    public void addColumn(String tableName, String columnName, String type) throws Exception {
                String sql = "ALTER TABLE " + tableName
                        + " add column " + columnName + " " + type;
                sqlQuery(sql);
    }

    public void dropColumn(String tableName, String columnName) throws Exception {
                String sql = "ALTER TABLE " + tableName
                        + " drop column " + columnName;
                sqlQuery(sql);
    }

    public void renameColumn(String tableName, String columnName, String newColumnName)
            throws SQLException {
                String sql = "ALTER TABLE " + tableName
                        + " RENAME COLUMN " + columnName + " TO " + newColumnName;
                sqlQuery(sql);
    }

    public static String getTableScheme(Connection connection, String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "select * from %s limit 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) throws Exception {
        String path = "./src/main/resources/app.properties";
        Properties config = new Properties();
        try (FileInputStream in = new FileInputStream(path)) {
            config.load(in);
            try (TableEditor tableEditor = new TableEditor(config)) {

                tableEditor.dropTable("demo_db2");

                tableEditor.createTable("demo_db2");
                System.out.println(getTableScheme(tableEditor.connection, "demo_db2"));

                tableEditor.addColumn("demo_db2", "Age", "int");
                System.out.println(getTableScheme(tableEditor.connection, "demo_db2"));

                tableEditor.renameColumn("demo_db2", "Age", "LastName");
                System.out.println(getTableScheme(tableEditor.connection, "demo_db2"));

                tableEditor.dropColumn("demo_db2", "Lastname");
                System.out.println(getTableScheme(tableEditor.connection, "demo_db2"));
            }
        }

    }
}