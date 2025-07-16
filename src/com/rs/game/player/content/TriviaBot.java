package com.rs.game.player.content;
 
import java.util.Random;

import com.rs.game.World;
import com.rs.game.player.Player;
import com.rs.utils.Misc;
 

 
/**
 * @Author: Danny & Innocent
 */
public class TriviaBot {
     
     
    private static String songs [][] = { 
          {"Hey I just met you, and this is crazy", "Call Me Maybe"},
          {"Let's take this back to straight Hip-Hop, and start it from scratch", "Berzerk"},
          {"Rejoice every time you hear the sound of my voice", "When I'm Gone"},
          {"I crashed my car into the bridge", "I Love It"},
          {"You hear my voice, you hear that sound, like thunder gonna shake the ground", "Roar"},
          {"Snap back to reality, oh there goes gravity oh, there goes Rabbit", "Lose yourself"},
          {"Become so tired, so much more aware", "Numb"} ,
    //JEFF'S
		  {"Hold your breath, my dear! We're going under!", "Dear Insanity"} ,
		  {"And the untold stories, are painted black and white!", "Webs We Weave"} ,
		  {"I've got to make it on my own!", "Vagrant Stories"} ,
		  {"I have nothing left to give. I have found a perfect end.", "Dear Agony"} ,
		  {"You got the world on its' knees! You're taking all that you please!", "Enemies"} ,
		  {"Fill in the blanks: 'What can I do to _ _ _ _  _ _ _  _ _ _?'", "make you see"} ,
		  {"welcome home! While away!", "The strength To Go On"} ,
		  {"I'm sorry, oh...", "My Black Dahlia"} ,
		  {"I'm missing your bed, I never sleep.", "The Quiet Screaming"} ,
		  {"Fill in the blanks: It's so hard! To see... 'when your are are _ _ _ _ _ _ _!'", "Rolling"}, 
		  {"Don't think that you're better than this! Malevolence goes both ways!", "Ultranumb"} ,
		  {"Fill in the blanks: We have to find a _ _ _ _ _ _ way!", "Better"} ,
		  {"I've heard it all and I'm done with that chit!", "Blow"} ,
		  {"He probably gets the nerve to walk the floor", "Miserable At Best"} ,
		  {"I'm catching rain in my open mouth!", "Wolves At The Door"} ,
		  {"Fill in the blanks: 'This is my _ _ _ _ _'", "Curse"} ,
		  {"I'm sorry I forgot your name...", "Sick Or Sane"} ,
		  {"I'll die alone!", "The Martyr"} ,
		  {"My swan song will...", "Bite To Break Skin"} ,
		  {"Fill in the blanks: Sh-sh-sh-shake it _ _ _!'", "off"} ,
		  {"Fill in the blanks: I'll have you know.. That I've become _ _ _ _ _ _ _ _ _ _ _ _ _ _ _!'", "Indestructible"} ,
		  {"The room is spinning round' the bed...", "Burning Hearts"}};
		    
