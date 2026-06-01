#!/usr/bin/env bash
set -e
find src -name "*.java" > sources.txt
javac -d out @sources.txt
java -cp out MainDemo | tee demo-output.txt
grep -q "Adapter" demo-output.txt
grep -q "Factory Method" demo-output.txt
grep -q "Composite" demo-output.txt
grep -q "Chain" demo-output.txt
grep -q "State" demo-output.txt
grep -q "Facade" demo-output.txt
grep -q "Observer" demo-output.txt
echo "Smoke test passed."
