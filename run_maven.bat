@echo off

cd demo

:: Ejecutar mvn clean install y luego mvn spring-boot:run si el primero tiene éxito
mvn clean install && mvn spring-boot:run