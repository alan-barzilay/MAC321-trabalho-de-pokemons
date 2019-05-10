package pokemon;
public class Controller {
	
	private EventSet es = new EventSet();	
	public void addEvent(Event c) { es.add(c); }
	
	public void run() {
		// checa qual dos 2 eventos tem a maior prioridade, roda eles na ordem certa e dps limpa a lista de eventos
		Event e0 = es.getEvents()[0];
		Event e1 = es.getEvents()[1];
		
		// checa corner cases e lida com eles
		if(e0 == null && e1 ==null) {
			return;
		}
		else if(e0 == null) {
			e1.action();
			System.out.println(e1.description());
			es.clearEvents();
			return;
		}
		else if(e1 == null) {
			e0.action();
			System.out.println(e0.description());
			es.clearEvents();
			return;
		}
		
		//comportamento normal
		if(e0.getPriority() > e1.getPriority()) {	
			e0.action();
			System.out.println(e0.description());
			
			e1 = es.getEvents()[1];
			if (e1==null) {
				return;
			}
			e1.action();
			System.out.println(e1.description());
			es.clearEvents();			
		}
		
		else if(e0.getPriority() < e1.getPriority()){
			e1.action();
			System.out.println(e1.description());
			
			
			e0 = es.getEvents()[0];
			if (e0==null) {
				return;
			}
			e0.action();
			System.out.println(e0.description());
			es.clearEvents();
		}		
		else {
			System.out.println("Alguem errou na hora de atribuir prioridades");
		}
	}

	public void clearEvents() {
		es.clearEvents();
	}
	
	
	public static void imprimeLogo() {
		System.out.println("                                   ,'\\\n" + 
				"    _.----.        ____         ,'  _\\   ___    ___     ____\n" + 
				"_,-'       `.     |    |  /`.   \\,-'    |   \\  /   |   |    \\  |`.\n" + 
				"\\      __    \\    '-.  | /   `.  ___    |    \\/    |   '-.   \\ |  |\n" + 
				" \\.    \\ \\   |  __  |  |/    ,','_  `.  |          | __  |    \\|  |\n" + 
				"   \\    \\/   /,' _`.|      ,' / / / /   |          ,' _`.|     |  |\n" + 
				"    \\     ,-'/  /   \\    ,'   | \\/ / ,`.|         /  /   \\  |     |\n" + 
				"     \\    \\ |   \\_/  |   `-.  \\    `'  /|  |    ||   \\_/  | |\\    |\n" + 
				"      \\    \\ \\      /       `-.`.___,-' |  |\\  /| \\      /  | |   |\n" + 
				"       \\    \\ `.__,'|  |`-._    `|      |__| \\/ |  `.__,'|  | |   |\n" + 
				"        \\_.-'       |__|    `-._ |              '-.|     '-.| |   |\n" + 
				"                                `'                            '-._|");		
	}
	
