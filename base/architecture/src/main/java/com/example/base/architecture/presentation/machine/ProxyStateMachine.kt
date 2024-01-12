package com.example.base.architecture.presentation.machine

/**
 * State-machine used in proxies
 * @param uiState Initial UI state
 * @param init Initializes the machine
 * @param onUiStateChange Called when UI state changes
 */
internal class ProxyStateMachine<G : Any, U : Any>(
    private var uiState: U,
    init: () -> CommonMachineState<G, U>,
    private val onUiStateChange: (U) -> Unit
) : CommonStateMachine.Base<G, U>(init) {
    /**
     * Current UI state
     * @return current UI state or `null` if not yet available
     */
    override fun getUiState(): U = uiState

    /**
     * Updates UI state
     * @param uiState UI state
     */
    override fun setUiState(uiState: U) {
        this.uiState = uiState
        onUiStateChange(uiState)
    }
}