    private static String anagrams [][] = { //NEED 50
          {"Figure out this anagram: 'svrm'", "RSMV"},
		  {"Figure out this anagram: 'mrvs'", "RSMV"},
		  {"Figure out this anagram: 'svrm'", "RSMV"},
		  {"Figure out this anagram: 'srvm'", "RSMV"},
		  {"Figure out this anagram: 'vsmr'", "RSMV"},
		  
          {"Figure out this anagram: 'lieraavc'", "Cavalier"},
		  {"Figure out this anagram: 'rcvilaae'", "Cavalier"},
		  {"Figure out this anagram: 'valircea'", "Cavalier"},
		  {"Figure out this anagram: 'aevclrai'", "Cavalier"},
		  {"Figure out this anagram: 'lrvecaia'", "Cavalier"},
		  
		  
          {"Figure out this anagram: 'yosn vgesa'", "Sony Vegas"},
		  {"Figure out this anagram: 'nsoy esgav'", "Sony Vegas"},
		  {"Figure out this anagram: 'osyn aesvg'", "Sony Vegas"},
		  {"Figure out this anagram: 'oyns vages'", "Sony Vegas"},
		  {"Figure out this anagram: 'yons vgsae'", "Sony Vegas"},
		   
          {"Figure out this anagram: 'aetrf efefcst'", "After Effects"},
          {"Figure out this anagram: 'wsinodw iovem krame'", "Windows Movie Maker"},
          {"Figure out this anagram: 'nvgeimu'", "Vengium"},
          {"Figure out this anagram: 'noivexta'", "Vexation"},
          {"Figure out this anagram: 'orobem'", "Boomer"},
          {"Figure out this anagram: 'gtoujhnnzyt'", "Johnnyguttz"},
          {"Figure out this anagram: 'mixta'", "Matix"},
          {"Figure out this anagram: 'thecqhmpa'", "TheChampQ"},
          {"Figure out this anagram: 'egam eapc cnsy'", "Mage Cape Sync"},
		  
          {"Figure out this anagram: 'smtvrse'", "RSMVSet"},
		  {"Figure out this anagram: 'mrvests'", "RSMVSet"},
		  {"Figure out this anagram: 'vmtsrse'", "RSMVSet"},
		  {"Figure out this anagram: 'vmstser'", "RSMVSet"},
		  {"Figure out this anagram: 'rmstsve'", "RSMVSet"},
			
		  
          {"Figure out this anagram: 'klbtecukcuy'", "Luckybucket"},
		  {"Figure out this anagram: 'etlbkcucuky'", "Luckybucket"},
		  {"Figure out this anagram: 'kluubycckte'", "Luckybucket"},
		  {"Figure out this anagram: 'kuutlekccyb'", "Luckybucket"},
		  {"Figure out this anagram: 'ybkuelkucct'", "Luckybucket"},
		  
		  {"Figure out this anagram: 'cysceyohptr'", "Cryptosyche"},
		  {"Figure out this anagram: 'hysytporcce'", "Cryptosyche"},
		  {"Figure out this anagram: 'pyoeccrshty'", "Cryptosyche"},
		  {"Figure out this anagram: 'ocphtyrcyse'", "Cryptosyche"},
		  {"Figure out this anagram: 'hesrtocyypc'", "Cryptosyche"},
		  
		  {"Figure out this anagram: 'eonvstindehie'", "TheDivineOnes"},
		  {"Figure out this anagram: 'eesdnnoheivit'", "TheDivineOnes"},
		  {"Figure out this anagram: 'eviietesnhond'", "TheDivineOnes"},
		  {"Figure out this anagram: 'iehtsevnodeni'", "TheDivineOnes"},
		  {"Figure out this anagram: 'tshdnoeeneivi'", "TheDivineOnes"},
		  
		  {"Figure out this anagram: 'agcim apec'", "Magic Cape"},
		  {"Figure out this anagram: 'micga ecpa'", "Magic Cape"},
		  {"Figure out this anagram: 'ciagm cpea'", "Magic Cape"},
		  {"Figure out this anagram: 'imagc caep'", "Magic Cape"},
		  {"Figure out this anagram: 'magci peac'", "Magic Cape"},
		  
          };
    
    private static String trueorfalse [][] = { 
            {"Actual RSMVs were made before Youtube began.", "False"},
            {"Runescape once hosted an RSMV Contest.", "True"},
            {"Making an RSMV helps you understand lyrics better.", "True"},
            {"RSMVs are works of art.", "True"},
            {"Syncing requires top-notch hand-eye coordination skills.", "True"},
            {"RSMVs were uploaded most in 2012.", "False"},
            {"Sony Vegas is the most used program for RSMVing.", "True"},
            {"The most popular location is Clan Wars.", "True"},
            {"Runescape is the best MMO to use to make music videos.", "True"},
            {"Air guitar can count as overused.", "False"},
            {"Hypercam is the most popular screen-recorder.", "False"},
            {"RSMV fights originated in Vexation.", "True"},
            {"Legion of Doom is a post-hardcore group.", "False"},
            {"Legion of Doom is a mash-up group.", "True"},
            {"Dance emote is mainly used to initiate a sync?", "True"},
      	 };
    