	public static void imprimeCharizard() {
		System.out.println(" 	  .\"-,.__\n" + 
				"                 `.     `.  ,\n" + 
				"              .--'  .._,'\"-' `.\n" + 
				"             .    .'         `'\n" + 
				"             `.   /          ,'\n" + 
				"               `  '--.   ,-\"'\n" + 
				"                `\"`   |  \\\n" + 
				"                   -. \\, |\n" + 
				"                    `--Y.'      ___.\n" + 
				"                         \\     L._, \\\n" + 
				"               _.,        `.   <  <\\                _\n" + 
				"             ,' '           `, `.   | \\            ( `\n" + 
				"          ../, `.            `  |    .\\`.           \\ \\_\n" + 
				"         ,' ,..  .           _.,'    ||\\l            )  '\".\n" + 
				"        , ,'   \\           ,'.-.`-._,'  |           .  _._`.\n" + 
				"      ,' /      \\ \\        `' ' `--/   | \\          / /   ..\\\n" + 
				"    .'  /        \\ .         |\\__ - _ ,'` `        / /     `.`.\n" + 
				"    |  '          ..         `-...-\"  |  `-'      / /        . `.\n" + 
				"    | /           |L__           |    |          / /          `. `.\n" + 
				"   , /            .   .          |    |         / /             ` `\n" + 
				"  / /          ,. ,`._ `-_       |    |  _   ,-' /               ` \\\n" + 
				" / .           \\\"`_/. `-_ \\_,.  ,'    +-' `-'  _,        ..,-.    \\`.\n" + 
				".  '         .-f    ,'   `    '.       \\__.---'     _   .'   '     \\ \\\n" + 
				"' /          `.'    l     .' /          \\..      ,_|/   `.  ,'`     L`\n" + 
				"|'      _.-\"\"` `.    \\ _,'  `            \\ `.___`.'\"`-.  , |   |    | \\\n" + 
				"||    ,'      `. `.   '       _,...._        `  |    `/ '  |   '     .|\n" + 
				"||  ,'          `. ;.,.---' ,'       `.   `.. `-'  .-' /_ .'    ;_   ||\n" + 
				"|| '              V      / /           `   | `   ,'   ,' '.    !  `. ||\n" + 
				"||/            _,-------7 '              . |  `-'    l         /    `||\n" + 
				". |          ,' .-   ,' ||               | .-.        `.      .'     ||\n" + 
				" `'        ,'    `\".'    |               |    `.        '. -.'       `'\n" + 
				"          /      ,'      |               |,'    \\-.._,.'/'\n" + 
				"          .     /        .               .       \\    .''\n" + 
				"        .`.    |         `.             /         :_,'.'\n" + 
				"          \\ `...\\   _     ,'-.        .'         /_.-'\n" + 
				"           `-.__ `,  `'   .  _.>----''.  _  __  /\n" + 
				"                .'        /\"'          |  \"'   '_\n" + 
				"               /_|.-'\\ ,\".             '.'`__'-( \\\n" + 
				"                 / ,\"'\"\\,'               `/  `-.|\"  ");
	}

	public static void imprimeLapras() {
		System.out.println("                                       ,|\n" + 
				"                                       ||\n" + 
				"                               ,-\"'\"\"`' `._\n" + 
				"                              '----.     __`....._\n" + 
				"                               `    `.  `. ;      `.\n" + 
				"           FIM                  `.    `.  `   ,\"`. |\n" + 
				"                                  `.  _.`._   |  ' |\n" + 
				"                                  .','  ,' `.  `--'\n" + 
				"                                 /.' _,'    | /\n" + 
				"                                '/_.'       |.\n" + 
				"                                 `---`\".    ||\n" + 
				"                                       |    ||\n" + 
				"                                      ,'    `|\n" + 
				"                         _           /       |\n" + 
				"                        ' `.        .'       |\n" + 
				"                         .  `._  _,'/|       |\n" + 
				"                        _|     \"'  / |       '\n" + 
				"                    _,-' |        /  '        .\n" + 
				"                 |\"'            ,'  '          \\\n" + 
				"                 |   _        ,'   /            \\\n" + 
				"                 ;  '        /    j              .\n" + 
				"            ,\"--'    `.    .      |              |         ________\n" + 
				"            `.   -.       / '     |              |   _,-\"\"'   __.._\"`-._\n" + 
				"             ,' ,-.`-.__.' /      '              |.-'  _..--'\"       _.-'\n" + 
				"             \\.'   `-.___.'      ,               '__.-\"           _.'\n" + 
				"             /        _..--    . |              /               ,'\n" + 
				"           ,`      .-'         | |           _,'._          _,-'\n" + 
				"       _,-'      ,'           .' '       _.-'     \"-.....-\"'\n" + 
				"     ,'     __ ,'          _.'  /  __..-'\n" + 
				"   ,' _.-\"\"'  /         _.'  _.'-\"'\n" + 
				"  '-'\"       /      _.-' _.-\"\n" + 
				"            /    _.' _.-'\n" + 
				"           .   .'_.-'\n" + 
				"           | ,'.'\n" + 
				"           | .`\n" + 
				"            `");
	}
}