package allstreamer_.sharedbankgui.Gui;

import allstreamer_.sharedbankgui.SharedBankGui;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class ClickEventClass implements Listener {
    private static Economy econ;

    public SharedBankGui instance;

    private static Double DepositAmount1;
    private static Double DepositAmount2;
    private static Double DepositAmount3;

    private static Double WithdrawAmount1;
    private static Double WithdrawAmount2;
    private static Double WithdrawAmount3;

    public ClickEventClass(SharedBankGui instance_) {
        this.instance = instance_;
        econ = SharedBankGui.getEconomy();

        FileConfiguration configuration = instance.getConfig();
        DepositAmount1 = configuration.getDouble("DepositAmount1");
        DepositAmount2 = configuration.getDouble("DepositAmount2");
        DepositAmount3 = configuration.getDouble("DepositAmount3");

        WithdrawAmount1 = configuration.getDouble("WithdrawAmount1");
        WithdrawAmount2 = configuration.getDouble("WithdrawAmount2");
        WithdrawAmount3 = configuration.getDouble("WithdrawAmount3");
    }

    @EventHandler
    public void ClickEvent(InventoryClickEvent Event){
        try {
            String inventoryName = Event.getView().getTitle();
            final ItemStack currentItem = Event.getCurrentItem();
            if (currentItem == null) return;
            Material ItemPickup = currentItem.getType();

            Player player = (Player) Event.getWhoClicked();

            if (inventoryName.equals(ChatColor.GOLD + "Shared Bank")) {
                if (ItemPickup == Material.CHEST) {
                    player.closeInventory();
                    SharedBankGui.getInteractionUI().OpenDepositGui(player);
                } else if (ItemPickup == Material.DROPPER) {
                    player.closeInventory();
                    SharedBankGui.getInteractionUI().OpenWithdrawGui(player);
                }

                Event.setCancelled(true);
            }else if (inventoryName.equals(ChatColor.GOLD + "Deposit")){
                if (ItemPickup == Material.GOLD_NUGGET){
                    if (SharedBankGui.round(econ.getBalance(player),2) >= DepositAmount1) {

                        SharedBankGui.addSharedMoney(DepositAmount1);
                        econ.withdrawPlayer(player,DepositAmount1);

                        player.sendMessage("Deposited " + ChatColor.GOLD + DepositAmount1 + "$");
                        player.closeInventory();
                    }else {
                        player.sendMessage(ChatColor.RED + "Not Enought Money To Deposit");
                    }
                }else if (ItemPickup == Material.GOLD_INGOT){
                    if (SharedBankGui.round(econ.getBalance(player),2) >= DepositAmount2) {

                        SharedBankGui.addSharedMoney(DepositAmount2);
                        econ.withdrawPlayer(player,DepositAmount2);

                        player.sendMessage("Deposited " + ChatColor.GOLD + DepositAmount2 + "$");
                        player.closeInventory();
                    }else {
                        player.sendMessage(ChatColor.RED + "Not Enought Money To Deposit");
                    }
                }else if (ItemPickup == Material.GOLD_BLOCK){
                    if (SharedBankGui.round(econ.getBalance(player),2) >= DepositAmount3) {

                        SharedBankGui.addSharedMoney(DepositAmount3);
                        econ.withdrawPlayer(player,DepositAmount3);

                        player.sendMessage("Deposited " + ChatColor.GOLD + DepositAmount3 + "$");
                        player.closeInventory();
                    }else {
                        player.sendMessage(ChatColor.RED + "Not Enought Money To Deposit");
                    }
                }else if (ItemPickup == Material.DIAMOND_BLOCK){
                    player.sendMessage("You Deposited " + ChatColor.GOLD + (SharedBankGui.round(econ.getBalance(player),2) + "$"));

                    SharedBankGui.addSharedMoney(SharedBankGui.round(econ.getBalance(player),2));
                    econ.withdrawPlayer(player,SharedBankGui.round(econ.getBalance(player),2));
                    player.closeInventory();
                }

                Event.setCancelled(true);
            }else if (inventoryName.equals(ChatColor.GOLD + "Withdraw")){
                if (ItemPickup == Material.GOLD_NUGGET){
                    if (SharedBankGui.getSharedMoney() >= WithdrawAmount1) {

                        SharedBankGui.subtractSharedMoney(WithdrawAmount1);
                        econ.depositPlayer(player,WithdrawAmount1);

                        player.sendMessage("Withdrew " + ChatColor.GOLD + WithdrawAmount1 + "$");
                        player.closeInventory();
                    }else {
                        player.sendMessage(ChatColor.RED + "Not Enought Money To Withdraw");
                    }
                }else if (ItemPickup == Material.GOLD_INGOT){
                    if (SharedBankGui.getSharedMoney() >= WithdrawAmount2) {

                        SharedBankGui.subtractSharedMoney(WithdrawAmount2);
                        econ.depositPlayer(player,WithdrawAmount2);

                        player.sendMessage("Withdrew " + ChatColor.GOLD + WithdrawAmount2 + "$");
                        player.closeInventory();
                    }else {
                        player.sendMessage(ChatColor.RED + "Not Enought Money To Withdraw");
                    }
                }else if (ItemPickup == Material.GOLD_BLOCK){
                    if (SharedBankGui.getSharedMoney() >= WithdrawAmount3) {

                        SharedBankGui.subtractSharedMoney(WithdrawAmount3);
                        econ.depositPlayer(player,WithdrawAmount3);

                        player.sendMessage("Withdrew " + ChatColor.GOLD + WithdrawAmount3 + "$");
                        player.closeInventory();
                    }else {
                        player.sendMessage(ChatColor.RED + "Not Enought Money To Withdraw");
                    }
                }

                Event.setCancelled(true);
            }
        }catch (Exception E){

        }
    }
}
