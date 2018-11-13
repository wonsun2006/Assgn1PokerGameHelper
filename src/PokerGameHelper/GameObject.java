package PokerGameHelper;

class Player{
	private Card[] cardDeck = new Card[7];
	boolean isDead = false;
	
	void setCard(int i, Card card) {
		this.cardDeck[i] = card;
	}
	
	void showStats() {
		System.out.print("Player Status: ");	//Player의 생존 유무를 표시
		if(isDead==false)
			System.out.println("alive");
		else
			System.out.println("dead");
		
		System.out.println("<Card lists>");		//Player의 카드 덱을 표시
		for(int i=0; i<cardDeck.length; i++) {
			if(cardDeck[i].isHidden==false && cardDeck[i]!=null)
				System.out.print(cardDeck[i].toString());
		}
	}
}

class Card{
	public static final String[] CardCharacter = {"SPADE", "DIAMOND", "HEART", "CLOVER"};
	String character;
	int number;
	boolean isHidden;
	boolean isTaken;
	
	Card(String character, int number, boolean isHidden, boolean isTaken){
		this.character=character;
		this.number=number;
		this.isHidden=isHidden;
		this.isTaken=isTaken;
	}
	
	public String toString() {
		return character + " " + number;
	}
}
