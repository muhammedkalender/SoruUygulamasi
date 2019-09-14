package software.kalender.soruuygulamasi.Objects;

public class Output {
    private int id;
    private String title;
    private String message;
    private boolean status;
    private Exception exception;

    private Object data;

    //region Getters & Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return status;
    }

    public boolean isFailure() {
        return !status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    //endregion

    //region Constructors

    public Output(){

    }

    public Output(String title, String message, boolean status) {
        this.title = title;
        this.message = message;
        this.status = status;
    }

    public Output(String title, String message, boolean status, Exception exception) {
        this.title = title;
        this.message = message;
        this.status = status;
        this.exception = exception;
    }

    public Output(String title, String message, boolean status, Exception exception, Object data) {
        this.title = title;
        this.message = message;
        this.status = status;
        this.exception = exception;
        this.data = data;
    }

    public Output(int id, String title, String message, boolean status, Exception exception, Object data) {
        this.id = id;
        this.title = title;
        this.message = message;
        this.status = status;
        this.exception = exception;
        this.data = data;
    }

    //endregion;
}
