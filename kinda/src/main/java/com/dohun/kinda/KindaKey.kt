package com.dohun.kinda

class KindaKey<T : Any, out R : T>(private val clazz: Class<R>) {
    fun check(value: T) = clazz.isInstance(value)
}