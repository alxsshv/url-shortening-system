#!/usr/bin/env bash
set -e

# Параметры
MASTER_DB="short-link-database"


# Подписываемся на master
psql -U "$POSTGRES_USER" -d "$POSTGRES_DB" -c "DROP SUBSCRIPTION IF EXISTS ${POSTGRES_DB}_subscription;"
psql -U "$POSTGRES_USER" -d "$POSTGRES_DB" -c "CREATE SUBSCRIPTION ${POSTGRES_DB}_subscription CONNECTION 'dbname="$POSTGRES_DB" host=$MASTER_DB user=$POSTGRES_USER password=$POSTGRES_PASSWORD' PUBLICATION links_publication;"

echo "$POSTGRES_DB configured to replicate from $MASTER_DB."
