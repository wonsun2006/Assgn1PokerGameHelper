package PokerGameHelper;

import java.util.Arrays;
import java.util.List;

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

	public static int countLeftDeck() {
		int num = 0;
		for (int i = 0; i < Card.TotalDeck.length; i++) {
			for (int j = 0; j < Card.TotalDeck[i].length; j++)
				if (!(Card.TotalDeck[i][j].isTaken))
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

	public static class NeedAvailable {
		int need;
		int available;

		NeedAvailable(int need, int available) {
			this.need = need;
			this.available = available;
		}
	}

	public static NeedAvailable[] TripleCase(Player player) {
		Card[] paramDeck = Card.sortedDeck(player.cardDeck);
		NeedAvailable[] TripleCase = new NeedAvailable[13];

		for (int i = 0; i < 13; i++) {
			TripleCase[i] = new NeedAvailable(3, 4);
		} // 생성자 실행

		for (int i = 0; i < 13; i++) {
			for (int j = 0; j < paramDeck.length; j++) {
				if (paramDeck[j].number == i + 1)
					TripleCase[i].need--;
			}
		}
		for (int a = 0; a < 13; a++) {
			for (int b = 0; b < 4; b++) {
				if (Card.TotalDeck[b][a].isTaken == true)
					TripleCase[a].available--;
			}
		} // 숫자마다 뽑을 수 있는 카드 수
		return TripleCase;
	}
	
	public static NeedAvailable[] PairCase(Player player) {
		Card[] paramDeck = Card.sortedDeck(player.cardDeck);
		NeedAvailable[] PairCase = new NeedAvailable[13];

		for (int i = 0; i < 13; i++) {
			PairCase[i] = new NeedAvailable(2, 4);
		} // 생성자 실행

		for (int i = 0; i < 13; i++) {
			for (int j = 0; j < paramDeck.length; j++) {
				if (paramDeck[j].number == i + 1)
					PairCase[i].need--;
			}
		}
		for (int a = 0; a < 13; a++) {
			for (int b = 0; b < 4; b++) {
				if (Card.TotalDeck[b][a].isTaken == true)
					PairCase[a].available--;
			}
		} // 숫자마다 뽑을 수 있는 카드 수
		return PairCase;
	}

	public static void TotalCardCombination(Player player) {
		player.myComb = new CardCombination();
		player.myComb.TotalDeckCount = countLeftDeck();
		RoyalFlushNum(player);
		StraightFlushNum(player);
		FourCardNum(player);
		FullHouseNum(player);
		FlushNum(player);
		
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

			if (num == 0) {
				player.myComb.RoyalFlush = "Exists";
				return; // 필요한 카드가 없다면 반환 (이미 존재)
			}
			if (available <= GameSource.leftDraw) {
				for (int j = 0; j < 5; j++) {
					if (instantDeck[i][j] == null)
						if (!(Card.TotalDeck[i][(j + 9) % 13].isTaken)) // 필요 카드 가져갔는지 확인
							available--;
				}
				if (available == 0)
					player.myComb.RoyalFlush = Integer
							.toString(Combination(countLeftDeck() - num, GameSource.leftDraw - num)); // 로열 플러쉬 경우의 수
			} // 남은 드로우 수랑 맞을 때 실행
		}
	}

	public static void StraightFlushNum(Player player) {
		Card[] paramDeck = Card.sortedDeck(player.cardDeck);
		List<Card> paramList = Arrays.asList(paramDeck);
		int totalNum = 0;

		for (int i = 0; i < Card.CardCharacter.length; i++) {
			int index;
			for (index = 0; index < 7 - GameSource.leftDraw; index++)
				if (paramDeck[index].character != Card.CardCharacter[i])
					break;

			int[] instNum = new int[13];
			for (int a = 0; a < index + 1; a++) {
				instNum[paramDeck[a].number - 1] = paramDeck[a].number;
			} // 있는 카드들 집어넣기

			for (int a = 0; a < 10; a++) {
				int num = 0;

				for (int b = 0; b < 5; b++) {
					if (!(paramList.contains(Card.TotalDeck[i][(a + b) % 13]))
							&& Card.TotalDeck[i][(a + b) % 13].isTaken) {
						num = -1;
						break;
					} else if (instNum[(a + b) % 13] == 0)
						num++;
				}

				if (num == 0) {
					player.myComb.StraightFlush = "Exists";
					return;
				} else if (GameSource.leftDraw >= num && num > 0) {
					totalNum += Combination(countLeftDeck() - num, GameSource.leftDraw - num);
				}
			} // 필요한 카드 수 구하기
		}
		if (player.myComb.RoyalFlush.equals("Exists"))
			player.myComb.StraightFlush = Integer.toString(totalNum);// 결과 반환 ("Exists" 값 처리 포함)
		else
			player.myComb.StraightFlush = Integer.toString(totalNum - Integer.parseInt(player.myComb.RoyalFlush));
	}

	public static void FourCardNum(Player player) {
		Card[] paramDeck = Card.numberFirstSortedDeck(player.cardDeck);
		int totalNum = 0;
		for (int i = 0; i < 13; i++) {
			int need = 4, isSame = 4;
			for (int j = 0; j < paramDeck.length; j++) {
				if (paramDeck[j] == null)
					break;
				if (paramDeck[j].number == i + 1)
					need--;
			}

			for (int j = 0; j < 4; j++) {
				if (Card.TotalDeck[j][i].isTaken)
					isSame--;
			}
			if (need == 0) {
				player.myComb.FourCard = "Exists";
				return;
			}
			if (need == isSame && need <= GameSource.leftDraw && need > 0) {
				totalNum += Combination(countLeftDeck() - need, GameSource.leftDraw - need);
			}

		}
		player.myComb.FourCard = Integer.toString(totalNum);
	}

	public static void FullHouseNum(Player player) {
		Card[] paramDeck = Card.sortedDeck(player.cardDeck);
		NeedAvailable[] TripleCase = TripleCase(player);
		NeedAvailable[] PairCase = PairCase(player);
		int totalNum = 0;

		for (int i = 0; i < 13; i++) {
			for (int j = 0; j < 13; j++) {
				if (i != j) {
					if (TripleCase[i].need <= 0 && PairCase[j].need <= 0) {
						player.myComb.FullHouse = "Exists";
						return;
					}
					if (TripleCase[i].need >= TripleCase[i].available 
							&& PairCase[j].need >= PairCase[j].available
							&& TripleCase[i].available >= GameSource.leftDraw
							&& PairCase[j].available >= GameSource.leftDraw
							&& TripleCase[i].need + PairCase[j].need <= GameSource.leftDraw) {
						totalNum += (Combination(TripleCase[i].available, TripleCase[i].need)
								* Combination(PairCase[j].available, PairCase[j].need)
								* Combination(countLeftDeck() - TripleCase[i].need - PairCase[j].need,
										GameSource.leftDraw - TripleCase[i].need - PairCase[j].need));
					}
				}
			}
		}
		player.myComb.FullHouse = Integer.toString(totalNum);
	}

	public static void FlushNum(Player player) {
		int[] playerCharNum = new int[4];
		int[] totalCharNum = new int[4];
		int totalNum=0;
		for(int i=0; i<player.cardDeck.length;i++){
		if(player.cardDeck[i]!=null){
			if(player.cardDeck[i].character=="SPADE")
				playerCharNum[0]++;
			else if(player.cardDeck[i].character=="DIAMOND")
				playerCharNum[1]++;
			else if(player.cardDeck[i].character=="HEART")
				playerCharNum[2]++;
			else if(player.cardDeck[i].character=="CLOVER")
				playerCharNum[3]++;
		} else
			break;
		}//문양 개수 세기
		for(int i=0; i<4; i++)
			if(playerCharNum[i]>=5) {
				player.myComb.Flush="Exists";
				return;
			}//이미 있는지 검사
		
		for(int i=0; i<4; i++) {
			for(int j=0; j<13; j++) {
				if(Card.TotalDeck[i][j].isTaken==false)
					totalCharNum[i]++;
			}
		}//전체 덱에 뽑을 수 있는 카드 문양 별로 갯수 세기
		
		for(int i=0; i<4; i++) {
			if(playerCharNum[i]<=GameSource.leftDraw&&totalCharNum[i]>=playerCharNum[i]) {
				totalNum+=Combination(totalCharNum[i],playerCharNum[i])*Combination(countLeftDeck()-playerCharNum[i],GameSource.leftDraw-playerCharNum[i]);
			}
		}
		totalNum-=(Integer.parseInt(player.myComb.RoyalFlush)+Integer.parseInt(player.myComb.StraightFlush));
		player.myComb.Flush=Integer.toString(totalNum);
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
