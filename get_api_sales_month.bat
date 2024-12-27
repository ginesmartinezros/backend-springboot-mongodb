@echo off
setlocal

:: Configuración
set TOKEN=09f85b34-a62d-45c2-b1ca-5829e2032712
set API_URL=http://localhost:8080/api/transactions/sales/month

:: Solicitud GET
echo Enviando solicitud GET...
curl -X GET "%API_URL%" ^
     -H "Authorization: %TOKEN%"
pause