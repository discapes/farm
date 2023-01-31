#!/bin/bash
# This is to enable GraalVM native image building, because serialization requires a special config file.
# Make sure you have GraalVM installed and run `gu install native-image`
java -agentlib:native-image-agent=config-output-dir=src/main/resources/META-INF/native-image -jar target/farm-1.0-SNAPSHOT.jar
# Launch "x64 Native Tools Command Prompt for VS 2022"
# cd target 
# native-image -jar farm-1.0-SNAPSHOT.jar
