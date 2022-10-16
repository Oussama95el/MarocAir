package com.maroc_air.Database;

import java.sql.*;
import java.sql.SQLException;
import java.util.Objects;


public abstract class Connection {
    private static java.sql.Connection connection = null;

    private ResultSet resultSet = null;



    public static java.sql.Connection getConnection() {
        if (connection == null) {
            try {
                Class.forName("org.postgresql.Driver");
                connection = java.sql.DriverManager.getConnection(Config.getUrl(), Config.getUser(), Config.getPassword());
                System.out.println("connected successfully");
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
    public static  <T>void setParam(int index, T data,PreparedStatement statement) {
        try{

//            preparedStatement.setObject(index, data);
            if ("Boolean".equals(((Object) data).getClass().getSimpleName())) {
                statement.setBoolean(index, (Boolean) data);
            } else if ("Integer".equals(((Object) data).getClass().getSimpleName())) {
                statement.setInt(index, (int) data);
            } else if ("Long".equals(((Object) data).getClass().getSimpleName())) {
                statement.setLong(index, (long) data);
            } else if ("String".equals(((Object) data).getClass().getSimpleName())) {
                statement.setString(index, (String) data);
            } else if ("Float".equals(((Object) data).getClass().getSimpleName())) {
                statement.setDouble(index, (Float) data);
            } else if ("Double".equals(((Object) data).getClass().getSimpleName())) {
                statement.setDouble(index, (Double) data);
            } else if ("LocalDate".equals(((Object) data).getClass().getSimpleName())) {
                statement.setObject(index, data);
            }

        } catch (SQLException e){
            e.printStackTrace();
            System.out.println("Error with  statement parameter placeholder");
            statement = null;
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
    public ResultSet execute (PreparedStatement statement) {
        try{
            if(statement != null){
                resultSet = statement.executeQuery();
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
    public int executeUpdate (PreparedStatement statement) {
        int count = 0;
        try{
            if(statement != null){
                count = statement.executeUpdate();
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

    public void closeQueryOperations (PreparedStatement statement) {
        if (resultSet != null) try { resultSet.close(); } catch (SQLException ignore) {}
        if (statement != null) try { statement.close(); } catch (SQLException ignore) {}
    }

}
