package dohun.kim.kinda.kinda_core

import java.util.concurrent.atomic.AtomicBoolean

class Event<T>(
    private val data: T? = null
) {
    private val isPending = AtomicBoolean(true)

    fun getData(): T? {
        if (isPending.getAndSet(false)) {
            return data
        }
        return null
    }
}