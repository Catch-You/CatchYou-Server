#!/usr/bin/env bash

PROJECT_ROOT="/home/ubuntu/app"
INFRASTRUCTURE_JAR="$PROJECT_ROOT/Infrastructure/build/libs/Infrastructure.jar"

APP_LOG="$PROJECT_ROOT/infrastructure.log"
ERROR_LOG="$PROJECT_ROOT/infrastructure-error.log"

TIME_NOW=$(date +%c)

# 필요한 jar 파일 복사하여 하나의 jar 파일로 만듦
echo "Copying required jar files to create Infrastructure.jar..."
cp $PROJECT_ROOT/Infrastructure/build/libs/*.jar $INFRASTRUCTURE_JAR


echo "Starting Infrastructure module..."
nohup java -jar $INFRASTRUCTURE_JAR > $APP_LOG 2> $ERROR_LOG &
