package starter.tasks;

import io.cucumber.datatable.DataTable;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import starter.data.DataPruebaTecnica;
import starter.data.DataPruebaTecnicaStore;

import java.util.List;
import java.util.Map;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class SettingInputData implements Task {

    private final DataTable data;

    public SettingInputData(DataTable data) {
        this.data = data;
    }

    public static Performable withData(DataTable data){
        return instrumented(SettingInputData.class, data);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        List<Map<String, String>> rows = data.asMaps(String.class, String.class);
        DataPruebaTecnicaStore store = new DataPruebaTecnicaStore();
        for (Map<String, String> columns: rows){
            store.addData(new DataPruebaTecnica(columns.get("fecha"), columns.get("portafolio"),
                    Integer.parseInt(columns.get("nominal")), Double.parseDouble(columns.get("precio")),
                    Integer.parseInt(columns.get("total"))));
        }
        actor.remember("Lista", store);
    }
}
