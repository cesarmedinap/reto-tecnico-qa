package com.bdd.userinterfaces;
import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class DemoBlazeCompraUI {

    public static final Target btn_place_order = Target.the("botón realizar pedido")
            .located(By.cssSelector("button[data-target='#orderModal']"));

    public static final Target txt_name = Target.the("campo nombre")
            .located(By.id("name"));

    public static final Target txt_country = Target.the("campo país")
            .located(By.id("country"));

    public static final Target txt_city = Target.the("campo ciudad")
            .located(By.id("city"));

    public static final Target txt_credit_card = Target.the("campo tarjeta de crédito")
            .located(By.id("card"));

    public static final Target txt_month = Target.the("campo mes")
            .located(By.id("month"));

    public static final Target txt_year = Target.the("campo año")
            .located(By.id("year"));

    public static final Target btn_purchase = Target.the("botón comprar")
            .located(By.cssSelector("button[onclick='purchaseOrder()']"));

    public static final Target lbl_compra_exitosa = Target.the("mensaje de compra exitosa")
            .located(By.xpath("//h2[normalize-space()='Thank you for your purchase!']"));

}
