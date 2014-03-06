import java.util.*;
public class DotComBust {
	// ���塞 � ���樠�����㥬 ��६����, ����� ��� �����������
	private GameHelper helper = new GameHelper();
	private ArrayList<DotCom> dotComsList = new ArrayList<DotCom>();
	private int numOfGuesses = 0;
	
	private void setUpGame() {
		// �������� ��᪮�쪮 "ᠩ⮢" � ��᢮�� �� ����
		// ������ �� ��ꥪ� DotCom, ��� �� ����� � ����頥� � ArrayList
		DotCom one = new DotCom();
		one.setName("Pets.com");
		DotCom two = new DotCom();
		two.setName("eToys.com");
		DotCom three = new DotCom();
		three.setName("Go2.com");
		dotComsList.add(one);
		dotComsList.add(two);
		dotComsList.add(three);
		
		// �뢮��� ��⪨� ������樨 ��� ���짮��⥫�
		System.out.println("��� 楫� - ��⮯��� �� \"ᠩ�\".");
		System.out.println("Pets.com, eToys.com, Go2.com");
		System.out.println("����⠩��� ��⮯��� �� �� �������쭮� ������⢮ 室��");
		
		// �����塞 � ����� ��ꥪ⮬ DotCom � ᯨ᪥
		for (DotCom dotComToSet : dotComsList) {
			// ����訢��� � �ᯮ����⥫쭮�� ��ꥪ� ���� "ᠩ�"
			ArrayList<String> newLocation = helper.placeDotCom(3);
			// ��뢠�� ���� �� ��ꥪ� DotCom, �⮡� ��।��� ��� ���⮯��������, ���஥ ⮫쪮 �� ����稫� �� �ᯮ����⥫쭮�� ��ꥪ�
			dotComToSet.setLocationCells(newLocation);			
		}
	}
	private void startPlaying() {
		// �� �� ���, ���� ᯨ᮪ ��ꥪ⮢ DotCom �� �⠭�� �����
		while(!dotComsList.isEmpty()) {
			// ����砥� ���짮��⥫�᪨� ����
			String userGuess = helper.getUserInput("������� 室");
			// ��뢠�� ��� ��⮤ checkUserGuess
			checkUserGuess(userGuess);
		}
		// ��뢠�� ��� ��⮤ finishGame
		finishGame();
	}
	
	private void checkUserGuess(String userGuess) {
		// ���६����㥬 ������⢮ ����⮪, ����� ᤥ��� ���짮��⥫�
		numOfGuesses++;
		// ���ࠧ㬥���� �஬��, ���� �� ���᭨�� ���⭮��
		String result = "����";
		
		//�����塞 �� ��� ��� ��ꥪ⮢ DotCom � ᯨ᪥
		for (DotCom dotComToTest : dotComsList) {
			// ��ᨬ DotCom �஢���� 室 ���짮��⥫�, �饬 ��������� (��� ��⮯�����)
			result = dotComToTest.checkYourself(userGuess);
			if (result.equals("�����")) {
				// �롨ࠥ��� �� 横�� ࠭�� �६���, ��� ��᫠ �஢����� ��㣨� "ᠩ��"
				break;
			}
			if (result.equals("��⮯��")) {
				// ��� ���� �����, ⠪ �� 㤠�塞 ��� �� ᯨ᪠ "ᠩ⮢" � ��室�� �� 横��
				dotComsList.remove(dotComToTest);
				break;
			}
		}
		// �뢮��� ��� ���짮��⥫� १����
		System.out.println(result);
	}
	
	private void finishGame() {
		//  �뢮��� ᮮ�饭�� � ⮬, ��� ���짮��⥫� ���� ����
		System.out.println("�� \"ᠩ��\" �諨 �� ���! ��� ��樨 ⥯��� ��祣� �� ����.");
		if (numOfGuesses <= 18) {
			System.out.println("�� ���﫮 � ��� �ᣮ " + numOfGuesses + " ����⮪.");
			System.out.println("�� �ᯥ�� ������� �� ⮣�, ��� ��� �������� �⮭㫨.");
		} else {
			System.out.println("�� ���﫮 � ��� �����쭮 ����� �६���. " + numOfGuesses+ " ����⮪.");
			System.out.println("��� ����� �஢��� ����� ���� ��������.");
		}		
	}
	
	public static void main(String [] args) {
		// ������ ��஢�� ��ꥪ�
		DotComBust game = new DotComBust();
		// ����ਬ ��஢��� ��ꥪ�� �����⮢��� ����
		game.setUpGame();
		// ����ਬ ��஢��� ��ꥪ�� ����� ������ ��஢�� 横� (�த������ ����訢��� ���짮��⥫�᪨� ���� � �஢����� ����祭�� �����)
		game.startPlaying();
	}
}