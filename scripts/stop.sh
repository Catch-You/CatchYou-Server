#!/usr/bin/env bash

# Api 중지
echo "Stopping Api module..."
pkill -f "java -jar /home/ubuntu/app/Api/build/libs/Api.jar"

# Domain 중지
echo "Stopping Domain module..."
pkill -f "java -jar /home/ubuntu/app/Domain/build/libs/Domain.jar"

# Infrastructure 중지
echo "Stopping Infrastructure module..."
pkill -f "java -jar /home/ubuntu/app/Infrastructure/build/libs/Infrastructure.jar"

# Core 중지
echo "Stopping Core module..."
pkill -f "java -jar /home/ubuntu/app/Core/build/libs/Core.jar"
