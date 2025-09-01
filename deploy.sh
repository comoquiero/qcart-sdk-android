#!/bin/bash

# mvn clean package
# chmod +x deploy.sh
# sh deploy.sh

# mvn deploy:deploy-file \
#   -Dfile=target/sdk-core-1.0.0.jar \
#   -DgroupId=app.qcart \
#   -DartifactId=deeplink-sdk \
#   -Dversion=1.0.0 \
#   -Dpackaging=jar \
#   -DrepositoryId=ossrh \
#   -Durl=https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/

# https://central.sonatype.org/publish/publish-portal-api/#authentication-authorization

# printf "EfqG67:cxQaQYsRetA3EP4YOAwgfkzmIvRN8G3m5" | base64
# RWZxRzY3OmN4UWFRWXNSZXRBM0VQNFlPQXdnZmt6bUl2Uk44RzNtNQ==

#!/bin/bash

ARTIFACT_ID=sdk-core
VERSION=1.0.0
GROUP_ID=app.qcart
BUNDLE_NAME=central-bundle.zip

TARGET_DIR=target

TMPDIR=$(mktemp -d)
mkdir -p "$TMPDIR/$GROUP_ID/$ARTIFACT_ID/$VERSION"

cp "$TARGET_DIR/$ARTIFACT_ID-$VERSION.jar" "$TMPDIR/$GROUP_ID/$ARTIFACT_ID/$VERSION/"
cp pom.xml "$TMPDIR/$GROUP_ID/$ARTIFACT_ID/$VERSION/"

cd "$TMPDIR"
zip -r "$OLDPWD/$BUNDLE_NAME" "$GROUP_ID"

cd "$OLDPWD"
rm -rf "$TMPDIR"

echo "Created bundle: $BUNDLE_NAME"

curl --request POST \
  --verbose \
  --header 'Authorization: Bearer RWZxRzY3OmN4UWFRWXNSZXRBM0VQNFlPQXdnZmt6bUl2Uk44RzNtNQ==' \
  --form bundle=@central-bundle.zip \
  https://central.sonatype.com/api/v1/publisher/upload