package software.kalender.soruuygulamasi.Database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import software.kalender.soruuygulamasi.Helpers.Reporter;
import software.kalender.soruuygulamasi.Objects.Output;
import software.kalender.soruuygulamasi.Objects.Question;

public class Database extends SQLiteOpenHelper {
    public Database(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        Log.e("asdasd", "asdasd");

        sqLiteDatabase.execSQL("CREATE TABLE 'questions' ( " +
                " 'id' INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " 'question' TEXT DEFAULT 'Test Question Text Lenght Check And DB Controller, probly must support multipar. Well we see =', " +
                " 'answera' TEXT DEFAULT 'Test Answer AAAA', " +
                " 'answerb' TEXT DEFAULT 'Test Answer BBBB', " +
                " 'answerc' TEXT DEFAULT 'Test Answer CCCC', " +
                " 'answerd' TEXT DEFAULT 'Test Answer DDDDD', " +
                " 'category' INTEGER DEFAULT 0, " +
                " 'difficulty' INTEGER DEFAULT 0, " +
                " 'quested' INTEGER DEFAULT 0, " +
                " 'active' INTEGER DEFAULT 1 " +
                ");");


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void ekle() {
        Log.e("asdasd", "asdasd");

        SQLiteDatabase db = this.getWritableDatabase();

//
//        for (int i = 0; i< 1000000;i++){
//            if(i%100000 == 0){
//                Log.e("asdas", "10k eklendi");
//            }
//            db.execSQL("INSERT INTO questions(category) VALUES  (1)");
//        }

        db = this.getReadableDatabase();

        Cursor x = db.rawQuery("SELECT COUNT(*) FROM questions", null);
        x.moveToFirst();
        Log.e("asdasd", x.getInt(0) + "---");

        x = db.rawQuery("SELECT * FROM questions ORDER BY quested ASC LIMIT 1", null);
        x.moveToFirst();
        Log.e("asdasd", x.getInt(0) + "---");

        x = db.rawQuery("SELECT * FROM questions WHERE active = 1 ORDER BY quested ASC LIMIT 1", null);
        x.moveToFirst();
        Log.e("asdasd", x.getInt(0) + "---");

        db.execSQL("UPDATE questions SET quested= 1 WHERE id = 1 ");

        x = db.rawQuery("SELECT * FROM questions WHERE active = 1 ORDER BY quested ASC LIMIT 1", null);
        x.moveToFirst();
        Log.e("asdasd", x.getInt(0) + "---");
    }

    public Output get(String _QUERY) {
        Output output = new Output();

        SQLiteDatabase database = this.getReadableDatabase();

        try {
            Cursor cursor = database.rawQuery(_QUERY + " LIMIT 1", null);

            cursor.moveToFirst();

            output.setStatus(true);
            output.setData(cursor);
        } catch (Exception e) {
            output.setStatus(false);

            Reporter.error("DB_GET", e);
        }

        return output;
    }

    public Cursor getCursor(String _QUERY) {
        try {
            SQLiteDatabase database = this.getReadableDatabase();

            Cursor cursor = database.rawQuery(_QUERY + " LIMIT 1", null);

            cursor.moveToFirst();

            if (cursor.getCount() == 0) {
                throw new NullPointerException();
            }

            return cursor;
        } catch (Exception e) {
            Reporter.error("DB_GET_CURSOR", e);

            return null;
        }
    }

    //region Question

    public Question getQuestion(int _ID, int _CATEGORY, int _DIFFICULTY) {
        Question question = new Question();

        Cursor cursor;

        if (_ID > 0) {
            cursor = getCursor("SELECT * FROM questions WHERE id = " + _ID);
        } else if (_CATEGORY > 0) {
            if (_DIFFICULTY >= 0) {
                cursor = getCursor("SELECT * FROM questions WHERE category = " + _ID + " AND difficulty = " + _DIFFICULTY + " ORDER BY quested ASC");
            } else {
                cursor = getCursor("SELECT * FROM questions WHERE category = " + _ID + " ORDER BY quested ASC");
            }
        } else {
            cursor = getCursor("SELECT * FROM questions ORDER BY quested ASC");
        }

        try {
            if (cursor == null) {
                throw new NullPointerException();
            }

            question.setId(cursor.getInt(cursor.getColumnIndex("id")));
            question.setCategory(cursor.getInt(cursor.getColumnIndex("category")));
            question.setDifficulty(cursor.getInt(cursor.getColumnIndex("difficulty")));
            question.setQuested(cursor.getInt(cursor.getColumnIndex("quested")));
            question.setQuestion(cursor.getString(cursor.getColumnIndex("question")));
            question.setAnswerA(cursor.getString(cursor.getColumnIndex("answera")));
            question.setAnswerB(cursor.getString(cursor.getColumnIndex("answerb")));
            question.setAnswerC(cursor.getString(cursor.getColumnIndex("answerc")));
            question.setAnswerD(cursor.getString(cursor.getColumnIndex("answerd")));
        } catch (Exception e) {
            Reporter.error("DB_GET_QUESTION", e);

            return null;
        }

        return question;
    }

    //endregion
}
