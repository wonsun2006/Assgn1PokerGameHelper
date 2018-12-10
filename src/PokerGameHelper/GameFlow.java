package PokerGameHelper;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GameFlow {
	public static class MainMenu extends JFrame { // 1. 새 게임 시작 2. 헬퍼 프로그램 설명 3. 프로그램 종료
		private JButton button1 = new JButton("새 게임 시작");
		private JButton button2 = new JButton("프로그램 설명");
		private JButton button3 = new JButton("종료");

		public MainMenu() {
			setTitle("MainMenu");
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			Container c = getContentPane();
			c.setLayout(new FlowLayout());

			MenuButtonListener listener = new MenuButtonListener();

			button1.addActionListener(listener);
			button2.addActionListener(listener);
			button3.addActionListener(listener);

			c.add(button1);
			c.add(button2);
			c.add(button3);

			setSize(1500, 1000);
			setVisible(true);
		}

		class MenuButtonListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				JButton b = (JButton) e.getSource();
				if (b.equals(button1)) {
					dispose();
					String number=JOptionPane.showInputDialog("총 플레이어 수를 입력하세요. (1~8명)").trim();
					try{
						if(0<Integer.parseInt(number) && 8>=Integer.parseInt(number)) {
							new GameStart(Integer.parseInt(number));// 게임 시작 화면 출력
							} else {
								JOptionPane.showMessageDialog(null, "범위 내에서 입력하세요.", "Message", JOptionPane.ERROR_MESSAGE);
								new MainMenu();
						}
					}catch(NumberFormatException formatException) {
						JOptionPane.showMessageDialog(null, "숫자를 입력하세요.", "Message", JOptionPane.ERROR_MESSAGE);
						new MainMenu();
					}
				} else if (b.equals(button2)) {
					dispose();
					new Explanation();
				} else if (b.equals(button3)) {
					System.exit(0);
				}
			}
		}
		public static class Explanation extends JFrame {
				JButton button1 = new JButton("포커 룰");
				JButton button2 = new JButton("프로그램 설명");

				public Explanation() {
					setTitle("Explanation");
					setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					Container c = getContentPane();
					c.setLayout(new FlowLayout());
					ExplanationButtonListener listener = new ExplanationButtonListener();

					button1.addActionListener(listener);
					button2.addActionListener(listener);

					c.add(button1);
					c.add(button2);

					setSize(1500, 1000);
					setVisible(true);
				}

				class ExplanationButtonListener implements ActionListener {
					public void actionPerformed(ActionEvent e) {
						JButton b = (JButton) e.getSource();
						if (b.equals(button1)) {
							new Explanation1();	// 포커 룰 출력
						} else if (b.equals(button2)) {
							new Explanation2();	// 프로그램 설명 출력
						}
					}
				}
				
				public static class Explanation1 extends JFrame {
					public Explanation1() {
						setTitle("Explanation1");
						Container c = getContentPane();
						JTextArea jarea = new JTextArea("<세븐 포커 룰>\n"
								+ "1. 3장을 받은 뒤 1장을 공개한다.\n"
								+ "2. 턴이 지날 때마다 카드를 한장씩  받고 3턴 동안은 공개한다.\n"
								+ "3. 7번째 카드는 히든 카드이며, 마지막 카드이다.\n"
								+ "4. 5장으로 가장 높은 카드 조합을 만든 사람이 승리한다.\n"
								+ "\n"
								+ "<카드 조합 족보>\n"
								+ "1. 로열 스트레이트 플러시: 무늬가 같은 A, K, Q, J, 10\n"
								+ "2. 스트레이트 플러시: 숫자가 연속되고 무늬가 같은 카드 5장\n"
								+ "3. 포카드: 숫자가 같은 카드 4장\n"
								+ "4. 풀하우스: 트리플과 원 페어가 같이 존재\n"
								+ "5. 플러시: 숫자 상관없이 무늬가 같은 카드 5장\n"
								+ "6. 스트레이트: 숫자가 연속된 카드 5장\n"
								+ "7. 트리플: 숫자가 같은 카드 3장\n"
								+ "8. 투 페어: 원 페어가 2쌍 존재\n"
								+ "9. 원 페어: 숫자가 같은 카드 2장\n"
								+ "10. 하이카드: 숫자가 높은 카드 1장\n"
								+ "Reference: https://kr.pokernews.com/poker-rules/");
						jarea.setEditable(false);
						jarea.setFont(new Font("Gulim",Font.PLAIN,25));
						
						c.add(jarea);

						setSize(1500, 1000);
						setVisible(true);
					}
				}
				
				public static class Explanation2 extends JFrame {
					public Explanation2() {
						setTitle("Explanation2");
						Container c = getContentPane();
						JTextArea jarea = new JTextArea("이 프로그램은 세븐포커 기준입니다.\n"
								+ "\n"
								+ "1. 게임 메뉴\n"
								+ " 게임 메뉴는 크게 \"새 게임 시작\", \"프로그램 설명\", \"종료\"로 구성됩니다.\n"
								+ "새 게임 시작은 포커 게임을 하나 세팅하며, 기능을 실행할 준비를 합니다.\n"
								+ "프로그램 설명은 본 프로그램에 대한 설명을 표시합니다.\n"
								+ "종료는 프로그램을 즉시 종료합니다.\n"
								+ "\n"
								+ "2. 프로그램 기능\n"
								+ " 본 프로그램은 플레이어의 카드를 입력 받아\n"
								+ "플레이어가 가질 수 있는 카드 조합의 경우의 수를 계산합니다.\n"
								+ "계산된 경우의 수를 토대로 각 조합의 확률을 표시합니다.\n"
								+ "경우의 수 계산은 상황마다 맞지 않을 수 있으며, 한계가 존재합니다.\n"
								+ "상대방의 히든 카드가 그 예시입니다.\n"
								+ "본 프로그램은 상대방의 히든 카드가 무엇일지는 고려하지 않습니다.");
						jarea.setEditable(false);
						jarea.setFont(new Font("Gulim",Font.PLAIN,25));
						
						c.add(jarea);

						setSize(1500, 1000);
						setVisible(true);
					}
				}
			}
		
		public static class GameStart extends JFrame {
			JLabel TurnNum = new JLabel("첫번째 턴");
			public GameStart(int number) {
				setTitle("Game");
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				Container c = getContentPane();

				Functions.GameSet();
				Functions.PlayerSet(number);
				
				setSize(1500, 1000);
				setVisible(true);
			}
		}

	}
	public static void main(String[] args) {
			new MainMenu();
			
			/*Player pl1 = new Player();

			for (int i = 0; i < 3; i++) {
				pl1.cardDeck[i] = Card.TotalDeck[(int) (Math.random() * 4)][(int) (Math.random() * 13)];
				pl1.cardDeck[i].isTaken = true;
			}

			pl1.cardDeck = Card.numberFirstSortedDeck(pl1.cardDeck);
			pl1.showStats();
			Functions.TotalCardCombination(pl1);
			pl1.showCombinations();*/
		}
	}


class GameSource {
	Player[] player;
	public static int leftDraw = 7;
	public static int Hidden=0;
}