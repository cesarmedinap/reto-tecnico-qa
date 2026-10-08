package com.bdd.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static com.bdd.userinterfaces.DemoBlazeLoginUI.btn_cart;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isEnabled;

public class VisualizarCarrito implements Task {

    public static VisualizarCarrito abrir() {
        return Tasks.instrumented(VisualizarCarrito.class);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                WaitUntil.the(btn_cart, isEnabled()).forNoMoreThan(15).seconds(),
                Click.on(btn_cart)
        );
    }
}