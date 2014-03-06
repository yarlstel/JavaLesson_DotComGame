import java.util.*;
public class DotComBust {
	// Объявляем и инициализируем переменные, которые нам понадобятся
	private GameHelper helper = new GameHelper();
	private ArrayList<DotCom> dotComsList = new ArrayList<DotCom>();
	private int numOfGuesses = 0;
	
	private void setUpGame() {
		// Создадим несколько "сайтов" и присвоим им адреса
		// Создаём три объекта DotCom, даём им имена и помещаем в ArrayList
		DotCom one = new DotCom();
		one.setName("Pets.com");
		DotCom two = new DotCom();
		two.setName("eToys.com");
		DotCom three = new DotCom();
		three.setName("Go2.com");
		dotComsList.add(one);
		dotComsList.add(two);
		dotComsList.add(three);
		
		// Выводим краткие инструкции для пользователя
		System.out.println("Ваша цель - потопить три \"сайта\".");
		System.out.println("Pets.com, eToys.com, Go2.com");
		System.out.println("Попытайтесь потопить их за минимальное количество ходов");
		
		// Повторяем с каждым объектом DotCom в списке
		for (DotCom dotComToSet : dotComsList) {
			// Запрашиваем у вспомогательного объекта адрес "сайта"
			ArrayList<String> newLocation = helper.placeDotCom(3);
			// Вызываем сеттер из объекта DotCom, чтобы передать ему местоположение, которое только что получили от вспомогательного объекта
			dotComToSet.setLocationCells(newLocation);			
		}
	}
	private void startPlaying() {
		// До тех пор, пока список объектов DotCom не станет пустым
		while(!dotComsList.isEmpty()) {
			// Получаем пользовательский ввод
			String userGuess = helper.getUserInput("Сделайте ход");
			// Вызываем наш метод checkUserGuess
			checkUserGuess(userGuess);
		}
		// Вызываем наш метод finishGame
		finishGame();
	}
	
	private void checkUserGuess(String userGuess) {
		// Инкрементируем количество попыток, которые сделал пользователь
		numOfGuesses++;
		// Подразумеваем промах, пока не выяснили обратного
		String result = "Мимо";
		
		//Повторяем это для всех объектов DotCom в списке
		for (DotCom dotComToTest : dotComsList) {
			// Просим DotCom проверить ход пользователя, ищем попадание (или потопление)
			result = dotComToTest.checkYourself(userGuess);
			if (result.equals("Попал")) {
				// Выбираемся из цикла раньше времени, нет смысла проверять другие "сайты"
				break;
			}
			if (result.equals("Потопил")) {
				// Ему пришёл конец, так что удаляем его из списка "сайтов" и выходим из цикла
				dotComsList.remove(dotComToTest);
				break;
			}
		}
		// Выводим для пользователя результат
		System.out.println(result);
	}
	
	private void finishGame() {
		//  Выводим сообщение о том, как пользователь прошёл игру
		System.out.println("Все \"сайты\" ушли ко дну! Ваши акции теперь ничего не стоят.");
		if (numOfGuesses <= 18) {
			System.out.println("Это заняло у вас всго " + numOfGuesses + " попыток.");
			System.out.println("Вы успели выбраться до того, как ваши вложения утонули.");
		} else {
			System.out.println("Это заняло у вас довольно много времени. " + numOfGuesses+ " попыток.");
			System.out.println("Рыбы водят хороводы вокруг ваших вложений.");
		}		
	}
	
	public static void main(String [] args) {
		// Создаём игровой объект
		DotComBust game = new DotComBust();
		// Говорим игровому объекту подготовить игру
		game.setUpGame();
		// Говорим игровому объекту начать главный игровой цикл (продолжаем запрашивать пользовательский ввод и проверять полученные данные)
		game.startPlaying();
	}
}