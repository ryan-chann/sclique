#!/bin/bash

# Base directories
base_dir="$(pwd)"

json_dir="$base_dir/create-event"
image_dir="$base_dir/images/event-advertisement-image"

json_files=()
for i in {1..5}; do
  json_files+=("event-${i}.json")
done

# Corresponding image filenames
image_files=()
for i in {1..5}; do
  image_files+=("event-image-${i}.jpg")
done

# Loop through all items
for i in "${!json_files[@]}"; do
  json_path="$json_dir/${json_files[$i]}"
  image_path="$image_dir/${image_files[$i]}"

  echo "Uploading: $json_path with $image_path"

  curl -X POST http://localhost:8080/api/v1/event \
    --form "request=@$json_path;type=application/json" \
    --form "eventAdvertisementImage=@$image_path" \
    --silent --show-error --fail || echo "❌ Failed: ${json_files[$i]}"

  echo "----------------------------------------"
done
