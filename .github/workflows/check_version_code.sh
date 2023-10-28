#!/bin/bash
# check_version_code.sh
PREV_VERSION_CODE=$(git show HEAD~1:app/build.gradle | grep versionCode | awk '{print $2}')
CURR_VERSION_CODE=$(grep versionCode app/build.gradle | awk '{print $2}')

if [ "$PREV_VERSION_CODE" != "$CURR_VERSION_CODE" ]; then
  echo "versionCode changed. Proceeding with distribution..."
  exit 0
else
  echo "versionCode did not change. Skipping distribution..."
  exit 1
fi
