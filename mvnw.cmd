@echo off
rem Maven wrapper script for Windows

setlocal

set MAVEN_HOME=%~dp0\.mvn\wrapper\maven-wrapper.jar
set MAVEN_OPTS=-Xmx1024m

if not exist "%MAVEN_HOME%" (
    echo Maven wrapper not found. Please run mvnw to download Maven.
    exit /b 1
)

java %MAVEN_OPTS% -jar "%MAVEN_HOME%" %*
exit /b %ERRORLEVEL%