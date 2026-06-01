# SDA-Pro Test Evidence

This folder provides lightweight test evidence for the plain Java prototype. The project does not use JUnit or an external database, so testing is handled through compile checks, smoke testing, and documented pattern-level test cases.

## Test Types Covered

| Test Type | Evidence |
|---|---|
| Unit-level pattern checks | `pattern-test-cases.md` |
| Integration flow check | `integration-test-scenario.md` |
| End-to-end smoke test | `smoke-test.sh` or `Makefile smoke` |
| Contract review | `docs/api/` |

## Commands

Linux/macOS/Git Bash:

```bash
make smoke
```

Windows PowerShell:

```powershell
Get-ChildItem -Recurse src -Filter *.java | ForEach-Object { $_.FullName } > sources.txt
javac -d out @sources.txt
java -cp out MainDemo
```
