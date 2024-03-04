package it.creeper.easyfreeze.commands;

import it.creeper.easyfreeze.EasyFreeze;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;


public class FreezeCommand implements CommandExecutor, Listener {

    private Plugin plugin = EasyFreeze.getPlugin(EasyFreeze.class);

    private static final ArrayList<Player> frozenPlayers = new ArrayList<>();

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {



        Player player = (Player) commandSender;


        if (label.equalsIgnoreCase("easyfreeze")) {
            player.sendMessage(ChatColor.BLUE + "EasyFreeze | by Creeper215");
            return true;
        }


        if(!player.hasPermission("easyfreeze.reload")) {
            String nopermsconfigreload = plugin.getConfig().getString("nopermsreload");
            player.sendMessage(ChatColor.RED + nopermsconfigreload);
            return true;
        }

        if (label.equalsIgnoreCase("easyfreezereload")) {
            plugin.reloadConfig();
            String configreloaded = plugin.getConfig().getString("configreloaded");
            player.sendMessage(ChatColor.AQUA + configreloaded);
            return true;
        }

        //Player target = Bukkit.getPlayer(args[0]);

        if(!player.hasPermission("easyfreeze.freeze")) {
            String nopermsmessage = plugin.getConfig().getString("noperms");
            player.sendMessage(ChatColor.RED + nopermsmessage);
            return true;
        }


        if(!(commandSender instanceof Player)) {

            String noconsoleexc = plugin.getConfig().getString("consoleunable");
            commandSender.sendMessage(ChatColor.RED + noconsoleexc);

            return true;
        }



        if (label.equalsIgnoreCase("freeze")) {



            if(args.length == 0) {
                String freezehelpstr = plugin.getConfig().getString("usefreezehelp");
                player.sendMessage(ChatColor.YELLOW + freezehelpstr);

                return true;
            }

            Player target = Bukkit.getPlayer(args[0]);

            if(target == null) {
                String targnullstr = plugin.getConfig().getString("targetnull");
                player.sendMessage(ChatColor.RED + targnullstr);

                return true;

            }
            String freezemessage = plugin.getConfig().getString("freezemessage");
            player.sendMessage(ChatColor.GREEN + freezemessage);
            frozenPlayers.add(target);

            /*if(frozenPlayers.contains(target)) {

            }

             */


            return true;
        }


        if (label.equalsIgnoreCase("unfreeze")) {



            if(args.length == 0) {
                String useunfreezehelp = plugin.getConfig().getString("useunfreezehelp");
                player.sendMessage(ChatColor.YELLOW + useunfreezehelp);

                return true;
            }



            Player target = Bukkit.getPlayer(args[0]);

            if (target == null) {
                String targnullstr = plugin.getConfig().getString("targetnull");
                player.sendMessage(ChatColor.RED + targnullstr);
                return true;

            }

            String unfreezemessage = plugin.getConfig().getString("unfreezemessage");
            player.sendMessage(ChatColor.GREEN + unfreezemessage);
            frozenPlayers.remove(target);
            return true;
        }



        return false;
    }

    @EventHandler
    void onPlayerMove(PlayerMoveEvent event) {
        if(frozenPlayers.contains(event.getPlayer())) {
            Player player = event.getPlayer();
            event.setCancelled(true);
            String frozenplayermessage = plugin.getConfig().getString("frozenplayermessage");
            player.sendMessage(ChatColor.RED + frozenplayermessage);

        }
    }
}
