#!/bin/bash
# check_version_code.sh

# Check if this is the first commit by checking the number of commits
COMMIT_COUNT=$(git rev-list --count HEAD)

if [ "$COMMIT_COUNT" -eq 1 ]; then
  echo "::set-env name=version-changed::false"
  echo "First commit, skipping distribution..."
  exit 0
fi

# Compare the versionCode between the last commit and the current commit
PREV_VERSION_CODE=$(git show HEAD^:app/build.gradle | grep versionCode | awk '{print $2}')
CURR_VERSION_CODE=$(grep versionCode app/build.gradle | awk '{print $2}')

if [ "$PREV_VERSION_CODE" != "$CURR_VERSION_CODE" ]; then
  echo "::set-env name=version-changed::true"
  echo "versionCode changed. Proceeding with distribution..."
else
  echo "::set-env name=version-changed::false"
  echo "versionCode did not change. Skipping distribution..."
fi
