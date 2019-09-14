package software.kalender.soruuygulamasi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.games.AchievementsClient;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.SnapshotsClient;
import com.google.android.gms.games.snapshot.Snapshot;
import com.google.android.gms.games.snapshot.SnapshotMetadata;
import com.google.android.gms.games.snapshot.SnapshotMetadataChange;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import software.kalender.soruuygulamasi.Database.Database;
import software.kalender.soruuygulamasi.Enums.RequestCodes;
import software.kalender.soruuygulamasi.Helpers.Config;
import software.kalender.soruuygulamasi.Helpers.PlayerHelper;
import software.kalender.soruuygulamasi.Helpers.Reporter;
import software.kalender.soruuygulamasi.Helpers.SoundHelper;

public class MainActivity extends AppCompatActivity {
    int count = 60;
    int i;
    GoogleSignInAccount account;
    GoogleApiClient apiClient;


    public static RelativeLayout mainLayout;
    public static TextView tvLife, tvPoint;

    public static AchievementsClient achievementsClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //region Initalize statics

        Statics.context = getApplicationContext();

        Statics.database = new Database(this, "questions", null, 1);
        Statics.config = new Config(this);
        Statics.soundHelper = new SoundHelper(this);
        Statics.player = new PlayerHelper(this);

        mainLayout = findViewById(R.id.rlMain);
        tvLife = findViewById(R.id.tvLife);
        tvPoint = findViewById(R.id.tvPoint);


        //endregion

        //region todo Test

        Statics.player.incrementJokerHalf();
        Statics.player.incrementJokerDouble();
        Statics.player.incrementJokerTime();
        Statics.player.incrementLife();
        Statics.player.incrementLife();
        Statics.player.incrementLife();
        Statics.player.incrementLife();

        //endregion

        //todo
        Statics.player.setResuming(true);

        if(Statics.player.isResuming()){
            mainLayout.findViewById(R.id.btnResume).setAlpha(1);
            mainLayout.findViewById(R.id.btnResume).setClickable(true);
        }

//        Question question = new Question();
//        question.loadFromDB(-1, -1);
//
//        question.loadView(findViewById(R.id.rlScreenQuestion));


        GoogleSignInOptions.Builder builder = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN);
        builder.requestScopes(Games.SCOPE_GAMES);
        //builder.requestScopes(Drive.SCOPE_APPFOLDER);
        //builder.requestScopes(Games.SCOPE_GAMES_LITE);
        builder.requestEmail();
        GoogleSignInOptions gso = builder.build();
        GoogleSignInClient mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RequestCodes.PLAY_LOGIN);


        //snapshotsClient.commitAndClose()
