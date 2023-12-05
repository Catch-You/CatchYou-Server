#!/usr/bin/env bash

# Core 실행
echo "Starting Core module..."
bash start-core.sh

# Infrastructure 실행
echo "Starting Infrastructure module..."
bash start-infrastructure.sh

# Domain 실행
echo "Starting Domain module..."
bash start-domain.sh

# Api 실행
echo "Starting Api module..."
bash start-api.sh

