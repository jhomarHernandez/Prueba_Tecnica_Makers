package starter.abilities;

import net.serenitybdd.screenplay.Ability;
import net.serenitybdd.screenplay.Actor;

import java.sql.*;

public class ConnectToDataBase implements Ability {

    private String connectorUrl = "jdbc:mysql://%s/%s?allowPublicKeyRetrieval=true&serverTimezone=America/Lima&useSSL=false";
    private String user;
    private String pass;

    private ConnectToDataBase(String host, String dbname, String user, String pass){
        connectorUrl = String.format(connectorUrl, host, dbname);
        this.user = user;
        this.pass = pass;
    }

    private Connection con;

    public static ConnectToDataBase mysqlOnline(String host, String dbname, String user, String pass){
        return new ConnectToDataBase(host, dbname, user, pass);
    }

    public static ConnectToDataBase as(Actor actor){
        return actor.abilityTo(ConnectToDataBase.class);
    }

    public void connect(){
        try{
            this.con = DriverManager.getConnection(connectorUrl, user,pass);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public ResultSet getAllInformation(String query){
        ResultSet resultSet = null;
        connect();
        try {
            PreparedStatement ps = con.prepareStatement(query);
            resultSet = ps.executeQuery();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return resultSet;
    }

    public void executeQuery(String query){
        connect();
        try {
            PreparedStatement ps = con.prepareStatement(query);
            ps.executeUpdate();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
