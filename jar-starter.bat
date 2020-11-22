docker build -f devenv/Dockerfile-jar-starter -t jar-starter .
docker run -p 8080:8080 --rm --name jar-starter-container jar-starter