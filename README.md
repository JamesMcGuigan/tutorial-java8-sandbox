Code example from Effective Java: Chapter 2.5 Creating Unnecessary Objects (Long vs long) 

Extended to provide examples of passing Java 8 Lambda and Method functions


Compile Directory
```
find . -name "*.java" -print | xargs javac -d out  -Xlint:unchecked
```

Compile Dependancies of Specific File
```
javac -sourcepath . src/com/company/LongLoop.java -d out/
```

Run: 
```
java -cp out/ com.company.LongLoop
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
