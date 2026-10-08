Feature: Consultar Mascota por Estado

  Background:
    Given url baseUrl

  @ConsultarMascotaPorEstado
  Scenario: Consultar Mascota por estado

    * def resultado = call read('classpath:features/ActualizarMascota.feature')
    * def mascotaId = resultado.mascotaId

    And path '/pet/findByStatus'
    And param status = 'sold'
    And header Accept = 'application/json'
    When method get
    Then status 200
    * print response

    * def mascotaEncontrada = response.filter(x => x.id == mascotaId)
    * print 'Mascota encontrada:', mascotaEncontrada

