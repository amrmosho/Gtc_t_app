package tabuk.amin.e.gtc_t_app.libs;

/**
 * Created by ismail on 10/31/2015.
 */
public class simpleList {
    public String getValue() {
        return value;
    }

    public String getText() {
        return text;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setText(String text) {
        this.text = text;
    }





    @Override
    public String toString() {
        return this.text;
    }
    public String value;

    public String text;

}
