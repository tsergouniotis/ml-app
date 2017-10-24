FROM java:8

MAINTAINER Thanassis Sergouniotis "tsergouniotis@gmail.com"

##-qq suppress output that we dont need in dockerfile
RUN apt-get -qq update

RUN apt-get -qq -y install vim

RUN apt-get -qq -y install supervisor

RUN wget http://download.jboss.org/wildfly/11.0.0.Final/wildfly-11.0.0.Final.tar.gz
RUN cp wildfly-11.0.0.Final.tar.gz /usr/local

RUN wget https://jdbc.postgresql.org/download/postgresql-42.0.0.jar
RUN cp postgresql-42.0.0.jar /opt

RUN cd /usr/local\
	&& tar xvfz wildfly-11.0.0.Final.tar.gz 

ENV JBOSS_HOME /usr/local/wildfly-11.0.0.Final
ENV LAUNCH_JBOSS_IN_BACKGROUND false

RUN mkdir /var/log/supervisord
RUN mkdir /var/log/ml

ADD target/ml-app-core.war /opt/ml-app-core.war

#COPY target/dashboard-module.tar.gz $JBOSS_HOME/dashboard-module.tar.gz
#RUN cd $JBOSS_HOME	&& tar xvfz dashboard-module.tar.gz 


ADD src/main/resources/run.sh /run.sh
ADD src/main/resources/conf.cli /conf.cli
ADD src/main/resources/supervisord.conf /etc/supervisor/conf.d/supervisord.conf
ADD src/main/resources/processlistener.py /processlistener.py

EXPOSE 8080
EXPOSE 8787
EXPOSE 9000
EXPOSE 9999
EXPOSE 9990

ENTRYPOINT ["/bin/bash", "run.sh" ]