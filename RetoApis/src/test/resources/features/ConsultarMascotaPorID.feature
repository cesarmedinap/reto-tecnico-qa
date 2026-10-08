Feature: Consultar Mascota por ID

  Background:
    Given url baseUrl

  @ConsultarMascotaID
  Scenario: Consultar Mascota por ID

    * def resultado = call read('classpath:features/AgregarMascota.feature')
    * def mascotaId = resultado.mascotaId
    And  path '/pet', mascotaId
    And header Accept = 'application/json'
    When method get
    Then status 200
    * print response
    * def mascotaId = response.id

