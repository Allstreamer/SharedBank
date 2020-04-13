package allstreamer_.sharedbankgui.Commands;

import allstreamer_.sharedbankgui.SharedBankGui;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

public class SharedCommandClass implements CommandExecutor {
    Economy econ = SharedBankGui.getEconomy();

    private static ItemStack FillerItem = new ItemStack(Material.STAINED_GLASS_PANE,1,(short)7);
    private static ItemMeta FillterItemMeta = FillerItem.getItemMeta();

    private static ItemStack DepositItem = new ItemStack(Material.CHEST,1);
    private static ItemMeta DepositItemMeta = DepositItem.getItemMeta();

    private static ItemStack WithdrawItem = new ItemStack(Material.DROPPER,1);
    private static ItemMeta WithdrawItemMeta = WithdrawItem.getItemMeta();

    private static ItemStack BankInfoItem = new ItemStack(Material.GOLD_BLOCK,1);
    private static ItemMeta BankInfoItemMeta = BankInfoItem.getItemMeta();

    public SharedCommandClass() {
        FillterItemMeta.setDisplayName(" ");
        FillerItem.setItemMeta(FillterItemMeta);

        DepositItemMeta.setDisplayName("Deposit Your Money");
        List<String> DepositItemLore = Arrays.asList(new String[]{ChatColor.DARK_PURPLE + "Deposit Your Money Here!"});
        DepositItemMeta.setLore(DepositItemLore);
        DepositItem.setItemMeta(DepositItemMeta);

        WithdrawItemMeta.setDisplayName("Withdraw Your Money");
        List<String> WithdrawItemLore = Arrays.asList(new String[]{ChatColor.DARK_PURPLE + "Withdraw Your Money Here!"});
        WithdrawItemMeta.setLore(WithdrawItemLore);
        WithdrawItem.setItemMeta(WithdrawItemMeta);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            Player player = (Player)sender;

            Inventory gui = Bukkit.createInventory(player,27, ChatColor.GOLD + "Shared Bank");

            List<String> BankInfoItemLore = Arrays.asList(new String[]{ChatColor.DARK_PURPLE + "The Bank Has",ChatColor.GOLD + (SharedBankGui.getSharedMoney() + "$"),ChatColor.DARK_PURPLE + "You Have",ChatColor.GOLD + (SharedBankGui.round(econ.getBalance(player),2) + "$")});
            BankInfoItemMeta.setLore(BankInfoItemLore);
            BankInfoItemMeta.setDisplayName("Bank Account");
            BankInfoItem.setItemMeta(BankInfoItemMeta);

            ItemStack[] Contents = {FillerItem,FillerItem,FillerItem,FillerItem,FillerItem,FillerItem,FillerItem,FillerItem,FillerItem,
                                    FillerItem,FillerItem,DepositItem,FillerItem,BankInfoItem,FillerItem,WithdrawItem,FillerItem,FillerItem,
                                    FillerItem,FillerItem,FillerItem,FillerItem,FillerItem,FillerItem,FillerItem,FillerItem,FillerItem};

            gui.setContents(Contents);
            player.openInventory(gui);
            return true;
        }else {
            return false;
        }

    }
}
