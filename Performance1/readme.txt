PRUEBA DE CARGA - SERVICIO LOGIN (https://fakestoreapi.com/auth/login)
Herramienta: Apache JMeter

VERSIONES
- Apache JMeter 5.6.3
- Java JDK 17 (minimo 8)
- Sistema operativo: Windows / Linux / macOS

ARCHIVOS
- LoginLoadTest.jmx : plan de pruebas
- users.csv         : datos parametrizados (user,passwd). Debe estar en la MISMA carpeta que el .jmx
- readme.txt        : este archivo
- conclusiones.txt  : hallazgos 

ESCENARIO
- 50 hilos (usuarios virtuales), ramp-up 30 s, duracion 300 s
- Constant Throughput Timer: 1500 muestras/min = 25 TPS (objetivo minimo 20 TPS)
- Validaciones: codigo 200/201, respuesta contiene "token", tiempo maximo 1500 ms
- Criterio de error: tasa de error < 3 % (revisar columna "Error %" en Summary/Aggregate Report)

EJECUCION POR INTERFAZ GRAFICA
1. Instalar Java 17 y descargar JMeter 5.6.3 (https://jmeter.apache.org/download_jmeter.cgi).
2. Ejecutar bin/jmeter.bat (Windows) o bin/jmeter.sh (Linux/macOS).
3. Menu File > Open > seleccionar LoginLoadTest.jmx.
4. Verificar que users.csv este junto al .jmx (si no, ajustar la ruta en "CSV - Usuarios").
5. Pulsar Start (boton verde). Ver resultados en Summary Report y Aggregate Report.


Parametros opcionales:
  -Jthreads=50 -Jramp=30 -Jduration=300 -Jtpsmin=1500


