package crashtest.schibsted.com.crashtestdummy;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.getsentry.raven.android.Raven;
public class MainActivity extends AppCompatActivity {

    LogWrapper log;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        log = new LogWrapper();

        Context ctx = this.getApplicationContext();
        // Use the Sentry DSN (client key) from the Project Settings page on Sentry
        String sentryDsn = "http://credentials@127.0.0.1/5";

        if (Config.ENABLE_SENTRY) Raven.init(ctx, sentryDsn);
        log.debug("onCreate() run");
    }

    @Override
    protected void onStart() {
        super.onStart();
        log.debug("onStart() run");
    }

    public void crash1(View view) {
        log.debug("crash1() run");
        throw new StupidException("Yello");
    }

    public void crash2(View view) {
        log.debug("crash 2 clicked");
        SupportClass.crash(new StupidException("G'day"));
    }

    public void crash3(View view) {
        log.debug("crash 3 clicked");
        throw new CoolException("Sup?");
    }

    public void handledCrash(View view) {
        try {
            SupportClass.crash(new StupidException("I got this"));
        } catch (Exception e) {
            Raven.capture(e);
        }
    }
    public void customEvent(View view) {
        Raven.capture("How YOU doin'?");
    }
}
