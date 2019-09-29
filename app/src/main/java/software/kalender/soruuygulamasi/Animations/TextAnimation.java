package software.kalender.soruuygulamasi.Animations;

import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.util.Log;
import android.widget.TextView;

import software.kalender.soruuygulamasi.Consts;
import software.kalender.soruuygulamasi.Helpers.Reporter;

public class TextAnimation {
    public void animate(final TextView view, long start, long end) {
        if (view == null) {
            return;
        }

        try {
            //https://stackoverflow.com/a/33841596
            ValueAnimator animator = new ValueAnimator();
            animator.setObjectValues(start, end);
            //https://www.codota.com/code/java/methods/android.animation.ValueAnimator/setEvaluator
            animator.setEvaluator(new TypeEvaluator<Long>() {
                @Override
                public Long evaluate(float fraction, Long startValue, Long endValue) {
                    return (long) Math.round(startValue + (endValue - startValue) * fraction);
                }
            });
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                public void onAnimationUpdate(ValueAnimator animation) {
                    view.setText(String.valueOf(animation.getAnimatedValue()));
                }
            });
            animator.setDuration(Consts.ANIMATION_DURATION_POINT * 1000);
            animator.start();
        } catch (Exception e) {
            Reporter.error("TA_ANIMATE", e);
        }
    }
}
