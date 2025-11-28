@echo off
echo Creating JAR with dependencies...

if exist SimPL.jar del SimPL.jar
if exist temp rmdir /s /q temp
mkdir temp

REM Copy all class files from bin
echo Copying class files...
xcopy /E /I /Q bin temp

REM Extract java-cup-runtime.jar
echo Extracting java-cup-runtime...
cd temp
jar xf ..\lib\java-cup-11a-runtime.jar
if exist META-INF rmdir /s /q META-INF
cd ..

REM Create final JAR
echo Creating JAR...
cd temp
jar cfe ..\SimPL.jar simpl.interpreter.Interpreter .
cd ..

REM Clean up
rmdir /s /q temp

echo.
echo JAR created successfully!
echo.
echo Testing...
java -jar SimPL.jar doc\examples\plus.spl

pause