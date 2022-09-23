FROM openjdk:17.0.2-jdk-slim-buster
RUN apt upgrade && addgroup main --disabled-password && \
    adduser main --ingroup main && \
    mkdir -p /app
WORKDIR /app
COPY app/target/*.jar app.jar
RUN chown -R main:main /app
USER main:main
ENV DB_HOST=DB_HOST \
    DB_PORT=DB_PORT \
    DB_NAME=DB_NAME \
    DB_USER=DB_USER \
    DB_PASS=DB_PASS \
    STATS_SERVER_URL=STATS_SERVER_URL
ENTRYPOINT java -DDB_HOST=$DB_HOST \
                -DDB_PORT=$DB_PORT \
                -DDB_NAME=$DB_NAME \
                -DDB_USER=$DB_USER \
                -DDB_PASS=$DB_PASS \
                -DSTATS_SERVER_URL=$STATS_SERVER_URL \
                -jar app.jar