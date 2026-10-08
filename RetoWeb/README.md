# Automation DemoBlaze - Screenplay Pattern

Proyecto de automatización web de la aplicación **DemoBlaze**, desarrollado utilizando **Java, Cucumber, Serenity BDD, Selenium WebDriver y Screenplay Pattern**.

El proyecto implementa pruebas automatizadas utilizando BDD (Behavior Driven Development) mediante escenarios escritos en Gherkin y genera reportes de ejecución con Serenity BDD.

## 📋 Requisitos

- Java 11+
- Maven 3.6+
- Google Chrome instalado
- Conexion a Internet

## 📊 Stack Tecnológico

| Tecnología | Versión |
|---|---|
| Java | 11 |
| Cucumber | 7.11.0 |
| Serenity BDD | 3.6.12 |
| JUnit | 4.13.2 |
| Selenium WebDriver | Gestionado por Serenity |
| WebDriverManager | 5.6.3 |
| Maven Compiler Plugin | 3.11.0 |
| Maven Surefire Plugin | 3.0.0 |

## 🎭 Patrón de diseño

El proyecto utiliza **Screenplay Pattern**, permitiendo organizar la automatización en componentes reutilizables y fáciles de mantener.

La estructura principal contempla:

- **Tasks**: acciones de negocio realizadas por el Actor.
- **Interactions**: acciones atómicas y reutilizables.
- **Questions**: consultas y validaciones.
- **User Interfaces**: elementos y localizadores de la aplicación.
- **Models**: modelos de datos.
- **Utils**: utilidades generales.

## 📁 Estructura del Proyecto

```text
src/
├── test/
│   ├── java/
│   │   ├── runners/                # Runners de Cucumber + Serenity
│   │   ├── stepdefinitions/        # Definiciones de pasos Gherkin
│   │   ├── hooks/                  # Hooks de Cucumber
│   │   │
│   │   └── com/bdd/
│   │       ├── interactions/       # Acciones atómicas
│   │       ├── models/             # Modelos de datos
│   │       ├── questions/          # Validaciones
│   │       ├── tasks/              # Tareas de negocio
│   │       ├── userinterfaces/     # Selectores UI
│   │       └── utils/              # Utilidades
│   │
│   └── resources/
│       ├── features/               # Escenarios Gherkin
│       └── serenity.conf           # Configuración de Serenity BDD
│
└── pom.xml
```


## ▶️ Ejecución de las Pruebas

Las pruebas se ejecutan mediante Maven utilizando el **tag de Cucumber** como parámetro.

### Ejecutar pruebas por Tag

Por ejemplo, para ejecutar los escenarios asociados al tag:

```text
@ComprarProductos

Utilizar:
mvn clean test "-Dcucumber.filter.tags=@ComprarProductos"
```

Este comando:
Limpia los resultados anteriores.
Compila el proyecto.
Ejecuta las pruebas.
Ejecuta únicamente los escenarios asociados al tag indicado.

## 📊 Generación del Reporte Serenity BDD

Después de ejecutar las pruebas, se genera el reporte HTML de Serenity utilizando:
```text
mvn serenity:aggregate
```

El reporte se encuentra en:
target/site/serenity/index.html



