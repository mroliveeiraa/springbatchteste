# springbatchteste
spring batch



src/main/java/com/example/batch/
│── config/           # Configuração do Batch
│── model/            # Modelos das entidades
│── reader/           # ItemReader
│── writer/           # ItemWriter (banco + Kafka)
│── repository/       # Repositório JPA (opcional)
│── Application.java  # Classe principal






@echo off
SET DEPLOYMENT=%1
SET NAMESPACE=%2

IF "%DEPLOYMENT%"=="" (
    echo Uso: %0 deployment namespace
    exit /b 1
)

IF "%NAMESPACE%"=="" (
    echo Uso: %0 deployment namespace
    exit /b 1
)

echo 🔄 Verificando rollout do deployment "%DEPLOYMENT%" no namespace "%NAMESPACE%"...

REM 1. Checa rollout
kubectl rollout status deployment %DEPLOYMENT% -n %NAMESPACE%
IF ERRORLEVEL 1 (
    echo ❌ Rollout falhou ou nao completou.
    exit /b 1
)

echo ✅ Rollout completo!

REM 2. Lista pods e status
echo.
echo 📋 Pods em execucao:
kubectl get pods -n %NAMESPACE% -l app=%DEPLOYMENT% -o wide

REM 3. Mostra imagem em uso
echo.
echo 📦 Imagens em execucao:
kubectl get pods -n %NAMESPACE% -l app=%DEPLOYMENT% ^
  -o=jsonpath="{range .items[*]}{.metadata.name}{' -> '}{.spec.containers[*].image}{'\n'}{end}"
