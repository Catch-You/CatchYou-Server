#!/usr/bin/env bash

PROJECT_ROOT="/home/ubuntu/app"

# Core 실행
echo "Starting Core module..."
bash $PROJECT_ROOT/scripts/start-core.sh

# Infrastructure 실행
echo "Starting Infrastructure module..."
bash $PROJECT_ROOT/scripts/start-infrastructure.sh

# Domain 실행
echo "Starting Domain module..."
bash $PROJECT_ROOT/scripts/start-domain.sh

# Api 실행
echo "Starting Api module..."
bash $PROJECT_ROOT/scripts/start-api.sh

