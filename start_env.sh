#!/bin/bash

./gradlew clean bootJar
docker-compose up -d --build
