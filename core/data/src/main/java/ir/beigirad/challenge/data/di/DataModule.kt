package ir.beigirad.challenge.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ir.beigirad.challenge.data.repository.TransferRepository
import ir.beigirad.challenge.data.repository.TransferRepositoryImpl

/**
 * Created by Farhad Beigirad on 6/24/23.
 */
@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun bindTransferRepository(impl: TransferRepositoryImpl): TransferRepository
}