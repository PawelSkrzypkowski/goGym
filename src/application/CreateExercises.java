package application;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InvalidClassException;
import java.util.LinkedList;

import diary.Exercise;
import diary.Workout;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
/**
 * Klasa u¿ywana przy pierwszym w³¹czeniu apliakcji do utworzenia plików z æwiczeniami i przyk³adowym treningiem.
 * @author Pawe³ Skrzypkowski
 *
 */
public class CreateExercises {
	Stage stage = new Stage();
	/**
	 * Metoda tworz¹ca okno oraz ³aduj¹ca æwiczenia i trening
	 */
	public void start() {
		try {
			VBox root = new VBox();
			root.setPadding(new Insets(20));
			Scene scene = new Scene(root, 250, 100);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			stage.setScene(scene);
			stage.setResizable(false);
			stage.setTitle("£adowanie plików");
			stage.getIcons().add(new Image((getClass().getResource("/icon.png").toExternalForm())));
			stage.show();
			Label descr = new Label("Æwiczenia za³adowane"), descr2 = new Label("Przyk³adowy trening za³adowany");
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
			alert.setContentText("B³¹d: " + e.toString() + ". £adowanie plików nie powiod³o siê.");
			alert.showAndWait();
		}
	}
	/**
	 * Metoda tworz¹ca przyk¹³dowy trening
	 * @return true
	 * @throws InvalidClassException
	 * @throws FileNotFoundException
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public boolean loadWorkout() throws InvalidClassException, FileNotFoundException, ClassNotFoundException, IOException{
		Workout exampleWorkout = new Workout("3-dniowy Full Body Workout", "Trening FBW, czyli Full Body Workout, jest to opcja treningu niemal¿e dla ka¿dego adepta si³owni. Znajduje swoje zastosowanie w treningu rozbudowuj¹cym masê miêœniow¹, redukuj¹cym tkankê t³uszczow¹, zwiêkszaj¹cym si³ê. Mo¿emy stosowaæ go równie¿ jako opcjê treningu obwodowego. Dobrze u³o¿ony i wykonywany plan FBW kompleksowo zadba o rozwój wszystkich partii miêœniowych naszego cia³a.", "FBW", "pocz¹tkuj¹cy");
		LinkedList<Exercise> exercises = new LinkedList<Exercise>();
		LinkedList<Integer> sets = new LinkedList<Integer>();
		LinkedList<Integer> rest = new LinkedList<Integer>();
		exercises.add(Exercise.readExercise("PRZYSIADY ZE SZTANG¥ TRZYMAN¥ Z PRZODU"));
		exercises.add(Exercise.readExercise("PODCI¥GANIE NA DR¥¯KU SZEROKIM UCHWYTEM (NACHWYT)"));
		exercises.add(Exercise.readExercise("ROZPIÊTKI ZE SZTANGIELKAMI W LE¯ENIU NA £AWCE SKOŒNEJ - G£OW¥ DO GÓRY"));
		exercises.add(Exercise.readExercise("Wyciskanie sztangi zza g³owy"));
		exercises.add(Exercise.readExercise("PROSTOWANIE RAMION NA WYCI¥GU STOJ¥C"));
		exercises.add(Exercise.readExercise("UGINANIE RAMION ZE SZTANGIELKAMI STOJ¥C PODCHWYTEM(Z „SUPINACJ¥” NADGARSTKA)"));
		exercises.add(Exercise.readExercise("SK£ONY TU£OWIA Z LINK¥ WYCI¥GU KLÊCZ¥C"));
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
 * Metoda tworz¹ca æwiczenia
 * @return true
 * @throws IOException
 */
	public boolean loadExercises() throws IOException {
		// Barki

		new Exercise("Wyciskanie sztangi sprzed g³owy",
				"Æwiczenie to mo¿na wykonywaæ zarówno w pozycji stoj¹cej (tzw. ¿o³nierskie wyciskanie), jak i siedz¹cej. Do æwiczenia mo¿na równie¿ u¿yæ tzw. suwnicy Smitha.",
				new String[] { "przednie i boczne aktony miêsni naramiennych", "miêœnie trójg³owe ramion" })
						.saveExercise();

		new Exercise("Wyciskanie sztangi zza g³owy",
				"Æwiczenie to mo¿na wykonywaæ zarówno w pozycji stoj¹cej (tzw. ¿o³nierskie wyciskanie), jak i siedz¹cej. Do æwiczenia mo¿na równie¿ u¿yæ tzw. suwnicy Smitha.",
				new String[] { "przednie i boczne  aktony miêœni naramiennych", "miêœnie trojg³owe ramion" })
						.saveExercise();

		new Exercise("Wyciskanie sztangielek",
				"kolejne æwiczenie, które mo¿na wykonywaæ zarówno w pozycji stoj¹cej, jak i siedz¹cej  - d³onie ze sztangielkami przez ca³y czas trzymamy tak, aby ich wewnêtrzne czêœci skierowane by³y do przodu(inna wersja przewiduje uchwyt m³otkowy-d³onie zwrócone w czasie ca³ego ruchu palcami w kierunku g³owy).Ruch powinien odbywaæ siê  pod pe³n¹ kontrol¹ ciê¿aru-wa¿na jest równie¿ pozycja podczas æwiczenia-podobna do pozycji przy wyciskaniu sztangi. (wypchniêta klatka, naturalna krzywizna krêgos³upa)",
				new String[] { "przednie i boczne aktony miêœni naramiennych", "miêœnie trójg³owe ramion" })
						.saveExercise();

		new Exercise("Unoszenie sztangielek bokiem w górê",
				"æwiczenie mo¿na wykonywaæ w pozycji stoj¹cej lub siedz¹cej, obur¹cz lub jednor¹cz. W pozycji wyjœciowej tu³ów lekko pochylony, rêce ze sztangielkami nieco ugiête w ³okciach, opuszczone w dó³, d³onie wewnêtrznymi stronami skierowane do œrodka. Ruch unoszenia rozpoczynamy przy ugiêtych rêkach, ³okcie w ka¿dej fazie ruchu wyprzedzaj¹ d³onie. Sztangielki unosimy powy¿ej linii barków i bez zatrzymania opuszczamy powoli w dó³ (lub przytrzymujemy w pozycji szczytowej przez chwilê w celu dodatkowego napiêcia miêœni). Æwiczenie to mo¿na równie¿ wykonywaæ jednor¹cz sztangielk¹ w odchyleniu-chwytamy siê porêczy, drabinek lub jakiegoœ innego przyrz¹du i odchylamy tu³ów w bok (jedna rêk¹ trzymamy siê porêczy, a w drugiej trzymamy sztangielkê) i unosimy sztangielkê bokiem w górê do poziomu (w tym punkcie mo¿na zatrzymaæ ruch na chwilê) nastêpnie opuszczamy j¹ do pozycji wyjœciowej.",
				new String[] { "œrodkowe aktony  miêœni naramiennych" }).saveExercise();

		new Exercise("Unoszenie sztangielek w opadzie tu³owia",
				"æwiczenie wykonujemy w pozycji siedz¹cej lub stoj¹cej -W pozycji stoj¹cej: tu³ów ustawiamy w po³o¿eniu zbli¿onym do prostopad³ego do pod³o¿a i staramy siê w trakcie ruchu nie wykonywaæ nim tzw. bujania-utrzymujemy mo¿liwie sztywno. Z pozycji wyjœciowej ruchem kolistym unosimy sztangielki maksymalnie w górê, utrzymuj¹c przez ca³y czas æwiczone miêœnie w stanie napiêcia. Staramy siê, aby w ruchu powrotnym sztangielki porusza³y siê po tym samym torze. Jak w poprzednim æwiczeniu ,mo¿na w pozycji szczytowej przytrzymaæ przez chwilê sztangielki w celu dodatkowego napiêcia miêœni.",
				new String[] { "tylna czêœæ miêœni naramiennych" }).saveExercise();

		new Exercise("Podci¹ganie sztangi wzd³u¿ tu³owia",
				"w pozycji stoj¹cej rozkrok nieco szerszy od rozstawu barków, uchwyt na szerokoœæ ramion (inna wersja przewiduje szerszy rozstaw d³oni, nawet szerszy ni¿ rozstaw barków-zaanga¿owane s¹ wtedy bardziej boczne aktony miêsni naramiennych, a ruch koñczymy na wysokoœci klatki piersiowej). Ruchem wolnym i kontrolowanym unosimy sztangê w kierunku brody, staraj¹c siê aby przemieszcza³a siê mo¿liwie najbli¿ej tu³owia. £okcie przez ca³y czas trzymamy powy¿ej gryfu sztangi. Ruch unoszenia koñczymy, gdy sztanga znajdzie siê na wysokoœci klatki piersiowej (lub staramy siê podci¹gn¹æ sztangê a¿ do brody), opuszczamy równie¿ powoli, unikamy odchylania i bujania tu³owia. Koncentrujemy siê na unoszeniu ³okci, a sztanga pod¹¿y za nimi.",
				new String[] { "wszystkie aktony miêœni naramiennych",
						"miêsieñ czworoboczny grzbietu (tzw. kapturowy)" }).saveExercise();

		new Exercise("Unoszenie ramion w przód ze sztang¹",
				"æwiczenie wykonujemy w staniu -rozkrok nieco szerzej od barków, uchwyt na szerokoœæ barków-u³o¿enie d³oni zale¿y od rodzaju sztangi ,z jak¹ wykonujemy æwiczenie: mo¿na zastosowaæ nachwyt (przy u¿yciu sztangi prostej), nachwyt pod k¹tem (przy u¿yciu sztangi ³amanej), uchwyt m³otkowy (przy u¿yciu „kratownicy”)- rozbudowuje siê dodatkowo obszar po³¹czenia m. piersiowych z naramiennymi .Unosimy sztangê miarowym ruchem (bez szarpania) ponad poziom barków i opuszczamy równie¿ p³ynnym ruchem. Staramy siê nie bujaæ tu³owiem. W szczytowym po³o¿eniu mo¿na zatrzymaæ ruch na chwilê w celu dodatkowego napiêcia miêœni. W fazie negatywnej (opuszczanie sztangi) utrzymujemy pe³n¹ kontrolê nad ciê¿arem.",
				new String[] { "przednie i boczne aktony miêœni naramiennych" }).saveExercise();

		new Exercise("Unoszenie ramion ze sztangielkami w le¿eniu",
				"æwiczenie wykonujemy w pozycji le¿¹cej na pod³odze lub ³aweczce. Ruch zaczynamy nieco poni¿ej poziomu i miarowo unosimy sztangielkê do momentu, kiedy poczujemy brak napiêcia w miêœniach barków, czyli mniej wiêcej do pionu. Mo¿na stosowaæ uchwyt jak na Gifie (pracuj¹ tylne i boczne aktony miêœni naramiennych), lecz mo¿na zastosowaæ chwyt kciukiem do do³u, separujemy wtedy bardziej tylne aktony miêœni naramiennych).",
				new String[] { "boczne i tylne aktony miêœni naramiennych" }).saveExercise();

		new Exercise("Odwrotne rozpiêtki (na maszynie)",
				"przed rozpoczêciem æwiczenia ustawiamy wysokoœæ siedzenia, tak aby rêce porusza³y siê równolegle do pod³o¿a. Stosujemy chwyt neutralny (m³otkowy). Chwytamy r¹czki przyrz¹du, robimy wdech i wstrzymujemy oddech na czas odwodzenia ramion do ty³u. Dochodzimy do momentu, w którym ³okcie znajd¹ siê tu¿ za plecami. Jeœli nie mo¿emy odci¹gn¹æ ³okci odpowiednio daleko do ty³u, powinniœmy zmniejszyæ obci¹¿enie.",
				new String[] { "tylne aktony miêœni naramiennych." }).saveExercise();

		// Klatka piersiowa
		new Exercise("WYCISKANIE SZTANGI W LE¯ENIU NA £AWCE POZIOMEJ",
				"K³adziemy siê na ³awce tak, by nogi ugiête by³y pod k¹tem prostym i przylega³y do pod³o¿a. Uchwyt œredni(taki, by po opuszczeniu sztangi na klatkê ramiona tworzy³y z przedramionami k¹t prosty-kciuk dla bezpieczeñstwa powinien obejmowaæ sztangê-choæ wielu bardziej doœwiadczonych kulturystów preferuje raczej tzw. ”ma³pi chwyt” (kciuk ponad gryfem).Opuszczamy sztangê na klatkê na wysokoœæ ok.1 cm powy¿ej brodawek. Przy opuszczaniu sztangi wykonujemy g³êboki wdech-wydychamy powietrze w trakcie wyciskania. Mo¿na okresowo praktykowaæ przytrzymywanie sztangi przez chwilê na klatce przed wyciœniêciem.(szczególnie przydatne, je¿eli mamy w planach ewentualne starty w zawodach w wyciskaniu)-dodatkowo rozbudowuje si³ê-pobudza do dodatkowego wysi³ku. £okcie prowadzimy w trakcie ca³ego ruchu po bokach-tak by nie „ucieka³y”do œrodka. Ruch wyciskania koñczymy(dla lepszego napiêcia miêœni)zanim ³okcie zostan¹ zblokowane.",
				new String[] { "ca³a grupa miêœni klatki piersiowej", "miêœnie trójg³owe ramion",
						"przednie aktony miêœni naramiennych" }).saveExercise();

		new Exercise("WYCISKANIE SZTANGI W LE¯ENIU NA £AWCE SKOŒNEJ-G£OW¥  W GÓRÊ",
				"£awkê ustawiamy pod k¹tem 30-45 stopni(wiêkszy k¹t bardziej anga¿uje w æwiczeniu miêœnie naramienne), k³adziemy siê g³ow¹ do góry. Uchwyt sztangi, oddychanie, prowadzenie ³okci, jak w æwiczeniu na ³awce p³askiej. Chwytamy sztangê i opuszczamy j¹ na klatkê-ok.10 cm. poni¿ej szyi. Staramy siê skupiaæ uwagê na anga¿owaniu w pracê tylko miêsni piersiowych i maksymalnym wy³¹czeniu z niej miêsni naramiennych. W tym celu mo¿na lekko wygi¹æ grzbiet, jednoczeœnie wypychaj¹c klatkê do przodu. Nie wolno jednak odrywaæ zbytnio pleców od ³awki, a biodra musz¹ bezwzglêdnie przylegaæ do ³awki. ",
				new String[] { "miêœnie trójg³owe ramion", "przednie aktony miêœni naramiennych " }).saveExercise();

		new Exercise("WYCISKANIE SZTANGI W LE¯ENIU NA £AWCE SKOŒNEJ-G£OW¥ W DÓ£",
				"£aweczkê ustawiamy pod k¹tem 30-45 stopni- k³adziemy siê g³ow¹ w dó³, zapieraj¹c nogi o coœ dla stabilnoœci,(aby nie zsun¹æ siê w dó³ podczas wykonywania æwiczenia).Ruch wygl¹da tak samo, jak podczas wyciskania na ³awce poziomej. Opuszczaj¹c sztangê nabieramy g³êboko powietrza, wydychamy je wyciskaj¹c sztangê w górê. Æwiczenie mo¿na wykonywaæ równie¿ przy pomocy suwnicy Smitha lub maszyn.",
				new String[] { "ca³a grupa miêsni piersiowych z podkreœleniem dolnych rejonów",
						"miêœnie trójg³owe ramion", "przednie aktony miêœni naramiennych" }).saveExercise();

		new Exercise("WYCISKANIE SZTANGIELEK W LE¯ENIU NA £AWCE SKOŒNEJ-G£OW¥ W DÓ£",
				"Pozycja, jak w æwiczeniu z u¿yciem sztangi-dodatkowe mo¿liwoœci: np. chc¹c po³o¿yæ wiêkszy nacisk na rozwój wewnêtrznej czêœci klatki mo¿na w górnym po³o¿eniu(podczas fazy wyciskania) zbli¿aæ sztangielki do siebie, co nie jest mo¿liwe przy u¿yciu sztangi. Równie¿ faza opuszczania mo¿e mieæ nieco odmienny przebieg - przy æwiczeniu ze sztang¹ ruch ogranicza nam gryf sztangi, a wykorzystuj¹c do tego æwiczenia sztangielki mo¿emy opuszczaæ rêce poni¿ej (g³êbiej) poziomu klatki, co dodatkowo rozci¹ga miêsnie( w myœl zasady: im wiêkszy zakres ruchu, tym pe³niejszy rozwój miêœni). Dodatkowo mo¿na w koñcowej fazie ruchu obracaæ nadgarstki tak, by d³onie skierowane by³y do siebie palcami(pozwala to na dodatkowe napiêcie wewnêtrznych czêœci miêœni).",
				new String[] { "ca³a grupa miêsni piersiowych z podkreœleniem dolnych rejonów",
						"miêœnie trójg³owe ramion", "przednie aktony miêœni naramiennych" }).saveExercise();

		new Exercise("ROZPIÊTKI ZE SZTANGIELKAMI W LE¯ENIU NA £AWCE POZIOMEJ",
				"Æwiczenie rozpoczynamy z ramionami wyprostowanymi-prostopad³ymi do pod³o¿a( palce d³oni skierowane s¹ do siebie), a w trakcie ruchu lekko uginamy je w ³okciach. Nabieramy powietrza, gdy sztangielki s¹ u góry(na pocz¹tku ruchu) wydychamy je, gdy sztangielki wêdruj¹ do góry. W koñcowej fazie ruch mo¿na zatrzymaæ na chwilê w celu lepszego napiêcia miêœni. Staramy siê nie uderzaæ sztangielkami o siebie, ale zatrzymywaæ ruch zanim siê zetkn¹. Wa¿ne jest wykonywanie pe³nego zakresu ruchu(by dostatecznie rozci¹gn¹æ miêœnie)-im wiêkszy zakres wykonanego ruchu, tym pe³niejszy ogólny rozwój miêœnia. Obci¹¿enia dostosowujemy takie, by  wykonywaæ æwiczenie poprawnie technicznie. Æwiczenie to mo¿na równie¿ wykonywaæ przy pomocy linek wyci¹gów,lub specjalnej maszyny",
				new String[] { "miêsieñ piersiowy wiêkszy(jego wewnêtrzna i zewnêtrzna czêœæ)",
						"miêsieñ kruczoramienny", "przednie aktony miêœni naramiennych", "piersiowy mniejszy",
						"zêbaty przedni" }).saveExercise();

		new Exercise("ROZPIÊTKI ZE SZTANGIELKAMI W LE¯ENIU NA £AWCE SKOŒNEJ - G£OW¥ DO GÓRY",
				"£aweczkê ustawiamy pod k¹tem 30-45 stopni, æwiczenie rozpoczynamy z ramionami wyprostowanymi-prostopad³ymi do pod³o¿a( palce d³oni skierowane s¹ do siebie), a w trakcie ruchu lekko uginamy je w ³okciach. Nabieramy powietrza, gdy sztangielki s¹ u góry(na pocz¹tku ruchu) wydychamy je, gdy sztangielki wêdruj¹ do góry.Wa¿ne jest wykonywanie pe³nego zakresu ruchu(by dostatecznie rozci¹gn¹æ miêœnie)-im wiêkszy zakres wykonanego ruchu, tym pe³niejszy ogólny rozwój miêœnia. Obci¹¿enia dostosowujemy takie, by wykonywaæ æwiczenie poprawnie technicznie. Æwiczenie równie¿ mo¿na wykonywaæ zastêpuj¹c sztangielki r¹czkami wyci¹gów.",
				new String[] { "miêsieñ piersiowy wiêkszy(jego górna wewnêtrzna i zewnêtrzna czêœæ)",
						"miêsieñ kruczoramienny i przednie aktony miêœni naramiennych", "piersiowy mniejszy",
						"zêbaty przedni" }).saveExercise();

		new Exercise("WYCISKANIE SZTANGI W LE¯ENIU NA £AWCE POZIOMEJ W¥SKIM UCHWYTEM",
				"Aby æwiczenie to anga¿owa³o g³ównie miêœnie piersiowe, a dopiero w drugim stopniu trójg³owe ramion, nale¿y prowadziæ ³okcie mo¿liwie najdalej na boki od tu³owia i koncentrowaæ siê na pracy miêsni klatki, a nie ramion. Pozycja na ³awce i oddychanie, jak przy wyciskaniu w szerokim uchwycie. Uchwyt na szerokoœæ barków lub odrobinê wê¿szy. Æwiczenie mo¿na wykonywaæ równie¿ na suwnicy Smitha",
				new String[] { "wewnêtrzna czêœæ miêœnia", "przednie aktony miêœni naramiennych",
						"miêœnie trójg³owe ramion" }).saveExercise();

		new Exercise("PRZENOSZENIE SZTANGIELKI W LE¯ENIU W POPRZEK £AWKI POZIOMEJ",
				"K³adziemy siê w poprzek ³awki poziomej-w ten sposób, by do jej powierzchni przylega³a jedynie czêœæ pleców w okolicy ³opatek i karku. Chwytamy sztangielkê pod talerzami(gryf sztangielki pomiêdzy kciukami i palcami wskazuj¹cymi).Ramiona lekko ugiête podczas ca³ego ruchu-ich prostowanie w trakcie æwiczenia anga¿uje dodatkowo miêsnie trójg³owe ramion i najszersze grzbietu. Sztangielkê opuszczamy do ty³u w dó³ do momentu maksymalnego wychylenia, w jakim mo¿emy kontrolowaæ ciê¿ar. Bardzo istotne jest w tym æwiczeniu oddychanie- opuszczaj¹c w ty³ sztangielkê-nabieramy mocno powietrza( maksymalnie rozszerzaj¹c klatkê)-w drodze powrotnej wypuszczamy je. Dla lepszego zaanga¿owania miêœni zêbatych mo¿na przy opuszczaniu sztangielki w ty³ jednoczeœnie obni¿yæ biodra, co dodatkowo rozci¹gnie tu³ów i powiêkszy zakres ruchu. Pamiêtaæ nale¿y równie¿ o koncentracji na pracy miêœni piersiowych i wyeliminowaniu pracy miêœni grzbietu. Mo¿na to æwiczenie równie¿ wykonywaæ le¿¹c wzd³u¿ ³awki, ale wtedy zakres ruchu jest mniejszy.",
				new String[] { "ca³a grupa miêœni piersiowych", "miêœnie najszersze grzbietu" }).saveExercise();

		new Exercise("POMPKI NA PORÊCZACH",
				"W tym æwiczeniu, podobnie jak przy wyciskaniu w¹sko równie¿ wa¿ne jest by pracê wykonywa³y w g³ównym stopniu miêœnie piersiowe, w mniejszym stopniu chodzi nam o pracê miêœni trójg³owych ramion. Elementem decyduj¹cym o wiêkszym zaanga¿owaniu jednych, b¹dŸ drugich miêœni jest pozycja tu³owia i u³o¿enie ³okci. Nale¿y wypracowaæ takie u³o¿enie tu³owia, przy którym g³ówn¹ pracê bêd¹ wykonywa³y miêœnie piersiowe, a ³okcie pracowaæ powinny w pewnym oddaleniu od tu³owia. Dla lepszego wyeliminowania pracy tricepsów i lepszego napiêcia miêsni piersiowych mo¿na równie¿ nie prostowaæ ramion do koñca.",
				new String[] { "ca³a grupa miêœni klatki piersiowej", "przednie aktony miêœni naramiennych",
						"miêœnie trójg³owe ramion" }).saveExercise();

		new Exercise("ROZPIÊTKI W SIADZIE NA MASZYNIE",
				"wa¿ne jest zajêcie dobrej pozycji do æwiczenia(odpowiednia regulacja wysokoœci siedzenia-ramiona powinny tworzyæ z przedramionami k¹t prosty),przedramiona na ca³ej d³ugoœci wraz z ³okciami powinny przylegaæ do poduszek oporowych.Przed rozpoczêciem ruchu robimy wdech ,a powietrze wydychamy podczas zbli¿ania ramion do siebie.W pozycji koñcowej(ramiona najbli¿ej siebie)mo¿na wstrzymaæ ruch na 1-2 sekundy(dla wiêkszego napiêcia miêœni).Ruch powrotny powinien odbywaæ siê pod pe³na kontrol¹.",
				new String[] { "miêsieñ piersiowy wiêkszy",
						"miêsieñ kruczoramienny i przednie aktony miêœni naramiennych" }).saveExercise();

		new Exercise("KRZY¯OWANIE LINEK WYCI¥GU W STANIU",
				"Stajemy pomiêdzy dwoma górnymi wyci¹gami( w tzw.”bramie”)-tu³ów lekko pochylony, co daje lepsz¹ separacjê miêsni piersiowych. Chwytamy r¹czki wyci¹gów i œci¹gamy je do wewn¹trz w dó³. Do tego æwiczenia nale¿y u¿ywaæ umiarkowanych ciê¿arów i wykonywaæ pe³en zakres ruchu. Wstrzymanie ruchu w koñcowej fazie pozwoli otrzymaæ lepsze napiêcie miêœni i poprawi wyrazistoœæ szczegó³ów umiêœnienia.",
				new String[] { "ca³a grupa miêsni piersiowych", "przednie aktony miêœni naramiennych" }).saveExercise();

		// Plecy
		new Exercise("PODCI¥GANIE NA DR¥¯KU SZEROKIM UCHWYTEM (NACHWYT)",
				"Nie ma drugiego takiego æwiczenia pod wzglêdem wszechstronnoœci rozwoju miêsni grzbietu. Æwiczenie to mo¿na wykonywaæ do karku i do brody, lecz wersja do karku jest mniej naturalna dla stawów. Chwytamy dr¹¿ek nachwytem na szerokoœæ tak¹, by po podci¹gniêciu ramiona z przedramionami tworzy³y k¹t prosty(w przybli¿eniu).Nogi ugiête w kolanach(dla lepszej stabilnoœci mo¿na je spleœæ).£okcie pracuj¹ w p³aszczyŸnie pleców-w jednej linii. Wdech robimy przed rozpoczêciem ruchu podci¹gania-wydech dopiero, gdy jesteœmy ju¿ u góry. Ruch podci¹gania koñczymy w momencie, gdy nasza broda(lub kark) jest na wysokoœci dr¹¿ka lub nieco ponad nim. Opuszczamy siê wolno i pod pe³n¹ kontrol¹. Jeœli jesteœmy bardziej zaawansowani i mo¿emy wykonaæ wiele powtórzeñ w tym æwiczeniu, to mo¿na zastosowaæ dodatkowe obci¹¿enie.",
				new String[] { "miêœnie najszersze grzbietu", "ob³e mniejsze", "ob³e wiêksze", "podgrzebieniowe",
						"dwug³owe ramion" }).saveExercise();

		new Exercise("PODCI¥GANIE NA DR¥¯KU W UCHWYCIE NEUTRALNYM",
				"Chwytamy specjalny uchwyt(mo¿e to byæ r¹czka trójk¹tna od wyci¹gu przerzucona nad dr¹¿kiem prostym)-d³onie równolegle do siebie w odleg³oœci ok.20-25cm.,palcami skierowane do siebie. Z pe³nego zwisu podci¹gamy siê do linii podmostkowej. Nogi zwisaj¹ luŸno, lekko podkurczone w kolanach. £okcie staramy siê prowadziæ wzd³u¿ tu³owia. Opuszczamy siê powoli-kontroluj¹c swój ciê¿ar. Jeœli jesteœmy bardziej zaawansowani i mo¿emy wykonaæ wiele powtórzeñ w tym æwiczeniu, to mo¿na zastosowaæ dodatkowe obci¹¿enie.",
				new String[] { "miêœnie ob³e mniejsze", "ob³e wiêksze", "podgrzebieniowe", "najszersze grzbietu",
						"dwug³owe ramion" }).saveExercise();

		new Exercise("PODCI¥GANIE NA DR¥¯KU PODCHWYTEM",
				"Chwytamy dr¹¿ek prosty podchwytem. Nogi zwisaj¹ luŸno, lekko podkurczone w kolanach. Ze zwisu przechodzimy do podci¹gania. Koñczymy je, gdy nasza broda znajdzie siê ponad dr¹¿kiem, a nasze ramiona bêd¹ w pe³ni ugiête w ³okciach. £okcie staramy siê prowadziæ wzd³u¿ tu³owia. Opuszczamy siê powoli-kontroluj¹c swój ciê¿ar. Jeœli jesteœmy bardziej zaawansowani i mo¿emy wykonaæ wiele powtórzeñ w tym æwiczeniu, to mo¿na zastosowaæ dodatkowe obci¹¿enie.",
				new String[] { "najszersze grzbietu", "ob³e mniejsze", "ob³e wiêksze", "podgrzebieniowe",
						"dwug³owe ramion" }).saveExercise();

		new Exercise("PODCI¥GANIE SZTANGI W OPADZIE(WIOS£OWANIE)",
				"Stajemy nad sztang¹ w rozkroku na szerokoœæ barków, pochylamy tu³ów do pozycji prawie równoleg³ej do pod³o¿a, plecy w dolnym odcinku mocno ugiête do œrodka, nogi lekko ugiête w kolanach przez ca³y czas trwania æwiczenia. Wdech bierzemy w momencie rozpoczêcia podci¹gania-wydech dopiero, gdy sztanga dochodzi do brzucha(lub do klatki). Sztangê chwytamy na szerokoœæ nieco wiêksz¹ od barków i podci¹gamy j¹ do brzucha(³okcie prowadzimy na boki).Druga wersja zak³ada podci¹ganie sztangi do klatki piersiowej(jest to ruch odwrotny do wyciskania sztangi na ³awce)-anga¿owane s¹ mocniej w tej wersji miêœnie czworoboczne grzbietu, ob³e wiêksze, mniejsze i tylne aktony miêœni naramiennych. Æwiczenie to mo¿na równie¿ wykonywaæ przy pomocy suwnicy Smitha. Opuszczamy ciê¿ar z pe³n¹ kontrol¹, wolnym tempem. Jeszcze inna wersja zak³ada zastosowanie w tym æwiczeniu podchwytu(mo¿na zastosowaæ wtedy, dla lepszych efektów sztangê ³aman¹).Ta wersja z kolei mocniej anga¿uje dolne rejony æwiczonych miêœni.",
				new String[] { "najszersze grzbietu", "ob³e mniejsze", "ob³e wiêksze", "podgrzebieniowe",
						"czworoboczne", "miêœnie równoleg³oboczne" }).saveExercise();

		new Exercise("PODCI¥GANIE KOÑCA SZTANGI W OPADZIE",
				"Stajemy okrakiem nad gryfem sztangi(pó³sztangi)i chwytamy dr¹¿ek, tu³ów z udami tworz¹ k¹t prosty, a z pod³og¹ nieco wiêkszy. Nogi lekko ugiête w kolanach. W takiej pozycji podci¹gamy sztangê do brzucha. Opuszczanie ciê¿aru kontrolowane. Wdech bierzemy w momencie rozpoczêcia podci¹gania-wydech dopiero, gdy sztanga dochodzi do brzucha. W zale¿noœci od k¹ta, pod jakim chcemy zaatakowaæ miêœnie u¿ywamy w tym æwiczeniu ró¿nych dr¹¿ków(chwytamy je ró¿nym uchwytem).I tak np. mo¿e to byæ dr¹¿ek sztangi typu „T” (mocniej zaanga¿owane górne czêœci miêsni najszerszych i miêœni ob³ych-³okcie prowadzimy w bok od tu³owia.)lub dr¹¿ek/r¹czka równoleg³a(³okcie prowadzone wzd³u¿ tu³owia-mocniej zaanga¿owane œrodkowe czêœci miêsni najszerszych i ob³ych).Mo¿ne te¿ æwiczenie to wykonywaæ w le¿eniu na ³awce skoœnej(mniej anga¿uje miêœnie dolnego odcinka grzbietu, mocniej izoluje miêœnie najszersze)",
				new String[] { "najszersze grzbietu", "ob³e mniejsze", "ob³e wiêksze", "podgrzebieniowe",
						"czworoboczne", "miêœnie równoleg³oboczne" }).saveExercise();

		new Exercise("PRZYCI¥GANIE LINKI WYCI¥GU DOLNEGO W SIADZIE P£ASKIM",
				"Siadamy p³asko przed wyci¹giem dolnym nogi zaparte o stabilny punkt oparcia i chwytamy r¹czkê wyci¹gu. Przyci¹gamy j¹ do brzucha, utrzymuj¹c przez ca³y czas tu³ów w pozycji pionowej. W koñcowej fazie ruchu staramy siê œci¹gn¹æ ³opatki ku sobie. Po czym powoli, kontroluj¹c ruch opuszczamy ciê¿ar. Wdech przed rozpoczêciem przyci¹gania- wydech, gdy r¹czka jest przy brzuchu. R¹czka mo¿e byæ ró¿na(uchwyt w zwi¹zku z tym równie¿ mo¿e byæ ró¿ny. R¹czka równoleg³a(trójk¹tna)pozwala na wykonanie æwiczenia z uchwytem „m³otkowym”. Anga¿uje on miêsnie górnej i œrodkowej czêœci grzbietu. R¹czka/dr¹¿ek prosta/y pozwala na uchwyt nachwytem lub podchwytem, szeroko lub w¹sko, co równie¿ anga¿uje pod ró¿nym k¹tem miêsnie grzbietu. Nachwyt w¹ski i szeroki izoluje bardziej górn¹ czêœæ miêsni grzbietu(szczególnie najszerszych i ob³ych), podchwyt w¹ski i szeroki anga¿uje mocniej œrodkow¹ i doln¹ czêœæ tych miêœni. W æwiczeniach szerokim uchwytem ³okcie prowadzone s¹ na boki, we wszystkich innych odmianach æwiczenia prowadzone s¹ przy tu³owiu. Æwiczenie to mo¿na wykonywaæ równie¿ jednor¹cz.",
				new String[] { "najszersze grzbietu", "ob³e wiêksze", "ob³e mniejsze", "miêœnie równoleg³oboczne" })
						.saveExercise();

		new Exercise("ŒCI¥GANIE DR¥¯KA lub R¥CZKI WYCI¥GU GÓRNEGO W SIADZIE SZEROKIM UCHWYTEM (NACHWYT)",
				"Siadamy na siode³ku pod wyci¹giem górnym, chwytamy r¹czkê/dr¹¿ek nachwytem na szerokoœæ tak¹, jak przy podci¹ganiu na dr¹¿ku i przyci¹gamy j¹ do klatki lub karku, w zale¿noœci od wersji, jak¹ wykonujemy. Obie wersje anga¿uj¹ te same miêsnie, ale pod nieco innymi k¹tami. £opatki œci¹gamy do siebie, jednoczeœnie ³okcie przywodz¹c do ty³u. Przy przyci¹ganiu do klatki tu³ów nieco odchylony do ty³u, a przy drugiej wersji(do karku)-tu³ów w pionie. W dolnym po³o¿eniu przytrzymujemy dr¹¿ek na chwilê dla lepszego napiêcia miêsni. Opuszczanie ciê¿aru kontrolowane. Sta³e napiêcie w æwiczonych miêœniach i „czucie” ich pracy.",
				new String[] { "miêœnie najszersze grzbietu", "ob³e mniejsze", "ob³e wiêksze", "podgrzebieniowe" })
						.saveExercise();

		new Exercise("PRZNOSZENIE SZTANGI W LE¯ENIU NA £AWCE POZIOMEJ",
				"Machanie sztangaJest to æwiczenie podobne do przenoszenia sztangielki, jednak zastosowanie sztangi zmienia nieco k¹t, pod jakim pracuj¹ ramiona, a co za tym idzie lepiej anga¿uje do pracy miêœnie grzbietu, przy jednoczesnym zmniejszeniu zaanga¿owania miêsni klatki piersiowej. Jeœli jednak decydujemy siê na wykonanie æwiczenia z u¿yciem sztangielki, to musimy pamiêtaæ, by wykonywaæ je na ugiêtych i u³o¿onych równolegle do tu³owia(nie na boki, jak w æwiczeniu na klatkê piersiow¹) ramionach. Pozwala to na lepsze zaanga¿owanie miêsni grzbietu. Przy wersji ze sztang¹ nale¿y samemu zadecydowaæ, jakie u³o¿enie cia³a(wzd³u¿, czy w poprzek)jest dla nas najlepsze. Mo¿na æwiczenie to wykonywaæ z ramionami wyprostowanymi, lub(co zdecydowanie zmniejsza naprê¿enia w stawach ³okciowych)na ugiêtych ramionach(podchwytem i nachwytem). Ruch opuszczania jest bardzo istotny i powinien byæ wykonany z maksymaln¹ koncentracj¹ i pod pe³n¹ kontrol¹ ciê¿aru. Sztangê opuszczamy do pe³nego rozci¹gniêcia miêsni grzbietu. Unoszenie koñczymy, gdy ramiona znajd¹ siê w pozycji pionowej do pod³o¿a. Æwiczenie mo¿na równie¿ wykonaæ zastêpuj¹c sztangê r¹czk¹/dr¹¿kiem wyci¹gu dolnego znajduj¹cego siê za nasz¹ g³ow¹ lub przy pomocy specjalnej maszyny",
				new String[] { "dolne partie miêœni najszerszych grzbietu", "miêœnie zêbate przednie",
						"miêœnie piersiowe" }).saveExercise();

		new Exercise("PODCI¥GANIE (WIOS£OWANIE) W LE¯ENIU NA £AWECZCE POZIOMEJ",
				"Jest to æwiczenie podobne do wios³owania w opadzie tu³owia, ale odci¹¿a ono dolny odcinek miêsni grzbietu-szczególnie polecane dla osób, które maj¹ k³opoty z t¹ w³aœnie czêœci¹. Technika podobna jak w æwiczeniu w opadzie. Tu³ów oparty o ³awkê poziom¹. £okcie przy æwiczeniu ze sztang¹ prowadzimy w bok od tu³owia, a w wersji ze sztangielkami wzd³u¿ tu³owia(zaanga¿owanie miêœni analogicznie, jak przy æwiczeniu w opadzie-z wy³¹czeniem pracy dolnego odcinka grzbietu). Æwiczenie to mo¿na równie¿ wykonaæ na ³awce skoœnej. Zaanga¿owane bêd¹ te same miêsnie jednak pod innym k¹tem.",
				new String[] { "miêœnie najszersze grzbietu", "ob³e mniejsze", "ob³e wiêksze" }).saveExercise();

		new Exercise("SK£ONY ZE SZTANG¥ TRZYMAN¥ NA KARKU",
				"Stajemy w rozkroku nieco wiêkszym ni¿ szerokoœæ barków. Sztangê k³adziemy na górnej czêœci miêsni czworobocznych grzbietu. G³owa lekko wygiêta do ty³u, ale bez przesady-zbytnie wyginanie g³owy mo¿e byæ przyczyn¹ kontuzji. Tu³ów wyprostowany, klatka piersiowa wypchniêta ku przodowi, ³opatki œci¹gniête do siebie. Nogi lekko ugiête w kolanach przez ca³y czas trwania æwiczenia. Z takiej pozycji wykonujemy sk³on do pozycji zbli¿onej do poziomego u³o¿enia tu³owia wzglêdem pod³ogi. Bez zatrzymania, ale nie szarpi¹c unosimy tu³ów do pozycji wyjœciowej. Æwiczenie mo¿na wykonywaæ przy pomocy suwnicy Smitha. Ruch powinien byæ p³ynny i kontrolowany.",
				new String[] { "prostowniki grzbietu", "dwug³owe ud", "poœladkowe" }).saveExercise();

		// Biceps
		new Exercise("UGINANIE RAMION ZE SZTANG¥ STOJAC PODCHWYTEM",
				"Stajemy w rozkroku(na szerokoœæ barków lub nieco szerzej)-sztangê chwytamy w zale¿noœci od tego, któr¹ g³owê bicepsu chcemy zaanga¿owaæ bardziej. I tak odpowiednio:\n-uchwyt w¹ski(wê¿szy ni¿ szerokoœæ ramion)-wiêksze zaanga¿owanie g³ów krótkich,\n-uchwyt œredni(na szerokoœæ ramion)-obie g³owy zaanga¿owane w równym stopniu,\n-uchwyt szeroki(szerszy od ramion)-wiêksze zaanga¿owanie g³ów d³ugich.\nTu³ów podczas æwiczenia utrzymujemy w pozycji wyprostowanej(bez bujania nim). Zakres ruchu: od pe³nego rozgiêcia bicepsów(nie ramion)do pe³nego ich skurczu. Pe³ne rozci¹gniêcie bicepsów, to nie to samo, co pe³ny wyprost ramion. Nale¿y unikaæ(nie tylko w tym æwiczeniu) tzw. ”przeprostów” ramion, czyli nadmiernego ich wyprostowywania(do pe³nego zakresu ruchu w stawie ³okciowym).£okcie przez ca³y czas przylegaj¹ do tu³owia-nie powinny uciekaæ na boki, ani w przód, gdy¿ powoduje to zaanga¿owanie innych miêœni do pracy. Powietrza nabieramy w pozycji wyjœciowej, wypuszczamy je dopiero po przejœciu ciê¿aru przez najtrudniejszy punkt ruchu. W pozycji koñcowej mo¿na zatrzymaæ na chwilê ciê¿ar dla lepszego ukrwienia miêœnia, ale pod warunkiem utrzymania bicepsów w pe³nym napiêciu. Nale¿y pamiêtaæ, ¿e ruch opuszczania musi byæ w pe³ni kontrolowany i wolniejszy od unoszenia. Do æwiczenia mo¿na u¿ywaæ zarówno sztangi prostej, jak i ³amanej-gryf ³amany zmniejsza napiêcia powstaj¹ce w nadgarstkach.",
				new String[] { "miêœnie dwug³owe ramion", "miêœnie przedramion" }).saveExercise();

		new Exercise("UGINANIE RAMION ZE SZTANGIELKAMI STOJ¥C PODCHWYTEM(Z „SUPINACJ¥” NADGARSTKA)",
				"Æwiczenie to mo¿na wykonywaæ zarówno w pozycji stoj¹cej, jak i siedz¹cej. Bardzo dobre, jako rozgrzewkowe przed ciê¿kimi seriami ze sztang¹, ale równie¿ jako samodzielne æwiczenie rozwijaj¹ce masê i kszta³t bicepsów. Polecane wykonanie z „supinacj¹” nadgarstka. Polega ona na stopniowym obracaniu d³oni w trakcie wykonywania æwiczenia. W pozycji wyjœciowej( ramiona wyprostowane) d³onie zwrócone s¹ ku sobie palcami, a w miarê uginania ramion obracaj¹ siê tak, by w koñcowym momencie ruchu(przy zgiêtych ramionach)ma³e palce by³y wy¿ej od kciuków. Daje to dodatkowe napiêcie miêœni oraz kszta³tuje kulistoœæ bicepsów. Prostowanie ramienia koñczymy w momencie rozci¹gniêcia miêœni dwug³owych(nie do pe³nej mo¿liwoœci stawu ³okciowego). Ruch mo¿na wykonywaæ na przemian-raz jedna rêka, raz druga(po 1 powtórzeniu), obiema rêkami jednoczeœnie, lub opuszczaj¹c jedn¹ rêkê- jednoczeœnie unosz¹c drug¹. Samemu trzeba wybraæ, która wersja jest dla nas najefektywniejsza. U³o¿enie ³okci jak w æwiczeniu 1-ze sztang¹. Mo¿na równie¿ pomin¹æ supinacjê nadgarstka, ale zmniejsza to efektywnoœæ æwiczenia.",
				new String[] { "dwug³owe ramion", "ramienno promieniowe", "miêsnie przedramion" }).saveExercise();

		new Exercise("UGINANIE RAMION ZE SZTANG¥ NA „MODLITEWNIKU”",
				"Æwiczenie zarówno na rozwój masy, jak i „wypiêtrzenie” bicepsów, a to za spraw¹ miêœnia ramiennego, po³o¿onego pod dwug³owym, który wypycha go do góry. Siadamy na siode³ku modlitewnika. Nogi rozstawiamy w taki sposób, by pozwoli³y nam utrzymaæ stabiln¹ pozycjê. Górna krawêdŸ modlitewnika powinna znaleŸæ siê pod naszymi pachami. Ramiona rozstawione na szerokoœæ barków-równolegle do siebie. Rozstaw d³oni, podobnie jak w æwiczeniu ze sztanga stoj¹c-w zale¿noœci od celu æwiczenia(zaanga¿owanie poszczególnych g³ów, jak w æwiczeniu ze sztang¹ stoj¹c). Zakres ruchu: od pe³nego rozgiêcia bicepsów(nie ramion)do pe³nego ich skurczu, przy czym przedramiona nie powinny przekraczaæ linii pionu. Pe³ne rozci¹gniêcie bicepsów, to nie to samo, co pe³ny wyprost ramion. Nale¿y unikaæ(nie tylko w tym æwiczeniu) tzw. ”przeprostów” ramion, czyli nadmiernego ich wyprostowywania(do pe³nego zakresu ruchu w stawie ³okciowym).Faza negatywna ruchu-prostowanie ramion powinna odbywaæ siê przy pe³nej kontroli ciê¿aru. Opuszczamy sztangê wolniej ni¿ unosimy. Oddychanie jak w æwiczeniu ze sztang¹ stoj¹c. Do æwiczenia mo¿na u¿ywaæ zarówno sztangi prostej, jak i ³amanej-gryf ³amany zmniejsza napiêcia powstaj¹ce w nadgarstkach. Æwiczenie to mo¿na wykonywaæ równie¿ zastêpuj¹c sztangê dr¹¿kiem wyci¹gu dolnego, lub na specjalnych maszynach.",
				new String[] { "miêsnie dwug³owe ramion", "miêœnie ramienne", "miêœnie ramienno promieniowe" })
						.saveExercise();

		new Exercise("UGINANIE RAMION ZE SZTANGIELKAMI W SIADZIE NA £AWCE SKOŒNEJ(Z SUPINACJ¥ NADGARSTKA)",
				"Jest to jedno z tych æwiczeñ, które rozwijaj¹ zarówno masê miêœni dwug³owych, jak i charakterystyczny zaokr¹glony kszta³t. Trzeba je wykonywaæ z du¿¹ koncentracj¹. Siadamy na ³awce skoœnej, o nachyleniu ok.45 stopni. Przedramiona powinny byæ lekko odchylone od tu³owia, a ³okcie przylegaæ do niego. Wymodelowaniu kszta³tu miêœni s³u¿y „supinacja” nadgarstka. Polega ona na stopniowym obracaniu d³oni w trakcie wykonywania æwiczenia. W pozycji wyjœciowej( ramiona wyprostowane) d³onie zwrócone s¹ ku sobie palcami, a w miarê uginania ramion obracaj¹ siê tak, by w koñcowym momencie ruchu(przy zgiêtych ramionach)ma³e palce by³y wy¿ej od kciuków. Wskazane dla lepszego rozwoju bicepsów jest zatrzymanie ruchu w koñcowym po³o¿eniu i maksymalne napiêcie miêœni przez 1-3 sekundy. Ruch mo¿na wykonywaæ na przemian-raz jedna rêka, raz druga(po 1 powtórzeniu), obiema rêkami jednoczeœnie, lub opuszczaj¹c jedn¹ rêkê jednoczeœnie unosz¹c drug¹. Samemu trzeba wybraæ, która wersja jest dla nas najefektywniejsza.",
				new String[] { "miêsnie dwug³owe ramion", "miêœnie ramienne", "miêœnie przedramion",
						"miêœnie ramienno promieniowe" }).saveExercise();

		new Exercise("UGINANIE RAMIENIA ZE SZTANGIELK¥ W SIADZIE-W PODPORZE O KOLANO",
				"Jest to æwiczenie modeluj¹ce kszta³t bicepsów-ich wierzcho³ek. Wymaga ono du¿ej koncentracji w czasie wykonywania. Siadamy na ³awce lub krzeœle, pochylamy siê lekko do przodu. Chwytamy sztangielkê w d³oñ i opieramy ³okieæ o wewnêtrzn¹ czêœæ uda. Ruch powinien mieæ wolne tempo(zarówno podczas unoszenia i opuszczania)-jest to æwiczenie koncentryczne i technika jest w nim wa¿niejsza od wielkoœci ciê¿aru. Mo¿na równie¿ w tym æwiczeniu stosowaæ supinacjê nadgarstka. Ramiona „zamykamy” do koñca, napinaj¹c maksymalnie miêsieñ. Prostujemy ramiê(jak w innych æwiczeniach na miêœnie dwug³owe)tylko do momentu pe³nego rozci¹gniêcia bicepsów, nie do pe³nego zakresu ruchu w stawie ³okciowym.",
				new String[] { "miêsnie dwug³owe ramion", "miêœnie ramienne", "miêœnie przedramion" }).saveExercise();

		new Exercise("UGINANIE RAMION ZE SZTANGA NACHWYTEM STOJ¥C",
				"Stajemy w rozkroku na szerokoœæ barków(lub nieco szerszym) sztangê chwytamy nachwytem. £okcie nieruchomo przy tu³owiu, nadgarstki zblokowane w jednej pozycji przez ca³y czas æwiczenia. Ruch odbywa siê tylko w stawach ³okciowych. Opuszczanie odbywa siê wolnym tempem i pod pe³n¹ kontrol¹ ciê¿aru. Oddychanie, jak w æwiczeniu ze sztang¹ podchwytem. Æwiczenie to mo¿na wykonywaæ równie¿ zastêpuj¹c sztangê dr¹¿kiem wyci¹gu dolnego.",
				new String[] { "miêsnie ramienno promieniowe(umieszczone na wierzchu przedramion)",
						"miêœnie dwug³owe ramion", "miêœnie przedramion" }).saveExercise();

		new Exercise("UGINANIE NADGARSTKÓW PODCHWYTEM W SIADZIE",
				"Æwiczenie to rozwija wewnêtrzn¹ czêœæ przedramion-podstawowe dla rozwoju tej partii miêœni. Wyrabia si³ê uœcisku d³oni. £apiemy sztangê w siadzie podchwytem, rozstaw d³oni ok. 15 cm(szerszy nadwyrê¿a nadgarstki).Opieramy przedramiona o uda, tak by poza nogi wystawa³y jedynie nasze d³onie. Pracuj¹ tylko nadgarstki. Staramy siê, aby zakres ruchu by³ jak najwiêkszy i w tym celu pozwalamy w dolnym po³o¿eniu na stoczenie siê sztangi a¿ do koñców palców - po czym ponownie œciskamy d³oñ i zginamy maksymalnie nadgarstek. Æwiczenie mo¿na wykonywaæ równie¿ zastêpuj¹c sztangê dr¹¿kiem wyci¹gu dolnego, lub sztangielk¹ (jednor¹cz).",
				new String[] { "grupa miêsni przedramion-zginaczy nadgarstków" }).saveExercise();

		new Exercise("PROSTOWANIE RAMION NA WYCI¥GU STOJ¥C",
				"Stajemy w lekkim rozkroku przed wyci¹giem, chwytamy r¹czkê nachwytem na szerokoœæ ok.10-20 cm. Kciuki na r¹czce(tzw. ”ma³pi” chwyt).Lekko pochylamy siê w przód i naciskamy r¹czkê wyci¹gu w dó³. Ramiona przyciœniête do tu³owia. Ruch wykonuj¹ tylko przedramiona(pracuj¹ jedynie stawy ³okciowe). Ramiona prostujemy do koñca-dla lepszego napiêcia miêœni. Nie unosimy ³okci, gdy r¹czka wyci¹gu jest w górnym po³o¿eniu-spowodowa³oby to zanik napiêcia w tricepsach. Najwa¿niejsze jest sta³e utrzymanie napiêcia w æwiczonych miêœniach. Unikamy szarpania-ruch powinien byæ p³ynny, a faza opuszczania ciê¿aru wolniejsza, z pe³n¹ kontrol¹ ciê¿aru.",
				new String[] { "wszystkie g³owy miêœni trójg³owych ramion" }).saveExercise();

		new Exercise("WYCISKANIE „FRANCUSKIE”SZTANGI W SIADZIE",
				"Æwiczenie to rozwija wszystkie g³owy tricepsów ze szczególnym wskazaniem na g³owê d³uga. Mo¿na je wykonywaæ zarówno w pozycji stoj¹cej, jak i siedz¹cej. W pozycji siedz¹cej ponadto mo¿na zastosowaæ oparcie(np. na ³awce skoœnej).Polepsza to stabilnoœæ, a co za tym idzie pozwala lepiej skoncentrowaæ siê na wykonaniu æwiczenia. Mo¿emy u¿yæ sztangi prostej i ³amanej. £amana ma tê zaletê, ¿e pozwala na zwiêkszenie zakresu ruchu i zmniejsza naprê¿enia powstaj¹ce w nadgarstkach. £okcie powinny trwaæ nieruchomo(jak najbli¿ej g³owy) podczas æwiczenia. Zakres ruchu:od pe³nego wyprostu ramion, do pe³nego zgiêcia w ³okciach. Przy pe³nym zgiêciu ramion, gdy sztanga znajduje siê za g³ow¹, nie powinno siê traciæ nad ni¹ kontroli(swobodne opuszczenie jej przeci¹¿a mocno stawy ³okciowe, co mo¿e prowadziæ do kontuzji).Æwiczenie mo¿na wykonywaæ równie¿ zastêpuj¹c sztangê dr¹¿kiem wyci¹gu dolnego (prostym lub ³amanym, b¹dŸ grubym sznurem zakoñczonym wêz³ami-stosujemy wtedy uchwyt równoleg³y). Mo¿na równie¿ wykonywaæ nieco inn¹ odmianê æwiczenia ze sztangielk¹ trzyman¹ obur¹cz. Przy tej wersji nale¿y pamiêtaæ, o jednakowym anga¿owaniu w æwiczenie obu r¹k.",
				new String[] { "g³owy boczne i przyœrodkowe miêœni trójg³owych ramion" }).saveExercise();

		new Exercise("PROSTOWNIE RAMIENIA ZE SZTANGIELK¥ W OPADZIE TU£OWIA",
				"Przy wyprostowanych plecach pochylamy siê do przodu i opieramy jedn¹ rêk¹ o ³awkê. W drugiej trzymamy sztangielkê i unosimy ramiê nieco ponad liniê pleców(nie ni¿ej)-przedramiê prostopadle do pod³ogi. Z tej pozycji pracuj¹c tylko tricepsem prostujemy ramiê w ³okciu unosz¹c ciê¿ar a¿ do pe³nego wyprostu. W pozycji koñcowej zatrzymujemy na moment ruch dla lepszego napiêcia miêœni. Musimy zwracaæ szczególn¹ uwagê na to, by w trakcie æwiczenia w ruchu by³o jedynie przedramiê(praca w stawie ³okciowym).Nie wolno dopuszczaæ do bujania ramieniem. Ruch opuszczania wolniejszy od unoszenia, z pe³n¹ kontrol¹ ciê¿aru.",
				new String[] { "g³owy boczne i przyœrodkowe miêœni trójg³owych ramion" }).saveExercise();

		// Uda i poœladki
		new Exercise("PRZYSIADY ZE SZTANG¥ TRZYMAN¥ Z PRZODU",
				"Podobna technika, jak przy zwyk³ych przysiadach. Mocniej jednak anga¿owane s¹ miêœnie czworog³owe ud-spowodowane jest to pionow¹ pozycj¹ tu³owia zwi¹zan¹ z po³o¿eniem sztangi z przodu. Gryf spoczywa na przedniej czêœci miêœni naramiennych i górnej czêœci klatki piersiowej. Uchwyt na szerokoœæ barków-je¿eli trzymamy sztangê podchwytem,(co jest mo¿e mniej wygodne, ale bezpieczniejsze- gif 2a)lub wê¿szy-je¿eli trzymamy gryf nachwytem-ze skrzy¿owanymi ramionami",
				new String[] {
						"æwiczenie to anga¿uje przede wszystkim g³owy boczne i przyœrodkowe miêœni czworog³owych" })
								.saveExercise();

		new Exercise("HACK-PRZYSIADY",
				"Æwiczenie ró¿ni siê od zwyk³ych przysiadów u³o¿eniem sztangi(z ty³u za plecami, pod poœladkami w wyprostowanych rêkach)i, co za tym idzie bardziej pionow¹ pozycj¹ tu³owia w trakcie wykonania æwiczenia. Ze wzglêdu na technikê wykonania i u³o¿enie sztangi ciê¿ar, jaki u¿yjemy w tym æwiczeniu bêdzie mniejszy, ni¿ w zwyk³ych przysiadach. Pozycja wyjœciowa to wyprostowany tu³ów, klatka wypchniêta ku przodowi, nogi w rozkroku na szerokoœæ barków, ramiona wyprostowane wzd³u¿ tu³owia, w d³oniach gryf sztangi(trzymany za plecami). Z tej pozycji wykonujemy przysiad do momentu, gdy nasze nogi bêd¹ ugiête pod k¹tem 90 stopni lub nieco mniejszym. Jednoczeœnie wypychamy kolana nieco do przodu, nie odrywaj¹c jednak stóp od pod³o¿a-ciê¿ar wypychamy z piêt. Æwiczenie mo¿na równie¿ wykonaæ przy pomocy suwnicy Smitha",
				new String[] { "wszystkie g³owy miêsni czworog³owych ud" }).saveExercise();

		new Exercise("SYZYFKI",
				"Æwiczenie to mo¿na wykonywaæ zarówno bez obci¹¿enia, jak i z nim. Chwytamy wtedy w jedn¹ rêkê kr¹¿ek i k³adziemy go sobie na klatce, drug¹ rêk¹ podpieramy siê dla zachowania równowagi czegoœ stabilnego. Æwiczenie polega na wykonaniu przysiadu z jednoczesnym mocnym odchyleniem tu³owia do ty³u i wspiêciem na palce stóp po³¹czonym z wypchniêciem kolan do przodu-dla lepszego rozci¹gniêcia miêœni górnej czêœci ud i zwiêkszenia poziomu trudnoœci æwiczenia.",
				new String[] { "wszystkie g³owy miêœni czworog³owych ud" }).saveExercise();

		new Exercise("PROSTOWNIE NÓG W SIADZIE",
				"siadamy na siodle maszyny(³awki), dobrze gdy mamy oparcie-zapewnia ono lepsz¹ stabilnoœæ tu³owia, d³oñmi chwytamy za uchwyt(lub krawêdŸ) maszyny. Nogi ugiête w kolanach, zaparte o dr¹¿ek maszyny na stopami(na wysokoœci kostek). Z tej pozycji wykonujemy ruch prostowania nóg do pe³nego wyprostu w stawach kolanowych. W pozycji wyprostowanej zatrzymujemy ruch przez chwilê dla lepszego napiêcia miêœni. Po czym powracamy do pozycji wyjœciowej. Powrót w tempie wolnym i pod pe³n¹ kontrol¹ ciê¿aru. Powietrze nabieramy przed rozpoczêciem prostowania, wypuszczamy je, gdy koñczymy prostowanie nóg.",
				new String[] { "zaanga¿owane wszystkie g³owy miêœni czworog³owych ud" }).saveExercise();

		new Exercise("UGINANIE NÓG W LE¯ENIU",
				"Æwiczenie to wykonujemy na specjalnej maszynie,lub za pomoc¹ wyci¹gu i specjalnym opasek na nogi(gif 8). K³adziemy siê na brzuchy tak, by poza ³awkê wystawa³y jedynie podudzia poni¿ej kolan, nogi wyprostowane w kolanach, zaparte o dr¹¿ek maszyny na wysokoœci œciêgien Achillesa(nad piêtami), d³onie na uchwytach(lub krawêdzi) maszyny. Z tej pozycji wykonujemy ruch maksymalnego uginania nóg w kolanach. W koñcowym momencie uginania zatrzymujemy ruch przez chwilê dla lepszego napiêcia miêœni. Po czym powracamy do pozycji wyjœciowej. Powrót w tempie wolnym i pod pe³n¹ kontrol¹ ciê¿aru. Biodra(jak i reszta tu³owia) przez ca³y czas trwania æwiczenia przylegaj¹ do powierzchni ³awki. Powietrze nabieramy przed rozpoczêciem uginania, wypuszczamy je, gdy koñczymy uginanie nóg. Æwiczenie to mo¿na wykonaæ równie¿ w pozycji stoj¹c(jednonó¿)",
				new String[] { "miêœnie dwug³owe ud", "miêœnie pó³œciêgniste", "miêœnie smuk³e", "miêœnie krawieckie" })
						.saveExercise();

		new Exercise("NO¯YCE",
				"Æwiczenie to jest odwrotnoœci¹ poprzedniego-wykrok jest robiony do ty³u zamiast do przodu. Przysiad wykonujemy nie na nodze wykrocznej, ale na zakrocznej-czyli tej, która pozostaje w miejscu. Æwiczenie to mo¿na wykonaæ ze sztang¹, sztangielkami, lub za pomoc¹ suwnicy Smitha",
				new String[] { "przywodziciele krótkie i wielkie", "w mniejszym stopniu g³owy boczne",
						"przyœrodkowe miêsni czworog³owych ud" }).saveExercise();

		new Exercise("ŒCI¥GANIE KOLAN W SIADZIE",
				"Æwiczenie wykonujemy na specjalnej maszynie siedz¹c. Plecy oparte o oparcie maszyny, nogi ugiête w kolanach pod k¹tem prostym, oparte o poduszki maszyny. Z tej pozycji wykonujemy ruch ³¹czenie œci¹gania nóg do wewn¹trz, jak w celu z³¹czenia ich ze sob¹, pokonuj¹c jednoczeœnie opór maszyny. W pozycji maksymalnego œci¹gniêcia nóg zatrzymujemy ruch przez chwilê dla lepszego napiêcia miêœni. Po czym powracamy do pozycji wyjœciowej. Powrót w tempie wolnym i pod pe³n¹ kontrol¹ ciê¿aru. Powietrze nabieramy przed rozpoczêciem œci¹gania, wypuszczamy je, gdy koñczymy œci¹ganie-nogi s¹ w po³o¿eniu najbli¿szym sobie.",
				new String[] { "miêœnie przywodziciele wielkie" }).saveExercise();

		new Exercise("PRZYWODZENIE NÓG DO WEWN¥TRZ",
				"Stajemy przy wyci¹gu dolnym, zak³adamy na nogê(na wysokoœci kostki)specjaln¹ opaskê po³¹czon¹ z link¹ wyci¹gu. Stajemy w takiej odleg³oœci od wyci¹gu, by ruch zaczyna³ siê w momencie, gdy æwiczona noga odchylona jest od pionu w kierunku wyci¹gu(gif 15). Z tej pozycji wykonujemy przywodzenie nogi przed sob¹, a¿ do momentu, gdy trenowana noga znajdzie siê w pozycji odchylonej od pionu w kierunku przeciwnym do wyci¹gu. W koñcowym momencie( maksymalne wychylenie nogi w górê, do wewn¹trz) mo¿na zatrzymaæ ruch na moment, po czym wracamy do pozycji wyjœciowej.",
				new String[] { "miêœnie przywodziciele wielkie" }).saveExercise();

		new Exercise("ODWODZENIE NÓG NA ZEWN¥TRZ",
				"Stajemy przy wyci¹gu dolnym, zak³adamy na nogê(na wysokoœci kostki)specjaln¹ opaskê po³¹czon¹ z link¹ wyci¹gu. Stajemy w takiej odleg³oœci od wyci¹gu, by ruch zaczyna³ siê w momencie, gdy æwiczona noga odchylona jest od pionu w kierunku wyci¹gu. Z tej pozycji wykonujemy odwodzenie(nie wymachy, jak to æwiczenie nazywane jest czêsto)nogi w kierunku przeciwnym do wyci¹gu. W koñcowym momencie( maksymalne wychylenie nogi w górê) mo¿na zatrzymaæ ruch na moment, po czym wracamy do pozycji wyjœciowej.",
				new String[] { "przywodziciele wielkie", "przywodziciele d³ugie i smuk³e" }).saveExercise();

		// ³ydki
		new Exercise("WSPIECIA NA PALCE W STANIU",
				"Æwiczenie to mo¿na wykonywaæ zarówno przy pomocy sztangi, suwnicy Smitha lub specjalnej maszyny. Mo¿na wykonywaæ je równie¿ bez obci¹¿enia, a tak¿e jednonó¿.Sztangê mo¿na równie¿ zast¹piæ sztangielk¹ trzyman¹ w d³oni(po tej samej stronie, co æwiczona noga: lewa noga- lewa rêka, prawa noga- prawa rêka). Istotnym elementem w tym æwiczeniu jest u¿ycie grubej podk³adki pod palce stóp, która pozwala zwiêkszyæ znacznie zakres ruchu, a co za tym idzie-poprawiæ efektywnoœæ æwiczenia. Pozycja wyjœciowa, to wyprostowany tu³ów i plecy, nogi wyprostowane w kolanach, rozkrok 25-30 cm, palce stóp(wraz ze stawami ³¹cz¹cymi je ze œródstopiem) na podk³adce-miêsnie ³ydek rozci¹gniête maksymalnie. Z takiej pozycji rozpoczynamy wspiêcia. Ruch powinien byæ wolny i dok³adny, ze sta³ym „czuciem” pracy miêœni. Nale¿y unikaæ odbijania siê piêt od pod³ogi.",
				new String[] { "obie g³owy miêsni brzuchatych ³ydek", "miêœnie p³aszczkowate", "strza³kowe d³ugie" })
						.saveExercise();

		new Exercise("WSPIÊCIA NA PALCE NA HACK-MASZYNIE",
				"Æwiczenie to mo¿na wykonywaæ w pozycji ty³em do maszyny, jak równie¿ przodem do maszyny(o ile oczywiœcie dysponujemy maszyn¹ ze specjalnymi oparciami na barki). Wskazane jest, jak w pozosta³ych æwiczeniach na miêsnie ³ydek, grubej podk³adki pod palce.",
				new String[] { "przednie g³owy miêœni brzuchatych ³ydek", "miêœnie p³aszczkowate ³ydek",
						"g³owy boczne miêœni brzuchatych", "miêœnie strza³kowe d³ugie" }).saveExercise();

		new Exercise("WYPYCHANIE CIÊ¯ARU NA MASZYNIE LUB SUWNICY PALCAMI NÓG",
				"Jest to, jakby odwrotna wersja wspiêæ na Hack-maszynie- odwrotna jest pozycja-g³owa znajduje siê ni¿ej nóg. Pozycja wyjœciowa to siad na siedzisku maszyny/suwnicy, plecy oparte, nogi wyprostowane w kolanach, stopy dotykaj¹ do p³aszczyzny maszyny/suwnicy tylko palcami i stawami ³¹cz¹cymi je ze sródstopiem, miêœnie ³ydek rozci¹gniête maksymalnie. Z tej pozycji wypychamy ciê¿ar si³¹ miêsni ³ydek.",
				new String[] { "przednie g³owy miêœni brzuchatych ³ydek", "miêœnie p³aszczkowate ³ydek",
						"g³owy boczne miêœni brzuchatych", "miêœnie strza³kowe d³ugie" }).saveExercise();

		new Exercise("ODWROTNE WSPIÊCIA W STANIU",
				"Æwiczenie podobne do wspiêæ na palce- ró¿nica jest taka, ¿e podk³adki s¹ pod piêtami, a unosimy nie piêty, lecz œródstopia nóg. Pozycja taka, jak przy wspiêciach na palce, ale nie rozci¹gamy miêœni ³ydek w pocz¹tkowej fazie-tylko miêœnie piszczelowe.",
				new String[] { "miêœnie piszczelowe" }).saveExercise();

		// przedramiona
		new Exercise("UGINANIE NADGARSTKÓW PODCHWYTEM W SIADZIE",
				"Æwiczenie to rozwija wewnêtrzn¹ czêœæ przedramion-podstawowe dla rozwoju tej partii miêœni. Wyrabia si³ê uœcisku d³oni. £apiemy sztangê w siadzie podchwytem, rozstaw d³oni ok. 15 cm(szerszy nadwyrê¿a nadgarstki).Opieramy przedramiona o uda, tak by poza nogi wystawa³y jedynie nasze d³onie. Pracuj¹ tylko nadgarstki. Staramy siê, aby zakres ruchu by³ jak najwiêkszy i w tym celu pozwalamy w dolnym po³o¿eniu na stoczenie siê sztangi a¿ do koñców palców - po czym ponownie œciskamy d³oñ i zginamy maksymalnie nadgarstek. Æwiczenie mo¿na wykonywaæ równie¿ zastêpuj¹c sztangê dr¹¿kiem wyci¹gu dolnego, lub sztangielk¹ (jednor¹cz).",
				new String[] { "grupa miêsni przedramion-zginaczy nadgarstków" }).saveExercise();

		new Exercise("UGINANIE NADGARSTKÓW NACHWYTEM W SIADZIE",
				"Pozycja i technika, jak w æwiczeniu poprzednim(podchwytem)-ró¿nica polega na uchwycie sztangi-w tym æwiczeniu stosujemy nachwyt i raczej nie pozwalamy na „zjechanie” sztangi na koñce palców. Zginaj¹c nadgarstki opuszczamy sztangê tak mocno, jak pozwala na to zakres ruchu, po czym unosimy si³¹ nadgarstków sztangê tak wysoko, jak tylko damy radê. W ruchu pozostaje jedynie nadgarstek.",
				new String[] { "grupa miêsni przedramion-prostowników nadgarstków" }).saveExercise();

		// brzuch
		new Exercise("SK£ONY W LE¯ENIU P£ASKO",
				"K³adziemy siê na materacu lub ³awce. Nogi ugiête, rêce nad g³ow¹ i unosimy tu³ów w górê. Pierwsza do góry unosi siê g³owa, potem barki, a na koñcu reszta tu³owia. Dla lepszego zaanga¿owania miêœni skoœnych brzucha, w koñcowej fazie unoszenia tu³owia mo¿na wykonywaæ nim skrêty. Jest to jednak wersja trudniejsza i bardziej nara¿aj¹ca na ewentualne kontuzje(mocniej obci¹¿a dolne partie grzbietu).Nabieramy powietrza przed rozpoczêciem ruchu, a wypuszczamy je w trakcie unoszenia tu³owia.",
				new String[] { "miêœnie proste brzucha", "skoœne brzucha" }).saveExercise();

		new Exercise("SK£ONY W LE¯ENIU G£OW¥ W DÓ£",
				"Wykonanie jak w æwiczeniu poprzednim-p³asko, ale pozycja wyjœciowa jest g³ow¹ w dó³ na ³awce skoœnej. Dla lepszego zaanga¿owania miêœni skoœnych brzucha w koñcowej fazie unoszenia tu³owia mo¿na wykonywaæ nim skrêty. Jest to jednak wersja trudniejsza i bardziej nara¿aj¹ca na ewentualne kontuzje(mocniej obci¹¿a dolne partie grzbietu).",
				new String[] { "miêœnie proste brzucha", "skoœne brzucha" }).saveExercise();

		new Exercise("UNOSZENIE NÓG W LE¯ENIU NA SKOŒNEJ £AWCE",
				"K³adziemy siê na ³awce poziomej lub skoœnej-g³ow¹ do góry, ramiona za g³ow¹(najlepiej jeœli trzymamy jakiœ punkt oparcia np. ³awkê lub dr¹¿ek), tu³ów przylega do pod³o¿a. Z tej pozycji unosimy nogi do klatki jednoczeœnie zginaj¹c je lekko w kolanach. Nabieramy powietrza przed rozpoczêciem ruchu, a wypuszczamy je w trakcie unoszenia nóg. Pod koniec unoszenia mo¿na skrêcaæ nieco tu³ów i biodra dla zaanga¿owania miêœni skoœnych brzucha.",
				new String[] { "miêœnie proste brzucha", "skoœne brzucha" }).saveExercise();

		new Exercise("UNOSZENIE NÓG W ZWISIE NA DR¥¯KU",
				"Chwytamy dr¹¿ek prosty nachwytem lub podchwytem, je¿eli mamy k³opot z d³u¿szym utrzymaniem siê na dr¹¿ku, mo¿emy zastosowaæ paski. Unosimy nogi jak najwy¿ej do brody. Mo¿na równie¿ w tym æwiczeniu wprowadziæ skrêty tu³owia w koñcowej fazie unoszenia nóg, co mocniej zaanga¿uje do pracy miêœnie skoœne brzucha. Jeszcze inna wersja(mocno anga¿uj¹ca miêœnie skoœne)polega na jednoczesnym skrêcie bioder wraz z unoszeniem nóg. Osoby zaawansowane mog¹ w tym æwiczeniu u¿ywaæ dodatkowego obci¹¿enia zamocowanego do nóg, ale tylko je¿eli czuj¹ siê na si³ach-³atwo „przedobrzyæ” i nabawiæ siê bolesnej kontuzji. Tempo ruchu umiarkowane, bez zrywów. Im mniejsze ugiêcie nóg w kolanach, tym wiêkszy stopieñ trudnoœci æwiczenia, wiêksze zaanga¿owanie miêœni zginaczy bioder(przy zmniejszeniu pracy miêsni brzucha) i wiêksze napiêcia w dolnym odcinku grzbietu.",
				new String[] { "miêœnie proste brzucha", "skoœne brzucha", "miêsnie zêbate przednie" }).saveExercise();

		new Exercise("UNOSZENIE NÓG W PODPORZE",
				"Stajemy plecami do specjalnej podpory, ramiona opieramy na poziomych poprzeczkach podpory, d³oñmi chwytamy uchwyty, w tym momencie znajdujemy siê ju¿ ponad pod³og¹. Z tej pozycji unosimy nogi w górê, w kierunku klatki piersiowej, jednoczeœnie uginaj¹c je w kolanach. Technika podobna, jak w unoszeniu nóg w zwisie, jednak miêœnie zaanga¿owane pod innym k¹tem. W tym æwiczeniu równie¿ nale¿y pamiêtaæ o zachowaniu pe³nego zakresu ruchu-zmniejszanie go prowadzi do skracania miêœni. Tempo ruchu umiarkowane, bez zrywów. Im mniejsze ugiêcie nóg w kolanach, tym wiêkszy stopieñ trudnoœci æwiczenia, wiêksze zaanga¿owanie miêœni zginaczy bioder(przy zmniejszeniu pracy miêsni brzucha) i wiêksze napiêcia w dolnym odcinku grzbietu.",
				new String[] { "miêœnie proste brzucha", "skoœne brzucha," }).saveExercise();

		new Exercise("”SPINANIE”, UNOSZENIE KOLAN W LE¯ENIU P£ASKO",
				"K³adziemy siê na ³awce lub materacu p³asko, nogi wyprostowane, ramiona uniesione do góry nad g³ow¹(dla lepszej stabilizacji mo¿na chwyciæ nimi za jakiœ punkt oparcia- np. ³awkê) i z tej pozycji podci¹gamy kolana do klatki piersiowej. Æwiczenie to mo¿na równie¿ wykonywaæ z dodatkowym obci¹¿eniem w postaci linki wyci¹gu zahaczonej o nogi",
				new String[] { "miêœnie proste brzucha" }).saveExercise();

		new Exercise("SK£ONY TU£OWIA Z LINK¥ WYCI¥GU SIEDZ¥C",
				"Siadamy na ³awce, tu³ów wyprostowany,(najlepiej z podpor¹ pod plecy),za plecami mamy wyci¹g górny(zamiast r¹czki zaczepiona lina z wêz³ami na koñcach),chwytamy koniec liny(w ten sposób, ¿e otacza nam z ty³u kark), z tej pozycji wykonujemy sk³ony w przód na taka g³êbokoœæ, by nie odrywaæ dolnego odcinka pleców od oparcia, staraj¹c siê przez ca³y czas utrzymaæ dolny odcinek grzbietu wyprostowany. Powrotny ruch kontrolowany i w wolnym tempie. Bardzo podobne dzia³anie ma æwiczenie wykonane na specjalnej maszynie",
				new String[] { "proste brzucha", "skoœne brzucha" }).saveExercise();

		new Exercise("SKRÊTY TU£OWIA",
				"Æwiczenie to mo¿na wykonaæ zarówno w pozycji siedz¹cej, jak i stoj¹cej-na maszynie lub, jeœli takiej nie posiadamy-za pomoc¹ gryfu sztangi(tylko nie „olimpijskiej”- mo¿e byæ zbyt ciê¿ki)zaawansowani mog¹ pozwoliæ sobie na u¿ycie pewnego obci¹¿enia, oczywiœcie z umiarem. Zbyt du¿e przeci¹¿a dolny odcinek krêgos³upa. W pozycji stoj¹cej- stajemy w rozkroku szerszym ni¿ barki, gryf k³adziemy na karku, ramiona oparte szeroko na gryfie. W pozycji siedz¹cej(na maszynie) chwytamy r¹czki maszyny, tu³ów wyprostowany przez ca³y czas wykonania æwiczenia, nogi w jednakowej pozycji(ugiête w kolanach i skierowane do przodu-najlepiej, gdy s¹ zaparte- dla lepszej stabilizacji tu³owia)w trakcie ca³ego æwiczenia.",
				new String[] { "skoœne brzucha", "proste brzucha", "prostowniki grzbietu" }).saveExercise();

		new Exercise("SK£ONY BOCZNE",
				"Stajemy w lekkim rozkroku(na szerokoœæ barków, lub nieco szerzej),tu³ów wyprostowany, w jedn¹ rêkê chwytamy sztangielkê, drug¹ zak³adamy sobie na g³owê(d³oni¹). Z tej pozycji wykonujemy sk³on w kierunku wolnej rêki, napinaj¹c miêœnie skoœne brzucha. Oddech bierzemy w momencie rozpoczêcia ruchu, wypuszczamy powietrze w momencie maksymalnego sk³onu. Powrót do pozycji wyjœciowej mo¿e byæ z przekroczeniem linii pionu(tu³owia)-zwiêksza to napiêcie miêœni skoœnych. Ruch powinien byæ p³ynny i w wolnym tempie, bez gwa³townych szarpniêæ. Æwiczenie to mo¿na równie¿ wykonaæ przy pomocy wyci¹gu dolnego-zastêpuj¹c nim sztangielkê.",
				new String[] { "skoœne brzucha" }).saveExercise();

		new Exercise("SK£ONY TU£OWIA Z LINK¥ WYCI¥GU KLÊCZ¥C",
				"Do tego æwiczenia znów potrzebna bêdzie specjalna lina za wêz³ami na koñcach, zastêpuj¹ca r¹czkê wyci¹gu,(jeœli takiej nie posiadamy mo¿na æwiczenie wykonaæ trzymaj¹c r¹czkê wyci¹gu nad g³ow¹-zmieni siê nieco po³o¿enie d³oni w æwiczeniu na mniej wygodne).Klêkamy przed wyci¹giem górnym, biodra cofniête do ty³u, chwytamy koñce liny tak, by przebiega³a ona nad g³ow¹, z tej pozycji wykonujemy sk³ony tu³owia w przód pokonuj¹c opór wyci¹gu, jednoczeœnie napinaj¹c miêœnie brzucha. Bardzo istotne jest, by „czuæ” w³aœciw¹ pracê miêœni brzucha-tylko one wykonuj¹ prace. Unikamy ruchów ramionami(anga¿uje to do pracy miêsnie najszersze)-pozostaj¹ one w jednakowym po³o¿eniu, d³onie obok g³owy(lub nad ni¹). Powrotny ruch kontrolowany i w wolnym tempie. Zalecany ostro¿ny dobór obci¹¿enia-zbyt du¿e przeszkadza w poprawnym wykonaniu æwiczenia i ponadto przeci¹¿a dolne partie grzbietu.",
				new String[] { "proste brzucha", "skoœne brzucha" }).saveExercise();

		return true;
	}

	public void closeWindow() {
		stage.close();
	}
}
