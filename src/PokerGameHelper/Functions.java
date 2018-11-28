package PokerGameHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Functions {
	public static int max(int a, int b) {
		return (a > b) ? a : b;
	}
	
	public static boolean searchCard(Player player, Card card) {
		for(int i=0; i<player.cardDeck.length; i++) {
			if(player.cardDeck[i].equals(card))
				return true;
		}
		return false;
	}
	
	public static void TotalCardCombination(Player player) {
		RoyalFlushNum(player);
	} //전체 카드 조합들의 경우의 수 분석
	
	public static void RoyalFlushNum(Player player) {
		Card[][] instantDeck = new Card[4][5];

		for(int i=0;i<4;i++) {
			for(int j=9;j<13;j++) {
				if(searchCard(player, Card.TotalDeck[i][j])) {
					instantDeck[i][j-9]=Card.TotalDeck[i][j];
				}
			}
		}
		for(int i=0;i<4;i++) {
			if(searchCard(player, Card.TotalDeck[i][0]))
				instantDeck[i][4]=Card.TotalDeck[i][0];
		} //사용자의 덱에서 로열 플러쉬 조건 만족 카드 골라내기
		
		for(int i=0;i<4;i++) {
			int num=0;
			for(int j=0;j<5;j++) {
				if(instantDeck[i][j]==null)
					num++;
			}//필요한 카드수 세기
			
			if(num==0)
				player.myComb.RoyalFlush=-1;//필요한 카드가 없다면 -1 반환 (이미 존재)
			
			if(num<GameSource.leftDraw) {
				for(int j=0;j<5;j++) {
					if(instantDeck[i][j]==null)
						if(!(instantDeck[i][j].isTaken)) //필요 카드 가져갔는지 확인
							num--;
				}
				if(num==0)
					player.myComb.RoyalFlush++; //로열 플러쉬 경우의 수 1개 증가
			}//남은 드로우 수랑 맞을 때 실행
		}
	}
	
	public static void StraightFlushNum(Player player) {
		
	}
	
	public static void FourCardNum(Player player) {
		
	}
	
	public static void FullHouseNum(Player player) {
		
	}
	
	public static void FlushNum(Player player) {
		
	}
	
	public static void StraightNum(Player player) {
		
	}
	
	public static void TripleNum(Player player) {
		
	}
	
	public static void TwoPairNum(Player player) {
		
	}
	
	public static void OnePairNum(Player player) {
		
	}
	
	public static void HighCard(Player player) {
		
	}
}
