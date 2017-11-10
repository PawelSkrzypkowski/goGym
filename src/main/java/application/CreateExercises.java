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
 * Klasa u�ywana przy pierwszym w��czeniu apliakcji do utworzenia plik�w z �wiczeniami i przyk�adowym treningiem.
 * @author Pawe� Skrzypkowski
 *
 */
public class CreateExercises {
	Stage stage = new Stage();
	/**
	 * Metoda tworz�ca okno oraz �aduj�ca �wiczenia i trening
	 */
	public void start() {
		try {
			VBox root = new VBox();
			root.setPadding(new Insets(20));
			Scene scene = new Scene(root, 250, 100);
			scene.getStylesheets().add(getClass().getResource("/application.css").toExternalForm());
			stage.setScene(scene);
			stage.setResizable(false);
			stage.setTitle("�adowanie plik�w");
			stage.getIcons().add(new Image((getClass().getResource("/images/icon.png").toExternalForm())));
			stage.show();
			Label descr = new Label("�wiczenia za�adowane"), descr2 = new Label("Przyk�adowy trening za�adowany");
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
			alert.setContentText("B��d: " + e.toString() + ". �adowanie plik�w nie powiod�o si�.");
			alert.showAndWait();
		}
	}
	/**
	 * Metoda tworz�ca przyk��dowy trening
	 * @return true
	 * @throws InvalidClassException
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public boolean loadWorkout() throws InvalidClassException, FileNotFoundException, ClassNotFoundException, IOException{
		Workout exampleWorkout = new Workout("3-dniowy Full Body Workout", "Trening FBW, czyli Full Body Workout, jest to opcja treningu niemal�e dla ka�dego adepta si�owni. Znajduje swoje zastosowanie w treningu rozbudowuj�cym mas� mi�niow�, redukuj�cym tkank� t�uszczow�, zwi�kszaj�cym si��. Mo�emy stosowa� go r�wnie� jako opcj� treningu obwodowego. Dobrze u�o�ony i wykonywany plan FBW kompleksowo zadba o rozw�j wszystkich partii mi�niowych naszego cia�a.", "FBW", "pocz�tkuj�cy");
		LinkedList<Exercise> exercises = new LinkedList<Exercise>();
		LinkedList<Integer> sets = new LinkedList<Integer>();
		LinkedList<Integer> rest = new LinkedList<Integer>();
		exercises.add(Exercise.readExercise("PRZYSIADY ZE SZTANG� TRZYMAN� Z PRZODU"));
		exercises.add(Exercise.readExercise("PODCI�GANIE NA DR��KU SZEROKIM UCHWYTEM (NACHWYT)"));
		exercises.add(Exercise.readExercise("ROZPI�TKI ZE SZTANGIELKAMI W LE�ENIU NA �AWCE SKO�NEJ - G�OW� DO G�RY"));
		exercises.add(Exercise.readExercise("Wyciskanie sztangi zza g�owy"));
		exercises.add(Exercise.readExercise("PROSTOWANIE RAMION NA WYCI�GU STOJ�C"));
		exercises.add(Exercise.readExercise("UGINANIE RAMION ZE SZTANGIELKAMI STOJ�C PODCHWYTEM(Z �SUPINACJ�� NADGARSTKA)"));
		exercises.add(Exercise.readExercise("SK�ONY TU�OWIA Z LINK� WYCI�GU KL�CZ�C"));
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
 * Metoda tworz�ca �wiczenia
 * @return true
 * @throws IOException
 */
	public boolean loadExercises() throws IOException {
		// Barki

		new Exercise("Wyciskanie sztangi sprzed g�owy",
				"�wiczenie to mo�na wykonywa� zar�wno w pozycji stoj�cej (tzw. �o�nierskie wyciskanie), jak i siedz�cej. Do �wiczenia mo�na r�wnie� u�y� tzw. suwnicy Smitha.",
				new String[] { "przednie i boczne aktony mi�sni naramiennych", "mi�nie tr�jg�owe ramion" })
						.saveExercise();

		new Exercise("Wyciskanie sztangi zza g�owy",
				"�wiczenie to mo�na wykonywa� zar�wno w pozycji stoj�cej (tzw. �o�nierskie wyciskanie), jak i siedz�cej. Do �wiczenia mo�na r�wnie� u�y� tzw. suwnicy Smitha.",
				new String[] { "przednie i boczne  aktony mi�ni naramiennych", "mi�nie trojg�owe ramion" })
						.saveExercise();

		new Exercise("Wyciskanie sztangielek",
				"kolejne �wiczenie, kt�re mo�na wykonywa� zar�wno w pozycji stoj�cej, jak i siedz�cej  - d�onie ze sztangielkami przez ca�y czas trzymamy tak, aby ich wewn�trzne cz�ci skierowane by�y do przodu(inna wersja przewiduje uchwyt m�otkowy-d�onie zwr�cone w czasie ca�ego ruchu palcami w kierunku g�owy).Ruch powinien odbywa� si�  pod pe�n� kontrol� ci�aru-wa�na jest r�wnie� pozycja podczas �wiczenia-podobna do pozycji przy wyciskaniu sztangi. (wypchni�ta klatka, naturalna krzywizna kr�gos�upa)",
				new String[] { "przednie i boczne aktony mi�ni naramiennych", "mi�nie tr�jg�owe ramion" })
						.saveExercise();

		new Exercise("Unoszenie sztangielek bokiem w g�r�",
				"�wiczenie mo�na wykonywa� w pozycji stoj�cej lub siedz�cej, obur�cz lub jednor�cz. W pozycji wyj�ciowej tu��w lekko pochylony, r�ce ze sztangielkami nieco ugi�te w �okciach, opuszczone w d�, d�onie wewn�trznymi stronami skierowane do �rodka. Ruch unoszenia rozpoczynamy przy ugi�tych r�kach, �okcie w ka�dej fazie ruchu wyprzedzaj� d�onie. Sztangielki unosimy powy�ej linii bark�w i bez zatrzymania opuszczamy powoli w d� (lub przytrzymujemy w pozycji szczytowej przez chwil� w celu dodatkowego napi�cia mi�ni). �wiczenie to mo�na r�wnie� wykonywa� jednor�cz sztangielk� w odchyleniu-chwytamy si� por�czy, drabinek lub jakiego� innego przyrz�du i odchylamy tu��w w bok (jedna r�k� trzymamy si� por�czy, a w drugiej trzymamy sztangielk�) i unosimy sztangielk� bokiem w g�r� do poziomu (w tym punkcie mo�na zatrzyma� ruch na chwil�) nast�pnie opuszczamy j� do pozycji wyj�ciowej.",
				new String[] { "�rodkowe aktony  mi�ni naramiennych" }).saveExercise();

		new Exercise("Unoszenie sztangielek w opadzie tu�owia",
				"�wiczenie wykonujemy w pozycji siedz�cej lub stoj�cej -W pozycji stoj�cej: tu��w ustawiamy w po�o�eniu zbli�onym do prostopad�ego do pod�o�a i staramy si� w trakcie ruchu nie wykonywa� nim tzw. bujania-utrzymujemy mo�liwie sztywno. Z pozycji wyj�ciowej ruchem kolistym unosimy sztangielki maksymalnie w g�r�, utrzymuj�c przez ca�y czas �wiczone mi�nie w stanie napi�cia. Staramy si�, aby w ruchu powrotnym sztangielki porusza�y si� po tym samym torze. Jak w poprzednim �wiczeniu ,mo�na w pozycji szczytowej przytrzyma� przez chwil� sztangielki w celu dodatkowego napi�cia mi�ni.",
				new String[] { "tylna cz�� mi�ni naramiennych" }).saveExercise();

		new Exercise("Podci�ganie sztangi wzd�u� tu�owia",
				"w pozycji stoj�cej rozkrok nieco szerszy od rozstawu bark�w, uchwyt na szeroko�� ramion (inna wersja przewiduje szerszy rozstaw d�oni, nawet szerszy ni� rozstaw bark�w-zaanga�owane s� wtedy bardziej boczne aktony mi�sni naramiennych, a ruch ko�czymy na wysoko�ci klatki piersiowej). Ruchem wolnym i kontrolowanym unosimy sztang� w kierunku brody, staraj�c si� aby przemieszcza�a si� mo�liwie najbli�ej tu�owia. �okcie przez ca�y czas trzymamy powy�ej gryfu sztangi. Ruch unoszenia ko�czymy, gdy sztanga znajdzie si� na wysoko�ci klatki piersiowej (lub staramy si� podci�gn�� sztang� a� do brody), opuszczamy r�wnie� powoli, unikamy odchylania i bujania tu�owia. Koncentrujemy si� na unoszeniu �okci, a sztanga pod��y za nimi.",
				new String[] { "wszystkie aktony mi�ni naramiennych",
						"mi�sie� czworoboczny grzbietu (tzw. kapturowy)" }).saveExercise();

		new Exercise("Unoszenie ramion w prz�d ze sztang�",
				"�wiczenie wykonujemy w staniu -rozkrok nieco szerzej od bark�w, uchwyt na szeroko�� bark�w-u�o�enie d�oni zale�y od rodzaju sztangi ,z jak� wykonujemy �wiczenie: mo�na zastosowa� nachwyt (przy u�yciu sztangi prostej), nachwyt pod k�tem (przy u�yciu sztangi �amanej), uchwyt m�otkowy (przy u�yciu �kratownicy�)- rozbudowuje si� dodatkowo obszar po��czenia m. piersiowych z naramiennymi .Unosimy sztang� miarowym ruchem (bez szarpania) ponad poziom bark�w i opuszczamy r�wnie� p�ynnym ruchem. Staramy si� nie buja� tu�owiem. W szczytowym po�o�eniu mo�na zatrzyma� ruch na chwil� w celu dodatkowego napi�cia mi�ni. W fazie negatywnej (opuszczanie sztangi) utrzymujemy pe�n� kontrol� nad ci�arem.",
				new String[] { "przednie i boczne aktony mi�ni naramiennych" }).saveExercise();

		new Exercise("Unoszenie ramion ze sztangielkami w le�eniu",
				"�wiczenie wykonujemy w pozycji le��cej na pod�odze lub �aweczce. Ruch zaczynamy nieco poni�ej poziomu i miarowo unosimy sztangielk� do momentu, kiedy poczujemy brak napi�cia w mi�niach bark�w, czyli mniej wi�cej do pionu. Mo�na stosowa� uchwyt jak na Gifie (pracuj� tylne i boczne aktony mi�ni naramiennych), lecz mo�na zastosowa� chwyt kciukiem do do�u, separujemy wtedy bardziej tylne aktony mi�ni naramiennych).",
				new String[] { "boczne i tylne aktony mi�ni naramiennych" }).saveExercise();

		new Exercise("Odwrotne rozpi�tki (na maszynie)",
				"przed rozpocz�ciem �wiczenia ustawiamy wysoko�� siedzenia, tak aby r�ce porusza�y si� r�wnolegle do pod�o�a. Stosujemy chwyt neutralny (m�otkowy). Chwytamy r�czki przyrz�du, robimy wdech i wstrzymujemy oddech na czas odwodzenia ramion do ty�u. Dochodzimy do momentu, w kt�rym �okcie znajd� si� tu� za plecami. Je�li nie mo�emy odci�gn�� �okci odpowiednio daleko do ty�u, powinni�my zmniejszy� obci��enie.",
				new String[] { "tylne aktony mi�ni naramiennych." }).saveExercise();

		// Klatka piersiowa
		new Exercise("WYCISKANIE SZTANGI W LE�ENIU NA �AWCE POZIOMEJ",
				"K�adziemy si� na �awce tak, by nogi ugi�te by�y pod k�tem prostym i przylega�y do pod�o�a. Uchwyt �redni(taki, by po opuszczeniu sztangi na klatk� ramiona tworzy�y z przedramionami k�t prosty-kciuk dla bezpiecze�stwa powinien obejmowa� sztang�-cho� wielu bardziej do�wiadczonych kulturyst�w preferuje raczej tzw. �ma�pi chwyt� (kciuk ponad gryfem).Opuszczamy sztang� na klatk� na wysoko�� ok.1 cm powy�ej brodawek. Przy opuszczaniu sztangi wykonujemy g��boki wdech-wydychamy powietrze w trakcie wyciskania. Mo�na okresowo praktykowa� przytrzymywanie sztangi przez chwil� na klatce przed wyci�ni�ciem.(szczeg�lnie przydatne, je�eli mamy w planach ewentualne starty w zawodach w wyciskaniu)-dodatkowo rozbudowuje si��-pobudza do dodatkowego wysi�ku. �okcie prowadzimy w trakcie ca�ego ruchu po bokach-tak by nie �ucieka�y�do �rodka. Ruch wyciskania ko�czymy(dla lepszego napi�cia mi�ni)zanim �okcie zostan� zblokowane.",
				new String[] { "ca�a grupa mi�ni klatki piersiowej", "mi�nie tr�jg�owe ramion",
						"przednie aktony mi�ni naramiennych" }).saveExercise();

		new Exercise("WYCISKANIE SZTANGI W LE�ENIU NA �AWCE SKO�NEJ-G�OW�  W G�R�",
				"�awk� ustawiamy pod k�tem 30-45 stopni(wi�kszy k�t bardziej anga�uje w �wiczeniu mi�nie naramienne), k�adziemy si� g�ow� do g�ry. Uchwyt sztangi, oddychanie, prowadzenie �okci, jak w �wiczeniu na �awce p�askiej. Chwytamy sztang� i opuszczamy j� na klatk�-ok.10 cm. poni�ej szyi. Staramy si� skupia� uwag� na anga�owaniu w prac� tylko mi�sni piersiowych i maksymalnym wy��czeniu z niej mi�sni naramiennych. W tym celu mo�na lekko wygi�� grzbiet, jednocze�nie wypychaj�c klatk� do przodu. Nie wolno jednak odrywa� zbytnio plec�w od �awki, a biodra musz� bezwzgl�dnie przylega� do �awki. ",
				new String[] { "mi�nie tr�jg�owe ramion", "przednie aktony mi�ni naramiennych " }).saveExercise();

		new Exercise("WYCISKANIE SZTANGI W LE�ENIU NA �AWCE SKO�NEJ-G�OW� W Dӣ",
				"�aweczk� ustawiamy pod k�tem 30-45 stopni- k�adziemy si� g�ow� w d�, zapieraj�c nogi o co� dla stabilno�ci,(aby nie zsun�� si� w d� podczas wykonywania �wiczenia).Ruch wygl�da tak samo, jak podczas wyciskania na �awce poziomej. Opuszczaj�c sztang� nabieramy g��boko powietrza, wydychamy je wyciskaj�c sztang� w g�r�. �wiczenie mo�na wykonywa� r�wnie� przy pomocy suwnicy Smitha lub maszyn.",
				new String[] { "ca�a grupa mi�sni piersiowych z podkre�leniem dolnych rejon�w",
						"mi�nie tr�jg�owe ramion", "przednie aktony mi�ni naramiennych" }).saveExercise();

		new Exercise("WYCISKANIE SZTANGIELEK W LE�ENIU NA �AWCE SKO�NEJ-G�OW� W Dӣ",
				"Pozycja, jak w �wiczeniu z u�yciem sztangi-dodatkowe mo�liwo�ci: np. chc�c po�o�y� wi�kszy nacisk na rozw�j wewn�trznej cz�ci klatki mo�na w g�rnym po�o�eniu(podczas fazy wyciskania) zbli�a� sztangielki do siebie, co nie jest mo�liwe przy u�yciu sztangi. R�wnie� faza opuszczania mo�e mie� nieco odmienny przebieg - przy �wiczeniu ze sztang� ruch ogranicza nam gryf sztangi, a wykorzystuj�c do tego �wiczenia sztangielki mo�emy opuszcza� r�ce poni�ej (g��biej) poziomu klatki, co dodatkowo rozci�ga mi�snie( w my�l zasady: im wi�kszy zakres ruchu, tym pe�niejszy rozw�j mi�ni). Dodatkowo mo�na w ko�cowej fazie ruchu obraca� nadgarstki tak, by d�onie skierowane by�y do siebie palcami(pozwala to na dodatkowe napi�cie wewn�trznych cz�ci mi�ni).",
				new String[] { "ca�a grupa mi�sni piersiowych z podkre�leniem dolnych rejon�w",
						"mi�nie tr�jg�owe ramion", "przednie aktony mi�ni naramiennych" }).saveExercise();

		new Exercise("ROZPI�TKI ZE SZTANGIELKAMI W LE�ENIU NA �AWCE POZIOMEJ",
				"�wiczenie rozpoczynamy z ramionami wyprostowanymi-prostopad�ymi do pod�o�a( palce d�oni skierowane s� do siebie), a w trakcie ruchu lekko uginamy je w �okciach. Nabieramy powietrza, gdy sztangielki s� u g�ry(na pocz�tku ruchu) wydychamy je, gdy sztangielki w�druj� do g�ry. W ko�cowej fazie ruch mo�na zatrzyma� na chwil� w celu lepszego napi�cia mi�ni. Staramy si� nie uderza� sztangielkami o siebie, ale zatrzymywa� ruch zanim si� zetkn�. Wa�ne jest wykonywanie pe�nego zakresu ruchu(by dostatecznie rozci�gn�� mi�nie)-im wi�kszy zakres wykonanego ruchu, tym pe�niejszy og�lny rozw�j mi�nia. Obci��enia dostosowujemy takie, by  wykonywa� �wiczenie poprawnie technicznie. �wiczenie to mo�na r�wnie� wykonywa� przy pomocy linek wyci�g�w,lub specjalnej maszyny",
				new String[] { "mi�sie� piersiowy wi�kszy(jego wewn�trzna i zewn�trzna cz��)",
						"mi�sie� kruczoramienny", "przednie aktony mi�ni naramiennych", "piersiowy mniejszy",
						"z�baty przedni" }).saveExercise();

		new Exercise("ROZPI�TKI ZE SZTANGIELKAMI W LE�ENIU NA �AWCE SKO�NEJ - G�OW� DO G�RY",
				"�aweczk� ustawiamy pod k�tem 30-45 stopni, �wiczenie rozpoczynamy z ramionami wyprostowanymi-prostopad�ymi do pod�o�a( palce d�oni skierowane s� do siebie), a w trakcie ruchu lekko uginamy je w �okciach. Nabieramy powietrza, gdy sztangielki s� u g�ry(na pocz�tku ruchu) wydychamy je, gdy sztangielki w�druj� do g�ry.Wa�ne jest wykonywanie pe�nego zakresu ruchu(by dostatecznie rozci�gn�� mi�nie)-im wi�kszy zakres wykonanego ruchu, tym pe�niejszy og�lny rozw�j mi�nia. Obci��enia dostosowujemy takie, by wykonywa� �wiczenie poprawnie technicznie. �wiczenie r�wnie� mo�na wykonywa� zast�puj�c sztangielki r�czkami wyci�g�w.",
				new String[] { "mi�sie� piersiowy wi�kszy(jego g�rna wewn�trzna i zewn�trzna cz��)",
						"mi�sie� kruczoramienny i przednie aktony mi�ni naramiennych", "piersiowy mniejszy",
						"z�baty przedni" }).saveExercise();

		new Exercise("WYCISKANIE SZTANGI W LE�ENIU NA �AWCE POZIOMEJ W�SKIM UCHWYTEM",
				"Aby �wiczenie to anga�owa�o g��wnie mi�nie piersiowe, a dopiero w drugim stopniu tr�jg�owe ramion, nale�y prowadzi� �okcie mo�liwie najdalej na boki od tu�owia i koncentrowa� si� na pracy mi�sni klatki, a nie ramion. Pozycja na �awce i oddychanie, jak przy wyciskaniu w szerokim uchwycie. Uchwyt na szeroko�� bark�w lub odrobin� w�szy. �wiczenie mo�na wykonywa� r�wnie� na suwnicy Smitha",
				new String[] { "wewn�trzna cz�� mi�nia", "przednie aktony mi�ni naramiennych",
						"mi�nie tr�jg�owe ramion" }).saveExercise();

		new Exercise("PRZENOSZENIE SZTANGIELKI W LE�ENIU W POPRZEK �AWKI POZIOMEJ",
				"K�adziemy si� w poprzek �awki poziomej-w ten spos�b, by do jej powierzchni przylega�a jedynie cz�� plec�w w okolicy �opatek i karku. Chwytamy sztangielk� pod talerzami(gryf sztangielki pomi�dzy kciukami i palcami wskazuj�cymi).Ramiona lekko ugi�te podczas ca�ego ruchu-ich prostowanie w trakcie �wiczenia anga�uje dodatkowo mi�snie tr�jg�owe ramion i najszersze grzbietu. Sztangielk� opuszczamy do ty�u w d� do momentu maksymalnego wychylenia, w jakim mo�emy kontrolowa� ci�ar. Bardzo istotne jest w tym �wiczeniu oddychanie- opuszczaj�c w ty� sztangielk�-nabieramy mocno powietrza( maksymalnie rozszerzaj�c klatk�)-w drodze powrotnej wypuszczamy je. Dla lepszego zaanga�owania mi�ni z�batych mo�na przy opuszczaniu sztangielki w ty� jednocze�nie obni�y� biodra, co dodatkowo rozci�gnie tu��w i powi�kszy zakres ruchu. Pami�ta� nale�y r�wnie� o koncentracji na pracy mi�ni piersiowych i wyeliminowaniu pracy mi�ni grzbietu. Mo�na to �wiczenie r�wnie� wykonywa� le��c wzd�u� �awki, ale wtedy zakres ruchu jest mniejszy.",
				new String[] { "ca�a grupa mi�ni piersiowych", "mi�nie najszersze grzbietu" }).saveExercise();

		new Exercise("POMPKI NA POR�CZACH",
				"W tym �wiczeniu, podobnie jak przy wyciskaniu w�sko r�wnie� wa�ne jest by prac� wykonywa�y w g��wnym stopniu mi�nie piersiowe, w mniejszym stopniu chodzi nam o prac� mi�ni tr�jg�owych ramion. Elementem decyduj�cym o wi�kszym zaanga�owaniu jednych, b�d� drugich mi�ni jest pozycja tu�owia i u�o�enie �okci. Nale�y wypracowa� takie u�o�enie tu�owia, przy kt�rym g��wn� prac� b�d� wykonywa�y mi�nie piersiowe, a �okcie pracowa� powinny w pewnym oddaleniu od tu�owia. Dla lepszego wyeliminowania pracy triceps�w i lepszego napi�cia mi�sni piersiowych mo�na r�wnie� nie prostowa� ramion do ko�ca.",
				new String[] { "ca�a grupa mi�ni klatki piersiowej", "przednie aktony mi�ni naramiennych",
						"mi�nie tr�jg�owe ramion" }).saveExercise();

		new Exercise("ROZPI�TKI W SIADZIE NA MASZYNIE",
				"wa�ne jest zaj�cie dobrej pozycji do �wiczenia(odpowiednia regulacja wysoko�ci siedzenia-ramiona powinny tworzy� z przedramionami k�t prosty),przedramiona na ca�ej d�ugo�ci wraz z �okciami powinny przylega� do poduszek oporowych.Przed rozpocz�ciem ruchu robimy wdech ,a powietrze wydychamy podczas zbli�ania ramion do siebie.W pozycji ko�cowej(ramiona najbli�ej siebie)mo�na wstrzyma� ruch na 1-2 sekundy(dla wi�kszego napi�cia mi�ni).Ruch powrotny powinien odbywa� si� pod pe�na kontrol�.",
				new String[] { "mi�sie� piersiowy wi�kszy",
						"mi�sie� kruczoramienny i przednie aktony mi�ni naramiennych" }).saveExercise();

		new Exercise("KRZY�OWANIE LINEK WYCI�GU W STANIU",
				"Stajemy pomi�dzy dwoma g�rnymi wyci�gami( w tzw.�bramie�)-tu��w lekko pochylony, co daje lepsz� separacj� mi�sni piersiowych. Chwytamy r�czki wyci�g�w i �ci�gamy je do wewn�trz w d�. Do tego �wiczenia nale�y u�ywa� umiarkowanych ci�ar�w i wykonywa� pe�en zakres ruchu. Wstrzymanie ruchu w ko�cowej fazie pozwoli otrzyma� lepsze napi�cie mi�ni i poprawi wyrazisto�� szczeg��w umi�nienia.",
				new String[] { "ca�a grupa mi�sni piersiowych", "przednie aktony mi�ni naramiennych" }).saveExercise();

		// Plecy
		new Exercise("PODCI�GANIE NA DR��KU SZEROKIM UCHWYTEM (NACHWYT)",
				"Nie ma drugiego takiego �wiczenia pod wzgl�dem wszechstronno�ci rozwoju mi�sni grzbietu. �wiczenie to mo�na wykonywa� do karku i do brody, lecz wersja do karku jest mniej naturalna dla staw�w. Chwytamy dr��ek nachwytem na szeroko�� tak�, by po podci�gni�ciu ramiona z przedramionami tworzy�y k�t prosty(w przybli�eniu).Nogi ugi�te w kolanach(dla lepszej stabilno�ci mo�na je sple��).�okcie pracuj� w p�aszczy�nie plec�w-w jednej linii. Wdech robimy przed rozpocz�ciem ruchu podci�gania-wydech dopiero, gdy jeste�my ju� u g�ry. Ruch podci�gania ko�czymy w momencie, gdy nasza broda(lub kark) jest na wysoko�ci dr��ka lub nieco ponad nim. Opuszczamy si� wolno i pod pe�n� kontrol�. Je�li jeste�my bardziej zaawansowani i mo�emy wykona� wiele powt�rze� w tym �wiczeniu, to mo�na zastosowa� dodatkowe obci��enie.",
				new String[] { "mi�nie najszersze grzbietu", "ob�e mniejsze", "ob�e wi�ksze", "podgrzebieniowe",
						"dwug�owe ramion" }).saveExercise();

		new Exercise("PODCI�GANIE NA DR��KU W UCHWYCIE NEUTRALNYM",
				"Chwytamy specjalny uchwyt(mo�e to by� r�czka tr�jk�tna od wyci�gu przerzucona nad dr��kiem prostym)-d�onie r�wnolegle do siebie w odleg�o�ci ok.20-25cm.,palcami skierowane do siebie. Z pe�nego zwisu podci�gamy si� do linii podmostkowej. Nogi zwisaj� lu�no, lekko podkurczone w kolanach. �okcie staramy si� prowadzi� wzd�u� tu�owia. Opuszczamy si� powoli-kontroluj�c sw�j ci�ar. Je�li jeste�my bardziej zaawansowani i mo�emy wykona� wiele powt�rze� w tym �wiczeniu, to mo�na zastosowa� dodatkowe obci��enie.",
				new String[] { "mi�nie ob�e mniejsze", "ob�e wi�ksze", "podgrzebieniowe", "najszersze grzbietu",
						"dwug�owe ramion" }).saveExercise();

		new Exercise("PODCI�GANIE NA DR��KU PODCHWYTEM",
				"Chwytamy dr��ek prosty podchwytem. Nogi zwisaj� lu�no, lekko podkurczone w kolanach. Ze zwisu przechodzimy do podci�gania. Ko�czymy je, gdy nasza broda znajdzie si� ponad dr��kiem, a nasze ramiona b�d� w pe�ni ugi�te w �okciach. �okcie staramy si� prowadzi� wzd�u� tu�owia. Opuszczamy si� powoli-kontroluj�c sw�j ci�ar. Je�li jeste�my bardziej zaawansowani i mo�emy wykona� wiele powt�rze� w tym �wiczeniu, to mo�na zastosowa� dodatkowe obci��enie.",
				new String[] { "najszersze grzbietu", "ob�e mniejsze", "ob�e wi�ksze", "podgrzebieniowe",
						"dwug�owe ramion" }).saveExercise();

		new Exercise("PODCI�GANIE SZTANGI W OPADZIE(WIOS�OWANIE)",
				"Stajemy nad sztang� w rozkroku na szeroko�� bark�w, pochylamy tu��w do pozycji prawie r�wnoleg�ej do pod�o�a, plecy w dolnym odcinku mocno ugi�te do �rodka, nogi lekko ugi�te w kolanach przez ca�y czas trwania �wiczenia. Wdech bierzemy w momencie rozpocz�cia podci�gania-wydech dopiero, gdy sztanga dochodzi do brzucha(lub do klatki). Sztang� chwytamy na szeroko�� nieco wi�ksz� od bark�w i podci�gamy j� do brzucha(�okcie prowadzimy na boki).Druga wersja zak�ada podci�ganie sztangi do klatki piersiowej(jest to ruch odwrotny do wyciskania sztangi na �awce)-anga�owane s� mocniej w tej wersji mi�nie czworoboczne grzbietu, ob�e wi�ksze, mniejsze i tylne aktony mi�ni naramiennych. �wiczenie to mo�na r�wnie� wykonywa� przy pomocy suwnicy Smitha. Opuszczamy ci�ar z pe�n� kontrol�, wolnym tempem. Jeszcze inna wersja zak�ada zastosowanie w tym �wiczeniu podchwytu(mo�na zastosowa� wtedy, dla lepszych efekt�w sztang� �aman�).Ta wersja z kolei mocniej anga�uje dolne rejony �wiczonych mi�ni.",
				new String[] { "najszersze grzbietu", "ob�e mniejsze", "ob�e wi�ksze", "podgrzebieniowe",
						"czworoboczne", "mi�nie r�wnoleg�oboczne" }).saveExercise();

		new Exercise("PODCI�GANIE KO�CA SZTANGI W OPADZIE",
				"Stajemy okrakiem nad gryfem sztangi(p�sztangi)i chwytamy dr��ek, tu��w z udami tworz� k�t prosty, a z pod�og� nieco wi�kszy. Nogi lekko ugi�te w kolanach. W takiej pozycji podci�gamy sztang� do brzucha. Opuszczanie ci�aru kontrolowane. Wdech bierzemy w momencie rozpocz�cia podci�gania-wydech dopiero, gdy sztanga dochodzi do brzucha. W zale�no�ci od k�ta, pod jakim chcemy zaatakowa� mi�nie u�ywamy w tym �wiczeniu r�nych dr��k�w(chwytamy je r�nym uchwytem).I tak np. mo�e to by� dr��ek sztangi typu �T� (mocniej zaanga�owane g�rne cz�ci mi�sni najszerszych i mi�ni ob�ych-�okcie prowadzimy w bok od tu�owia.)lub dr��ek/r�czka r�wnoleg�a(�okcie prowadzone wzd�u� tu�owia-mocniej zaanga�owane �rodkowe cz�ci mi�sni najszerszych i ob�ych).Mo�ne te� �wiczenie to wykonywa� w le�eniu na �awce sko�nej(mniej anga�uje mi�nie dolnego odcinka grzbietu, mocniej izoluje mi�nie najszersze)",
				new String[] { "najszersze grzbietu", "ob�e mniejsze", "ob�e wi�ksze", "podgrzebieniowe",
						"czworoboczne", "mi�nie r�wnoleg�oboczne" }).saveExercise();

		new Exercise("PRZYCI�GANIE LINKI WYCI�GU DOLNEGO W SIADZIE P�ASKIM",
				"Siadamy p�asko przed wyci�giem dolnym nogi zaparte o stabilny punkt oparcia i chwytamy r�czk� wyci�gu. Przyci�gamy j� do brzucha, utrzymuj�c przez ca�y czas tu��w w pozycji pionowej. W ko�cowej fazie ruchu staramy si� �ci�gn�� �opatki ku sobie. Po czym powoli, kontroluj�c ruch opuszczamy ci�ar. Wdech przed rozpocz�ciem przyci�gania- wydech, gdy r�czka jest przy brzuchu. R�czka mo�e by� r�na(uchwyt w zwi�zku z tym r�wnie� mo�e by� r�ny. R�czka r�wnoleg�a(tr�jk�tna)pozwala na wykonanie �wiczenia z uchwytem �m�otkowym�. Anga�uje on mi�snie g�rnej i �rodkowej cz�ci grzbietu. R�czka/dr��ek prosta/y pozwala na uchwyt nachwytem lub podchwytem, szeroko lub w�sko, co r�wnie� anga�uje pod r�nym k�tem mi�snie grzbietu. Nachwyt w�ski i szeroki izoluje bardziej g�rn� cz�� mi�sni grzbietu(szczeg�lnie najszerszych i ob�ych), podchwyt w�ski i szeroki anga�uje mocniej �rodkow� i doln� cz�� tych mi�ni. W �wiczeniach szerokim uchwytem �okcie prowadzone s� na boki, we wszystkich innych odmianach �wiczenia prowadzone s� przy tu�owiu. �wiczenie to mo�na wykonywa� r�wnie� jednor�cz.",
				new String[] { "najszersze grzbietu", "ob�e wi�ksze", "ob�e mniejsze", "mi�nie r�wnoleg�oboczne" })
						.saveExercise();

		new Exercise("�CI�GANIE DR��KA lub R�CZKI WYCI�GU G�RNEGO W SIADZIE SZEROKIM UCHWYTEM (NACHWYT)",
				"Siadamy na siode�ku pod wyci�giem g�rnym, chwytamy r�czk�/dr��ek nachwytem na szeroko�� tak�, jak przy podci�ganiu na dr��ku i przyci�gamy j� do klatki lub karku, w zale�no�ci od wersji, jak� wykonujemy. Obie wersje anga�uj� te same mi�snie, ale pod nieco innymi k�tami. �opatki �ci�gamy do siebie, jednocze�nie �okcie przywodz�c do ty�u. Przy przyci�ganiu do klatki tu��w nieco odchylony do ty�u, a przy drugiej wersji(do karku)-tu��w w pionie. W dolnym po�o�eniu przytrzymujemy dr��ek na chwil� dla lepszego napi�cia mi�sni. Opuszczanie ci�aru kontrolowane. Sta�e napi�cie w �wiczonych mi�niach i �czucie� ich pracy.",
				new String[] { "mi�nie najszersze grzbietu", "ob�e mniejsze", "ob�e wi�ksze", "podgrzebieniowe" })
						.saveExercise();

		new Exercise("PRZNOSZENIE SZTANGI W LE�ENIU NA �AWCE POZIOMEJ",
				"Machanie sztangaJest to �wiczenie podobne do przenoszenia sztangielki, jednak zastosowanie sztangi zmienia nieco k�t, pod jakim pracuj� ramiona, a co za tym idzie lepiej anga�uje do pracy mi�nie grzbietu, przy jednoczesnym zmniejszeniu zaanga�owania mi�sni klatki piersiowej. Je�li jednak decydujemy si� na wykonanie �wiczenia z u�yciem sztangielki, to musimy pami�ta�, by wykonywa� je na ugi�tych i u�o�onych r�wnolegle do tu�owia(nie na boki, jak w �wiczeniu na klatk� piersiow�) ramionach. Pozwala to na lepsze zaanga�owanie mi�sni grzbietu. Przy wersji ze sztang� nale�y samemu zadecydowa�, jakie u�o�enie cia�a(wzd�u�, czy w poprzek)jest dla nas najlepsze. Mo�na �wiczenie to wykonywa� z ramionami wyprostowanymi, lub(co zdecydowanie zmniejsza napr�enia w stawach �okciowych)na ugi�tych ramionach(podchwytem i nachwytem). Ruch opuszczania jest bardzo istotny i powinien by� wykonany z maksymaln� koncentracj� i pod pe�n� kontrol� ci�aru. Sztang� opuszczamy do pe�nego rozci�gni�cia mi�sni grzbietu. Unoszenie ko�czymy, gdy ramiona znajd� si� w pozycji pionowej do pod�o�a. �wiczenie mo�na r�wnie� wykona� zast�puj�c sztang� r�czk�/dr��kiem wyci�gu dolnego znajduj�cego si� za nasz� g�ow� lub przy pomocy specjalnej maszyny",
				new String[] { "dolne partie mi�ni najszerszych grzbietu", "mi�nie z�bate przednie",
						"mi�nie piersiowe" }).saveExercise();

		new Exercise("PODCI�GANIE (WIOS�OWANIE) W LE�ENIU NA �AWECZCE POZIOMEJ",
				"Jest to �wiczenie podobne do wios�owania w opadzie tu�owia, ale odci��a ono dolny odcinek mi�sni grzbietu-szczeg�lnie polecane dla os�b, kt�re maj� k�opoty z t� w�a�nie cz�ci�. Technika podobna jak w �wiczeniu w opadzie. Tu��w oparty o �awk� poziom�. �okcie przy �wiczeniu ze sztang� prowadzimy w bok od tu�owia, a w wersji ze sztangielkami wzd�u� tu�owia(zaanga�owanie mi�ni analogicznie, jak przy �wiczeniu w opadzie-z wy��czeniem pracy dolnego odcinka grzbietu). �wiczenie to mo�na r�wnie� wykona� na �awce sko�nej. Zaanga�owane b�d� te same mi�snie jednak pod innym k�tem.",
				new String[] { "mi�nie najszersze grzbietu", "ob�e mniejsze", "ob�e wi�ksze" }).saveExercise();

		new Exercise("SK�ONY ZE SZTANG� TRZYMAN� NA KARKU",
				"Stajemy w rozkroku nieco wi�kszym ni� szeroko�� bark�w. Sztang� k�adziemy na g�rnej cz�ci mi�sni czworobocznych grzbietu. G�owa lekko wygi�ta do ty�u, ale bez przesady-zbytnie wyginanie g�owy mo�e by� przyczyn� kontuzji. Tu��w wyprostowany, klatka piersiowa wypchni�ta ku przodowi, �opatki �ci�gni�te do siebie. Nogi lekko ugi�te w kolanach przez ca�y czas trwania �wiczenia. Z takiej pozycji wykonujemy sk�on do pozycji zbli�onej do poziomego u�o�enia tu�owia wzgl�dem pod�ogi. Bez zatrzymania, ale nie szarpi�c unosimy tu��w do pozycji wyj�ciowej. �wiczenie mo�na wykonywa� przy pomocy suwnicy Smitha. Ruch powinien by� p�ynny i kontrolowany.",
				new String[] { "prostowniki grzbietu", "dwug�owe ud", "po�ladkowe" }).saveExercise();

		// Biceps
		new Exercise("UGINANIE RAMION ZE SZTANG� STOJAC PODCHWYTEM",
				"Stajemy w rozkroku(na szeroko�� bark�w lub nieco szerzej)-sztang� chwytamy w zale�no�ci od tego, kt�r� g�ow� bicepsu chcemy zaanga�owa� bardziej. I tak odpowiednio:\n-uchwyt w�ski(w�szy ni� szeroko�� ramion)-wi�ksze zaanga�owanie g��w kr�tkich,\n-uchwyt �redni(na szeroko�� ramion)-obie g�owy zaanga�owane w r�wnym stopniu,\n-uchwyt szeroki(szerszy od ramion)-wi�ksze zaanga�owanie g��w d�ugich.\nTu��w podczas �wiczenia utrzymujemy w pozycji wyprostowanej(bez bujania nim). Zakres ruchu: od pe�nego rozgi�cia biceps�w(nie ramion)do pe�nego ich skurczu. Pe�ne rozci�gni�cie biceps�w, to nie to samo, co pe�ny wyprost ramion. Nale�y unika�(nie tylko w tym �wiczeniu) tzw. �przeprost�w� ramion, czyli nadmiernego ich wyprostowywania(do pe�nego zakresu ruchu w stawie �okciowym).�okcie przez ca�y czas przylegaj� do tu�owia-nie powinny ucieka� na boki, ani w prz�d, gdy� powoduje to zaanga�owanie innych mi�ni do pracy. Powietrza nabieramy w pozycji wyj�ciowej, wypuszczamy je dopiero po przej�ciu ci�aru przez najtrudniejszy punkt ruchu. W pozycji ko�cowej mo�na zatrzyma� na chwil� ci�ar dla lepszego ukrwienia mi�nia, ale pod warunkiem utrzymania biceps�w w pe�nym napi�ciu. Nale�y pami�ta�, �e ruch opuszczania musi by� w pe�ni kontrolowany i wolniejszy od unoszenia. Do �wiczenia mo�na u�ywa� zar�wno sztangi prostej, jak i �amanej-gryf �amany zmniejsza napi�cia powstaj�ce w nadgarstkach.",
				new String[] { "mi�nie dwug�owe ramion", "mi�nie przedramion" }).saveExercise();

		new Exercise("UGINANIE RAMION ZE SZTANGIELKAMI STOJ�C PODCHWYTEM(Z �SUPINACJ�� NADGARSTKA)",
				"�wiczenie to mo�na wykonywa� zar�wno w pozycji stoj�cej, jak i siedz�cej. Bardzo dobre, jako rozgrzewkowe przed ci�kimi seriami ze sztang�, ale r�wnie� jako samodzielne �wiczenie rozwijaj�ce mas� i kszta�t biceps�w. Polecane wykonanie z �supinacj�� nadgarstka. Polega ona na stopniowym obracaniu d�oni w trakcie wykonywania �wiczenia. W pozycji wyj�ciowej( ramiona wyprostowane) d�onie zwr�cone s� ku sobie palcami, a w miar� uginania ramion obracaj� si� tak, by w ko�cowym momencie ruchu(przy zgi�tych ramionach)ma�e palce by�y wy�ej od kciuk�w. Daje to dodatkowe napi�cie mi�ni oraz kszta�tuje kulisto�� biceps�w. Prostowanie ramienia ko�czymy w momencie rozci�gni�cia mi�ni dwug�owych(nie do pe�nej mo�liwo�ci stawu �okciowego). Ruch mo�na wykonywa� na przemian-raz jedna r�ka, raz druga(po 1 powt�rzeniu), obiema r�kami jednocze�nie, lub opuszczaj�c jedn� r�k�- jednocze�nie unosz�c drug�. Samemu trzeba wybra�, kt�ra wersja jest dla nas najefektywniejsza. U�o�enie �okci jak w �wiczeniu 1-ze sztang�. Mo�na r�wnie� pomin�� supinacj� nadgarstka, ale zmniejsza to efektywno�� �wiczenia.",
				new String[] { "dwug�owe ramion", "ramienno promieniowe", "mi�snie przedramion" }).saveExercise();

		new Exercise("UGINANIE RAMION ZE SZTANG� NA �MODLITEWNIKU�",
				"�wiczenie zar�wno na rozw�j masy, jak i �wypi�trzenie� biceps�w, a to za spraw� mi�nia ramiennego, po�o�onego pod dwug�owym, kt�ry wypycha go do g�ry. Siadamy na siode�ku modlitewnika. Nogi rozstawiamy w taki spos�b, by pozwoli�y nam utrzyma� stabiln� pozycj�. G�rna kraw�d� modlitewnika powinna znale�� si� pod naszymi pachami. Ramiona rozstawione na szeroko�� bark�w-r�wnolegle do siebie. Rozstaw d�oni, podobnie jak w �wiczeniu ze sztanga stoj�c-w zale�no�ci od celu �wiczenia(zaanga�owanie poszczeg�lnych g��w, jak w �wiczeniu ze sztang� stoj�c). Zakres ruchu: od pe�nego rozgi�cia biceps�w(nie ramion)do pe�nego ich skurczu, przy czym przedramiona nie powinny przekracza� linii pionu. Pe�ne rozci�gni�cie biceps�w, to nie to samo, co pe�ny wyprost ramion. Nale�y unika�(nie tylko w tym �wiczeniu) tzw. �przeprost�w� ramion, czyli nadmiernego ich wyprostowywania(do pe�nego zakresu ruchu w stawie �okciowym).Faza negatywna ruchu-prostowanie ramion powinna odbywa� si� przy pe�nej kontroli ci�aru. Opuszczamy sztang� wolniej ni� unosimy. Oddychanie jak w �wiczeniu ze sztang� stoj�c. Do �wiczenia mo�na u�ywa� zar�wno sztangi prostej, jak i �amanej-gryf �amany zmniejsza napi�cia powstaj�ce w nadgarstkach. �wiczenie to mo�na wykonywa� r�wnie� zast�puj�c sztang� dr��kiem wyci�gu dolnego, lub na specjalnych maszynach.",
				new String[] { "mi�snie dwug�owe ramion", "mi�nie ramienne", "mi�nie ramienno promieniowe" })
						.saveExercise();

		new Exercise("UGINANIE RAMION ZE SZTANGIELKAMI W SIADZIE NA �AWCE SKO�NEJ(Z SUPINACJ� NADGARSTKA)",
				"Jest to jedno z tych �wicze�, kt�re rozwijaj� zar�wno mas� mi�ni dwug�owych, jak i charakterystyczny zaokr�glony kszta�t. Trzeba je wykonywa� z du�� koncentracj�. Siadamy na �awce sko�nej, o nachyleniu ok.45 stopni. Przedramiona powinny by� lekko odchylone od tu�owia, a �okcie przylega� do niego. Wymodelowaniu kszta�tu mi�ni s�u�y �supinacja� nadgarstka. Polega ona na stopniowym obracaniu d�oni w trakcie wykonywania �wiczenia. W pozycji wyj�ciowej( ramiona wyprostowane) d�onie zwr�cone s� ku sobie palcami, a w miar� uginania ramion obracaj� si� tak, by w ko�cowym momencie ruchu(przy zgi�tych ramionach)ma�e palce by�y wy�ej od kciuk�w. Wskazane dla lepszego rozwoju biceps�w jest zatrzymanie ruchu w ko�cowym po�o�eniu i maksymalne napi�cie mi�ni przez 1-3 sekundy. Ruch mo�na wykonywa� na przemian-raz jedna r�ka, raz druga(po 1 powt�rzeniu), obiema r�kami jednocze�nie, lub opuszczaj�c jedn� r�k� jednocze�nie unosz�c drug�. Samemu trzeba wybra�, kt�ra wersja jest dla nas najefektywniejsza.",
				new String[] { "mi�snie dwug�owe ramion", "mi�nie ramienne", "mi�nie przedramion",
						"mi�nie ramienno promieniowe" }).saveExercise();

		new Exercise("UGINANIE RAMIENIA ZE SZTANGIELK� W SIADZIE-W PODPORZE O KOLANO",
				"Jest to �wiczenie modeluj�ce kszta�t biceps�w-ich wierzcho�ek. Wymaga ono du�ej koncentracji w czasie wykonywania. Siadamy na �awce lub krze�le, pochylamy si� lekko do przodu. Chwytamy sztangielk� w d�o� i opieramy �okie� o wewn�trzn� cz�� uda. Ruch powinien mie� wolne tempo(zar�wno podczas unoszenia i opuszczania)-jest to �wiczenie koncentryczne i technika jest w nim wa�niejsza od wielko�ci ci�aru. Mo�na r�wnie� w tym �wiczeniu stosowa� supinacj� nadgarstka. Ramiona �zamykamy� do ko�ca, napinaj�c maksymalnie mi�sie�. Prostujemy rami�(jak w innych �wiczeniach na mi�nie dwug�owe)tylko do momentu pe�nego rozci�gni�cia biceps�w, nie do pe�nego zakresu ruchu w stawie �okciowym.",
				new String[] { "mi�snie dwug�owe ramion", "mi�nie ramienne", "mi�nie przedramion" }).saveExercise();

		new Exercise("UGINANIE RAMION ZE SZTANGA NACHWYTEM STOJ�C",
				"Stajemy w rozkroku na szeroko�� bark�w(lub nieco szerszym) sztang� chwytamy nachwytem. �okcie nieruchomo przy tu�owiu, nadgarstki zblokowane w jednej pozycji przez ca�y czas �wiczenia. Ruch odbywa si� tylko w stawach �okciowych. Opuszczanie odbywa si� wolnym tempem i pod pe�n� kontrol� ci�aru. Oddychanie, jak w �wiczeniu ze sztang� podchwytem. �wiczenie to mo�na wykonywa� r�wnie� zast�puj�c sztang� dr��kiem wyci�gu dolnego.",
				new String[] { "mi�snie ramienno promieniowe(umieszczone na wierzchu przedramion)",
						"mi�nie dwug�owe ramion", "mi�nie przedramion" }).saveExercise();

		new Exercise("UGINANIE NADGARSTK�W PODCHWYTEM W SIADZIE",
				"�wiczenie to rozwija wewn�trzn� cz�� przedramion-podstawowe dla rozwoju tej partii mi�ni. Wyrabia si�� u�cisku d�oni. �apiemy sztang� w siadzie podchwytem, rozstaw d�oni ok. 15 cm(szerszy nadwyr�a nadgarstki).Opieramy przedramiona o uda, tak by poza nogi wystawa�y jedynie nasze d�onie. Pracuj� tylko nadgarstki. Staramy si�, aby zakres ruchu by� jak najwi�kszy i w tym celu pozwalamy w dolnym po�o�eniu na stoczenie si� sztangi a� do ko�c�w palc�w - po czym ponownie �ciskamy d�o� i zginamy maksymalnie nadgarstek. �wiczenie mo�na wykonywa� r�wnie� zast�puj�c sztang� dr��kiem wyci�gu dolnego, lub sztangielk� (jednor�cz).",
				new String[] { "grupa mi�sni przedramion-zginaczy nadgarstk�w" }).saveExercise();

		new Exercise("PROSTOWANIE RAMION NA WYCI�GU STOJ�C",
				"Stajemy w lekkim rozkroku przed wyci�giem, chwytamy r�czk� nachwytem na szeroko�� ok.10-20 cm. Kciuki na r�czce(tzw. �ma�pi� chwyt).Lekko pochylamy si� w prz�d i naciskamy r�czk� wyci�gu w d�. Ramiona przyci�ni�te do tu�owia. Ruch wykonuj� tylko przedramiona(pracuj� jedynie stawy �okciowe). Ramiona prostujemy do ko�ca-dla lepszego napi�cia mi�ni. Nie unosimy �okci, gdy r�czka wyci�gu jest w g�rnym po�o�eniu-spowodowa�oby to zanik napi�cia w tricepsach. Najwa�niejsze jest sta�e utrzymanie napi�cia w �wiczonych mi�niach. Unikamy szarpania-ruch powinien by� p�ynny, a faza opuszczania ci�aru wolniejsza, z pe�n� kontrol� ci�aru.",
				new String[] { "wszystkie g�owy mi�ni tr�jg�owych ramion" }).saveExercise();

		new Exercise("WYCISKANIE �FRANCUSKIE�SZTANGI W SIADZIE",
				"�wiczenie to rozwija wszystkie g�owy triceps�w ze szczeg�lnym wskazaniem na g�ow� d�uga. Mo�na je wykonywa� zar�wno w pozycji stoj�cej, jak i siedz�cej. W pozycji siedz�cej ponadto mo�na zastosowa� oparcie(np. na �awce sko�nej).Polepsza to stabilno��, a co za tym idzie pozwala lepiej skoncentrowa� si� na wykonaniu �wiczenia. Mo�emy u�y� sztangi prostej i �amanej. �amana ma t� zalet�, �e pozwala na zwi�kszenie zakresu ruchu i zmniejsza napr�enia powstaj�ce w nadgarstkach. �okcie powinny trwa� nieruchomo(jak najbli�ej g�owy) podczas �wiczenia. Zakres ruchu:od pe�nego wyprostu ramion, do pe�nego zgi�cia w �okciach. Przy pe�nym zgi�ciu ramion, gdy sztanga znajduje si� za g�ow�, nie powinno si� traci� nad ni� kontroli(swobodne opuszczenie jej przeci��a mocno stawy �okciowe, co mo�e prowadzi� do kontuzji).�wiczenie mo�na wykonywa� r�wnie� zast�puj�c sztang� dr��kiem wyci�gu dolnego (prostym lub �amanym, b�d� grubym sznurem zako�czonym w�z�ami-stosujemy wtedy uchwyt r�wnoleg�y). Mo�na r�wnie� wykonywa� nieco inn� odmian� �wiczenia ze sztangielk� trzyman� obur�cz. Przy tej wersji nale�y pami�ta�, o jednakowym anga�owaniu w �wiczenie obu r�k.",
				new String[] { "g�owy boczne i przy�rodkowe mi�ni tr�jg�owych ramion" }).saveExercise();

		new Exercise("PROSTOWNIE RAMIENIA ZE SZTANGIELK� W OPADZIE TU�OWIA",
				"Przy wyprostowanych plecach pochylamy si� do przodu i opieramy jedn� r�k� o �awk�. W drugiej trzymamy sztangielk� i unosimy rami� nieco ponad lini� plec�w(nie ni�ej)-przedrami� prostopadle do pod�ogi. Z tej pozycji pracuj�c tylko tricepsem prostujemy rami� w �okciu unosz�c ci�ar a� do pe�nego wyprostu. W pozycji ko�cowej zatrzymujemy na moment ruch dla lepszego napi�cia mi�ni. Musimy zwraca� szczeg�ln� uwag� na to, by w trakcie �wiczenia w ruchu by�o jedynie przedrami�(praca w stawie �okciowym).Nie wolno dopuszcza� do bujania ramieniem. Ruch opuszczania wolniejszy od unoszenia, z pe�n� kontrol� ci�aru.",
				new String[] { "g�owy boczne i przy�rodkowe mi�ni tr�jg�owych ramion" }).saveExercise();

		// Uda i po�ladki
		new Exercise("PRZYSIADY ZE SZTANG� TRZYMAN� Z PRZODU",
				"Podobna technika, jak przy zwyk�ych przysiadach. Mocniej jednak anga�owane s� mi�nie czworog�owe ud-spowodowane jest to pionow� pozycj� tu�owia zwi�zan� z po�o�eniem sztangi z przodu. Gryf spoczywa na przedniej cz�ci mi�ni naramiennych i g�rnej cz�ci klatki piersiowej. Uchwyt na szeroko�� bark�w-je�eli trzymamy sztang� podchwytem,(co jest mo�e mniej wygodne, ale bezpieczniejsze- gif 2a)lub w�szy-je�eli trzymamy gryf nachwytem-ze skrzy�owanymi ramionami",
				new String[] {
						"�wiczenie to anga�uje przede wszystkim g�owy boczne i przy�rodkowe mi�ni czworog�owych" })
								.saveExercise();

		new Exercise("HACK-PRZYSIADY",
				"�wiczenie r�ni si� od zwyk�ych przysiad�w u�o�eniem sztangi(z ty�u za plecami, pod po�ladkami w wyprostowanych r�kach)i, co za tym idzie bardziej pionow� pozycj� tu�owia w trakcie wykonania �wiczenia. Ze wzgl�du na technik� wykonania i u�o�enie sztangi ci�ar, jaki u�yjemy w tym �wiczeniu b�dzie mniejszy, ni� w zwyk�ych przysiadach. Pozycja wyj�ciowa to wyprostowany tu��w, klatka wypchni�ta ku przodowi, nogi w rozkroku na szeroko�� bark�w, ramiona wyprostowane wzd�u� tu�owia, w d�oniach gryf sztangi(trzymany za plecami). Z tej pozycji wykonujemy przysiad do momentu, gdy nasze nogi b�d� ugi�te pod k�tem 90 stopni lub nieco mniejszym. Jednocze�nie wypychamy kolana nieco do przodu, nie odrywaj�c jednak st�p od pod�o�a-ci�ar wypychamy z pi�t. �wiczenie mo�na r�wnie� wykona� przy pomocy suwnicy Smitha",
				new String[] { "wszystkie g�owy mi�sni czworog�owych ud" }).saveExercise();

		new Exercise("SYZYFKI",
				"�wiczenie to mo�na wykonywa� zar�wno bez obci��enia, jak i z nim. Chwytamy wtedy w jedn� r�k� kr��ek i k�adziemy go sobie na klatce, drug� r�k� podpieramy si� dla zachowania r�wnowagi czego� stabilnego. �wiczenie polega na wykonaniu przysiadu z jednoczesnym mocnym odchyleniem tu�owia do ty�u i wspi�ciem na palce st�p po��czonym z wypchni�ciem kolan do przodu-dla lepszego rozci�gni�cia mi�ni g�rnej cz�ci ud i zwi�kszenia poziomu trudno�ci �wiczenia.",
				new String[] { "wszystkie g�owy mi�ni czworog�owych ud" }).saveExercise();

		new Exercise("PROSTOWNIE N�G W SIADZIE",
				"siadamy na siodle maszyny(�awki), dobrze gdy mamy oparcie-zapewnia ono lepsz� stabilno�� tu�owia, d�o�mi chwytamy za uchwyt(lub kraw�d�) maszyny. Nogi ugi�te w kolanach, zaparte o dr��ek maszyny na stopami(na wysoko�ci kostek). Z tej pozycji wykonujemy ruch prostowania n�g do pe�nego wyprostu w stawach kolanowych. W pozycji wyprostowanej zatrzymujemy ruch przez chwil� dla lepszego napi�cia mi�ni. Po czym powracamy do pozycji wyj�ciowej. Powr�t w tempie wolnym i pod pe�n� kontrol� ci�aru. Powietrze nabieramy przed rozpocz�ciem prostowania, wypuszczamy je, gdy ko�czymy prostowanie n�g.",
				new String[] { "zaanga�owane wszystkie g�owy mi�ni czworog�owych ud" }).saveExercise();

		new Exercise("UGINANIE N�G W LE�ENIU",
				"�wiczenie to wykonujemy na specjalnej maszynie,lub za pomoc� wyci�gu i specjalnym opasek na nogi(gif 8). K�adziemy si� na brzuchy tak, by poza �awk� wystawa�y jedynie podudzia poni�ej kolan, nogi wyprostowane w kolanach, zaparte o dr��ek maszyny na wysoko�ci �ci�gien Achillesa(nad pi�tami), d�onie na uchwytach(lub kraw�dzi) maszyny. Z tej pozycji wykonujemy ruch maksymalnego uginania n�g w kolanach. W ko�cowym momencie uginania zatrzymujemy ruch przez chwil� dla lepszego napi�cia mi�ni. Po czym powracamy do pozycji wyj�ciowej. Powr�t w tempie wolnym i pod pe�n� kontrol� ci�aru. Biodra(jak i reszta tu�owia) przez ca�y czas trwania �wiczenia przylegaj� do powierzchni �awki. Powietrze nabieramy przed rozpocz�ciem uginania, wypuszczamy je, gdy ko�czymy uginanie n�g. �wiczenie to mo�na wykona� r�wnie� w pozycji stoj�c(jednon�)",
				new String[] { "mi�nie dwug�owe ud", "mi�nie p�ci�gniste", "mi�nie smuk�e", "mi�nie krawieckie" })
						.saveExercise();

		new Exercise("NO�YCE",
				"�wiczenie to jest odwrotno�ci� poprzedniego-wykrok jest robiony do ty�u zamiast do przodu. Przysiad wykonujemy nie na nodze wykrocznej, ale na zakrocznej-czyli tej, kt�ra pozostaje w miejscu. �wiczenie to mo�na wykona� ze sztang�, sztangielkami, lub za pomoc� suwnicy Smitha",
				new String[] { "przywodziciele kr�tkie i wielkie", "w mniejszym stopniu g�owy boczne",
						"przy�rodkowe mi�sni czworog�owych ud" }).saveExercise();

		new Exercise("�CI�GANIE KOLAN W SIADZIE",
				"�wiczenie wykonujemy na specjalnej maszynie siedz�c. Plecy oparte o oparcie maszyny, nogi ugi�te w kolanach pod k�tem prostym, oparte o poduszki maszyny. Z tej pozycji wykonujemy ruch ��czenie �ci�gania n�g do wewn�trz, jak w celu z��czenia ich ze sob�, pokonuj�c jednocze�nie op�r maszyny. W pozycji maksymalnego �ci�gni�cia n�g zatrzymujemy ruch przez chwil� dla lepszego napi�cia mi�ni. Po czym powracamy do pozycji wyj�ciowej. Powr�t w tempie wolnym i pod pe�n� kontrol� ci�aru. Powietrze nabieramy przed rozpocz�ciem �ci�gania, wypuszczamy je, gdy ko�czymy �ci�ganie-nogi s� w po�o�eniu najbli�szym sobie.",
				new String[] { "mi�nie przywodziciele wielkie" }).saveExercise();

		new Exercise("PRZYWODZENIE N�G DO WEWN�TRZ",
				"Stajemy przy wyci�gu dolnym, zak�adamy na nog�(na wysoko�ci kostki)specjaln� opask� po��czon� z link� wyci�gu. Stajemy w takiej odleg�o�ci od wyci�gu, by ruch zaczyna� si� w momencie, gdy �wiczona noga odchylona jest od pionu w kierunku wyci�gu(gif 15). Z tej pozycji wykonujemy przywodzenie nogi przed sob�, a� do momentu, gdy trenowana noga znajdzie si� w pozycji odchylonej od pionu w kierunku przeciwnym do wyci�gu. W ko�cowym momencie( maksymalne wychylenie nogi w g�r�, do wewn�trz) mo�na zatrzyma� ruch na moment, po czym wracamy do pozycji wyj�ciowej.",
				new String[] { "mi�nie przywodziciele wielkie" }).saveExercise();

		new Exercise("ODWODZENIE N�G NA ZEWN�TRZ",
				"Stajemy przy wyci�gu dolnym, zak�adamy na nog�(na wysoko�ci kostki)specjaln� opask� po��czon� z link� wyci�gu. Stajemy w takiej odleg�o�ci od wyci�gu, by ruch zaczyna� si� w momencie, gdy �wiczona noga odchylona jest od pionu w kierunku wyci�gu. Z tej pozycji wykonujemy odwodzenie(nie wymachy, jak to �wiczenie nazywane jest cz�sto)nogi w kierunku przeciwnym do wyci�gu. W ko�cowym momencie( maksymalne wychylenie nogi w g�r�) mo�na zatrzyma� ruch na moment, po czym wracamy do pozycji wyj�ciowej.",
				new String[] { "przywodziciele wielkie", "przywodziciele d�ugie i smuk�e" }).saveExercise();

		// �ydki
		new Exercise("WSPIECIA NA PALCE W STANIU",
				"�wiczenie to mo�na wykonywa� zar�wno przy pomocy sztangi, suwnicy Smitha lub specjalnej maszyny. Mo�na wykonywa� je r�wnie� bez obci��enia, a tak�e jednon�.Sztang� mo�na r�wnie� zast�pi� sztangielk� trzyman� w d�oni(po tej samej stronie, co �wiczona noga: lewa noga- lewa r�ka, prawa noga- prawa r�ka). Istotnym elementem w tym �wiczeniu jest u�ycie grubej podk�adki pod palce st�p, kt�ra pozwala zwi�kszy� znacznie zakres ruchu, a co za tym idzie-poprawi� efektywno�� �wiczenia. Pozycja wyj�ciowa, to wyprostowany tu��w i plecy, nogi wyprostowane w kolanach, rozkrok 25-30 cm, palce st�p(wraz ze stawami ��cz�cymi je ze �r�dstopiem) na podk�adce-mi�snie �ydek rozci�gni�te maksymalnie. Z takiej pozycji rozpoczynamy wspi�cia. Ruch powinien by� wolny i dok�adny, ze sta�ym �czuciem� pracy mi�ni. Nale�y unika� odbijania si� pi�t od pod�ogi.",
				new String[] { "obie g�owy mi�sni brzuchatych �ydek", "mi�nie p�aszczkowate", "strza�kowe d�ugie" })
						.saveExercise();

		new Exercise("WSPI�CIA NA PALCE NA HACK-MASZYNIE",
				"�wiczenie to mo�na wykonywa� w pozycji ty�em do maszyny, jak r�wnie� przodem do maszyny(o ile oczywi�cie dysponujemy maszyn� ze specjalnymi oparciami na barki). Wskazane jest, jak w pozosta�ych �wiczeniach na mi�snie �ydek, grubej podk�adki pod palce.",
				new String[] { "przednie g�owy mi�ni brzuchatych �ydek", "mi�nie p�aszczkowate �ydek",
						"g�owy boczne mi�ni brzuchatych", "mi�nie strza�kowe d�ugie" }).saveExercise();

		new Exercise("WYPYCHANIE CIʯARU NA MASZYNIE LUB SUWNICY PALCAMI N�G",
				"Jest to, jakby odwrotna wersja wspi�� na Hack-maszynie- odwrotna jest pozycja-g�owa znajduje si� ni�ej n�g. Pozycja wyj�ciowa to siad na siedzisku maszyny/suwnicy, plecy oparte, nogi wyprostowane w kolanach, stopy dotykaj� do p�aszczyzny maszyny/suwnicy tylko palcami i stawami ��cz�cymi je ze sr�dstopiem, mi�nie �ydek rozci�gni�te maksymalnie. Z tej pozycji wypychamy ci�ar si�� mi�sni �ydek.",
				new String[] { "przednie g�owy mi�ni brzuchatych �ydek", "mi�nie p�aszczkowate �ydek",
						"g�owy boczne mi�ni brzuchatych", "mi�nie strza�kowe d�ugie" }).saveExercise();

		new Exercise("ODWROTNE WSPI�CIA W STANIU",
				"�wiczenie podobne do wspi�� na palce- r�nica jest taka, �e podk�adki s� pod pi�tami, a unosimy nie pi�ty, lecz �r�dstopia n�g. Pozycja taka, jak przy wspi�ciach na palce, ale nie rozci�gamy mi�ni �ydek w pocz�tkowej fazie-tylko mi�nie piszczelowe.",
				new String[] { "mi�nie piszczelowe" }).saveExercise();

		// przedramiona
		new Exercise("UGINANIE NADGARSTK�W PODCHWYTEM W SIADZIE",
				"�wiczenie to rozwija wewn�trzn� cz�� przedramion-podstawowe dla rozwoju tej partii mi�ni. Wyrabia si�� u�cisku d�oni. �apiemy sztang� w siadzie podchwytem, rozstaw d�oni ok. 15 cm(szerszy nadwyr�a nadgarstki).Opieramy przedramiona o uda, tak by poza nogi wystawa�y jedynie nasze d�onie. Pracuj� tylko nadgarstki. Staramy si�, aby zakres ruchu by� jak najwi�kszy i w tym celu pozwalamy w dolnym po�o�eniu na stoczenie si� sztangi a� do ko�c�w palc�w - po czym ponownie �ciskamy d�o� i zginamy maksymalnie nadgarstek. �wiczenie mo�na wykonywa� r�wnie� zast�puj�c sztang� dr��kiem wyci�gu dolnego, lub sztangielk� (jednor�cz).",
				new String[] { "grupa mi�sni przedramion-zginaczy nadgarstk�w" }).saveExercise();

		new Exercise("UGINANIE NADGARSTK�W NACHWYTEM W SIADZIE",
				"Pozycja i technika, jak w �wiczeniu poprzednim(podchwytem)-r�nica polega na uchwycie sztangi-w tym �wiczeniu stosujemy nachwyt i raczej nie pozwalamy na �zjechanie� sztangi na ko�ce palc�w. Zginaj�c nadgarstki opuszczamy sztang� tak mocno, jak pozwala na to zakres ruchu, po czym unosimy si�� nadgarstk�w sztang� tak wysoko, jak tylko damy rad�. W ruchu pozostaje jedynie nadgarstek.",
				new String[] { "grupa mi�sni przedramion-prostownik�w nadgarstk�w" }).saveExercise();

		// brzuch
		new Exercise("SK�ONY W LE�ENIU P�ASKO",
				"K�adziemy si� na materacu lub �awce. Nogi ugi�te, r�ce nad g�ow� i unosimy tu��w w g�r�. Pierwsza do g�ry unosi si� g�owa, potem barki, a na ko�cu reszta tu�owia. Dla lepszego zaanga�owania mi�ni sko�nych brzucha, w ko�cowej fazie unoszenia tu�owia mo�na wykonywa� nim skr�ty. Jest to jednak wersja trudniejsza i bardziej nara�aj�ca na ewentualne kontuzje(mocniej obci��a dolne partie grzbietu).Nabieramy powietrza przed rozpocz�ciem ruchu, a wypuszczamy je w trakcie unoszenia tu�owia.",
				new String[] { "mi�nie proste brzucha", "sko�ne brzucha" }).saveExercise();

		new Exercise("SK�ONY W LE�ENIU G�OW� W Dӣ",
				"Wykonanie jak w �wiczeniu poprzednim-p�asko, ale pozycja wyj�ciowa jest g�ow� w d� na �awce sko�nej. Dla lepszego zaanga�owania mi�ni sko�nych brzucha w ko�cowej fazie unoszenia tu�owia mo�na wykonywa� nim skr�ty. Jest to jednak wersja trudniejsza i bardziej nara�aj�ca na ewentualne kontuzje(mocniej obci��a dolne partie grzbietu).",
				new String[] { "mi�nie proste brzucha", "sko�ne brzucha" }).saveExercise();

		new Exercise("UNOSZENIE N�G W LE�ENIU NA SKO�NEJ �AWCE",
				"K�adziemy si� na �awce poziomej lub sko�nej-g�ow� do g�ry, ramiona za g�ow�(najlepiej je�li trzymamy jaki� punkt oparcia np. �awk� lub dr��ek), tu��w przylega do pod�o�a. Z tej pozycji unosimy nogi do klatki jednocze�nie zginaj�c je lekko w kolanach. Nabieramy powietrza przed rozpocz�ciem ruchu, a wypuszczamy je w trakcie unoszenia n�g. Pod koniec unoszenia mo�na skr�ca� nieco tu��w i biodra dla zaanga�owania mi�ni sko�nych brzucha.",
				new String[] { "mi�nie proste brzucha", "sko�ne brzucha" }).saveExercise();

		new Exercise("UNOSZENIE N�G W ZWISIE NA DR��KU",
				"Chwytamy dr��ek prosty nachwytem lub podchwytem, je�eli mamy k�opot z d�u�szym utrzymaniem si� na dr��ku, mo�emy zastosowa� paski. Unosimy nogi jak najwy�ej do brody. Mo�na r�wnie� w tym �wiczeniu wprowadzi� skr�ty tu�owia w ko�cowej fazie unoszenia n�g, co mocniej zaanga�uje do pracy mi�nie sko�ne brzucha. Jeszcze inna wersja(mocno anga�uj�ca mi�nie sko�ne)polega na jednoczesnym skr�cie bioder wraz z unoszeniem n�g. Osoby zaawansowane mog� w tym �wiczeniu u�ywa� dodatkowego obci��enia zamocowanego do n�g, ale tylko je�eli czuj� si� na si�ach-�atwo �przedobrzy� i nabawi� si� bolesnej kontuzji. Tempo ruchu umiarkowane, bez zryw�w. Im mniejsze ugi�cie n�g w kolanach, tym wi�kszy stopie� trudno�ci �wiczenia, wi�ksze zaanga�owanie mi�ni zginaczy bioder(przy zmniejszeniu pracy mi�sni brzucha) i wi�ksze napi�cia w dolnym odcinku grzbietu.",
				new String[] { "mi�nie proste brzucha", "sko�ne brzucha", "mi�snie z�bate przednie" }).saveExercise();

		new Exercise("UNOSZENIE N�G W PODPORZE",
				"Stajemy plecami do specjalnej podpory, ramiona opieramy na poziomych poprzeczkach podpory, d�o�mi chwytamy uchwyty, w tym momencie znajdujemy si� ju� ponad pod�og�. Z tej pozycji unosimy nogi w g�r�, w kierunku klatki piersiowej, jednocze�nie uginaj�c je w kolanach. Technika podobna, jak w unoszeniu n�g w zwisie, jednak mi�nie zaanga�owane pod innym k�tem. W tym �wiczeniu r�wnie� nale�y pami�ta� o zachowaniu pe�nego zakresu ruchu-zmniejszanie go prowadzi do skracania mi�ni. Tempo ruchu umiarkowane, bez zryw�w. Im mniejsze ugi�cie n�g w kolanach, tym wi�kszy stopie� trudno�ci �wiczenia, wi�ksze zaanga�owanie mi�ni zginaczy bioder(przy zmniejszeniu pracy mi�sni brzucha) i wi�ksze napi�cia w dolnym odcinku grzbietu.",
				new String[] { "mi�nie proste brzucha", "sko�ne brzucha," }).saveExercise();

		new Exercise("�SPINANIE�, UNOSZENIE KOLAN W LE�ENIU P�ASKO",
				"K�adziemy si� na �awce lub materacu p�asko, nogi wyprostowane, ramiona uniesione do g�ry nad g�ow�(dla lepszej stabilizacji mo�na chwyci� nimi za jaki� punkt oparcia- np. �awk�) i z tej pozycji podci�gamy kolana do klatki piersiowej. �wiczenie to mo�na r�wnie� wykonywa� z dodatkowym obci��eniem w postaci linki wyci�gu zahaczonej o nogi",
				new String[] { "mi�nie proste brzucha" }).saveExercise();

		new Exercise("SK�ONY TU�OWIA Z LINK� WYCI�GU SIEDZ�C",
				"Siadamy na �awce, tu��w wyprostowany,(najlepiej z podpor� pod plecy),za plecami mamy wyci�g g�rny(zamiast r�czki zaczepiona lina z w�z�ami na ko�cach),chwytamy koniec liny(w ten spos�b, �e otacza nam z ty�u kark), z tej pozycji wykonujemy sk�ony w prz�d na taka g��boko��, by nie odrywa� dolnego odcinka plec�w od oparcia, staraj�c si� przez ca�y czas utrzyma� dolny odcinek grzbietu wyprostowany. Powrotny ruch kontrolowany i w wolnym tempie. Bardzo podobne dzia�anie ma �wiczenie wykonane na specjalnej maszynie",
				new String[] { "proste brzucha", "sko�ne brzucha" }).saveExercise();

		new Exercise("SKR�TY TU�OWIA",
				"�wiczenie to mo�na wykona� zar�wno w pozycji siedz�cej, jak i stoj�cej-na maszynie lub, je�li takiej nie posiadamy-za pomoc� gryfu sztangi(tylko nie �olimpijskiej�- mo�e by� zbyt ci�ki)zaawansowani mog� pozwoli� sobie na u�ycie pewnego obci��enia, oczywi�cie z umiarem. Zbyt du�e przeci��a dolny odcinek kr�gos�upa. W pozycji stoj�cej- stajemy w rozkroku szerszym ni� barki, gryf k�adziemy na karku, ramiona oparte szeroko na gryfie. W pozycji siedz�cej(na maszynie) chwytamy r�czki maszyny, tu��w wyprostowany przez ca�y czas wykonania �wiczenia, nogi w jednakowej pozycji(ugi�te w kolanach i skierowane do przodu-najlepiej, gdy s� zaparte- dla lepszej stabilizacji tu�owia)w trakcie ca�ego �wiczenia.",
				new String[] { "sko�ne brzucha", "proste brzucha", "prostowniki grzbietu" }).saveExercise();

		new Exercise("SK�ONY BOCZNE",
				"Stajemy w lekkim rozkroku(na szeroko�� bark�w, lub nieco szerzej),tu��w wyprostowany, w jedn� r�k� chwytamy sztangielk�, drug� zak�adamy sobie na g�ow�(d�oni�). Z tej pozycji wykonujemy sk�on w kierunku wolnej r�ki, napinaj�c mi�nie sko�ne brzucha. Oddech bierzemy w momencie rozpocz�cia ruchu, wypuszczamy powietrze w momencie maksymalnego sk�onu. Powr�t do pozycji wyj�ciowej mo�e by� z przekroczeniem linii pionu(tu�owia)-zwi�ksza to napi�cie mi�ni sko�nych. Ruch powinien by� p�ynny i w wolnym tempie, bez gwa�townych szarpni��. �wiczenie to mo�na r�wnie� wykona� przy pomocy wyci�gu dolnego-zast�puj�c nim sztangielk�.",
				new String[] { "sko�ne brzucha" }).saveExercise();

		new Exercise("SK�ONY TU�OWIA Z LINK� WYCI�GU KL�CZ�C",
				"Do tego �wiczenia zn�w potrzebna b�dzie specjalna lina za w�z�ami na ko�cach, zast�puj�ca r�czk� wyci�gu,(je�li takiej nie posiadamy mo�na �wiczenie wykona� trzymaj�c r�czk� wyci�gu nad g�ow�-zmieni si� nieco po�o�enie d�oni w �wiczeniu na mniej wygodne).Kl�kamy przed wyci�giem g�rnym, biodra cofni�te do ty�u, chwytamy ko�ce liny tak, by przebiega�a ona nad g�ow�, z tej pozycji wykonujemy sk�ony tu�owia w prz�d pokonuj�c op�r wyci�gu, jednocze�nie napinaj�c mi�nie brzucha. Bardzo istotne jest, by �czu� w�a�ciw� prac� mi�ni brzucha-tylko one wykonuj� prace. Unikamy ruch�w ramionami(anga�uje to do pracy mi�snie najszersze)-pozostaj� one w jednakowym po�o�eniu, d�onie obok g�owy(lub nad ni�). Powrotny ruch kontrolowany i w wolnym tempie. Zalecany ostro�ny dob�r obci��enia-zbyt du�e przeszkadza w poprawnym wykonaniu �wiczenia i ponadto przeci��a dolne partie grzbietu.",
				new String[] { "proste brzucha", "sko�ne brzucha" }).saveExercise();

		return true;
	}

	public void closeWindow() {
		stage.close();
	}
}
