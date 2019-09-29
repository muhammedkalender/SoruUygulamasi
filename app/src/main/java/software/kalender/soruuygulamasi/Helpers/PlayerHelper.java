package software.kalender.soruuygulamasi.Helpers;

import android.content.Context;

import com.google.android.gms.games.Player;


import software.kalender.soruuygulamasi.Animations.TextAnimation;
import software.kalender.soruuygulamasi.Enums.QuestionDifficulty;
import software.kalender.soruuygulamasi.MainActivity;
import software.kalender.soruuygulamasi.Statics;

public class PlayerHelper {
    private Context context;

    private String name;

    private String email;

    private int level = 0;

    private int life = 0;

    //Point
    private long point = 0;
    private long totalPoint = 0;

    //Soruları bildikçe kazanılan puan artacak
    private float pointMultiplier = 1;

    //Total Point - Point
    private long spentPoint = 0;

    //region Jokers

    //Soruyu pas geçme [ Birşeyi seçer, yanlış bilsede devam eder ]
    private int jokerPass = 0;

    //Çift Cevap
    private int jokerDouble = 0;

    //Şık Eleme
    private int jokerHalf = 0;

    //Zaman Jokeri +15 Sn
    private int jokerTime = 0;

    //İstatistik jokeri, olasıkla şıkları gösterir. [ Sonradan toplam bilenler şeklinde olacak ] TODO
    private int jokerStatics = 0;

    //endregion

    //Kaçıncı soruda kalındığı
    private int questionCombo = 0;
    private int questionDifficulty = -1;

    //-1 => Karışık, Ana kategori karışıksa suba bakmadan geçecek
    private int questionCategory = -1;
    private int questionSubCategory;

    private boolean isResuming = false;

    private long lastUpdate;

    private TextAnimation textAnimation;

    public PlayerHelper(Context context) {
        //todo

        this.context = context;

        textAnimation = new TextAnimation();

        readSave();

    }

    public void incrementQuestion() {
        //todo
        questionCombo++;
    }

    public void resetQuestion() {
        //todo
        questionCombo = 0;
    }

    public long getTotalPoint() {
        return this.totalPoint;
    }

    public float getPointMultiplier() {
        return pointMultiplier;
    }

    public int getJokerStatics() {
        return jokerStatics;
    }

    public int getQuestionCombo() {
        return questionCombo;
    }

    public int getQuestionSubCategory() {
        return questionSubCategory;
    }


    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public int getLevel() {
        return level;
    }

    public long getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(long lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    //region Point

    public long getPoint() {
        return this.point;
    }

    public void incrementPoint(long value) {
        //todo
        textAnimation.animate(MainActivity.tvPoint, this.point, this.point + value);

        point += value;
        totalPoint += value;
    }

    public void decrementPoint(long value) {
        //todo

        point -= value;
        incrementSpentPoint(value);
    }

    //endregion

    //region Spent Point

    public long getSpentPoint() {
        return this.spentPoint;
    }

    public void incrementSpentPoint(long value) {
        //todo
        spentPoint += value;
    }

    public void decrementSpentPoint(long value) {
        //todo

        spentPoint -= value;
    }

    //endregion

    public long calcQuestionPoint() {
        long basePoint = 1000; //todo

        switch (questionDifficulty) {
            case QuestionDifficulty.MEDIUM:
                pointMultiplier = 1.5f;
                break;
            case QuestionDifficulty.HARD:
                pointMultiplier = 2;
                break;
            default:
                pointMultiplier = 1;
                break;
        }

        basePoint *= pointMultiplier;

        return basePoint;
    }

    public int getJokerHalf() {
        return this.jokerHalf;
    }

    public void decrementJokerHalf() {
        this.jokerHalf--;
    }

    public void incrementJokerHalf() {
        this.jokerHalf++;
    }

    public int getJokerPass() {
        return this.jokerPass;
    }

    public void decrementJokerPass() {
        this.jokerPass--;
    }

    public void incrementJokerPass() {
        this.jokerPass++;
    }

    public int getJokerDouble() {
        return this.jokerDouble;
    }

    public void decrementJokerDouble() {
        this.jokerDouble--;
    }

    public void incrementJokerDouble() {
        this.jokerDouble++;
    }

    public int getJokerTime() {
        return this.jokerTime;
    }

    public void decrementJokerTime() {
        this.jokerTime--;
    }

    public void incrementJokerTime() {
        this.jokerTime++;
    }

    public int getLife() {
        return this.life;
    }

    public void decrementLife() {
        this.life--;
    }

    public void incrementLife() {
        this.life++;
    }

    public void setQuestionCategory(int questionCategory) {
        this.questionCategory = questionCategory;
    }

    public int getQuestionCategory() {
        return this.questionCategory;
    }

    public int getQuestionDifficulty() {
        return this.questionDifficulty;
    }

    public boolean isResuming() {
        return isResuming;
    }

    public void setResuming(boolean resuming) {
        isResuming = resuming;
    }

    public void saveGame() {
        //todo

        Statics.config.setInt("save_life", life);
        Statics.config.setLong("save_point", point);
        Statics.config.setLong("save_total_point", totalPoint);
        Statics.config.setFloat("save_point_multiplier", pointMultiplier);
        Statics.config.setLong("save_spent_point", spentPoint);
        Statics.config.setInt("save_joker_pass", jokerPass);
        Statics.config.setInt("save_joker_double", jokerDouble);
        Statics.config.setInt("save_joker_half", jokerHalf);
        Statics.config.setInt("save_joker_time", jokerTime);
        Statics.config.setInt("save_joker_statics", jokerStatics);
        Statics.config.setInt("save_question_combo", questionCombo);
        Statics.config.setInt("save_question_difficulty", questionDifficulty);
        Statics.config.setInt("save_question_category", questionCategory);
        Statics.config.setInt("save_question_sub_category", questionSubCategory);
        Statics.config.setBoolean("save_resuming", isResuming);
        Statics.config.setLong("last_update", lastUpdate);

        Statics.firebase.saveGame();
    }

    public boolean readSave() {
        //todo
        try {
            life = Statics.config.getInt("save_life", life);
            point = Statics.config.getLong("save_point", point);
            totalPoint = Statics.config.getLong("save_total_point", totalPoint);
            pointMultiplier = Statics.config.getFloat("save_point_multiplier", pointMultiplier);
            spentPoint = Statics.config.getLong("save_spent_point", spentPoint);
            jokerPass = Statics.config.getInt("save_joker_pass", jokerPass);
            jokerDouble = Statics.config.getInt("save_joker_double", jokerDouble);
            jokerHalf = Statics.config.getInt("save_joker_half", jokerHalf);
            jokerTime = Statics.config.getInt("save_joker_time", jokerTime);
            jokerStatics = Statics.config.getInt("save_joker_statics", jokerStatics);
            questionCombo = Statics.config.getInt("save_question_combo", questionCombo);
            questionDifficulty = Statics.config.getInt("save_question_difficulty", questionDifficulty);
            questionCategory = Statics.config.getInt("save_question_category", questionCategory);
            questionSubCategory = Statics.config.getInt("save_question_sub_category", questionSubCategory);
            isResuming = Statics.config.getBoolean("save_resuming", isResuming);
            lastUpdate = Statics.config.getLong("last_update", System.currentTimeMillis() / 1000);

            return true;
        } catch (Exception e) {
            Reporter.error("LOAD_SAVE", e);
            return false;
        }
    }
}
