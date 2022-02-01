package starter.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import starter.data.DataPruebaTecnica;
import starter.data.DataPruebaTecnicaStore;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class ApplyNominalPerPricePlus2000 implements Task {

    public static Performable InCaseOfOBLRiesgoPortfolio(){
        return instrumented(ApplyNominalPerPricePlus2000.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        DataPruebaTecnicaStore store = actor.recall("Lista");
        for(DataPruebaTecnica dataPruebaTecnica: store.getData()){
            if(dataPruebaTecnica.getPortafolio().equals("OBL-RIESGO")){
                dataPruebaTecnica.setTotal(
                        (int) Math.round(((dataPruebaTecnica.getNominal() * dataPruebaTecnica.getPrecio())
                                + 2000)*100/100)
                );
            }
        }
        actor.remember("Lista", store);
    }
}
