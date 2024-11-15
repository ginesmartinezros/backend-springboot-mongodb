@echo off
REM Verificar si existe la carpeta "target" y eliminarla si es necesario
if exist target (
    echo Eliminando la carpeta "target"...
    rmdir /s /q target
    if %ERRORLEVEL% neq 0 (
        echo "No se pudo eliminar la carpeta target. Verifica los permisos."
        exit /b %ERRORLEVEL%
    )
)

REM Ejecutar mvn clean install y mvn spring-boot:run
mvn clean install && mvn spring-boot:run