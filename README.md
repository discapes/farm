# farm  - ![build status](https://github.com/discapes/farm/actions/workflows/test_and_package.yml/badge.svg)

This was one of my first Java programs, where you can have a farm with different animals. I actually rewrote this recently for fun and to refresh my Java knowledge, but there's still a branch with the old code. I also added some new features:

- Google Guice for dependency injection
- JUnit 5 tests that combine Guice and Mockito very nicely
- a Github Action to test and package the program.
- Saving the game with XStream, in a backwards-compatible way.
- Revamped and uniform TUI
- Support for native-image generation with GraalVM

### How to

- Build: `mvn package`
- Test: `mvn test`
- Update dependencies: `mvn versions:use-latest-releases`
- Create native executable:
  1. Install GraalVM
  2. `gu install native-image`
  3. `native-image -jar farm-1.0-SNAPSHOT.jar`

### VS Code

If you have trouble with the extension, try running `Java > clean Java Language server workspace`
