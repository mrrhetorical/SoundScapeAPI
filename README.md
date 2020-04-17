# SoundScapeAPI

Sound scape API for simulating loud, long distance sounds in Minecraft. 

See the JavaDocs here: https://mrrhetorical.github.io/SoundScapeAPI/

### Depend on this Plugin with Maven

#### Maven Repository
```xml
	<repositories>
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
	</repositories>
```

#### Maven Dependency
```xml
	<dependency>
	    <groupId>com.github.mrrhetorical</groupId>
	    <artifactId>SoundScapeAPI</artifactId>
	    <version>1.0.0</version>
	</dependency>
```

How to use the API
------

Import the main API class:
```java
 import com.rhetorical.soundscape.SoundScapeAPI;
```

Play a sound originating from a player using the following:
```java

SoundScapeAPI.playSound((Player) player, (Sound) sound, (float) volume, (float) pitch, (float) distance);
```

Play a sound originating from a location using the following:
```java

SoundScapeAPI.playSound((Location) location, (Sound) sound, (float) volume, (float) pitch, (float) distance);
```

Send a sound to a specific player with the given distance and normal vector (as a location) pointing towards the source:
```java

SoundScapeAPI.queueSound((Player) player, (Sound) sound, (Location) normal, (float) volume, (float) pitch, (float) distance);
```