//
//        apiClient = new GoogleApiClient.Builder(this)
//                .addApi(Games.API)
//                .addScope(Games.SCOPE_GAMES)
//                .enableAutoManage(this,   new GoogleApiClient.OnConnectionFailedListener() {
//                    @Override
//                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
//                        Log.e("test", "Could not connect to Play games services");
//                        finish();
//                    }
//                }).build();

        updateUI();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RequestCodes.PLAY_LOGIN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private Task<SnapshotMetadata> writeSnapshot(Snapshot snapshot,
                                                 byte[] data) {

        // Set the data payload for the snapshot
        snapshot.getSnapshotContents().writeBytes(data);

        // Create the change operation
        SnapshotMetadataChange metadataChange = new SnapshotMetadataChange.Builder()

                .build();

        SnapshotsClient snapshotsClient =
                Games.getSnapshotsClient(this, account);

        // Commit the operation
        return snapshotsClient.commitAndClose(snapshot, metadataChange);
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            account = completedTask.getResult(ApiException.class);

            SnapshotsClient snapshotsClient =
                    Games.getSnapshotsClient(this, account);

            snapshotsClient.open("test", true, SnapshotsClient.RESOLUTION_POLICY_MOST_RECENTLY_MODIFIED).addOnCompleteListener(new OnCompleteListener<SnapshotsClient.DataOrConflict<Snapshot>>() {
                @Override
                public void onComplete(@NonNull Task<SnapshotsClient.DataOrConflict<Snapshot>> task) {

                }
            });
            // Signed in successfully, show authenticated UI.
            Log.e("as dasd", account.getId());


//            Games.getAchievementsClient(this, account)
//                    .increment(getString(R.string.achievement_test), 1);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.e("ASDASD AS DASD", "signInResult:failed code=" + e.getStatusCode());

        }
    }

    public void test(View v) {

        achievementsClient = Games.getAchievementsClient(MainActivity.this, account);

        findViewById(R.id.btnAchievements).setClickable(true);
        //.submitScore(getString(R.string.leaderboard_test_skoru), 1337);

        // LeaderboardsClient achievementsClient =    Games.getLeaderboardsClient(MainActivity.this, account);
        //Games.getAchievementsClient(MainActivity.this, account).increment(getString(R.string.achievement_test_2), 10);
//        achievementsClient.submitScore(getString(R.string.leaderboard_test_skoru), 1500);
////        Games.getLeaderboardsClient(this, GoogleSignIn.getLastSignedInAccount(this)).submitScoreImmediate(getString(R.string.leaderboard_test_skoru), 1337).addOnCompleteListener(new OnCompleteListener<ScoreSubmissionData>() {
////            @Override
////            public void onComplete(@NonNull Task<ScoreSubmissionData> task) {
////                Log.e("asdas", task.isSuccessful()?"e":"h");
////            }
////        });
//        achievementsClient.submitScore(getString(R.string.leaderboard_oilayyy), 1500);


    }

    public void call(View v) {
        test(v);
        return;
        //  final TextView timer = ((TextView) findViewById(R.id.timer));
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for (i = 10; i > 0; i--) {
//                    try {
//                        Log.e("asdasd", "zxxxxxxxxxxxxx");
//                        Thread.sleep(1000);
//
//
//                               timer.setText("Kalan : "+i);
//
//                    } catch (InterruptedException e) {
//                        Log.e("asdasd",e.getMessage()+"");
//                    }
//                }
//
//                Log.e("asdasdas", "asdasdasd");
//            }
//        }).run();
//        AchievementsClient achievementsClient =    Games.getAchievementsClient(MainActivity.this, account);
//        //Games.getAchievementsClient(MainActivity.this, account).increment(getString(R.string.achievement_test_2), 10);
//       achievementsClient.unlock(getString(R.string.achievement_test));
//
//        new CountDownTimer(5000, 1000) {
//
//            public void onTick(long millisUntilFinished) {
//                timer.setText("seconds remaining: " + millisUntilFinished / 1000);
//            }
//
//            public void onFinish() {
//                Games.getAchievementsClient(MainActivity.this, account).getAchievementsIntent().addOnSuccessListener(new OnSuccessListener<Intent>() {
//                    @Override
//                    public void onSuccess(Intent intent) {
//                        startActivityForResult(intent, 123);
//                    }
//                });;
//                //   mTextField.setText("done!");
//            }
//        }.start();
    }

    private void signInSilently() {

        Log.e("asdasd", "başladı");
        GoogleSignInOptions signInOptions = GoogleSignInOptions.DEFAULT_GAMES_SIGN_IN;
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if (GoogleSignIn.hasPermissions(account, signInOptions.getScopeArray())) {
            // Already signed in.
            // The signed in account is stored in the 'account' variable.
            GoogleSignInAccount signedInAccount = account;

            Log.e("loginli", signedInAccount.getId());
        } else {

            Log.e("adas,", "else düştü");
            // Haven't been signed-in before. Try the silent sign-in first.
            GoogleSignInClient signInClient = GoogleSignIn.getClient(this, signInOptions);
            signInClient
                    .silentSignIn()
                    .addOnCompleteListener(
                            this,
                            new OnCompleteListener<GoogleSignInAccount>() {
                                @Override
                                public void onComplete(@NonNull Task<GoogleSignInAccount> task) {
                                    if (task.isSuccessful()) {
                                        // The signed in account is stored in the task's result.
                                        GoogleSignInAccount signedInAccount = task.getResult();

                                        Log.e("yeni", signedInAccount.getId());

                                    } else {
                                        Log.e("asdas", "hop");
                                        // Player will need to sign-in explicitly using via UI.
                                        // See [sign-in best practices](http://developers.google.com/games/services/checklist) for guidance on how and when to implement Interactive Sign-in,
                                        // and [Performing Interactive Sign-in](http://developers.google.com/games/services/android/signin#performing_interactive_sign-in) for details on how to implement
                                        // Interactive Sign-in.
                                    }
                                }
                            });
        }
    }

    public static void updateUI() {
        //todo



        tvLife.setText(String.valueOf(Statics.player.getLife()));
        tvPoint.setText(String.valueOf(Statics.player.getPoint()));

    }

    public void viewSettings(View view) {
        //todo
    }

    public void viewLeaderBoard(View view) {
        try {
            Games.getLeaderboardsClient(MainActivity.this, account).getAllLeaderboardsIntent().addOnSuccessListener(new OnSuccessListener<Intent>() {
                @Override
                public void onSuccess(Intent intent) {
                    startActivityForResult(intent, RequestCodes.VIEW_LEEADERBOARDS);
                }
            });
        } catch (Exception ex) {
            Reporter.error("M.ViewLB", ex);
        }
    }

    public void viewAchievement(View view) {
        try {
            Games.getAchievementsClient(MainActivity.this, account).getAchievementsIntent().addOnSuccessListener(new OnSuccessListener<Intent>() {
                @Override
                public void onSuccess(Intent intent) {
                    startActivityForResult(intent, RequestCodes.VIEW_ACHIEVEMENT);
                }
            });
        } catch (Exception ex) {
            Reporter.error("M.ViewA", ex);
        }
    }

    public void newGame(View view) {
        viewDifficulty();
    }

    public void viewDifficulty(){
        //todo
       // viewCategories();
    }
}
