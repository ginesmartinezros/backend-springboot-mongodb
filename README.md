**Sales webpage server with Springboot and MongoDB**

- Maven build

`cd demo`

`mvn clean install`

`mvn spring-boot:run`

or in Windows

`.\run_maven.bat`

- Docker

`docker build -t pbenito-app .`

`docker run -p 8080:8080 -e SECRET_TOKEN_PBENITO=TOKEN pbenito-app`

- API
  - `api/test/`

`curl -H "Authorization: TOKEN" https://pbenito.onrender.com/api/test`
