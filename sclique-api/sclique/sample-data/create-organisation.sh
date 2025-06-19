#!/bin/bash

base_dir="$(pwd)"
json_dir="$base_dir/create-organisation"
profile_image_dir="$base_dir/images/organisation-profile-image"
cover_image_dir="$base_dir/images/organisation-cover-image"

cover_image_filename="organisation-1"
cover_path="$(find "$cover_image_dir" -maxdepth 1 -type f -iname "${cover_image_filename}.*" | head -n 1)"

if [[ -z "$cover_path" ]]; then
  echo "❌ Cover image not found with prefix: $cover_image_filename"
  exit 1
fi

for i in {1..12}; do
  json_path="$json_dir/organisation-${i}.json"
  profile_path="$(find "$profile_image_dir" -maxdepth 1 -type f -iname "organisation-image-${i}.*" | head -n 1)"

  echo "Uploading: organisation-${i}.json"
  echo "JSON:    $json_path"
  echo "Profile: $profile_path"
  echo "Cover:   $cover_path"

  if [[ ! -f "$json_path" ]]; then
    echo "❌ JSON file not found: $json_path"
    continue
  fi

  if [[ -z "$profile_path" ]]; then
    echo "❌ Profile image not found for profile-${i}.*"
    continue
  fi

  curl -X POST http://localhost:8080/api/v1/organisation \
    --form "request=@$json_path;type=application/json" \
    --form "organisationProfileImage=@$profile_path" \
    --form "organisationCoverImage=@$cover_path" \
    --silent --show-error --fail || echo "❌ Failed: organisation-${i}.json"

  echo "----------------------------------------"
done
