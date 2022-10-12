package com.maroc_air.DAO;

import com.maroc_air.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class DAO extends Connection {
    public static HashMap<String, DAO> repositories = new HashMap<>();
    private final String tableName;

    //Constructor
    public DAO(String tableName) {
        this.tableName = tableName;
    }

    //getters
    public String getTableName() {
        return tableName;
    }

    public static DAO getRepository(String name) {
        DAO repository = repositories.get(name);
        if (repository == null) {
            repository = new DAO(name);
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
            PreparedStatement preparedStatement = db.prepare(query);
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

}
