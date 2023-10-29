#!/bin/bash
# check_version_code.sh

# Eğer HEAD~1 geçersizse, script false döndürerek işlemlerin geri kalanını atlar
PREV_VERSION_CODE=$(git show HEAD~1:app/build.gradle | grep versionCode | awk '{print $2}' || echo "")
CURR_VERSION_CODE=$(grep versionCode app/build.gradle | awk '{print $2}')

if [ "$PREV_VERSION_CODE" != "$CURR_VERSION_CODE" ] && [ ! -z "$PREV_VERSION_CODE" ]; then
  echo "true"
else
  echo "false"
fi
