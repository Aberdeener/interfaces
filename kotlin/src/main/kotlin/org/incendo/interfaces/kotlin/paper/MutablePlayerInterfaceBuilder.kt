package org.incendo.interfaces.kotlin.paper

import org.bukkit.event.inventory.InventoryClickEvent
import org.incendo.interfaces.core.click.ClickHandler
import org.incendo.interfaces.core.transform.InterfaceProperty
import org.incendo.interfaces.core.transform.Transform
import org.incendo.interfaces.core.view.InterfaceView
import org.incendo.interfaces.kotlin.MutableInterfaceBuilder
import org.incendo.interfaces.paper.PlayerViewer
import org.incendo.interfaces.paper.click.InventoryClickContext
import org.incendo.interfaces.paper.pane.PlayerPane
import org.incendo.interfaces.paper.type.PlayerInterface
import org.incendo.interfaces.paper.view.PlayerInventoryView
import org.incendo.interfaces.paper.view.PlayerView

@Suppress("unused")
public class MutablePlayerInterfaceBuilder :
    MutableInterfaceBuilder<
        PlayerPane,
        InventoryClickEvent,
        PlayerViewer,
        InventoryClickContext<PlayerPane, PlayerInventoryView>> {

    private var internalBuilder: PlayerInterface.Builder = PlayerInterface.builder()

    // <editor-fold desc="Mutable Properties">
    /** The click handler of the interface. */
    public var clickHandler:
        ClickHandler<
            PlayerPane,
            InventoryClickEvent,
            PlayerViewer,
            InventoryClickContext<PlayerPane, PlayerInventoryView>>
        get() = internalBuilder.clickHandler()
        set(value) = mutate { internalBuilder.clickHandler(value) }
    // </editor-fold>

    // <editor-fold desc="Mutating Functions">
    /**
     * Sets whether the interface should update.
     *
     * @param toggle true if the interface should update, false if not
     * @param interval how many ticks to wait between updates
     */
    public fun updates(toggle: Boolean = true, interval: Int = 1): Unit = mutate {
        internalBuilder.updates(toggle, interval)
    }

    /**
     * Adds the given [transform] to the interface.
     *
     * @param transform transform to add
     */
    public fun addTransform(transform: Transform<PlayerPane, PlayerViewer>): Unit = mutate {
        internalBuilder.addTransform(transform)
    }

    /**
     * Adds the given [transform] to the interface.
     *
     * @param transform transform to add
     */
    @Suppress("unchecked_cast")
    public fun addTransform(
        property: InterfaceProperty<*> = InterfaceProperty.dummy(),
        transform: (PlayerPane, PlayerView<PlayerPane>) -> PlayerPane
    ): Unit = mutate {
        internalBuilder.addTransform(
            property, transform as (PlayerPane, InterfaceView<PlayerPane, *>) -> PlayerPane)
    }

    /**
     * Adds the given [transform] to the interface.
     *
     * @param transform transform to add
     */
    public fun withTransform(
        property: InterfaceProperty<*> = InterfaceProperty.dummy(),
        transform: (MutablePlayerPaneView) -> Unit
    ) {
        addTransform(property) { PlayerPane, interfaceView ->
            MutablePlayerPaneView(PlayerPane, interfaceView).also(transform).toPlayerPane()
        }
    }
    // </editor-fold>

    private fun mutate(mutator: PlayerInterface.Builder.() -> PlayerInterface.Builder) {
        this.internalBuilder = internalBuilder.mutator()
    }

    /**
     * Converts this mutable player interface builder to a [PlayerInterface.Builder].
     *
     * @return the converted builder
     */
    public fun toBuilder(): PlayerInterface.Builder = internalBuilder
}
