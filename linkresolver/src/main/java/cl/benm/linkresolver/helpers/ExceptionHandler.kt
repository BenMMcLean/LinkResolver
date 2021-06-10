package cl.benm.linkresolver.helpers

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import cl.benm.androidlogger.AndroidLogger
import cl.benm.androidlogger.Logger

interface ExceptionHandler {

    fun catch(link: String, e: Exception, context: Context)

}

class DefaultExceptionHandler(
    private val logger: Logger = AndroidLogger()
): ExceptionHandler {

    override fun catch(link: String, e: Exception, context: Context) {
        logger.d("LinkResolver", "Failed to open link: $link", e)
        Handler(Looper.getMainLooper()).post {
            Toast.makeText(context, "Failed to open link", Toast.LENGTH_SHORT).show()
        }
    }
}