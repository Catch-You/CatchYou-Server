#!/usr/bin/env bash

PROJECT_ROOT="/home/ubuntu/app"
CATCHYOU_JAR="$PROJECT_ROOT/catchyou.jar"

APP_LOG="$PROJECT_ROOT/application.log"
ERROR_LOG="$PROJECT_ROOT/error.log"
DEPLOY_LOG="$PROJECT_ROOT/deploy.log"

TIME_NOW=$(date +%c)

# Core 모듈 빌드
echo "Building Core module..."
cd $PROJECT_ROOT/Core
./gradlew clean build -x test

# Infrastructure 모듈 빌드
echo "Building Infrastructure module..."
cd $PROJECT_ROOT/Infrastructure
./gradlew clean build -x test

# Domain 모듈 빌드
echo "Building Domain module..."
cd $PROJECT_ROOT/Domain
./gradlew clean build -x test

# Api 모듈 빌드
echo "Building Api module..."
cd $PROJECT_ROOT/Api
./gradlew clean build -x test

# 프로젝트 루트로 이동
cd $PROJECT_ROOT

# 필요한 jar 파일 복사하여 하나의 jar 파일로 만듦
echo "Copying required jar files to create catchyou.jar..."
cp $PROJECT_ROOT/Core/build/libs/core.jar $CATCHYOU_JAR
cp $PROJECT_ROOT/Infrastructure/build/libs/infrastructure.jar $CATCHYOU_JAR
cp $PROJECT_ROOT/Domain/build/libs/domain.jar $CATCHYOU_JAR
cp $PROJECT_ROOT/Api/build/libs/api.jar $CATCHYOU_JAR

# 실행

echo "$TIME_NOW > $CATCHYOU_JAR 실행" >> $DEPLOY_LOG
nohup java -jar $CATCHYOU_JAR > $APP_LOG 2> $ERROR_LOG &


CURRENT_PID=$(pgrep -f $JAR_FILE)
echo "$TIME_NOW > 실행된 프로세스 아이디 $CURRENT_PID 입니다." >> $DEPLOY_LOG
