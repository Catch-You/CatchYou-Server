#!/usr/bin/env bash

PROJECT_ROOT="/home/ubuntu/app"
API_JAR="$PROJECT_ROOT/Api/build/libs/Api.jar"

APP_LOG="$PROJECT_ROOT/api.log"
ERROR_LOG="$PROJECT_ROOT/api-error.log"

TIME_NOW=$(date +%c)

# 필요한 jar 파일 복사하여 하나의 jar 파일로 만듦
echo "Copying required jar files to create Api.jar..."
cp $PROJECT_ROOT/Api/build/libs/*.jar $API_JAR


echo "Starting Api module..."
nohup java -jar $API_JAR > $APP_LOG 2> $ERROR_LOG &
