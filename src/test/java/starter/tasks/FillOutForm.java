package starter.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.waits.WaitUntil;
import starter.ui.prueba_tecnica.DejenosUnMensajeForm;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class FillOutForm implements Task {

    private String numberPhone;
    private String firstName;
    private String lastName;
    private String email;
    private String message;

    public FillOutForm(String numberPhone, String firstName, String lastName, String email, String message) {
        this.numberPhone = numberPhone;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.message = message;
    }

    public static FillOutFormWith with(){
        return new FillOutFormWith();
    }

    public Performable andCellNumber(String numberPhone) {
        this.numberPhone = numberPhone;
        return instrumented(FillOutForm.class, this.numberPhone);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                WaitUntil.the(DejenosUnMensajeForm.FIRST_NAME_FIELD, isVisible())
                        .forNoMoreThan(10).seconds(),
                Enter.theValue(firstName).into(DejenosUnMensajeForm.FIRST_NAME_FIELD),
                Enter.theValue(lastName).into(DejenosUnMensajeForm.LAST_NAME_FIELD),
                Enter.theValue(email).into(DejenosUnMensajeForm.EMAIL_FIELD),
                Enter.theValue(numberPhone).into(DejenosUnMensajeForm.PHONE_FIELD),
                Enter.theValue(message).into(DejenosUnMensajeForm.MESSAGE_FIELD)
        );
    }

    public static class FillOutFormWith{
        private String numberPhone;
        private String firstName;
        private String lastName;
        private String email;
        private String message;

        public FillOutFormWith firstName(String firstName){
            this.firstName = firstName;
            return this;
        }

        public FillOutFormWith lastName(String lastName){
            this.lastName = lastName;
            return this;
        }

        public FillOutFormWith email(String email){
            this.email = email;
            return this;
        }

        public FillOutFormWith message(String message){
            this.message = message;
            return this;
        }

        public Performable andNumberPhone(String numberPhone){
            return new FillOutForm(numberPhone, firstName, lastName, email, message);
        }
    }
}
