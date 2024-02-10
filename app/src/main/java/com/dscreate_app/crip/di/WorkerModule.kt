package com.dscreate_app.crip.di

import androidx.work.ListenableWorker
import com.dscreate_app.crip.data.workers.ChildWorkerFactory
import com.dscreate_app.crip.data.workers.RefreshDataWorker
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface WorkerModule {

    @Binds
    @IntoMap
    @WorkerKey(RefreshDataWorker::class)
    fun bindRefreshDataWorkerFactory(worker: RefreshDataWorker.Factory): ChildWorkerFactory
}