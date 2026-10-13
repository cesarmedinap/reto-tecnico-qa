package stepdefinitions;

import com.bdd.tasks.AgregarProductoPorSeccion;
import com.bdd.tasks.IniciarSesionDemoBlaze;
import com.bdd.tasks.RealizarPedidoDemoBlaze;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.openqa.selenium.WebDriver;
import com.bdd.tasks.VisualizarCarrito;

import static com.bdd.userinterfaces.DemoBlazeCompraUI.lbl_compra_exitosa;
import static net.serenitybdd.screenplay.EventualConsequence.eventually;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static net.serenitybdd.screenplay.actors.OnStage.theActorInTheSpotlight;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;
import static net.serenitybdd.screenplay.questions.WebElementQuestion.the;


public class ComprarProductosStepDefinitions {
    @Before
    public void prepararEscenario() {

        OnStage.setTheStage(new OnlineCast());
        OnStage.theActorCalled("Usuario");

    }

    @Given("el usuario accede a la web de demoblaze")
    public void elUsuarioAccedeALaWebDeDemoblaze() {
        theActorInTheSpotlight().attemptsTo(
                Open.url("https://www.demoblaze.com/index.html")

        );
        WebDriver driver = Serenity.getDriver();
        driver.manage().window().maximize();
    }


    @When("inicia sesion en demoblaze con {string} y {string}")
    public void iniciaSesionEnDemoblazeConY(String user, String password) {
        theActorInTheSpotlight().attemptsTo(
                IniciarSesionDemoBlaze.conCredenciales(user,password)

        );

    }

    @And("agrega el producto {string} de la seccion {string}")
    public void agregaElProductoDeLaSeccion(String producto, String seccion) {
        theActorInTheSpotlight().attemptsTo(

                AgregarProductoPorSeccion.conDatos(producto,seccion)
        );
    }

    @And("visualiza el carrito")
    public void visualizaElCarrito() {
        theActorInTheSpotlight().attemptsTo(
                VisualizarCarrito.abrir()
        );
    }

    @And("realiza el pedido con los siguientes datos {string} {string} {string} {string} {string} {string}")
    public void realizaElPedidoConLosSiguientesDatos(String name, String country, String city, String CreditCard, String Month, String Year) {
        theActorInTheSpotlight().attemptsTo(

                RealizarPedidoDemoBlaze.conDatos(name, country, city, CreditCard, Month, Year)

        );
    }


    @Then("valida que la compra se realizo correctamente mediante el voucher de confirmacion")
    public void validaQueLaCompraSeRealizoCorrectamenteMedianteElVoucherDeConfirmacion() {
        theActorInTheSpotlight().attemptsTo(
                WaitUntil.the(lbl_compra_exitosa, isVisible())
                        .forNoMoreThan(30).seconds()
        );
    }







}
