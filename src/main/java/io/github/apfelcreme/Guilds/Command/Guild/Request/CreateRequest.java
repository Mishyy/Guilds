package io.github.apfelcreme.Guilds.Command.Guild.Request;

import io.github.apfelcreme.Guilds.Command.Request;
import io.github.apfelcreme.Guilds.Guild.Guild;
import io.github.apfelcreme.Guilds.Guilds;
import io.github.apfelcreme.Guilds.GuildsConfig;
import io.github.apfelcreme.Guilds.GuildsUtil;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

/**
 * Guilds
 * Copyright (C) 2015 Lord36 aka Apfelcreme
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
 * @author Lord36 aka Apfelcreme on 10.05.2015.
 */
public class CreateRequest extends Request {

    private String name;
    private String tag;
    private ChatColor color;

    public CreateRequest(Player sender, String name, String tag, ChatColor color) {
        super(sender);
        this.name = name;
        this.tag = tag;
        this.color = color;
    }

    @Override
    public void execute() {
        Guild guild = new Guild(null, name, tag, color, 0.0);
        guild.create(sender);
        Guilds.getEconomy().withdrawPlayer(sender, GuildsConfig.getLevelData(1).getCost());
        Guilds.getInstance().getChat().sendMessage(sender, GuildsConfig
                .getColoredText("info.guild.create.guildCreated", guild.getColor())
                .replace("{0}", guild.getName()));
        Guilds.getInstance().getLogger().info(sender.getName() + " has created guild '"
                + guild.getName() + "'");
    }
}
