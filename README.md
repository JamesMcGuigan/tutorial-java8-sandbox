 
# Classes

## com.company.Main

This is the main method, referenced in [jarfile.manifest]() [build.xml]() and [pom.xml]()

Its calls the .run() methods of all the other classes

## com.company.LongLoop

Code example from Effective Java: Chapter 2.5 Creating Unnecessary Objects (Long vs long)

Extended to provide examples of passing Java 8 Lambda and Method functions

## com.company.FileReader

Multiple different examples of how to read a text file


# Build

Command Line Compile
```
mkdir -p build/classes/
find ./src/ -name "*.java" | xargs -t javac -d build/classes/ -Xlint:unchecked  # Whole directory
javac -sourcepath ./src/ src/main/java/com/company/Main.java -d build/classes/  # Dependencies of Specific File
java -cp build/classes/ com.company.Main
```


JAR build
```
echo Main-Class: com.company.Main > ./jarfile.manifest
mkdir -p  build/jar/
jar cfm   build/jar/LongLoop.jar ./jarfile.manifest -C build/classes/ .
java -jar build/jar/LongLoop.jar
```


Ant Build (default file is build.xml)
- https://ant.apache.org/manual/tutorial-HelloWorldWithAnt.html
```
ant -f build.xml clean
ant compile jar 
ant run
```


Maven Build (pom.xml)
- https://maven.apache.org/guides/getting-started/maven-in-five-minutes.html
```
mvn package exec:java 
java -jar target/LongLoop-1.0-SNAPSHOT.jar 
```


Output
```
bigLong:    2305843005992468481
9131 ms
littleLong: 2305843005992468481
1489 ms
bigLong:    2305843005992468481
9649 ms
9649 ms
littleLong: 2305843005992468481
1618 ms
1618 ms
bigLong:    2305843005992468481
11876 ms
11876 ms
littleLong: 2305843005992468481
1469 ms
1469 ms
```
