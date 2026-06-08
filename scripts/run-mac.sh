#!/usr/bin/env bash
set -euo pipefail

if ! command -v java >/dev/null 2>&1; then
  echo "Java nao encontrado. Instale o JDK 17 antes de continuar."
  exit 1
fi

if ! command -v mvn >/dev/null 2>&1; then
  echo "Maven nao encontrado. Instale o Maven antes de continuar."
  exit 1
fi

JAVA_VERSION="$(java -version 2>&1 | head -n 1)"
if [[ "$JAVA_VERSION" != *"17."* ]]; then
  echo "Este projeto usa Java 17. Versao atual: $JAVA_VERSION"
  exit 1
fi

if [[ -f ".env" ]]; then
  set -a
  # shellcheck disable=SC1091
  source .env
  set +a
else
  echo "Arquivo .env nao encontrado. Usando valores padrao do application.properties."
  echo "Para Supabase, copie .env.example para .env e preencha as credenciais."
fi

mvn spring-boot:run
