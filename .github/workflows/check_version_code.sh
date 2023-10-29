#!/bin/bash

# Eğer HEAD referansı yoksa (yani henüz commit yapılmamışsa), scripti çıkış yap
if ! git rev-parse HEAD > /dev/null 2>&1; then
    echo "::set-output name=version-changed::false"
    exit 0
fi

# En son commit ile bir önceki commit arasındaki farkları al
CHANGED_FILES=$(git diff HEAD^ HEAD --name-only)

# Eğer app/build.gradle dosyası değiştirilmişse, versionCode'un değişip değişmediğini kontrol et
if echo "$CHANGED_FILES" | grep -q "app/build.gradle"; then
    PREV_VERSION_CODE=$(git show HEAD^:app/build.gradle | grep versionCode | awk '{print $2}')
    CURR_VERSION_CODE=$(grep versionCode app/build.gradle | awk '{print $2}')

    if [ "$PREV_VERSION_CODE" != "$CURR_VERSION_CODE" ]; then
        echo "::set-output name=version-changed::true"
    else
        echo "::set-output name=version-changed::false"
    fi
else
    echo "::set-output name=version-changed::false"
fi
