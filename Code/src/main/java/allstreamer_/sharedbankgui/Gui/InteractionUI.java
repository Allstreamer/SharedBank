package allstreamer_.sharedbankgui.Gui;

import allstreamer_.sharedbankgui.SharedBankGui;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;

public class InteractionUI {
    SharedBankGui instance;
    Economy econ;

    private static Double DepositAmount1;
    private static Double DepositAmount2;
    private static Double DepositAmount3;

    private static Double WithdrawAmount1;
    private static Double WithdrawAmount2;
    private static Double WithdrawAmount3;

    private static ItemStack DepositItem1 = new ItemStack(Material.GOLD_NUGGET,1);
    private static ItemMeta DepositMeta1 = DepositItem1.getItemMeta();
    private static ItemStack DepositItem2 = new ItemStack(Material.GOLD_INGOT,1);
    private static ItemMeta DepositMeta2 = DepositItem2.getItemMeta();
    private static ItemStack DepositItem3 = new ItemStack(Material.GOLD_BLOCK,1);
    private static ItemMeta DepositMeta3 = DepositItem3.getItemMeta();
    private static ItemStack DepositItemFull = new ItemStack(Material.DIAMOND_BLOCK,1);
    private static ItemMeta DepositMetaFull = DepositItemFull.getItemMeta();

    private static ItemStack WithdrawItem1 = new ItemStack(Material.GOLD_NUGGET,1);
    private static ItemMeta WithdrawMeta1 = WithdrawItem1.getItemMeta();
    private static ItemStack WithdrawItem2 = new ItemStack(Material.GOLD_INGOT,1);
    private static ItemMeta WithdrawMeta2 = WithdrawItem2.getItemMeta();
    private static ItemStack WithdrawItem3 = new ItemStack(Material.GOLD_BLOCK,1);
    private static ItemMeta WithdrawMeta3 = WithdrawItem3.getItemMeta();

    private static ItemStack FillerItem = new ItemStack(Material.GRAY_STAINED_GLASS_PANE,1);
    private static ItemMeta FillterItemMeta = FillerItem.getItemMeta();

    private static ItemStack BankInfoItem = new ItemStack(Material.ANVIL,1);
    private static ItemMeta BankInfoMeta = BankInfoItem.getItemMeta();

    public InteractionUI(SharedBankGui instance_) {
        instance = instance_;
        econ = SharedBankGui.getEconomy();

        FileConfiguration configuration = instance.getConfig();
        DepositAmount1 = configuration.getDouble("DepositAmount1");
        DepositAmount2 = configuration.getDouble("DepositAmount2");
        DepositAmount3 = configuration.getDouble("DepositAmount3");

        WithdrawAmount1 = configuration.getDouble("WithdrawAmount1");
        WithdrawAmount2 = configuration.getDouble("WithdrawAmount2");
        WithdrawAmount3 = configuration.getDouble("WithdrawAmount3");

        FillterItemMeta.setDisplayName(" ");
        FillerItem.setItemMeta(FillterItemMeta);

        BankInfoMeta.setDisplayName(ChatColor.GOLD + "Bank");

        DepositMeta1.setDisplayName("Deposit " + ChatColor.GOLD + DepositAmount1 + "$");
        DepositMeta2.setDisplayName("Deposit " + ChatColor.GOLD + DepositAmount2 + "$");
        DepositMeta3.setDisplayName("Deposit " + ChatColor.GOLD + DepositAmount3 + "$");
        DepositMetaFull.setDisplayName("Deposit All Your Money");

        WithdrawMeta1.setDisplayName("Withdraw " + ChatColor.GOLD + WithdrawAmount1 + "$");
        WithdrawMeta2.setDisplayName("Withdraw " + ChatColor.GOLD + WithdrawAmount2 + "$");
        WithdrawMeta3.setDisplayName("Withdraw " + ChatColor.GOLD + WithdrawAmount3 + "$");

        DepositItem1.setItemMeta(DepositMeta1);
        DepositItem2.setItemMeta(DepositMeta2);
        DepositItem3.setItemMeta(DepositMeta3);
        DepositItemFull.setItemMeta(DepositMetaFull);

        WithdrawItem1.setItemMeta(WithdrawMeta1);
        WithdrawItem2.setItemMeta(WithdrawMeta2);
        WithdrawItem3.setItemMeta(WithdrawMeta3);
    }

    public void OpenDepositGui(Player player){
        Inventory gui = Bukkit.createInventory(player,27, ChatColor.GOLD + "Deposit");

        UpdateBankInfo(player);

        ItemStack[] Contents = {FillerItem,FillerItem,FillerItem,FillerItem,BankInfoItem,FillerItem,FillerItem,FillerItem,FillerItem,
                                FillerItem,FillerItem,DepositItem1,FillerItem,DepositItem2,FillerItem,DepositItem3,FillerItem,FillerItem,
                                FillerItem,FillerItem,FillerItem,FillerItem,DepositItemFull,FillerItem,FillerItem,FillerItem,FillerItem};

        gui.setContents(Contents);
        player.openInventory(gui);
    }

    public void OpenWithdrawGui(Player player){
        Inventory gui = Bukkit.createInventory(player,27, ChatColor.GOLD + "Withdraw");

        UpdateBankInfo(player);

        ItemStack[] Contents = {FillerItem,FillerItem,FillerItem,FillerItem,BankInfoItem,FillerItem,FillerItem,FillerItem,FillerItem,
                                FillerItem,FillerItem,WithdrawItem1,FillerItem,WithdrawItem2,FillerItem,WithdrawItem3,FillerItem,FillerItem,
                                FillerItem,FillerItem,FillerItem,FillerItem,FillerItem,FillerItem,FillerItem,FillerItem,FillerItem};

        gui.setContents(Contents);
        player.openInventory(gui);
    }

    private void UpdateBankInfo(Player player){
        List<String> BankInfoLore = Arrays.asList(ChatColor.DARK_PURPLE + "Bank Money: ", ChatColor.GOLD + (SharedBankGui.getSharedMoney() + "$"),
                ChatColor.DARK_PURPLE + "Your Money: ",ChatColor.GOLD + (econ.getBalance(player) + "$"));
        BankInfoMeta.setLore(BankInfoLore);
        BankInfoItem.setItemMeta(BankInfoMeta);

    }
}
