package br.edu.fatecpg.licaojava.bd;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {
    public static Connection connection(){
        try{
            var jdbcUrl = "jdbc:postgresql://localhost:5432/tarefas";
            var user = "postgres";
            var password = "1234";
            return DriverManager.getConnection(jdbcUrl, user, password);
        }
        catch(SQLException e){
            System.err.println(e.getMessage());
        }
        return null;
    }
}

