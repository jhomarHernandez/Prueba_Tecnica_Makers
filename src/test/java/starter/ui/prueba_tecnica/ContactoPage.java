package starter.ui.prueba_tecnica;

import net.serenitybdd.core.pages.PageObject;
import org.openqa.selenium.By;

public class ContactoPage extends PageObject {

    public static By LNK_CELLPHONE_NUMBER = By.xpath("//a[contains(@title,'celular')]");

    public static DejenosUnMensajeForm dejenosUnMensajeForm;

}
