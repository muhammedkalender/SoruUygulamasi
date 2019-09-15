package software.kalender.soruuygulamasi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.drive.Drive;
import com.google.android.gms.games.AchievementsClient;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.SnapshotsClient;
import com.google.android.gms.games.snapshot.Snapshot;
import com.google.android.gms.games.snapshot.SnapshotMetadata;
import com.google.android.gms.games.snapshot.SnapshotMetadataChange;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;

import org.w3c.dom.Text;

import software.kalender.soruuygulamasi.Database.Database;
import software.kalender.soruuygulamasi.Enums.RequestCodes;
import software.kalender.soruuygulamasi.Helpers.AssetsHelper;
import software.kalender.soruuygulamasi.Helpers.Config;
import software.kalender.soruuygulamasi.Helpers.FirebaseHelper;
import software.kalender.soruuygulamasi.Helpers.PlayerHelper;
import software.kalender.soruuygulamasi.Helpers.PopUpHelper;
import software.kalender.soruuygulamasi.Helpers.Reporter;
import software.kalender.soruuygulamasi.Helpers.SoundHelper;
import software.kalender.soruuygulamasi.Helpers.StaticsHelper;
import software.kalender.soruuygulamasi.Helpers.StoreHelper;
import software.kalender.soruuygulamasi.Helpers.TextHelper;
import software.kalender.soruuygulamasi.Objects.Category;
import software.kalender.soruuygulamasi.Objects.Question;

public class MainActivity extends AppCompatActivity {
    int count = 60;
    int i;
    GoogleSignInAccount account;
    GoogleApiClient apiClient;


    public static Question question;
    public static RelativeLayout mainLayout;
    public static TextView tvLife, tvPoint;

    public static AchievementsClient achievementsClient;

    PopUpHelper popUpHelper;

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
        Statics.textHelper = new TextHelper();
        Statics.staticsHelper = new StaticsHelper();
        Statics.firebase = new FirebaseHelper();

        mainLayout = findViewById(R.id.rlMain);
        tvLife = findViewById(R.id.tvLife);
        tvPoint = findViewById(R.id.tvPoint);

        //endregion

        //region todo Test

//        Statics.player.incrementJokerHalf();
//        Statics.player.incrementJokerDouble();
//        Statics.player.incrementJokerTime();
//        Statics.player.incrementLife();
//        Statics.player.incrementLife();
//        Statics.player.incrementLife();
//        Statics.player.incrementLife();

        //endregion

        firstOpen();

        //todo   showHomePage(null);

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

        FirebaseApp.initializeApp(this);

        FirebaseHelper firebaseHelper = new FirebaseHelper();
        firebaseHelper.checkOptionalUpdate();

//        MobileAds.initialize(this, new OnInitializationCompleteListener() {
//            @Override
//            public void onInitializationComplete(InitializationStatus initializationStatus) {
//                AdView adView = new AdView(MainActivity.this);
//                adView.setAdSize(AdSize.BANNER);
//                adView.setAdUnitId("ca-app-pub-3940256099942544/6300978111");
//            }
//        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RequestCodes.PLAY_LOGIN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            account = completedTask.getResult(ApiException.class);

            if (account.getId() != null) {
                Statics.googleID = account.getId();

                FirebaseHelper firebaseHelper = new FirebaseHelper();
              // firebaseHelper.saveGame();
                firebaseHelper.loadSave();
            }

            SnapshotsClient snapshotsClient =
                    Games.getSnapshotsClient(this, account);

            snapshotsClient.open("test", true, SnapshotsClient.RESOLUTION_POLICY_MOST_RECENTLY_MODIFIED).addOnCompleteListener(new OnCompleteListener<SnapshotsClient.DataOrConflict<Snapshot>>() {
                @Override
                public void onComplete(@NonNull Task<SnapshotsClient.DataOrConflict<Snapshot>> task) {

                }
            });
            // Signed in successfully, show authenticated UI.
            Log.e("as dasd", account.getId());


            Games.getAchievementsClient(this, account)
                    .increment(getString(R.string.achievement_test), 10);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.e("ASDASD AS DASD", "signInResult:failed code=" + e.getStatusCode());

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

