package com.aizidev.examapps.util

import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class AppDispatchers @Inject constructor(val main: CoroutineDispatcher,
                     val io: CoroutineDispatcher)