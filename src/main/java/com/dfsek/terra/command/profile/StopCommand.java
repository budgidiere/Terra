package com.dfsek.terra.command.profile;

import com.dfsek.terra.TerraProfiler;
import com.dfsek.terra.config.lang.LangUtil;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.polydev.gaea.command.WorldCommand;
import org.polydev.gaea.profiler.WorldProfiler;

import java.util.Collections;
import java.util.List;

public class StopCommand extends WorldCommand {
    public StopCommand(org.polydev.gaea.command.Command parent) {
        super(parent);
    }

    @Override
    public boolean execute(@NotNull Player sender, @NotNull Command command, @NotNull String label, @NotNull String[] args, World world) {
        WorldProfiler profile = TerraProfiler.fromWorld(world);
        profile.setProfiling(false);
        LangUtil.send("command.profile.stop", sender);
        return true;
    }

    @Override
    public String getName() {
        return "stop";
    }

    @Override
    public List<org.polydev.gaea.command.Command> getSubCommands() {
        return Collections.emptyList();
    }

    @Override
    public int arguments() {
        return 0;
    }

    @Override
    public List<String> getTabCompletions(@NotNull CommandSender sender, @NotNull String alias, @NotNull String[] args) {
        return Collections.emptyList();
    }
}
