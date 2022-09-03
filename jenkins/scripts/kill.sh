#!/usr/bin/env sh

echo 'The following command terminates the "java -jar target/${NAME}-${VERSION}.jar" process using its PID'
echo '(written to ".pidfile"), all of which were conducted when "deliver.sh"'
echo 'was executed.'
set -x
# echo 'not actually kill, because already finished and the app just console hello world'
kill $(cat .pidfile)