    public void viewDifficulty() {
        //todo
        // viewCategories();

        //todo showScreen(R.id.screenDifficulty);
        viewCategory(null);
    }

    public static void showScreen(int id) {
        mainLayout.findViewById(R.id.screenMain).setVisibility(View.INVISIBLE);
        mainLayout.findViewById(R.id.screenDifficulty).setVisibility(View.INVISIBLE);
        mainLayout.findViewById(R.id.screenCategories).setVisibility(View.INVISIBLE);
        mainLayout.findViewById(R.id.screenQuestion).setVisibility(View.INVISIBLE);
        mainLayout.findViewById(R.id.screenAfterQuest).setVisibility(View.INVISIBLE);
        mainLayout.findViewById(R.id.screenLoseGame).setVisibility(View.INVISIBLE);
        mainLayout.findViewById(R.id.screenPause).setVisibility(View.INVISIBLE);
        mainLayout.findViewById(R.id.screenStore).setVisibility(View.INVISIBLE);

        mainLayout.findViewById(id).setVisibility(View.VISIBLE);
    }

    public static void showHomePage(View view) {
        //todo

        if (Statics.player.isResuming()) {
            mainLayout.findViewById(R.id.btnResume).setAlpha(1);
            mainLayout.findViewById(R.id.btnResume).setClickable(true);
        } else {
            mainLayout.findViewById(R.id.btnResume).setAlpha(0.7f);
            mainLayout.findViewById(R.id.btnResume).setClickable(false);
        }

        showScreen(R.id.screenMain);


        updateUI();
    }

