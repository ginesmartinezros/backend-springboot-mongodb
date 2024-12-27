@echo off
setlocal

:: Configuración
set TOKEN=61Soe2xKUSrrWOhljy0P7Cb
set API_URL=http://localhost:8080/api/transactions/non-sales

:: Solicitud GET
echo Enviando solicitud GET...
curl -X GET "%API_URL%" ^
     -H "Authorization: %TOKEN%"
pause