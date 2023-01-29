#!/bin/bash
# If you have VS Code, just use the default build task

shopt -s globstar
javac -d ./bin ./src/**/*.java
cd bin
jar cmvf ../META-INF/MANIFEST.MF ../farm.jar **/*.class