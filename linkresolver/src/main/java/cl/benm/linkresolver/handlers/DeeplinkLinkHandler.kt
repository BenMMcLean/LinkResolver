package cl.benm.linkresolver.handlers

import android.content.Context
import android.net.Uri
import androidx.navigation.NavController
import cl.benm.linkresolver.MatchingLinkHandler
import java.util.concurrent.Executor

abstract class DeeplinkLinkHandler(
    private val navController: NavController,
    private val uiExecutor: Executor
): MatchingLinkHandler {

    override fun handle(link: String, context: Context): Boolean {
        if (!matching(link, context)) return true

        uiExecutor.execute {
            navController.navigate(Uri.parse(link))
        }

        return false
    }

}

class PackageDeeplinkHandler(
    navController: NavController,
    uiExecutor: Executor
): DeeplinkLinkHandler(
    navController, uiExecutor
) {

    override fun matching(link: String, context: Context): Boolean {
        return link.startsWith(context.packageName)
    }

}