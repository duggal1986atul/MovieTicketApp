# MovieTicketApp
Setup Requirement
1) Java 17 Installation
2) Docker engine Setup
3) Maven installation


To run test report , use mvn test command, This will generate a jacoco report at
target/site/jacoco/index.html
To run this app for local installation -> go to src/main/local
Run docker-compose up -d, This will fire mongodb container
Run following command -> mvn spring-boot:run.
Test against postman following end points
GET http://localhost:8080/transactions

POST http://localhost:8080/addTransactions
Sample body
{
"transactionId": 1,
"customers": [
{
"name": "JohnSmith",
"age": 70
},
{
"name": "JaneDoe",
"age": 5
},
{
"name": "BobDoe",
"age": 6
}
]
}