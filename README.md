# Optimistic Locking example using JPA

use case : 

there are two threads (users) running and order product concurrently

the first thread will lock the row of product stock and update it
the other one will try to update it and get caught optimistic locking

update stock value to test order function

DB : H2 Database
URL : http://localhost:3333/h2-console

Just run command -> mvn clean spring-boot:run

see the log for details state