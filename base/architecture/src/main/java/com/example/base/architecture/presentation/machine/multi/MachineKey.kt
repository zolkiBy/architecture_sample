package com.example.base.architecture.presentation.machine.multi

/**
 * Machine key
 * Identifies the machine in multi-machine bundles
 * @param G Gesture type
 * @param U UI-state type
 * @param tag An extra string to distinguish one key from the other
 */
abstract class MachineKey<G: Any, U: Any>(val tag: String? = null) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as MachineKey<*, *>

        return tag == other.tag
    }

    override fun hashCode(): Int {
        return tag?.hashCode() ?: 0
    }
}
