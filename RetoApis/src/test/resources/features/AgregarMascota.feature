Feature: Agregar Mascota

  Background:
    Given url baseUrl

  @AgregarMascota
  Scenario: Agregar una Mascota

    * def randomDog = 'Dog' + Math.floor(Math.random() * 9000 + 1000)
    * def body = read('classpath:request/AgregarMascota.json')
    * set body.name = randomDog
    And path '/pet'
    And header Accept = 'application/json'
    And request body
    When method post
    Then status 200
    * print response
    * def mascotaId = response.id
