FROM tomcat:9.0.20-jre8
COPY /target/HealthOneTest2-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/healthone.war
CMD ["catalina.sh","run"]