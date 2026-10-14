#!/bin/sh
set -eu
cd "$(dirname "$0")"
mkdir -p build/classes
javac -encoding UTF-8 -d build/classes src/orbitpatrol/*.java
java -cp build/classes orbitpatrol.MainFrame
