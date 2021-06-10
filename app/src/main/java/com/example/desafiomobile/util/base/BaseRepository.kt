package br.com.aaf.libraryCore.base

abstract class aseRepository {

    abstract fun getScheduler(): BaseSchedulerProvider
}