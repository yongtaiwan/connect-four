package com.company

import java.io.Serializable
import java.io.ByteArrayInputStream
import java.io.ObjectOutputStream
import java.io.ObjectInputStream
import java.io.ByteArrayOutputStream

fun <T : Serializable> deepCopyNullable(obj: T?): T? {
    if (obj == null) return null
    return deepCopy(obj)
}

fun <T : Serializable> deepCopy(obj: T): T {
    val baos = ByteArrayOutputStream()
    val oos  = ObjectOutputStream(baos)
    oos.writeObject(obj)
    oos.close()
    val bais = ByteArrayInputStream(baos.toByteArray())
    val ois  = ObjectInputStream(bais)
    @Suppress("unchecked_cast")
    return ois.readObject() as T
}
