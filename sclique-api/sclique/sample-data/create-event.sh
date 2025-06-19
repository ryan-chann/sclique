#!/bin/bash

base_dir="$(pwd)"

json_dir="$base_dir/create-event"
image_dir="$base_dir/images/event-advertisement-image"

json_files=()
for i in {1..5}; do
  json_files+=("event-${i}.json")
done

image_files=()
for i in {1..5}; do
  image_file=$(find "$image_dir" -type f -name "event-image-${i}.*" | head -n 1)
  image_files+=("$image_file")
done

for i in "${!json_files[@]}"; do
  json_path="$json_dir/${json_files[$i]}"
  image_path="${image_files[$i]}"

  echo "Uploading: $json_path with $image_path"

  if [[ ! -f "$json_path" ]]; then
    echo "❌ JSON not found: $json_path"
    continue
  fi

  if [[ ! -f "$image_path" ]]; then
    echo "❌ Image not found for index $i"
    continue
  fi

  mime_type=$(file --brief --mime-type "$image_path")

  http_status=$(curl --write-out "%{http_code}" \
    --silent \
    --output /dev/null \
    -X POST http://localhost:8080/api/v1/event \
    --form "request=@$json_path;type=application/json" \
    --form "eventAdvertisementImage=@$image_path;type=$mime_type")

  if [[ "$http_status" -eq 200 || "$http_status" -eq 201 ]]; then
    echo "✅ Success: ${json_files[$i]}"
  else
    echo "❌ Failed: ${json_files[$i]} (HTTP $http_status)"
    exit 1
  fi

  echo "----------------------------------------"
done

echo "🎉 All uploads completed successfully."
