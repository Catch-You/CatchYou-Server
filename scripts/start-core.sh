#!/usr/bin/env bash

PROJECT_ROOT="/home/ubuntu/app"
CORE_JAR="$PROJECT_ROOT/Core/build/libs/Core.jar"

APP_LOG="$PROJECT_ROOT/core.log"
ERROR_LOG="$PROJECT_ROOT/core-error.log"

TIME_NOW=$(date +%c)

# 필요한 jar 파일 복사하여 하나의 jar 파일로 만듦
echo "Copying required jar files to create Core.jar..."
cp $PROJECT_ROOT/Core/build/libs/*.jar $CORE_JAR


echo "Starting Core module..."
nohup java -jar $CORE_JAR > $APP_LOG 2> $ERROR_LOG &
