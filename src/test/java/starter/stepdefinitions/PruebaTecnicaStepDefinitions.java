package starter.stepdefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.es.*;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.targets.Target;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.thucydides.core.util.EnvironmentVariables;
import org.hamcrest.Matchers;
import org.openqa.selenium.By;
import starter.abilities.ConnectToDataBase;
import starter.data.DataPruebaTecnicaStore;
import starter.questions.ContactoQuestions;
import starter.questions.GetAllInformation;
import starter.tasks.*;
import starter.ui.prueba_tecnica.DejenosUnMensajeForm;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.*;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class PruebaTecnicaStepDefinitions {

    private EnvironmentVariables environmentVariables;

    @Before
    public void setTheStage(){
        OnStage.setTheStage(new OnlineCast());
    }

    @Before
    public void clearBD(){
        theActorCalled("Actor").attemptsTo(
                ClearDataBase.toTest()
        );
    }

    @Dados("los siguientes parametros de entrada")
    public void losSiguientesParametrosDeEntrada(DataTable table) {
        theActorInTheSpotlight().attemptsTo(
                SettingInputData.withData(table)
        );
    }

    @Entonces("debo insertar los registros en la base de datos")
    public void deboInsertarLosRegistrosEnLaBaseDeDatos() {
        theActorInTheSpotlight().whoCan(
                ConnectToDataBase.mysqlOnline(
                        environmentVariables.optionalProperty("mysqlonline.host")
                                .orElseThrow(IllegalArgumentException::new),
                        environmentVariables.optionalProperty("mysqlonline.dbname")
                                .orElseThrow(IllegalArgumentException::new),
                        environmentVariables.optionalProperty("mysqlonline.user")
                                .orElseThrow(IllegalArgumentException::new),
                        environmentVariables.optionalProperty("mysqlonline.password")
                                .orElseThrow(IllegalArgumentException::new)
                ));
        theActorInTheSpotlight().attemptsTo(
                InsertIntoDataBase.theInputData()
        );
    }

    @Dados("los parametros de entrada previos")
    public void losParametrosDeEntradaPrevios() {

    }

    @Entonces("calcular el valor total asi [nominal * precio]")
    public void calcularElValorTotalAsiNominalPrecio() {
        theActorInTheSpotlight().attemptsTo(
                CalculateTheTotalValue.nominalByPrice()
        );
    }

    @Y("validar que el valor total calculado sea igual al campo total de la base de datos")
    public void validarQueElValorTotalCalculadoSeaIgualAlCampoTotalDeLaBaseDeDatos() {
        DataPruebaTecnicaStore storeCalculate = theActorInTheSpotlight().recall("Lista");
        theActorInTheSpotlight().should(
                seeThat("Se valida que el valor calculado sea igual que el campo total de la base de datos",
                        GetAllInformation.dataBase(), Matchers.equalTo(storeCalculate))
        );
    }

    @Dada("la siguiente url {string}")
    public void laSiguienteUrl(String url) {
        theActorCalled("Actor").attemptsTo(
                Open.url(url)
        );
    }

    @Cuando("hacemos clic en el enlace de {string}")
    public void hacemosClicEnElEnlaceDe(String link) {
        theActorInTheSpotlight().attemptsTo(
                WaitUntil.the(Target.the("Enlace " + link).located(By.linkText(link)), isVisible())
                        .forNoMoreThan(20).seconds(),
                Click.on(Target.the("Enlace " + link).located(By.linkText(link)))
        );
    }

    @Entonces("capturar el numero de celular de servicio al cliente")
    public void capturarElNumeroDeCelularDeServicioAlCliente() {
        theActorInTheSpotlight().remember("CellPhoneNumber", ContactoQuestions.cellPhoneNumber()
                .answeredBy(theActorInTheSpotlight()));
    }

    @Y("llenar el formulario \"dejenos un mensaje\" y en el campo mensaje adjuntar el numero de celular capturado")
    public void llenarElFormularioYEnElCampoMensajeAdjuntarElNumeroDeCelularCapturado() {
        theActorInTheSpotlight().attemptsTo(
                FillOutForm.with()
                        .firstName(environmentVariables.optionalProperty("contactenos.first_name")
                                .orElseThrow(IllegalArgumentException::new))
                        .lastName(environmentVariables.optionalProperty("contactenos.last_name")
                            .orElseThrow(IllegalArgumentException::new))
                        .email(environmentVariables.optionalProperty("contactenos.email")
                                .orElseThrow(IllegalArgumentException::new))
                        .message(environmentVariables.optionalProperty("contactenos.message")
                                .orElseThrow(IllegalArgumentException::new))
                        .andNumberPhone(theActorInTheSpotlight().recall("CellPhoneNumber"))
        );
    }

    @Y("antes de hacer click en el boton \"enviar mensaje\" tomar un pantallazo con la información diligenciada")
    public void antesDeHacerClickEnElBotonTomarUnPantallazoConLaInformaciónDiligenciada() {
        theActorInTheSpotlight().attemptsTo(
                TakesAScreenShotWithSelenium.withTitle("Foto solicitada"),
                Click.on(DejenosUnMensajeForm.SEND_MESSAGE_BUTTON)
        );
    }

    @Pero("si el portafolio es {string} se debe calcular el valor total asi [\\(nominal * precio) + {int}]")
    public void siElPortafolioEsSeDebeCalcularElValorTotalAsiNominalPrecio(String arg0, int arg1) {
        theActorInTheSpotlight().attemptsTo(
                ApplyNominalPerPricePlus2000.InCaseOfOBLRiesgoPortfolio()
        );
    }
}
