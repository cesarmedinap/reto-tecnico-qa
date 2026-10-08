Feature: Actualizar datos de Mascota

  Background:
    Given url baseUrl

  @ActualizarMascota
  Scenario: Actualizar datos de mascota
    * def resultado = call read('classpath:features/ConsultarMascotaPorID.feature')
    * def mascotaId = resultado.mascotaId
    * def aleatorio = 'Cat' + Math.floor(Math.random() * 9000 + 1000)
    * def body = read('classpath:request/AgregarMascota.json')
    * set body.name = aleatorio
    * set body.id = mascotaId
    * set body.status = 'sold'
    And path '/pet'
    And header Accept = 'application/json'
    And request body
    When method put
    Then status 200
    * print response
    * def mascotaId = response.id
