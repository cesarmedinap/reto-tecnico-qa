package com.bdd.tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.waits.WaitUntil;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isVisible;

import static com.bdd.userinterfaces.DemoBlazeCompraUI.*;
import static net.serenitybdd.screenplay.matchers.WebElementStateMatchers.isEnabled;

public class RealizarPedidoDemoBlaze implements Task {

    private final String name;
    private final String country;
    private final String city;
    private final String creditCard;
    private final String month;
    private final String year;

    public RealizarPedidoDemoBlaze(
            String name,
            String country,
            String city,
            String creditCard,
            String month,
            String year) {

        this.name = name;
        this.country = country;
        this.city = city;
        this.creditCard = creditCard;
        this.month = month;
        this.year = year;
    }

    public static RealizarPedidoDemoBlaze conDatos(
            String name,
            String country,
            String city,
            String creditCard,
            String month,
            String year) {

        return Tasks.instrumented(
                RealizarPedidoDemoBlaze.class,
                name,
                country,
                city,
                creditCard,
                month,
                year
        );
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        actor.attemptsTo(
                WaitUntil.the(btn_place_order, isEnabled()).forNoMoreThan(15).seconds(),
                Click.on(btn_place_order),

                WaitUntil.the(txt_name, isEnabled())
                        .forNoMoreThan(15).seconds(),
                Enter.theValue(name).into(txt_name),

                WaitUntil.the(txt_country, isEnabled())
                        .forNoMoreThan(15).seconds(),
                Enter.theValue(country).into(txt_country),

                WaitUntil.the(txt_city, isEnabled())
                        .forNoMoreThan(15).seconds(),
                Enter.theValue(city).into(txt_city),

                WaitUntil.the(txt_credit_card, isEnabled())
                        .forNoMoreThan(15).seconds(),
                Enter.theValue(creditCard).into(txt_credit_card),

                WaitUntil.the(txt_month, isEnabled())
                        .forNoMoreThan(15).seconds(),
                Enter.theValue(month).into(txt_month),

                WaitUntil.the(txt_year, isEnabled())
                        .forNoMoreThan(15).seconds(),
                Enter.theValue(year).into(txt_year),

                WaitUntil.the(btn_purchase, isEnabled()).forNoMoreThan(15).seconds(),
                Click.on(btn_purchase),

                WaitUntil.the(lbl_compra_exitosa, isVisible()).forNoMoreThan(15).seconds()
        );
    }
}
