package com.bdd.interactions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Interaction;

public class Pause implements Interaction {

    private final int segundos;

    public Pause(int segundos) {
        this.segundos = segundos;
    }

    public static Pause deSegundos(int segundos) {
        return new Pause(segundos);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        try {
            Thread.sleep(segundos * 1000L); // Convertir a milisegundos
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

