package starter.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import starter.abilities.ConnectToDataBase;
import starter.data.DataPruebaTecnicaStore;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;

public class InsertIntoDataBase implements Task {

    public static Performable theInputData(){
        return instrumented(InsertIntoDataBase.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        DataPruebaTecnicaStore lista = theActorInTheSpotlight().recall("Lista");
        lista.getData().forEach(data -> {
            ConnectToDataBase.as(actor).executeQuery(
                    "insert into data_prueba_tecnica (fecha, portafolio, nominal, precio, total) values ('"
                            + data.getFecha() + "','" + data.getPortafolio() + "'," + data.getNominal() + "," +
                            data.getPrecio() + "," + data.getTotal() +")");
        });
    }
}
