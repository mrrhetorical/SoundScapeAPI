# SoundScapeAPI

Sound scape API for simulating loud, long distance sounds in Minecraft. 

See the JavaDocs here: https://mrrhetorical.github.io/SoundScapeAPI/

API Usage
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
