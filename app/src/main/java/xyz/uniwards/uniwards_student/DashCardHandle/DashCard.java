package xyz.uniwards.uniwards_student.DashCardHandle;

import android.text.method.DateTimeKeyListener;

import java.util.Date;

/**
 * Created by Umayr on 4/28/2018.
 */

public class DashCard {
    private Integer cardImage;
    private String cardTitle;
    private String cardDesc;
    private Date cardDate;

    public DashCard(Integer cardImage, String cardTitle, String cardDesc, Date cardDate) {
        this.cardImage = cardImage;
        this.cardTitle = cardTitle;
        this.cardDesc = cardDesc;
        this.cardDate = cardDate;
    }

    public Integer GetCardImage() { return this.cardImage; }
    public void SetCardImage(Integer cardImage) { this.cardImage = cardImage; }

    public String GetCardTitle() { return this.cardTitle; }
    public void SetCardTitle(String cardTitle) { this.cardTitle = cardTitle; }

    public String GetCardDesc() { return this.cardDesc; }
    public void SetCardDesc(String cardDesc) { this.cardDesc = cardDesc; }

    public Date GetCardDate() { return this.cardDate; }
    public void SetCardDate(Date cardDate) { this.cardDate = cardDate; }
}
