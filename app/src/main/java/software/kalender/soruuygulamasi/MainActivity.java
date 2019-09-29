package software.kalender.soruuygulamasi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
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

import java.util.Random;

import software.kalender.soruuygulamasi.Animations.TextAnimation;
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


    public static RewardedVideoAd mRewardedVideoAd;


    public static Question question;
    public static RelativeLayout mainLayout;
    public static TextView tvLife, tvPoint;
    public static ImageView ivPause;

    public static AchievementsClient achievementsClient;

    private CountDownTimer cdtPause;

    public static boolean isLiveAd = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loadGame();
        //region Initalize statics


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


        //todo   showHomePage(null);

//        Question question = new Question();
//        question.loadFromDB(-1, -1);
//
//        question.loadView(findViewById(R.id.rlScreenQuestion));


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

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RequestCodes.PLAY_LOGIN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);

            setContentView(R.layout.activity_main);

            mainLayout = findViewById(R.id.rlMain);
            tvLife = findViewById(R.id.tvLife);
            tvPoint = findViewById(R.id.tvPoint);
            ivPause = findViewById(R.id.ivPause);

            firstOpen();

            updateUI();

            //todo
            mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(this);
            mRewardedVideoAd.setRewardedVideoAdListener(new RewardedVideoAdListener() {
                @Override
                public void onRewardedVideoAdLoaded() {
                    findViewById(R.id.llLife).setEnabled(true);
                }

                @Override
                public void onRewardedVideoAdOpened() {
                    question.pauseGame();
                }

                @Override
                public void onRewardedVideoStarted() {
                }

                @Override
                public void onRewardedVideoAdClosed() {
                    question.resumeGame();
                }

                @Override
                public void onRewarded(RewardItem rewardItem) {
                    if (isLiveAd) {
                        Statics.player.incrementLife();
                        Statics.player.saveGame();

                        isLiveAd = false;

                        updateUI();

                        Statics.popUp = new PopUpHelper("Bir can kazandın !!!", "", "Tamam", null);
                        MainActivity.mainLayout.addView(Statics.popUp.getViewConfirm());
                    } else {
                        //todo

                        String message = "";

                        int random = Statics.random.nextInt(101);

                        if (random <= Consts.CHANGE_JOKER_HALF) {
                            //[0, 45]
                            Statics.player.incrementJokerHalf();

                            message = "Yarı Yarıya";
                        } else if (random <= Consts.CHANGE_JOKER_HALF + Consts.CHANGE_JOKER_DOUBLE) {
                            //[46, 85]
                            Statics.player.incrementJokerDouble();

                            message = "Çift Cevap";
                        } else if (random <= Consts.CHANGE_JOKER_HALF + Consts.CHANGE_JOKER_DOUBLE + Consts.CHANGE_JOKER_TIME) {
                            //[86, 95]
                            Statics.player.incrementJokerTime();

                            message = "Zaman";
                        } else {
                            //[96, 100]
                            Statics.player.incrementJokerPass();

                            message = "Pas Geçme";
                        }

                        Statics.player.saveGame();

                        question.updateUI();
                        updateUI();

                        Statics.popUp.hideView();

                        Statics.popUp = new PopUpHelper( message+" Jokeri Kazandın", "", "Tamam" ,null);
                        MainActivity.mainLayout.addView(Statics.popUp.getViewConfirm());
                    }
                }

                @Override
                public void onRewardedVideoAdLeftApplication() {

                }

                @Override
                public void onRewardedVideoAdFailedToLoad(int i) {
                    findViewById(R.id.llLife).setEnabled(false);
                }

                @Override
                public void onRewardedVideoCompleted() {

                }
            });

            //todo
            mRewardedVideoAd.loadAd("ca-app-pub-3940256099942544/5224354917",
                    new AdRequest.Builder().build());

            findViewById(R.id.llLife).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    View.OnClickListener clickRight = new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Statics.popUp.hideView();

                            if (mRewardedVideoAd.isLoaded()) {
                                isLiveAd = true;

                                mRewardedVideoAd.show();
                            }

                        }
                    };

                    Statics.popUp = new PopUpHelper("Can kazanmak için reklam izleyin !!!", "", "Sonra", "Hemen İzle !", null, clickRight);
                    MainActivity.mainLayout.addView(Statics.popUp.getViewWithOptions());
                }
            });

            AdRequest adRequest = new AdRequest.Builder().build();
            ((AdView) findViewById(R.id.adView)).loadAd(adRequest);
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

        ivPause.setVisibility(View.INVISIBLE);

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
                ivPause.setVisibility(View.VISIBLE);
                ivPause.setImageResource(R.drawable.ic_pause_circle_outline);
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
        int drawStar = 0;

        if (remainingTime > Consts.STAR_THREE) {
            //3 Yıldız

            drawStar = 3;
        } else if (remainingTime > Consts.STAR_TWO) {
            //2 Yıldız

            drawStar = 2;
        } else if (remainingTime > Consts.STAR_ONE) {
            //1 Yıldız

            drawStar = 1;
        } else {
            //0 Yıldız

            drawStar = 0;
        }

        Animation fadeIn = new AlphaAnimation(0, 1);
        fadeIn.setInterpolator(new DecelerateInterpolator()); //add this
        fadeIn.setDuration(Consts.ANIMATION_DURATION_STAR_FADE * 1000);

        if (drawStar == 0) {
            ((ImageView) mainLayout.findViewById(R.id.ivStar1)).setImageResource(R.drawable.ic_star_border);
            ((ImageView) mainLayout.findViewById(R.id.ivStar2)).setImageResource(R.drawable.ic_star_border);
            ((ImageView) mainLayout.findViewById(R.id.ivStar3)).setImageResource(R.drawable.ic_star_border);
        }

        if (drawStar > 0) {
            ((ImageView) mainLayout.findViewById(R.id.ivStar1)).setImageResource(R.drawable.ic_star);
            ((ImageView) mainLayout.findViewById(R.id.ivStar1)).setAnimation(fadeIn);

            ((ImageView) mainLayout.findViewById(R.id.ivStar2)).setImageResource(R.drawable.ic_star_border);
            ((ImageView) mainLayout.findViewById(R.id.ivStar3)).setImageResource(R.drawable.ic_star_border);
        }

        if (drawStar > 1) {
            ((ImageView) mainLayout.findViewById(R.id.ivStar2)).setImageResource(R.drawable.ic_star);
            ((ImageView) mainLayout.findViewById(R.id.ivStar2)).setAnimation(fadeIn);

            ((ImageView) mainLayout.findViewById(R.id.ivStar3)).setImageResource(R.drawable.ic_star_border);
        }

        if (drawStar == 3) {
            ((ImageView) mainLayout.findViewById(R.id.ivStar3)).setImageResource(R.drawable.ic_star);
            ((ImageView) mainLayout.findViewById(R.id.ivStar3)).setAnimation(fadeIn);
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

        ivPause.setImageResource(R.drawable.ic_pause_circle_outline);

        cdtPause.cancel();
    }

    public void buyItem(View view) {
        final int itemId = Integer.parseInt(view.getTag().toString());

        View.OnClickListener left = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //todo
                Statics.popUp.hideView();
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
                        Statics.popUp.hideView();
                        Statics.popUp = new PopUpHelper("Ürünü almak için puanınız yetersiz", "", "Tamam", null);
                        MainActivity.mainLayout.addView(Statics.popUp.getViewConfirm());
                        break;
                }
            }
        };

        Statics.popUp = new PopUpHelper("Ürünü almak istediğinize eminmisiniz ?", "", "Hayır", "Evet", left, right);
        MainActivity.mainLayout.addView(Statics.popUp.getViewWithOptions());
    }

    public void showStore(View view) {
        //todo

        showScreen(R.id.screenStore);
    }

    public void pauseGame(View v) {
        question.pauseGame();
        ivPause.setImageResource(R.drawable.ic_play_circle_outline);
        ivPause.setVisibility(View.VISIBLE);

        cdtPause.start();
    }

    @Override
    public void onBackPressed() {
        if (tvLife == null) {
            //todo
            super.onBackPressed();

            return;
        }

        if (Statics.popUp != null && Statics.popUp.isShow()) {
            Statics.popUp.forceNegative();
        } else if (findViewById(R.id.screenStore).getVisibility() == View.VISIBLE) {
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
            pauseGame(null);
        } else {
            super.onBackPressed();
        }
    }

    public static void jokerPopup() {

        //todo negatie

        String message = Statics.context.getString(R.string.popup_video_reward, Consts.CHANGE_JOKER_HALF, Consts.CHANGE_JOKER_DOUBLE, Consts.CHANGE_JOKER_TIME, Consts.CHANGE_JOKER_PASS);

        View.OnClickListener clickRight = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isLiveAd = false;

                if (mRewardedVideoAd.isLoaded()) {
                    mRewardedVideoAd.show();
                }
            }
        };

        Statics.popUp = new PopUpHelper(message, "", "Daha Sonra", "Hemen İzle !!!", null, clickRight);
        MainActivity.mainLayout.addView(Statics.popUp.getViewWithOptions());
    }

    private void loadGame() {
        setContentView(R.layout.activity_loading);

        Statics.context = MainActivity.this;

        Statics.database = new Database(this, "questions", null, 1);
        Statics.config = new Config(this);
        Statics.soundHelper = new SoundHelper(this);
        Statics.player = new PlayerHelper(this);
        Statics.textHelper = new TextHelper();
        Statics.staticsHelper = new StaticsHelper();
        Statics.firebase = new FirebaseHelper();

        setVolumeControlStream(AudioManager.STREAM_MUSIC);

        GoogleSignInOptions.Builder builder = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN);
        builder.requestScopes(Games.SCOPE_GAMES);
        //builder.requestScopes(Drive.SCOPE_APPFOLDER);
        //builder.requestScopes(Games.SCOPE_GAMES_LITE);
        builder.requestEmail();

        GoogleSignInOptions gso = builder.build();

        GoogleSignInClient mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RequestCodes.PLAY_LOGIN);

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

        cdtPause = new CountDownTimer(Consts.PAUSE_TIME * 1000, 5000) {
            @Override
            public void onTick(long l) {
            }

            @Override
            public void onFinish() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        showHomePage(null);
                    }
                });
            }
        };
    }
}
