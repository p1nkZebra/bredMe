package logic;

import java.math.BigDecimal;
import java.sql.*;

public class Connector {



    public static void main(String[] args) {

        Connection connection = null;
        Statement statement = null;
        PreparedStatement preparedStatement = null;
        String sql = "";

        try {
            Class.forName("org.postgresql.Driver");


            String url = "jdbc:postgresql://localhost:5432/postgres";
            String user = "postgres";
            String password = "123";
            connection = DriverManager.getConnection(url, user, password);

            statement = connection.createStatement();


            sql = "create schema if not exists Elvira_in_java";
            statement.executeUpdate(sql);


            sql = "create table if not exists Elvira_in_java.actor " +
                    "( " +
                    " id            serial primary key  not null, " +
                    " name          varchar(100)        not null, " +
                    " second_name   varchar(100)        not null " +
                    ")";
            statement.executeUpdate(sql);


            sql = "create table if not exists Elvira_in_java.film " +
                    "( " +
                    " id            serial primary key  not null, " +
                    " name          varchar(100)        not null, " +
                    " director      varchar(100)        not null " +
                    ")";
            statement.executeUpdate(sql);


            sql = "insert INTO Elvira_in_java.film (name, director) " +
                    "values (?, ?) " +
                    "";

            preparedStatement =
                    connection.prepareStatement(sql);

            preparedStatement.setString(1, "Бойцовский клуб");
            preparedStatement.setString(2, "Larson");
            preparedStatement.executeUpdate();



            ResultSet result = statement.executeQuery("select * from Elvira_in_java.actor");

            while(result.next()) {
                long id = result.getLong("id");
                String name = result.getString("name");

                System.out.println("id: " + id);
                System.out.println("name: " + name);

            }


            System.out.println();

            statement.close();
            connection.close();


        } catch (ClassNotFoundException e) {
            e.printStackTrace();

            System.out.println("Error in sql: " + sql);

        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }



    }
}


