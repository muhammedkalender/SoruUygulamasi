package software.kalender.soruuygulamasi.Objects;

import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.games.Player;

import java.util.Random;

import software.kalender.soruuygulamasi.Enums.JokerEnum;
import software.kalender.soruuygulamasi.Helpers.PopUpHelper;
import software.kalender.soruuygulamasi.Helpers.Reporter;
import software.kalender.soruuygulamasi.MainActivity;
import software.kalender.soruuygulamasi.R;
import software.kalender.soruuygulamasi.Statics;

public class Question {
    private int id;
    private int difficulty;
    private int quested;
    private int active;
    private int category;
    private int remainingTime;

    private String question;
    private String answerA;
    private String answerB;
    private String answerC;
    private String answerD;

    private View.OnClickListener listenerAnswer;
    private View.OnClickListener listenerJoker;
    private TextView tvQuestion;
    private TextView tvTimer;

    private TextView[] tvAnswers = new TextView[4];

    private boolean onJokerDouble = false;

    private Thread timer;

    private View view;

    private boolean isFinished = false;
    private boolean isPaused = false;

    private PopUpHelper popUpHelper;

    public Question() {
        remainingTime = 60; //todo

        listenerAnswer = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //todo

                if (view == null) {
                    Log.e("aasdas", "asdasd");
                    Statics.database.incrementTimeout(getId());

                    wrongAnswer();
                } else if ((char) view.getTag() == 'A') {
                    //Doğru
                    Statics.database.incrementCorrect(getId());

                    view.setBackgroundColor(Color.GREEN);

                    correctAnswer();
                } else {
                    Statics.database.decrementCorrect(getId(), Character.toLowerCase((char) view.getTag()));

                    view.setBackgroundColor(Color.RED);
                    view.setClickable(false);

                    if (onJokerDouble) {
                        onJokerDouble = false;
                    } else {
                        for (int i = 0; i < 4; i++) {
                            if ((char) tvAnswers[i].getTag() == 'A') {
                                //todo
                                tvAnswers[i].setBackgroundColor(Color.GREEN);
                                break;
                            }
                        }

                        wrongAnswer();
                    }
                }
            }
        };

        listenerJoker = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int code = (int) view.getTag();

                switch (code) {
                    case JokerEnum.HALF:
                        jokerHalf();
                        break;
                    case JokerEnum.DOUBLE:
                        jokerDouble();
                        break;
                    case JokerEnum.TIME:
                        jokerTime();
                        break;
                    case JokerEnum.PASS:
                        jokerPass();
                        break;
                }

                view.setClickable(false);
                view.setBackgroundColor(Color.BLUE);
                //todo
            }
        };

        timer = new Thread() {
            @Override
            public void run() {
                while (remainingTime != 0) {
                    if (isFinished) {
                        return;
                    }

                    while (isPaused) {
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    //todo

                    remainingTime--;

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    tvTimer.post(new Runnable() {
                        @Override
                        public void run() {
                            tvTimer.setText(String.valueOf(remainingTime)); //todo
                        }
                    });
                }

                tvTimer.post(new Runnable() {
                    @Override
                    public void run() {
                        listenerAnswer.onClick(null);
                    }
                });

            }
        };

    }


    //region Getters & Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public int getQuested() {
        return quested;
    }

    public void setQuested(int quested) {
        this.quested = quested;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = Statics.textHelper.decode(question);
    }

    public String getAnswerA() {
        return answerA;
    }

    public void setAnswerA(String answerA) {
        this.answerA = Statics.textHelper.decode(answerA);
    }

    public String getAnswerB() {
        return answerB;
    }

    public void setAnswerB(String answerB) {
        this.answerB = Statics.textHelper.decode(answerB);
    }

    public String getAnswerC() {
        return answerC;
    }

    public void setAnswerC(String answerC) {
        this.answerC = Statics.textHelper.decode(answerC);
    }

    public String getAnswerD() {
        return answerD;
    }

    public void setAnswerD(String answerD) {
        this.answerD = Statics.textHelper.decode(answerD);
    }

    //endregion

    //region DB
    public Output loadFromDB() {
        return loadFromDB(-1, -1, -1);
    }

    public Output loadFromDB(int _ID) {
        return loadFromDB(_ID, -1, -1);
    }

    public Output loadFromDB(int _CATEGORY, int _DIFFICULTY) {
        return loadFromDB(-1, _CATEGORY, _DIFFICULTY);
    }

    public Output loadFromDB(int _ID, int _CATEGORY, int _DIFFICULTY) {
        //todo
        Output output = new Output();

        try {
            Question question = Statics.database.getQuestion(_ID, _CATEGORY, _DIFFICULTY);

            if (question == null) {
                throw new NullPointerException();
            }

            this.setId(question.getId());
            this.setDifficulty(question.getDifficulty());
            this.setCategory(question.getCategory());
            this.setQuested(question.getQuested());
            this.setQuestion(question.getQuestion());
            this.setAnswerA(question.getAnswerA());
            this.setAnswerB(question.getAnswerB());
            this.setAnswerC(question.getAnswerC());
            this.setAnswerD(question.getAnswerD());

            output.setStatus(true);
        } catch (Exception e) {
            //todo
            output.setStatus(false);
            output.setTitle("Soru Getirilemedi");
            output.setMessage("Soru veritabanından getirilemedi");
            output.setException(e);

            MainActivity.showScreen(R.id.screenMain);

            Reporter.error("TEST_CODE", e);
        }

        return output;
    }

    //endregion

    //region View

    public boolean loadView(View _VIEW) {
        view = _VIEW;

        //todo şık karıştırma

        tvQuestion = (TextView) _VIEW.findViewById(R.id.tvQuestion);
        tvAnswers[0] = (TextView) _VIEW.findViewById(R.id.tvAnswerA);
        tvAnswers[1] = (TextView) _VIEW.findViewById(R.id.tvAnswerB);
        tvAnswers[2] = (TextView) _VIEW.findViewById(R.id.tvAnswerC);
        tvAnswers[3] = (TextView) _VIEW.findViewById(R.id.tvAnswerD);

        tvQuestion.setText(getQuestion());

        //todo
        int[][] orders = new int[][]{
                new int[]{0, 1, 2, 3},
                new int[]{1, 2, 3, 0},
                new int[]{2, 1, 3, 0},
                new int[]{3, 0, 1, 2},

        };

        int index = new Random().nextInt(orders.length);

        for (int i = 0; i < 4; i++) {
            switch (orders[index][i]) {
                case 0:
                    tvAnswers[i].setText(getAnswerA());
                    tvAnswers[i].setTag('A');
                    break;
                case 1:
                    tvAnswers[i].setText(getAnswerB());
                    tvAnswers[i].setTag('B');
                    break;
                case 2:
                    tvAnswers[i].setText(getAnswerC());
                    tvAnswers[i].setTag('C');
                    break;
                case 3:
                    tvAnswers[i].setText(getAnswerD());
                    tvAnswers[i].setTag('D');
                    break;
            }

            String prefix = "A)";

            if (i == 1) {
                prefix = "B)";
            } else if (i == 2) {
                prefix = "C)";
            } else if (i == 3) {
                prefix = "D)";
            }

            tvAnswers[i].setText(prefix + tvAnswers[i].getText());
            tvAnswers[i].setBackgroundColor(Color.WHITE); //todo
            tvAnswers[i].setClickable(true);
            tvAnswers[i].setOnClickListener(listenerAnswer);
        }

        //todo icon
        ((TextView) _VIEW.findViewById(R.id.tvJokerHalf)).setText(String.valueOf(Statics.player.getJokerHalf()));
        ((LinearLayout) _VIEW.findViewById(R.id.llJokerHalf)).setOnClickListener(listenerJoker);
        ((LinearLayout) _VIEW.findViewById(R.id.llJokerHalf)).setTag(JokerEnum.HALF);

        //todo icon
        ((TextView) _VIEW.findViewById(R.id.tvJokerDouble)).setText(String.valueOf(Statics.player.getJokerDouble()));
        ((LinearLayout) _VIEW.findViewById(R.id.llJokerDouble)).setOnClickListener(listenerJoker);
        ((LinearLayout) _VIEW.findViewById(R.id.llJokerDouble)).setTag(JokerEnum.DOUBLE);

        //todo icon
        ((TextView) _VIEW.findViewById(R.id.tvJokerTime)).setText(String.valueOf(Statics.player.getJokerTime()));
        ((LinearLayout) _VIEW.findViewById(R.id.llJokerTime)).setOnClickListener(listenerJoker);
        ((LinearLayout) _VIEW.findViewById(R.id.llJokerTime)).setTag(JokerEnum.TIME);

        //todo icon
        ((TextView) _VIEW.findViewById(R.id.tvJokerPass)).setText(String.valueOf(Statics.player.getJokerPass()));
        ((LinearLayout) _VIEW.findViewById(R.id.llJokerPass)).setOnClickListener(listenerJoker);
        ((LinearLayout) _VIEW.findViewById(R.id.llJokerPass)).setTag(JokerEnum.PASS);

        MainActivity.updateUI();

        tvTimer = _VIEW.findViewById(R.id.tvTimer);
        tvTimer.setText(String.valueOf(remainingTime));

        timer.start(); //todo

        return true;
    }

    //endregion

    //region Jokers

    public void jokerHalf() {
        if (Statics.player.getJokerHalf() > 0) {
            //todo
            int[][] checkOrder = new int[][]{
                    new int[]{0, 1, 2, 3},
                    new int[]{1, 0, 2, 3},
                    new int[]{2, 0, 1, 3},
                    new int[]{3, 0, 1, 2},
                    new int[]{0, 2, 1, 3}
            };

            int index = new Random().nextInt(checkOrder.length);
            int disableCount = 0;

            for (int i = 0; i < 4; i++) {
                if ((char) tvAnswers[checkOrder[index][i]].getTag() != 'A') {
                    //todo
                    tvAnswers[checkOrder[index][i]].setBackgroundColor(Color.GRAY);
                    tvAnswers[checkOrder[index][i]].setClickable(false);

                    if (disableCount == 1) {
                        break;
                    }

                    Statics.player.decrementJokerHalf();
                    disableCount++;
                }
            }
        } else {
            //todo

            Log.e("asdas", "jokerin yok");
        }
    }

    public void jokerDouble() {
        if (Statics.player.getJokerDouble() > 0) {
            this.onJokerDouble = true;
        } else {
            //todo

            Log.e("asdas", "jok111erin yok");
        }
    }

    public void jokerPass(){
        if (Statics.player.getJokerPass() > 0) {
           correctAnswer();
        } else {
            //todo

            Log.e("asdas", "jok111erin yok");
        }
    }

    public void jokerTime() {
        if (Statics.player.getJokerTime() > 0) {
            //todo
            this.remainingTime += 15;
        } else {
            //todo

            Log.e("asdas", "jok111erin y11111ok");
        }
    }
    //endregion

    public void finishGame() {
        //todo

        Log.e("asdas", " ounbitti zenci");
    }

    public void correctAnswer() {
        //todo

        Log.e("asdas", " kazandın zenci");

        isFinished = true;

        Statics.player.incrementPoint(Statics.player.calcQuestionPoint());
        Statics.player.incrementQuestion();
        Statics.player.setResuming(true);

        MainActivity.showNextQuestion(remainingTime);

        Statics.player.saveGame();
    }

    public void wrongAnswer() {
        //todo


        if (Statics.player.getLife() > 0) {
            View.OnClickListener left = new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //todo sayfa aç

                    popUpHelper.hideView();

                    //reset todo
                    Statics.player.setResuming(false);
                    Statics.player.resetQuestion();
                    MainActivity.showLoseScreen();

                    Statics.player.saveGame();
                }
            };

            View.OnClickListener right = new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //todo
                    Statics.player.decrementLife();
                    load();
                    popUpHelper.hideView();

                    Statics.player.saveGame();
                }
            };


            popUpHelper = new PopUpHelper("Devam etmek için can kullanmak istermisiniz ?", "", "Hayır", "Evet", left, right);
            MainActivity.mainLayout.addView(popUpHelper.getView());
            isPaused = true;
        }else{
            isFinished = true;
            Statics.player.resetQuestion();
            Statics.player.setResuming(false);

            MainActivity.showLoseScreen();

            Statics.player.saveGame();
        }

        Log.e("asdas", " kaybettin zenci");

    }

    public void load() {
        this.isFinished = true;

        Question question = new Question();
        question.loadFromDB(Statics.player.getQuestionCategory(), Statics.player.getQuestionDifficulty());
        question.loadView(view);
    }

    public void pauseGame() {
        //todo
        MainActivity.showScreen(R.id.screenPause);

        isPaused = true;

    }

    public void resumeGame() {
        //todo
        MainActivity.showScreen(R.id.screenQuestion);

        isPaused = false;

    }
}
