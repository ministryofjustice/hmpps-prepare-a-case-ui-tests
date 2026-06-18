#!/bin/sh
# ----------------------------------------------------------------------------
# Licensed to the Apache Software Foundation (ASF) under one
# or more contributor license agreements.  See the NOTICE file
# distributed with this work for additional information
# regarding copyright ownership.  The ASF licenses this file
# to you under the Apache License, Version 2.0 (the
# "License"); you may not use this file except in compliance
# with the License.  You may obtain a copy of the License at
#
#    http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing,
# software distributed under the License is distributed on an
# "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
# KIND, either express or implied.  See the License for the
# specific language governing permissions and limitations
# under the License.
# ----------------------------------------------------------------------------

# ----------------------------------------------------------------------------
# Apache Maven Wrapper startup batch script, version 3.3.2
#
# Optional ENV vars
# -----------------
#   JAVA_HOME - location of a JDK home dir, required when download maven via java source
#   MVNW_REPOURL - repo url base for downloading maven distribution
#   MVNW_USERNAME/MVNW_PASSWORD - user and password for downloading maven
#   MVNW_VERBOSE - true: enable verbose log; others: silence the output
# ----------------------------------------------------------------------------

set -euf
[ "${MVNW_VERBOSE-}" != true ] || set -x

# OS specific support.
native_path() { printf %s\\n "$1"; }
case "$(uname)" in
CYGWIN* | MINGW*)
  [ -z "${JAVA_HOME-}" ] || JAVA_HOME="$(cygpath --unix "$JAVA_HOME")"
  native_path() { cygpath --path --windows "$1"; }
  ;;
esac

# set JAVA_CMD
# https://github.com/apache/maven-mvnd/blob/master/daemon/src/main/java/org/mvndaemon/mvnd/launcher/ShellHolder.java#L32
if [ -n "${JAVA_HOME-}" ]; then
  if [ -x "$JAVA_HOME/jre/sh/java" ]; then
    # IBM's JDK on AIX uses strange locations for the executables
    JAVA_CMD="$JAVA_HOME/jre/sh/java"
  else
    JAVA_CMD="$JAVA_HOME/bin/java"

    if [ ! -x "$JAVA_CMD" ]; then
      die "ERROR: JAVA_HOME is set to an invalid directory: $JAVA_HOME

Please set the JAVA_HOME variable in your environment to match the
location of your Java installation."
    fi
  fi
else
  JAVA_CMD="$(
    'set' +e
    'unset' -f command 2>/dev/null
    'command' -v java
  )" || :
  if [ ! -x "${JAVA_CMD-}" ]; then
    die "ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH.

Please set the JAVA_HOME variable in your environment to match the
location of your Java installation."
  fi
fi

# hash://
hash_  () { openssl dgst -"$1" < "$2" | cut -d ' ' -f 2; }
hash_file() { hash_ "$1" "$2"; }

verbose() { :; }
[ "${MVNW_VERBOSE-}" != true ] || verbose() { printf %s\\n "${1-}"; }

die() {
  printf %s\\n "$1" >&2
  exit 1
}

trim() { printf %s\\n "${1}" | sed 's/^[[:space:]]*//' | sed 's/[[:space:]]*$//'; }

# parse distributionUrl and optional distributionSha256Sum, requires .teledata
while IFS="=" read -r key value; do
  case "${key-}" in
  distributionUrl) distributionUrl=$(trim "${value-}") ;;
  distributionSha256Sum) distributionSha256Sum=$(trim "${value-}") ;;
  esac
done <"${0%/*}/.mvn/wrapper/maven-wrapper.properties"

# apply MVNW_REPOURL
case "${distributionUrl-}" in
*?*)
  [ -z "${MVNW_REPOURL-}" ] || distributionUrl="$MVNW_REPOURL/${distributionUrl#*"repo.maven.apache.org/maven2/"}"
  ;;
esac

# Determine MAVEN_HOME from script location
MAVEN_HOME="${0%/*}"

# Find the project basedir
basedir="${0%/*}"
wdir="${0%/*}"
while :; do
  if [ -d "$wdir/.mvn" ]; then
    basedir=$wdir
    break
  fi
  if [ "$wdir" = / ]; then
    basedir="${0%/*}"
    break
  fi
  wdir="${wdir%/*}"
