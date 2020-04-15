<h2>Melita Backend Task</h2>
(All commands in this README should be run in the same folder of this README.md file)

<h3>Build JAR File:</h2>
1. mvn clean install

<h3>Run JAR File:</h2>
2. java -jar target/16098338.jar

<h3>Build Docker Image:</h2>
(Dockerfile has to be available in this folder)
2. docker build -t grechjoseph/backend-task:latest .
3. (Optional) docker push grechjoseph/melita-backend-task
4. docker run -p 8080:8080 grechjoseph/melita-backend-task