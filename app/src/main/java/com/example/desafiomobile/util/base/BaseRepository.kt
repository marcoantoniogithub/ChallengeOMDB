package br.com.aaf.libraryCore.base

abstract class BaseRepository {

    abstract fun getScheduler(): BaseSchedulerProvider
}