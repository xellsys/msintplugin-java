FROM openjdk:8-jre
COPY msintplugin-kotlin.jar /
COPY entrypoint.sh /
RUN chmod +x /entrypoint.sh
ENV JAVA_DEBUG true
ENTRYPOINT ["/entrypoint.sh"]
EXPOSE 8080 5005