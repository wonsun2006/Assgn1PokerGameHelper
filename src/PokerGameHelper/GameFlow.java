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
				Card.TotalDeck[i][j] = new Card(Card.CardCharacter[i], j + 1, false, false); // 전체 카드덱 설정 (모양 idx 0~4, 숫자 idx 1~13)
		
	}
	


	public static void main(String[] args) {
		MainMenu();
		GameSet();
	}

}	
class GameSource{
		public static int leftDraw = 4;
	}