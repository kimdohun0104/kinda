package com.dohun.kinda.core

class KindaKey<T : Any, out R : T>(private val clazz: Class<R>) {
    fun check(value: T) = clazz.isInstance(value)
}