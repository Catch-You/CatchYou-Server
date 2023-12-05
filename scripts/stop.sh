#!/usr/bin/env bash

PROJECT_ROOT="/home/ubuntu/app"
API_JAR="$PROJECT_ROOT/Api/build/libs/Api.jar" 
DOMAIN_JAR="$PROJECT_ROOT/Domain/build/libs/Domain.jar"
INFRASTRUCTURE_JAR="$PROJECT_ROOT/Infrastructure/build/libs/Infrastructure.jar"
CORE_JAR="$PROJECT_ROOT/Core/build/libs/Core.jar"

DEPLOY_LOG="$PROJECT_ROOT/deploy.log"

TIME_NOW=$(date +%c)

# 현재 구동 중인 애플리케이션 pid 확인
CURRENT_API_PID=$(pgrep -f $API_JAR)

# Api 중지
echo "Stopping Api module..."
# 프로세스가 켜져 있으면 종료
if [ -z $CURRENT_API_PID ]; then
  echo "$TIME_NOW > 현재 실행중인 Api 애플리케이션이 없습니다" >> $DEPLOY_LOG
else
  echo "$TIME_NOW > 실행중인 $CURRENT_API_PID 애플리케이션 종료 " >> $DEPLOY_LOG
  kill -15 $CURRENT_API_PID
fi

# 현재 구동 중인 애플리케이션 pid 확인
CURRENT_DOMAIN_PID=$(pgrep -f $DOMAIN_JAR)

# Domain 중지
echo "Stopping Domain module..."
# 프로세스가 켜져 있으면 종료
if [ -z $CURRENT_DOMAIN_PID ]; then
  echo "$TIME_NOW > 현재 실행중인 Domain 애플리케이션이 없습니다" >> $DEPLOY_LOG
else
  echo "$TIME_NOW > 실행중인 $CURRENT_DOMAIN_PID 애플리케이션 종료 " >> $DEPLOY_LOG
  kill -15 $CURRENT_DOMAIN_PID
fi

# 현재 구동 중인 애플리케이션 pid 확인
CURRENT_INFRASTRUCTURE_PID=$(pgrep -f $INFRASTRUCTURE_JAR)

# Infrastructure 중지
echo "Stopping Infrastructure module..."
# 프로세스가 켜져 있으면 종료
if [ -z $CURRENT_INFRASTRUCTURE_PID ]; then
  echo "$TIME_NOW > 현재 실행중인 Infrastructure 애플리케이션이 없습니다" >> $DEPLOY_LOG
else
  echo "$TIME_NOW > 실행중인 $CURRENT_INFRASTRUCTURE_PID 애플리케이션 종료 " >> $DEPLOY_LOG
  kill -15 $CURRENT_INFRASTRUCTURE_PID
fi

# 현재 구동 중인 애플리케이션 pid 확인
CURRENT_CORE_PID=$(pgrep -f $CORE_JAR)

# Core 중지
echo "Stopping Core module..."
# 프로세스가 켜져 있으면 종료
if [ -z $CURRENT_CORE_PID ]; then
  echo "$TIME_NOW > 현재 실행중인 Core 애플리케이션이 없습니다" >> $DEPLOY_LOG
else
  echo "$TIME_NOW > 실행중인 $CURRENT_CORE_PID 애플리케이션 종료 " >> $DEPLOY_LOG
  kill -15 $CURRENT_CORE_PID
fi