    private static String riddles [][] = { 
            {"He lingers in every sync in almost every RSMV.", "Kevin"},
  		  	{"These brothers feed your addiction.", "ThreeAddictedBrothers"},
  		  	{"The book everyone loves.", "Prayer Book"},
  		  	{"This pet knows how to rock, but not roll.", "Pet rock"},
  		  	{"When twins and PKing come together.", "boomertwins"},
  		  	{"If the green liquid is to drip, we are to go to ectofuntus.", "ectophial"},
  		  	{"This item IS a camera.", "Orb of Oculus"},
  		  	{"Spoon is to Kitchen as TV Simulator is to _ _ _ _  _ _ _ _ _.", "Sony vegas"},
  		  	{"Dance is to Choreography as Emote is to _ _ _ _.", "Sync"},
  		  	{"The book everyone loves.", "Prayer Book"},
      	  };
      
    
    private static String server [][] = { 
          {"Fill in the blanks: Our Head-Admin is _ _ _ _.", "Cole" },
          {"Who was the owner of Vextion?", "Tony"},
          {"Who is the owner of Vengium?", "Multak"},
          {"Who is Johnnyguttz", "God"},
          {"Fill in the blanks: Our JoneMarrow-Admin is _ o _.", "Jon"},
          {"Fill in the blanks: Our Supervillain-Admin is M _ _ _ _ t .", "Misfit"},
          {"Fill in the blanks: Our Sandwich-Admin is _ _ e y.", "Trey"},
          {"Fill in the blanks: Our Halo_Admin is _ n _ e _.", "Angel"},
          {"Fill in the blanks: Our Djent-Mod is _ a _ _.", "Lane"},
          {"Fill in the blanks: Our Gainz-Mod is _ u _ t _ _.", "Hunter"},
          {"Fill in the blanks: Our Hmmm-Admin is _ a l _ _.", "Wally"},
          {"Which guild is the Vengium home?", "Fishing"},
		  {"Which item is used to find all command?", "Guide Book"},
          };
    
    private static String general [][] = { 
          {"Which RS world was known as the 'RSMVer World?'", "27"},
          {"What is the acronymn for a Runescape Music Video?", "RSMV"},
		  {"What is the acronymn for a Multi Editor Project?", "MEP"},
          {"What is the most overused emote-item in RSMV history?", "Prayer book"},
          {"True or false: Dance emote is mainly used to initiate a sync?", "True"},
		  {"Fill in the blanks: The first ever RSMV was made by _ _ _ _ _ _ _ _ _.", "Xgen20o4"},
		  {"Fill in the blanks: Luckybucket's motto was 'Making RSMV _ _ _ _ _ _ _", "History"},
    	  };
    
    private static String guess [][] = { 
        {"Never misses a beat, often uses film effect, and edited 'I Made It'.", "toshero3"},
        {"Created color flashes, awesome flow, creative to the fullest.", "matixpuls3"},
        {"Little to no effects cause his old videos had tons of effects, often uses effects for transitions, and uses chroma key and mask generator often.", "girochen"},
        {"Weird, but sick editing style, uses cheat engine still, can't edit without weed", "alchemy510"},
        {"Very old RSMVer known to use sepia, inspired matixpuls3 and many others, and started the facemask trend.", "luckybucket"},
        {"Created the legendary 'Lolita's Medicine' RSMV, awesome coloring, had many popular RSMVs back in the days.", "butpain"},
        {"Has one of the most popular RSMVs of all time, started the whirlpool velocity trend, and had the most popular PKMVs.", "boomertwins"},
        {"Legendary, impeccable flow, miles ahead of competitoin, the God", "johnnyguttz"},
        {"The unknown semen, the fatherless sperm, Mr.Ididntkeyloganyone!", "Hunter"},
        };

     
    private static String categories [][][] = { songs, anagrams, trueorfalse, riddles, server, general, guess };
    private static int catId;
    public static int questionid = -1;
    public static int round = 0;
    public static int position = 0;
    public static boolean victory = false;
    public static int answers = 0;
 
