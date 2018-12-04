package PokerGameHelper;

import java.util.Scanner;

public class GameFlow {

	public static void MainMenu() {
		Scanner menuScanner = new Scanner(System.in);
		System.out.println("<포커 게임 헬퍼>");
		
		System.out.println("1. 새 게임 시작");
		System.out.println("2. 헬퍼 프로그램 설명");
		System.out.println("3. 프로그램 종료");
		

		while(true) {
			System.out.print("옵션을 선택하세요>> ");
		try {
			switch(menuScanner.nextInt()) {
			case 1: break;
			case 2: break;
			case 3: break;
			}
		break;	
		}catch(Exception e) {
				System.out.println("에러: 정수를 입력해주세요.");
				menuScanner.nextLine();
			}
		}//메뉴 화면 옵션 선택
		
	}

	public static void GameSet() {
		for (int i = 0; i < 4; i++)
			for (int j = 0; j < 13; j++)
				Card.TotalDeck[i][j] = new Card(Card.CardCharacter[i], j + 1, false); // 전체 카드덱 설정 (모양 idx 0~4, 숫자 idx 0~12)
//		GameSource.leftDraw = 6;//테스트를 위해 보류
	}
	
	public static void PlayerGetCard(Player player, Card card) {
		int i=0;
		for(; player.cardDeck[i]!=null; i++) {
		}
		player.cardDeck[i]=card;
		card.isTaken=true;
	}

	public static void main(String[] args) {
//		MainMenu();
		GameSet();
		Player pl1 = new Player();
/*		for(int i=0; i<4; i++) {
					pl1.cardDeck[i]=Card.TotalDeck[0][i+9];
					Card.TotalDeck[0][i+9].isTaken=true;
		}
//		pl1.cardDeck[4]=Card.TotalDeck[0][0];
*/		

		for(int i=0; i<7; i++) {
			pl1.cardDeck[i]=Card.TotalDeck[(int)(Math.random()*4)][(int)(Math.random()*13)];
			pl1.cardDeck[i].isTaken=true;
		}
			pl1.showStats();
		Functions.TotalCardCombination(pl1);
		pl1.showCombinations();
	}
}

class GameSource{
		public static int leftDraw = 4;
	}