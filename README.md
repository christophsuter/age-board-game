# age-board-game

Project to learn Unit-Testing with JUnit and Mockito.


## Build Project

Building Project:

`mvn clean package`

Build Project with Javadoc (used for Swagger) and create distribution directory:

`mvn clean package -Pattach-src-and-javadoc -Pdistribution`

## Start Application
Starting the application:

`cd target/age-board-game-${version}-bin`

`java -jar age-board-game-${version}.jar server conf.conf`

## Access the resource
The application can be accessed on the following URL (defined in conf.yml):

`http://localhost:8080/api/`

## Swagger (Description of the API)
`http://localhost:8080/api/doc/`