import processing.core.PApplet;

public class App extends PApplet {

    public static int gameWidth;
    CardGame cardGame = new Uno();
    private int timer;
    static Card draggedCard = null;
    int dragOffsetX;
    int dragOffsetY;

    public static void main(String[] args) {
        PApplet.main("App");
    }
    @Override
    public void settings() {
        size(600, 600);   
        gameWidth = 600;
    }

    @Override
    public void draw() {
        textAlign(CENTER, CENTER);
        background(255);
        // Draw player hands
        for (int i = 0; i < cardGame.playerOneHand.getSize(); i++) {
            Card card = cardGame.playerOneHand.getCard(i);
            if (card != null) {
                card.draw(this);
            }
        }
        // Draw computer hand
        for (int i = 0; i < cardGame.playerTwoHand.getSize(); i++) {
            Card card = cardGame.playerTwoHand.getCard(i);
            if (card != null) {
                card.draw(this);
            }
        }
        
        // Draw draw button
        fill(200);
        cardGame.drawButton.draw(this);
        fill(0);
        textAlign(CENTER, CENTER);
        text("Draw", cardGame.drawButton.x + cardGame.drawButton.width / 2, cardGame.drawButton.y + cardGame.drawButton.height / 2);

        // Display current player
        fill(0);
        textSize(16);
        text("Current Player: " + cardGame.getCurrentPlayer(), width / 2, 20);

        // Display deck size
        text("Deck Size: " + cardGame.getDeckSize(), width / 2,
                height - 20);
        // Display last played card
        if (cardGame.getLastPlayedCard() != null) {
            cardGame.getLastPlayedCard().setPosition(width / 2 - 40, height / 2 - 60, 80, 120);
            cardGame.getLastPlayedCard().draw(this);
        }
        if ("Player Two".equals(cardGame.getCurrentPlayer())) {
            fill(0);
            textSize(16);
            text("Computer is thinking...", width / 2, height / 2 + 80);
            timer++;
            if (timer == 100) {
                cardGame.handleComputerTurn();
                timer = 0;
            }
        }

        cardGame.drawChoices(this);
        cardGame.drawPlayAgain(this);
    }

    
    @Override
    public void mousePressed() {
        cardGame.handleDrawButtonClick(mouseX, mouseY);
        cardGame.handlePlayAgainClick(mouseX, mouseY);
        cardGame.handleCardClick(mouseX, mouseY);
        draggedCard = cardGame.getClickedCard(mouseX,mouseY); //start dragging a card

        if(draggedCard!=null){
            dragOffsetX = mouseX - draggedCard.x;
            dragOffsetY = mouseY - draggedCard.y;
        }

    }
    public void mouseDragged(){
        if(draggedCard !=null){
            draggedCard.x = mouseX - dragOffsetX;
            draggedCard.y = mouseY - dragOffsetY;
        }
    }
public void mouseReleased(){
    if(draggedCard!=null){
        //drop to (center)pile
        int pileX = width/2-40;
        int pileY = height/2 - 60;
        
        if(mouseX>pileX && mouseX <pileX + 80 && mouseY>pileY && mouseY<pileY + 120){
            cardGame.playCard(draggedCard, cardGame.playerOneHand);
        }
        //resets the card positions
        cardGame.playerOneHand.positionCards(50,450,80,120,20);
        draggedCard = null;
    }
}
}
