package PokerGameHelper;

import java.util.Arrays;
import java.util.Comparator;

class Player {
	public Card[] cardDeck = new Card[7];
	boolean isDead = false;
	CardCombination myComb=new CardCombination();

	void setCard(int i, Card card) {
		this.cardDeck[i] = card;
	}

	void showStats() {
		System.out.print("Player Status: "); // Player의 생존 유무를 표시
		if (isDead == false)
			System.out.println("alive");
		else
			System.out.println("dead");

		System.out.println("<Card lists>"); // Player의 카드 덱을 표시
		for (int i = 0; i < cardDeck.length; i++) {
			if (cardDeck[i] != null) {
				System.out.println(cardDeck[i].toString());
			}
		}
	}
	
	void showCombinations() {
		Functions.TotalCardCombination(this);
		System.out.println("로열 스트레이트 플러쉬: "+this.myComb.RoyalFlush);
		System.out.println(this.myComb.StraightFlush);
		System.out.println(this.myComb.FourCard);
		System.out.println(this.myComb.FullHouse);
		System.out.println(this.myComb.Flush);
		System.out.println(this.myComb.Straight);
		System.out.println(this.myComb.Triple);
		System.out.println(this.myComb.TwoPair);
		System.out.println(this.myComb.OnePair);
		System.out.println(this.myComb.HighCard);		
	}
}

class Card implements Comparable<Card>{
	public static Card[][] TotalDeck = new Card[4][13];
	public static final String[] CardCharacter = { "SPADE", "DIAMOND", "HEART", "CLOVER" };
	String character;//카드 모양
	int number;//카드 숫자
	boolean isTaken;//카드가 플레이어에게 있는가를 판단
	
	Card(String character, int number, boolean isTaken) {
		this.character = character;
		this.number = number;
		this.isTaken = isTaken;
	}//카드 생성자
	
	public int compareTo(Card other) {
		return character.compareTo(other.character);
	}
	
	public static Comparator<Card> totalComparator = new Comparator<Card>() {
		public int compare(Card card1, Card card2) {
			if(Arrays.asList(Card.CardCharacter).indexOf(card1.character)
				!=Arrays.asList(Card.CardCharacter).indexOf(card2.character)) {
				return Arrays.asList(Card.CardCharacter).indexOf(card1.character)
						- Arrays.asList(Card.CardCharacter).indexOf(card2.character);
			} else {
				return card1.number-card2.number;
			}
		}
	};//카드 비교 방식(문자 우열/ 숫자 오름차순)
	
	public static Comparator<Card> numberFirstComparator = new Comparator<Card>() {
		public int compare(Card card1, Card card2) {
			if(card1.number!=card2.number)
				return card1.number-card2.number;
			else {
				return Arrays.asList(Card.CardCharacter).indexOf(card1.character)
						- Arrays.asList(Card.CardCharacter).indexOf(card2.character);
			} 
		}
	};//카드 비교 방식(편하게 정렬하기 위한 방식)
	
	public static Card[] sortedDeck(Card[] deck) {
		Card[] instDeck = Arrays.copyOf(deck,deck.length);
		Arrays.sort(instDeck,0,7-GameSource.leftDraw, Card.totalComparator);
		return instDeck;
	} //카드를 스페이드,다이아,하트,클로버/숫자 순으로 정렬
	
	public static Card[] numberFirstSortedDeck(Card[] deck) {
		Card[] instDeck = Arrays.copyOf(deck,deck.length);
		Arrays.sort(instDeck,0,7-GameSource.leftDraw, Card.numberFirstComparator);;
		return instDeck;
	} //카드를 스페이드,다이아,하트,클로버/숫자 순으로 정렬

	public String toString() {
		return character + " " + number;
	}	//카드 문자와 숫자를 문자열로 표현
	
	public boolean equals(Card card) {
		if(character.equals(card.character)&&number==card.number)
			return true;
		else
		return false;
	}
	
}

class CardCombination {
	int TotalDeckCount = 0;
	String RoyalFlush;
	String StraightFlush;
	String FourCard;
	String FullHouse;
	String Flush;
	String Straight;
	String Triple;
	String TwoPair;
	String OnePair;
	String HighCard;
}
