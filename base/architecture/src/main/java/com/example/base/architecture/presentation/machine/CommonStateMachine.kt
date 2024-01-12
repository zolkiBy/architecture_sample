package com.example.base.architecture.presentation.machine

/**
 * Common state machine input - from the outside world to the current state
 * @param G UI gesture
 */
interface MachineInput<in G : Any> {
    /**
     * Updates state with UI gesture
     * @param gesture UI gesture to proceed
     */
    fun process(gesture: G)

    /**
     * Cleans-up and shuts down the state-machine
     */
    fun clear()
}

/**
 * Common state machine output - from the current state to the outside world
 * @param U UI state
 */
interface MachineOutput<G : Any, U : Any> {
    /**
     * Sets active machine state
     * @param machineState State to transition to
     */
    fun setMachineState(machineState: CommonMachineState<G, U>)

    /**
     * Updates UI state
     * @param uiState UI state
     */
    fun setUiState(uiState: U)
}

/**
 * Current public machine status
 */
interface MachineStatus<out U : Any> {
    /**
     * Checks if machine is started
     */
    fun isStarted(): Boolean

    /**
     * Current UI state
     * @return current UI state or `null` if not yet available
     */
    fun getUiState(): U
}

/**
 * Common state machine
 * @param G UI gesture
 * @param U UI state
 */
interface CommonStateMachine<G : Any, U : Any> : MachineInput<G>, MachineOutput<G, U>, MachineStatus<U> {

    /**
     * Starts the machine
     */
    fun start()

    /**
     * Base state-machine implementation
     * @param G UI gesture
     * @param U UI state
     * @param init Initial state producer
     */
    abstract class Base<G : Any, U : Any>(private val init: () -> CommonMachineState<G, U>) : CommonStateMachine<G, U> {

        /**
         * Active machine state
         */
        protected lateinit var activeState: CommonMachineState<G, U>

        /**
         * Start fuze
         */
        private var started: Boolean = false

        /**
         * Checks if machine is started
         */
        final override fun isStarted(): Boolean = started

        /**
         * Sets active machine state
         */
        final override fun setMachineState(machineState: CommonMachineState<G, U>) {
            clear()
            activeState = machineState
            startMachineState()
        }

        /**
         * Starts the machine
         */
        override fun start() {
            if (started.not()) {
                activeState = init()
                startMachineState()
                started = true
            }
        }

        /**
         * Updates state with UI gesture
         * @param gesture UI gesture to proceed
         */
        final override fun process(gesture: G) {
            activeState.process(gesture)
        }

        /**
         * Cleans-up state-machine
         */
        final override fun clear() {
            activeState.clear()
        }

        /**
         * Starts machine state
         */
        private fun startMachineState() {
            activeState.start(this)
        }
    }
}