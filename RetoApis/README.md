# Karate API - Proyecto de Automatización de Pruebas Petstore

## 📋 Descripción

Proyecto de automatización de pruebas de API utilizando Karate Framework, tomando como aplicación bajo prueba la API de Petstore

---

## 🔧 Tecnologías Utilizadas

- **Karate Framework 1.4.1**: Framework de automatización de pruebas API y UI
- **JUnit 5**: Framework de testing
- **Maven**: Gestor de dependencias y compilación
- **Java 11**: Lenguaje de programación
- **SLF4J + Logback**: Logging y monitoreo

---

## 📁 Estructura del Proyecto

```
karate-test/
├── src/
│   ├
│   │
│   └── test/
│       ├── java/
│       │   └── app/test/
│       │       └── TestRunner.java    # Ejecutor principal de pruebas
│       │
│       └── resources/
│           ├── features/              # Archivos .feature con los escenarios
│           │   ├── ActualizarMascota.feature
│           │   ├── AgregarMascota.feature
│           │   ├── ConsultarMascotaPorEstado.feature
│           │   ├── ConsultarMascotaPorID.feature
│           │
│           ├── request/                # request
│           │   └── AgregarMascota.json
│           │
│           │
│           ├── karate-config.js       # Configuración global de Karate
│           └── logback-test.xml       # Configuración de logging
│
├── target/                  # Directorio de compilación y reportes
├── pom.xml                  # Configuración Maven
└── README.md               # Este archivo
```
## 🔗 Dependencia entre las Pruebas

Los escenarios están relacionados entre sí y se ejecutan siguiendo un flujo de dependencias. Cada escenario utiliza información generada por el escenario anterior.

El flujo es el siguiente:

```text
ConsultarMascotaPorEstado
        ↓
ActualizarMascota
        ↓
ConsultarMascotaPorID
        ↓
AgregarMascota
```
Cada escenario tiene como prerrequisito el escenario que necesita para obtener la información requerida. Por esta razón, los escenarios pueden ejecutarse individualmente mediante su tag sin necesidad de ejecutar previamente los demás escenarios de forma manual.

## ▶️ Ejecutar Pruebas

Las pruebas pueden ejecutarse de dos formas: desde el TestRunner en IntelliJ IDEA o desde la consola utilizando Maven.

Ejecutar desde TestRunner
El archivo TestRunner.java permite definir un tag por defecto para ejecutar un escenario específico:
```
String tag = System.getProperty(
        "karate.tag",
        "@ConsultarMascotaPorEstado"
);
```
Ejecutar desde consola
También es posible indicar el tag desde Maven, sin modificar el código.
De esta manera, se puede seleccionar qué escenarios ejecutar utilizando el tag correspondiente.
```
mvn -Dtest=TestRunner "-Dkarate.tag=@ActualizarMascota" test
```
## 📊 Reporte de Pruebas
Después de ejecutar las pruebas, Karate genera automáticamente el reporte HTML en:
```
target/site/karate-reports/karate-summary.html
```
Este archivo puede abrirse directamente en un navegador para consultar el resultado de los escenarios ejecutados.
