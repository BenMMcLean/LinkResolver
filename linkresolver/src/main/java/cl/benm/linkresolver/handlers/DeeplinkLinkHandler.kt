package cl.benm.linkresolver.handlers

import android.content.Context
import android.net.Uri
import android.os.Handler
import android.os.Looper
import androidx.navigation.NavController
import cl.benm.linkresolver.MatchingLinkHandler
import java.util.concurrent.Executor

abstract class DeeplinkLinkHandler(
    private val navController: NavController
): MatchingLinkHandler {

    override fun handle(link: String, context: Context): Boolean {
        if (!matching(link, context)) return true

        Handler(Looper.getMainLooper()).post {
            navController.navigate(Uri.parse(link))
        }

        return false
    }

}

class PackageDeeplinkHandler(
    navController: NavController
): DeeplinkLinkHandler(
    navController
) {

    override fun matching(link: String, context: Context): Boolean {
        return link.startsWith(context.packageName)
    }

}