#!/bin/bash
# check_version_code.sh

# Check if this is the first commit
if [ $(git rev-list --count HEAD) -eq 1 ]; then
  echo "First commit, proceeding with distribution..."
  echo 'true' > $GITHUB_ENV
  exit 0
fi

# Get a list of files changed between the current commit and the previous commit
CHANGED_FILES=$(git diff HEAD~1 --name-only)

# Check if app/build.gradle is in the list of changed files
if echo "$CHANGED_FILES" | grep -q "app/build.gradle"; then
  # app/build.gradle has changed, now check if versionCode has changed
  VERSION_CODE_CHANGED=$(git diff HEAD~1 -- app/build.gradle | grep versionCode)
  if [ -n "$VERSION_CODE_CHANGED" ]; then
    echo 'true' > $GITHUB_ENV
    exit 0
  fi
fi

# Either app/build.gradle was not changed, or versionCode was not changed
echo 'false' > $GITHUB_ENV
exit 0
