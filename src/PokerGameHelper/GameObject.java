package PokerGameHelper;

class Player{
	public Card[] cardDeck = new Card[7];
	boolean isDead = false;
	CardCombination myComb;
	
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
				System.out.println(cardDeck[i].toString());
		}
	}
}

class Card{
	static Card[][] TotalDeck = new Card[4][13];
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

class CardCombination {
	int RoyalFlush = 0;
	int StraightFlush = 0;
	int FourOfAKind = 0;
	int FullHouse = 0;
	int Straight = 0;
	int ThreeOfAKind = 0;
	int TwoPair = 0;
	int OnePair = 0;
	Card HighCard;
}