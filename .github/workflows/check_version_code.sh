#!/bin/bash
# check_version_code.sh

# Commit sayısını elde edin
COMMIT_COUNT=$(git rev-list --count HEAD)

# Eğer commit sayısı 1'den büyükse, HEAD~1 referansını kullanabilirsiniz
if [ "$COMMIT_COUNT" -gt 1 ]; then
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
else
  echo "Not enough commit history. Skipping distribution..."
  exit 1
fi
