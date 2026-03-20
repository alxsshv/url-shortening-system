#!/usr/bin/env bash
set -e

# Configure the master database
echo "Конфигурируем базу данных $POSTGRES_DB как мастер..."
psql --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" -c "DROP PUBLICATION IF EXISTS links_publication;"
psql --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" -c "CREATE PUBLICATION links_publication FOR TABLE links.links;"

