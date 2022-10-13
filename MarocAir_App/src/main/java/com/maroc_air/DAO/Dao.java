package com.maroc_air.DAO;

import com.maroc_air.Database.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;

public class Dao extends Connection {
    public static HashMap<String, Dao> repositories = new HashMap<>();
    private final String tableName;

    //Constructor
    public Dao(String tableName) {
        this.tableName = tableName;
    }

    //getters
    public String getTableName() {
        return tableName;
    }

    public static Dao getRepository(String name) {
        Dao repository = repositories.get(name);
        if (repository == null) {
            repository = new Dao(name);
            repositories.put(name, repository);
        }
        return repository;
    }

    protected String selectQuery(String[] fields) {
        StringBuilder query = new StringBuilder("SELECT * FROM " + getTableName() + " WHERE ");
        for (int i = 0; i < fields.length; i++) {
            query.append(fields[i]).append(" = ?");
            if (i < fields.length - 1) {
                query.append(" AND ");
            }
        }
        // order by id desc
        query.append(" order by id desc");
        return query.toString();
    }

    public ResultSet getAll() {
        String query = "SELECT * FROM " + getTableName();
        try {
            PreparedStatement preparedStatement = Connection.prepare(query);
            assert preparedStatement != null;
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                return null;
            }
            return resultSet;
        } catch (SQLException e) {
            System.out.println("Error while getting all from \"" + getTableName() + "\"");
            return null;
        }
    }

    public <T> ResultSet getByFields(String[] fields,T[] values) throws SQLException {
        String query = selectQuery(fields);
        PreparedStatement preparedStatement = Connection.prepare(query);
        try {
            for (int i = 0; i < values.length; i++) {
               if (preparedStatement != null){
                   Connection.setParam(i + 1, values[i]);
               }
            }
            assert preparedStatement != null;
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                return null;
            }
            return resultSet;
        } catch (SQLException e) {
            System.out.println("Error while getting from \"" + getTableName() + "\" by string fields: " + Arrays.toString(fields) + " values:" + Arrays.toString(values));
            return null;
        }
    }

    public static void main(String[] args) {

    String[] clients = {"nom","prenom","email","phone"};
    Dao test = new Dao("clients");

        System.out.println( test.getAll());
    }
}