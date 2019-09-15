package software.kalender.soruuygulamasi.Objects;

public class Category {
    private int id;
    private String text;
    private String icon;
    private int parent_id;
    private boolean parent;
    private String color;

    public Category(int id, String text, String icon, int parent_id, boolean parent, String color) {
        this.id = id;
        this.text = text;
        this.icon = icon;
        this.parent_id = parent_id;
        this.parent = parent;
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getParent_id() {
        return parent_id;
    }

    public void setParent_id(int parent_id) {
        this.parent_id = parent_id;
    }

    public boolean isParent() {
        return parent;
    }

    public void setParent(boolean parent) {
        this.parent = parent;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
