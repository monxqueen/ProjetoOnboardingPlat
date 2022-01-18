package com.example.common.utils

class FileReader {
    operator fun invoke(path: String) = this.javaClass.classLoader?.getResource(path)?.readText()
}