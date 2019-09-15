package software.kalender.soruuygulamasi.Helpers;

import android.content.Context;

import software.kalender.soruuygulamasi.Enums.QuestionDifficulty;
import software.kalender.soruuygulamasi.MainActivity;

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
    private int jokerPas = 0;

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

    public PlayerHelper(Context context){
        //todo

        this.context = context;

    }

    public void incrementQuestion() {
        //todo
        questionCombo++;
    }

    public void resetQuestion(){
        //todo
        questionCombo = 0;
    }

    //region Point

    public long getPoint(){
        return this.point;
    }

    public void incrementPoint(long value){
        //todo
        point += value;
    }

    public void decrementPoint(long value){
        //todo

        point -= value;
    }

    //endregion

    //region Spent Point

    public long getSpentPoint(){
        return this.spentPoint;
    }

    public void incrementSpentPoint(long value){
        //todo
        spentPoint += value;
    }

    public void decrementSpentPoint(long value){
        //todo

        spentPoint -= value;
    }

    //endregion

    public long calcQuestionPoint(){
        long basePoint = 1000; //todo

        switch (questionDifficulty){
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

    public int getJokerHalf(){
        return this.jokerHalf;
    }

    public void decrementJokerHalf(){
        this.jokerHalf--;
    }

    public void incrementJokerHalf(){
        this.jokerHalf++;
    }


    public int getJokerDouble(){
        return this.jokerDouble;
    }

    public void decrementJokerDouble(){
        this.jokerDouble--;
    }

    public void incrementJokerDouble(){
        this.jokerDouble++;
    }

    public int getJokerTime(){
        return this.jokerTime;
    }

    public void decrementJokerTime(){
        this.jokerTime--;
    }

    public void incrementJokerTime(){
        this.jokerTime++;
    }

    public int getLife(){
        return this.life;
    }

    public void decrementLife(){
        this.life--;
    }

    public void incrementLife(){
        this.life++;
    }

    public void setQuestionCategory(int questionCategory){
        this.questionCategory = questionCategory;
    }

    public int getQuestionCategory(){
        return this.questionCategory;
    }

    public int getQuestionDifficulty(){
        return this.questionDifficulty;
    }

    public boolean isResuming() {
        return isResuming;
    }

    public void setResuming(boolean resuming) {
        isResuming = resuming;
    }
}
