package com.maroc_air.Database;

import java.sql.*;
import java.sql.SQLException;
import java.util.Objects;


public abstract class Connection {
    private static java.sql.Connection connection = null;
    private static PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;


    public static java.sql.Connection getConnection() {
        if (connection == null) {
            try {
                Class.forName("org.postgresql.Driver");
                connection = java.sql.DriverManager.getConnection(Config.getUrl(), Config.getUser(), Config.getPassword());
                return connection;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        return connection;
    }


    /**
     * Set statement after setting parameters and cast them to the referred type
     * @param index int
     * @param data generic type T it can be (Boolean, Int, Long...)
     * @param <T> generic type T
     */
    public static  <T>void setParam(int index, T data) {
        try{

//            preparedStatement.setObject(index, data);
            switch (((Object) data).getClass().getSimpleName()) {
                case "Boolean" -> preparedStatement.setBoolean(index, (Boolean) data);
                case "Integer" -> preparedStatement.setInt(index, (int) data);
                case "Long" -> preparedStatement.setLong(index, (long) data);
                case "String" -> preparedStatement.setString(index, (String) data);
                case "Float" -> preparedStatement.setDouble(index, (Float) data);
                case "Double" -> preparedStatement.setDouble(index, (Double) data);
                case "LocalDate" ->preparedStatement.setObject(index, data);
            }

        } catch (SQLException e){
            e.printStackTrace();
            System.out.println("Error with  statement parameter placeholder");
            preparedStatement = null;
        }
    }

    /**
     * Initialize statement and return prepared statement
     * @param query String
     * @return PreparedStatement
     */
    protected static PreparedStatement prepare(String query) throws SQLException {
        return Objects.requireNonNull(getConnection())
                .prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
    }


    /**
     * Execute query and return a result set
     * @return ResultSet
     */
    public ResultSet execute () {
        try{
            if(preparedStatement != null){
                resultSet = preparedStatement.executeQuery();
                return resultSet;
            } else {
                System.out.println("Prepared query is null!");
                return null;
            }
        } catch (SQLException e){
            e.printStackTrace();
            System.out.println(e.getMessage());
            return null;
        }
    }
    public int executeUpdate () {
        int count = 0;
        try{
            if(preparedStatement != null){
                count = preparedStatement.executeUpdate();
            } else {
                System.out.println("Prepared query is null!");
            }
        } catch (SQLException e){
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return count;
    }



    public boolean isEmpty() {
        boolean isEmpty = true;
        if (resultSet != null) {
            try {
                isEmpty = !resultSet.isBeforeFirst();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return isEmpty;
    }

    public void closeConnection () {
        if (connection != null) try {
            connection.close();
            System.out.println("Closing DB connection..");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error closing DB connection");
        }
    }

    public void closeQueryOperations () {
        if (resultSet != null) try { resultSet.close(); } catch (SQLException ignore) {}
        if (preparedStatement != null) try { preparedStatement.close(); } catch (SQLException ignore) {}
    }

}
