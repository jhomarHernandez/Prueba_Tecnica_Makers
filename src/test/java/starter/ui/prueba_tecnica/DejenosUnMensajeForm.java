package starter.ui.prueba_tecnica;

import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.By;

public class DejenosUnMensajeForm extends PageObject {

    public static By FIRST_NAME_FIELD = By.id("et_pb_contact_first_0");

    public static By LAST_NAME_FIELD = By.id("et_pb_contact_last_0");

    public static By EMAIL_FIELD = By.id("et_pb_contact_email_0");

    public static By PHONE_FIELD = By.id("et_pb_contact_phone_0");

    public static By MESSAGE_FIELD = By.id("et_pb_contact_message_0");

    public static By SEND_MESSAGE_BUTTON = By.xpath("//button[@type='submit'][contains(.,'Enviar Mensaje')]");

}
