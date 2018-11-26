package PokerGameHelper;

public class Functions {
	public static int max(int a, int b) {
		return (a > b) ? a : b;
	}
	
	public static boolean searchCard(Player player, Card card) {
		for(int i=0; i<player.cardDeck.length; i++) {
			if(player.cardDeck[i].character==card.character&&player.cardDeck[i].number==card.number)
				return true;
		}
		return false;
	}
	
	public static CardCombination TotalCardCombination(Player player) {
		
	} //전체 카드 조합들의 경우의 수 분석
	
	public static void RoyalFlushNum(Player player) {
		for()
	}
	
}
