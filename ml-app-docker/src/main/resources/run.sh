#!/bin/bash
      

function wait_db() {
 sleep 10
}
  

#echo "=> Waiting for the database to boot and database to be setup"
wait_db

#exec ${JBOSS_HOME}/bin/standalone.sh -b 0.0.0.0

exec /usr/bin/supervisord -c /etc/supervisor/conf.d/supervisord.conf
