#!/bin/bash

base_dir="$(pwd)"
json_dir="$base_dir/create-committee-member"
image_dir="$base_dir/images/committee-member-face-image"

shared_image="$image_dir/member-face-image-1.webp"

for i in {1..15}; do
  json_path="$json_dir/member-${i}.json"

  echo "Uploading: member-${i}.json"
  echo "JSON:  $json_path"
  echo "Image: $shared_image"

  if [[ ! -f "$json_path" ]]; then
    echo "❌ JSON file not found: $json_path"
    continue
  fi

  if [[ ! -f "$shared_image" ]]; then
    echo "❌ Shared face image not found: $shared_image"
    break
  fi

  curl -X POST http://localhost:8080/api/v1/committee-member \
    --form "request=@$json_path;type=application/json" \
    --form "memberFaceImage=@$shared_image" \
    --silent --show-error --fail || echo "❌ Failed: member-${i}.json"

  echo "----------------------------------------"
done
