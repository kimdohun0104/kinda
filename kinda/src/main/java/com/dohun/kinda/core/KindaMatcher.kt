package com.dohun.kinda.core

class KindaMatcher<T : Any, out R : T>(private val clazz: Class<R>) {
    fun match(value: T) = clazz.isInstance(value)
}