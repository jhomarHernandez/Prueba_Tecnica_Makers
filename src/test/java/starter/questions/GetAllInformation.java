package starter.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import starter.abilities.ConnectToDataBase;
import starter.data.DataPruebaTecnica;
import starter.data.DataPruebaTecnicaStore;

import java.sql.ResultSet;
import java.sql.SQLException;

public class GetAllInformation implements Question<DataPruebaTecnicaStore> {

    public static Question<DataPruebaTecnicaStore> dataBase(){
        return new GetAllInformation();
    }

    @Override
    public DataPruebaTecnicaStore answeredBy(Actor actor) {
        DataPruebaTecnicaStore store = new DataPruebaTecnicaStore();
        try{
            ResultSet rs = ConnectToDataBase.as(actor).getAllInformation("select * from data_prueba_tecnica");
            while(rs.next()){
                DataPruebaTecnica pruebaTecnica = new DataPruebaTecnica(
                        rs.getString("fecha"),
                        rs.getString("portafolio"),
                        rs.getInt("nominal"),
                        rs.getDouble("precio"),
                        rs.getInt("total")
                );
                store.addData(pruebaTecnica);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return store;
    }
}
