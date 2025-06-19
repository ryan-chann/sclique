#!/bin/bash

base_dir="$(pwd)"
json_dir="$base_dir/join-organisation"

for i in {16..30}; do
  json_path="$json_dir/join-${i}.json"

  echo "Joining organisation with: join-${i}.json"
  echo "JSON: $json_path"

  if [[ ! -f "$json_path" ]]; then
    echo "❌ JSON file not found: $json_path"
    continue
  fi

  # Run the request and capture both response and HTTP status
  http_status=$(curl --write-out "%{http_code}" \
    --silent \
    --output /dev/null \
    --location 'http://localhost:8080/api/v1/committee-member/join/organisation' \
    --header 'Content-Type: application/json' \
    --data @"$json_path")

  if [[ "$http_status" -eq 200 || "$http_status" -eq 201 ]]; then
    echo "✅ Success: join-${i}.json"
  else
    echo "❌ Failed: join-${i}.json (HTTP $http_status)"
    echo "⏹️ Halting script due to failure."
    exit 1
  fi

  echo "----------------------------------------"
done

echo "🎉 All requests completed successfully."
