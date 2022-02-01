package starter.questions;

import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.TextContent;
import starter.ui.prueba_tecnica.ContactoPage;

public class ContactoQuestions {

    public static Question<String> cellPhoneNumber(){
        return actor -> TextContent.of(ContactoPage.LNK_CELLPHONE_NUMBER).answeredBy(actor).trim();
    }
}
