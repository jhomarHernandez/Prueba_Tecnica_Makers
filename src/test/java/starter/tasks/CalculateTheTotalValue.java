package starter.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import starter.data.DataPruebaTecnica;
import starter.data.DataPruebaTecnicaStore;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class CalculateTheTotalValue implements Task {

    public static Performable nominalByPrice(){
        return instrumented(CalculateTheTotalValue.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        DataPruebaTecnicaStore store = actor.recall("Lista");
        for(DataPruebaTecnica dataPruebaTecnica: store.getData()){
            dataPruebaTecnica.setTotal((int) Math.round((dataPruebaTecnica.getNominal() * dataPruebaTecnica.getPrecio())
                    *100/100));
        }
        actor.remember("Lista", store);
    }
}
