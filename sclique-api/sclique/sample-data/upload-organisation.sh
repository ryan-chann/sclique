#!/bin/bash

# Base directories
base_dir="$(pwd)"

json_dir="$base_dir/create-organisation"
profile_image_dir="$base_dir/images/organisation-profile-picture"
cover_image_dir="$base_dir/images/organisation-cover-image"

# Define filenames
json_files=()
profile_images=()
cover_images=()

for i in {1..5}; do
  json_files+=("organisation-${i}.json")
  profile_images+=("profile-${i}.jpg")
  cover_images+=("cover-${i}.jpg")
done

for i in "${!json_files[@]}"; do
  json_path="$json_dir/${json_files[$i]}"
  profile_path="$profile_image_dir/$organisation-image-[$i]}"
  cover_path="$cover_image_dir/${cover_images[$i]}"

  echo "Uploading: ${json_files[$i]}"

  curl -X POST http://localhost:8080/api/v1/organisation \
    --form "request=@$json_path;type=application/json" \
    --form "organisationProfileImage=@$profile_path" \
    --form "organisationCoverImage=@$cover_path" \
    --silent --show-error --fail || echo "❌ Failed: ${json_files[$i]}"

  echo "----------------------------------------"
done
