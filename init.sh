#!/usr/bin/env bash

set -e

JVM_OPS="${JVM_OPS:-""}"
APPLICATION_PORT="${PORT:-"8080"}"

COMMAND=${1:-"web"}
echo $COMMAND

echo "Application port:  $APPLICATION_PORT"

case "$COMMAND" in
  migrate|web)
    export SPRING_DATASOURCE_URL="${JDBC_DATABASE_URL}"

    exec java ${JVM_OPS} -Djava.security.egd=file:/dev/./urandom \
      -Duser.Timezone=America/Sao_Paulo \
      -Dserver.port=$APPLICATION_PORT \
      -jar /app/case-*.jar \
      $COMMAND
    ;;
  *)
    exec sh -c "$*"
    ;;
esac
