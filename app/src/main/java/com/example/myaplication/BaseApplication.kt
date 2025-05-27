package com.example.myaplication

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp // аннотация Dependency Injection  с помощью Hilt
class BaseApplication : Application() {

}