package com.bdd.userinterfaces;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class DemoBlazeLoginUI {

    public static final Target btn_login = Target.the("botón de iniciar sesión")
            .located(By.id("login2"));

    public static final Target txt_username = Target.the("campo de usuario")
            .located(By.id("loginusername"));

    public static final Target txt_password = Target.the("campo de contraseña")
            .located(By.id("loginpassword"));

    public static final Target btn_confirm_login = Target.the("botón confirmar inicio de sesión")
            .located(By.xpath("//button[@onclick='logIn()']"));

    public static Target btn_seccion(String seccion) {
        return Target.the("sección " + seccion)
                .located(By.xpath("//a[@id='itemc' and normalize-space()='" + seccion + "']"));
    }

    public static Target btn_producto(String nombreProducto) {
        return Target.the("producto " + nombreProducto)
                .located(By.xpath("//a[@class='hrefch' and normalize-space()='" + nombreProducto + "']"));
    }

    public static final Target btn_cart = Target.the("botón del carrito")
            .located(By.id("cartur"));


    public static final Target btn_agregar_carrito = Target.the("botón agregar al carrito")
            .located(By.xpath("//a[contains(normalize-space(), 'Add to cart')]"));


    public static final Target btn_home = Target.the("botón de inicio")
            .located(By.cssSelector("a[href='index.html']"));




}
