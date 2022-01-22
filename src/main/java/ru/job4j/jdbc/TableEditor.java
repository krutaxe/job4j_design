package ru.job4j.jdbc;

import ru.job4j.io.Config;

import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private Connection connection;

    private Properties properties;

    public TableEditor(Properties properties) {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() {
        connection = null;
    }

    private static Connection getConnection() throws Exception {
        Config config = new Config("./data/app.properties");
        config.load();
        Class.forName("org.postgresql.Driver");
        return DriverManager.getConnection(config.value("url"),
                config.value("login"), config.value("password"));
    }

    public void createTable(String tableName) throws Exception {
       try (Connection connection = getConnection()) {
           try (Statement statement = connection.createStatement()) {
               String sql = String.format(
                       "create table " + tableName + " (%s, %s);",
                       "id serial primary key",
                       "name varchar(255)"
               );
               statement.executeUpdate(sql);
           }
       }
    }

    public void dropTable(String tableName) throws Exception {
        try (Connection connection = getConnection()) {
            try (Statement statement = connection.createStatement()) {
                String sql = "DROP TABLE " + tableName;
                statement.executeUpdate(sql);
            }
        }
    }

    public void addColumn(String tableName, String columnName, String type) throws Exception {
        try (Connection connection = getConnection()) {
            try (Statement statement = connection.createStatement()) {
                String sql = "ALTER TABLE " + tableName
                        + " add column " + columnName + " " + type;
                statement.executeUpdate(sql);
            }
        }
    }

    public void dropColumn(String tableName, String columnName) throws Exception {
        try (Connection connection = getConnection()) {
            try (Statement statement = connection.createStatement()) {
                String sql = "ALTER TABLE " + tableName
                        + " drop column " + columnName;
                statement.executeUpdate(sql);
            }
        }
    }

    public void renameColumn(String tableName, String columnName, String newColumnName)
            throws Exception {
        try (Connection connection = getConnection()) {
            try (Statement statement = connection.createStatement()) {
                String sql = "ALTER TABLE " + tableName
                        + " RENAME COLUMN " + columnName + " TO " + newColumnName;
                statement.executeUpdate(sql);
            }
        }
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
        Properties properties = new Properties();

        TableEditor tableEditor = new TableEditor(properties);

        tableEditor.createTable("demo_db2");
        try (Connection connection = getConnection()) {
                System.out.println(getTableScheme(connection, "demo_db2"));
            }

        tableEditor.addColumn("demo_db2", "AGE", "int");
        try (Connection connection = getConnection()) {
            System.out.println(getTableScheme(connection, "demo_db2"));
        }

        tableEditor.dropColumn("demo_db2", "AGE");
        try (Connection connection = getConnection()) {
            System.out.println(getTableScheme(connection, "demo_db2"));
        }

        tableEditor.renameColumn("demo_db2", "name", "lastName");
        try (Connection connection = getConnection()) {
            System.out.println(getTableScheme(connection, "demo_db2"));
        }

        tableEditor.dropTable("demo_db2");
    }
}