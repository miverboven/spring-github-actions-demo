# Example of GitHub action implementation for a Spring Boot project (Maven)

![Test, Build and Upload artifact](https://github.com/miverboven/spring-github-actions-demo/workflows/Test,%20Build%20and%20Upload%20artifact/badge.svg)

This is an example where GitHub Actions are used to establish a CI pipline that checks compilation, tests, building and finally uploads the .jar-file artifact of a Spring Boot MVC project (Maven) using Thymeleaf.

The workflow starts with a compilation job using Java 8 as the only option in a matrix:

```yaml
name: Test, Build and Upload artifact

on: push

jobs:
  compile:
    runs-on: ubuntu-latest
    strategy:
      matrix:
        java: [ 8 ]
    name: Java ${{ matrix.java }} compile
    steps:
      - uses: actions/checkout@master
      - name: Setup java
        uses: actions/setup-java@v1
        with:
          java-version: ${{ matrix.java }}
      - run: mvn -f pom.xml clean compile
```

It then continues by running tests, building and finally uploading the resulting .jar-file. Two illustrative steps are included as well, whereby the contents of two directories inside the container are shown. Please note the inclusion of the version number in the filename of the .jar-file.

```yaml
build:
    runs-on: ubuntu-latest
    needs: compile
    name: Build Maven project and upload artifact
    steps:
    - uses: actions/checkout@v1
    - name: Set up JDK 8
      uses: actions/setup-java@v1
      with:
        java-version: 8
    - name: Build and test project
      run: mvn -f pom.xml clean install
    - name: Show contents of the current working directory
      run: ls -la
    - name: Show contents of the target directory
      run: ls -la target
    - name: Upload Maven build artifact
      uses: actions/upload-artifact@v1
      with:
        name: artifact
        path: ./target/spring-github-actions-demo-0.1.jar
```