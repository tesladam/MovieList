#!/bin/bash
# check_version_code.sh

# Get a list of files changed between the current commit and the previous commit
CHANGED_FILES=$(git diff HEAD^ HEAD --name-only)

# Check if app/build.gradle is in the list of changed files
if echo "$CHANGED_FILES" | grep -q "app/build.gradle"; then
    # app/build.gradle has changed, now check if versionCode has changed
    VERSION_CODE_CHANGED=$(git diff HEAD^ HEAD -- app/build.gradle | grep versionCode)
    if [ -n "$VERSION_CODE_CHANGED" ]; then
        echo "::set-output name=version-changed::true"
        exit 0
    fi
fi

# Either app/build.gradle was not changed, or versionCode was not changed
echo "::set-output name=version-changed::false"
exit 0
