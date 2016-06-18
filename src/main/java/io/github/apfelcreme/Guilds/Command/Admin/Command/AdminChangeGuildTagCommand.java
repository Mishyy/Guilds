package io.github.apfelcreme.Guilds.Command.Admin.Command;

import io.github.apfelcreme.Guilds.Command.Admin.Request.ChangeGuildTagRequest;
import io.github.apfelcreme.Guilds.Command.SubCommand;
import io.github.apfelcreme.Guilds.Guild.Guild;
import io.github.apfelcreme.Guilds.Guilds;
import io.github.apfelcreme.Guilds.GuildsConfig;
import io.github.apfelcreme.Guilds.GuildsUtil;
import io.github.apfelcreme.Guilds.Manager.RequestController;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Copyright (C) 2016 Lord36 aka Apfelcreme
 * <p>
 * This program is free software;
 * you can redistribute it and/or modify it under the terms of the GNU General
 * Public License as published by the Free Software Foundation; either version 3
 * of the License, or (at your option) any later version.
 * <p>
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * <p>
 * You should have received a copy of the GNU General Public License along with
 * this program; if not, see <http://www.gnu.org/licenses/>.
 *
 * @author Lord36 aka Apfelcreme
 */
public class AdminChangeGuildTagCommand implements SubCommand {

    /**
     * executes the command
     *
     * @param commandSender the sender
     * @param strings       the command args
     */
    public void execute(CommandSender commandSender, String[] strings) {
        Player sender = (Player) commandSender;
        if (sender.hasPermission("Guilds.changeTag")) {
            if (strings.length >= 2) {
                Guild guild = Guilds.getInstance().getGuild(strings[1]);
                if (guild != null) {
                    String newTag = strings[2];
                    if (GuildsUtil.strip(newTag).length() <= GuildsConfig.getGuildTagLength()) {
                        RequestController.getInstance().addRequest(new ChangeGuildTagRequest(sender, guild, newTag));
                        Guilds.getInstance().getChat().sendMessage(sender, GuildsConfig
                                .getText("info.guildadmin.confirm.confirm"));
                    } else {
                        Guilds.getInstance().getChat().sendMessage(sender, GuildsConfig.getText("error.nameTooLong"));
                    }
                } else {
                    Guilds.getInstance().getChat().sendMessage(sender, GuildsConfig.getText("error.guildDoesntExist"));
                }
            } else {
                Guilds.getInstance().getChat().sendMessage(sender, GuildsConfig.getText("error.wrongUsage.changeNameAdmin"));
            }
        } else {
            Guilds.getInstance().getChat().sendMessage(sender, GuildsConfig.getText("error.noPermission"));
        }
    }
}
