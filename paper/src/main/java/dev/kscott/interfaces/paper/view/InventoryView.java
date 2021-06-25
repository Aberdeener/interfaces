package dev.kscott.interfaces.paper.view;

import dev.kscott.interfaces.core.Interface;
import dev.kscott.interfaces.core.pane.Pane;
import dev.kscott.interfaces.core.view.InterfaceView;
import dev.kscott.interfaces.paper.PlayerViewer;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.checkerframework.checker.nullness.qual.NonNull;

/**
 * An InterfaceView containing a Bukkit inventory.
 */
public interface InventoryView<T extends Pane> extends InterfaceView<T, PlayerViewer, Interface<T, PlayerViewer>>, InventoryHolder {

    /**
     * Returns the inventory.
     *
     * @return the inventory
     */
    @NonNull Inventory inventory();

}
