FROM openjdk:8-jdk-alpine

VOLUME /tmp
ENV JAVA_OPTS=""

# For profiling with the JProfiler GUI
# RUN wget -qO- http://download-keycdn.ej-technologies.com/jprofiler/jprofiler_linux_10_1.tar.gz | tar -xz -C /usr/local
# EXPOSE 8849

# For native JVM profiling
# Run `docker exec compose_cloud_1 jcmd 0 VM.native_memory summary`
# where 0 is the PID of java inside the container
RUN apk add --no-cache tini

ADD build/libs/kotlin-springboot-starter-0.1.0-SNAPSHOT.jar app.jar

ENTRYPOINT ["/sbin/tini", "--"]
CMD java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar
