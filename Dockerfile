# ---- Etapa 1: build con Maven + JDK 21 ----
FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /app

# cache de dependencias
COPY pom.xml .
COPY api/pom.xml api/pom.xml
COPY ejb/pom.xml ejb/pom.xml
COPY web/pom.xml web/pom.xml
COPY ear/pom.xml ear/pom.xml
RUN mvn -B -DskipTests dependency:go-offline

# compilar
COPY . .
RUN mvn -B -DskipTests clean package

# normalizar nombre del EAR a /out/app.ear
RUN set -eux; \
    ear=$(ls ear/target/*.ear | head -n1); \
    mkdir -p /out; \
    cp "$ear" /out/app.ear

# ---- Etapa 2: WildFly 37 con JDK 21 ----
FROM quay.io/wildfly/wildfly:37.0.0.Final-jdk21
ENV JBOSS_HOME=/opt/jboss/wildfly

# memoria más contenida para planes free
ENV JAVA_OPTS="-Xms128m -Xmx512m -Djava.net.preferIPv4Stack=true"

# desplegar EAR por scanner
COPY --from=build /out/app.ear ${JBOSS_HOME}/standalone/deployments/app.ear

# Railway expone $PORT, lo usamos como puerto HTTP
EXPOSE 8080
CMD ["sh","-c", "/opt/jboss/wildfly/bin/standalone.sh -b 0.0.0.0 -Djboss.http.port=${PORT:-8080}"]
