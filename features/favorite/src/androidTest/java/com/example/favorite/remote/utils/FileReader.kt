package com.example.favorite.remote.utils

class FileReader {
    operator fun invoke(path: String) = this.javaClass.classLoader?.getResource(path)?.readText()
}