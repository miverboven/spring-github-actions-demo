# Example of GitHub action implementation for a Spring Boot project (Maven)

![Test, Upload artifact and Deploy to Heroku](https://github.com/miverboven/spring-github-actions-demo/workflows/Test,%20Upload%20artifact%20and%20Deploy%20to%20Heroku/badge.svg)

This is an example where GitHub Actions are used to establish a CI/CD pipline that checks compilation, tests, building, uploads the .jar-file artifact, and finally does an upload to Heroku of a Spring Boot MVC project (Maven) using Thymeleaf.

The combined-pipeline.yml starts with a testing job using Java 8 and 11 as the options in a matrix:

```yaml
name: Test, Upload artifact and Deploy to Heroku

on: workflow_dispatch

jobs:
  test:
    runs-on: ubuntu-latest
    strategy:
      matrix:
        java: [ 8, 11 ]
    name: Run unit tests on Java ${{ matrix.java }}
    steps:
      - uses: actions/checkout@master
      - name: Setup java
        uses: actions/setup-java@v1
        with:
          java-version: ${{ matrix.java }}
      - run: mvn -f pom.xml clean test
```

It then continues by running tests, building and finally uploading the resulting .jar-file. Two illustrative steps are included as well, whereby the contents of two directories inside the container are shown. Please note the inclusion of the version number in the filename of the .jar-file.

```yaml
  release:
    runs-on: ubuntu-latest
    needs: test
    name: Build, package and upload .jar artifact
    steps:
    - uses: actions/checkout@v1
    - name: Set up JDK 8
      uses: actions/setup-java@v1
      with:
        java-version: 8
    - name: Build and package project
      run: mvn -f pom.xml clean package
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

The last job deploys the application to Heroku.

```yaml
  deploy:
    runs-on: ubuntu-latest
    needs: release
    name: Deploy to Heroku
    steps:
      - uses: actions/checkout@v2
      - uses: akhileshns/heroku-deploy@v3.12.12
        with:
          heroku_api_key: ${{secrets.HEROKU_API_KEY}}
          heroku_email: ${{secrets.HEROKU_EMAIL}}
          heroku_app_name: ${{secrets.HEROKU_APP}}
```

This repository also includes the seperate and simplified versions of these jobs in their seperate files.