done

# MAVEN_PROJECTBASEDIR
MAVEN_PROJECTBASEDIR="${basedir}"
export MAVEN_PROJECTBASEDIR
verbose "Using MAVEN_PROJECTBASEDIR: $MAVEN_PROJECTBASEDIR"

# Download maven if needed
distributionUrlName="${distributionUrl##*/}"
distributionUrlNameMain="${distributionUrlName%.*}"
distributionUrlNameMain="${distributionUrlNameMain%-bin}"
MAVEN_HOME_LOCAL="${MAVEN_PROJECTBASEDIR}/.mvn/wrapper/${distributionUrlNameMain}"

if [ -d "$MAVEN_HOME_LOCAL" ]; then
  verbose "Found existing Maven installation at $MAVEN_HOME_LOCAL"
else
  verbose "Downloading Maven from $distributionUrl"

  if command -v curl > /dev/null 2>&1; then
    verbose "Using curl for download"
    if [ -n "${MVNW_USERNAME-}" ] && [ -n "${MVNW_PASSWORD-}" ]; then
      curl ${MVNW_VERBOSE:+"-v"} -f -L -o "$MAVEN_PROJECTBASEDIR/.mvn/wrapper/${distributionUrlName}" \
        --user "${MVNW_USERNAME}:${MVNW_PASSWORD}" "$distributionUrl" || die "Failed to download $distributionUrl"
    else
      curl ${MVNW_VERBOSE:+"-v"} -f -L -o "$MAVEN_PROJECTBASEDIR/.mvn/wrapper/${distributionUrlName}" \
        "$distributionUrl" || die "Failed to download $distributionUrl"
    fi
  elif command -v wget > /dev/null 2>&1; then
    verbose "Using wget for download"
    if [ -n "${MVNW_USERNAME-}" ] && [ -n "${MVNW_PASSWORD-}" ]; then
      wget ${MVNW_VERBOSE:+"-v"} --http-user="${MVNW_USERNAME}" --http-password="${MVNW_PASSWORD}" \
        -O "$MAVEN_PROJECTBASEDIR/.mvn/wrapper/${distributionUrlName}" "$distributionUrl" || die "Failed to download $distributionUrl"
    else
      wget ${MVNW_VERBOSE:+"-v"} -O "$MAVEN_PROJECTBASEDIR/.mvn/wrapper/${distributionUrlName}" \
        "$distributionUrl" || die "Failed to download $distributionUrl"
    fi
  else
    die "ERROR: Could not find 'curl' or 'wget' to download Maven distribution."
  fi

  # verify sha256sum if provided
  if [ -n "${distributionSha256Sum-}" ]; then
    if command -v sha256sum > /dev/null 2>&1; then
      echo "${distributionSha256Sum}  $MAVEN_PROJECTBASEDIR/.mvn/wrapper/${distributionUrlName}" | sha256sum -c - > /dev/null 2>&1 || \
        die "ERROR: Failed to validate Maven distribution SHA-256."
    elif command -v shasum > /dev/null 2>&1; then
      echo "${distributionSha256Sum}  $MAVEN_PROJECTBASEDIR/.mvn/wrapper/${distributionUrlName}" | shasum -a 256 -c - > /dev/null 2>&1 || \
        die "ERROR: Failed to validate Maven distribution SHA-256."
    else
      verbose "WARNING: Could not validate Maven distribution SHA-256, no sha256sum or shasum command found."
    fi
  fi

  # unzip
  if command -v unzip > /dev/null 2>&1; then
    unzip -q -o -d "$MAVEN_PROJECTBASEDIR/.mvn/wrapper" "$MAVEN_PROJECTBASEDIR/.mvn/wrapper/${distributionUrlName}" || \
      die "ERROR: Failed to unzip Maven distribution."
  else
    die "ERROR: Could not find 'unzip' to extract Maven distribution."
  fi

  # Clean up zip
  rm -f "$MAVEN_PROJECTBASEDIR/.mvn/wrapper/${distributionUrlName}"
fi

MAVEN_HOME="$MAVEN_HOME_LOCAL"
verbose "Using MAVEN_HOME: $MAVEN_HOME"

exec "$MAVEN_HOME/bin/mvn" "$@"

