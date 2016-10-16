Code example from Effective Java: Chapter 2.5 Creating Unnecessary Objects (Long vs long) 

Extended to provide examples of passing Java 8 Lambda and Method functions


Command Line Compile
```
mkdir -p build/classes/
find ./src/ -name "*.java" | xargs -t javac -d build/classes/ -Xlint:unchecked      # Whole directory
javac -sourcepath ./src/ src/main/java/com/company/LongLoop.java -d build/classes/  # Dependencies of Specific File
java -cp build/classes/ com.company.LongLoop
```


JAR build
```
echo Main-Class: com.company.LongLoop > ./LongLoop.manifest
mkdir -p  build/jar/
jar cfm   build/jar/LongLoop.jar ./LongLoop.manifest -C build/classes/ .
java -jar build/jar/LongLoop.jar
```


Ant Build (default file is build.xml)            
```
ant -f LongLoop.xml clean
ant -f LongLoop.xml compile jar run
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
