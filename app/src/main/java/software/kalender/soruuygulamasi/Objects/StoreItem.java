package software.kalender.soruuygulamasi.Objects;

import software.kalender.soruuygulamasi.Statics;

public class StoreItem {
    private String title, description;
    private long price;
    private int code;

    public StoreItem(int title, int description, long price, int code) {
        this.title = Statics.getString(title);
        this.description = Statics.getString(description);
        this.price = price;
        this.code = code;
    }

    public StoreItem(String title, String description, long price, int code) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
