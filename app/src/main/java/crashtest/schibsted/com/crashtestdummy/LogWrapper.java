package crashtest.schibsted.com.crashtestdummy;

import com.getsentry.raven.event.Breadcrumb;
import com.getsentry.raven.event.BreadcrumbBuilder;

/**
 * Wrap logcat and log to service, if applicable
 */

public class LogWrapper {
    public void debug(String s) {
        android.util.Log.d("CrashApp", s);
        if (Config.ENABLE_SENTRY) {
            new BreadcrumbBuilder()
                    .setLevel(Breadcrumb.Level.DEBUG)
                    .setMessage(s)
                    .build();
        }
    }
}
