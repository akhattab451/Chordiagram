package com.akhttp.chordiagram.util

sealed class Resource<T>(val data: T? = null) {
     class Loading<T>(): Resource<T>(null)
     class Loaded<T>(data: T): Resource<T>(data)
}