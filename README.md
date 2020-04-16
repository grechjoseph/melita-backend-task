<h2>Melita Customer Backend Task</h2>
This task is a basic system for adding and retrieval of Customer data from a Melita backend.

<h3>Features</h3>
<h2>Creating new Customers</h2>
1. Creating a new Customer to the Melita API provided.

<h2>Retrieving existing Customers</h2>
1. Retrieving a list of the IDs of all existing Customers.
2. Retrieving a Customer object by its ID.

<h3>Some Technologies Used</h3>
<ul>
    <li>Java</li>
    <li>Spring Boot</li>
    <li>Spring Web</li>
    <li>Spring Cache</li>
    <li>Lombok</li>
    <li>Spring Test</li>
    <li>Orika</li>
    <li>Open Feign</li>
</ul>

<h3>Running the project</h3>
This project is written using Java 11, therefore ensure Java 11 is available on the target machine, before running.

Build JAR File:
1. mvn clean install

Run JAR File:
1. java -jar target/16098338.jar

Build Docker Image:
(Dockerfile has to be available in this folder)
1. docker build -t grechjoseph/backend-task:latest .
2. (Optional) docker push grechjoseph/melita-backend-task
3. docker run -p 8080:8080 grechjoseph/melita-backend-task