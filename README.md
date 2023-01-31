# farm

One of my first Java programs: a farm game!

I actually rewrote this recently for fun, and to refresh my Java knowledge. I even added a Github Action to create a jar file artifact. You can see the old code on the other branch. It's pretty entertaining to read, as it was written when I was just learning programming.

### Changes

I added dependency injection with Google Guice, and testing with JUnit 5 and Mockito.

### How to

- Build: `mvn package`
- Test: `mvn test`
- Create native executable: see build-serialization.sh
- Update dependencies: `mvn versions:use-latest-releases`

### VS Code

If you have trouble with the extension, try running `Java > clean Java Language server workspace`
