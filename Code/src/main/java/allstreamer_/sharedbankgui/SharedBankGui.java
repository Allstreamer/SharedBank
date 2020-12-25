package allstreamer_.sharedbankgui;

import allstreamer_.sharedbankgui.Commands.SharedCommandClass;
import allstreamer_.sharedbankgui.Gui.ClickEventClass;
import allstreamer_.sharedbankgui.Gui.InteractionUI;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Server;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.math.BigDecimal;
import java.math.RoundingMode;

public final class SharedBankGui extends JavaPlugin {
    private static Economy econ = null;
    private static double SharedMoney;
    public static InteractionUI interactionUI;

    @Override
    public void onEnable() {
        System.out.println("Loading Shared Bank Gui Plugin");
        if (!setupEconomy() ) {
            System.out.println("SharedBankGui - Disabled due to no Vault dependency found!");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        saveDefaultConfig();

        interactionUI = new InteractionUI(this);
        SharedMoney = round(getConfig().getDouble("MainBank"),2);

        Server server = getServer();
        PluginManager pluginManager = server.getPluginManager();

        pluginManager.registerEvents(new ClickEventClass(this),this);
        getCommand("shared").setExecutor(new SharedCommandClass());

        System.out.println("Done Loading!");
    }

    @Override
    public void onDisable() {
        getConfig().set("MainBank",round(SharedMoney,2));
        saveConfig();
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return true;
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = BigDecimal.valueOf(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public static Economy getEconomy() {
        return econ;
    }

    public static double getSharedMoney() { return round(SharedMoney,2); }
    public static void setSharedMoney(double Amount) { SharedMoney = round(Amount,2); }
    public static void subtractSharedMoney(double Amount) { SharedMoney -= round(Amount,2); }
    public static void addSharedMoney(double Amount) { SharedMoney += round(Amount,2); }

    public static InteractionUI getInteractionUI() { return interactionUI; }
}
