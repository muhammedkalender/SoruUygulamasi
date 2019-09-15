package software.kalender.soruuygulamasi.Helpers;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.games.Player;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;

import software.kalender.soruuygulamasi.MainActivity;
import software.kalender.soruuygulamasi.Statics;

public class FirebaseHelper {
    public static boolean loadSaveInProgress = false;


    public void checkOptionalUpdate() {
        try {
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            db.collection("optional_update").document(String.valueOf(Statics.config.getInt("optional_update", 100))).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    try {
                        if (task.isSuccessful()) {
                            if (task.getResult() == null) {
                                return;
                            }

                            String query = String.valueOf(task.getResult().get("query"));

                            if (query != null && !query.equals("") && !query.equals("null") && Statics.database.execute(query)) {
                                Statics.config.setInt("optional_update", Statics.config.getInt("optional_update", 100) + 1);

                                //todo
                            }
                        }
                    } catch (Exception e) {
                        Reporter.error("COP_OC", e);
                    }
                }
            });

//            new OnCompleteListener<QuerySnapshot>() {
//                @Override
//                public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                    if(task.isSuccessful()){
//                        Log.e("asdas", "oldu ama aga :D"+task.getResult(). toString());
//
//                        for (QueryDocumentSnapshot document : task.getResult()) {
//                            Log.e("asda", "girdi");
//                            Log.e("asdasd", document.getData().toString());
//                        }
//                    }else{
//                        Log.e("asdas"," olmadı be aga"+task.getException().getMessage());
//                    }
//                }
//            });
        } catch (Exception e) {
            Reporter.error("FB_RD", e);
        }
    }

    public void saveGame() {
        try {
            FirebaseFirestore db = FirebaseFirestore.getInstance();

            db.collection("saves").document(Statics.googleID).set(Statics.player);
            db.collection("statics").document(Statics.googleID).set(Statics.staticsHelper);
        } catch (Exception e) {
            Reporter.error("FB_SG", e);
        }
    }

    public void loadSave() {
        try {
            loadSaveInProgress = true;

            FirebaseFirestore db = FirebaseFirestore.getInstance();

            db.collection("saves").document(Statics.googleID).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    try {
                        if (task.isSuccessful() && task.getResult() != null) {
                            if((Long)task.getResult().get("lastUpdate") < Statics.player.getLastUpdate()){
                                loadSaveInProgress = false;
                                return;
                            }

                            //todo önce classa ata daha sağlıklı olur
                            Statics.config.setInt("save_life", Integer.parseInt(String.valueOf(task.getResult().get("life"))));
                            Statics.config.setLong("save_point", (Long) task.getResult().get("point"));
                            Statics.config.setLong("save_total_point", (Long) task.getResult().get("totalPoint"));
                            Statics.config.setFloat("save_point_multiplier", Float.parseFloat(String.valueOf(task.getResult().get("pointMultiplier"))));
                            Statics.config.setLong("save_spent_point", (Long) task.getResult().get("spentPoint"));
                            Statics.config.setInt("save_joker_pass", Integer.parseInt(String.valueOf(task.getResult().get("jokerPass"))));
                            Statics.config.setInt("save_joker_double", Integer.parseInt(String.valueOf(task.getResult().get("jokerDouble"))));
                            Statics.config.setInt("save_joker_half", Integer.parseInt(String.valueOf(task.getResult().get("jokerHalf"))));
                            Statics.config.setInt("save_joker_time", Integer.parseInt(String.valueOf(task.getResult().get("jokerTime"))));
                            Statics.config.setInt("save_joker_statics", Integer.parseInt(String.valueOf(task.getResult().get("jokerStatics"))));
                            Statics.config.setInt("save_question_combo", Integer.parseInt(String.valueOf(task.getResult().get("questionCombo"))));
                            Statics.config.setInt("save_question_difficulty", Integer.parseInt(String.valueOf(task.getResult().get("questionDifficulty"))));
                            Statics.config.setInt("save_question_category", Integer.parseInt(String.valueOf(task.getResult().get("questionCategory"))));
                            Statics.config.setInt("save_question_sub_category", Integer.parseInt(String.valueOf(task.getResult().get("questionSubCategory"))));
                            Statics.config.setBoolean("save_resuming", (Boolean) task.getResult().get("resuming"));
                            Statics.config.setLong("last_update", (Long)task.getResult().get("lastUpdate"));

                            loadSaveInProgress = false;

                            Statics.player.readSave();
                            MainActivity.updateUI();
                        }
                    } catch (Exception e) {
                        Reporter.error("FB_LS_G", e);
                        loadSaveInProgress = false;
                    }
                }
            });

            loadSaveInProgress = false;
        } catch (Exception e) {
            Reporter.error("FB_LS", e);
            loadSaveInProgress = false;
        }
    }
}