    public TriviaBot() {
        //TODO
    }
     
    public static int getPlace() {
		return answers++;
     }
    
    
    public static void Run() {
        catId = Misc.random(0, 5);
        int rand = RandomQuestion(catId);
        questionid = rand;
        answers = 0;
        position = 0;
        for(Player p3 : World.getPlayers()) {
        if (!p3.hasAnswered){
        p3.streak = 0;	
        p3.endedstreak = true;
        p3.startedstreak = false;        
        }
        }
        victory = false;
        String title = "Trivia";
        if (catId == 0)
            title = "Name the Song";
        else if (catId == 1)
            title = "Anagrams";
        else if (catId == 2)
            title = "True or False";
        else if (catId == 3)
            title = "Riddles";
        else if (catId == 4)
            title = "RuneScape/Server";
        else if (catId == 5)
            title = "General Trivia";
        else if (catId == 6)
            title = "Guess the RSMVer";
        for(Player participant : World.getPlayers()) {
            if(participant == null)
                continue;
                participant.hasAnswered = false;
                participant.getPackets().sendGameMessage("<col=56A5EC>["+title+"] "+categories[catId][rand][0]+"</col>");
        }
    }
     
   
    public static void sendRoundWinner(String winner, Player player) {
    	
	    if ((player.startedstreak == false || player.endedstreak == true)) {
	        
	        if (answers == 0)
	        	position = 1;
	        if (answers == 1)
	        	position = 2;
	        if (answers == 2)
	        	position = 3;
	        if (answers == 3)
	        	position = 4;
    	    if (answers == 4)
    	    	position = 5;
    	    if (position == 1) {
	    	    if (answers < 5)
		        	answers++;
			    if (answers == 5)
			        victory = true;
			    	position = 0;
			    player.RSMVerPoints++;
			    player.getPackets().sendGameMessage("<shad=56A5EC><col=56A5EC>[Trivia] "+winner+", you now have "+ player.RSMVerPoints +" RSMVer Points.</col>");
			    player.hasAnswered = true;
			    player.startedstreak = true;
			    player.endedstreak = false;
			    player.streak = 1;
			    World.sendWorldMessage("<shad=56A5EC><col=56A5EC>[Winner] <shad=FF0000><col=FF0000>"+ winner +"</col></shad><shad=56A5EC><col=56A5EC> has set on a streak..! ("+answers+"/5)!</col></shad>", false);
    	    return; 
    	    }
    	    
    	    if (position > 1) {
	    	    if (answers < 5)
		        	answers++;
			    if (answers == 5)
			        victory = true;
			    	position = 0;
			    player.RSMVerPoints++;
			    player.getPackets().sendGameMessage("<col=56A5EC>[Trivia] "+winner+", you now have "+ player.RSMVerPoints +" RSMVer Points.</col>");
			    player.hasAnswered = true;
			    player.endedstreak = true;
			    player.startedstreak = false;
			    player.streak = 0;
			    World.sendWorldMessage("<col=56A5EC>[Winner] <col=FF0000>"+ winner +"</col><col=56A5EC> answered the question correctly ("+answers+"/5)!</col>", false);
    	    return;
    	    } 
    	}
	    else if ((player.startedstreak == true || player.endedstreak == false)) {
	    	 if (answers == 0)
		        	position = 1;
		        if (answers == 1)
		        	position = 2;
		        if (answers == 2)
		        	position = 3;
		        if (answers == 3)
		        	position = 4;
	    	    if (answers == 4)
	    	    	position = 5;
    	    if (position > 1 && player.streak == 1) {
	    	    if (answers < 5)
		        	answers++;
			    if (answers == 5)
			        victory = true;
			    	position = 0;
		    	player.RSMVerPoints++;
				player.getPackets().sendGameMessage("<shad=56A5EC><col=56A5EC>[Trivia] "+winner+", you have lost your streak. You now have "+ player.RSMVerPoints +" RSMVer Points. </col>");
				player.hasAnswered = true;
				player.streak = 0;
				player.endedstreak = true;
			    player.startedstreak = false;
				World.sendWorldMessage("<shad=56A5EC><col=56A5EC>[Winner] <shad=FF0000><col=FF0000>"+ winner +"</col></shad><shad=56A5EC><col=56A5EC> was defeated in his streak..! ("+answers+"/5)!</col></shad>", false);
		    return;
		    } 
    	    if (position > 1 && player.streak == 1) {
	    	    if (answers < 5)
		        	answers++;
			    if (answers == 5)
			        victory = true;
			    	position = 0;
		    	player.RSMVerPoints++;
				player.getPackets().sendGameMessage("<shad=56A5EC><col=56A5EC>[Trivia] "+winner+", you have lost your streak. You now have "+ player.RSMVerPoints +" RSMVer Points. </col>");
				player.hasAnswered = true;
				player.streak = 0;
				player.endedstreak = true;
			    player.startedstreak = false;
				World.sendWorldMessage("<shad=56A5EC><col=56A5EC>[Winner] <shad=FF0000><col=FF0000>"+ winner +"</col></shad><shad=56A5EC><col=56A5EC> was defeated on his streak..! ("+answers+"/5)!</col></shad>", false);
		    return;
		    } 
			if (position == 1 && player.streak == 1) {
	    	    if (answers < 5)
		        	answers++;
			    if (answers == 5)
			        victory = true;
			    	position = 0;
		    	player.RSMVerPoints++;
				player.RSMVerPoints++;
				player.getPackets().sendGameMessage("<shad=56A5EC><col=56A5EC>[Trivia] "+winner+", your are on a streak! You now have "+ player.RSMVerPoints +" RSMVer Points. </col>");
				player.hasAnswered = true;
				player.streak = 2;
				World.sendWorldMessage("<shad=56A5EC><col=56A5EC>[Winner] <shad=FF0000><col=FF0000>"+ winner +"</col></shad><shad=56A5EC><col=56A5EC> has answered 2 in a row..! ("+answers+"/5)!</col></shad>", false);
		    return;
		    } 
			if (position > 1 && player.streak == 2) {
	    	    if (answers < 5)
		        	answers++;
			    if (answers == 5)
			        victory = true;
			    	position = 0;
		    	player.RSMVerPoints++;
				player.getPackets().sendGameMessage("<shad=56A5EC><col=56A5EC>[Trivia] "+winner+", you have lost your streak. You now have "+ player.RSMVerPoints +" RSMVer Points. </col>");
				player.hasAnswered = true;
				player.streak = 0;
				player.endedstreak = true;
			    player.startedstreak = false;
				World.sendWorldMessage("<shad=56A5EC><col=56A5EC>[Winner] <shad=FF0000><col=FF0000>"+ winner +"</col></shad><shad=56A5EC><col=56A5EC> was defeated on his streak..! ("+answers+"/5)!</col></shad>", false);
		    return;
		    } 
			if (position == 1 && player.streak == 2) {
	    	    if (answers < 5)
		        	answers++;
			    if (answers == 5)
			        victory = true;
			    	position = 0;
			     player.RSMVerPoints++;
				 player.RSMVerPoints++;
				 player.RSMVerPoints++;
				 player.getPackets().sendGameMessage("<shad=56A5EC><col=56A5EC>[Trivia] "+winner+", your are on a streak! You now have "+ player.RSMVerPoints +" RSMVer Points.</col>");
				 player.hasAnswered = true;
				 player.streak = 3;
				 World.sendWorldMessage("<shad=56A5EC><col=56A5EC>[Winner] <shad=FF0000><col=FF0000>"+ winner +"</col></shad><shad=56A5EC><col=56A5EC> has answered 3 in a row..! ("+answers+"/5)!</col></shad>", false);
		    return;
		    }if (position > 1 && player.streak == 3) {
	    	    if (answers < 5)
		        	answers++;
			    if (answers == 5)
			        victory = true;
			    	position = 0;
		    	player.RSMVerPoints++;
				player.getPackets().sendGameMessage("<shad=56A5EC><col=56A5EC>[Trivia] "+winner+", you have lost your streak. You now have "+ player.RSMVerPoints +" RSMVer Points. </col>");
				player.hasAnswered = true;
				player.streak = 0;
				player.endedstreak = true;
			    player.startedstreak = false;
				World.sendWorldMessage("<shad=56A5EC><col=56A5EC>[Winner] <shad=FF0000><col=FF0000>"+ winner +"</col></shad><shad=56A5EC><col=56A5EC> was defeated on his streak..! ("+answers+"/5)!</col></shad>", false);
		    return;
		    } 
			if (position == 1 && player.streak == 3) {
	    	    if (answers < 5)
		        	answers++;
			    if (answers == 5)
			        victory = true;
			    	position = 0;
			   player.RSMVerPoints++;
			   player.RSMVerPoints++;
			   player.RSMVerPoints++;
			   player.RSMVerPoints++;
			   player.getPackets().sendGameMessage("<shad=56A5EC><col=56A5EC>[Trivia] "+winner+", you now have "+ player.RSMVerPoints +" RSMVer Points and your streak has been replenished. Congradulations, you have recieved an RSMV Token...</col>");
			   player.hasAnswered = true;
			   player.endedstreak = true;
		       player.startedstreak = false;
		       player.streak = 0;
		       player.getInventory().addItem(29980, 1);
		       World.sendWorldMessage("<shad=56A5EC><col=56A5EC>[Winner] <shad=FF0000><col=FF0000>"+ winner +"</col></shad><shad=56A5EC><col=56A5EC> is god-like..! ("+answers+"/5)!</col></shad>", false);
		    return;
		    } 
		  }
    	}
    
