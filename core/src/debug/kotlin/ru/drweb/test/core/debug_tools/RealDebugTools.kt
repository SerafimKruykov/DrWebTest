package ru.drweb.test.core.debug_tools

import android.content.Context
import me.nemiron.hyperion.networkemulation.NetworkEmulatorInterceptor
import okhttp3.Interceptor
import ru.drweb.test.core.error_handling.ServerException
import java.io.IOException

class RealDebugTools(
    context: Context,
) : DebugTools {

    private val networkEmulatorInterceptor = NetworkEmulatorInterceptor(
        context,
        failureExceptionProvider = { IOException(ServerException(cause = null)) }
    )

    override val interceptors: List<Interceptor> = listOf(
        networkEmulatorInterceptor,
    )

    override fun launch() = Unit
}