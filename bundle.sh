#!/bin/bash

# Variables
GROUP_ID="app/qcart"
ARTIFACT_ID="deeplink-sdk"
VERSION="1.0.0"
M2_REPO="$HOME/.m2/repository/$GROUP_ID/$ARTIFACT_ID/$VERSION"
STAGING_DIR="./central-staging"

# Clean previous staging folder
rm -rf "$STAGING_DIR"
mkdir -p "$STAGING_DIR/$GROUP_ID/$ARTIFACT_ID/$VERSION"

# Copy all artifacts except _remote.repositories
rsync -av --exclude='_remote.repositories' "$M2_REPO/" "$STAGING_DIR/$GROUP_ID/$ARTIFACT_ID/$VERSION/"

# Create ZIP
cd "$STAGING_DIR"
zip -r "../$ARTIFACT_ID-$VERSION-central.zip" "$GROUP_ID"

echo "ZIP created: $PWD/../$ARTIFACT_ID-$VERSION-central.zip"