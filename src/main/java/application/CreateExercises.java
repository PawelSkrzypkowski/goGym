package application;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InvalidClassException;
import java.util.LinkedList;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.diary.Exercise;
import model.diary.Workout;

/**
 * Klasa używana przy pierwszym włączeniu apliakcji do utworzenia plików z ćwiczeniami i przykładowym treningiem.
 * @author Paweł Skrzypkowski
 *
 */
public class CreateExercises {
	Stage stage = new Stage();
	/**
	 * Metoda tworząca okno oraz ładująca ćwiczenia i trening
	 */
	public void start() {
		try {
			VBox root = new VBox();
			root.setPadding(new Insets(20));
			Scene scene = new Scene(root, 250, 100);
			scene.getStylesheets().add(getClass().getResource("/application.css").toExternalForm());
			stage.setScene(scene);
			stage.setResizable(false);
			stage.setTitle("Ładowanie plików");
			stage.getIcons().add(new Image((getClass().getResource("/images/icon.png").toExternalForm())));
			stage.show();
			Label descr = new Label("Ćwiczenia załadowane"), descr2 = new Label("Przykładowy trening załadowany");
			root.getChildren().addAll(descr, descr2);
			descr.setVisible(false);
			descr2.setVisible(false);
			if(loadExercises() == true){
				descr.setVisible(true);
			}
			if(loadWorkout()){
				descr2.setVisible(true);
			}
			Main newStart = new Main();
			newStart.start(new Stage());
		} catch (IOException | ClassNotFoundException e) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Informacja");
			alert.setHeaderText("");
			alert.setContentText("Błąd: " + e.toString() + ". Ładowanie plików nie powiodło się.");
			alert.showAndWait();
		}
	}
	/**
	 * Metoda tworząca przykładowy trening
	 * @return true
	 * @throws InvalidClassException
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public boolean loadWorkout() throws InvalidClassException, FileNotFoundException, ClassNotFoundException, IOException{
		Workout exampleWorkout = new Workout("3-dniowy Full Body Workout", "Trening FBW, czyli Full Body Workout, jest to opcja treningu niemalże dla każdego adepta siłowni. Znajduje swoje zastosowanie w treningu rozbudowującym masę mięśniową, redukującym tkankę tłuszczową, zwiększającym siłę. Możemy stosować go również jako opcję treningu obwodowego. Dobrze ułożony i wykonywany plan FBW kompleksowo zadba o rozwój wszystkich partii mięśniowych naszego ciała.", "FBW", "początkujący");
		LinkedList<Exercise> exercises = new LinkedList<Exercise>();
		LinkedList<Integer> sets = new LinkedList<Integer>();
		LinkedList<Integer> rest = new LinkedList<Integer>();
		exercises.add(Exercise.readExercise("PRZYSIADY ZE SZTANGĄ TRZYMANĄ Z PRZODU"));
		exercises.add(Exercise.readExercise("PODCIĄGANIE NA DRĄŻKU SZEROKIM UCHWYTEM (NACHWYT)"));
		exercises.add(Exercise.readExercise("ROZPIĘTKI ZE SZTANGIELKAMI W LEŻENIU NA ŁAWCE SKOŚNEJ - GŁOWĄ DO GÓRY"));
		exercises.add(Exercise.readExercise("Wyciskanie sztangi zza głowy"));
		exercises.add(Exercise.readExercise("PROSTOWANIE RAMION NA WYCIĄGU STOJĄC"));
		exercises.add(Exercise.readExercise("UGINANIE RAMION ZE SZTANGIELKAMI STOJĄC PODCHWYTEM(Z „SUPINACJĄ” NADGARSTKA)"));
		exercises.add(Exercise.readExercise("SKŁONY TUŁOWIA Z LINKĄ WYCIĄGU KLĘCZĄC"));
		for(int i=0;i<7;i++){
			sets.add(4);
			rest.add(60);
		}
		exampleWorkout.setExercises(exercises);
		exampleWorkout.setSetsNumber(sets);
		exampleWorkout.setRest(rest);
		exampleWorkout.saveWorkout();
		return true;
	}
	/**
	 * Metoda tworząca ćwiczenia
	 * @return true
	 * @throws IOException
	 */
	public boolean loadExercises() throws IOException {
		// Barki

		new Exercise("Wyciskanie sztangi sprzed głowy",
				"Ćwiczenie to można wykonywać zarówno w pozycji stojącej (tzw. żołnierskie wyciskanie), jak i siedzącej. Do ćwiczenia można również użyć tzw. suwnicy Smitha.",
				new String[] { "przednie i boczne aktony mięsni naramiennych", "mięśnie trójgłowe ramion" })
				.saveExercise();

		new Exercise("Wyciskanie sztangi zza głowy",
				"Ćwiczenie to można wykonywać zarówno w pozycji stojącej (tzw. żołnierskie wyciskanie), jak i siedzącej. Do ćwiczenia można również użyć tzw. suwnicy Smitha.",
				new String[] { "przednie i boczne  aktony mięśni naramiennych", "mięśnie trojgłowe ramion" })
				.saveExercise();

		new Exercise("Wyciskanie sztangielek",
				"kolejne ćwiczenie, które można wykonywać zarówno w pozycji stojącej, jak i siedzącej  - dłonie ze sztangielkami przez cały czas trzymamy tak, aby ich wewnętrzne części skierowane były do przodu(inna wersja przewiduje uchwyt młotkowy-dłonie zwrócone w czasie całego ruchu palcami w kierunku głowy).Ruch powinien odbywać się  pod pełną kontrolą ciężaru-ważna jest również pozycja podczas ćwiczenia-podobna do pozycji przy wyciskaniu sztangi. (wypchnięta klatka, naturalna krzywizna kręgosłupa)",
				new String[] { "przednie i boczne aktony mięśni naramiennych", "mięśnie trójgłowe ramion" })
				.saveExercise();

		new Exercise("Unoszenie sztangielek bokiem w górę",
				"ćwiczenie można wykonywać w pozycji stojącej lub siedzącej, oburącz lub jednorącz. W pozycji wyjściowej tułów lekko pochylony, ręce ze sztangielkami nieco ugięte w łokciach, opuszczone w dół, dłonie wewnętrznymi stronami skierowane do środka. Ruch unoszenia rozpoczynamy przy ugiętych rękach, łokcie w każdej fazie ruchu wyprzedzają dłonie. Sztangielki unosimy powyżej linii barków i bez zatrzymania opuszczamy powoli w dół (lub przytrzymujemy w pozycji szczytowej przez chwilę w celu dodatkowego napięcia mięśni). Ćwiczenie to można również wykonywać jednorącz sztangielką w odchyleniu-chwytamy się poręczy, drabinek lub jakiegoś innego przyrządu i odchylamy tułów w bok (jedna ręką trzymamy się poręczy, a w drugiej trzymamy sztangielkę) i unosimy sztangielkę bokiem w górę do poziomu (w tym punkcie można zatrzymać ruch na chwilę) następnie opuszczamy ją do pozycji wyjściowej.",
				new String[] { "środkowe aktony  mięśni naramiennych" }).saveExercise();

		new Exercise("Unoszenie sztangielek w opadzie tułowia",
				"ćwiczenie wykonujemy w pozycji siedzącej lub stojącej -W pozycji stojącej: tułów ustawiamy w położeniu zbliżonym do prostopadłego do podłoża i staramy się w trakcie ruchu nie wykonywać nim tzw. bujania-utrzymujemy możliwie sztywno. Z pozycji wyjściowej ruchem kolistym unosimy sztangielki maksymalnie w górę, utrzymując przez cały czas ćwiczone mięśnie w stanie napięcia. Staramy się, aby w ruchu powrotnym sztangielki poruszały się po tym samym torze. Jak w poprzednim ćwiczeniu ,można w pozycji szczytowej przytrzymać przez chwilę sztangielki w celu dodatkowego napięcia mięśni.",
				new String[] { "tylna część mięśni naramiennych" }).saveExercise();

		new Exercise("Podciąganie sztangi wzdłuż tułowia",
				"w pozycji stojącej rozkrok nieco szerszy od rozstawu barków, uchwyt na szerokość ramion (inna wersja przewiduje szerszy rozstaw dłoni, nawet szerszy niż rozstaw barków-zaangażowane są wtedy bardziej boczne aktony mięsni naramiennych, a ruch kończymy na wysokości klatki piersiowej). Ruchem wolnym i kontrolowanym unosimy sztangę w kierunku brody, starając się aby przemieszczała się możliwie najbliżej tułowia. Łokcie przez cały czas trzymamy powyżej gryfu sztangi. Ruch unoszenia kończymy, gdy sztanga znajdzie się na wysokości klatki piersiowej (lub staramy się podciągnąć sztangę aż do brody), opuszczamy również powoli, unikamy odchylania i bujania tułowia. Koncentrujemy się na unoszeniu łokci, a sztanga podąży za nimi.",
				new String[] { "wszystkie aktony mięśni naramiennych",
						"mięsień czworoboczny grzbietu (tzw. kapturowy)" }).saveExercise();

		new Exercise("Unoszenie ramion w przód ze sztangą",
				"ćwiczenie wykonujemy w staniu -rozkrok nieco szerzej od barków, uchwyt na szerokość barków-ułożenie dłoni zależy od rodzaju sztangi ,z jaką wykonujemy ćwiczenie: można zastosować nachwyt (przy użyciu sztangi prostej), nachwyt pod kątem (przy użyciu sztangi łamanej), uchwyt młotkowy (przy użyciu „kratownicy”)- rozbudowuje się dodatkowo obszar połączenia m. piersiowych z naramiennymi .Unosimy sztangę miarowym ruchem (bez szarpania) ponad poziom barków i opuszczamy również płynnym ruchem. Staramy się nie bujać tułowiem. W szczytowym położeniu można zatrzymać ruch na chwilę w celu dodatkowego napięcia mięśni. W fazie negatywnej (opuszczanie sztangi) utrzymujemy pełną kontrolę nad ciężarem.",
				new String[] { "przednie i boczne aktony mięśni naramiennych" }).saveExercise();

		new Exercise("Unoszenie ramion ze sztangielkami w leżeniu",
				"ćwiczenie wykonujemy w pozycji leżącej na podłodze lub ławeczce. Ruch zaczynamy nieco poniżej poziomu i miarowo unosimy sztangielkę do momentu, kiedy poczujemy brak napięcia w mięśniach barków, czyli mniej więcej do pionu. Można stosować uchwyt jak na Gifie (pracują tylne i boczne aktony mięśni naramiennych), lecz można zastosować chwyt kciukiem do dołu, separujemy wtedy bardziej tylne aktony mięśni naramiennych).",
				new String[] { "boczne i tylne aktony mięśni naramiennych" }).saveExercise();

		new Exercise("Odwrotne rozpiętki (na maszynie)",
				"przed rozpoczęciem ćwiczenia ustawiamy wysokość siedzenia, tak aby ręce poruszały się równolegle do podłoża. Stosujemy chwyt neutralny (młotkowy). Chwytamy rączki przyrządu, robimy wdech i wstrzymujemy oddech na czas odwodzenia ramion do tyłu. Dochodzimy do momentu, w którym łokcie znajdą się tuż za plecami. Jeśli nie możemy odciągnąć łokci odpowiednio daleko do tyłu, powinniśmy zmniejszyć obciążenie.",
				new String[] { "tylne aktony mięśni naramiennych." }).saveExercise();

		// Klatka piersiowa
		new Exercise("WYCISKANIE SZTANGI W LEŻENIU NA ŁAWCE POZIOMEJ",
				"Kładziemy się na ławce tak, by nogi ugięte były pod kątem prostym i przylegały do podłoża. Uchwyt średni(taki, by po opuszczeniu sztangi na klatkę ramiona tworzyły z przedramionami kąt prosty-kciuk dla bezpieczeństwa powinien obejmować sztangę-choć wielu bardziej doświadczonych kulturystów preferuje raczej tzw. ”małpi chwyt” (kciuk ponad gryfem).Opuszczamy sztangę na klatkę na wysokość ok.1 cm powyżej brodawek. Przy opuszczaniu sztangi wykonujemy głęboki wdech-wydychamy powietrze w trakcie wyciskania. Można okresowo praktykować przytrzymywanie sztangi przez chwilę na klatce przed wyciśnięciem.(szczególnie przydatne, jeżeli mamy w planach ewentualne starty w zawodach w wyciskaniu)-dodatkowo rozbudowuje siłę-pobudza do dodatkowego wysiłku. Łokcie prowadzimy w trakcie całego ruchu po bokach-tak by nie „uciekały”do środka. Ruch wyciskania kończymy(dla lepszego napięcia mięśni)zanim łokcie zostaną zblokowane.",
				new String[] { "cała grupa mięśni klatki piersiowej", "mięśnie trójgłowe ramion",
						"przednie aktony mięśni naramiennych" }).saveExercise();

		new Exercise("WYCISKANIE SZTANGI W LEŻENIU NA ŁAWCE SKOŚNEJ-GŁOWĄ  W GÓRĘ",
				"Ławkę ustawiamy pod kątem 30-45 stopni(większy kąt bardziej angażuje w ćwiczeniu mięśnie naramienne), kładziemy się głową do góry. Uchwyt sztangi, oddychanie, prowadzenie łokci, jak w ćwiczeniu na ławce płaskiej. Chwytamy sztangę i opuszczamy ją na klatkę-ok.10 cm. poniżej szyi. Staramy się skupiać uwagę na angażowaniu w pracę tylko mięsni piersiowych i maksymalnym wyłączeniu z niej mięsni naramiennych. W tym celu można lekko wygiąć grzbiet, jednocześnie wypychając klatkę do przodu. Nie wolno jednak odrywać zbytnio pleców od ławki, a biodra muszą bezwzględnie przylegać do ławki. ",
				new String[] { "mięśnie trójgłowe ramion", "przednie aktony mięśni naramiennych " }).saveExercise();

		new Exercise("WYCISKANIE SZTANGI W LEŻENIU NA ŁAWCE SKOŚNEJ-GŁOWĄ W DÓŁ",
				"Ławeczkę ustawiamy pod kątem 30-45 stopni- kładziemy się głową w dół, zapierając nogi o coś dla stabilności,(aby nie zsunąć się w dół podczas wykonywania ćwiczenia).Ruch wygląda tak samo, jak podczas wyciskania na ławce poziomej. Opuszczając sztangę nabieramy głęboko powietrza, wydychamy je wyciskając sztangę w górę. Ćwiczenie można wykonywać również przy pomocy suwnicy Smitha lub maszyn.",
				new String[] { "cała grupa mięsni piersiowych z podkreśleniem dolnych rejonów",
						"mięśnie trójgłowe ramion", "przednie aktony mięśni naramiennych" }).saveExercise();

		new Exercise("WYCISKANIE SZTANGIELEK W LEŻENIU NA ŁAWCE SKOŚNEJ-GŁOWĄ W DÓŁ",
				"Pozycja, jak w ćwiczeniu z użyciem sztangi-dodatkowe możliwości: np. chcąc położyć większy nacisk na rozwój wewnętrznej części klatki można w górnym położeniu(podczas fazy wyciskania) zbliżać sztangielki do siebie, co nie jest możliwe przy użyciu sztangi. Również faza opuszczania może mieć nieco odmienny przebieg - przy ćwiczeniu ze sztangą ruch ogranicza nam gryf sztangi, a wykorzystując do tego ćwiczenia sztangielki możemy opuszczać ręce poniżej (głębiej) poziomu klatki, co dodatkowo rozciąga mięsnie( w myśl zasady: im większy zakres ruchu, tym pełniejszy rozwój mięśni). Dodatkowo można w końcowej fazie ruchu obracać nadgarstki tak, by dłonie skierowane były do siebie palcami(pozwala to na dodatkowe napięcie wewnętrznych części mięśni).",
				new String[] { "cała grupa mięsni piersiowych z podkreśleniem dolnych rejonów",
						"mięśnie trójgłowe ramion", "przednie aktony mięśni naramiennych" }).saveExercise();

		new Exercise("ROZPIĘTKI ZE SZTANGIELKAMI W LEŻENIU NA ŁAWCE POZIOMEJ",
				"Ćwiczenie rozpoczynamy z ramionami wyprostowanymi-prostopadłymi do podłoża( palce dłoni skierowane są do siebie), a w trakcie ruchu lekko uginamy je w łokciach. Nabieramy powietrza, gdy sztangielki są u góry(na początku ruchu) wydychamy je, gdy sztangielki wędrują do góry. W końcowej fazie ruch można zatrzymać na chwilę w celu lepszego napięcia mięśni. Staramy się nie uderzać sztangielkami o siebie, ale zatrzymywać ruch zanim się zetkną. Ważne jest wykonywanie pełnego zakresu ruchu(by dostatecznie rozciągnąć mięśnie)-im większy zakres wykonanego ruchu, tym pełniejszy ogólny rozwój mięśnia. Obciążenia dostosowujemy takie, by  wykonywać ćwiczenie poprawnie technicznie. Ćwiczenie to można również wykonywać przy pomocy linek wyciągów,lub specjalnej maszyny",
				new String[] { "mięsień piersiowy większy(jego wewnętrzna i zewnętrzna część)",
						"mięsień kruczoramienny", "przednie aktony mięśni naramiennych", "piersiowy mniejszy",
						"zębaty przedni" }).saveExercise();

		new Exercise("ROZPIĘTKI ZE SZTANGIELKAMI W LEŻENIU NA ŁAWCE SKOŚNEJ - GŁOWĄ DO GÓRY",
				"Ławeczkę ustawiamy pod kątem 30-45 stopni, ćwiczenie rozpoczynamy z ramionami wyprostowanymi-prostopadłymi do podłoża( palce dłoni skierowane są do siebie), a w trakcie ruchu lekko uginamy je w łokciach. Nabieramy powietrza, gdy sztangielki są u góry(na początku ruchu) wydychamy je, gdy sztangielki wędrują do góry.Ważne jest wykonywanie pełnego zakresu ruchu(by dostatecznie rozciągnąć mięśnie)-im większy zakres wykonanego ruchu, tym pełniejszy ogólny rozwój mięśnia. Obciążenia dostosowujemy takie, by wykonywać ćwiczenie poprawnie technicznie. Ćwiczenie również można wykonywać zastępując sztangielki rączkami wyciągów.",
				new String[] { "mięsień piersiowy większy(jego górna wewnętrzna i zewnętrzna część)",
						"mięsień kruczoramienny i przednie aktony mięśni naramiennych", "piersiowy mniejszy",
						"zębaty przedni" }).saveExercise();

		new Exercise("WYCISKANIE SZTANGI W LEŻENIU NA ŁAWCE POZIOMEJ WĄSKIM UCHWYTEM",
				"Aby ćwiczenie to angażowało głównie mięśnie piersiowe, a dopiero w drugim stopniu trójgłowe ramion, należy prowadzić łokcie możliwie najdalej na boki od tułowia i koncentrować się na pracy mięsni klatki, a nie ramion. Pozycja na ławce i oddychanie, jak przy wyciskaniu w szerokim uchwycie. Uchwyt na szerokość barków lub odrobinę węższy. Ćwiczenie można wykonywać również na suwnicy Smitha",
				new String[] { "wewnętrzna część mięśnia", "przednie aktony mięśni naramiennych",
						"mięśnie trójgłowe ramion" }).saveExercise();

		new Exercise("PRZENOSZENIE SZTANGIELKI W LEŻENIU W POPRZEK ŁAWKI POZIOMEJ",
				"Kładziemy się w poprzek ławki poziomej-w ten sposób, by do jej powierzchni przylegała jedynie część pleców w okolicy łopatek i karku. Chwytamy sztangielkę pod talerzami(gryf sztangielki pomiędzy kciukami i palcami wskazującymi).Ramiona lekko ugięte podczas całego ruchu-ich prostowanie w trakcie ćwiczenia angażuje dodatkowo mięsnie trójgłowe ramion i najszersze grzbietu. Sztangielkę opuszczamy do tyłu w dół do momentu maksymalnego wychylenia, w jakim możemy kontrolować ciężar. Bardzo istotne jest w tym ćwiczeniu oddychanie- opuszczając w tył sztangielkę-nabieramy mocno powietrza( maksymalnie rozszerzając klatkę)-w drodze powrotnej wypuszczamy je. Dla lepszego zaangażowania mięśni zębatych można przy opuszczaniu sztangielki w tył jednocześnie obniżyć biodra, co dodatkowo rozciągnie tułów i powiększy zakres ruchu. Pamiętać należy również o koncentracji na pracy mięśni piersiowych i wyeliminowaniu pracy mięśni grzbietu. Można to ćwiczenie również wykonywać leżąc wzdłuż ławki, ale wtedy zakres ruchu jest mniejszy.",
				new String[] { "cała grupa mięśni piersiowych", "mięśnie najszersze grzbietu" }).saveExercise();

		new Exercise("POMPKI NA PORĘCZACH",
				"W tym ćwiczeniu, podobnie jak przy wyciskaniu wąsko również ważne jest by pracę wykonywały w głównym stopniu mięśnie piersiowe, w mniejszym stopniu chodzi nam o pracę mięśni trójgłowych ramion. Elementem decydującym o większym zaangażowaniu jednych, bądź drugich mięśni jest pozycja tułowia i ułożenie łokci. Należy wypracować takie ułożenie tułowia, przy którym główną pracę będą wykonywały mięśnie piersiowe, a łokcie pracować powinny w pewnym oddaleniu od tułowia. Dla lepszego wyeliminowania pracy tricepsów i lepszego napięcia mięsni piersiowych można również nie prostować ramion do końca.",
				new String[] { "cała grupa mięśni klatki piersiowej", "przednie aktony mięśni naramiennych",
						"mięśnie trójgłowe ramion" }).saveExercise();

		new Exercise("ROZPIĘTKI W SIADZIE NA MASZYNIE",
				"ważne jest zajęcie dobrej pozycji do ćwiczenia(odpowiednia regulacja wysokości siedzenia-ramiona powinny tworzyć z przedramionami kąt prosty),przedramiona na całej długości wraz z łokciami powinny przylegać do poduszek oporowych.Przed rozpoczęciem ruchu robimy wdech ,a powietrze wydychamy podczas zbliżania ramion do siebie.W pozycji końcowej(ramiona najbliżej siebie)można wstrzymać ruch na 1-2 sekundy(dla większego napięcia mięśni).Ruch powrotny powinien odbywać się pod pełna kontrolą.",
				new String[] { "mięsień piersiowy większy",
						"mięsień kruczoramienny i przednie aktony mięśni naramiennych" }).saveExercise();

		new Exercise("KRZYŻOWANIE LINEK WYCIĄGU W STANIU",
				"Stajemy pomiędzy dwoma górnymi wyciągami( w tzw.”bramie”)-tułów lekko pochylony, co daje lepszą separację mięsni piersiowych. Chwytamy rączki wyciągów i ściągamy je do wewnątrz w dół. Do tego ćwiczenia należy używać umiarkowanych ciężarów i wykonywać pełen zakres ruchu. Wstrzymanie ruchu w końcowej fazie pozwoli otrzymać lepsze napięcie mięśni i poprawi wyrazistość szczegółów umięśnienia.",
				new String[] { "cała grupa mięsni piersiowych", "przednie aktony mięśni naramiennych" }).saveExercise();

		// Plecy
		new Exercise("PODCIĄGANIE NA DRĄŻKU SZEROKIM UCHWYTEM (NACHWYT)",
				"Nie ma drugiego takiego ćwiczenia pod względem wszechstronności rozwoju mięsni grzbietu. Ćwiczenie to można wykonywać do karku i do brody, lecz wersja do karku jest mniej naturalna dla stawów. Chwytamy drążek nachwytem na szerokość taką, by po podciągnięciu ramiona z przedramionami tworzyły kąt prosty(w przybliżeniu).Nogi ugięte w kolanach(dla lepszej stabilności można je spleść).Łokcie pracują w płaszczyźnie pleców-w jednej linii. Wdech robimy przed rozpoczęciem ruchu podciągania-wydech dopiero, gdy jesteśmy już u góry. Ruch podciągania kończymy w momencie, gdy nasza broda(lub kark) jest na wysokości drążka lub nieco ponad nim. Opuszczamy się wolno i pod pełną kontrolą. Jeśli jesteśmy bardziej zaawansowani i możemy wykonać wiele powtórzeń w tym ćwiczeniu, to można zastosować dodatkowe obciążenie.",
				new String[] { "mięśnie najszersze grzbietu", "obłe mniejsze", "obłe większe", "podgrzebieniowe",
						"dwugłowe ramion" }).saveExercise();

		new Exercise("PODCIĄGANIE NA DRĄŻKU W UCHWYCIE NEUTRALNYM",
				"Chwytamy specjalny uchwyt(może to być rączka trójkątna od wyciągu przerzucona nad drążkiem prostym)-dłonie równolegle do siebie w odległości ok.20-25cm.,palcami skierowane do siebie. Z pełnego zwisu podciągamy się do linii podmostkowej. Nogi zwisają luźno, lekko podkurczone w kolanach. Łokcie staramy się prowadzić wzdłuż tułowia. Opuszczamy się powoli-kontrolując swój ciężar. Jeśli jesteśmy bardziej zaawansowani i możemy wykonać wiele powtórzeń w tym ćwiczeniu, to można zastosować dodatkowe obciążenie.",
				new String[] { "mięśnie obłe mniejsze", "obłe większe", "podgrzebieniowe", "najszersze grzbietu",
						"dwugłowe ramion" }).saveExercise();

		new Exercise("PODCIĄGANIE NA DRĄŻKU PODCHWYTEM",
				"Chwytamy drążek prosty podchwytem. Nogi zwisają luźno, lekko podkurczone w kolanach. Ze zwisu przechodzimy do podciągania. Kończymy je, gdy nasza broda znajdzie się ponad drążkiem, a nasze ramiona będą w pełni ugięte w łokciach. Łokcie staramy się prowadzić wzdłuż tułowia. Opuszczamy się powoli-kontrolując swój ciężar. Jeśli jesteśmy bardziej zaawansowani i możemy wykonać wiele powtórzeń w tym ćwiczeniu, to można zastosować dodatkowe obciążenie.",
				new String[] { "najszersze grzbietu", "obłe mniejsze", "obłe większe", "podgrzebieniowe",
						"dwugłowe ramion" }).saveExercise();

		new Exercise("PODCIĄGANIE SZTANGI W OPADZIE(WIOSŁOWANIE)",
				"Stajemy nad sztangą w rozkroku na szerokość barków, pochylamy tułów do pozycji prawie równoległej do podłoża, plecy w dolnym odcinku mocno ugięte do środka, nogi lekko ugięte w kolanach przez cały czas trwania ćwiczenia. Wdech bierzemy w momencie rozpoczęcia podciągania-wydech dopiero, gdy sztanga dochodzi do brzucha(lub do klatki). Sztangę chwytamy na szerokość nieco większą od barków i podciągamy ją do brzucha(łokcie prowadzimy na boki).Druga wersja zakłada podciąganie sztangi do klatki piersiowej(jest to ruch odwrotny do wyciskania sztangi na ławce)-angażowane są mocniej w tej wersji mięśnie czworoboczne grzbietu, obłe większe, mniejsze i tylne aktony mięśni naramiennych. Ćwiczenie to można również wykonywać przy pomocy suwnicy Smitha. Opuszczamy ciężar z pełną kontrolą, wolnym tempem. Jeszcze inna wersja zakłada zastosowanie w tym ćwiczeniu podchwytu(można zastosować wtedy, dla lepszych efektów sztangę łamaną).Ta wersja z kolei mocniej angażuje dolne rejony ćwiczonych mięśni.",
				new String[] { "najszersze grzbietu", "obłe mniejsze", "obłe większe", "podgrzebieniowe",
						"czworoboczne", "mięśnie równoległoboczne" }).saveExercise();

		new Exercise("PODCIĄGANIE KOŃCA SZTANGI W OPADZIE",
				"Stajemy okrakiem nad gryfem sztangi(półsztangi)i chwytamy drążek, tułów z udami tworzą kąt prosty, a z podłogą nieco większy. Nogi lekko ugięte w kolanach. W takiej pozycji podciągamy sztangę do brzucha. Opuszczanie ciężaru kontrolowane. Wdech bierzemy w momencie rozpoczęcia podciągania-wydech dopiero, gdy sztanga dochodzi do brzucha. W zależności od kąta, pod jakim chcemy zaatakować mięśnie używamy w tym ćwiczeniu różnych drążków(chwytamy je różnym uchwytem).I tak np. może to być drążek sztangi typu „T” (mocniej zaangażowane górne części mięsni najszerszych i mięśni obłych-łokcie prowadzimy w bok od tułowia.)lub drążek/rączka równoległa(łokcie prowadzone wzdłuż tułowia-mocniej zaangażowane środkowe części mięsni najszerszych i obłych).Możne też ćwiczenie to wykonywać w leżeniu na ławce skośnej(mniej angażuje mięśnie dolnego odcinka grzbietu, mocniej izoluje mięśnie najszersze)",
				new String[] { "najszersze grzbietu", "obłe mniejsze", "obłe większe", "podgrzebieniowe",
						"czworoboczne", "mięśnie równoległoboczne" }).saveExercise();

		new Exercise("PRZYCIĄGANIE LINKI WYCIĄGU DOLNEGO W SIADZIE PŁASKIM",
				"Siadamy płasko przed wyciągiem dolnym nogi zaparte o stabilny punkt oparcia i chwytamy rączkę wyciągu. Przyciągamy ją do brzucha, utrzymując przez cały czas tułów w pozycji pionowej. W końcowej fazie ruchu staramy się ściągnąć łopatki ku sobie. Po czym powoli, kontrolując ruch opuszczamy ciężar. Wdech przed rozpoczęciem przyciągania- wydech, gdy rączka jest przy brzuchu. Rączka może być różna(uchwyt w związku z tym również może być różny. Rączka równoległa(trójkątna)pozwala na wykonanie ćwiczenia z uchwytem „młotkowym”. Angażuje on mięsnie górnej i środkowej części grzbietu. Rączka/drążek prosta/y pozwala na uchwyt nachwytem lub podchwytem, szeroko lub wąsko, co również angażuje pod różnym kątem mięsnie grzbietu. Nachwyt wąski i szeroki izoluje bardziej górną część mięsni grzbietu(szczególnie najszerszych i obłych), podchwyt wąski i szeroki angażuje mocniej środkową i dolną część tych mięśni. W ćwiczeniach szerokim uchwytem łokcie prowadzone są na boki, we wszystkich innych odmianach ćwiczenia prowadzone są przy tułowiu. Ćwiczenie to można wykonywać również jednorącz.",
				new String[] { "najszersze grzbietu", "obłe większe", "obłe mniejsze", "mięśnie równoległoboczne" })
				.saveExercise();

		new Exercise("ŚCIĄGANIE DRĄŻKA lub RĄCZKI WYCIĄGU GÓRNEGO W SIADZIE SZEROKIM UCHWYTEM (NACHWYT)",
				"Siadamy na siodełku pod wyciągiem górnym, chwytamy rączkę/drążek nachwytem na szerokość taką, jak przy podciąganiu na drążku i przyciągamy ją do klatki lub karku, w zależności od wersji, jaką wykonujemy. Obie wersje angażują te same mięsnie, ale pod nieco innymi kątami. Łopatki ściągamy do siebie, jednocześnie łokcie przywodząc do tyłu. Przy przyciąganiu do klatki tułów nieco odchylony do tyłu, a przy drugiej wersji(do karku)-tułów w pionie. W dolnym położeniu przytrzymujemy drążek na chwilę dla lepszego napięcia mięsni. Opuszczanie ciężaru kontrolowane. Stałe napięcie w ćwiczonych mięśniach i „czucie” ich pracy.",
				new String[] { "mięśnie najszersze grzbietu", "obłe mniejsze", "obłe większe", "podgrzebieniowe" })
				.saveExercise();

		new Exercise("PRZNOSZENIE SZTANGI W LEŻENIU NA ŁAWCE POZIOMEJ",
				"Machanie sztangaJest to ćwiczenie podobne do przenoszenia sztangielki, jednak zastosowanie sztangi zmienia nieco kąt, pod jakim pracują ramiona, a co za tym idzie lepiej angażuje do pracy mięśnie grzbietu, przy jednoczesnym zmniejszeniu zaangażowania mięsni klatki piersiowej. Jeśli jednak decydujemy się na wykonanie ćwiczenia z użyciem sztangielki, to musimy pamiętać, by wykonywać je na ugiętych i ułożonych równolegle do tułowia(nie na boki, jak w ćwiczeniu na klatkę piersiową) ramionach. Pozwala to na lepsze zaangażowanie mięsni grzbietu. Przy wersji ze sztangą należy samemu zadecydować, jakie ułożenie ciała(wzdłuż, czy w poprzek)jest dla nas najlepsze. Można ćwiczenie to wykonywać z ramionami wyprostowanymi, lub(co zdecydowanie zmniejsza naprężenia w stawach łokciowych)na ugiętych ramionach(podchwytem i nachwytem). Ruch opuszczania jest bardzo istotny i powinien być wykonany z maksymalną koncentracją i pod pełną kontrolą ciężaru. Sztangę opuszczamy do pełnego rozciągnięcia mięsni grzbietu. Unoszenie kończymy, gdy ramiona znajdą się w pozycji pionowej do podłoża. Ćwiczenie można również wykonać zastępując sztangę rączką/drążkiem wyciągu dolnego znajdującego się za naszą głową lub przy pomocy specjalnej maszyny",
				new String[] { "dolne partie mięśni najszerszych grzbietu", "mięśnie zębate przednie",
						"mięśnie piersiowe" }).saveExercise();

		new Exercise("PODCIĄGANIE (WIOSŁOWANIE) W LEŻENIU NA ŁAWECZCE POZIOMEJ",
				"Jest to ćwiczenie podobne do wiosłowania w opadzie tułowia, ale odciąża ono dolny odcinek mięsni grzbietu-szczególnie polecane dla osób, które mają kłopoty z tą właśnie częścią. Technika podobna jak w ćwiczeniu w opadzie. Tułów oparty o ławkę poziomą. Łokcie przy ćwiczeniu ze sztangą prowadzimy w bok od tułowia, a w wersji ze sztangielkami wzdłuż tułowia(zaangażowanie mięśni analogicznie, jak przy ćwiczeniu w opadzie-z wyłączeniem pracy dolnego odcinka grzbietu). Ćwiczenie to można również wykonać na ławce skośnej. Zaangażowane będą te same mięsnie jednak pod innym kątem.",
				new String[] { "mięśnie najszersze grzbietu", "obłe mniejsze", "obłe większe" }).saveExercise();

		new Exercise("SKŁONY ZE SZTANGĄ TRZYMANĄ NA KARKU",
				"Stajemy w rozkroku nieco większym niż szerokość barków. Sztangę kładziemy na górnej części mięsni czworobocznych grzbietu. Głowa lekko wygięta do tyłu, ale bez przesady-zbytnie wyginanie głowy może być przyczyną kontuzji. Tułów wyprostowany, klatka piersiowa wypchnięta ku przodowi, łopatki ściągnięte do siebie. Nogi lekko ugięte w kolanach przez cały czas trwania ćwiczenia. Z takiej pozycji wykonujemy skłon do pozycji zbliżonej do poziomego ułożenia tułowia względem podłogi. Bez zatrzymania, ale nie szarpiąc unosimy tułów do pozycji wyjściowej. Ćwiczenie można wykonywać przy pomocy suwnicy Smitha. Ruch powinien być płynny i kontrolowany.",
				new String[] { "prostowniki grzbietu", "dwugłowe ud", "pośladkowe" }).saveExercise();

		// Biceps
		new Exercise("UGINANIE RAMION ZE SZTANGĄ STOJAC PODCHWYTEM",
				"Stajemy w rozkroku(na szerokość barków lub nieco szerzej)-sztangę chwytamy w zależności od tego, którą głowę bicepsu chcemy zaangażować bardziej. I tak odpowiednio:\n-uchwyt wąski(węższy niż szerokość ramion)-większe zaangażowanie głów krótkich,\n-uchwyt średni(na szerokość ramion)-obie głowy zaangażowane w równym stopniu,\n-uchwyt szeroki(szerszy od ramion)-większe zaangażowanie głów długich.\nTułów podczas ćwiczenia utrzymujemy w pozycji wyprostowanej(bez bujania nim). Zakres ruchu: od pełnego rozgięcia bicepsów(nie ramion)do pełnego ich skurczu. Pełne rozciągnięcie bicepsów, to nie to samo, co pełny wyprost ramion. Należy unikać(nie tylko w tym ćwiczeniu) tzw. ”przeprostów” ramion, czyli nadmiernego ich wyprostowywania(do pełnego zakresu ruchu w stawie łokciowym).Łokcie przez cały czas przylegają do tułowia-nie powinny uciekać na boki, ani w przód, gdyż powoduje to zaangażowanie innych mięśni do pracy. Powietrza nabieramy w pozycji wyjściowej, wypuszczamy je dopiero po przejściu ciężaru przez najtrudniejszy punkt ruchu. W pozycji końcowej można zatrzymać na chwilę ciężar dla lepszego ukrwienia mięśnia, ale pod warunkiem utrzymania bicepsów w pełnym napięciu. Należy pamiętać, że ruch opuszczania musi być w pełni kontrolowany i wolniejszy od unoszenia. Do ćwiczenia można używać zarówno sztangi prostej, jak i łamanej-gryf łamany zmniejsza napięcia powstające w nadgarstkach.",
				new String[] { "mięśnie dwugłowe ramion", "mięśnie przedramion" }).saveExercise();

		new Exercise("UGINANIE RAMION ZE SZTANGIELKAMI STOJĄC PODCHWYTEM(Z „SUPINACJĄ” NADGARSTKA)",
				"Ćwiczenie to można wykonywać zarówno w pozycji stojącej, jak i siedzącej. Bardzo dobre, jako rozgrzewkowe przed ciężkimi seriami ze sztangą, ale również jako samodzielne ćwiczenie rozwijające masę i kształt bicepsów. Polecane wykonanie z „supinacją” nadgarstka. Polega ona na stopniowym obracaniu dłoni w trakcie wykonywania ćwiczenia. W pozycji wyjściowej( ramiona wyprostowane) dłonie zwrócone są ku sobie palcami, a w miarę uginania ramion obracają się tak, by w końcowym momencie ruchu(przy zgiętych ramionach)małe palce były wyżej od kciuków. Daje to dodatkowe napięcie mięśni oraz kształtuje kulistość bicepsów. Prostowanie ramienia kończymy w momencie rozciągnięcia mięśni dwugłowych(nie do pełnej możliwości stawu łokciowego). Ruch można wykonywać na przemian-raz jedna ręka, raz druga(po 1 powtórzeniu), obiema rękami jednocześnie, lub opuszczając jedną rękę- jednocześnie unosząc drugą. Samemu trzeba wybrać, która wersja jest dla nas najefektywniejsza. Ułożenie łokci jak w ćwiczeniu 1-ze sztangą. Można również pominąć supinację nadgarstka, ale zmniejsza to efektywność ćwiczenia.",
				new String[] { "dwugłowe ramion", "ramienno promieniowe", "mięsnie przedramion" }).saveExercise();

		new Exercise("UGINANIE RAMION ZE SZTANGĄ NA „MODLITEWNIKU”",
				"Ćwiczenie zarówno na rozwój masy, jak i „wypiętrzenie” bicepsów, a to za sprawą mięśnia ramiennego, położonego pod dwugłowym, który wypycha go do góry. Siadamy na siodełku modlitewnika. Nogi rozstawiamy w taki sposób, by pozwoliły nam utrzymać stabilną pozycję. Górna krawędź modlitewnika powinna znaleźć się pod naszymi pachami. Ramiona rozstawione na szerokość barków-równolegle do siebie. Rozstaw dłoni, podobnie jak w ćwiczeniu ze sztanga stojąc-w zależności od celu ćwiczenia(zaangażowanie poszczególnych głów, jak w ćwiczeniu ze sztangą stojąc). Zakres ruchu: od pełnego rozgięcia bicepsów(nie ramion)do pełnego ich skurczu, przy czym przedramiona nie powinny przekraczać linii pionu. Pełne rozciągnięcie bicepsów, to nie to samo, co pełny wyprost ramion. Należy unikać(nie tylko w tym ćwiczeniu) tzw. ”przeprostów” ramion, czyli nadmiernego ich wyprostowywania(do pełnego zakresu ruchu w stawie łokciowym).Faza negatywna ruchu-prostowanie ramion powinna odbywać się przy pełnej kontroli ciężaru. Opuszczamy sztangę wolniej niż unosimy. Oddychanie jak w ćwiczeniu ze sztangą stojąc. Do ćwiczenia można używać zarówno sztangi prostej, jak i łamanej-gryf łamany zmniejsza napięcia powstające w nadgarstkach. Ćwiczenie to można wykonywać również zastępując sztangę drążkiem wyciągu dolnego, lub na specjalnych maszynach.",
				new String[] { "mięsnie dwugłowe ramion", "mięśnie ramienne", "mięśnie ramienno promieniowe" })
				.saveExercise();

		new Exercise("UGINANIE RAMION ZE SZTANGIELKAMI W SIADZIE NA ŁAWCE SKOŚNEJ(Z SUPINACJĄ NADGARSTKA)",
				"Jest to jedno z tych ćwiczeń, które rozwijają zarówno masę mięśni dwugłowych, jak i charakterystyczny zaokrąglony kształt. Trzeba je wykonywać z dużą koncentracją. Siadamy na ławce skośnej, o nachyleniu ok.45 stopni. Przedramiona powinny być lekko odchylone od tułowia, a łokcie przylegać do niego. Wymodelowaniu kształtu mięśni służy „supinacja” nadgarstka. Polega ona na stopniowym obracaniu dłoni w trakcie wykonywania ćwiczenia. W pozycji wyjściowej( ramiona wyprostowane) dłonie zwrócone są ku sobie palcami, a w miarę uginania ramion obracają się tak, by w końcowym momencie ruchu(przy zgiętych ramionach)małe palce były wyżej od kciuków. Wskazane dla lepszego rozwoju bicepsów jest zatrzymanie ruchu w końcowym położeniu i maksymalne napięcie mięśni przez 1-3 sekundy. Ruch można wykonywać na przemian-raz jedna ręka, raz druga(po 1 powtórzeniu), obiema rękami jednocześnie, lub opuszczając jedną rękę jednocześnie unosząc drugą. Samemu trzeba wybrać, która wersja jest dla nas najefektywniejsza.",
				new String[] { "mięsnie dwugłowe ramion", "mięśnie ramienne", "mięśnie przedramion",
						"mięśnie ramienno promieniowe" }).saveExercise();

		new Exercise("UGINANIE RAMIENIA ZE SZTANGIELKĄ W SIADZIE-W PODPORZE O KOLANO",
				"Jest to ćwiczenie modelujące kształt bicepsów-ich wierzchołek. Wymaga ono dużej koncentracji w czasie wykonywania. Siadamy na ławce lub krześle, pochylamy się lekko do przodu. Chwytamy sztangielkę w dłoń i opieramy łokieć o wewnętrzną część uda. Ruch powinien mieć wolne tempo(zarówno podczas unoszenia i opuszczania)-jest to ćwiczenie koncentryczne i technika jest w nim ważniejsza od wielkości ciężaru. Można również w tym ćwiczeniu stosować supinację nadgarstka. Ramiona „zamykamy” do końca, napinając maksymalnie mięsień. Prostujemy ramię(jak w innych ćwiczeniach na mięśnie dwugłowe)tylko do momentu pełnego rozciągnięcia bicepsów, nie do pełnego zakresu ruchu w stawie łokciowym.",
				new String[] { "mięsnie dwugłowe ramion", "mięśnie ramienne", "mięśnie przedramion" }).saveExercise();

		new Exercise("UGINANIE RAMION ZE SZTANGA NACHWYTEM STOJĄC",
				"Stajemy w rozkroku na szerokość barków(lub nieco szerszym) sztangę chwytamy nachwytem. Łokcie nieruchomo przy tułowiu, nadgarstki zblokowane w jednej pozycji przez cały czas ćwiczenia. Ruch odbywa się tylko w stawach łokciowych. Opuszczanie odbywa się wolnym tempem i pod pełną kontrolą ciężaru. Oddychanie, jak w ćwiczeniu ze sztangą podchwytem. Ćwiczenie to można wykonywać również zastępując sztangę drążkiem wyciągu dolnego.",
				new String[] { "mięsnie ramienno promieniowe(umieszczone na wierzchu przedramion)",
						"mięśnie dwugłowe ramion", "mięśnie przedramion" }).saveExercise();

		new Exercise("UGINANIE NADGARSTKÓW PODCHWYTEM W SIADZIE",
				"Ćwiczenie to rozwija wewnętrzną część przedramion-podstawowe dla rozwoju tej partii mięśni. Wyrabia siłę uścisku dłoni. Łapiemy sztangę w siadzie podchwytem, rozstaw dłoni ok. 15 cm(szerszy nadwyręża nadgarstki).Opieramy przedramiona o uda, tak by poza nogi wystawały jedynie nasze dłonie. Pracują tylko nadgarstki. Staramy się, aby zakres ruchu był jak największy i w tym celu pozwalamy w dolnym położeniu na stoczenie się sztangi aż do końców palców - po czym ponownie ściskamy dłoń i zginamy maksymalnie nadgarstek. Ćwiczenie można wykonywać również zastępując sztangę drążkiem wyciągu dolnego, lub sztangielką (jednorącz).",
				new String[] { "grupa mięsni przedramion-zginaczy nadgarstków" }).saveExercise();

		new Exercise("PROSTOWANIE RAMION NA WYCIĄGU STOJĄC",
				"Stajemy w lekkim rozkroku przed wyciągiem, chwytamy rączkę nachwytem na szerokość ok.10-20 cm. Kciuki na rączce(tzw. ”małpi” chwyt).Lekko pochylamy się w przód i naciskamy rączkę wyciągu w dół. Ramiona przyciśnięte do tułowia. Ruch wykonują tylko przedramiona(pracują jedynie stawy łokciowe). Ramiona prostujemy do końca-dla lepszego napięcia mięśni. Nie unosimy łokci, gdy rączka wyciągu jest w górnym położeniu-spowodowałoby to zanik napięcia w tricepsach. Najważniejsze jest stałe utrzymanie napięcia w ćwiczonych mięśniach. Unikamy szarpania-ruch powinien być płynny, a faza opuszczania ciężaru wolniejsza, z pełną kontrolą ciężaru.",
				new String[] { "wszystkie głowy mięśni trójgłowych ramion" }).saveExercise();

		new Exercise("WYCISKANIE „FRANCUSKIE”SZTANGI W SIADZIE",
				"Ćwiczenie to rozwija wszystkie głowy tricepsów ze szczególnym wskazaniem na głowę długa. Można je wykonywać zarówno w pozycji stojącej, jak i siedzącej. W pozycji siedzącej ponadto można zastosować oparcie(np. na ławce skośnej).Polepsza to stabilność, a co za tym idzie pozwala lepiej skoncentrować się na wykonaniu ćwiczenia. Możemy użyć sztangi prostej i łamanej. Łamana ma tę zaletę, że pozwala na zwiększenie zakresu ruchu i zmniejsza naprężenia powstające w nadgarstkach. Łokcie powinny trwać nieruchomo(jak najbliżej głowy) podczas ćwiczenia. Zakres ruchu:od pełnego wyprostu ramion, do pełnego zgięcia w łokciach. Przy pełnym zgięciu ramion, gdy sztanga znajduje się za głową, nie powinno się tracić nad nią kontroli(swobodne opuszczenie jej przeciąża mocno stawy łokciowe, co może prowadzić do kontuzji).Ćwiczenie można wykonywać również zastępując sztangę drążkiem wyciągu dolnego (prostym lub łamanym, bądź grubym sznurem zakończonym węzłami-stosujemy wtedy uchwyt równoległy). Można również wykonywać nieco inną odmianę ćwiczenia ze sztangielką trzymaną oburącz. Przy tej wersji należy pamiętać, o jednakowym angażowaniu w ćwiczenie obu rąk.",
				new String[] { "głowy boczne i przyśrodkowe mięśni trójgłowych ramion" }).saveExercise();

		new Exercise("PROSTOWNIE RAMIENIA ZE SZTANGIELKĄ W OPADZIE TUŁOWIA",
				"Przy wyprostowanych plecach pochylamy się do przodu i opieramy jedną ręką o ławkę. W drugiej trzymamy sztangielkę i unosimy ramię nieco ponad linię pleców(nie niżej)-przedramię prostopadle do podłogi. Z tej pozycji pracując tylko tricepsem prostujemy ramię w łokciu unosząc ciężar aż do pełnego wyprostu. W pozycji końcowej zatrzymujemy na moment ruch dla lepszego napięcia mięśni. Musimy zwracać szczególną uwagę na to, by w trakcie ćwiczenia w ruchu było jedynie przedramię(praca w stawie łokciowym).Nie wolno dopuszczać do bujania ramieniem. Ruch opuszczania wolniejszy od unoszenia, z pełną kontrolą ciężaru.",
				new String[] { "głowy boczne i przyśrodkowe mięśni trójgłowych ramion" }).saveExercise();

		// Uda i pośladki
		new Exercise("PRZYSIADY ZE SZTANGĄ TRZYMANĄ Z PRZODU",
				"Podobna technika, jak przy zwykłych przysiadach. Mocniej jednak angażowane są mięśnie czworogłowe ud-spowodowane jest to pionową pozycją tułowia związaną z położeniem sztangi z przodu. Gryf spoczywa na przedniej części mięśni naramiennych i górnej części klatki piersiowej. Uchwyt na szerokość barków-jeżeli trzymamy sztangę podchwytem,(co jest może mniej wygodne, ale bezpieczniejsze- gif 2a)lub węższy-jeżeli trzymamy gryf nachwytem-ze skrzyżowanymi ramionami",
				new String[] {
						"ćwiczenie to angażuje przede wszystkim głowy boczne i przyśrodkowe mięśni czworogłowych" })
				.saveExercise();

		new Exercise("HACK-PRZYSIADY",
				"Ćwiczenie różni się od zwykłych przysiadów ułożeniem sztangi(z tyłu za plecami, pod pośladkami w wyprostowanych rękach)i, co za tym idzie bardziej pionową pozycją tułowia w trakcie wykonania ćwiczenia. Ze względu na technikę wykonania i ułożenie sztangi ciężar, jaki użyjemy w tym ćwiczeniu będzie mniejszy, niż w zwykłych przysiadach. Pozycja wyjściowa to wyprostowany tułów, klatka wypchnięta ku przodowi, nogi w rozkroku na szerokość barków, ramiona wyprostowane wzdłuż tułowia, w dłoniach gryf sztangi(trzymany za plecami). Z tej pozycji wykonujemy przysiad do momentu, gdy nasze nogi będą ugięte pod kątem 90 stopni lub nieco mniejszym. Jednocześnie wypychamy kolana nieco do przodu, nie odrywając jednak stóp od podłoża-ciężar wypychamy z pięt. Ćwiczenie można również wykonać przy pomocy suwnicy Smitha",
				new String[] { "wszystkie głowy mięsni czworogłowych ud" }).saveExercise();

		new Exercise("SYZYFKI",
				"Ćwiczenie to można wykonywać zarówno bez obciążenia, jak i z nim. Chwytamy wtedy w jedną rękę krążek i kładziemy go sobie na klatce, drugą ręką podpieramy się dla zachowania równowagi czegoś stabilnego. Ćwiczenie polega na wykonaniu przysiadu z jednoczesnym mocnym odchyleniem tułowia do tyłu i wspięciem na palce stóp połączonym z wypchnięciem kolan do przodu-dla lepszego rozciągnięcia mięśni górnej części ud i zwiększenia poziomu trudności ćwiczenia.",
				new String[] { "wszystkie głowy mięśni czworogłowych ud" }).saveExercise();

		new Exercise("PROSTOWNIE NÓG W SIADZIE",
				"siadamy na siodle maszyny(ławki), dobrze gdy mamy oparcie-zapewnia ono lepszą stabilność tułowia, dłońmi chwytamy za uchwyt(lub krawędź) maszyny. Nogi ugięte w kolanach, zaparte o drążek maszyny na stopami(na wysokości kostek). Z tej pozycji wykonujemy ruch prostowania nóg do pełnego wyprostu w stawach kolanowych. W pozycji wyprostowanej zatrzymujemy ruch przez chwilę dla lepszego napięcia mięśni. Po czym powracamy do pozycji wyjściowej. Powrót w tempie wolnym i pod pełną kontrolą ciężaru. Powietrze nabieramy przed rozpoczęciem prostowania, wypuszczamy je, gdy kończymy prostowanie nóg.",
				new String[] { "zaangażowane wszystkie głowy mięśni czworogłowych ud" }).saveExercise();

		new Exercise("UGINANIE NÓG W LEŻENIU",
				"Ćwiczenie to wykonujemy na specjalnej maszynie,lub za pomocą wyciągu i specjalnym opasek na nogi(gif 8). Kładziemy się na brzuchy tak, by poza ławkę wystawały jedynie podudzia poniżej kolan, nogi wyprostowane w kolanach, zaparte o drążek maszyny na wysokości ścięgien Achillesa(nad piętami), dłonie na uchwytach(lub krawędzi) maszyny. Z tej pozycji wykonujemy ruch maksymalnego uginania nóg w kolanach. W końcowym momencie uginania zatrzymujemy ruch przez chwilę dla lepszego napięcia mięśni. Po czym powracamy do pozycji wyjściowej. Powrót w tempie wolnym i pod pełną kontrolą ciężaru. Biodra(jak i reszta tułowia) przez cały czas trwania ćwiczenia przylegają do powierzchni ławki. Powietrze nabieramy przed rozpoczęciem uginania, wypuszczamy je, gdy kończymy uginanie nóg. Ćwiczenie to można wykonać również w pozycji stojąc(jednonóż)",
				new String[] { "mięśnie dwugłowe ud", "mięśnie półścięgniste", "mięśnie smukłe", "mięśnie krawieckie" })
				.saveExercise();

		new Exercise("NOŻYCE",
				"Ćwiczenie to jest odwrotnością poprzedniego-wykrok jest robiony do tyłu zamiast do przodu. Przysiad wykonujemy nie na nodze wykrocznej, ale na zakrocznej-czyli tej, która pozostaje w miejscu. Ćwiczenie to można wykonać ze sztangą, sztangielkami, lub za pomocą suwnicy Smitha",
				new String[] { "przywodziciele krótkie i wielkie", "w mniejszym stopniu głowy boczne",
						"przyśrodkowe mięsni czworogłowych ud" }).saveExercise();

		new Exercise("ŚCIĄGANIE KOLAN W SIADZIE",
				"Ćwiczenie wykonujemy na specjalnej maszynie siedząc. Plecy oparte o oparcie maszyny, nogi ugięte w kolanach pod kątem prostym, oparte o poduszki maszyny. Z tej pozycji wykonujemy ruch łączenie ściągania nóg do wewnątrz, jak w celu złączenia ich ze sobą, pokonując jednocześnie opór maszyny. W pozycji maksymalnego ściągnięcia nóg zatrzymujemy ruch przez chwilę dla lepszego napięcia mięśni. Po czym powracamy do pozycji wyjściowej. Powrót w tempie wolnym i pod pełną kontrolą ciężaru. Powietrze nabieramy przed rozpoczęciem ściągania, wypuszczamy je, gdy kończymy ściąganie-nogi są w położeniu najbliższym sobie.",
				new String[] { "mięśnie przywodziciele wielkie" }).saveExercise();

		new Exercise("PRZYWODZENIE NÓG DO WEWNĄTRZ",
				"Stajemy przy wyciągu dolnym, zakładamy na nogę(na wysokości kostki)specjalną opaskę połączoną z linką wyciągu. Stajemy w takiej odległości od wyciągu, by ruch zaczynał się w momencie, gdy ćwiczona noga odchylona jest od pionu w kierunku wyciągu(gif 15). Z tej pozycji wykonujemy przywodzenie nogi przed sobą, aż do momentu, gdy trenowana noga znajdzie się w pozycji odchylonej od pionu w kierunku przeciwnym do wyciągu. W końcowym momencie( maksymalne wychylenie nogi w górę, do wewnątrz) można zatrzymać ruch na moment, po czym wracamy do pozycji wyjściowej.",
				new String[] { "mięśnie przywodziciele wielkie" }).saveExercise();

		new Exercise("ODWODZENIE NÓG NA ZEWNĄTRZ",
				"Stajemy przy wyciągu dolnym, zakładamy na nogę(na wysokości kostki)specjalną opaskę połączoną z linką wyciągu. Stajemy w takiej odległości od wyciągu, by ruch zaczynał się w momencie, gdy ćwiczona noga odchylona jest od pionu w kierunku wyciągu. Z tej pozycji wykonujemy odwodzenie(nie wymachy, jak to ćwiczenie nazywane jest często)nogi w kierunku przeciwnym do wyciągu. W końcowym momencie( maksymalne wychylenie nogi w górę) można zatrzymać ruch na moment, po czym wracamy do pozycji wyjściowej.",
				new String[] { "przywodziciele wielkie", "przywodziciele długie i smukłe" }).saveExercise();

		// łydki
		new Exercise("WSPIECIA NA PALCE W STANIU",
				"Ćwiczenie to można wykonywać zarówno przy pomocy sztangi, suwnicy Smitha lub specjalnej maszyny. Można wykonywać je również bez obciążenia, a także jednonóż.Sztangę można również zastąpić sztangielką trzymaną w dłoni(po tej samej stronie, co ćwiczona noga: lewa noga- lewa ręka, prawa noga- prawa ręka). Istotnym elementem w tym ćwiczeniu jest użycie grubej podkładki pod palce stóp, która pozwala zwiększyć znacznie zakres ruchu, a co za tym idzie-poprawić efektywność ćwiczenia. Pozycja wyjściowa, to wyprostowany tułów i plecy, nogi wyprostowane w kolanach, rozkrok 25-30 cm, palce stóp(wraz ze stawami łączącymi je ze śródstopiem) na podkładce-mięsnie łydek rozciągnięte maksymalnie. Z takiej pozycji rozpoczynamy wspięcia. Ruch powinien być wolny i dokładny, ze stałym „czuciem” pracy mięśni. Należy unikać odbijania się pięt od podłogi.",
				new String[] { "obie głowy mięsni brzuchatych łydek", "mięśnie płaszczkowate", "strzałkowe długie" })
				.saveExercise();

		new Exercise("WSPIĘCIA NA PALCE NA HACK-MASZYNIE",
				"Ćwiczenie to można wykonywać w pozycji tyłem do maszyny, jak również przodem do maszyny(o ile oczywiście dysponujemy maszyną ze specjalnymi oparciami na barki). Wskazane jest, jak w pozostałych ćwiczeniach na mięsnie łydek, grubej podkładki pod palce.",
				new String[] { "przednie głowy mięśni brzuchatych łydek", "mięśnie płaszczkowate łydek",
						"głowy boczne mięśni brzuchatych", "mięśnie strzałkowe długie" }).saveExercise();

		new Exercise("WYPYCHANIE CIĘŻARU NA MASZYNIE LUB SUWNICY PALCAMI NÓG",
				"Jest to, jakby odwrotna wersja wspięć na Hack-maszynie- odwrotna jest pozycja-głowa znajduje się niżej nóg. Pozycja wyjściowa to siad na siedzisku maszyny/suwnicy, plecy oparte, nogi wyprostowane w kolanach, stopy dotykają do płaszczyzny maszyny/suwnicy tylko palcami i stawami łączącymi je ze sródstopiem, mięśnie łydek rozciągnięte maksymalnie. Z tej pozycji wypychamy ciężar siłą mięsni łydek.",
				new String[] { "przednie głowy mięśni brzuchatych łydek", "mięśnie płaszczkowate łydek",
						"głowy boczne mięśni brzuchatych", "mięśnie strzałkowe długie" }).saveExercise();

		new Exercise("ODWROTNE WSPIĘCIA W STANIU",
				"Ćwiczenie podobne do wspięć na palce- różnica jest taka, że podkładki są pod piętami, a unosimy nie pięty, lecz śródstopia nóg. Pozycja taka, jak przy wspięciach na palce, ale nie rozciągamy mięśni łydek w początkowej fazie-tylko mięśnie piszczelowe.",
				new String[] { "mięśnie piszczelowe" }).saveExercise();

		// przedramiona
		new Exercise("UGINANIE NADGARSTKÓW PODCHWYTEM W SIADZIE",
				"Ćwiczenie to rozwija wewnętrzną część przedramion-podstawowe dla rozwoju tej partii mięśni. Wyrabia siłę uścisku dłoni. Łapiemy sztangę w siadzie podchwytem, rozstaw dłoni ok. 15 cm(szerszy nadwyręża nadgarstki).Opieramy przedramiona o uda, tak by poza nogi wystawały jedynie nasze dłonie. Pracują tylko nadgarstki. Staramy się, aby zakres ruchu był jak największy i w tym celu pozwalamy w dolnym położeniu na stoczenie się sztangi aż do końców palców - po czym ponownie ściskamy dłoń i zginamy maksymalnie nadgarstek. Ćwiczenie można wykonywać również zastępując sztangę drążkiem wyciągu dolnego, lub sztangielką (jednorącz).",
				new String[] { "grupa mięsni przedramion-zginaczy nadgarstków" }).saveExercise();

		new Exercise("UGINANIE NADGARSTKÓW NACHWYTEM W SIADZIE",
				"Pozycja i technika, jak w ćwiczeniu poprzednim(podchwytem)-różnica polega na uchwycie sztangi-w tym ćwiczeniu stosujemy nachwyt i raczej nie pozwalamy na „zjechanie” sztangi na końce palców. Zginając nadgarstki opuszczamy sztangę tak mocno, jak pozwala na to zakres ruchu, po czym unosimy siłą nadgarstków sztangę tak wysoko, jak tylko damy radę. W ruchu pozostaje jedynie nadgarstek.",
				new String[] { "grupa mięsni przedramion-prostowników nadgarstków" }).saveExercise();

		// brzuch
		new Exercise("SKŁONY W LEŻENIU PŁASKO",
				"Kładziemy się na materacu lub ławce. Nogi ugięte, ręce nad głową i unosimy tułów w górę. Pierwsza do góry unosi się głowa, potem barki, a na końcu reszta tułowia. Dla lepszego zaangażowania mięśni skośnych brzucha, w końcowej fazie unoszenia tułowia można wykonywać nim skręty. Jest to jednak wersja trudniejsza i bardziej narażająca na ewentualne kontuzje(mocniej obciąża dolne partie grzbietu).Nabieramy powietrza przed rozpoczęciem ruchu, a wypuszczamy je w trakcie unoszenia tułowia.",
				new String[] { "mięśnie proste brzucha", "skośne brzucha" }).saveExercise();

		new Exercise("SKŁONY W LEŻENIU GŁOWĄ W DÓŁ",
				"Wykonanie jak w ćwiczeniu poprzednim-płasko, ale pozycja wyjściowa jest głową w dół na ławce skośnej. Dla lepszego zaangażowania mięśni skośnych brzucha w końcowej fazie unoszenia tułowia można wykonywać nim skręty. Jest to jednak wersja trudniejsza i bardziej narażająca na ewentualne kontuzje(mocniej obciąża dolne partie grzbietu).",
				new String[] { "mięśnie proste brzucha", "skośne brzucha" }).saveExercise();

		new Exercise("UNOSZENIE NÓG W LEŻENIU NA SKOŚNEJ ŁAWCE",
				"Kładziemy się na ławce poziomej lub skośnej-głową do góry, ramiona za głową(najlepiej jeśli trzymamy jakiś punkt oparcia np. ławkę lub drążek), tułów przylega do podłoża. Z tej pozycji unosimy nogi do klatki jednocześnie zginając je lekko w kolanach. Nabieramy powietrza przed rozpoczęciem ruchu, a wypuszczamy je w trakcie unoszenia nóg. Pod koniec unoszenia można skręcać nieco tułów i biodra dla zaangażowania mięśni skośnych brzucha.",
				new String[] { "mięśnie proste brzucha", "skośne brzucha" }).saveExercise();

		new Exercise("UNOSZENIE NÓG W ZWISIE NA DRĄŻKU",
				"Chwytamy drążek prosty nachwytem lub podchwytem, jeżeli mamy kłopot z dłuższym utrzymaniem się na drążku, możemy zastosować paski. Unosimy nogi jak najwyżej do brody. Można również w tym ćwiczeniu wprowadzić skręty tułowia w końcowej fazie unoszenia nóg, co mocniej zaangażuje do pracy mięśnie skośne brzucha. Jeszcze inna wersja(mocno angażująca mięśnie skośne)polega na jednoczesnym skręcie bioder wraz z unoszeniem nóg. Osoby zaawansowane mogą w tym ćwiczeniu używać dodatkowego obciążenia zamocowanego do nóg, ale tylko jeżeli czują się na siłach-łatwo „przedobrzyć” i nabawić się bolesnej kontuzji. Tempo ruchu umiarkowane, bez zrywów. Im mniejsze ugięcie nóg w kolanach, tym większy stopień trudności ćwiczenia, większe zaangażowanie mięśni zginaczy bioder(przy zmniejszeniu pracy mięsni brzucha) i większe napięcia w dolnym odcinku grzbietu.",
				new String[] { "mięśnie proste brzucha", "skośne brzucha", "mięsnie zębate przednie" }).saveExercise();

		new Exercise("UNOSZENIE NÓG W PODPORZE",
				"Stajemy plecami do specjalnej podpory, ramiona opieramy na poziomych poprzeczkach podpory, dłońmi chwytamy uchwyty, w tym momencie znajdujemy się już ponad podłogą. Z tej pozycji unosimy nogi w górę, w kierunku klatki piersiowej, jednocześnie uginając je w kolanach. Technika podobna, jak w unoszeniu nóg w zwisie, jednak mięśnie zaangażowane pod innym kątem. W tym ćwiczeniu również należy pamiętać o zachowaniu pełnego zakresu ruchu-zmniejszanie go prowadzi do skracania mięśni. Tempo ruchu umiarkowane, bez zrywów. Im mniejsze ugięcie nóg w kolanach, tym większy stopień trudności ćwiczenia, większe zaangażowanie mięśni zginaczy bioder(przy zmniejszeniu pracy mięsni brzucha) i większe napięcia w dolnym odcinku grzbietu.",
				new String[] { "mięśnie proste brzucha", "skośne brzucha," }).saveExercise();

		new Exercise("”SPINANIE”, UNOSZENIE KOLAN W LEŻENIU PŁASKO",
				"Kładziemy się na ławce lub materacu płasko, nogi wyprostowane, ramiona uniesione do góry nad głową(dla lepszej stabilizacji można chwycić nimi za jakiś punkt oparcia- np. ławkę) i z tej pozycji podciągamy kolana do klatki piersiowej. Ćwiczenie to można również wykonywać z dodatkowym obciążeniem w postaci linki wyciągu zahaczonej o nogi",
				new String[] { "mięśnie proste brzucha" }).saveExercise();

		new Exercise("SKŁONY TUŁOWIA Z LINKĄ WYCIĄGU SIEDZĄC",
				"Siadamy na ławce, tułów wyprostowany,(najlepiej z podporą pod plecy),za plecami mamy wyciąg górny(zamiast rączki zaczepiona lina z węzłami na końcach),chwytamy koniec liny(w ten sposób, że otacza nam z tyłu kark), z tej pozycji wykonujemy skłony w przód na taka głębokość, by nie odrywać dolnego odcinka pleców od oparcia, starając się przez cały czas utrzymać dolny odcinek grzbietu wyprostowany. Powrotny ruch kontrolowany i w wolnym tempie. Bardzo podobne działanie ma ćwiczenie wykonane na specjalnej maszynie",
				new String[] { "proste brzucha", "skośne brzucha" }).saveExercise();

		new Exercise("SKRĘTY TUŁOWIA",
				"Ćwiczenie to można wykonać zarówno w pozycji siedzącej, jak i stojącej-na maszynie lub, jeśli takiej nie posiadamy-za pomocą gryfu sztangi(tylko nie „olimpijskiej”- może być zbyt ciężki)zaawansowani mogą pozwolić sobie na użycie pewnego obciążenia, oczywiście z umiarem. Zbyt duże przeciąża dolny odcinek kręgosłupa. W pozycji stojącej- stajemy w rozkroku szerszym niż barki, gryf kładziemy na karku, ramiona oparte szeroko na gryfie. W pozycji siedzącej(na maszynie) chwytamy rączki maszyny, tułów wyprostowany przez cały czas wykonania ćwiczenia, nogi w jednakowej pozycji(ugięte w kolanach i skierowane do przodu-najlepiej, gdy są zaparte- dla lepszej stabilizacji tułowia)w trakcie całego ćwiczenia.",
				new String[] { "skośne brzucha", "proste brzucha", "prostowniki grzbietu" }).saveExercise();

		new Exercise("SKŁONY BOCZNE",
				"Stajemy w lekkim rozkroku(na szerokość barków, lub nieco szerzej),tułów wyprostowany, w jedną rękę chwytamy sztangielkę, drugą zakładamy sobie na głowę(dłonią). Z tej pozycji wykonujemy skłon w kierunku wolnej ręki, napinając mięśnie skośne brzucha. Oddech bierzemy w momencie rozpoczęcia ruchu, wypuszczamy powietrze w momencie maksymalnego skłonu. Powrót do pozycji wyjściowej może być z przekroczeniem linii pionu(tułowia)-zwiększa to napięcie mięśni skośnych. Ruch powinien być płynny i w wolnym tempie, bez gwałtownych szarpnięć. Ćwiczenie to można również wykonać przy pomocy wyciągu dolnego-zastępując nim sztangielkę.",
				new String[] { "skośne brzucha" }).saveExercise();

		new Exercise("SKŁONY TUŁOWIA Z LINKĄ WYCIĄGU KLĘCZĄC",
				"Do tego ćwiczenia znów potrzebna będzie specjalna lina za węzłami na końcach, zastępująca rączkę wyciągu,(jeśli takiej nie posiadamy można ćwiczenie wykonać trzymając rączkę wyciągu nad głową-zmieni się nieco położenie dłoni w ćwiczeniu na mniej wygodne).Klękamy przed wyciągiem górnym, biodra cofnięte do tyłu, chwytamy końce liny tak, by przebiegała ona nad głową, z tej pozycji wykonujemy skłony tułowia w przód pokonując opór wyciągu, jednocześnie napinając mięśnie brzucha. Bardzo istotne jest, by „czuć” właściwą pracę mięśni brzucha-tylko one wykonują prace. Unikamy ruchów ramionami(angażuje to do pracy mięsnie najszersze)-pozostają one w jednakowym położeniu, dłonie obok głowy(lub nad nią). Powrotny ruch kontrolowany i w wolnym tempie. Zalecany ostrożny dobór obciążenia-zbyt duże przeszkadza w poprawnym wykonaniu ćwiczenia i ponadto przeciąża dolne partie grzbietu.",
				new String[] { "proste brzucha", "skośne brzucha" }).saveExercise();

		return true;
	}

	public void closeWindow() {
		stage.close();
	}
}
