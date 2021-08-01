package br.com.example.eitherdatatype.infrastructure.protocols

interface InputBoundaryAdapter<out T> {
    fun toInputBoundary(): T
}