package xyz.uniwards.uniwards_student;

/**
 * Created by Umayr on 4/28/2018.
 */

public class DashCard {
    private Integer cardImage;
    private String cardTitle;
    private String cardDesc;

    public DashCard(Integer cardImage, String cardTitle, String cardDesc) {
        this.cardImage = cardImage;
        this.cardTitle = cardTitle;
        this.cardDesc = cardDesc;
    }

    public Integer GetCardImage() { return this.cardImage; }
    public void SetCardImage(Integer cardImage) { this.cardImage = cardImage; }

    public String GetCardTitle() { return this.cardTitle; }
    public void SetCardTitle(String cardTitle) { this.cardTitle = cardTitle; }

    public String GetCardDesc() { return this.cardDesc; }
    public void SetCardDesc(String cardDesc) { this.cardDesc = cardDesc; }
}
