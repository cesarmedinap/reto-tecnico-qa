package com.bdd.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.waits.WaitUntil;

import static com.bdd.userinterfaces.DemoBlazeLoginUI.*;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isEnabled;

public class IniciarSesionDemoBlaze implements Task {

    private final String username;
    private final String password;

    public IniciarSesionDemoBlaze(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public static IniciarSesionDemoBlaze conCredenciales(
            String username,
            String password) {

        return Tasks.instrumented(
                IniciarSesionDemoBlaze.class,
                username,
                password
        );
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                WaitUntil.the(btn_login,isEnabled()).forNoMoreThan(15).seconds(),
                Click.on(btn_login),
                Enter.theValue(username).into(txt_username),
                Enter.theValue(password).into(txt_password),
                WaitUntil.the(btn_confirm_login,isEnabled()).forNoMoreThan(15).seconds(),
                Click.on(btn_confirm_login)
        );
    }
}
