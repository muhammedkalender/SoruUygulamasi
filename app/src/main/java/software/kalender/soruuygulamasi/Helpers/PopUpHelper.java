package software.kalender.soruuygulamasi.Helpers;

import android.content.Context;
import android.text.Layout;
import android.util.Log;
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
    private boolean show = false;

    public PopUpHelper(String message, String icon, String optionMiddle, View.OnClickListener optionClick) {
        this.message = message;
        this.icon = icon;
        this.optionMiddle = optionMiddle;
        this.optionClick = optionClick;
        this.context = Statics.context;

        if (optionClick == null) {
            this.optionClick = new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    PopUpHelper.this.hideView();
                }
            };
        }

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

        if (leftOptionClick == null) {
            this.leftOptionClick = new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    PopUpHelper.this.hideView();
                }
            };
        }

        if (rightOptionClick == null) {
            this.rightOptionClick = new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    PopUpHelper.this.hideView();
                }
            };
        }
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

        setShow(true);

        return view;
    }

    public View getViewConfirm() {
        view = LayoutInflater.from(context).inflate(R.layout.popup_confirm, null);

        ((TextView) view.findViewById(R.id.tvPopUp)).setText(message);
        ((Button) view.findViewById(R.id.btnPopUpOption)).setText(optionMiddle);
        ((Button) view.findViewById(R.id.btnPopUpOption)).setOnClickListener(optionClick);

        setShow(true);

        return view;
    }

    public void hideView() {
        try {
            ((RelativeLayout) (view.getParent())).removeView(view);
            setShow(false);
        } catch (Exception e) {
            Reporter.error("POPUP_REMOVE", e);
        }
    }

    public boolean isShow() {
        return show;
    }

    public void setShow(boolean show) {
        this.show = show;
    }

    public void forceNegative() {
        if (isShow()) {
            if (leftOptionClick != null) {
                leftOptionClick.onClick(null);
            } else {
                optionClick.onClick(null);
            }
        }
    }
}
