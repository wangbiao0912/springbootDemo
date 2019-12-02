#!/usr/bin/env bash
mvn clean package -Dmaven.test.skip=true
mvn clean package docker:build
docker run -p 9999:9999 -t cache-sql:latesty
#scp target/ElasticSearchTest-1.0.jar.original nti_java@10.8.24.249:/home/nti_java/esTest/lib/ElasticSearchTest-1.0.jar
