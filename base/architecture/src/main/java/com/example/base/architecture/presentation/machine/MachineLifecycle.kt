package com.example.base.architecture.presentation.machine

import com.example.base.architecture.presentation.machine.MachineLifecycle.State
import com.example.base.architecture.presentation.machine.lyfecycle.CombineMachineLifecycle

/**
 * Provides some activity state to be able to shut-down
 * tasks like background monitoring while the host is inactive
 * and to resume when back in action.
 * E.g.: Application focus
 */
interface MachineLifecycle {

    /**
     * Activity state
     */
    fun getState(): State

    /**
     * True if something observes lifecycle
     */
    fun hasObservers(): Boolean

    /**
     * Adds state observer.
     * @param observer Observer to get state updates
     */
    fun addObserver(observer: Observer)

    /**
     * Removes state observer
     * @param observer Observer to get state updates
     */
    fun removeObserver(observer: Observer)

    /**
     * UI state
     */
    enum class State {
        /**
         * UI is paused
         */
        PAUSED,

        /**
         * UI is resumed
         */
        ACTIVE;
    }

    /**
     * State observer
     */
    fun interface Observer {
        /**
         * Called when state changes
         */
        fun onStateChange(state: State)
    }
}

/**
 * Combines parent (receiver) state with [child] to get the combined state with OR on [MachineLifecycle.State.PAUSED]:
 * - Parent active, child paused - PAUSED
 * - Parent paused, child paused - PAUSED
 * - Parent active, child active - ACTIVE
 */
fun MachineLifecycle.combinePaused(child: MachineLifecycle): MachineLifecycle = CombineMachineLifecycle(
    this,
    child,
    ::pausedIfNotAllActive
)

/**
 * Combines parent (receiver) state with [child] and [State.ACTIVE] if both are active:
 * - Parent active, child paused - PAUSED
 * - Parent paused, child paused - PAUSED
 * - Parent active, child active - ACTIVE
 */
private fun pausedIfNotAllActive(parent: State, child: State): State = when {
    State.ACTIVE == parent && State.ACTIVE == child -> State.ACTIVE
    else -> State.PAUSED
}

