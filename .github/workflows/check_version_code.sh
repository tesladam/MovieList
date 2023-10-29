#!/bin/bash
# check_version_code.sh

# Son committe değişiklik yapılan dosyaları elde edin
CHANGED_FILES=$(git diff HEAD~1 --name-only)

# Eğer app/build.gradle dosyası değiştirilmişse, versionCode'un değişip değişmediğini kontrol edin
if echo "$CHANGED_FILES" | grep -q "app/build.gradle"; then
  PREV_VERSION_CODE=$(git show HEAD~1:app/build.gradle | grep versionCode | awk '{print $2}')
  CURR_VERSION_CODE=$(grep versionCode app/build.gradle | awk '{print $2}')

  if [ "$PREV_VERSION_CODE" != "$CURR_VERSION_CODE" ]; then
    echo "versionCode changed. Proceeding with distribution..."
    exit 0
  else
    echo "versionCode did not change. Skipping distribution..."
    exit 1
  fi
else
  echo "app/build.gradle did not change. Skipping distribution..."
  exit 1
fi
