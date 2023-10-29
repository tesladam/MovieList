#!/bin/bash
# check_version_code.sh

PREV_VERSION_CODE=$(git log -n 1 --pretty=format:%h --skip=1 -- app/build.gradle | xargs -I {} git show {}:app/build.gradle | grep versionCode | awk '{print $2}')
CURR_VERSION_CODE=$(grep versionCode app/build.gradle | awk '{print $2}')

if [ "$PREV_VERSION_CODE" != "$CURR_VERSION_CODE" ]; then
  echo "version-changed=true" >> $GITHUB_ENV
else
  echo "version-changed=false" >> $GITHUB_ENV
fi