    public void viewCategory(View view) {
        //todo

        View.OnClickListener categoryClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.e("zclick)", view.getTag() + "---");
                Statics.player.setQuestionCategory((int) view.getTag());

                question = new Question();
                question.loadFromDB(Statics.player.getQuestionCategory(), Statics.player.getQuestionDifficulty());
                question.loadView(findViewById(R.id.screenQuestion));
                showScreen(R.id.screenQuestion);
            }
        };

        LinearLayout llCategories = findViewById(R.id.llCategories);
        llCategories.removeAllViews();

        Category[] categories = Statics.database.getCategories();

        View viewCategory = View.inflate(MainActivity.this, R.layout.screen_categories_object, null);
        viewCategory.setOnClickListener(categoryClickListener);
        //todo icon
        viewCategory.setTag(-1);
        ((TextView) viewCategory.findViewById(R.id.tvCategory)).setText("Karışık");
        llCategories.addView(viewCategory);

        for (int i = 0; i < categories.length; i++) {
            viewCategory = View.inflate(MainActivity.this, R.layout.screen_categories_object, null);

            viewCategory.setOnClickListener(categoryClickListener);
            //todo icon
            Log.e("asdas", "id" + categories[i].getId());
            viewCategory.setTag(categories[i].getId());
            ((TextView) viewCategory.findViewById(R.id.tvCategory)).setText(categories[i].getText());

            llCategories.addView(viewCategory);
        }

        showScreen(R.id.screenCategories);
    }

    public void firstOpen() {
        if (Statics.config.getBoolean("first_open", true)) {
            AssetsHelper assetsHelper = new AssetsHelper();
            String result = assetsHelper.read("db.sql", MainActivity.this);

            if (!result.equals("")) {
                String query[] = result.split(";");

                for (int i = 0; i < query.length; i++) {
                    Statics.database.execute(query[i]);

                    Log.e("EXECUTED", query[i]);
                }

                Statics.config.setBoolean("first_open", false);
            }
        }
    }

    public static void showNextQuestion(int remainingTime) {
        if (remainingTime > 30) {
            //3 Yıldız

            ((ImageView) mainLayout.findViewById(R.id.ivStat1)).setBackgroundColor(Color.GREEN);
            ((ImageView) mainLayout.findViewById(R.id.ivStat2)).setBackgroundColor(Color.GREEN);
            ((ImageView) mainLayout.findViewById(R.id.ivStat3)).setBackgroundColor(Color.GREEN);
        } else if (remainingTime > 15) {
            //2 Yıldız

            ((ImageView) mainLayout.findViewById(R.id.ivStat1)).setBackgroundColor(Color.GREEN);
            ((ImageView) mainLayout.findViewById(R.id.ivStat2)).setBackgroundColor(Color.GREEN);
            ((ImageView) mainLayout.findViewById(R.id.ivStat3)).setBackgroundColor(Color.RED);
        } else if (remainingTime > 5) {
            //1 Yıldız

            ((ImageView) mainLayout.findViewById(R.id.ivStat1)).setBackgroundColor(Color.GREEN);
            ((ImageView) mainLayout.findViewById(R.id.ivStat2)).setBackgroundColor(Color.RED);
            ((ImageView) mainLayout.findViewById(R.id.ivStat3)).setBackgroundColor(Color.RED);
        } else {
            //0 Yıldız

            ((ImageView) mainLayout.findViewById(R.id.ivStat1)).setBackgroundColor(Color.RED);
            ((ImageView) mainLayout.findViewById(R.id.ivStat2)).setBackgroundColor(Color.RED);
            ((ImageView) mainLayout.findViewById(R.id.ivStat3)).setBackgroundColor(Color.RED);
        }

        showScreen(R.id.screenAfterQuest);
    }

    public void loadNewQuestion(View view) {
        question = new Question();
        question.loadFromDB(Statics.player.getQuestionCategory(), Statics.player.getQuestionDifficulty());
        question.loadView(findViewById(R.id.screenQuestion));
        showScreen(R.id.screenQuestion);
    }

    public static void showLoseScreen() {
        showScreen(R.id.screenLoseGame);
    }

    public void resumeGame(View view) {
        question = new Question();
        question.loadFromDB(Statics.player.getQuestionCategory(), Statics.player.getQuestionDifficulty());
        question.loadView(mainLayout);

        showScreen(R.id.screenQuestion);
    }

    public void unpauseGame(View view) {
        //todo
        question.resumeGame();
    }

    public void buyItem(View view) {
        final int itemId = Integer.parseInt(view.getTag().toString());

        View.OnClickListener left = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //todo
                popUpHelper.hideView();
            }
        };

        View.OnClickListener right = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //todo
                StoreHelper storeHelper = new StoreHelper();
                int result = storeHelper.buy(itemId);

                switch (result) {
                    case 0:
                        break;
                    case 1:
                        popUpHelper.hideView();
                        popUpHelper = new PopUpHelper("Ürünü almak için puanınız yetersiz", "", "Tamam", null);
                        MainActivity.mainLayout.addView(popUpHelper.getViewConfirm());
                        break;
                }
            }
        };

        popUpHelper = new PopUpHelper("Ürünü almak istediğinize eminmisiniz ?", "", "Hayır", "Evet", left, right);
        MainActivity.mainLayout.addView(popUpHelper.getViewWithOptions());
    }

    public void showStore(View view) {
        //todo

        showScreen(R.id.screenStore);
    }

    @Override
    public void onBackPressed() {
        if (findViewById(R.id.screenStore).getVisibility() == View.VISIBLE) {
            showScreen(R.id.screenMain);
        } else if (findViewById(R.id.screenLoseGame).getVisibility() == View.VISIBLE) {
            showScreen(R.id.screenMain);
        } else if (findViewById(R.id.screenAfterQuest).getVisibility() == View.VISIBLE) {
            showScreen(R.id.screenMain);
        } else if (findViewById(R.id.screenCategories).getVisibility() == View.VISIBLE) {
            showScreen(R.id.screenMain);
        } else if (findViewById(R.id.screenDifficulty).getVisibility() == View.VISIBLE) {
            showScreen(R.id.screenMain);
        } else if (findViewById(R.id.screenPause).getVisibility() == View.VISIBLE) {
            question.resumeGame();
        } else if (findViewById(R.id.screenQuestion).getVisibility() == View.VISIBLE) {
            question.pauseGame();
        } else {
            super.onBackPressed();
        }
    }
}
