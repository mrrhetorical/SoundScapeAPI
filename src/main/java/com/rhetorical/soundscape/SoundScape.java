package com.rhetorical.soundscape;

import org.bukkit.plugin.java.JavaPlugin;

public class SoundScape extends JavaPlugin {

	private static SoundScape instance;

	private boolean debug;
	private boolean simulateDistance;
	private boolean logarithmic;

	@Override
	public void onEnable() {
		if (instance != null)
			return;

		instance = this;

		saveDefaultConfig();
		reloadConfig();

		debug = getConfig().getBoolean("debug");
		simulateDistance = getConfig().getBoolean("simulateDistance");
		logarithmic = getConfig().getBoolean("logarithmic");
	}

	public static SoundScape getInstance() {
		return instance;
	}


// Enable to enable the commands to test out values for yourself!
//	@Override
//	public boolean onCommand(CommandSender sender, Command cmd, String label, String... args) {
//		if (!label.equalsIgnoreCase("ss"))
//			return false;
//
//		if (!(sender instanceof Player))
//			return true;
//
//		if (args.length == 0)
//			SoundScapeAPI.playSound((Player) sender, Sound.ENTITY_PLAYER_HURT, 1f, 1f,300f);
//
//		if (args.length == 1) {
//			Sound sound;
//			try {
//				sound = Sound.valueOf(args[0]);
//			} catch (Exception e) {
//				sender.sendMessage(ChatColor.RED + "No sound with name " + args[0] + "!");
//				return true;
//			}
//
//			SoundScapeAPI.playSound((Player) sender, sound, 1f, 1f,300f);
//		} else if (args.length == 2) {
//			Sound sound;
//			try {
//				sound = Sound.valueOf(args[0]);
//			} catch (Exception e) {
//				sender.sendMessage(ChatColor.RED + "No sound with name " + args[0] + "!");
//				return true;
//			}
//
//			float distance;
//
//			try {
//				distance = Float.parseFloat(args[1]);
//
//			} catch (Exception e) {
//				sender.sendMessage(ChatColor.RED + "Invalid distance " + args[1] + "!");
//				return true;
//			}
//
//			SoundScapeAPI.playSound((Player) sender, sound, 0.7f, 1f, distance);
//		}
//
//		return true;
//	}

	public boolean isDebug() {
		return debug;
	}

	public boolean isSimulateDistance() {
		return simulateDistance;
	}

	public boolean isLogarithmic() {
		return logarithmic;
	}
}
