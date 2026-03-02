import processing.core.PApplet;

public class ClickableRectangle {
    int x;
    int y;
    int width;
    int height;
 String text = "";

    ClickableRectangle() {
    }

    ClickableRectangle(int x, int y, int width, int height, String text) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.text = text;
    }
    boolean isClicked(int mouseX, int mouseY) {
        return mouseX >= x && mouseX <= x + width &&
               mouseY >= y && mouseY <= y + height;
    }

    public void draw(PApplet app) {
        app.rect(x, y, width, height);
    } 
}