    public static void verifyAnswer(final Player player, String answer) {
        if(victory && (!player.hasAnswered) && (player.streak >= 1 || player.streak <= 3)) {
        	player.endedstreak = true;
            player.getPackets().sendGameMessage("<img=15><col=ff0000>That round has already been completed. Your streak has ended. Please wait for the next round.");
        } else if(victory && (player.hasAnswered && (getPlace() == 1)) && (player.streak >= 1 || player.streak <= 3)) {
        	player.endedstreak = true;
            player.getPackets().sendGameMessage("<img=15><col=ff0000>That round has already been completed. Your are on a streak. Please wait for the next round.");
        } else if(victory) {
            player.getPackets().sendGameMessage("<img=15><col=ff0000>The round has ended. Please wait for the next round.");
        } else if (player.hasAnswered && (player.streak >= 1 || player.streak <= 3)) {
            player.getPackets().sendGameMessage("<img=15><col=ff0000>You have already answered this question. You are on a streak.");
        } else if (player.hasAnswered) {
            player.getPackets().sendGameMessage("<img=15><col=ff0000>You have already answered this question.");
        } else if(categories[catId][questionid][1].equalsIgnoreCase(answer)) {
            round++;
            sendRoundWinner(player.getDisplayName(), player);    
        } else {
        	if ((player.streak >= 1 && player.streak <= 3)) {
	        	player.endedstreak = true;
	        	player.startedstreak = false;
	            player.getPackets().sendGameMessage("<img=15><col=ff0000>That answer wasn't correct and your steak has ended. Try again.");
        	 }
	         if ((player.streak == 0)) {
		    	player.endedstreak = true;
		    	player.startedstreak = false;
		        player.getPackets().sendGameMessage("<img=15><col=ff0000>That answer wasn't correct. Try again.");
	         }
        }
    }
     
    public static int RandomQuestion(int i) {
        int random = 0;
        Random rand = new Random();
        random = rand.nextInt(categories[i].length);
        return random;
    }
     
    public static boolean TriviaArea(final Player participant) {
        if(participant.getX() >= 2630 && participant.getX() <= 2660 && participant.getY() >= 9377 && participant.getY() <= 9400) {
            return true;
        }
        return false;
    }
}