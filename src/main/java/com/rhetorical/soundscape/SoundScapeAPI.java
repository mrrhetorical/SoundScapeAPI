package com.rhetorical.soundscape;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public class SoundScapeAPI {

	public static final float SPEED_OF_SOUND = 343f;

	private static List<Player> getNearbyPlayers(Location origin, float distance) {
		List<Player> nearby = new ArrayList<>();
		if (origin.getWorld() == null)
			return nearby;

		for (Player p : origin.getWorld().getPlayers()) {
			if (p.getLocation().distanceSquared(origin) <= distance)
				nearby.add(p);
		}

		return nearby;
	}

	/**
	 * Normalizes the vector and returns a copy.
	 * @param location The location to normalize the position vector of.
	 * @return A copy of the vector, normalized.
	 * */
	private static Location normalize(Location location) {
		double x = location.getX(), y = location.getY(), z = location.getZ();
		float mag = (float) Math.sqrt(x * x + y * y + z * z);
		return new Location(location.getWorld(), x / mag, y / mag, z / mag);
	}

	/**
	 * Plays the sound to all players within the given distance originating from the player origin.
	 * The further away from the origin the surrounding players are, the quieter it will take.
	 * @param origin The player the sound originates from
	 * @param sound The sound to play
	 * @param pitch The pitch of the sound
	 * @param maxDistance The maximum distance away the sound will be played to other players
	 * */
	public static void playSound(Player origin, Sound sound, float pitch, float maxDistance) {
		playSound(origin.getLocation(), sound, pitch, maxDistance, true);
	}

	/**
	 * Plays the sound to all players within the given distance originating from the player origin.
	 * The further away from the origin the surrounding players are, the quieter it will take.
	 * @param origin The player the sound originates from
	 * @param sound The sound to play
	 * @param pitch The pitch of the sound
	 * @param maxDistance The maximum distance away the sound will be played to other players
	 * @param simDistance Should distance be simulated
	 * */
	public static void playSound(Player origin, Sound sound, float pitch, float maxDistance, boolean simDistance) {
		playSound(origin.getLocation(), sound, pitch, maxDistance, simDistance);
	}

	/**
	 * Plays the sound to all players within the given distance originating from the player origin.
	 * The further away from the origin the surrounding players are, the quieter it will take.
	 * @param origin The player the sound originates from
	 * @param sound The sound to play
	 * @param pitch The pitch of the sound
	 * @param maxDistance The maximum distance away the sound will be played to other players
	 * */
	public static void playSound(Location origin, Sound sound, float pitch, float maxDistance) {
		playSound(origin, sound, pitch, maxDistance, true);
	}


	/**
	 * Plays the sound to all players within the given distance originating from the player origin.
	 * The further away from the origin the surrounding players are, the quieter it will take.
	 * @param origin The player the sound originates from
	 * @param sound The sound to play
	 * @param pitch The pitch of the sound
	 * @param maxDistance The maximum distance away the sound will be played to other players
	 * @param simDistance Should distance be simulated
	 * */
	public static void playSound(Location origin, Sound sound, float pitch, float maxDistance, boolean simDistance) {
		List<Player> nearby = getNearbyPlayers(origin, maxDistance * maxDistance);

		float distance, per, volume;
		Location direction;
		for (Player p : nearby) {
			distance = (float) origin.distance(p.getLocation());
			per = (distance / maxDistance);

			//logarithmic degradation after 13% maximum distance away
			if (per <= 0.13) {
				volume = 1 - per;
			} else {
				volume = (float) -Math.log(per);
			}

			volume = volume > 1f ? 1f : volume < 0f ? 0 : volume;
			direction = normalize(origin.subtract(p.getLocation()));

			//Queue the sound for sound simulation
			if (SoundScape.getInstance().isSimulateDistance() && simDistance)
				queueSound(p, sound, direction, volume, pitch, distance);
			else
				p.playSound(p.getLocation().add(direction), sound, volume, pitch);

			if (SoundScape.getInstance().isDebug()) {
				Bukkit.getServer().getLogger().info("Sound Generated");
				Bukkit.getServer().getLogger().info(String.format("From: %s:(%s, %s, %s))", origin.getWorld()!= null ? origin.getWorld().getName() : "null", origin.getX(), origin.getY(), origin.getZ()));
				Bukkit.getServer().getLogger().info("Target: " + p.getName());
				Bukkit.getServer().getLogger().info("Sound: " + sound.toString());
				Bukkit.getServer().getLogger().info("Volume: " + volume);
				Bukkit.getServer().getLogger().info("Distance: " + distance);
				Bukkit.getServer().getLogger().info("Percentage of the maximum away: " + per);
				Bukkit.getServer().getLogger().info("Travel time: " + distance / SPEED_OF_SOUND);
			}
		}
	}



	/**
	 * Queues the sound to be played with the given distance away from the origin and the normal vector pointing
	 * towards the source.
	 *
	 * @param p The player to send the sound to
	 * @param sound The sound to play
	 * @param normal The location as a direction towards the audio source
	 * @param volume The volume of the sound
	 * @param pitch The pitch of the sound
	 * @param distance The distance the sound has to travel
	 * */
	public static void queueSound(Player p, Sound sound, Location normal, float volume, float pitch, float distance) {
		BukkitRunnable br = new BukkitRunnable() {
			@Override
			public void run() {
				p.playSound(p.getLocation().add(normal), sound, volume, pitch);
			}
		};

		br.runTaskLaterAsynchronously(SoundScape.getInstance(), 20L * Math.round(distance / SPEED_OF_SOUND));
	}

}
