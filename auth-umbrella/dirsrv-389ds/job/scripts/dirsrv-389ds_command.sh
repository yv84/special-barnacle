#!/usr/bin/env bash

set -e

echo "Processing 389ds task..."
ldapadd -H ldap://${LDAP_HOST}:${LDAP_PORT} -v -c -x -D"$ROOT_DN" -w${DIRSRV_MANAGER_PASSWORD} -f /opt/dirsrv-389ds/changelog/add.ldif || EXIT_CODE=$? && true ;
ldapmodify -H ldap://${LDAP_HOST}:${LDAP_PORT} -v -c -x -D"$ROOT_DN" -w${DIRSRV_MANAGER_PASSWORD} -f /opt/dirsrv-389ds/changelog/modify.ldif || EXIT_CODE=$? && true ;
 
echo "finish = $EXIT_CODE"

if [[ "$EXIT_CODE" == 0 ]] ;
then
    echo finish
elif [[ "$EXIT_CODE" == 68 ]] ;
then
    echo finish
else
    error_exit "$EXIT_CODE"
fi

