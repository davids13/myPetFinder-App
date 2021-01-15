FROM payara/micro:jdk11
LABEL key="https://github.com/davids13"
USER root
RUN mkdir ${PAYARA_HOME}/config
COPY /ope/payara/domain.xml ${PAYARA_HOME}/config
# Set the Payara user and working directory owned by the new user
RUN chown -R payara:payara ${PAYARA_HOME}/config
USER payara
WORKDIR ${PAYARA_HOME}
COPY /target/myPetFinderApp.war $DEPLOY_DIR
ENTRYPOINT ["java", "-jar", "payara-micro.jar", "--deploy", "/opt/payara/deployments/myPetFinderApp.war"]
EXPOSE 8080
CMD ["--deploymentDir", "/opt/payara/deployments", "--rootDir", "/opt/payara/config", "--domainConfig", "/opt/payara/config/domain.xml"]