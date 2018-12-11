package PokerGameHelper;

import java.awt.*;
import java.awt.event.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

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
			c.setLayout(null);
			MenuButtonListener listener = new MenuButtonListener();

			button1.addActionListener(listener);
			button2.addActionListener(listener);
			button3.addActionListener(listener);
			button1.setSize(300,150);
			button1.setLocation(600,225);
			button2.setSize(300,150);
			button2.setLocation(600,425);
			button3.setSize(300,150);
			button3.setLocation(600,625);

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
					String number = JOptionPane.showInputDialog("총 플레이어 수를 입력하세요. (1~8명)").trim();
					try {
						if (0 < Integer.parseInt(number) && 8 >= Integer.parseInt(number)) {
							new GameStart(Integer.parseInt(number)); // 게임 시작 화면 출력
						} else {
							JOptionPane.showMessageDialog(null, "범위 내에서 입력하세요.", "Message", JOptionPane.ERROR_MESSAGE);
							new MainMenu();
						}
					} catch (NumberFormatException formatException) {
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
						new Explanation1(); // 포커 룰 출력
					} else if (b.equals(button2)) {
						new Explanation2(); // 프로그램 설명 출력
					}
				}
			}

			public static class Explanation1 extends JFrame {
				public Explanation1() {
					setTitle("Explanation1");
					Container c = getContentPane();
					JTextArea jarea = new JTextArea("<세븐 포커 룰>\n" + "1. 3장을 받은 뒤 1장을 공개한다.\n"
							+ "2. 턴이 지날 때마다 카드를 한장씩  받고 3턴 동안은 공개한다.\n" + "3. 7번째 카드는 히든 카드이며, 마지막 카드이다.\n"
							+ "4. 5장으로 가장 높은 카드 조합을 만든 사람이 승리한다.\n" + "\n" + "<카드 조합 족보>\n"
							+ "1. 로열 스트레이트 플러시: 무늬가 같은 A, K, Q, J, 10\n" + "2. 스트레이트 플러시: 숫자가 연속되고 무늬가 같은 카드 5장\n"
							+ "3. 포카드: 숫자가 같은 카드 4장\n" + "4. 풀하우스: 트리플과 원 페어가 같이 존재\n"
							+ "5. 플러시: 숫자 상관없이 무늬가 같은 카드 5장\n" + "6. 스트레이트: 숫자가 연속된 카드 5장\n" + "7. 트리플: 숫자가 같은 카드 3장\n"
							+ "8. 투 페어: 원 페어가 2쌍 존재\n" + "9. 원 페어: 숫자가 같은 카드 2장\n" + "10. 하이카드: 숫자가 높은 카드 1장\n"
							+ "Reference: https://kr.pokernews.com/poker-rules/");
					jarea.setEditable(false);
					jarea.setFont(new Font("Gulim", Font.PLAIN, 25));

					c.add(jarea);

					setSize(1500, 1000);
					setVisible(true);
				}
			}

			public static class Explanation2 extends JFrame {
				public Explanation2() {
					setTitle("Explanation2");
					Container c = getContentPane();
					JTextArea jarea = new JTextArea("이 프로그램은 세븐포커 기준입니다.\n" + "\n" + "1. 게임 메뉴\n"
							+ " 게임 메뉴는 크게 \"새 게임 시작\", \"프로그램 설명\", \"종료\"로 구성됩니다.\n"
							+ "새 게임 시작은 포커 게임을 하나 세팅하며, 기능을 실행할 준비를 합니다.\n" + "프로그램 설명은 본 프로그램에 대한 설명을 표시합니다.\n"
							+ "종료는 프로그램을 즉시 종료합니다.\n" + "\n" + "2. 프로그램 기능\n" + " 본 프로그램은 플레이어의 카드를 입력 받아\n"
							+ "플레이어가 가질 수 있는 카드 조합의 경우의 수를 계산합니다.\n" + "계산된 경우의 수를 토대로 각 조합의 확률을 표시합니다.\n"
							+ "경우의 수 계산은 상황마다 맞지 않을 수 있으며, 한계가 존재합니다.\n" + "상대방의 히든 카드가 그 예시입니다.\n"
							+ "본 프로그램은 상대방의 히든 카드가 무엇일지는 고려하지 않습니다.");
					jarea.setEditable(false);
					jarea.setFont(new Font("Gulim", Font.PLAIN, 25));

					c.add(jarea);

					setSize(1500, 1000);
					setVisible(true);
				}
			}
		}

		public static class GameStart extends JFrame {
			static JLabel TurnNum = new JLabel("첫번째 턴");
			static JLabel playerName = new JLabel("자신");
			static final String[] TurnOrder = { "첫", "두", "세", "네", "다섯", "여섯", "일곱"};
			static JButton[] Buttons = { new JButton("카드 입력"), new JButton("경우의 수"), new JButton("전체 상황"), new JButton("게임 포기") };

			public GameStart(int number) {
				setTitle("Game");
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				Container c = getContentPane();
				c.setLayout(new FlowLayout());
				GameButtonsListener listener = new GameButtonsListener();

				Functions.GameSet();
				Functions.PlayerSet(number);

				c.add(TurnNum);
				c.add(playerName);
//				Functions.showSimpleCard(GameSource.player[0], c);
				for (int i = 0; i < Buttons.length; i++) {
					Buttons[i].addActionListener(listener);
					c.add(Buttons[i]);
				}

				setSize(1500, 1000);
				setVisible(true);
			}

			class GameButtonsListener implements ActionListener {
				public void actionPerformed(ActionEvent e) {
					JButton b = (JButton) e.getSource();
					if (b.equals(Buttons[0])) {
						new InsertCard(GameSource.player);
					} else if (b.equals(Buttons[1])) {
						Functions.TotalCardCombination(GameSource.player[GameSource.playeridx]);
						new CombinationCase();
						// 경우의 수 출력
					} else if (b.equals(Buttons[2])) {
						new TotalStats();
						// 전체 상황
					} else if (b.equals(Buttons[3])) {
						GameSource.player[GameSource.playeridx].isDead=true;
						GameSource.DeadPlayer++;
						if(GameSource.playeridx==0) {
							JOptionPane.showMessageDialog(null, "게임이 종료되었습니다.", "Message", NORMAL);
							System.exit(0);
						}else {
							do {
								if(GameSource.DeadPlayer>=GameSource.player.length-1&&GameSource.player.length!=1) {
									JOptionPane.showMessageDialog(null, "게임이 종료되었습니다.", "Message", NORMAL);
									System.exit(0);
								}
								if (GameSource.playeridx == GameSource.player.length-1) {
									if(GameSource.leftDraw==1) {
										JOptionPane.showMessageDialog(null, "게임이 종료되었습니다.", "Message", NORMAL);
										System.exit(0);
										return;
									}else {
										GameSource.playeridx = 0;
										GameSource.leftDraw--;
										TurnNum.setText(TurnOrder[7 - GameSource.leftDraw] + "번째 턴");
										playerName.setText("자신");
									}
								}
								else {
									if (GameSource.playeridx < GameSource.player.length) {
										GameSource.playeridx++;
										playerName.setText("다른 플레이어" + GameSource.playeridx);
									}
								}
							}while(GameSource.player[GameSource.playeridx].isDead);
						}	// 게임 포기
					}
				}
			}

			public static class InsertCard extends JFrame {
				final Integer[] cardNumbers = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13 };
				JComboBox<String> characters = new JComboBox<String>(Card.CardCharacter);
				JComboBox<Integer> numbers = new JComboBox<Integer>(cardNumbers);
				JLabel j1 = new JLabel("문자 모양");
				JLabel j2 = new JLabel("카드 숫자");
				JButton button1 = new JButton("확인");
				JButton button2 = new JButton("취소");
				int characteridx = 0;
				int numberidx = 0;

				public InsertCard(Player[] player) {
					setTitle("Insert");
					setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					Container c = getContentPane();
					c.setLayout(new FlowLayout());
					cardListener listener1 = new cardListener();

					characters.addActionListener(listener1);
					numbers.addActionListener(listener1);
					button1.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							if (((JButton) e.getSource()).equals(button1)) {
								if(Card.TotalDeck[characteridx][numberidx].isTaken) {
									JOptionPane.showMessageDialog(null, "이미 가져간 카드입니다.\n 다른 카드를 선택하세요.", "Message", JOptionPane.ERROR_MESSAGE);
									return;
								}
								Functions.PlayerGetCard(player[GameSource.playeridx], Card.TotalDeck[characteridx][numberidx]);
								do {
									if(GameSource.DeadPlayer>=GameSource.player.length-1&&GameSource.player.length!=1) {
										JOptionPane.showMessageDialog(null, "게임이 종료되었습니다.", "Message", NORMAL);
										System.exit(0);
									}
									if (GameSource.playeridx == player.length-1) {
										if(GameSource.leftDraw==1) {
											JOptionPane.showMessageDialog(null, "게임이 종료되었습니다.", "Message", NORMAL);
											System.exit(0);
											return;
										}else {
											GameSource.playeridx = 0;
											GameSource.leftDraw--;
											TurnNum.setText(TurnOrder[7 - GameSource.leftDraw] + "번째 턴");
											playerName.setText("자신");
										}
									}
									else {
										if (GameSource.playeridx < player.length) {
											GameSource.playeridx++;
											playerName.setText("다른 플레이어" + GameSource.playeridx);
										}
									}
								} while(GameSource.player[GameSource.playeridx].isDead);
								dispose();
							}
						}
					});

					button2.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							if (((JButton) e.getSource()).equals(button2)) {
								dispose();
							}
						}
					});

					c.add(j1);
					c.add(j2);
					c.add(characters);
					c.add(numbers);
					c.add(button1);
					c.add(button2);

					setSize(900, 600);
					setVisible(true);
				}

				class cardListener implements ActionListener {
					public void actionPerformed(ActionEvent e) {
						JComboBox<String> jb1 = (JComboBox<String>) e.getSource();
						JComboBox<Integer> jb2 = (JComboBox<Integer>) e.getSource();
						if (jb1.equals(characters)) {
							characteridx = jb1.getSelectedIndex();
						} else if (jb2.equals(numbers)) {
							numberidx = jb2.getSelectedIndex();
						}
					}
				}
			}

			public static class CombinationCase extends JFrame {
				JLabel arrow = new JLabel("->");
				JTextArea probability = new JTextArea();
				public CombinationCase() {
					setTitle("Cases");
					Container c = getContentPane();
					c.setLayout(new FlowLayout()
							);
					
					JTextArea text = new JTextArea(" 로열 스트레이트 플러쉬: "+GameSource.player[GameSource.playeridx].myComb.RoyalFlush
						+ "\n 스트레이트 플러쉬: "+GameSource.player[GameSource.playeridx].myComb.StraightFlush
						+ "\n 포카드: "+GameSource.player[GameSource.playeridx].myComb.FourCard
						+ "\n 풀하우스: "+GameSource.player[GameSource.playeridx].myComb.FullHouse
						+ "\n 플러쉬: "+GameSource.player[GameSource.playeridx].myComb.Flush
						+ "\n 스트레이트: "+GameSource.player[GameSource.playeridx].myComb.Straight
						+ "\n 트리플: "+GameSource.player[GameSource.playeridx].myComb.Triple
						+ "\n 투 페어: "+GameSource.player[GameSource.playeridx].myComb.TwoPair
						+ "\n 원 페어: "+GameSource.player[GameSource.playeridx].myComb.OnePair
						+ "\n 하이 카드: "+GameSource.player[GameSource.playeridx].myComb.HighCard);
					text.setFont(new Font("Gulim", Font.PLAIN, 15));
					text.setEditable(false);
					probability.setFont(new Font("Gulim", Font.PLAIN, 15));
					probability.setEditable(false);
					probability.setText(" 로열 스트레이트 플러쉬: "+Functions.probability(GameSource.player[GameSource.playeridx].myComb.RoyalFlush)
							+ "%\n 스트레이트 플러쉬: "+Functions.probability(GameSource.player[GameSource.playeridx].myComb.StraightFlush)
							+ "%\n 포카드: "+Functions.probability(GameSource.player[GameSource.playeridx].myComb.FourCard)
							+ "%\n 풀하우스: "+Functions.probability(GameSource.player[GameSource.playeridx].myComb.FullHouse)
							+ "%\n 플러쉬: "+Functions.probability(GameSource.player[GameSource.playeridx].myComb.Flush)
							+ "%\n 스트레이트: "+Functions.probability(GameSource.player[GameSource.playeridx].myComb.Straight)
							+ "%\n 트리플: "+Functions.probability(GameSource.player[GameSource.playeridx].myComb.Triple)
							+ "%\n 투 페어: "+Functions.probability(GameSource.player[GameSource.playeridx].myComb.TwoPair)
							+ "%\n 원 페어: "+Functions.probability(GameSource.player[GameSource.playeridx].myComb.OnePair)
							+"%");
					
					c.add(text);
					c.add(arrow);
					c.add(probability);
					
					setSize(900, 600);
					setVisible(true);
				}
			}

			public static class TotalStats extends JFrame {
				JTextArea[] playerStats=new JTextArea[GameSource.player.length];
				public TotalStats() {
					setTitle("Stats");
					Container c = getContentPane();
					c.setLayout(new FlowLayout());
					
					for(int i=0; i<GameSource.player.length;i++) {
						if(i==0) {
							playerStats[i]=new JTextArea("[자신]\n"+GameSource.player[i].Stats());
						}else {
							playerStats[i]=new JTextArea("[다른 플레이어"+i+"]\n"+GameSource.player[i].Stats());
						}
						playerStats[i].setFont(new Font("Gulim", Font.PLAIN, 15));
						playerStats[i].setEditable(false);
						c.add(playerStats[i]);
					}

					setSize(900, 600);
					setVisible(true);
				}
			}
		}
	}

	public static void main(String[] args) {
		new MainMenu();
	}
}

class GameSource {
	public static int playeridx = 0;
	public static int DeadPlayer = 0;
	public static Player[] player;
	public static int leftDraw = 7;
	public static int Hidden = 0;
}