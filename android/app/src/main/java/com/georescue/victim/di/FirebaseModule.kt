package com.georescue.victim.di

import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
//import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
//import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.database
//import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule {

    @Provides
    @Singleton
    fun provideFirebaseAuth(): FirebaseAuth = Firebase.auth

    @Provides
    @Singleton
    fun provideFirebaseFirestore(): FirebaseFirestore = Firebase.firestore

    @Provides
    @Singleton
    fun provideFirebaseDatabase(): FirebaseDatabase = Firebase.database("https://geo-rescue-ai-default-rtdb.asia-southeast1.firebasedatabase.app/")
}