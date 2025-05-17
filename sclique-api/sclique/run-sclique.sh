#!/bin/bash

# Change to your project directory
cd /Users/ryancjy/Documents/Github/sclique/sclique-api/sclique || { echo "Failed to cd to project directory"; exit 1; }

# Run Maven clean install
mvn clean install || { echo "Maven build failed"; exit 1; }

# Change to target directory
cd target || { echo "Failed to cd to target directory"; exit 1; }

# Run the jar
java -jar sclique-0.0.1-SNAPSHOT.jar