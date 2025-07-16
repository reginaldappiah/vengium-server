package com.rs.game.player.content;

import java.util.Arrays;

import com.rs.cache.loaders.ItemDefinitions;
import com.rs.game.World;
import com.rs.game.WorldObject;
//import com.rs.cache.loaders.ObjectDefinitions;
import com.rs.game.player.Player;


public class ClanCitadelTheatre {
	
	public static int PROP_MACHINE_INTERFACE = 797;
	public static int LIGHTING_THEME_INTERFACE = 388;
	public static int CLOSING_CURATIN_LEFT = 62003;
	public static int CLOSING_CURTAIN_RIGHT = 62004;
	private boolean accepted;
	
	public void quit() {
		boolean canceled = false;
		if(accepted) {
			accepted = false;
			canceled = true;
	}}
	public void accept() {
				accepted = true;
	}
	public static void ModifyBackdrop(final Player player) {
		player.getInterfaceManager().sendInterface(PROP_MACHINE_INTERFACE);
	}
	
	public static void handleModifyMachine(Player player, int buttonId) {
	if (buttonId == 7) { // Accept
		player.closeInterfaces();
	} else if (buttonId == 22) { // Greenscreen 
		World.spawnObject(new WorldObject(62121, 10, 1, 4869, 4026, 0), true);//Greenscreen
		World.spawnObject(new WorldObject(62122, 10, 1, 4869, 4024, 0), true);//Greenscreen side leg left
		World.spawnObject(new WorldObject(62123, 10, 1, 4880, 4024, 0), true);//Greenscreen side leg rigt
	} else if (buttonId == 35) { // Cave
		 World.spawnObject(new WorldObject(62016, 10, 1, 4869, 4026, 0), true);//Cave
			World.spawnObject(new WorldObject(62021, 10, 1, 4869, 4024, 0), true);//Cave side leg left
			World.spawnObject(new WorldObject(62026, 10, 1, 4880, 4024, 0), true);//Cave side leg rigt
		 //World.spawnObject(new WorldObject(62017, 10, 1, 4869, 4026, 0), true);//Cave (green lighting)
		 //World.spawnObject(new WorldObject(62018, 10, 1, 4869, 4026, 0), true);//Cave (night lighting)
		 //World.spawnObject(new WorldObject(62019, 10, 1, 4869, 4026, 0), true);//Cave (dusk lighting)
		 //World.spawnObject(new WorldObject(62020, 10, 1, 4869, 4026, 0), true);//Cave (dawn lighting)
	} else if (buttonId == 49) { // Crypt
		World.spawnObject(new WorldObject(62031, 10, 1, 4869, 4026, 0), true); //Crypt 
		World.spawnObject(new WorldObject(62036, 10, 1, 4869, 4024, 0), true);//Jungle side leg left
		World.spawnObject(new WorldObject(62041, 10, 1, 4880, 4024, 0), true);//Jungle side leg rigt
		//World.spawnObject(new WorldObject(62032, 10, 1, 4869, 4026, 0), true);//Crypt (green lighting)
		 //World.spawnObject(new WorldObject(62033, 10, 1, 4869, 4026, 0), true);//Crypt (night lighting)
		 //World.spawnObject(new WorldObject(62034, 10, 1, 4869, 4026, 0), true);//Crypt (dusk lighting)
		 //World.spawnObject(new WorldObject(62035, 10, 1, 4869, 4026, 0), true);//Crypt (dawn lighting)
	} else if (buttonId == 63) { // Forest
		World.spawnObject(new WorldObject(62061, 10, 1, 4869, 4026, 0), true);//Jungle
		World.spawnObject(new WorldObject(62066, 10, 1, 4869, 4024, 0), true);//Jungle side leg left
		World.spawnObject(new WorldObject(62071, 10, 1, 4880, 4024, 0), true);//Jungle side leg rigt
		//World.spawnObject(new WorldObject(62062, 10, 1, 4869, 4026, 0), true);//Jungle (green lighting)
		 //World.spawnObject(new WorldObject(62063, 10, 1, 4869, 4026, 0), true);//Jungle (night lighting)
		 //World.spawnObject(new WorldObject(62064, 10, 1, 4869, 4026, 0), true);//Jungle (dusk lighting)
		 //World.spawnObject(new WorldObject(62065, 10, 1, 4869, 4026, 0), true);//Jungle (dawn lighting)
	} else if (buttonId == 77) { // Ocean
		World.spawnObject(new WorldObject(62076, 10, 1, 4869, 4026, 0), true);//Ocean
		World.spawnObject(new WorldObject(62081, 10, 1, 4869, 4024, 0), true);//Ocean side leg left
		World.spawnObject(new WorldObject(62086, 10, 1, 4880, 4024, 0), true);//Ocean side leg rigt
		//World.spawnObject(new WorldObject(62077, 10, 1, 4869, 4026, 0), true);//Ocean (green lighting)
		 //World.spawnObject(new WorldObject(62078, 10, 1, 4869, 4026, 0), true);//Ocean (night lighting)
		 //World.spawnObject(new WorldObject(62079, 10, 1, 4869, 4026, 0), true);//Ocean (dusk lighting)
		 //World.spawnObject(new WorldObject(62080, 10, 1, 4869, 4026, 0), true);//Ocean (dawn lighting)
	} else if (buttonId == 91) { // Hills
		World.spawnObject(new WorldObject(62106, 10, 1, 4869, 4026, 0), true);//Hills
		World.spawnObject(new WorldObject(62111, 10, 1, 4869, 4024, 0), true);//Jungle side leg left
		World.spawnObject(new WorldObject(62116, 10, 1, 4880, 4024, 0), true);//Jungle side leg rigt
		//World.spawnObject(new WorldObject(62107, 10, 1, 4869, 4026, 0), true);//Hills (green lighting)
		 //World.spawnObject(new WorldObject(62108, 10, 1, 4869, 4026, 0), true);//Hills (night lighting)
		 //World.spawnObject(new WorldObject(62109, 10, 1, 4869, 4026, 0), true);//Hills (dusk lighting)
		 //World.spawnObject(new WorldObject(62110, 10, 1, 4869, 4026, 0), true);//Hills (dawn lighting)
	} else if (buttonId == 105) { // Interior
		World.spawnObject(new WorldObject(62091, 10, 1, 4869, 4026, 0), true);//Interior
		World.spawnObject(new WorldObject(62096, 10, 1, 4869, 4024, 0), true);//Interior side leg left
		World.spawnObject(new WorldObject(62101, 10, 1, 4880, 4024, 0), true);//Interior side leg rigt
		//World.spawnObject(new WorldObject(62092, 10, 1, 4869, 4026, 0), true);//Interior (green lighting)
		 //World.spawnObject(new WorldObject(62093, 10, 1, 4869, 4026, 0), true);//Interior (night lighting)
		 //World.spawnObject(new WorldObject(62094, 10, 1, 4869, 4026, 0), true);//Interior (dusk lighting)
		 //World.spawnObject(new WorldObject(62095, 10, 1, 4869, 4026, 0), true);//Interior (dawn lighting)
	} else if (buttonId == 119) { // Village
		World.spawnObject(new WorldObject(62046, 10, 1, 4869, 4026, 0), true);//Village
		World.spawnObject(new WorldObject(62051, 10, 1, 4869, 4024, 0), true);//Village side leg left
		World.spawnObject(new WorldObject(62057, 10, 1, 4880, 4024, 0), true);//Village side leg rigt
		//World.spawnObject(new WorldObject(62047, 10, 1, 4869, 4026, 0), true);//Village (green lighting)
		 //World.spawnObject(new WorldObject(62048, 10, 1, 4869, 4026, 0), true);//Village (night lighting)
		 //World.spawnObject(new WorldObject(62049, 10, 1, 4869, 4026, 0), true);//Village (dusk lighting)
		 //World.spawnObject(new WorldObject(62050, 10, 1, 4869, 4026, 0), true);//Village (dawn lighting)
	} else if (buttonId == 137) { // Quit
		player.closeInterfaces();
	}
}
	public static void OpenCurtain(final Player player) {
		World.spawnObject(new WorldObject(62005, 10, 0, 4869, 4021, 0), true);//Open
		World.spawnObject(new WorldObject(62006, 10, 0, 4875, 4021, 0), true);//Open
		
	}
	public static void CloseCurtain(final Player player) {
		World.spawnObject(new WorldObject(62003, 10, 0, 4869, 4021, 0), true);//Open
		World.spawnObject(new WorldObject(62004, 10, 0, 4875, 4021, 0), true);//Open
	}
}

