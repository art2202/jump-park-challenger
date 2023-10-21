package com.example.jumpparkchallenger.data.data_source.main

import com.example.jumpparkchallenger.core.utils.SharedPreferenceHelper
import com.example.jumpparkchallenger.data.database.dao.EstablishmentDao
import com.example.jumpparkchallenger.data.database.dao.PaymentMethodDao
import com.example.jumpparkchallenger.data.database.dao.PriceDao
import com.example.jumpparkchallenger.data.database.dao.SessionDao
import com.example.jumpparkchallenger.data.database.dao.UserDao
import com.example.jumpparkchallenger.data.database.dao.ValueDetailDao
import com.example.jumpparkchallenger.data.database.dao.VehicleDao
import com.example.jumpparkchallenger.data.database.entity.EstablishmentEntity
import com.example.jumpparkchallenger.data.database.entity.SessionEntity
import com.example.jumpparkchallenger.data.database.entity.UserEntity

class MainLocalDataSourceImpl(
    private val userDao: UserDao,
    private val establishmentDao: EstablishmentDao,
    private val sessionDao: SessionDao,
    private val paymentMethodDao: PaymentMethodDao,
    private val priceDao: PriceDao,
    private val valueDetailDao: ValueDetailDao,
    private val vehicleDao: VehicleDao,
    private val sharedPreferenceHelper: SharedPreferenceHelper
) : MainLocalDataSource {
    override suspend fun getUser(): UserEntity? {
        return userDao.getUser()
    }

    override suspend fun getEstablishment(): EstablishmentEntity? {
        return establishmentDao.getEstablishment()
    }

    override suspend fun getSession(): SessionEntity? {
        return sessionDao.getSession()
    }

    override suspend fun clearDatabase() {
        sharedPreferenceHelper.clearMemory()
        establishmentDao.deleteAllEstablishments()
        sessionDao.deleteAllSessions()
        userDao.deleteAllUsers()
        paymentMethodDao.deleteAllPaymentMethods()
        valueDetailDao.deleteAllValueDetail()
        vehicleDao.deleteAllVehicles()
        priceDao.deleteAllPrices()
    }

}