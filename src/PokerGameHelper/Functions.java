package PokerGameHelper;

public class Functions {
	public static int max(int a, int b) {
		return (a > b) ? a : b;// 더 큰 값 반환
	}

	public static int Combination(int n, int r) {
		if (r == 0)
			return 1;
		else if (r == n)
			return 1;
		else
			return Combination(n - 1, r - 1) + Combination(n - 1, r);// 중복 조합
	}

	public static int countLeftDeck(Card[][] deck) {
		int num = 0;
		for (int i = 0; i < deck.length; i++) {
			for (int j = 0; j < deck[i].length; j++)
				if (!(deck[i][j].isTaken))
					num++;
		}
		return num;// 남은 카드 장수 (전체 덱 버전)
	}

	public static boolean searchCard(Player player, Card card) {
		for (int i = 0; i < player.cardDeck.length; i++) {
			if (player.cardDeck[i] != null && player.cardDeck[i].equals(card))
				return true;
		}
		return false;// 개인 덱에서 카드 찾기
	}

	public static void TotalCardCombination(Player player) {
		player.myComb = new CardCombination();
		player.myComb.TotalDeckCount = countLeftDeck(Card.TotalDeck);
		RoyalFlushNum(player);
		StraightFlushNum(player);

	} // 전체 카드 조합들의 경우의 수 분석

	public static void RoyalFlushNum(Player player) {
		Card[][] instantDeck = new Card[4][5];

		for (int i = 0; i < 4; i++) {
			for (int j = 9; j < 13; j++) {
				if (searchCard(player, Card.TotalDeck[i][j])) {
					instantDeck[i][j - 9] = Card.TotalDeck[i][j];
				}
			}
		}
		for (int i = 0; i < 4; i++) {
			if (searchCard(player, Card.TotalDeck[i][0]))
				instantDeck[i][4] = Card.TotalDeck[i][0];
		} // 사용자의 덱에서 로열 플러쉬 조건 만족 카드 골라내기

		for (int i = 0; i < 4; i++) {
			int available = 0, num = 0;
			for (int j = 0; j < 5; j++) {
				if (instantDeck[i][j] == null)
					available++;
			} // 필요한 카드수 세기
			num = available;

			if (available == 0) {
				player.myComb.RoyalFlush = "Exists";
				return; // 필요한 카드가 없다면 반환 (이미 존재)
			}
			if (available < GameSource.leftDraw) {
				for (int j = 0; j < 5; j++) {
					if (instantDeck[i][j] == null)
						if (!(Card.TotalDeck[i][(j + 9) % 13].isTaken)) // 필요 카드 가져갔는지 확인
							available--;
				}
				if (available == 0)
					player.myComb.RoyalFlush = Integer.toString(Combination(GameSource.leftDraw, num)); // 로열 플러쉬 경우의 수
																										// 1개 증가
			} // 남은 드로우 수랑 맞을 때 실행
		}
	}

	public static void StraightFlushNum(Player player) {
		Card[] paramDeck = Card.sortedDeck(player.cardDeck);
		int totalNum = 0;

		for (int i = 0; i < Card.CardCharacter.length; i++) {
			int index;
			for (index = 0; index < 7 - GameSource.leftDraw; index++)
				if (paramDeck[index].character != Card.CardCharacter[i])
					break;

			int[] instNum = new int[13];
			for (int a = 0; a < index; a++) {
				instNum[paramDeck[a].number - 1] = paramDeck[a].number;
			} // 있는 카드들 집어넣기

			for (int a = 0; a < 10; a++) {
				int num = 0;

				for (int b = 0; b < 5; b++) {
					if (Card.TotalDeck[i][(a + b) % 13].isTaken) {
						num = -1;
						break;
					} else if (instNum[(a + b) % 13] == 0)
						num++;
				}

				if (num == 0) {
					player.myComb.StraightFlush = "Exists";
					return;
				} else if (GameSource.leftDraw >= num && num > 0) {
					totalNum += Combination(GameSource.leftDraw, num);
				}
			} // 필요한 카드 수 구하기
		}
		if (!player.myComb.RoyalFlush.equals("Exists"))
			player.myComb.StraightFlush = Integer.toString(totalNum - Integer.parseInt(player.myComb.RoyalFlush));
		else
			player.myComb.StraightFlush = Integer.toString(totalNum);//결과 반환 ("Exists" 값 처리 포함)
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
