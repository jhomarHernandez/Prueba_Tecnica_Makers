package starter.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import starter.abilities.ConnectToDataBase;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class ClearDataBase implements Task {
    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.whoCan(ConnectToDataBase.mysqlOnline("sql3.freemysqlhosting.net:3306"
                ,"sql3469282", "sql3469282", "W2qTEgQbu3"));
        ConnectToDataBase.as(actor).executeQuery("truncate data_prueba_tecnica");
    }

    public static Performable toTest(){
        return instrumented(ClearDataBase.class);
    }
}
