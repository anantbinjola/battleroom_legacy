set classpath=%CLASSPATH%;%CD%/res/
cd src
javac -d . *.java
java com.payne.game.window.Game