#!/bin/bash

# Son commit'te versionCode değiştiyse 1, değişmediyse 0 döner
git log -n 1 --pretty=format:"" --name-only | grep -q "app/build.gradle" && \
grep -q "versionCode" app/build.gradle && echo "1" || echo "0"
