# word-count

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

This application uses GraalVM. For more information, please visit its website: https://www.graalvm.org/

## Input assumptions
● Word : To simplify, a word is represented by a sequence of one or more characters
between „a‟ and „z‟ or between „A‟ and „Z‟). For example “agdfBh”.

● Letter Case : When counting frequencies, we are interested in the case insensitive
frequency (i.e. in the text “The sun shines over the lake”, the library should count 2
occurrences for any of the words “the” or “The” or “tHE” etc).

● Input Text : The input text contains words separated by various separator characters.
Note that the characters from „a‟ and „z‟ and „A‟ and „Z‟ can only appear within
words.

● Available Memory : There is enough memory to store the whole input text.

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:

```shell script
./gradlew quarkusDev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.

## Packaging and running the application

The application can be packaged using:

```shell script
./gradlew build
```

It produces the `quarkus-run.jar` file in the `build/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `build/quarkus-app/lib/` directory.

The application is now runnable using `java -jar build/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:

```shell script
./gradlew build -Dquarkus.package.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar build/*-runner.jar`.

## Creating a native executable

You can create a native executable using:

```shell script
./gradlew build -Dquarkus.package.type=native
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using:

```shell script
./gradlew build -Dquarkus.package.type=native -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./build/word-count-1.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/gradle-tooling.

## Run application with Docker
Before building the container image run:
```shell
 ./gradlew build
```

 Then, build the image with:

```shell
 docker build -f src/main/docker/Dockerfile.jvm -t quarkus/word-count-jvm .
```
 Then run the container using:

 ```shell
 docker run -i --rm -p 8080:8080 quarkus/word-count-jvm
 ```

## Testing

### Unit
```shell script
./gradlew test
```

### Integration
```shell script
./gradlew testNative
```

### Manual

You can run the following in the command line to test once the application is running locally
```shell script
curl -H "Content-Type: application/json" -X POST -d '{"sentence":"The sun shines over the lake"}' http://0.0.0.0:8080/wordFrequency/highest
```
```shell script
curl -H "Content-Type: application/json" -X POST -d '{"sentence":"The sun shines over the lake","word":"shines"}' http://0.0.0.0:8080/wordFrequency/frequency
```
```
curl -H "Content-Type: application/json" -X POST -d '{"sentence":"The sun shines over the lake","n":2}' http://0.0.0.0:8080/wordFrequency/mostFrequent
```

## Troubleshooting
Having trouble running the project? Make sure you have JAVA_HOME and PATH set correctly
For example:
```shell script
export PATH=/Library/Java/JavaVirtualMachines/graalvm-jdk-17.0.8+9.1/Contents/Home/bin/:$PATH
export JAVA_HOME=/Library/Java/JavaVirtualMachines/graalvm-jdk-17.0.8+9.1/Contents/Home/
```