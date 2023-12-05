#!/usr/bin/env bash

# Core 실행
echo "Starting Core module..."
bash scripts/start-core.sh

# Infrastructure 실행
echo "Starting Infrastructure module..."
bash scripts/start-infrastructure.sh

# Domain 실행
echo "Starting Domain module..."
bash scripts/start-domain.sh

# Api 실행
echo "Starting Api module..."
bash scripts/start-api.sh

