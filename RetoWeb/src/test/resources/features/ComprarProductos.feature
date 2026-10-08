Feature: Compra de productos

  @ComprarProductos
  Scenario Outline: Usuario realiza exitosamente la compra de productos
    Given el usuario accede a la web de demoblaze
    When inicia sesion en demoblaze con "<user>" y "<password>"
    And agrega el producto "Nokia lumia 1520" de la seccion "Phones"
    And agrega el producto "Sony vaio i5" de la seccion "Laptops"
    And visualiza el carrito
    And realiza el pedido con los siguientes datos "<Name>" "<Country>" "<City>" "<CreditCard>" "<Month>" "<Year>"
    Then valida que la compra se realizo correctamente mediante el voucher de confirmacion

    Examples:
      | user        | password      | Name     | Country | City    | CreditCard       | Month | Year |
      | testautoweb | testautoweb   | DevUser  | Peru    | Lima    | 4111111111111111 | 10    | 2028 |