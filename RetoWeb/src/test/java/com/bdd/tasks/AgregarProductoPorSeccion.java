package com.bdd.tasks;

import com.bdd.interactions.Pause;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;

import static com.bdd.userinterfaces.DemoBlazeLoginUI.*;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isEnabled;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

public class AgregarProductoPorSeccion implements Task {

    private final String producto;
    private final String seccion;

    public AgregarProductoPorSeccion(String producto, String seccion) {
        this.producto = producto;
        this.seccion = seccion;
    }

    public static AgregarProductoPorSeccion conDatos(
            String producto,
            String seccion) {

        return Tasks.instrumented(
                AgregarProductoPorSeccion.class,
                producto,
                seccion
        );
    }

    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Pause.deSegundos(5),

                WaitUntil.the(btn_seccion(seccion), isEnabled())
                        .forNoMoreThan(15).seconds(),
                Click.on(btn_seccion(seccion)),

                Pause.deSegundos(3),

                WaitUntil.the(btn_producto(producto), isEnabled())
                        .forNoMoreThan(15).seconds(),
                Click.on(btn_producto(producto)),

                Pause.deSegundos(3),

                WaitUntil.the(btn_agregar_carrito, isEnabled())
                        .forNoMoreThan(15).seconds(),
                Click.on(btn_agregar_carrito)
        );

        // CERRAR EL ALERT "Product added."
        try {
            new WebDriverWait(
                    BrowseTheWeb.as(actor).getDriver(),
                    Duration.ofSeconds(3)
            ).until(ExpectedConditions.alertIsPresent());

            BrowseTheWeb.as(actor)
                    .getDriver()
                    .switchTo()
                    .alert()
                    .accept();

        } catch (TimeoutException e) {
            // El alert no apareció, continuamos
        }

        actor.attemptsTo(
                WaitUntil.the(btn_home, isVisible())
                        .forNoMoreThan(15).seconds(),
                Click.on(btn_home)
        );
    }

}
