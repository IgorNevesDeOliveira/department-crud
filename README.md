### Department Registration

This project works as a department registration tool. It uses Java 11 and Postgres on the server-side and Angular 11 on the frontend.

**To run it locally you need docker and npm installed**

To create and run the Postgres docker image run the command:
```
docker run --name postgres-0 -e POSTGRES_USER=postgres -e POSTGRES_DB=itau -e POSTGRES_PASSWORD=postgres -d -p 5432:5432 postgres:alpine
```

To run the Java application you can use your desired IDE or use the command
```
./mvnw spring-boot:run
```

To run the Angular application, do the following command at the project root directory
```
npm install
```
and then:
```
npm start
```

After the project start the application will be available at localhost:4200
