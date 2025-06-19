#!/bin/bash

base_dir="$(pwd)"
json_dir="$base_dir/join-organisation"

for i in {1..30}; do
  json_path="$json_dir/join-${i}.json"

  echo "Joining organisation with: join-${i}.json"
  echo "JSON: $json_path"

  if [[ ! -f "$json_path" ]]; then
    echo "❌ JSON file not found: $json_path"
    continue
  fi

  curl --location 'http://localhost:8080/api/v1/committee-member/join/organisation' \
    --header 'Content-Type: application/json' \
    --data @"$json_path" \
    --silent --show-error --fail || echo "❌ Failed: join-${i}.json"

  echo "----------------------------------------"
done
