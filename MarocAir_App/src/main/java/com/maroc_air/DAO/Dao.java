package com.maroc_air.DAO;

import com.maroc_air.Database.Connection;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Dao extends Connection {
    public static HashMap<String, Dao> repositories = new HashMap<>();
    private final String tableName;


    //Constructor
    public Dao(String tableName) {
        Connection.getConnection();
        this.tableName = tableName;
    }

    //getters
    public String getTableName() {
        return tableName;
    }
    protected String getPrimaryKey() {
        return "id";
    }
    public String testTableName() {
        TableTest tableTest = getClass().getAnnotation(TableTest.class);
        if (tableTest != null) {
            return tableTest.tableName();
        }
        return null;
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
        query.append(" order by codeclient desc");
        return query.toString();
    }

    /**
     * get all data from table
     * @return ResultSet
     */
    public ResultSet getAll() {
        String query = "SELECT * FROM " + getTableName();

        try {
            PreparedStatement preparedStatement =  prepare(query);
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

    /**
     * return the size of ResultSet
     * @param resultSet ResultSet
     * @return
     */
    public static int getSize(ResultSet resultSet) {
        int size = 0;
        try {
            resultSet.last();
            size = resultSet.getRow();
            if (size > 0) {
                resultSet.beforeFirst();
            }
        }catch (SQLException e) {
            System.out.println("Error while getting size of result set");
            e.printStackTrace();
        }
        return size;
    }


    /**
     * Execute a dynamic select query that takes array of fields and array of values
     * @param fields String array of fields
     * @param values String array of values
     * @return ResultSet
     * @param <T> Generic type object
     */
    public <T> ResultSet getByFields(String[] fields,T[] values) {
        try {
            String query = selectQuery(fields);
            PreparedStatement preparedStatement =  prepare(query);
            System.out.println(preparedStatement);
            for (int i = 0; i < values.length; i++) {
                if (preparedStatement != null){
                    Connection.setParam(i + 1, values[i],preparedStatement);
                }
            }
            assert preparedStatement != null;
            ResultSet resultSet = execute(preparedStatement);
//            PreparedStatement statement = Connection.getConnection().prepareStatement("select * from clients limit 1");
//            ResultSet resultSet = statement.executeQuery();
            if (resultSet == null) {
                return null;
            }
            return resultSet;
        } catch (SQLException e) {
            System.out.println("Error while getting from \"" + getTableName() + "\" by string fields: " + Arrays.toString(fields) + " values:" + Arrays.toString(values));
            return null;
        }
    }

    /**
     * Construct a string query to execute insert into database
     * using dynamic fields and streams to filter and get field values
     * @return
     */
    public boolean save() {
        String primaryKey = getPrimaryKey();      // get primary key
        Object primaryKeyValue = getPrimaryKeyValue();  // get value of primary key
        assert primaryKeyValue != null;
        if (!primaryKeyValue.equals(-1)) {
//            return update();
        }
//        Build String Insert query using string builder
        StringBuilder query = new StringBuilder("INSERT INTO " + getTableName() + " (");
        StringBuilder values = new StringBuilder("VALUES (");
        ArrayList<String> unwanteds = new ArrayList<>(Arrays.asList(primaryKey));
//         filter TableName, empty, null, -1 fields using streams pipeline
        Field[] fields = Arrays.stream(getClass().getDeclaredFields()).filter(field -> {
            field.setAccessible(true);
            try {
                return field.get(this) != null
                        && !unwanteds.contains(field.getName())
                        && !field.get(this)
                        .toString()
                        .isEmpty()
                        && !field.get(this)
                        .toString()
                        .equals("-1");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                return false;
            }
        }).toArray(Field[]::new);
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            field.setAccessible(true);
            try {
                if (field.get(this) != null) {
                    query.append(field.getName());
                    values.append("?");
                    if (i < fields.length - 1) {
                        query.append(", ");
                        values.append(", ");
                    }
                }
            } catch (IllegalAccessException e) {
                System.out.println("Error could not access field " + field.getName());
                e.printStackTrace();
            }
        }
        query.append(") ").append(values).append(")");
        query.append(" RETURNING ").append(primaryKey);
        try {
            PreparedStatement preparedStatement = prepare(query.toString());
            System.out.println(preparedStatement);
            int index = 1;
            for (Field field : fields) {
                field.setAccessible(true);
                try {
                    if (field.get(this) != null) {
                        preparedStatement.setObject(index, field.get(this));
                        index++;
                    }
                } catch (IllegalAccessException e) {
                    System.out.println("Error while saving " + getTableName() + ": " + e.getMessage());
                    e.printStackTrace();
                }
            }
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet != null && resultSet.next()) {
                setPrimaryKeyValue(resultSet.getInt(primaryKey));
//                refresh();
            }
            return true;
        } catch (SQLException e) {
            System.out.println("Error while saving to \"" + getTableName() + "\" " + Arrays.toString(fields));
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Construct a string query to execute Update into table by id
     * using dynamic fields and streams to filter and get field values
     * @return
     */
    public boolean update() {
        StringBuilder query = new StringBuilder("update " + getTableName() + " set ");
        ArrayList<String> unwanteds = new ArrayList<>(Arrays.asList("id", "tableName"));
        Field[] fields = Arrays.stream(getClass().getDeclaredFields()).filter(field -> {
            field.setAccessible(true);
            try {
                return field.get(this) != null
                        && !unwanteds.contains(field.getName())
                        && !field.get(this)
                        .toString()
                        .isEmpty()
                        && !field.get(this)
                        .toString()
                        .equals("-1");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                return false;
            }
        }).toArray(Field[]::new);
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            field.setAccessible(true);
            try {
                if (field.get(this) != null) {
                    if (field.get(this)
                            .toString()
                            .equals("now()") || field.getName()
                            .equals("updated_at")) {
                        query.append(field.getName()).append(" = CURRENT_TIMESTAMP");
                    } else {
                        query.append(field.getName()).append(" = ?");
                    }
                    if (i < fields.length - 1) {
                        query.append(", ");
                    }
                }
            } catch (IllegalAccessException e) {
                System.out.println("Error could not access field " + field.getName());
                e.printStackTrace();
            }
        }
        String primaryKey = getPrimaryKey();
        Object primaryKeyValue = getPrimaryKeyValue();
        query.append(" where ").append(primaryKey).append(" = ?");
        try {
            PreparedStatement preparedStatement = prepare(query.toString());
            int index = 1;
            for (Field field : fields) {
                if (field.get(this).toString().equals("now()")) {
                    continue;
                }
                field.setAccessible(true);
                try {
                    if (field.get(this) != null) {
                        preparedStatement.setObject(index, field.get(this));
                        index++;
                    }
                } catch (IllegalAccessException e) {
                    System.out.println("Error while updating " + getTableName() + ": " + e.getMessage());
                    e.printStackTrace();
                }
            }
            preparedStatement.setObject(index, primaryKeyValue);
            preparedStatement.executeUpdate();
//            refresh();
            return true;
        } catch (SQLException | IllegalAccessException e) {
            System.out.println("Error while updating to \"" + getTableName() + "\" " + Arrays.toString(fields));
            e.printStackTrace();
            return false;
        }

    }

    

    /**
     * Get primary key value from class name by primaryKey
     */
    @Nullable
    private Object getPrimaryKeyValue() {
        String primaryKey = getPrimaryKey();
        Object primaryKeyValue = null;
        try {
            // get class then returns field object that is declared in field
            primaryKeyValue = getClass().getDeclaredField(primaryKey).get(this);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return primaryKeyValue;
    }
    /**
     * Set primary key value from class name by primaryKey
     */
    private void setPrimaryKeyValue(Object primaryKeyValue) {
        String primaryKey = getPrimaryKey();
        try {
            getClass().getDeclaredField(primaryKey).set(this, primaryKeyValue);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    protected String[] getFields() {
        return Arrays.stream(getClass().getDeclaredFields()).map(Field::getName).toArray(String[]::new);
    }
    @Override
    public String toString() {
        System.out.println(getTableName());
        String[] fields = getFields();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Entity: ").append(getClass().getName()).append(" {");
        for (String field : fields) {
            try {
                stringBuilder.append(field)
                        .append(": ")
                        .append(getClass().getDeclaredField(field).get(this))
                        .append(" ");
            } catch (IllegalAccessException | NoSuchFieldException e) {
                e.printStackTrace();
            }
        }
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

    public static void main(String[] args) {

    String[] clients = {"phone"};
    String[] data = {"123456"};
    Dao test = new Dao("clients");

        ResultSet resultSet = test.getByFields(clients,data);

                try {
                    while (resultSet.next())
                    {
                        System.out.println(resultSet.getString("email"));
                        System.out.println("tetst");
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

            }

}
