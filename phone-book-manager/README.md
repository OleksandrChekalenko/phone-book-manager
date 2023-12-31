# phone-book-manager

### How to run locally using IntelliJ?
1. Clean & build package
```
mvn clean
mvn install
```
2. Set up running environments
* ENV = local or dev (set to "local")
* AWS.ACCESS.KEY = your AWS access key
* AWS.SECRET.KEY = your AWS secret key
* AWS.BUCKET.NAME = AWS bucket name
* AWS.REGION = AWS region
* DB.USER.NAME = user name for DB access
* DB.USER.PASS = user password for DB access
* SERVER_PORT = if you want to use different of 8080

3.Run the application

### How to perform database schema migration

1. Create a sql file in this format V__<timestamp>_<description>.sql. 
    The timestamp is in the format of yyyyMMddHHmmSS. 
    One sample sql file would be V202307051630__CreatePersonTable.sql
2. Test the sql file against the database in the local environment.
3. Check in the sql file to the directory src/main/resources/db/migration.
4. The sql file will be migrated after deployment upon application startup.

### Swagger UI

- http://localhost:8000/swagger-ui/index.html