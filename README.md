Build Docker Image:
1. Check Dockerfile is available.
2. docker build -t grechjoseph/melita-backend-task:latest .
3. (Optional) docker push grechjoseph/melita-backend-task
4. docker run -p 8080:8080 grechjoseph/melita-backend-task