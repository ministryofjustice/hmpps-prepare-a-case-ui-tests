@REM ----------------------------------------------------------------------------
@REM Licensed to the Apache Software Foundation (ASF) under one
@REM or more contributor license agreements.  See the NOTICE file
@REM distributed with this work for additional information
@REM regarding copyright ownership.  The ASF licenses this file
@REM to you under the Apache License, Version 2.0 (the
@REM "License"); you may not use this file except in compliance
@REM with the License.  You may obtain a copy of the License at
@REM
@REM    http://www.apache.org/licenses/LICENSE-2.0
@REM
@REM Unless required by applicable law or agreed to in writing,
@REM software distributed under the License is distributed on an
@REM "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
@REM KIND, either express or implied.  See the License for the
@REM specific language governing permissions and limitations
@REM under the License.
@REM ----------------------------------------------------------------------------

@REM ----------------------------------------------------------------------------
@REM Apache Maven Wrapper startup batch script, version 3.3.2
@REM
@REM Optional ENV vars
@REM   JAVA_HOME - location of a JDK home dir, required when download maven via java source
@REM   MVNW_REPOURL - repo url base for downloading maven distribution
@REM   MVNW_USERNAME/MVNW_PASSWORD - user and password for downloading maven
@REM   MVNW_VERBOSE - true: enable verbose log; others: silence the output
@REM ----------------------------------------------------------------------------

@IF "%__MVNW_ARG0_NAME__%"=="" (SET __MVNW_ARG0_NAME__=%~nx0)
@SET __MVNW_CMD__=
@SET __MVNW_ERROR__=
@SET __MVNW_PSMODULEP_SAVE__=%PSModulePath%
@SET PSModulePath=
@FOR /F "usebackq tokens=1* delims==" %%A IN ("%~dp0\.mvn\wrapper\maven-wrapper.properties") DO @(
    IF "%%~A"=="wrapperUrl" SET "__MVNW_CMD__=%%~B"
    IF "%%~A"=="distributionUrl" SET "MVNW_DISTRO_URL=%%~B"
)
@SET "PSModulePath=%__MVNW_PSMODULEP_SAVE__%"
@SET __MVNW_PSMODULEP_SAVE__=
@SET MVNW_DISTRO_NAME=%MVNW_DISTRO_URL:*/=%

@REM Determine MAVEN_HOME
@SET "MAVEN_PROJECTBASEDIR=%~dp0"
@SET "MAVEN_HOME_LOCAL=%MAVEN_PROJECTBASEDIR%\.mvn\wrapper\%MVNW_DISTRO_NAME:-bin.zip=%"

@IF EXIST "%MAVEN_HOME_LOCAL%" GOTO :MvnFound

@REM Download Maven
@IF NOT EXIST "%MAVEN_PROJECTBASEDIR%\.mvn\wrapper" MKDIR "%MAVEN_PROJECTBASEDIR%\.mvn\wrapper"

@REM Use PowerShell to download
powershell -Command "& { [Net.ServicePointManager]::SecurityProtocol = [Net.SecurityProtocolType]::Tls12; Invoke-WebRequest -Uri '%MVNW_DISTRO_URL%' -OutFile '%MAVEN_PROJECTBASEDIR%\.mvn\wrapper\%MVNW_DISTRO_NAME%' }"
@IF %ERRORLEVEL% NEQ 0 (
    @ECHO ERROR: Failed to download Maven distribution from %MVNW_DISTRO_URL%
    @EXIT /B 1
)

@REM Unzip
powershell -Command "& { Expand-Archive -Path '%MAVEN_PROJECTBASEDIR%\.mvn\wrapper\%MVNW_DISTRO_NAME%' -DestinationPath '%MAVEN_PROJECTBASEDIR%\.mvn\wrapper' -Force }"
@IF %ERRORLEVEL% NEQ 0 (
    @ECHO ERROR: Failed to extract Maven distribution.
    @EXIT /B 1
)

@DEL "%MAVEN_PROJECTBASEDIR%\.mvn\wrapper\%MVNW_DISTRO_NAME%"

:MvnFound
@SET "MAVEN_HOME=%MAVEN_HOME_LOCAL%"
"%MAVEN_HOME%\bin\mvn.cmd" %*

