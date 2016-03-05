#!/bin/sh

echo "post-merge hooks is running:"
#1
production_branch='master'
MANIFEST="build.gradle"
versionName=0
versionCode=0

current_branch=$(git symbolic-ref HEAD | sed -e 's,.*/\(.*\),\1,')

#When you run git pull, git actually does a fetch followed by a merge.
#This means you can use the post-merge hook to execute a script when a pull is completed.
# 1- Check if the master was updated
# 2- Increment Android Build version code (auto)
# 3 - Increment Android version code (ask user to enter new version name)
# 4- add and commit Android manifest or app.gradle
# 5- push force to the master


#2
function updateVersionCode {

if [ -f $MANIFEST ]
then
LINE=$(grep -e 'versionCode [0-9]*' ${MANIFEST});
LINE=(`echo $LINE | tr "\"" " "`);
VERSION=(${LINE[1]});
INCREMENTED=$(($VERSION+1))
cat $MANIFEST | sed -e "s/versionCode ${VERSION}/versionCode ${INCREMENTED}/" > $MANIFEST.tmp && mv $MANIFEST.tmp $MANIFEST

echo "Updated Build Number To ${INCREMENTED} In ${MANIFEST}!";
versionCode=INCREMENTED
else
echo "File ${MANIFEST} not found!"
fi
}
#3
function updateVersionName {

if [ -f $MANIFEST ]
then
LINE=$(grep -e "versionName \".*\"" ${MANIFEST});
LINE=`echo $LINE | sed 's/[^"]*"\([^"]*\)".*/\1/'`
VERSION=(${LINE[0]});

echo "Current VersionName ${VERSION} In ${MANIFEST}!";
echo "Please updated version Name and press [ENTER]:"
read versionName

while [[ ! $versionName =~ ^[0-9]+(\.(\d)+)? ]]; do
echo "invalid Version Name entry! again:"
read versionName
done

cat $MANIFEST | sed -e "s/versionName \"${VERSION}\"/versionName \"${versionName}\"/" > $MANIFEST.tmp && mv $MANIFEST.tmp $MANIFEST
echo "Version Name update to ${versionName} In ${MANIFEST}!";
else
echo "File ${MANIFEST} not found!"
fi

}

if [[ $current_branch=$protected_branch ]]; then
echo "Continuous Integration: try ++ version name and ++ build version"
#    updateVersionCode
    updateVersionName
#	git add $MANIFEST
#	git commit -m "Bump $versionName"
#	git tag $versionName -m "Build version $versionCode"
#	git push --force
#	git push --tags
fi
exit 0