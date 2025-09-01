# MAVEN CENTRAL

## CHECK IF GPG EXISTS
gpg --list-secret-keys

## OR GENERATE GPG KEY
gpg --full-generate-key

## CHECK THE KEY EXISTS (USED 06A92E9C4AE27832A938F759B69D72D95FB4382F)
https://keyserver.ubuntu.com/

## OR UPLOAD THE KEY (USED 06A92E9C4AE27832A938F759B69D72D95FB4382F)
gpg --keyserver hkps://keyserver.ubuntu.com --send-keys 06A92E9C4AE27832A938F759B69D72D95FB4382F

---

# UPDATE

## BUILD
./gradlew clean build publishToMavenLocal
cd ~/.m2/repository/app/qcart/deeplink-sdk/1.0.0/
zip -r deeplink-sdk-1.0.0-bundle.zip .

## DEPLOY MANUALLY
https://central.sonatype.com/publishing
xdg-open ~/.m2/repository/app/qcart/deeplink-sdk/1.0.0
Publish Component -> deeplink-sdk-1.0.0-bundle.zip


## DEPLOY
<!-- mvn clean deploy -->
./gradlew clean build publishToMavenLocal
./gradlew clean assembleRelease publishToMavenLocal

sh bundle.sh


curl -u "DaGsgm:0dpUq0jyX2lMyyyIVh34Zq9ReZ0ih8TOE" \
  -X POST "https://central.sonatype.com/api/v1/publisher/deploy" \
  -F "artifact=@build/outputs/aar/qcart-sdk-release.aar" \
  -F "pom=@build/publications/release/pom-default.xml" \
  -F "signature=@qcart-sdk-release.aar.asc" \
  -F "pomSignature=@pom-default.xml.asc"
  