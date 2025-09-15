# ---------- Etapa 1: Build con Maven (JDK 21) ----------
FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /app

# Copiamos TODO el repo (simple y robusto para multimódulo)
COPY . .

# Compilamos todo y generamos el EAR
RUN mvn -B -DskipTests clean package && \
    mkdir -p /out && \
    cp ear/target/*.ear /out/app.ear

# ---------- Etapa 2: WildFly 37 con JDK 21 ----------
FROM quay.io/wildfly/wildfly:37.0.0.Final-jdk21
ENV JBOSS_HOME=/opt/jboss/wildfly

# (Opcional) Limitar memoria en planes chicos
ENV JAVA_OPTS="-Xms128m -Xmx512m -Djava.net.preferIPv4Stack=true"

# Desplegamos el EAR por el scanner
COPY --from=build /out/app.ear ${JBOSS_HOME}/standalone/deployments/app.ear

# Railway expone $PORT; lo usamos como puerto HTTP
EXPOSE 8080
CMD ["sh","-c","/opt/jboss/wildfly/bin/standalone.sh -b 0.0.0.0 -Djboss.http.port=${PORT:-8080}"]
