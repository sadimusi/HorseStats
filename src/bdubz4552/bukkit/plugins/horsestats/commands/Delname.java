package bdubz4552.bukkit.plugins.horsestats.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;

import bdubz4552.bukkit.plugins.horsestats.HorseStatsCommand;
import bdubz4552.bukkit.plugins.horsestats.HorseStatsMain;
import bdubz4552.bukkit.plugins.horsestats.Message;

public class Delname extends HorseStatsCommand implements CommandExecutor {
	
	public Delname(HorseStatsMain horseStatsMain) {
		this.main = horseStatsMain;
	}
	
	public boolean onCommand(CommandSender sender, Command command,	String label, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			Horse h = null;
			if (p.isInsideVehicle()) {
				if (p.getVehicle() instanceof Horse) {
					h = (Horse) p.getVehicle();
				}
			}
			if (label.equalsIgnoreCase("delname")) {
				if (this.permCheck(p, "delname")) {
					this.run(p, h);
				}
			}
		} else {
			sender.sendMessage(ChatColor.BLUE + "[HorseStats] " + ChatColor.RED + "Commands cannot be used in console!");
		}
		return true;
	}
	
	public void run(Player p, Horse h) {
		if (h != null) {
			if (h.getOwner() == p) {
				h.setCustomName(null);
				Message.NORMAL.send(p, "Custom name erased.");
			} else {
				Message.OWNER.send(p);
			}
		} else {
			Message.RIDING.send(p);
		}
	}
}
