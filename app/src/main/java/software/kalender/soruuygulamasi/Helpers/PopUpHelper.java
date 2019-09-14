package software.kalender.soruuygulamasi.Helpers;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.zip.Inflater;

import software.kalender.soruuygulamasi.R;
import software.kalender.soruuygulamasi.Statics;

public class PopUpHelper {
    private String message;
    private String icon; //todo ?
    private String optionLeft, optionRight, optionMiddle;
    private View.OnClickListener leftOptionClick, rightOptionClick, optionClick;
    private Context context;
    private boolean isWithOptions = false;
    private View view;

    public PopUpHelper(String message, String icon, String optionMiddle, View.OnClickListener optionClick) {
        this.message = message;
        this.icon = icon;
        this.optionMiddle = optionMiddle;
        this.optionClick = optionClick;
        this.context = Statics.context;

        isWithOptions = false;
    }

    public PopUpHelper(String message, String icon, String optionLeft, String optionRight, View.OnClickListener leftOptionClick, View.OnClickListener rightOptionClick) {
        this.message = message;
        this.icon = icon;
        this.optionLeft = optionLeft;
        this.optionRight = optionRight;
        this.leftOptionClick = leftOptionClick;
        this.rightOptionClick = rightOptionClick;
        this.context = Statics.context;

        isWithOptions = true;
    }

    public View getView() {
        if (isWithOptions) {
            return getViewWithOptions();
        } else {
            return getViewConfirm();
        }
    }

    public View getViewWithOptions() {
       view = LayoutInflater.from(context).inflate(R.layout.popup_with_options, null);

        ((TextView) view.findViewById(R.id.tvPopUp)).setText(message);
        ((Button) view.findViewById(R.id.btnPopUpOptionLeft)).setText(optionLeft);
        ((Button) view.findViewById(R.id.btnPopUpOptionRight)).setText(optionRight);
        ((Button) view.findViewById(R.id.btnPopUpOptionLeft)).setOnClickListener(leftOptionClick);
        ((Button) view.findViewById(R.id.btnPopUpOptionRight)).setOnClickListener(rightOptionClick);

        return view;
    }

    public View getViewConfirm() {
       view = LayoutInflater.from(context).inflate(R.layout.popup_confirm, null);

        ((TextView) view.findViewById(R.id.tvPopUp)).setText(message);
        ((Button) view.findViewById(R.id.btnPopUpOption)).setText(optionMiddle);
        ((Button) view.findViewById(R.id.btnPopUpOption)).setOnClickListener(optionClick);

        return view;
    }

    public void hideView(){
        ((RelativeLayout)(view.getParent())).removeView(view);
    }
}
