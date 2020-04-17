# SoundScapeAPI

I haven't ever noticed any problems with the sounds in Minecraft personally until recently. In the last few weeks as I've begun to develop more interesting things in playing around with the sounds, one of the things that's really bugged me is the fact that the sounds drop of *very* quickly. So, to remedy this, I've created this API. It's nothing too fancy, but it will allow sounds to be played at an extended range with some neat sound simulations.

For the curious, this library works as the following. When a sound is played, it has a maximum distance from which it can be heard. Up until that distance, the sound will be simulated to all the nearby players within earshot. The sound's volume is controlled by either a (decreasing) logarithmic or exponential function, and continues to decrease until it can no longer be heard. The sound is mimicked to be in the direction of the source by getting the normal vector poiting in the direction of the source to the player, and playing the sound with a delay equal to that of the distance divided by the speed of sound (343 m/s). In doing this, not only is the direction of the sound accurately simulated, but also is the transmittance of the sound. This does not yet take into account obstructions in the path of the sounds, but I do intend to look into doing that in the future.

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
	<version>1.0.1</version>
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
