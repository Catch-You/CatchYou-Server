#!/usr/bin/env bash

PROJECT_ROOT="/home/ubuntu/app"
DOMAIN_JAR="$PROJECT_ROOT/Domain/build/libs/Domain.jar"

APP_LOG="$PROJECT_ROOT/domain.log"
ERROR_LOG="$PROJECT_ROOT/domain-error.log"

TIME_NOW=$(date +%c)

# 필요한 jar 파일 복사하여 하나의 jar 파일로 만듦
echo "Copying required jar files to create Domain.jar..."
cp $PROJECT_ROOT/Domain/build/libs/*.jar $DOMAIN_JAR


echo "Starting Domain module..."
nohup java -jar $DOMAIN_JAR > $APP_LOG 2> $ERROR_LOG &
