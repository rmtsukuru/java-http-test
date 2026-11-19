#!/bin/sh

source ~/.bash_profile

# search "*.java" | grep "src/main/java" | xargs javac -d target/classes/
# Equivalent: find src/main/java -name "*.java" -print | xargs javac -d target/classes

# New and improved using better search function
echo 'search "*.java" src/main/java/ | xargs javac -d target/classes/'
echo ""
search "*.java" src/main/java/ | xargs javac -d target/classes/

