import processing.core.PApplet;

public class GiftCard extends UnoCard {

    public GiftCard(String suit){
        super("Gift", suit);
    }
    public void draw(PApplet sketch){
        super.draw(sketch);

        sketch.fill(0);
        sketch.textAlign(PApplet.CENTER, PApplet.CENTER);
        sketch.textSize(20);
        sketch.text("G", x + width/2, y + height/2);
    }
}