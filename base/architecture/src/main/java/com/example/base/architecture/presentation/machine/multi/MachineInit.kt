package com.example.base.architecture.presentation.machine.multi

import com.example.base.architecture.presentation.machine.CommonMachineState
import com.example.base.architecture.presentation.machine.MachineLifecycle

/**
 * Proxy machine initialization record
 */
interface MachineInit<G: Any, U: Any> {
    /**
     * Machine key to find a machine among the others
     * @see UiStateProvider
     * @see GestureProcessor
     * @see MultiMachineState.mapUiState
     * @see MultiMachineState.mapGesture
     */
    val key: MachineKey<G, U>

    /**
     * Initial UI state for the machine
     */
    val initialUiState: U

    /**
     * Creates initial child state
     * [MachineLifecycle] passed to the factory determines the activity of
     * the machine within the machine group. For example, for a paging screen
     * you may want to stop some pending operations when active machine is not
     * active anymore
     */
    val init: (MachineLifecycle) -> CommonMachineState<G, U>
}


