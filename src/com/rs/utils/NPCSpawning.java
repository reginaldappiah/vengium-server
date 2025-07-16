/*@author Dikkekont 
 */
package com.rs.utils;


import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import com.rs.game.World;
import com.rs.game.WorldObject;
import com.rs.game.WorldTile;
import com.rs.game.npc.NPC;
import com.rs.utils.Utils.EntityDirection;


public class NPCSpawning {

	/**
	 * Contains the custom npc spawning
	 */
	
	public static void spawnNPCS() {
		/*
		 * NEW NPC SPAWNS
		 */
		//Nulls
		World.removeObject(new WorldObject(158, 10, 0, 3080, 9785, 0), true);//Coffin
		/*World.removeObject(new WorldObject(49046, 10, 0, 2584, 3412, 0), true);//Barrel
		World.removeObject(new WorldObject(49047, 10, 0, 2589, 3417, 0), true);//Barrel
		World.removeObject(new WorldObject(49048, 10, 0, 2584, 3411, 0), true);//Barrel
		World.removeObject(new WorldObject(49048, 10, 0, 2590, 3417, 0), true);//Barrel*/
		//World.spawnObject(new WorldObject(-1, 10, 1, 2590, 3406, 0), true);
		/*World.spawnObject(new WorldObject(73660, 10, 0, 2607, 3407, 3), true);//Rain
		World.spawnObject(new WorldObject(73660, 10, 0, 2607, 3417, 3), true);//Rain
		World.spawnObject(new WorldObject(73660, 10, 0, 2607, 3427, 3), true);//Rain
		World.spawnObject(new WorldObject(73660, 10, 0, 2607, 3437, 3), true);//Rain
		World.spawnObject(new WorldObject(73660, 10, 0, 2595, 3429, 3), true);//Rain
		World.spawnObject(new WorldObject(73660, 10, 0, 2595, 3439, 3), true);//Rain*/
		World.spawnObject(new WorldObject(66556, 10, 0, 2591, 3410, 0), true);//Thieving Icon
		World.spawnObject(new WorldObject(162, 10, 0, 3080, 9785, 0), true);//Melee Icon
		World.spawnObject(new WorldObject(6551, 10, 0, 3091, 3106, 0), true);//Starter Portal
		//Combat Tutors
		World.spawnNPC(7950, new WorldTile(2592, 3402, 0), 0, false, EntityDirection.NORTHEAST);//Melee Tutor
		World.spawnObject(new WorldObject(66539, 10, 0, 2592, 3402, 0), true);//Melee Icon
		World.spawnNPC(1861, new WorldTile(2592, 3403, 0), 0, false, EntityDirection.NORTHEAST);//Ranged Instructor
		//World.spawnObject(new WorldObject(66539, 10, 0, 2592, 3402, 0), true);//Ranged Icon
		World.spawnNPC(4707, new WorldTile(2592, 3404, 0), 0, false, EntityDirection.NORTHEAST);//Magic Instructor
		//World.spawnObject(new WorldObject(66539, 10, 0, 2592, 3402, 0), true);//Magic Icon
		World.spawnNPC(9084, new WorldTile(2588, 3411, 0), 0, false, EntityDirection.NORTH);//Kurdal Slayer Tasker
		World.spawnNPC(14892, new WorldTile(2587, 3411, 0), 0, false, EntityDirection.NORTH);//Summoning Man
		//Skilling Tutors
		World.spawnNPC(4906, new WorldTile(2599, 3406, 0), 0, false, EntityDirection.NORTH);//Woodcutting Tutor
		World.spawnObject(new WorldObject(66557, 10, 0, 2599, 3406, 0), true);//Woodcutting Icon
		World.spawnNPC(4901, new WorldTile(2598, 3421, 0), 0, false, EntityDirection.SOUTH);//Fishing Tutor
		World.spawnObject(new WorldObject(66546, 10, 0, 2598, 3421, 0), true);//Fishing Icon
		World.spawnNPC(4900, new WorldTile(2600, 3406, 0), 0, false, EntityDirection.NORTH);//Crafting Tutor
		World.spawnObject(new WorldObject(66542, 10, 0, 2600, 3406, 0), true);//Crafting Icon
		World.spawnNPC(4902, new WorldTile(2601, 3406, 0), 0, false, EntityDirection.NORTH);//Mining Tutor
		World.spawnObject(new WorldObject(66550, 10, 0, 2601, 3406, 0), true);//Mining Icon
		World.spawnNPC(4905, new WorldTile(2602, 3406, 0), 0, false, EntityDirection.NORTH);//Smithing Tutor
		World.spawnObject(new WorldObject(66543, 10, 0, 2604, 3421, 0), true);//Dungeoneering Icon
		World.spawnObject(new WorldObject(48579, 10, 0, 2606, 3419, 0), true);//Fremennik ship
		World.spawnNPC(9708, new WorldTile(2604, 3421, 0), 0, false, EntityDirection.SOUTHWEST);//Dungeoneering Man
		World.spawnObject(new WorldObject(66554, 10, 0, 2602, 3406, 0), true);//Smithing Icon
		World.spawnObject(new WorldObject(66545, 10, 0, 2594, 3419, 0), true);//Firemaking Icon
		World.spawnObject(new WorldObject(13197, 10, 0, 2583, 3419, 0), true);//Altar
		World.spawnObject(new WorldObject(38165, 10, 1, 2595, 3418, 0), true);//Campfire
		World.spawnObject(new WorldObject(4874, 10, 0, 2592, 3412, 0), true);//Thieving Stall
		World.spawnObject(new WorldObject(4875, 10, 0, 2592, 3411, 0), true);//Thieving Stall
		World.spawnObject(new WorldObject(4876, 10, 0, 2592, 3410, 0), true);//Thieving Stall
		World.spawnObject(new WorldObject(4877, 10, 0, 2592, 3409, 0), true);//Thieving Stall
		World.spawnObject(new WorldObject(4878, 10, 0, 2592, 3408, 0), true);//Thieving Stall
		World.spawnObject(new WorldObject(38698, 10, 1, 2580, 3405, 0), true);//Thieving Stall
		World.spawnObject(new WorldObject(38699, 10, 1, 2580, 3409, 0), true);//Thieving Stall
		//Woodcutting Skilling Area
		World.spawnObject(new WorldObject(2133, 10, 1, 3179, 3235, 0), true);//Bank Deposit for Lumbridge
		World.spawnObject(new WorldObject(2133, 10, 1, 2817, 3086, 0), true);//Bank Deposit for Karamja
		World.spawnObject(new WorldObject(2133, 10, 3, 1297, 4580, 0), true);//Bank Deposit for Underground
		World.spawnObject(new WorldObject(2133, 10, 3, 2698, 3397, 0), true);//Bank Deposit for Tower of Life
		World.spawnObject(new WorldObject(2133, 10, 2, 2325, 3848, 0), true);//Bank Deposit for Arctic Pine
		
		//ADDED IN 5/22/2014
		World.removeObject(new WorldObject(2563, 10, 0, 2333, 3691, 0), true);//Mysterious Cape
		//Musicians
		World.spawnNPC(8723, new WorldTile(2255, 3190, 0), 0, false, EntityDirection.SOUTH);//Elf Musician
		World.spawnNPC(8703, new WorldTile(2605, 3385, 0), 0, false, EntityDirection.SOUTH);//Musician
		World.spawnNPC(8707, new WorldTile(2967, 3411, 0), 0, false, EntityDirection.SOUTH);//Musician
		//World.spawnNPC(8707, new WorldTile(2967, 3411, 0), 0, false, EntityDirection.SOUTH);//PETE
		//Random
		//World.spawnObject(new WorldObject(9295, 10, 3, 2594, 3417, 0), true);//Spirit Tree
		//World.spawnObject(new WorldObject(1317, 10, 4, 2591, 3425, 0), true);//Spirit Tree
		World.spawnObject(new WorldObject(15482, 10, 1, 2580, 3413, 0), true);//POH Portal
		World.spawnNPC(6537, new WorldTile(2996, 9676, 0), 0, false, EntityDirection.NORTHWEST);//Mandrith
		World.spawnNPC(6537, new WorldTile(2586, 3417, 0), 0, false, EntityDirection.SOUTH);//Mandrith
		World.spawnNPC(659, new WorldTile(2590, 3416, 0), 0, false, EntityDirection.SOUTHEAST);//RSMV Prestige
		World.spawnNPC(3809, new WorldTile(2594, 3418, 0), 0, false, EntityDirection.SOUTH);//Gnome Glider
		//World.removeObject(new WorldObject(2563, 10, 0, 2333, 3691, 0), true);//Mysterious Cape
		//World.spawnObject(new WorldObject(1293, 10, 2, 2591, 3425, 0), true);//Spirit Tree
		
		//Isdafar Butterflies
		World.spawnNPC(155, new WorldTile(2245, 3183, 0), -1, true, true);//Butterfly
		World.spawnNPC(155, new WorldTile(2245, 3183, 0), -1, true, true);//Butterfly
		World.spawnNPC(155, new WorldTile(2245, 3183, 0), -1, true, true);//Butterfly
		World.spawnNPC(155, new WorldTile(2245, 3183, 0), -1, true, true);//Butterfly
		World.spawnNPC(155, new WorldTile(2245, 3183, 0), -1, true, true);//Butterfly
		World.spawnNPC(155, new WorldTile(2245, 3183, 0), -1, true, true);//Butterfly
		World.spawnNPC(155, new WorldTile(2245, 3183, 0), -1, true, true);//Butterfly
		World.spawnNPC(155, new WorldTile(2245, 3183, 0), -1, true, true);//Butterfly
		World.spawnNPC(155, new WorldTile(2243, 3184, 0), -1, true, true);//Butterfly
		World.spawnNPC(155, new WorldTile(2243, 3184, 0), -1, true, true);//Butterfly
		World.spawnNPC(155, new WorldTile(2243, 3184, 0), -1, true, true);//Butterfly
		World.spawnNPC(155, new WorldTile(2243, 3184, 0), -1, true, true);//Butterfly
		World.spawnNPC(155, new WorldTile(2247, 3182, 0), -1, true, true);//Butterfly
		World.spawnNPC(155, new WorldTile(2247, 3182, 0), -1, true, true);//Butterfly
		//Castlewars portal fix
                World.spawnObject(new WorldObject(2465, 10, -2, 2439, 3096, 0), true);//Saradmon Portal Replacement
                World.spawnObject(new WorldObject(2469, 10, -2, 2375, 9487, 0), true);//Saradmon Portal Replacement

		//Home Anvil
                World.spawnObject(new WorldObject(12692, 10, -2, 2841, 10211, 0), true);//Anvil

		//Varrock Museum Above Ground
                World.spawnObject(new WorldObject(24360, 10, -1, 3255, 3452, 0), true);//Staircase

		//Barrows Ladder Fix With Ropes For Now
                World.spawnObject(new WorldObject(9662, 10, -2, 2849, 10213, 0), true);//Barrows Spade To Take
                World.spawnObject(new WorldObject(3829, 10, -2, 3551, 9703, 3), true);//Barrows Rope
                World.spawnObject(new WorldObject(3829, 10, -2, 3578, 9708, 3), true);//Barrows Rope
                World.spawnObject(new WorldObject(3829, 10, -2, 3565, 9684, 3), true);//Barrows Rope
                World.spawnObject(new WorldObject(3829, 10, -2, 3546, 9682, 3), true);//Barrows Rope
                World.spawnObject(new WorldObject(3829, 10, -2, 3534, 9701, 3), true);//Barrows Rope
                World.spawnObject(new WorldObject(3829, 10, -2, 3553, 9718, 3), true);//Barrows Rope

		//Godwars Ropes Fix
                World.spawnObject(new WorldObject(26293, 10, -1, 2872, 5280, 0), true);//Godwars Rope
                World.spawnObject(new WorldObject(26293, 10, -1, 2872, 5272, 0), true);//Godwars Rope

		//Slayer Tower Spawns
		World.spawnNPC(1648, new WorldTile(3411, 3537, 0), -1, true, true);//Crawling Hand
		World.spawnNPC(1648, new WorldTile(3408, 3537, 0), -1, true, true);//Crawling Hand
		World.spawnNPC(1648, new WorldTile(3413, 3536, 0), -1, true, true);//Crawling Hand
		World.spawnNPC(1648, new WorldTile(3410, 3540, 0), -1, true, true);//Crawling Hand
		World.spawnNPC(1648, new WorldTile(3411, 3549, 0), -1, true, true);//Crawling Hand
		World.spawnNPC(1648, new WorldTile(3413, 3546, 0), -1, true, true);//Crawling Hand
		World.spawnNPC(1648, new WorldTile(3415, 3572, 0), -1, true, true);//Crawling Hand
		World.spawnNPC(1648, new WorldTile(3411, 3573, 0), -1, true, true);//Crawling Hand
		World.spawnNPC(1648, new WorldTile(3416, 3572, 0), -1, true, true);//Crawling Hand
		World.spawnNPC(1648, new WorldTile(3413, 3571, 0), -1, true, true);//Crawling Hand
		World.spawnNPC(1648, new WorldTile(3414, 3558, 0), -1, true, true);//Crawling Hand
		World.spawnNPC(1648, new WorldTile(3411, 3561, 0), -1, true, true);//Crawling Hand
		World.spawnNPC(1648, new WorldTile(3411, 3556, 0), -1, true, true);//Crawling Hand
		World.spawnNPC(1648, new WorldTile(3417, 3556, 0), -1, true, true);//Crawling Hand
		World.spawnNPC(1648, new WorldTile(3420, 3558, 0), -1, true, true);//Crawling Hand
		World.spawnNPC(1648, new WorldTile(3434, 3572, 0), -1, true, true);//Crawling Hand
		World.spawnNPC(1648, new WorldTile(3437, 3574, 0), -1, true, true);//Crawling Hand
		World.spawnNPC(1648, new WorldTile(3432, 3574, 0), -1, true, true);//Crawling Hand
		World.spawnNPC(1648, new WorldTile(3432, 3570, 0), -1, true, true);//Crawling Hand
		World.spawnNPC(1648, new WorldTile(3438, 3570, 0), -1, true, true);//Crawling Hand
		World.spawnNPC(1612, new WorldTile(3439, 3562, 0), -1, true, true);//Banshee
		World.spawnNPC(1612, new WorldTile(3436, 3562, 0), -1, true, true);//Banshee
		World.spawnNPC(1612, new WorldTile(3441, 3559, 0), -1, true, true);//Banshee
		World.spawnNPC(1612, new WorldTile(3442, 3546, 0), -1, true, true);//Banshee
		World.spawnNPC(1612, new WorldTile(3446, 3549, 0), -1, true, true);//Banshee
		World.spawnNPC(1612, new WorldTile(3440, 3543, 0), -1, true, true);//Banshee
		World.spawnNPC(1612, new WorldTile(3446, 3536, 0), -1, true, true);//Banshee
		World.spawnNPC(1612, new WorldTile(3450, 3536, 0), -1, true, true);//Banshee
		World.spawnNPC(1612, new WorldTile(3447, 3539, 0), -1, true, true);//Banshee
		World.spawnNPC(1612, new WorldTile(3437, 3534, 0), -1, true, true);//Banshee
		World.spawnNPC(1612, new WorldTile(3445, 3537, 0), -1, true, true);//Banshee
		World.spawnNPC(1643, new WorldTile(3438, 3561, 1), -1, true, true);//Infernal Mage
		World.spawnNPC(1643, new WorldTile(3434, 3561, 1), -1, true, true);//Infernal Mage
		World.spawnNPC(1643, new WorldTile(3436, 3557, 1), -1, true, true);//Infernal Mage
		World.spawnNPC(1643, new WorldTile(3441, 3560, 1), -1, true, true);//Infernal Mage
		World.spawnNPC(1643, new WorldTile(3432, 3564, 1), -1, true, true);//Infernal Mage
		World.spawnNPC(1643, new WorldTile(3443, 3572, 1), -1, true, true);//Infernal Mage
		World.spawnNPC(1643, new WorldTile(3446, 3573, 1), -1, true, true);//Infernal Mage
		World.spawnNPC(1643, new WorldTile(3445, 3566, 1), -1, true, true);//Infernal Mage
		World.spawnNPC(1643, new WorldTile(3439, 3571, 1), -1, true, true);//Infernal Mage
		World.spawnNPC(1618, new WorldTile(3422, 3574, 1), -1, true, true);//Bloodveld
		World.spawnNPC(1618, new WorldTile(3419, 3573, 1), -1, true, true);//Bloodveld
		World.spawnNPC(1618, new WorldTile(3424, 3573, 1), -1, true, true);//Bloodveld
		World.spawnNPC(1618, new WorldTile(3410, 3574, 1), -1, true, true);//Bloodveld
		World.spawnNPC(1618, new WorldTile(3414, 3573, 1), -1, true, true);//Bloodveld
		World.spawnNPC(1618, new WorldTile(3410, 3570, 1), -1, true, true);//Bloodveld
		World.spawnNPC(1618, new WorldTile(3408, 3574, 1), -1, true, true);//Bloodveld
		World.spawnNPC(1618, new WorldTile(3421, 3562, 1), -1, true, true);//Bloodveld
		World.spawnNPC(1618, new WorldTile(3418, 3566, 1), -1, true, true);//Bloodveld
		World.spawnNPC(1618, new WorldTile(3423, 3566, 1), -1, true, true);//Bloodveld
		World.spawnNPC(1618, new WorldTile(3425, 3560, 1), -1, true, true);//Bloodveld
		World.spawnNPC(1618, new WorldTile(3418, 3560, 1), -1, true, true);//Bloodveld
		World.spawnNPC(1624, new WorldTile(3425, 3551, 1), -1, true, true);//Dustdevil
		World.spawnNPC(1624, new WorldTile(3422, 3553, 1), -1, true, true);//Dustdevil
		World.spawnNPC(1624, new WorldTile(3430, 3550, 1), -1, true, true);//Dustdevil
		World.spawnNPC(1624, new WorldTile(3434, 3550, 1), -1, true, true);//Dustdevil
		World.spawnNPC(1624, new WorldTile(3439, 3549, 1), -1, true, true);//Dustdevil
		World.spawnNPC(1624, new WorldTile(3437, 3545, 1), -1, true, true);//Dustdevil
		World.spawnNPC(1624, new WorldTile(3414, 3547, 1), -1, true, true);//Dustdevil
		World.spawnNPC(1624, new WorldTile(3412, 3550, 1), -1, true, true);//Dustdevil
		World.spawnNPC(1624, new WorldTile(3417, 3545, 1), -1, true, true);//Dustdevil
		World.spawnNPC(1624, new WorldTile(3423, 3540, 1), -1, true, true);//Dustdevil
		World.spawnNPC(1624, new WorldTile(3423, 3544, 1), -1, true, true);//Dustdevil
		World.spawnNPC(1624, new WorldTile(3426, 3541, 1), -1, true, true);//Dustdevil
		World.spawnNPC(1624, new WorldTile(3419, 3540, 1), -1, true, true);//Dustdevil
		World.spawnNPC(1624, new WorldTile(3418, 3536, 1), -1, true, true);//Dustdevil
		World.spawnNPC(1610, new WorldTile(3434, 3539, 2), -1, true, true);//Gargoyle
		World.spawnNPC(1610, new WorldTile(3440, 3539, 2), -1, true, true);//Gargoyle
		World.spawnNPC(1610, new WorldTile(3444, 3540, 2), -1, true, true);//Gargoyle
		World.spawnNPC(1610, new WorldTile(3446, 3535, 2), -1, true, true);//Gargoyle
		World.spawnNPC(1610, new WorldTile(3448, 3539, 2), -1, true, true);//Gargoyle
		World.spawnNPC(1610, new WorldTile(3442, 3548, 2), -1, true, true);//Gargoyle
		World.spawnNPC(1610, new WorldTile(3444, 3551, 2), -1, true, true);//Gargoyle
		World.spawnNPC(1610, new WorldTile(3445, 3547, 2), -1, true, true);//Gargoyle
		World.spawnNPC(1610, new WorldTile(3439, 3547, 2), -1, true, true);//Gargoyle
		World.spawnNPC(1613, new WorldTile(3438, 3563, 2), -1, true, true);//Nechryael
		World.spawnNPC(1613, new WorldTile(3442, 3561, 2), -1, true, true);//Nechryael
		World.spawnNPC(1613, new WorldTile(3437, 3558, 2), -1, true, true);//Nechryael
		World.spawnNPC(1613, new WorldTile(3446, 3562, 2), -1, true, true);//Nechryael
		World.spawnNPC(1613, new WorldTile(3444, 3568, 2), -1, true, true);//Nechryael
		World.spawnNPC(1613, new WorldTile(3439, 3570, 2), -1, true, true);//Nechryael
		World.spawnNPC(1613, new WorldTile(3436, 3568, 2), -1, true, true);//Nechryael
		World.spawnNPC(1613, new WorldTile(3435, 3572, 2), -1, true, true);//Nechryael
		World.spawnNPC(1613, new WorldTile(3446, 3572, 2), -1, true, true);//Nechryael
		World.spawnNPC(1615, new WorldTile(3425, 3568, 2), -1, true, true);//Abyssal Demon
		World.spawnNPC(1615, new WorldTile(3428, 3565, 2), -1, true, true);//Abyssal Demon
		World.spawnNPC(1615, new WorldTile(3425, 3574, 2), -1, true, true);//Abyssal Demon
		World.spawnNPC(1615, new WorldTile(3423, 3572, 2), -1, true, true);//Abyssal Demon
		World.spawnNPC(1615, new WorldTile(3427, 3570, 2), -1, true, true);//Abyssal Demon
		World.spawnNPC(1615, new WorldTile(3416, 3567, 2), -1, true, true);//Abyssal Demon
		World.spawnNPC(1615, new WorldTile(3413, 3570, 2), -1, true, true);//Abyssal Demon
		World.spawnNPC(1615, new WorldTile(3410, 3574, 2), -1, true, true);//Abyssal Demon
		World.spawnNPC(1615, new WorldTile(3413, 3574, 2), -1, true, true);//Abyssal Demon

		//Lava mine
                World.spawnObject(new WorldObject(49766, 10, -1, 2182, 5670, 0), true);//Novite
                World.spawnObject(new WorldObject(49768, 10, -1, 2182, 5671, 0), true);//Bathus
                World.spawnObject(new WorldObject(49770, 10, -1, 2182, 5672, 0), true);//Marmaros
                World.spawnObject(new WorldObject(49772, 10, -1, 2182, 5673, 0), true);//Kratonite
                World.spawnObject(new WorldObject(49774, 10, -1, 2182, 5674, 0), true);//Fractite
                World.spawnObject(new WorldObject(49776, 10, -1, 2182, 5675, 0), true);//Zephyrium
                World.spawnObject(new WorldObject(49778, 10, -1, 2182, 5676, 0), true);//Argonite
                World.spawnObject(new WorldObject(49780, 10, -1, 2182, 5677, 0), true);//Katagon
                World.spawnObject(new WorldObject(49782, 10, -1, 2182, 5678, 0), true);//Gogonite
                World.spawnObject(new WorldObject(49784, 10, -1, 2182, 5679, 0), true);//Promethium

		//AFK Zone Remove Objects
                World.deleteObject(new WorldTile(2008, 4757, 0));//Removal1
                World.deleteObject(new WorldTile(2007, 4757, 0));//Removal2
                World.deleteObject(new WorldTile(2006, 4757, 0));//Removal3
                World.deleteObject(new WorldTile(2005, 4757, 0));//Removal4
                World.deleteObject(new WorldTile(2004, 4757, 0));//Removal5
                World.deleteObject(new WorldTile(2003, 4757, 0));//Removal6
                World.deleteObject(new WorldTile(2008, 4755, 0));//Removal1
                World.deleteObject(new WorldTile(2007, 4755, 0));//Removal2
                World.deleteObject(new WorldTile(2006, 4755, 0));//Removal3
                World.deleteObject(new WorldTile(2005, 4755, 0));//Removal4
                World.deleteObject(new WorldTile(2004, 4755, 0));//Removal5
                World.deleteObject(new WorldTile(2003, 4755, 0));//Removal6
                World.deleteObject(new WorldTile(2008, 4753, 0));//Removal1
                World.deleteObject(new WorldTile(2007, 4753, 0));//Removal2
                World.deleteObject(new WorldTile(2006, 4753, 0));//Removal3
                World.deleteObject(new WorldTile(2005, 4753, 0));//Removal4
                World.deleteObject(new WorldTile(2004, 4753, 0));//Removal5
                World.deleteObject(new WorldTile(2003, 4753, 0));//Removal6
                World.deleteObject(new WorldTile(2011, 4753, 0));//Removal1
                World.deleteObject(new WorldTile(2012, 4753, 0));//Removal2
                World.deleteObject(new WorldTile(2013, 4753, 0));//Removal3
                World.deleteObject(new WorldTile(2014, 4753, 0));//Removal4
                World.deleteObject(new WorldTile(2015, 4753, 0));//Removal5
                World.deleteObject(new WorldTile(2016, 4753, 0));//Removal6
                World.deleteObject(new WorldTile(2011, 4755, 0));//Removal1
                World.deleteObject(new WorldTile(2012, 4755, 0));//Removal2
                World.deleteObject(new WorldTile(2013, 4755, 0));//Removal3
                World.deleteObject(new WorldTile(2014, 4755, 0));//Removal4
                World.deleteObject(new WorldTile(2015, 4755, 0));//Removal5
                World.deleteObject(new WorldTile(2016, 4755, 0));//Removal6
                World.deleteObject(new WorldTile(2011, 4757, 0));//Removal1
                World.deleteObject(new WorldTile(2012, 4757, 0));//Removal2
                World.deleteObject(new WorldTile(2013, 4757, 0));//Removal3
                World.deleteObject(new WorldTile(2014, 4757, 0));//Removal4
                World.deleteObject(new WorldTile(2015, 4757, 0));//Removal5
                World.deleteObject(new WorldTile(2016, 4757, 0));//Removal6

		//AFK Chairs
                World.spawnObject(new WorldObject(13671, 10, -2, 2008, 4758, 0), true);//Skeleton Throne1
                World.spawnObject(new WorldObject(13671, 10, -2, 2007, 4758, 0), true);//Skeleton Throne2
                World.spawnObject(new WorldObject(13671, 10, -2, 2006, 4758, 0), true);//Skeleton Throne3
                World.spawnObject(new WorldObject(13671, 10, -2, 2005, 4758, 0), true);//Skeleton Throne4
                World.spawnObject(new WorldObject(13671, 10, -2, 2004, 4758, 0), true);//Skeleton Throne5
                World.spawnObject(new WorldObject(13671, 10, -2, 2003, 4758, 0), true);//Skeleton Throne6
                World.spawnObject(new WorldObject(13671, 10, -2, 2008, 4756, 0), true);//Skeleton Throne1
                World.spawnObject(new WorldObject(13671, 10, -2, 2007, 4756, 0), true);//Skeleton Throne2
                World.spawnObject(new WorldObject(13671, 10, -2, 2006, 4756, 0), true);//Skeleton Throne3
                World.spawnObject(new WorldObject(13671, 10, -2, 2005, 4756, 0), true);//Skeleton Throne4
                World.spawnObject(new WorldObject(13671, 10, -2, 2004, 4756, 0), true);//Skeleton Throne5
                World.spawnObject(new WorldObject(13671, 10, -2, 2003, 4756, 0), true);//Skeleton Throne6
                World.spawnObject(new WorldObject(13671, 10, -2, 2008, 4754, 0), true);//Skeleton Throne1
                World.spawnObject(new WorldObject(13671, 10, -2, 2007, 4754, 0), true);//Skeleton Throne2
                World.spawnObject(new WorldObject(13671, 10, -2, 2006, 4754, 0), true);//Skeleton Throne3
                World.spawnObject(new WorldObject(13671, 10, -2, 2005, 4754, 0), true);//Skeleton Throne4
                World.spawnObject(new WorldObject(13671, 10, -2, 2004, 4754, 0), true);//Skeleton Throne5
                World.spawnObject(new WorldObject(13671, 10, -2, 2003, 4754, 0), true);//Skeleton Throne6
                World.spawnObject(new WorldObject(13671, 10, -2, 2008, 4752, 0), true);//Skeleton Throne1
                World.spawnObject(new WorldObject(13671, 10, -2, 2007, 4752, 0), true);//Skeleton Throne2
                World.spawnObject(new WorldObject(13671, 10, -2, 2006, 4752, 0), true);//Skeleton Throne3
                World.spawnObject(new WorldObject(13671, 10, -2, 2005, 4752, 0), true);//Skeleton Throne4
                World.spawnObject(new WorldObject(13671, 10, -2, 2004, 4752, 0), true);//Skeleton Throne5
                World.spawnObject(new WorldObject(13671, 10, -2, 2003, 4752, 0), true);//Skeleton Throne6
                World.spawnObject(new WorldObject(13671, 10, -2, 2011, 4752, 0), true);//Skeleton Throne1
                World.spawnObject(new WorldObject(13671, 10, -2, 2012, 4752, 0), true);//Skeleton Throne2
                World.spawnObject(new WorldObject(13671, 10, -2, 2013, 4752, 0), true);//Skeleton Throne3
                World.spawnObject(new WorldObject(13671, 10, -2, 2014, 4752, 0), true);//Skeleton Throne4
                World.spawnObject(new WorldObject(13671, 10, -2, 2015, 4752, 0), true);//Skeleton Throne5
                World.spawnObject(new WorldObject(13671, 10, -2, 2016, 4752, 0), true);//Skeleton Throne6
                World.spawnObject(new WorldObject(13671, 10, -2, 2011, 4754, 0), true);//Skeleton Throne1
                World.spawnObject(new WorldObject(13671, 10, -2, 2012, 4754, 0), true);//Skeleton Throne2
                World.spawnObject(new WorldObject(13671, 10, -2, 2013, 4754, 0), true);//Skeleton Throne3
                World.spawnObject(new WorldObject(13671, 10, -2, 2014, 4754, 0), true);//Skeleton Throne4
                World.spawnObject(new WorldObject(13671, 10, -2, 2015, 4754, 0), true);//Skeleton Throne5
                World.spawnObject(new WorldObject(13671, 10, -2, 2016, 4754, 0), true);//Skeleton Throne6
                World.spawnObject(new WorldObject(13671, 10, -2, 2011, 4756, 0), true);//Skeleton Throne1
                World.spawnObject(new WorldObject(13671, 10, -2, 2012, 4756, 0), true);//Skeleton Throne2
                World.spawnObject(new WorldObject(13671, 10, -2, 2013, 4756, 0), true);//Skeleton Throne3
                World.spawnObject(new WorldObject(13671, 10, -2, 2014, 4756, 0), true);//Skeleton Throne4
                World.spawnObject(new WorldObject(13671, 10, -2, 2015, 4756, 0), true);//Skeleton Throne5
                World.spawnObject(new WorldObject(13671, 10, -2, 2016, 4756, 0), true);//Skeleton Throne6
                World.spawnObject(new WorldObject(13671, 10, -2, 2011, 4758, 0), true);//Skeleton Throne1
                World.spawnObject(new WorldObject(13671, 10, -2, 2012, 4758, 0), true);//Skeleton Throne2
                World.spawnObject(new WorldObject(13671, 10, -2, 2013, 4758, 0), true);//Skeleton Throne3
                World.spawnObject(new WorldObject(13671, 10, -2, 2014, 4758, 0), true);//Skeleton Throne4
                World.spawnObject(new WorldObject(13671, 10, -2, 2015, 4758, 0), true);//Skeleton Throne5
                World.spawnObject(new WorldObject(13671, 10, -2, 2016, 4758, 0), true);//Skeleton Throne6

		//Thrones
                //World.spawnObject(new WorldObject(13671, 10, -1, 2846, 10208, 0), true);//Demonic Throne
                //World.spawnObject(new WorldObject(13671, 10, -1, 2846, 10207, 0), true);//Demonic Throne
                //World.spawnObject(new WorldObject(13671, 10, -1, 2846, 10206, 0), true);//Demonic Throne
                //World.spawnObject(new WorldObject(13671, 10, -1, 2846, 10211, 0), true);//Demonic Throne
                //World.spawnObject(new WorldObject(13671, 10, -1, 2846, 10212, 0), true);//Demonic Throne
                //World.spawnObject(new WorldObject(13671, 10, -1, 2846, 10213, 0), true);//Demonic Throne

		//Elvarg objects
                World.spawnObject(new WorldObject(32014, 10, -1, 2847, 9635, 0), true);//blocks exit
                World.spawnObject(new WorldObject(32014, 10, -1, 2847, 9636, 0), true);//blocks exit
                World.spawnObject(new WorldObject(32014, 10, -1, 2847, 9637, 0), true);//blocks exit
                World.spawnObject(new WorldObject(16048, 10, -1, 2848, 9627, 0), true);//Elvarg exit

		//Keldagrim Bankers
		World.spawnNPC(494, new WorldTile(2838, 10205, 0), -1, false);
		World.spawnNPC(494, new WorldTile(2836, 10205, 0), -1, false);

		//Prestige Grim
		//World.spawnNPC(14386, new WorldTile(2838, 10201, 0), -1, false);

		//Port Sarim Klarense
		World.spawnNPC(744, new WorldTile(3047, 3204, 0), -1, false);

		//Hunter shop
		World.spawnNPC(5112, new WorldTile(2525, 2915, 0), -1, false);

		//Sawmill Operator
		World.spawnNPC(4250, new WorldTile(3162, 3222, 0), -1, false);


		//Bork
                World.spawnObject(new WorldObject(52861, 10, -2, 2855, 10184, 0), true);

		//New Kalphite entrance
                World.spawnObject(new WorldObject(23610, 10, -2, 2840, 10184, 0), true);

		//Dungeoneering Banker
		World.spawnNPC(9710, new WorldTile(3446, 3718, 0), -1, false);
		World.spawnNPC(9709, new WorldTile(3452, 3720, 0), -1, false);
		World.spawnNPC(9713, new WorldTile(3439, 3698, 0), -1, false);

		//Dungeoneering shop
		World.spawnNPC(9711, new WorldTile(3443, 3698, 0), -1, false);
		World.spawnNPC(12392, new WorldTile(3503, 3567, 0), -1, false);

		//Dzone boss
		World.spawnNPC(6358, new WorldTile(1348, 5205, 0), -1, true, true);
                World.spawnObject(new WorldObject(41900, 10, -2, 2652, 3995, 1), true);//cave to dzone boss

		//Easter Event
		World.spawnNPC(7197, new WorldTile(2216, 3134, 0), -1, false);
		World.spawnNPC(3686, new WorldTile(2222, 3130, 0), -1, true, true);
		World.spawnNPC(3686, new WorldTile(2222, 3130, 0), -1, true, true);
		World.spawnNPC(3686, new WorldTile(2222, 3130, 0), -1, true, true);
		World.spawnNPC(3686, new WorldTile(2222, 3130, 0), -1, true, true);
		World.spawnNPC(3686, new WorldTile(2222, 3130, 0), -1, true, true);
		World.spawnNPC(3686, new WorldTile(2219, 3125, 0), -1, true, true);
		World.spawnNPC(3686, new WorldTile(2219, 3125, 0), -1, true, true);
		World.spawnNPC(3686, new WorldTile(2219, 3125, 0), -1, true, true);
		World.spawnNPC(3686, new WorldTile(2219, 3125, 0), -1, true, true);
		World.spawnNPC(3686, new WorldTile(2219, 3125, 0), -1, true, true);
		World.spawnNPC(3686, new WorldTile(2219, 3125, 0), -1, true, true);
		World.spawnNPC(3686, new WorldTile(2211, 3127, 0), -1, true, true);
		World.spawnNPC(3686, new WorldTile(2211, 3127, 0), -1, true, true);
		World.spawnNPC(3686, new WorldTile(2211, 3127, 0), -1, true, true);
		World.spawnNPC(3686, new WorldTile(2211, 3127, 0), -1, true, true);
		World.spawnNPC(3686, new WorldTile(2211, 3127, 0), -1, true, true);

		//Bandos NPC Spawns
		World.spawnNPC(6270, new WorldTile(2856, 5352, 0), -1, true, true);
		World.spawnNPC(6270, new WorldTile(2857, 5362, 0), -1, true, true);
		World.spawnNPC(6270, new WorldTile(2849, 5357, 0), -1, true, true);
		World.spawnNPC(6270, new WorldTile(2845, 5349, 0), -1, true, true);
		World.spawnNPC(6270, new WorldTile(2833, 5350, 0), -1, true, true);
		World.spawnNPC(6270, new WorldTile(2844, 5334, 0), -1, true, true);
		World.spawnNPC(6270, new WorldTile(2839, 5327, 0), -1, true, true);
		World.spawnNPC(6270, new WorldTile(2834, 5323, 0), -1, true, true);
		World.spawnNPC(6270, new WorldTile(2829, 5316, 0), -1, true, true);
		World.spawnNPC(6270, new WorldTile(2856, 5352, 0), -1, true, true);
		World.spawnNPC(6270, new WorldTile(2857, 5362, 0), -1, true, true);
		World.spawnNPC(6270, new WorldTile(2849, 5357, 0), -1, true, true);
		World.spawnNPC(6270, new WorldTile(2845, 5349, 0), -1, true, true);
		World.spawnNPC(6270, new WorldTile(2833, 5350, 0), -1, true, true);
		World.spawnNPC(6270, new WorldTile(2844, 5334, 0), -1, true, true);
		World.spawnNPC(6270, new WorldTile(2839, 5327, 0), -1, true, true);
		World.spawnNPC(6270, new WorldTile(2834, 5323, 0), -1, true, true);
		World.spawnNPC(6270, new WorldTile(2829, 5316, 0), -1, true, true);

		//Kree'arra NPC Spawns
		World.spawnNPC(6239, new WorldTile(2834, 5284, 0), -1, true, true);
		World.spawnNPC(6239, new WorldTile(2842, 5289, 0), -1, true, true);
		World.spawnNPC(6239, new WorldTile(2843, 5280, 0), -1, true, true);
		World.spawnNPC(6239, new WorldTile(2850, 5276, 0), -1, true, true);
		World.spawnNPC(6239, new WorldTile(2839, 5270, 0), -1, true, true);
		World.spawnNPC(6239, new WorldTile(2843, 5261, 0), -1, true, true);
		World.spawnNPC(6239, new WorldTile(2858, 5267, 0), -1, true, true);
		World.spawnNPC(6239, new WorldTile(2865, 5261, 0), -1, true, true);
		World.spawnNPC(6239, new WorldTile(2874, 5264, 0), -1, true, true);
		World.spawnNPC(6239, new WorldTile(2834, 5284, 0), -1, true, true);
		World.spawnNPC(6239, new WorldTile(2842, 5289, 0), -1, true, true);
		World.spawnNPC(6239, new WorldTile(2843, 5280, 0), -1, true, true);
		World.spawnNPC(6239, new WorldTile(2850, 5276, 0), -1, true, true);
		World.spawnNPC(6239, new WorldTile(2839, 5270, 0), -1, true, true);
		World.spawnNPC(6239, new WorldTile(2843, 5261, 0), -1, true, true);
		World.spawnNPC(6239, new WorldTile(2858, 5267, 0), -1, true, true);
		World.spawnNPC(6239, new WorldTile(2865, 5261, 0), -1, true, true);
		World.spawnNPC(6239, new WorldTile(2874, 5264, 0), -1, true, true);

		//Saradomin NPC Spawns
		World.spawnNPC(6258, new WorldTile(2920, 5266, 0), -1, true, true);
		World.spawnNPC(6258, new WorldTile(2917, 5272, 0), -1, true, true);
		World.spawnNPC(6258, new WorldTile(2911, 5267, 0), -1, true, true);
		World.spawnNPC(6258, new WorldTile(2921, 5284, 0), -1, true, true);
		World.spawnNPC(6258, new WorldTile(2915, 5286, 0), -1, true, true);
		World.spawnNPC(6258, new WorldTile(2928, 5290, 0), -1, true, true);
		World.spawnNPC(6258, new WorldTile(2921, 5292, 0), -1, true, true);
		World.spawnNPC(6258, new WorldTile(2918, 5297, 0), -1, true, true);
		World.spawnNPC(6258, new WorldTile(2920, 5266, 0), -1, true, true);
		World.spawnNPC(6258, new WorldTile(2917, 5272, 0), -1, true, true);
		World.spawnNPC(6258, new WorldTile(2911, 5267, 0), -1, true, true);
		World.spawnNPC(6258, new WorldTile(2921, 5284, 0), -1, true, true);
		World.spawnNPC(6258, new WorldTile(2915, 5286, 0), -1, true, true);
		World.spawnNPC(6258, new WorldTile(2928, 5290, 0), -1, true, true);
		World.spawnNPC(6258, new WorldTile(2921, 5292, 0), -1, true, true);
		World.spawnNPC(6258, new WorldTile(2918, 5297, 0), -1, true, true);

		//K'ril NPC Spawns
		World.spawnNPC(6215, new WorldTile(2920, 5337, 0), -1, true, true);
		World.spawnNPC(6215, new WorldTile(2929, 5342, 0), -1, true, true);
		World.spawnNPC(6215, new WorldTile(2923, 5346, 0), -1, true, true);
		World.spawnNPC(6215, new WorldTile(2926, 5353, 0), -1, true, true);
		World.spawnNPC(6215, new WorldTile(2915, 5348, 0), -1, true, true);
		World.spawnNPC(6215, new WorldTile(2911, 5342, 0), -1, true, true);
		World.spawnNPC(6215, new WorldTile(2909, 5354, 0), -1, true, true);
		World.spawnNPC(6215, new WorldTile(2902, 5355, 0), -1, true, true);
		World.spawnNPC(6215, new WorldTile(2893, 5355, 0), -1, true, true);
		World.spawnNPC(6215, new WorldTile(2886, 5354, 0), -1, true, true);
		World.spawnNPC(6215, new WorldTile(2889, 5362, 0), -1, true, true);
		World.spawnNPC(6215, new WorldTile(2920, 5337, 0), -1, true, true);
		World.spawnNPC(6215, new WorldTile(2929, 5342, 0), -1, true, true);
		World.spawnNPC(6215, new WorldTile(2923, 5346, 0), -1, true, true);
		World.spawnNPC(6215, new WorldTile(2926, 5353, 0), -1, true, true);
		World.spawnNPC(6215, new WorldTile(2915, 5348, 0), -1, true, true);
		World.spawnNPC(6215, new WorldTile(2911, 5342, 0), -1, true, true);
		World.spawnNPC(6215, new WorldTile(2909, 5354, 0), -1, true, true);
		World.spawnNPC(6215, new WorldTile(2902, 5355, 0), -1, true, true);
		World.spawnNPC(6215, new WorldTile(2893, 5355, 0), -1, true, true);
		World.spawnNPC(6215, new WorldTile(2886, 5354, 0), -1, true, true);
		World.spawnNPC(6215, new WorldTile(2889, 5362, 0), -1, true, true);

		//Nex Basement Room Spawns
		World.spawnNPC(6215, new WorldTile(2862, 5209, 0), -1, true, true);
		World.spawnNPC(6258, new WorldTile(2852, 5207, 0), -1, true, true);
		World.spawnNPC(6270, new WorldTile(2858, 5205, 0), -1, true, true);
		World.spawnNPC(6239, new WorldTile(2863, 5202, 0), -1, true, true);
		World.spawnNPC(6215, new WorldTile(2873, 5203, 0), -1, true, true);
		World.spawnNPC(6258, new WorldTile(2870, 5211, 0), -1, true, true);
		World.spawnNPC(6270, new WorldTile(2878, 5204, 0), -1, true, true);
		World.spawnNPC(6239, new WorldTile(2878, 5215, 0), -1, true, true);
		World.spawnNPC(6215, new WorldTile(2884, 5213, 0), -1, true, true);
		World.spawnNPC(6258, new WorldTile(2890, 5207, 0), -1, true, true);
		World.spawnNPC(6270, new WorldTile(2886, 5198, 0), -1, true, true);
		World.spawnNPC(6239, new WorldTile(2885, 5218, 0), -1, true, true);
		World.spawnNPC(6215, new WorldTile(2862, 5209, 0), -1, true, true);
		World.spawnNPC(6258, new WorldTile(2852, 5207, 0), -1, true, true);
		World.spawnNPC(6270, new WorldTile(2858, 5205, 0), -1, true, true);
		World.spawnNPC(6239, new WorldTile(2863, 5202, 0), -1, true, true);
		World.spawnNPC(6215, new WorldTile(2873, 5203, 0), -1, true, true);
		World.spawnNPC(6258, new WorldTile(2870, 5211, 0), -1, true, true);
		World.spawnNPC(6270, new WorldTile(2878, 5204, 0), -1, true, true);
		World.spawnNPC(6239, new WorldTile(2878, 5215, 0), -1, true, true);
		World.spawnNPC(6215, new WorldTile(2884, 5213, 0), -1, true, true);
		World.spawnNPC(6258, new WorldTile(2890, 5207, 0), -1, true, true);
		World.spawnNPC(6270, new WorldTile(2886, 5198, 0), -1, true, true);
		World.spawnNPC(6239, new WorldTile(2885, 5218, 0), -1, true, true);

		//RuneSpan Spawns
		World.spawnNPC(15402, new WorldTile(3997, 6114, 1), -1, true, true);
		World.spawnNPC(15406, new WorldTile(3997, 6099, 1), -1, true, true);
		World.spawnNPC(15405, new WorldTile(4003, 6108, 1), -1, true, true);
		World.spawnNPC(15403, new WorldTile(3989, 6110, 1), -1, true, true);

		//Lessers
		World.spawnNPC(82, new WorldTile(2839, 9558, 0), -1, true, true);
		World.spawnNPC(82, new WorldTile(2839, 9553, 0), -1, true, true);
		World.spawnNPC(82, new WorldTile(2836, 9560, 0), -1, true, true);
		World.spawnNPC(82, new WorldTile(2832, 9563, 0), -1, true, true);
		World.spawnNPC(82, new WorldTile(2837, 9566, 0), -1, true, true);
		World.spawnNPC(63, new WorldTile(2836, 9579, 0), -1, true, true);
		World.spawnNPC(63, new WorldTile(2833, 9585, 0), -1, true, true);
		World.spawnNPC(63, new WorldTile(2838, 9583, 0), -1, true, true);

		//Grotworm Lair Cave 1
		World.spawnNPC(15461, new WorldTile(1172, 6367, 0), -1, true, true);
		World.spawnNPC(15461, new WorldTile(1172, 6359, 0), -1, true, true);
		World.spawnNPC(15461, new WorldTile(1170, 6375, 0), -1, true, true);
		World.spawnNPC(15461, new WorldTile(1158, 6367, 0), -1, true, true);
		World.spawnNPC(15461, new WorldTile(1158, 6356, 0), -1, true, true);
		World.spawnNPC(15461, new WorldTile(1146, 6365, 0), -1, true, true);
		World.spawnNPC(15461, new WorldTile(1132, 6360, 0), -1, true, true);
		World.spawnNPC(15461, new WorldTile(1123, 6367, 0), -1, true, true);
		World.spawnNPC(15461, new WorldTile(1118, 6371, 0), -1, true, true);
		World.spawnNPC(15461, new WorldTile(1120, 6346, 0), -1, true, true);
		World.spawnNPC(15461, new WorldTile(1109, 6352, 0), -1, true, true);
		World.spawnNPC(15461, new WorldTile(1093, 6361, 0), -1, true, true);
		//Grotworm Lair Cave 2
		World.spawnNPC(15462, new WorldTile(1332, 6481, 0), -1, true, true);
		World.spawnNPC(15462, new WorldTile(1323, 6485, 0), -1, true, true);
		World.spawnNPC(15462, new WorldTile(1317, 6479, 0), -1, true, true);
		World.spawnNPC(15462, new WorldTile(1312, 6470, 0), -1, true, true);
		World.spawnNPC(15462, new WorldTile(1326, 6462, 0), -1, true, true);
		World.spawnNPC(15462, new WorldTile(1321, 6465, 0), -1, true, true);
		World.spawnNPC(15462, new WorldTile(1318, 6444, 0), -1, true, true);
		World.spawnNPC(15462, new WorldTile(1310, 6442, 0), -1, true, true);
		World.spawnNPC(15462, new WorldTile(1321, 6439, 0), -1, true, true);
		World.spawnNPC(15462, new WorldTile(1330, 6427, 0), -1, true, true);
		World.spawnNPC(15462, new WorldTile(1318, 6421, 0), -1, true, true);
		World.spawnNPC(15462, new WorldTile(1324, 6417, 0), -1, true, true);
		World.spawnNPC(15462, new WorldTile(1329, 6395, 0), -1, true, true);
		World.spawnNPC(15462, new WorldTile(1321, 6390, 0), -1, true, true);
		//Grotworm Lair Cave 3
		World.spawnNPC(15463, new WorldTile(1099, 6503, 0), -1, true, true);
		World.spawnNPC(15463, new WorldTile(1098, 6498, 0), -1, true, true);
		World.spawnNPC(15463, new WorldTile(1111, 6506, 0), -1, true, true);
		World.spawnNPC(15463, new WorldTile(1104, 6485, 0), -1, true, true);
		World.spawnNPC(15463, new WorldTile(1112, 6478, 0), -1, true, true);
		World.spawnNPC(15463, new WorldTile(1124, 6474, 0), -1, true, true);
		World.spawnNPC(15463, new WorldTile(1131, 6486, 0), -1, true, true);
		World.spawnNPC(15463, new WorldTile(1142, 6508, 0), -1, true, true);
		World.spawnNPC(15463, new WorldTile(1153, 6497, 0), -1, true, true);
		World.spawnNPC(15463, new WorldTile(1158, 6505, 0), -1, true, true);
		World.spawnNPC(15463, new WorldTile(1168, 6507, 0), -1, true, true);
		World.spawnNPC(15463, new WorldTile(1170, 6493, 0), -1, true, true);
		World.spawnNPC(15463, new WorldTile(1183, 6497, 0), -1, true, true);
		World.spawnNPC(15463, new WorldTile(1187, 6508, 0), -1, true, true);
		World.spawnNPC(15463, new WorldTile(1198, 6507, 0), -1, true, true);

		//Dungeoneering Monsters
		World.spawnNPC(1620, new WorldTile(2799, 10033, 0), -1, true, true);
		World.spawnNPC(1620, new WorldTile(2793, 10031, 0), -1, true, true);
		World.spawnNPC(1620, new WorldTile(2788, 10034, 0), -1, true, true);
		World.spawnNPC(1620, new WorldTile(2786, 10039, 0), -1, true, true);
		World.spawnNPC(1620, new WorldTile(2793, 10038, 0), -1, true, true);
		World.spawnNPC(1620, new WorldTile(2797, 10036, 0), -1, true, true);
		World.spawnNPC(1620, new WorldTile(2792, 10035, 0), -1, true, true);
		World.spawnNPC(9172, new WorldTile(2739, 9981, 0), -1, true, true);
		World.spawnNPC(9172, new WorldTile(2733, 9975, 0), -1, true, true);
		World.spawnNPC(9172, new WorldTile(2731, 9981, 0), -1, true, true);
		World.spawnNPC(9172, new WorldTile(2724, 9976, 0), -1, true, true);
		World.spawnNPC(9172, new WorldTile(2720, 9971, 0), -1, true, true);
		World.spawnNPC(9172, new WorldTile(2713, 9971, 0), -1, true, true);
		World.spawnNPC(9172, new WorldTile(2726, 9973, 0), -1, true, true);
		
		//Eyeball Minigame Barriers
                World.spawnObject(new WorldObject(1864, 10, 1, 3239, 9366, 0), true);
                World.spawnObject(new WorldObject(1864, 10, 1, 3239, 9365, 0), true);
                World.spawnObject(new WorldObject(1864, 10, 1, 3239, 9364, 0), true);
                World.spawnObject(new WorldObject(1864, 10, 1, 3239, 9363, 0), true);
		
		//Guildmaster Darren Lightfinger
		World.spawnNPC(11275, new WorldTile(4760, 5903, 0), -1, false);

		//Polypore Dungeon Shop
		World.spawnNPC(875, new WorldTile(4724, 5466, 0), -1, false);

		//Corporeal Shop
		World.spawnNPC(13191, new WorldTile(2722, 4897, 0), -1, false);

		//Tzhaar Mining
                World.spawnObject(new WorldObject(55514, 10, -2, 4686, 5139, 0), true);//Notive Ore
                World.spawnObject(new WorldObject(55516, 10, -2, 4686, 5140, 0), true);//Bathus Ore
                World.spawnObject(new WorldObject(55518, 10, -2, 4686, 5141, 0), true);//Marmaros Ore
                World.spawnObject(new WorldObject(55520, 10, -2, 4687, 5141, 0), true);//Kratonite Ore
                World.spawnObject(new WorldObject(55522, 10, -2, 4684, 5136, 0), true);//Fractite Ore
                World.spawnObject(new WorldObject(55524, 10, -2, 4683, 5136, 0), true);//Zephyrium Ore
                World.spawnObject(new WorldObject(55526, 10, -2, 4682, 5136, 0), true);//Argonite Ore
                World.spawnObject(new WorldObject(55528, 10, -2, 4681, 5136, 0), true);//Katagon Ore
                World.spawnObject(new WorldObject(55530, 10, -2, 4681, 5135, 0), true);//Gorgonite Ore
                World.spawnObject(new WorldObject(55532, 10, -2, 4682, 5140, 0), true);//Promethium Ore
                World.spawnObject(new WorldObject(50305, 10, -2, 4676, 5136, 0), true);//Pile Of Rocks AKA Perfect Gold
                World.spawnObject(new WorldObject(50305, 10, -2, 4675, 5139, 0), true);//Pile Of Rocks AKA Perfect Gold
                World.spawnObject(new WorldObject(50305, 10, -2, 4670, 5138, 0), true);//Pile Of Rocks AKA Perfect Gold
                World.spawnObject(new WorldObject(50305, 10, -2, 4672, 5132, 0), true);//Pile Of Rocks AKA Perfect Gold
                World.spawnObject(new WorldObject(50305, 10, -2, 4667, 5136, 0), true);//Pile Of Rocks AKA Perfect Gold
                World.spawnObject(new WorldObject(50305, 10, -2, 4664, 5140, 0), true);//Pile Of Rocks AKA Perfect Gold
                World.spawnObject(new WorldObject(2782, 10, -2, 4684, 5142, 0), true);//Anvil
                World.spawnObject(new WorldObject(2782, 10, -2, 4680, 5138, 0), true);//Anvil
                World.spawnObject(new WorldObject(2782, 10, -2, 3682, 3479, 0), true);//Anvil

		//Dagannoth Kings
		World.spawnNPC(2881, new WorldTile(2922, 4441, 0), -1, true, true);
		World.spawnNPC(2882, new WorldTile(2922, 4452, 0), -1, true, true);
		World.spawnNPC(2883, new WorldTile(2915, 4459, 0), -1, true, true);

		//Dung Bosses
		World.spawnNPC(10039, new WorldTile(2581, 3909, 0), -1, true, true);
		World.spawnNPC(10141, new WorldTile(1377, 5918, 0), -1, true, true);
		World.spawnNPC(9911, new WorldTile(2520, 10020, 0), -1, true, true);
		World.spawnNPC(12841, new WorldTile(2463, 4782, 0), -1, true, true);

		//Santa Event
                World.spawnObject(new WorldObject(65996, 10, -2, 2831, 3865, 0), true);//1-Air altar
		World.spawnNPC(9400, new WorldTile(2833, 3859, 0), -1, false);
		//World.spawnNPC(14765, new WorldTile(3434, 2889, 0), -1, false);//Snow Imp
		World.spawnNPC(4440, new WorldTile(2828, 3897, 0), -1, true, true);
		World.spawnNPC(4440, new WorldTile(2834, 3912, 0), -1, true, true);
		World.spawnNPC(4440, new WorldTile(2843, 3919, 0), -1, true, true);
		World.spawnNPC(4440, new WorldTile(2853, 3915, 0), -1, true, true);
		World.spawnNPC(4440, new WorldTile(2854, 3902, 0), -1, true, true);
		World.spawnNPC(4440, new WorldTile(2864, 3896, 0), -1, true, true);
		World.spawnNPC(4440, new WorldTile(2868, 3913, 0), -1, true, true);
		World.spawnNPC(4440, new WorldTile(2878, 3900, 0), -1, true, true);
		World.spawnNPC(4440, new WorldTile(2875, 3878, 0), -1, true, true);
		World.spawnNPC(4440, new WorldTile(2828, 3897, 0), -1, true, true);
		World.spawnNPC(4440, new WorldTile(2834, 3912, 0), -1, true, true);
		World.spawnNPC(4440, new WorldTile(2843, 3919, 0), -1, true, true);
		World.spawnNPC(4440, new WorldTile(2853, 3915, 0), -1, true, true);
		World.spawnNPC(4440, new WorldTile(2854, 3902, 0), -1, true, true);
		World.spawnNPC(4440, new WorldTile(2864, 3896, 0), -1, true, true);
		World.spawnNPC(4440, new WorldTile(2868, 3913, 0), -1, true, true);
		World.spawnNPC(4440, new WorldTile(2878, 3900, 0), -1, true, true);
		World.spawnNPC(4440, new WorldTile(2875, 3878, 0), -1, true, true);
		World.spawnNPC(4440, new WorldTile(2828, 3897, 0), -1, true, true);
		World.spawnNPC(4440, new WorldTile(2834, 3912, 0), -1, true, true);
		World.spawnNPC(4440, new WorldTile(2843, 3919, 0), -1, true, true);
		World.spawnNPC(4440, new WorldTile(2853, 3915, 0), -1, true, true);
		World.spawnNPC(4440, new WorldTile(2854, 3902, 0), -1, true, true);
		World.spawnNPC(4440, new WorldTile(2864, 3896, 0), -1, true, true);
		World.spawnNPC(4440, new WorldTile(2868, 3913, 0), -1, true, true);
		World.spawnNPC(4440, new WorldTile(2878, 3900, 0), -1, true, true);
		World.spawnNPC(4440, new WorldTile(2875, 3878, 0), -1, true, true);
		
		//Bankers
		World.spawnNPC(495, new WorldTile(3682, 2981, 0), -1, false, false);
		World.spawnNPC(494, new WorldTile(3682, 2982, 0), -1, false, false);
		World.spawnNPC(494, new WorldTile(3682, 2983, 0), -1, false, false);
		
		//Kurdal
		World.spawnNPC(9085, new WorldTile(3430, 3538, 0), 2, false, false);
		
		//Crabs
		World.spawnNPC(1265, new WorldTile(2700, 3715, 0), -1, true, true);
		World.spawnNPC(1265, new WorldTile(2696, 3719, 0), -1, true, true);
		World.spawnNPC(1265, new WorldTile(2706, 3724, 0), -1, true, true);
		World.spawnNPC(1265, new WorldTile(2711, 3719, 0), -1, true, true);
		World.spawnNPC(1265, new WorldTile(2717, 3726, 0), -1, true, true);
		World.spawnNPC(1265, new WorldTile(2721, 3717, 0), -1, true, true);
		World.spawnNPC(1265, new WorldTile(2721, 3706, 0), -1, true, true);
		World.spawnNPC(1265, new WorldTile(2716, 3700, 0), -1, true, true);
		World.spawnNPC(6105, new WorldTile(2697, 3724, 0), -1, true, true);
		World.spawnNPC(6105, new WorldTile(2714, 3728, 0), -1, true, true);
		//End Crabs start abyss
		World.spawnNPC(1615, new WorldTile(3029, 4842, 0), -1, true, true);
		World.spawnNPC(3200, new WorldTile(3026, 4829, 0), -1, true, true);
		World.spawnNPC(1615, new WorldTile(3033, 4821, 0), -1, true, true);
		World.spawnNPC(3200, new WorldTile(3047, 4822, 0), -1, true, true);
		World.spawnNPC(1615, new WorldTile(3051, 4837, 0), -1, true, true);
		//Start Wyrmzz
		World.spawnNPC(9463, new WorldTile(2983, 9517, 1), -1, true, true);
		World.spawnNPC(9465, new WorldTile(3299, 3020, 0), -1, true, true);
		World.spawnNPC(9465, new WorldTile(3300, 3028, 0), -1, true, true);
		//Snails
		World.spawnNPC(1227, new WorldTile(2861, 9732, 0), -1, true, true);
		World.spawnNPC(1228, new WorldTile(2857, 9733, 0), -1, true, true);
		World.spawnNPC(1229, new WorldTile(2858, 9738, 0), -1, true, true);
		World.spawnNPC(1230, new WorldTile(2861, 9738, 0), -1, true, true);
		World.spawnNPC(1231, new WorldTile(2866, 9736, 0), -1, true, true);
		World.spawnNPC(1232, new WorldTile(2862, 9734, 0), -1, true, true);
		World.spawnNPC(1233, new WorldTile(2860, 9733, 0), -1, true, true);
		World.spawnNPC(1234, new WorldTile(2859, 9736, 0), -1, true, true);
		World.spawnNPC(1235, new WorldTile(2860, 9740, 0), -1, true, true);
		World.spawnNPC(21, new WorldTile(2664, 3310, 0), -1, true, true);
		World.spawnNPC(20, new WorldTile(2664, 3310, 0), -1, true, true);
		World.spawnNPC(23, new WorldTile(2664, 3310, 0), -1, true, true);
		World.spawnNPC(21, new WorldTile(2664, 3301, 0), -1, true, true);
		World.spawnNPC(20, new WorldTile(2664, 3301, 0), -1, true, true);
		World.spawnNPC(23, new WorldTile(2664, 3301, 0), -1, true, true);
		World.spawnNPC(21, new WorldTile(2659, 3306, 0), -1, true, true);
		World.spawnNPC(20, new WorldTile(2659, 3306, 0), -1, true, true);
		World.spawnNPC(23, new WorldTile(2659, 3306, 0), -1, true, true);
		World.spawnNPC(2, new WorldTile(2655, 3314, 0), -1, true, true);
		World.spawnNPC(2, new WorldTile(2656, 3298, 0), -1, true, true);
		World.spawnNPC(2, new WorldTile(2667, 3299, 0), -1, true, true);
		World.spawnNPC(2, new WorldTile(2667, 3314, 0), -1, true, true);
		World.spawnNPC(9, new WorldTile(2965, 3396, 0), -1, true, true);
		World.spawnNPC(9, new WorldTile(2965, 3396, 0), -1, true, true);
		World.spawnNPC(9, new WorldTile(2965, 3396, 0), -1, true, true);
		World.spawnNPC(9, new WorldTile(2965, 3396, 0), -1, true, true);
		World.spawnNPC(19, new WorldTile(2972, 3343, 0), -1, true, true);
		World.spawnNPC(19, new WorldTile(2972, 3343, 0), -1, true, true);
		World.spawnNPC(19, new WorldTile(2972, 3343, 0), -1, true, true);
		World.spawnNPC(19, new WorldTile(2972, 3343, 0), -1, true, true);
		World.spawnNPC(19, new WorldTile(2972, 3343, 0), -1, true, true);
		World.spawnNPC(19, new WorldTile(2972, 3343, 0), -1, true, true);
		World.spawnNPC(19, new WorldTile(2972, 3343, 0), -1, true, true);
		World.spawnNPC(9, new WorldTile(3007, 3323, 0), -1, true, true);
		World.spawnNPC(9, new WorldTile(3007, 3323, 0), -1, true, true);
		World.spawnNPC(9, new WorldTile(3007, 3323, 0), -1, true, true);
		World.spawnNPC(9, new WorldTile(3007, 3323, 0), -1, true, true);
		World.spawnNPC(9, new WorldTile(3270, 3429, 0), -1, true, true);
		World.spawnNPC(9, new WorldTile(3270, 3429, 0), -1, true, true);
		World.spawnNPC(9, new WorldTile(3275, 3428, 0), -1, true, true);
		World.spawnNPC(9, new WorldTile(3275, 3428, 0), -1, true, true);
		World.spawnNPC(9, new WorldTile(3175, 3428, 0), -1, true, true);
		World.spawnNPC(9, new WorldTile(3175, 3428, 0), -1, true, true);
		World.spawnNPC(9, new WorldTile(3175, 3428, 0), -1, true, true);
		World.spawnNPC(9, new WorldTile(3211, 3381, 0), -1, true, true);
		World.spawnNPC(9, new WorldTile(3211, 3381, 0), -1, true, true);
		World.spawnNPC(172, new WorldTile(3227, 3372, 0), -1, true, true);
		World.spawnNPC(174, new WorldTile(3227, 3372, 0), -1, true, true);
		World.spawnNPC(172, new WorldTile(3227, 3372, 0), -1, true, true);
		World.spawnNPC(174, new WorldTile(3228, 3367, 0), -1, true, true);
		World.spawnNPC(172, new WorldTile(3228, 3367, 0), -1, true, true);
		World.spawnNPC(174, new WorldTile(3228, 3367, 0), -1, true, true);
		World.spawnNPC(107, new WorldTile(3299, 3387, 0), -1, true, true);
		World.spawnNPC(107, new WorldTile(3300, 3294, 0), -1, true, true);
		World.spawnNPC(107, new WorldTile(3295, 3299, 0), -1, true, true);
		World.spawnNPC(107, new WorldTile(3299, 3306, 0), -1, true, true);
		World.spawnNPC(107, new WorldTile(3299, 3313, 0), -1, true, true);
		World.spawnNPC(107, new WorldTile(3299, 3313, 0), -1, true, true);
		//Hunter
		World.spawnNPC(1028, new WorldTile(2715, 9203, 0), -1, true, true);
		World.spawnNPC(1029, new WorldTile(2715, 9203, 0), -1, true, true);
		World.spawnNPC(1030, new WorldTile(2715, 9203, 0), -1, true, true);
		World.spawnNPC(1031, new WorldTile(2715, 9203, 0), -1, true, true);
		World.spawnNPC(1032, new WorldTile(2715, 9203, 0), -1, true, true);
		World.spawnNPC(1033, new WorldTile(2715, 9203, 0), -1, true, true);
		World.spawnNPC(1034, new WorldTile(2715, 9203, 0), -1, true, true);
		World.spawnNPC(1035, new WorldTile(2715, 9203, 0), -1, true, true);
		World.spawnNPC(6053, new WorldTile(2715, 9203, 0), -1, true, true);
		World.spawnNPC(6054, new WorldTile(2715, 9203, 0), -1, true, true);
		World.spawnNPC(7420, new WorldTile(2715, 9203, 0), -1, true, true);
		World.spawnNPC(7845, new WorldTile(2715, 9203, 0), -1, true, true);
		World.spawnNPC(7866, new WorldTile(2715, 9203, 0), -1, true, true);
		World.spawnNPC(7902, new WorldTile(2715, 9203, 0), -1, true, true);
		World.spawnNPC(7903, new WorldTile(2715, 9203, 0), -1, true, true);
		World.spawnNPC(1028, new WorldTile(2732, 9186, 0), -1, true, true);
		World.spawnNPC(1029, new WorldTile(2732, 9186, 0), -1, true, true);
		World.spawnNPC(1030, new WorldTile(2732, 9186, 0), -1, true, true);
		World.spawnNPC(1031, new WorldTile(2732, 9186, 0), -1, true, true);
		World.spawnNPC(1032, new WorldTile(2732, 9186, 0), -1, true, true);
		World.spawnNPC(1033, new WorldTile(2732, 9186, 0), -1, true, true);
		World.spawnNPC(1034, new WorldTile(2732, 9186, 0), -1, true, true);
		World.spawnNPC(1035, new WorldTile(2732, 9186, 0), -1, true, true);
		World.spawnNPC(6053, new WorldTile(2732, 9186, 0), -1, true, true);
		World.spawnNPC(6054, new WorldTile(2732, 9186, 0), -1, true, true);
		World.spawnNPC(7420, new WorldTile(2732, 9186, 0), -1, true, true);
		World.spawnNPC(7845, new WorldTile(2732, 9186, 0), -1, true, true);
		World.spawnNPC(7866, new WorldTile(2732, 9186, 0), -1, true, true);
		World.spawnNPC(7902, new WorldTile(2732, 9186, 0), -1, true, true);
		World.spawnNPC(7903, new WorldTile(2732, 9186, 0), -1, true, true);
		World.spawnNPC(1028, new WorldTile(2714, 9170, 0), -1, true, true);
		World.spawnNPC(1029, new WorldTile(2714, 9170, 0), -1, true, true);
		World.spawnNPC(1030, new WorldTile(2714, 9170, 0), -1, true, true);
		World.spawnNPC(1031, new WorldTile(2714, 9170, 0), -1, true, true);
		World.spawnNPC(1032, new WorldTile(2714, 9170, 0), -1, true, true);
		World.spawnNPC(1033, new WorldTile(2714, 9170, 0), -1, true, true);
		World.spawnNPC(1034, new WorldTile(2714, 9170, 0), -1, true, true);
		World.spawnNPC(1035, new WorldTile(2714, 9170, 0), -1, true, true);
		World.spawnNPC(6053, new WorldTile(2714, 9170, 0), -1, true, true);
		World.spawnNPC(6054, new WorldTile(2714, 9170, 0), -1, true, true);
		World.spawnNPC(7420, new WorldTile(2714, 9170, 0), -1, true, true);
		World.spawnNPC(7845, new WorldTile(2714, 9170, 0), -1, true, true);
		World.spawnNPC(7866, new WorldTile(2714, 9170, 0), -1, true, true);
		World.spawnNPC(7902, new WorldTile(2714, 9170, 0), -1, true, true);
		World.spawnNPC(7903, new WorldTile(2714, 9170, 0), -1, true, true);
		World.spawnNPC(1028, new WorldTile(2699, 9186, 0), -1, true, true);
		World.spawnNPC(1029, new WorldTile(2699, 9186, 0), -1, true, true);
		World.spawnNPC(1030, new WorldTile(2699, 9186, 0), -1, true, true);
		World.spawnNPC(1031, new WorldTile(2699, 9186, 0), -1, true, true);
		World.spawnNPC(1032, new WorldTile(2699, 9186, 0), -1, true, true);
		World.spawnNPC(1033, new WorldTile(2699, 9186, 0), -1, true, true);
		World.spawnNPC(1034, new WorldTile(2699, 9186, 0), -1, true, true);
		World.spawnNPC(1035, new WorldTile(2699, 9186, 0), -1, true, true);
		World.spawnNPC(6053, new WorldTile(2699, 9186, 0), -1, true, true);
		World.spawnNPC(6054, new WorldTile(2699, 9186, 0), -1, true, true);
		World.spawnNPC(7420, new WorldTile(2699, 9186, 0), -1, true, true);
		World.spawnNPC(7845, new WorldTile(2699, 9186, 0), -1, true, true);
		World.spawnNPC(7866, new WorldTile(2699, 9186, 0), -1, true, true);
		World.spawnNPC(7902, new WorldTile(2699, 9186, 0), -1, true, true);
		World.spawnNPC(7903, new WorldTile(2699, 9186, 0), -1, true, true);
		//dung boss primal
		World.spawnNPC(12841, new WorldTile(1893, 5352, 2), -1, true, true);
		World.spawnNPC(12841, new WorldTile(1902, 5362, 2), -1, true, true);
		//Polypore Dungeon Floor 1
		World.spawnNPC(14620, new WorldTile(4623, 5453, 3), -1, true, true);
		World.spawnNPC(14692, new WorldTile(4620, 5470, 3), -1, true, true);
		World.spawnNPC(14692, new WorldTile(4623, 5467, 3), -1, true, true);
		World.spawnNPC(14692, new WorldTile(4628, 5478, 3), -1, true, true);
		World.spawnNPC(14692, new WorldTile(4625, 5481, 3), -1, true, true);
		World.spawnNPC(14692, new WorldTile(4629, 5485, 3), -1, true, true);
		World.spawnNPC(14692, new WorldTile(4632, 5480, 3), -1, true, true);
		World.spawnNPC(14690, new WorldTile(4641, 5484, 3), -1, true, true);
		World.spawnNPC(14690, new WorldTile(4640, 5490, 3), -1, true, true);
		World.spawnNPC(14690, new WorldTile(4644, 5488, 3), -1, true, true);
		World.spawnNPC(14690, new WorldTile(4653, 5481, 3), -1, true, true);
		World.spawnNPC(14690, new WorldTile(4651, 5475, 3), -1, true, true);
		World.spawnNPC(14690, new WorldTile(4656, 5477, 3), -1, true, true);
		World.spawnNPC(14692, new WorldTile(4655, 5467, 3), -1, true, true);
		World.spawnNPC(14692, new WorldTile(4657, 5463, 3), -1, true, true);
		World.spawnNPC(14692, new WorldTile(4654, 5460, 3), -1, true, true);
		World.spawnNPC(14692, new WorldTile(4650, 5462, 3), -1, true, true);
		World.spawnNPC(14692, new WorldTile(4655, 5451, 3), -1, true, true);
		World.spawnNPC(14692, new WorldTile(4651, 5448, 3), -1, true, true);
		World.spawnNPC(14690, new WorldTile(4656, 5435, 3), -1, true, true);
		World.spawnNPC(14690, new WorldTile(4660, 5432, 3), -1, true, true);
		World.spawnNPC(14690, new WorldTile(4654, 5431, 3), -1, true, true);
		World.spawnNPC(14692, new WorldTile(4652, 5416, 3), -1, true, true);
		World.spawnNPC(14692, new WorldTile(4656, 5414, 3), -1, true, true);
		World.spawnNPC(14690, new WorldTile(4657, 5406, 3), -1, true, true);
		World.spawnNPC(14692, new WorldTile(4661, 5407, 3), -1, true, true);
		World.spawnNPC(14692, new WorldTile(4662, 5403, 3), -1, true, true);
		World.spawnNPC(14700, new WorldTile(4658, 5401, 3), -1, true, true);
		World.spawnNPC(14700, new WorldTile(4654, 5403, 3), -1, true, true);
		World.spawnNPC(14700, new WorldTile(4654, 5393, 3), -1, true, true);
		World.spawnNPC(14700, new WorldTile(4651, 5389, 3), -1, true, true);
		World.spawnNPC(14700, new WorldTile(4647, 5393, 3), -1, true, true);
		World.spawnNPC(14692, new WorldTile(4641, 5389, 3), -1, true, true);
		World.spawnNPC(14692, new WorldTile(4637, 5387, 3), -1, true, true);
		World.spawnNPC(14692, new WorldTile(4638, 5391, 3), -1, true, true);
		World.spawnNPC(14700, new WorldTile(4629, 5392, 3), -1, true, true);
		World.spawnNPC(14700, new WorldTile(4625, 5395, 3), -1, true, true);
		World.spawnNPC(14700, new WorldTile(4629, 5399, 3), -1, true, true);
		World.spawnNPC(14692, new WorldTile(4627, 5408, 3), -1, true, true);
		World.spawnNPC(14692, new WorldTile(4629, 5411, 3), -1, true, true);
		World.spawnNPC(14690, new WorldTile(4629, 5418, 3), -1, true, true);
		World.spawnNPC(14690, new WorldTile(4634, 5417, 3), -1, true, true);
		World.spawnNPC(14690, new WorldTile(4635, 5421, 3), -1, true, true);
		World.spawnNPC(14690, new WorldTile(4629, 5422, 3), -1, true, true);
		World.spawnNPC(14692, new WorldTile(4687, 5477, 3), -1, true, true);
		World.spawnNPC(14692, new WorldTile(4693, 5478, 3), -1, true, true);
		World.spawnNPC(14692, new WorldTile(4692, 5473, 3), -1, true, true);
		World.spawnNPC(14692, new WorldTile(4690, 5486, 3), -1, true, true);
		World.spawnNPC(14690, new WorldTile(4702, 5487, 3), -1, true, true);
		World.spawnNPC(14690, new WorldTile(4706, 5490, 3), -1, true, true);
		World.spawnNPC(14690, new WorldTile(4707, 5485, 3), -1, true, true);
		World.spawnNPC(14692, new WorldTile(4721, 5491, 3), -1, true, true);
		World.spawnNPC(14692, new WorldTile(4723, 5488, 3), -1, true, true);
		World.spawnNPC(14690, new WorldTile(4715, 5474, 3), -1, true, true);
		World.spawnNPC(14690, new WorldTile(4719, 5471, 3), -1, true, true);
		World.spawnNPC(14690, new WorldTile(4716, 5457, 3), -1, true, true);
		World.spawnNPC(14690, new WorldTile(4719, 5454, 3), -1, true, true);
		World.spawnNPC(14690, new WorldTile(4709, 5453, 3), -1, true, true);
		World.spawnNPC(14690, new WorldTile(4706, 5456, 3), -1, true, true);
		World.spawnNPC(14700, new WorldTile(4695, 5457, 3), -1, true, true);
		World.spawnNPC(14700, new WorldTile(4690, 5459, 3), -1, true, true);
		World.spawnNPC(14700, new WorldTile(4690, 5455, 3), -1, true, true);
		//Polypore Dungeon Floor 2
		World.spawnNPC(14692, new WorldTile(4631, 5408, 2), -1, true, true);
		World.spawnNPC(14692, new WorldTile(4626, 5409, 2), -1, true, true);
		World.spawnNPC(14692, new WorldTile(4627, 5405, 2), -1, true, true);
		World.spawnNPC(14700, new WorldTile(4635, 5415, 2), -1, true, true);
		World.spawnNPC(14700, new WorldTile(4630, 5417, 2), -1, true, true);
		World.spawnNPC(14700, new WorldTile(4631, 5421, 2), -1, true, true);
		World.spawnNPC(14700, new WorldTile(4635, 5421, 2), -1, true, true);
		World.spawnNPC(14692, new WorldTile(4628, 5432, 2), -1, true, true);
		World.spawnNPC(14690, new WorldTile(4624, 5435, 2), -1, true, true);
		World.spawnNPC(14690, new WorldTile(4624, 5430, 2), -1, true, true);
		World.spawnNPC(14700, new WorldTile(4631, 5445, 2), -1, true, true);
		World.spawnNPC(14700, new WorldTile(4628, 5448, 2), -1, true, true);
		World.spawnNPC(14700, new WorldTile(4632, 5450, 2), -1, true, true);
		World.spawnNPC(14690, new WorldTile(4624, 5465, 2), -1, true, true);
		World.spawnNPC(14690, new WorldTile(4620, 5469, 2), -1, true, true);
		World.spawnNPC(14690, new WorldTile(4625, 5470, 2), -1, true, true);
		World.spawnNPC(14690, new WorldTile(4626, 5481, 2), -1, true, true);
		World.spawnNPC(14690, new WorldTile(4632, 5480, 2), -1, true, true);
		World.spawnNPC(14690, new WorldTile(4632, 5487, 2), -1, true, true);
		World.spawnNPC(14690, new WorldTile(4628, 5486, 2), -1, true, true);
		World.spawnNPC(14692, new WorldTile(4646, 5487, 2), -1, true, true);
		World.spawnNPC(14692, new WorldTile(4650, 5484, 2), -1, true, true);
		World.spawnNPC(14700, new WorldTile(4651, 5475, 2), -1, true, true);
		World.spawnNPC(14700, new WorldTile(4656, 5474, 2), -1, true, true);
		World.spawnNPC(14700, new WorldTile(4651, 5471, 2), -1, true, true);
		World.spawnNPC(14700, new WorldTile(4650, 5456, 2), -1, true, true);
		World.spawnNPC(14700, new WorldTile(4648, 5452, 2), -1, true, true);
		World.spawnNPC(14700, new WorldTile(4653, 5452, 2), -1, true, true);
		World.spawnNPC(14700, new WorldTile(4652, 5435, 2), -1, true, true);
		World.spawnNPC(14700, new WorldTile(4658, 5437, 2), -1, true, true);
		World.spawnNPC(14700, new WorldTile(4658, 5433, 2), -1, true, true);
		World.spawnNPC(14700, new WorldTile(4655, 5430, 2), -1, true, true);
		World.spawnNPC(14690, new WorldTile(4654, 5420, 2), -1, true, true);
		World.spawnNPC(14690, new WorldTile(4651, 5416, 2), -1, true, true);
		World.spawnNPC(14700, new WorldTile(4659, 5405, 2), -1, true, true);
		World.spawnNPC(14700, new WorldTile(4654, 5403, 2), -1, true, true);
		World.spawnNPC(14700, new WorldTile(4660, 5402, 2), -1, true, true);
		World.spawnNPC(14700, new WorldTile(4657, 5400, 2), -1, true, true);
		World.spawnNPC(14692, new WorldTile(4653, 5396, 2), -1, true, true);
		World.spawnNPC(14692, new WorldTile(4652, 5390, 2), -1, true, true);
		World.spawnNPC(14692, new WorldTile(4657, 5392, 2), -1, true, true);
		World.spawnNPC(14690, new WorldTile(4634, 5394, 2), -1, true, true);
		World.spawnNPC(14690, new WorldTile(4638, 5392, 2), -1, true, true);
		World.spawnNPC(14690, new WorldTile(4638, 5388, 2), -1, true, true);
		World.spawnNPC(14690, new WorldTile(4633, 5389, 2), -1, true, true);
		World.spawnNPC(14700, new WorldTile(4695, 5459, 2), -1, true, true);
		World.spawnNPC(14700, new WorldTile(4689, 5460, 2), -1, true, true);
		World.spawnNPC(14700, new WorldTile(4688, 5456, 2), -1, true, true);
		World.spawnNPC(14700, new WorldTile(4691, 5454, 2), -1, true, true);
		World.spawnNPC(14690, new WorldTile(4687, 5471, 2), -1, true, true);
		World.spawnNPC(14690, new WorldTile(4692, 5471, 2), -1, true, true);
		World.spawnNPC(14690, new WorldTile(4689, 5476, 2), -1, true, true);
		World.spawnNPC(14700, new WorldTile(4695, 5484, 2), -1, true, true);
		World.spawnNPC(14700, new WorldTile(4699, 5483, 2), -1, true, true);
		World.spawnNPC(14700, new WorldTile(4695, 5488, 2), -1, true, true);
		World.spawnNPC(14700, new WorldTile(4699, 5487, 2), -1, true, true);
		World.spawnNPC(14690, new WorldTile(4710, 5488, 2), -1, true, true);
		World.spawnNPC(14690, new WorldTile(4714, 5484, 2), -1, true, true);
		World.spawnNPC(14700, new WorldTile(4716, 5474, 2), -1, true, true);
		World.spawnNPC(14700, new WorldTile(4720, 5475, 2), -1, true, true);
		World.spawnNPC(14700, new WorldTile(4719, 5471, 2), -1, true, true);
		World.spawnNPC(14690, new WorldTile(4718, 5454, 2), -1, true, true);
		World.spawnNPC(14690, new WorldTile(4707, 5452, 2), -1, true, true);
		World.spawnNPC(14690, new WorldTile(4702, 5454, 2), -1, true, true);
		//Polypore Dungeon Floor 3
		World.spawnNPC(14700, new WorldTile(4705, 5456, 1), -1, true, true);
		World.spawnNPC(14700, new WorldTile(4702, 5452, 1), -1, true, true);
		World.spawnNPC(14688, new WorldTile(4689, 5455, 1), -1, true, true);
		World.spawnNPC(14688, new WorldTile(4690, 5458, 1), -1, true, true);
		World.spawnNPC(14688, new WorldTile(4694, 5459, 1), -1, true, true);
		World.spawnNPC(14688, new WorldTile(4689, 5463, 1), -1, true, true);
		World.spawnNPC(14688, new WorldTile(4691, 5473, 1), -1, true, true);
		World.spawnNPC(14688, new WorldTile(4687, 5474, 1), -1, true, true);
		World.spawnNPC(14688, new WorldTile(4688, 5478, 1), -1, true, true);
		World.spawnNPC(14688, new WorldTile(4692, 5477, 1), -1, true, true);
		World.spawnNPC(14698, new WorldTile(4694, 5484, 1), -1, true, true);
		World.spawnNPC(14698, new WorldTile(4693, 5488, 1), -1, true, true);
		World.spawnNPC(14698, new WorldTile(4697, 5487, 1), -1, true, true);
		World.spawnNPC(14688, new WorldTile(4709, 5487, 1), -1, true, true);
		World.spawnNPC(14688, new WorldTile(4714, 5486, 1), -1, true, true);
		World.spawnNPC(14688, new WorldTile(4711, 5482, 1), -1, true, true);
		World.spawnNPC(14698, new WorldTile(4718, 5476, 1), -1, true, true);
		World.spawnNPC(14698, new WorldTile(4714, 5474, 1), -1, true, true);
		World.spawnNPC(14698, new WorldTile(4719, 5470, 1), -1, true, true);
		World.spawnNPC(14698, new WorldTile(4630, 5446, 1), -1, true, true);
		World.spawnNPC(14698, new WorldTile(4625, 5454, 1), -1, true, true);
		World.spawnNPC(14698, new WorldTile(4629, 5456, 1), -1, true, true);
		World.spawnNPC(14698, new WorldTile(4623, 5458, 1), -1, true, true);
		World.spawnNPC(14698, new WorldTile(4627, 5461, 1), -1, true, true);
		World.spawnNPC(14688, new WorldTile(4625, 5475, 1), -1, true, true);
		World.spawnNPC(14688, new WorldTile(4628, 5478, 1), -1, true, true);
		World.spawnNPC(14688, new WorldTile(4627, 5482, 1), -1, true, true);
		World.spawnNPC(14688, new WorldTile(4623, 5483, 1), -1, true, true);
		World.spawnNPC(14698, new WorldTile(4638, 5484, 1), -1, true, true);
		World.spawnNPC(14698, new WorldTile(4635, 5487, 1), -1, true, true);
		World.spawnNPC(14688, new WorldTile(4656, 5486, 1), -1, true, true);
		World.spawnNPC(14688, new WorldTile(4658, 5491, 1), -1, true, true);
		World.spawnNPC(14688, new WorldTile(4660, 5488, 1), -1, true, true);
		World.spawnNPC(14698, new WorldTile(4651, 5482, 1), -1, true, true);
		World.spawnNPC(14698, new WorldTile(4649, 5478, 1), -1, true, true);
		World.spawnNPC(14698, new WorldTile(4653, 5474, 1), -1, true, true);
		World.spawnNPC(14698, new WorldTile(4656, 5478, 1), -1, true, true);
		World.spawnNPC(14698, new WorldTile(4655, 5467, 1), -1, true, true);
		World.spawnNPC(14698, new WorldTile(4659, 5467, 1), -1, true, true);
		World.spawnNPC(14698, new WorldTile(4656, 5464, 1), -1, true, true);
		World.spawnNPC(14698, new WorldTile(4653, 5452, 1), -1, true, true);
		World.spawnNPC(14698, new WorldTile(4648, 5451, 1), -1, true, true);
		World.spawnNPC(14698, new WorldTile(4649, 5446, 1), -1, true, true);
		World.spawnNPC(14698, new WorldTile(4653, 5445, 1), -1, true, true);
		World.spawnNPC(14700, new WorldTile(4655, 5433, 1), -1, true, true);
		World.spawnNPC(14700, new WorldTile(4652, 5431, 1), -1, true, true);
		World.spawnNPC(14700, new WorldTile(4655, 5425, 1), -1, true, true);
		World.spawnNPC(14700, new WorldTile(4658, 5429, 1), -1, true, true);
		World.spawnNPC(14698, new WorldTile(4652, 5412, 1), -1, true, true);
		World.spawnNPC(14698, new WorldTile(4658, 5411, 1), -1, true, true);
		World.spawnNPC(14698, new WorldTile(4655, 5406, 1), -1, true, true);
		World.spawnNPC(14688, new WorldTile(4653, 5398, 1), -1, true, true);
		World.spawnNPC(14688, new WorldTile(4657, 5395, 1), -1, true, true);
		World.spawnNPC(14688, new WorldTile(4655, 5390, 1), -1, true, true);
		World.spawnNPC(14688, new WorldTile(4651, 5392, 1), -1, true, true);
		World.spawnNPC(14688, new WorldTile(4641, 5388, 1), -1, true, true);
		World.spawnNPC(14688, new WorldTile(4636, 5390, 1), -1, true, true);
		World.spawnNPC(14688, new WorldTile(4636, 5395, 1), -1, true, true);
		World.spawnNPC(14688, new WorldTile(4642, 5394, 1), -1, true, true);
		World.spawnNPC(14690, new WorldTile(4629, 5391, 1), -1, true, true);
		World.spawnNPC(14690, new WorldTile(4625, 5394, 1), -1, true, true);
		World.spawnNPC(14688, new WorldTile(4629, 5403, 1), -1, true, true);
		World.spawnNPC(14688, new WorldTile(4626, 5406, 1), -1, true, true);
		World.spawnNPC(14688, new WorldTile(4632, 5407, 1), -1, true, true);
		World.spawnNPC(14688, new WorldTile(4628, 5410, 1), -1, true, true);
		World.spawnNPC(14698, new WorldTile(4629, 5418, 1), -1, true, true);
		World.spawnNPC(14698, new WorldTile(4632, 5420, 1), -1, true, true);
		World.spawnNPC(14698, new WorldTile(4630, 5423, 1), -1, true, true);
		World.spawnNPC(14688, new WorldTile(4624, 5429, 1), -1, true, true);
		World.spawnNPC(14688, new WorldTile(4628, 5432, 1), -1, true, true);
		World.spawnNPC(14688, new WorldTile(4623, 5436, 1), -1, true, true);
		//Polypore Dungeon Floor 4
		World.spawnNPC(14696, new WorldTile(4640, 5405, 0), -1, true, true);
		World.spawnNPC(14698, new WorldTile(4664, 5404, 0), -1, true, true);
		World.spawnNPC(14698, new WorldTile(4638, 5406, 0), -1, true, true);
		World.spawnNPC(14696, new WorldTile(4656, 5408, 0), -1, true, true);
		World.spawnNPC(14696, new WorldTile(4639, 5412, 0), -1, true, true);
		World.spawnNPC(14698, new WorldTile(4657, 5412, 0), -1, true, true);
		World.spawnNPC(14698, new WorldTile(4635, 5413, 0), -1, true, true);
		World.spawnNPC(14698, new WorldTile(4649, 5414, 0), -1, true, true);
		World.spawnNPC(14698, new WorldTile(4658, 5415, 0), -1, true, true);
		World.spawnNPC(14696, new WorldTile(4651, 5423, 0), -1, true, true);
		World.spawnNPC(14696, new WorldTile(4641, 5424, 0), -1, true, true);
		World.spawnNPC(14696, new WorldTile(4659, 5426, 0), -1, true, true);
		World.spawnNPC(14698, new WorldTile(4640, 5431, 0), -1, true, true);
		World.spawnNPC(14696, new WorldTile(4654, 5438, 0), -1, true, true);
		World.spawnNPC(14696, new WorldTile(4657, 5439, 0), -1, true, true);
		World.spawnNPC(14696, new WorldTile(4636, 5432, 0), -1, true, true);
		World.spawnNPC(14696, new WorldTile(4637, 5435, 0), -1, true, true);
		World.spawnNPC(14696, new WorldTile(4637, 5448, 0), -1, true, true);
		World.spawnNPC(14696, new WorldTile(4653, 5452, 0), -1, true, true);
		World.spawnNPC(14698, new WorldTile(4654, 5450, 0), -1, true, true);
		World.spawnNPC(14696, new WorldTile(4639, 5461, 0), -1, true, true);
		World.spawnNPC(14698, new WorldTile(4634, 5443, 0), -1, true, true);
		World.spawnNPC(14698, new WorldTile(4661, 5462, 0), -1, true, true);
		World.spawnNPC(14696, new WorldTile(4634, 5451, 0), -1, true, true);
		World.spawnNPC(14698, new WorldTile(4632, 5457, 0), -1, true, true);
		World.spawnNPC(14696, new WorldTile(4647, 5467, 0), -1, true, true);
		World.spawnNPC(14698, new WorldTile(4661, 5463, 0), -1, true, true);
		World.spawnNPC(14698, new WorldTile(4633, 5460, 0), -1, true, true);
		World.spawnNPC(14698, new WorldTile(4647, 5471, 0), -1, true, true);
		World.spawnNPC(14698, new WorldTile(4655, 5471, 0), -1, true, true);
		World.spawnNPC(14696, new WorldTile(4656, 5473, 0), -1, true, true);
		World.spawnNPC(14698, new WorldTile(4657, 5476, 0), -1, true, true);
		World.spawnNPC(14696, new WorldTile(4654, 5479, 0), -1, true, true);
		World.spawnNPC(14696, new WorldTile(4658, 5484, 0), -1, true, true);
		World.spawnNPC(14698, new WorldTile(4641, 5484, 0), -1, true, true);
		World.spawnNPC(14698, new WorldTile(4651, 5484, 0), -1, true, true);
		World.spawnNPC(14696, new WorldTile(4649, 5487, 0), -1, true, true);
		World.spawnNPC(14698, new WorldTile(4659, 5486, 0), -1, true, true);
		World.spawnNPC(14698, new WorldTile(4640, 5485, 0), -1, true, true);
		World.spawnNPC(14696, new WorldTile(4639, 5483, 0), -1, true, true);
		World.spawnNPC(14696, new WorldTile(4637, 5487, 0), -1, true, true);
		World.spawnNPC(14698, new WorldTile(4635, 5487, 0), -1, true, true);
		World.spawnNPC(14696, new WorldTile(4634, 5485, 0), -1, true, true);
		World.spawnNPC(14696, new WorldTile(4628, 5485, 0), -1, true, true);
		World.spawnNPC(14698, new WorldTile(4623, 5475, 0), -1, true, true);
		World.spawnNPC(14696, new WorldTile(4624, 5479, 0), -1, true, true);
		World.spawnNPC(14696, new WorldTile(4624, 5482, 0), -1, true, true);
		World.spawnNPC(14698, new WorldTile(4618, 5470, 0), -1, true, true);
		World.spawnNPC(14698, new WorldTile(4619, 5470, 0), -1, true, true);
		World.spawnNPC(14698, new WorldTile(4626, 5470, 0), -1, true, true);
		World.spawnNPC(14696, new WorldTile(4629, 5469, 0), -1, true, true);
		World.spawnNPC(14698, new WorldTile(4630, 5465, 0), -1, true, true);
		World.spawnNPC(14696, new WorldTile(4628, 5465, 0), -1, true, true);
		World.spawnNPC(14696, new WorldTile(4624, 5461, 0), -1, true, true);
		World.spawnNPC(14698, new WorldTile(4622, 5460, 0), -1, true, true);
		World.spawnNPC(14696, new WorldTile(4627, 5460, 0), -1, true, true);
		World.spawnNPC(14696, new WorldTile(4629, 5443, 0), -1, true, true);
		World.spawnNPC(14698, new WorldTile(4621, 5437, 0), -1, true, true);
		World.spawnNPC(14696, new WorldTile(4633, 5434, 0), -1, true, true);
		World.spawnNPC(14696, new WorldTile(4626, 5429, 0), -1, true, true);
		World.spawnNPC(14696, new WorldTile(4703, 5454, 0), -1, true, true);
		World.spawnNPC(14696, new WorldTile(4708, 5459, 0), -1, true, true);
		World.spawnNPC(14696, new WorldTile(4702, 5458, 0), -1, true, true);
		World.spawnNPC(14696, new WorldTile(4707, 5463, 0), -1, true, true);
		World.spawnNPC(14696, new WorldTile(4700, 5464, 0), -1, true, true);
		World.spawnNPC(14696, new WorldTile(4705, 5469, 0), -1, true, true);
		World.spawnNPC(14698, new WorldTile(4700, 5472, 0), -1, true, true);
		World.spawnNPC(14696, new WorldTile(4705, 5477, 0), -1, true, true);
		World.spawnNPC(14696, new WorldTile(4699, 5476, 0), -1, true, true);
		World.spawnNPC(14696, new WorldTile(4704, 5481, 0), -1, true, true);
		World.spawnNPC(14696, new WorldTile(4693, 5478, 0), -1, true, true);
		World.spawnNPC(14698, new WorldTile(4698, 5483, 0), -1, true, true);
		World.spawnNPC(14696, new WorldTile(4690, 5471, 0), -1, true, true);
		World.spawnNPC(14696, new WorldTile(4695, 5476, 0), -1, true, true);
		World.spawnNPC(14696, new WorldTile(4689, 5469, 0), -1, true, true);
		World.spawnNPC(14696, new WorldTile(4694, 5474, 0), -1, true, true);
		World.spawnNPC(14696, new WorldTile(4689, 5465, 0), -1, true, true);
		World.spawnNPC(14698, new WorldTile(4694, 5470, 0), -1, true, true);
		World.spawnNPC(14696, new WorldTile(4690, 5455, 0), -1, true, true);
		World.spawnNPC(14696, new WorldTile(4695, 5460, 0), -1, true, true);
		World.spawnNPC(14696, new WorldTile(4699, 5450, 0), -1, true, true);
		World.spawnNPC(14698, new WorldTile(4704, 5455, 0), -1, true, true);
		World.spawnNPC(14696, new WorldTile(4710, 5455, 0), -1, true, true);
		World.spawnNPC(14698, new WorldTile(4715, 5460, 0), -1, true, true);
		World.spawnNPC(14696, new WorldTile(4712, 5466, 0), -1, true, true);
		World.spawnNPC(14696, new WorldTile(4717, 5471, 0), -1, true, true);
		World.spawnNPC(14696, new WorldTile(4714, 5474, 0), -1, true, true);
		World.spawnNPC(14696, new WorldTile(4719, 5479, 0), -1, true, true);
		World.spawnNPC(14696, new WorldTile(4714, 5482, 0), -1, true, true);
		World.spawnNPC(14698, new WorldTile(4719, 5487, 0), -1, true, true);
		World.spawnNPC(14696, new WorldTile(4706, 5491, 0), -1, true, true);
		World.spawnNPC(14698, new WorldTile(4711, 5496, 0), -1, true, true);
		World.spawnNPC(14696, new WorldTile(4699, 5482, 0), -1, true, true);
		World.spawnNPC(14696, new WorldTile(4704, 5487, 0), -1, true, true);
		World.spawnNPC(14696, new WorldTile(4687, 5479, 0), -1, true, true);
		World.spawnNPC(14696, new WorldTile(4692, 5484, 0), -1, true, true);
		World.spawnNPC(14698, new WorldTile(4692, 5463, 0), -1, true, true);
		World.spawnNPC(14696, new WorldTile(4697, 5468, 0), -1, true, true);
		World.spawnNPC(14696, new WorldTile(4686, 5461, 0), -1, true, true);
		World.spawnNPC(14696, new WorldTile(4691, 5466, 0), -1, true, true);
		//Jadinko Lair
		World.spawnNPC(13820, new WorldTile(3016, 9259, 0), -1, true, true);
		World.spawnNPC(13820, new WorldTile(3016, 9259, 0), -1, true, true);
		World.spawnNPC(13820, new WorldTile(3016, 9259, 0), -1, true, true);
		World.spawnNPC(13820, new WorldTile(3023, 9255, 0), -1, true, true);
		World.spawnNPC(13820, new WorldTile(3023, 9255, 0), -1, true, true);
		World.spawnNPC(13820, new WorldTile(3023, 9255, 0), -1, true, true);
		World.spawnNPC(13820, new WorldTile(3017, 9253, 0), -1, true, true);
		World.spawnNPC(13820, new WorldTile(3017, 9253, 0), -1, true, true);
		World.spawnNPC(13821, new WorldTile(3044, 9272, 0), -1, true, true);
		World.spawnNPC(13821, new WorldTile(3044, 9272, 0), -1, true, true);
		World.spawnNPC(13821, new WorldTile(3044, 9272, 0), -1, true, true);
		World.spawnNPC(13821, new WorldTile(3043, 9264, 0), -1, true, true);
		World.spawnNPC(13821, new WorldTile(3043, 9264, 0), -1, true, true);
		World.spawnNPC(13821, new WorldTile(3043, 9264, 0), -1, true, true);
		World.spawnNPC(13821, new WorldTile(3043, 9264, 0), -1, true, true);
		World.spawnNPC(13821, new WorldTile(3043, 9264, 0), -1, true, true);
		World.spawnNPC(13821, new WorldTile(3043, 9257, 0), -1, true, true);
		World.spawnNPC(13821, new WorldTile(3043, 9257, 0), -1, true, true);
		World.spawnNPC(13822, new WorldTile(3060, 9250, 0), -1, true, true);
		World.spawnNPC(13822, new WorldTile(3060, 9250, 0), -1, true, true);
		World.spawnNPC(13822, new WorldTile(3060, 9250, 0), -1, true, true);
		World.spawnNPC(13822, new WorldTile(3060, 9244, 0), -1, true, true);
		World.spawnNPC(13822, new WorldTile(3060, 9237, 0), -1, true, true);
		World.spawnNPC(13822, new WorldTile(3060, 9237, 0), -1, true, true);
		World.spawnNPC(13822, new WorldTile(3060, 9237, 0), -1, true, true);
		World.spawnNPC(13822, new WorldTile(3060, 9237, 0), -1, true, true);
		World.spawnNPC(13822, new WorldTile(3060, 9237, 0), -1, true, true);
		World.spawnNPC(9176, new WorldTile(3042, 9236, 0), -1, true, true);
		World.spawnNPC(9176, new WorldTile(3023, 9233, 0), -1, true, true);
		// Curse Bearers
		World.spawnNPC(10127, new WorldTile(3222, 3684, 0), -1, true, true);
		World.spawnNPC(10127, new WorldTile(3222, 3684, 0), -1, true, true);
		World.spawnNPC(10127, new WorldTile(3222, 3684, 0), -1, true, true);
		World.spawnNPC(10127, new WorldTile(3222, 3684, 0), -1, true, true);
		World.spawnNPC(10127, new WorldTile(3222, 3684, 0), -1, true, true);
		World.spawnNPC(10127, new WorldTile(3222, 3684, 0), -1, true, true);
		World.spawnNPC(10127, new WorldTile(3222, 3684, 0), -1, true, true);
		World.spawnNPC(10127, new WorldTile(3228, 3675, 0), -1, true, true);
		World.spawnNPC(10127, new WorldTile(3228, 3675, 0), -1, true, true);
		World.spawnNPC(10127, new WorldTile(3228, 3675, 0), -1, true, true);
		World.spawnNPC(10127, new WorldTile(3228, 3675, 0), -1, true, true);
		World.spawnNPC(10127, new WorldTile(3228, 3675, 0), -1, true, true);
		World.spawnNPC(10127, new WorldTile(3228, 3675, 0), -1, true, true);
		World.spawnNPC(10127, new WorldTile(3228, 3675, 0), -1, true, true);
		World.spawnNPC(10127, new WorldTile(3233, 3687, 0), -1, true, true);
		World.spawnNPC(10127, new WorldTile(3233, 3687, 0), -1, true, true);
		World.spawnNPC(10127, new WorldTile(3233, 3687, 0), -1, true, true);
		World.spawnNPC(10127, new WorldTile(3233, 3687, 0), -1, true, true);
		World.spawnNPC(10127, new WorldTile(3233, 3687, 0), -1, true, true);
		World.spawnNPC(10127, new WorldTile(3233, 3687, 0), -1, true, true);
		World.spawnNPC(10127, new WorldTile(3233, 3687, 0), -1, true, true);
		World.spawnNPC(10127, new WorldTile(3233, 3687, 0), -1, true, true);
		//lummy start area
		World.spawnNPC(2, new WorldTile(3233, 3216, 0), -1, true, true);
		World.spawnNPC(2, new WorldTile(3233, 3222, 0), -1, true, true);
		World.spawnNPC(4, new WorldTile(3238, 3204, 0), -1, true, true);
		World.spawnNPC(73, new WorldTile(3246, 3201, 0), -1, true, true);
		World.spawnNPC(73, new WorldTile(3239, 3194, 0), -1, true, true);
		World.spawnNPC(103, new WorldTile(3249, 3193, 0), -1, true, true);
		World.spawnNPC(73, new WorldTile(3241, 3186, 0), -1, true, true);
		World.spawnNPC(3264, new WorldTile(3254, 3239, 0), -1, true, true);
		World.spawnNPC(3264, new WorldTile(3254, 3239, 0), -1, true, true);
		World.spawnNPC(3264, new WorldTile(3254, 3239, 0), -1, true, true);
		World.spawnNPC(3264, new WorldTile(3254, 3239, 0), -1, true, true);
		World.spawnNPC(3264, new WorldTile(3254, 3239, 0), -1, true, true);
		World.spawnNPC(3264, new WorldTile(3254, 3239, 0), -1, true, true);
		World.spawnNPC(3264, new WorldTile(3254, 3239, 0), -1, true, true);
		World.spawnNPC(3264, new WorldTile(3246, 3246, 0), -1, true, true);
		World.spawnNPC(3264, new WorldTile(3246, 3246, 0), -1, true, true);
		World.spawnNPC(3264, new WorldTile(3246, 3246, 0), -1, true, true);
		World.spawnNPC(3264, new WorldTile(3246, 3246, 0), -1, true, true);
		World.spawnNPC(3264, new WorldTile(3246, 3246, 0), -1, true, true);
		World.spawnNPC(3264, new WorldTile(3246, 3246, 0), -1, true, true);
		World.spawnNPC(3264, new WorldTile(3258, 3247, 0), -1, true, true);
		World.spawnNPC(3264, new WorldTile(3262, 3220, 0), -1, true, true);
		World.spawnNPC(81, new WorldTile(3259, 3263, 0), -1, true, true);
		World.spawnNPC(81, new WorldTile(3259, 3263, 0), -1, true, true);
		World.spawnNPC(81, new WorldTile(3259, 3263, 0), -1, true, true);
		World.spawnNPC(81, new WorldTile(3259, 3263, 0), -1, true, true);
		World.spawnNPC(81, new WorldTile(3259, 3263, 0), -1, true, true);
		World.spawnNPC(81, new WorldTile(3258, 3280, 0), -1, true, true);
		World.spawnNPC(81, new WorldTile(3258, 3280, 0), -1, true, true);
		World.spawnNPC(81, new WorldTile(3258, 3280, 0), -1, true, true);
		World.spawnNPC(81, new WorldTile(3251, 3289, 0), -1, true, true);
		World.spawnNPC(81, new WorldTile(3251, 3289, 0), -1, true, true);
		World.spawnNPC(81, new WorldTile(3251, 3289, 0), -1, true, true);
		World.spawnNPC(81, new WorldTile(3259, 3290, 0), -1, true, true);
		World.spawnNPC(81, new WorldTile(3259, 3290, 0), -1, true, true);
		World.spawnNPC(81, new WorldTile(3259, 3290, 0), -1, true, true);
		World.spawnNPC(41, new WorldTile(3230, 3299, 0), -1, true, true);
		World.spawnNPC(41, new WorldTile(3230, 3299, 0), -1, true, true);
		World.spawnNPC(41, new WorldTile(3230, 3299, 0), -1, true, true);
		World.spawnNPC(41, new WorldTile(3230, 3299, 0), -1, true, true);
		World.spawnNPC(41, new WorldTile(3230, 3299, 0), -1, true, true);
		World.spawnNPC(41, new WorldTile(3230, 3299, 0), -1, true, true);
		World.spawnNPC(41, new WorldTile(3230, 3299, 0), -1, true, true);
		World.spawnNPC(7, new WorldTile(3227, 3292, 0), -1, true, true);
		World.spawnNPC(86, new WorldTile(3262, 3351, 0), -1, true, true);
		World.spawnNPC(86, new WorldTile(3262, 3351, 0), -1, true, true);
		World.spawnNPC(86, new WorldTile(3262, 3351, 0), -1, true, true);
		World.spawnNPC(86, new WorldTile(3262, 3351, 0), -1, true, true);
		World.spawnNPC(86, new WorldTile(3262, 3351, 0), -1, true, true);
		World.spawnNPC(86, new WorldTile(3262, 3351, 0), -1, true, true);
		World.spawnNPC(86, new WorldTile(3262, 3351, 0), -1, true, true);
		World.spawnNPC(86, new WorldTile(3262, 3351, 0), -1, true, true);
		World.spawnNPC(133, new WorldTile(3280, 3347, 0), -1, true, true);
		World.spawnNPC(133, new WorldTile(3291, 3342, 0), -1, true, true);
		World.spawnNPC(1195, new WorldTile(3292, 3351, 0), -1, true, true);
		World.spawnNPC(81, new WorldTile(3160, 3319, 0), -1, true, true);
		World.spawnNPC(81, new WorldTile(3160, 3319, 0), -1, true, true);
		World.spawnNPC(81, new WorldTile(3160, 3319, 0), -1, true, true);
		World.spawnNPC(81, new WorldTile(3160, 3319, 0), -1, true, true);
		World.spawnNPC(81, new WorldTile(3170, 3323, 0), -1, true, true);
		World.spawnNPC(81, new WorldTile(3170, 3323, 0), -1, true, true);
		World.spawnNPC(81, new WorldTile(3170, 3323, 0), -1, true, true);
		World.spawnNPC(81, new WorldTile(3201, 3315, 0), -1, true, true);
		World.spawnNPC(81, new WorldTile(3201, 3315, 0), -1, true, true);
		World.spawnNPC(81, new WorldTile(3201, 3315, 0), -1, true, true);
		World.spawnNPC(81, new WorldTile(3201, 3315, 0), -1, true, true);
		World.spawnNPC(81, new WorldTile(3187, 3322, 0), -1, true, true);
		World.spawnNPC(81, new WorldTile(3187, 3322, 0), -1, true, true);
		World.spawnNPC(81, new WorldTile(3187, 3322, 0), -1, true, true);
		World.spawnNPC(3264, new WorldTile(3171, 3276, 0), -1, true, true);
		World.spawnNPC(3264, new WorldTile(3171, 3276, 0), -1, true, true);
		World.spawnNPC(3264, new WorldTile(3171, 3276, 0), -1, true, true);
		World.spawnNPC(3264, new WorldTile(3171, 3276, 0), -1, true, true);
		World.spawnNPC(3264, new WorldTile(3171, 3276, 0), -1, true, true);
		World.spawnNPC(41, new WorldTile(3185, 3278, 0), -1, true, true);
		World.spawnNPC(41, new WorldTile(3185, 3278, 0), -1, true, true);
		World.spawnNPC(41, new WorldTile(3185, 3278, 0), -1, true, true);
		World.spawnNPC(41, new WorldTile(3185, 3278, 0), -1, true, true);
		World.spawnNPC(41, new WorldTile(3185, 3278, 0), -1, true, true);
		World.spawnNPC(41, new WorldTile(3185, 3278, 0), -1, true, true);
		World.spawnNPC(7, new WorldTile(3190, 3273, 0), -1, true, true);
		World.spawnNPC(3264, new WorldTile(3214, 3280, 0), -1, true, true);
		World.spawnNPC(3264, new WorldTile(3214, 3280, 0), -1, true, true);
		World.spawnNPC(3264, new WorldTile(3214, 3280, 0), -1, true, true);
		World.spawnNPC(3264, new WorldTile(3214, 3280, 0), -1, true, true);
		World.spawnNPC(2, new WorldTile(3220, 3248, 0), -1, true, true);
		World.spawnNPC(2, new WorldTile(3220, 3248, 0), -1, true, true);
		World.spawnNPC(6, new WorldTile(3220, 3248, 0), -1, true, true);
		World.spawnNPC(3264, new WorldTile(3186, 3240, 0), -1, true, true);
		World.spawnNPC(3264, new WorldTile(3186, 3240, 0), -1, true, true);
		World.spawnNPC(3264, new WorldTile(3186, 3240, 0), -1, true, true);
		World.spawnNPC(3264, new WorldTile(3186, 3240, 0), -1, true, true);
		World.spawnNPC(3264, new WorldTile(3186, 3240, 0), -1, true, true);
		World.spawnNPC(3264, new WorldTile(3186, 3240, 0), -1, true, true);
		World.spawnNPC(3264, new WorldTile(3181, 3252, 0), -1, true, true);
		World.spawnNPC(3264, new WorldTile(3181, 3252, 0), -1, true, true);
		World.spawnNPC(3264, new WorldTile(3181, 3252, 0), -1, true, true);
		World.spawnNPC(3264, new WorldTile(3181, 3252, 0), -1, true, true);
		World.spawnNPC(3264, new WorldTile(3181, 3252, 0), -1, true, true);
		World.spawnNPC(3264, new WorldTile(3178, 3235, 0), -1, true, true);
		World.spawnNPC(3264, new WorldTile(3178, 3235, 0), -1, true, true);
		World.spawnNPC(3264, new WorldTile(3178, 3235, 0), -1, true, true);
		World.spawnNPC(3264, new WorldTile(3178, 3235, 0), -1, true, true);
		World.spawnNPC(3264, new WorldTile(3178, 3235, 0), -1, true, true);
		World.spawnNPC(3264, new WorldTile(3178, 3235, 0), -1, true, true);
		
		//Hope devourer
		World.spawnNPC(12900, new WorldTile(1909, 5364, 0), -1, true, true);
		
		//smithing dungeon
		World.spawnNPC(5247, new WorldTile(1774, 5360, 0), -1, true, true);
		World.spawnNPC(7160, new WorldTile(1759, 5357, 0), -1, true, true);
		World.spawnNPC(7160, new WorldTile(1775, 5352, 0), -1, true, true);
		World.spawnNPC(7160, new WorldTile(1772, 5337, 0), -1, true, true);
		World.spawnNPC(7160, new WorldTile(1766, 5328, 0), -1, true, true);
		World.spawnNPC(7160, new WorldTile(1751, 5338, 0), -1, true, true);
		World.spawnNPC(7159, new WorldTile(1742, 5342, 0), -1, true, true);
		World.spawnNPC(7159, new WorldTile(1742, 5359, 0), -1, true, true);
		World.spawnNPC(7159, new WorldTile(1764, 5338, 0), -1, true, true);
		World.spawnNPC(7159, new WorldTile(1778, 5326, 0), -1, true, true);
		World.spawnNPC(7158, new WorldTile(1773, 5346, 0), -1, true, true);
		World.spawnNPC(7158, new WorldTile(1773, 5346, 0), -1, true, true);
		World.spawnNPC(7158, new WorldTile(1773, 5346, 0), -1, true, true);
		World.spawnNPC(7158, new WorldTile(1766, 5358, 0), -1, true, true);
		World.spawnNPC(7158, new WorldTile(1766, 5358, 0), -1, true, true);
		World.spawnNPC(7158, new WorldTile(1766, 5358, 0), -1, true, true);
		World.spawnNPC(7158, new WorldTile(1766, 5358, 0), -1, true, true);
		World.spawnNPC(7158, new WorldTile(1748, 5327, 0), -1, true, true);
		World.spawnNPC(7158, new WorldTile(1748, 5327, 0), -1, true, true);
		World.spawnNPC(7158, new WorldTile(1748, 5327, 0), -1, true, true);
		World.spawnNPC(7158, new WorldTile(1748, 5327, 0), -1, true, true);
		World.spawnNPC(7158, new WorldTile(1748, 5327, 0), -1, true, true);
		//Melzar's Maze
		World.spawnNPC(4355, new WorldTile(2934, 3254, 0), -1, true, true);
		World.spawnNPC(4355, new WorldTile(2929, 3252, 0), -1, true, true);
		World.spawnNPC(4355, new WorldTile(2929, 3245, 0), -1, true, true);
		World.spawnNPC(4355, new WorldTile(2936, 3247, 0), -1, true, true);
		World.spawnNPC(8783, new WorldTile(2928, 3248, 1), -1, true, true);
		World.spawnNPC(8783, new WorldTile(2928, 3248, 1), -1, true, true);
		World.spawnNPC(8783, new WorldTile(2928, 3253, 1), -1, true, true);
		World.spawnNPC(8783, new WorldTile(2923, 3254, 1), -1, true, true);
		World.spawnNPC(192, new WorldTile(2931, 3256, 2), -1, true, true);
		World.spawnNPC(192, new WorldTile(2928, 3252, 2), -1, true, true);
		World.spawnNPC(192, new WorldTile(2928, 3252, 2), -1, true, true);
		World.spawnNPC(192, new WorldTile(2925, 3253, 2), -1, true, true);
		World.spawnNPC(192, new WorldTile(2925, 3253, 2), -1, true, true);
		World.spawnNPC(192, new WorldTile(2925, 3253, 2), -1, true, true);
		World.spawnNPC(4910, new WorldTile(2936, 9651, 0), -1, true, true);
		World.spawnNPC(4910, new WorldTile(2936, 9651, 0), -1, true, true);
		World.spawnNPC(4910, new WorldTile(2936, 9651, 0), -1, true, true);
		//Wildy Worms
		World.spawnNPC(3334, new WorldTile(3273, 3757, 0), -1, true, true);
		World.spawnNPC(3334, new WorldTile(3269, 3742, 0), -1, true, true);
		World.spawnNPC(3334, new WorldTile(3282, 3738, 0), -1, true, true);
		World.spawnNPC(3334, new WorldTile(3287, 3750, 0), -1, true, true);
		World.spawnNPC(3334, new WorldTile(3284, 3774, 0), -1, true, true);
		World.spawnNPC(3334, new WorldTile(3277, 3778, 0), -1, true, true);
		World.spawnNPC(3334, new WorldTile(3269, 3774, 0), -1, true, true);
		World.spawnNPC(3334, new WorldTile(3263, 3785, 0), -1, true, true);
		World.spawnNPC(3334, new WorldTile(3272, 3788, 0), -1, true, true);
		
		//Object spawning
		//World.spawnObject(new WorldObject(47120, 10, -1, 2844, 10222, 0), true); //zaros
		World.spawnObject(new WorldObject(409, 10, 1, 2834, 10210, 0), true);
		World.spawnObject(new WorldObject(409, 10, -4, 2988, 4371, 2), true);
		//World.spawnObject(new WorldObject(2079, 10, -1, 2837, 10210, 0), true); //Crystal chest
		//World.spawnObject(new WorldObject(6282, 10, -2, 2855, 10191, 0), true); //clanwars
		World.spawnObject(new WorldObject(66007, 10, 0, 2679, 2660, 0), true);
		
		//XuanTent
		World.spawnObject(new WorldObject(11448, 10, -1, 2645, 2668, 0), true);
		//Thieving stalls
		World.spawnObject(new WorldObject(4874, 10, -2, 2649, 2661, 0), true);
		World.spawnObject(new WorldObject(4875, 10, -2, 2650, 2661, 0), true);
		World.spawnObject(new WorldObject(4876, 10, -2, 2651, 2661, 0), true);
		World.spawnObject(new WorldObject(4877, 10, -2, 2652, 2661, 0), true);
		World.spawnObject(new WorldObject(4878, 10, -2, 2653, 2661, 0), true);
		
		//Tzhaar
		World.spawnNPC(2625, new WorldTile(2617, 5132, 0), -1, false);
		World.spawnNPC(2614, new WorldTile(4666, 5082, 0), -1, false);
		
		//runite ores
		World.spawnObject(new WorldObject(14860, 10, -3, 1595, 4505, 0), true);
		World.spawnObject(new WorldObject(14860, 10, -3, 1595, 4506, 0), true);
		World.spawnObject(new WorldObject(14860, 10, -3, 1595, 4507, 0), true);
		World.spawnObject(new WorldObject(14860, 10, -3, 1595, 4508, 0), true);
		World.spawnObject(new WorldObject(14860, 10, -3, 1595, 4509, 0), true);
		//magic tree
		World.spawnObject(new WorldObject(1306, 10, 0, 1595, 4503, 0), true);
		World.spawnObject(new WorldObject(1306, 10, 0, 1595, 4510, 0), true);
		//Furnace and Anvil
		World.spawnObject(new WorldObject(11010, 10, -4, 1599, 4512, 0), true);
		World.spawnObject(new WorldObject(2783, 10, -4, 1603, 4512, 0), true);
		
        //Fishing Spots at Home
        World.spawnNPC(327, new WorldTile(2604, 3419, 0), -1, true, true);
        World.spawnNPC(6267, new WorldTile(2605, 3420, 0), -1, true, true);
        World.spawnNPC(312, new WorldTile(2605, 3421, 0), -1, true, true);
        World.spawnNPC(313, new WorldTile(2604, 3422, 0), -1, true, true);
        World.spawnNPC(952, new WorldTile(2603, 3422, 0), -1, true, true);
        World.spawnNPC(327, new WorldTile(2604, 3423, 0), -1, true, true);
        World.spawnNPC(6267, new WorldTile(2605, 3424, 0), -1, true, true);
        World.spawnNPC(312, new WorldTile(2605, 3425, 0), -1, true, true);
        World.spawnNPC(327, new WorldTile(2599, 3419, 0), -1, true, true);
        World.spawnNPC(6267, new WorldTile(2598, 3422, 0), -1, true, true);
        //World.spawnNPC(8864, new WorldTile(2590, 3419, 0), -1, true, true);
        World.spawnNPC(8841, new WorldTile(2603, 3426, 0), -1, true, true);
        World.spawnNPC(8842, new WorldTile(2604, 3426, 0), -1, true, true);
		
		//Fishing Spot
	    //World.spawnObject(new WorldObject(36786, 10, 2, 2587, 3422, 0), true);
                            
        //Underground Summoning Area NPC
        World.spawnNPC(6970, new WorldTile(2207, 5345, 0), -1, true, true);//Pikkupstix (summoning shops)
		//END OF SHIT ADDED 5/22/2014
		
		//RSMV CHARACTERS
		World.spawnNPC(7929, new WorldTile(2591, 3421, 0), 0, false, EntityDirection.SOUTH);//Lumbridge Sage
		World.spawnNPC(598, new WorldTile(2588, 3420, 0), 0, false, EntityDirection.EAST);//Hairdresser
		World.spawnNPC(548, new WorldTile(2590, 3418, 0), 0, false, EntityDirection.NORTH);//Thessalia
		World.spawnNPC(2676, new WorldTile(2588, 3421, 0), 0, false, EntityDirection.EAST);//MakeOver Mage
		World.spawnNPC(3709, new WorldTile(2593, 3418, 0), 0, false, EntityDirection.NORTHWEST);//Mr. Ex
		World.spawnNPC(1301, new WorldTile(2590, 3422, 0), 0, false, EntityDirection.WEST);//Yrsa
		World.spawnNPC(8726, new WorldTile(2593, 3421, 0), 0, false, EntityDirection.SOUTH);//Agility Assistant
		World.spawnNPC(6715, new WorldTile(2584, 3416, 0), 0, false, EntityDirection.SOUTHEAST);//Estate
		World.spawnNPC(970, new WorldTile(2590, 3426, 0), 0, false, EntityDirection.SOUTH);//Diango
		//World.spawnNPC(, new WorldTile(2835, 10211, 0), 0, false, EntityDirection.EAST);
	
		//WATTS SKINNY
		World.spawnNPC(3201, new WorldTile(2673, 5211, 2), -1, true, true);
		World.spawnNPC(3201, new WorldTile(2670, 5212, 2), -1, true, true);
		World.spawnNPC(3201, new WorldTile(2665, 5215, 2), -1, true, true);
		World.spawnNPC(3201, new WorldTile(2661, 5223, 2), -1, true, true);
		World.spawnNPC(3201, new WorldTile(2648, 5219, 2), -1, true, true);
		World.spawnNPC(3201, new WorldTile(2643, 5215, 2), -1, true, true);
		World.spawnNPC(3201, new WorldTile(2647, 5223, 2), -1, true, true);
		World.spawnNPC(3201, new WorldTile(2660, 5298, 2), -1, true, true);
		World.spawnNPC(3201, new WorldTile(2656, 5209, 2), -1, true, true);
		World.spawnNPC(3201, new WorldTile(2646, 5197, 2), -1, true, true);
		World.spawnNPC(3201, new WorldTile(2641, 5205, 2), -1, true, true);
		
		World.spawnNPC(3201, new WorldTile(2657, 5207, 2), -1, true, true);
		World.spawnNPC(3201, new WorldTile(2655, 5211, 2), -1, true, true);
		World.spawnNPC(3201, new WorldTile(2655, 5217, 2), -1, true, true);
		World.spawnNPC(3201, new WorldTile(2654, 5218, 2), -1, true, true);
		
		World.spawnNPC(3201, new WorldTile(2665, 5202, 2), -1, true, true);
		World.spawnNPC(3201, new WorldTile(2661, 5200, 2), -1, true, true);
		World.spawnNPC(3201, new WorldTile(2665, 5197, 2), -1, true, true);
		World.spawnNPC(3201, new WorldTile(2669, 5203, 2), -1, true, true);
		
		//WATTS BUFF
		World.spawnNPC(3202, new WorldTile(2673, 5211, 2), -1, true, true);
		World.spawnNPC(3202, new WorldTile(2670, 5212, 2), -1, true, true);
		World.spawnNPC(3202, new WorldTile(2665, 5215, 2), -1, true, true);
		World.spawnNPC(3202, new WorldTile(2661, 5223, 2), -1, true, true);
		World.spawnNPC(3202, new WorldTile(2648, 5219, 2), -1, true, true);
		World.spawnNPC(3202, new WorldTile(2643, 5215, 2), -1, true, true);
		World.spawnNPC(3202, new WorldTile(2647, 5223, 2), -1, true, true);
		World.spawnNPC(3202, new WorldTile(2660, 5298, 2), -1, true, true);
		World.spawnNPC(3202, new WorldTile(2656, 5209, 2), -1, true, true);
		World.spawnNPC(3202, new WorldTile(2646, 5197, 2), -1, true, true);
		World.spawnNPC(3202, new WorldTile(2641, 5205, 2), -1, true, true);
		
		World.spawnNPC(3202, new WorldTile(2657, 5207, 2), -1, true, true);
		World.spawnNPC(3202, new WorldTile(2655, 5211, 2), -1, true, true);
		World.spawnNPC(3202, new WorldTile(2655, 5217, 2), -1, true, true);
		World.spawnNPC(3202, new WorldTile(2654, 5218, 2), -1, true, true);
		
		World.spawnNPC(3202, new WorldTile(2665, 5202, 2), -1, true, true);
		World.spawnNPC(3202, new WorldTile(2661, 5200, 2), -1, true, true);
		World.spawnNPC(3202, new WorldTile(2665, 5197, 2), -1, true, true);
		World.spawnNPC(3202, new WorldTile(2669, 5203, 2), -1, true, true);
		
		//WATTS STRAN
		World.spawnNPC(3203, new WorldTile(2673, 5211, 2), -1, true, true);
		World.spawnNPC(3203, new WorldTile(2670, 5212, 2), -1, true, true);
		World.spawnNPC(3203, new WorldTile(2665, 5215, 2), -1, true, true);
		World.spawnNPC(3203, new WorldTile(2661, 5223, 2), -1, true, true);
		World.spawnNPC(3203, new WorldTile(2648, 5219, 2), -1, true, true);
		World.spawnNPC(3203, new WorldTile(2643, 5215, 2), -1, true, true);
		World.spawnNPC(3203, new WorldTile(2647, 5223, 2), -1, true, true);
		World.spawnNPC(3203, new WorldTile(2660, 5298, 2), -1, true, true);
		World.spawnNPC(3203, new WorldTile(2656, 5209, 2), -1, true, true);
		World.spawnNPC(3203, new WorldTile(2646, 5197, 2), -1, true, true);
		World.spawnNPC(3203, new WorldTile(2641, 5205, 2), -1, true, true);
		
		World.spawnNPC(3203, new WorldTile(2657, 5207, 2), -1, true, true);
		World.spawnNPC(3203, new WorldTile(2655, 5211, 2), -1, true, true);
		World.spawnNPC(3203, new WorldTile(2655, 5217, 2), -1, true, true);
		World.spawnNPC(3203, new WorldTile(2654, 5218, 2), -1, true, true);
		
		World.spawnNPC(3203, new WorldTile(2665, 5202, 2), -1, true, true);
		World.spawnNPC(3203, new WorldTile(2661, 5200, 2), -1, true, true);
		World.spawnNPC(3203, new WorldTile(2665, 5197, 2), -1, true, true);
		World.spawnNPC(3203, new WorldTile(2669, 5203, 2), -1, true, true);
		
		//WATTS LIGHTNING`
		World.spawnNPC(3204, new WorldTile(2673, 5211, 2), 0, false, false);
		World.spawnNPC(3204, new WorldTile(2661, 5211, 2), 0, false, false);
		World.spawnNPC(3204, new WorldTile(2654, 5216, 2), 0, false, false);
		World.spawnNPC(3204, new WorldTile(2672, 5210, 2), 0, false, false);
		World.spawnNPC(3204, new WorldTile(2673, 5214, 2), 0, false, false);
		World.spawnNPC(3204, new WorldTile(2669, 5217, 2), 0, false, false);
		World.spawnNPC(3204, new WorldTile(2662, 5221, 2), 0, false, false);
		World.spawnNPC(3204, new WorldTile(2656, 5229, 2), 0, false, false);
		World.spawnNPC(3204, new WorldTile(2647, 5219, 2), 0, false, false);
		World.spawnNPC(3204, new WorldTile(2655, 5202, 2), 0, false, false);
		World.spawnNPC(3204, new WorldTile(2651, 5201, 2), 0, false, false);
		World.spawnNPC(3204, new WorldTile(2673, 5199, 2), 0, false, false);
		World.spawnNPC(3204, new WorldTile(2677, 5202, 2), 0, false, false);
		World.spawnNPC(3204, new WorldTile(2672, 5210, 2), 0, false, false);
		
		// PENGUINS
		World.spawnNPC(5428, new WorldTile(2503, 3897, 0), -1, true, true);
		World.spawnNPC(5428, new WorldTile(2503, 3897, 0), -1, true, true);
		World.spawnNPC(5428, new WorldTile(2503, 3897, 0), -1, true, true);
		World.spawnNPC(5428, new WorldTile(2503, 3897, 0), -1, true, true);
		World.spawnNPC(5428, new WorldTile(2503, 3897, 0), -1, true, true);
		World.spawnNPC(5428, new WorldTile(2503, 3897, 0), -1, true, true);
		World.spawnNPC(5428, new WorldTile(2503, 3897, 0), -1, true, true);
		World.spawnNPC(5428, new WorldTile(2503, 3897, 0), -1, true, true);
		//PENGUIN AGILITY COURSE
		//Pushups
		World.spawnNPC(5451, new WorldTile(2641, 4046, 1), -1, false, EntityDirection.SOUTH);
		World.spawnNPC(5451, new WorldTile(2640, 4046, 1), -1, false, EntityDirection.SOUTH);
		World.spawnNPC(5451, new WorldTile(2639, 4046, 1), -1, false, EntityDirection.SOUTH);
		World.spawnNPC(5451, new WorldTile(2638, 4046, 1), -1, false, EntityDirection.SOUTH);
		World.spawnNPC(5451, new WorldTile(2637, 4046, 1), -1, false, EntityDirection.SOUTH);
		World.spawnNPC(5451, new WorldTile(2636, 4046, 1), -1, false, EntityDirection.SOUTH);
		World.spawnNPC(5451, new WorldTile(2641, 4045, 1), -1, false, EntityDirection.SOUTH);
		World.spawnNPC(5451, new WorldTile(2640, 4045, 1), -1, false, EntityDirection.SOUTH);
		World.spawnNPC(5451, new WorldTile(2639, 4045, 1), -1, false, EntityDirection.SOUTH);
		World.spawnNPC(5451, new WorldTile(2638, 4045, 1), -1, false, EntityDirection.SOUTH);
		World.spawnNPC(5451, new WorldTile(2637, 4045, 1), -1, false, EntityDirection.SOUTH);
		World.spawnNPC(5451, new WorldTile(2636, 4045, 1), -1, false, EntityDirection.SOUTH);
		//Jumping Jacks
		World.spawnNPC(5449, new WorldTile(2634, 4044, 1), -1, false, EntityDirection.SOUTH);
		World.spawnNPC(5449, new WorldTile(2634, 4043, 1), -1, false, EntityDirection.SOUTH);
		World.spawnNPC(5449, new WorldTile(2634, 4042, 1), -1, false, EntityDirection.SOUTH);
		World.spawnNPC(5449, new WorldTile(2634, 4041, 1), -1, false, EntityDirection.SOUTH);
		World.spawnNPC(5449, new WorldTile(2634, 4040, 1), -1, false, EntityDirection.SOUTH);
		World.spawnNPC(5449, new WorldTile(2634, 4039, 1), -1, false, EntityDirection.SOUTH);
		World.spawnNPC(5449, new WorldTile(2633, 4044, 1), -1, false, EntityDirection.SOUTH);
		World.spawnNPC(5449, new WorldTile(2633, 4043, 1), -1, false, EntityDirection.SOUTH);
		World.spawnNPC(5449, new WorldTile(2633, 4042, 1), -1, false, EntityDirection.SOUTH);
		World.spawnNPC(5449, new WorldTile(2633, 4041, 1), -1, false, EntityDirection.SOUTH);
		World.spawnNPC(5449, new WorldTile(2633, 4040, 1), -1, false, EntityDirection.SOUTH);
		World.spawnNPC(5449, new WorldTile(2633, 4039, 1), -1, false, EntityDirection.SOUTH);
		//Situps
		World.spawnNPC(5450, new WorldTile(2636, 4038, 1), -1, false, EntityDirection.SOUTH);
		World.spawnNPC(5450, new WorldTile(2637, 4038, 1), -1, false, EntityDirection.SOUTH);
		World.spawnNPC(5450, new WorldTile(2638, 4038, 1), -1, false, EntityDirection.SOUTH);
		World.spawnNPC(5450, new WorldTile(2639, 4038, 1), -1, false, EntityDirection.SOUTH);
		World.spawnNPC(5450, new WorldTile(2640, 4038, 1), -1, false, EntityDirection.SOUTH);
		World.spawnNPC(5450, new WorldTile(2641, 4038, 1), -1, false, EntityDirection.SOUTH);
		World.spawnNPC(5450, new WorldTile(2636, 4037, 1), -1, false, EntityDirection.SOUTH);
		World.spawnNPC(5450, new WorldTile(2637, 4037, 1), -1, false, EntityDirection.SOUTH);
		World.spawnNPC(5450, new WorldTile(2638, 4037, 1), -1, false, EntityDirection.SOUTH);
		World.spawnNPC(5450, new WorldTile(2639, 4037, 1), -1, false, EntityDirection.SOUTH);
		World.spawnNPC(5450, new WorldTile(2640, 4037, 1), -1, false, EntityDirection.SOUTH);
		World.spawnNPC(5450, new WorldTile(2641, 4037, 1), -1, false, EntityDirection.SOUTH);
		//Agility
		World.spawnNPC(5456, new WorldTile(2634, 4056, 0), -1, true, true);
		World.spawnNPC(5457, new WorldTile(2633, 4053, 0), -1, true, true);
		World.spawnNPC(5458, new WorldTile(2632, 4056, 0), -1, true, true);
		World.spawnNPC(5459, new WorldTile(2631, 4053, 0), -1, true, true);
		World.spawnNPC(5458, new WorldTile(2630, 4056, 0), -1, true, true);
		World.spawnNPC(5459, new WorldTile(2629, 4053, 0), -1, true, true);
	
		// freaky foester + pheasants
		World.spawnNPC(100, new WorldTile(2600, 4775, 0), -1, false, EntityDirection.EAST); //Freaky Forester
		World.spawnNPC(101, new WorldTile(2596, 4773, 0), -1, true, true);
		World.spawnNPC(101, new WorldTile(2597, 4778, 0), -1, true, true);
		World.spawnNPC(101, new WorldTile(2601, 4776, 0), -1, true, true);
		World.spawnNPC(101, new WorldTile(2603, 4776, 0), -1, true, true);
		World.spawnNPC(101, new WorldTile(2600, 4777, 0), -1, true, true);
		World.spawnNPC(101, new WorldTile(2599, 4776, 0), -1, true, true);
		World.spawnNPC(101, new WorldTile(2603, 4770, 0), -1, true, true);
		World.spawnNPC(101, new WorldTile(2607, 4771, 0), -1, true, true);
		World.spawnNPC(101, new WorldTile(2607, 4773, 0), -1, true, true);
		World.spawnNPC(101, new WorldTile(2607, 4777, 0), -1, true, true);
		World.spawnNPC(101, new WorldTile(2610, 4775, 0), -1, true, true);
		World.spawnNPC(101, new WorldTile(2612, 4771, 0), -1, true, true);
				
		// Drill Demon
		World.spawnNPC(2790, new WorldTile(3167, 4818, 0), -1, true, true);
		World.spawnObject(new WorldObject(10075, 10, 0, 3160, 4821, 0), true);
		World.spawnObject(new WorldObject(10074, 10, 0, 3162, 4821, 0), true);
		World.spawnObject(new WorldObject(10073, 10, 0, 3164, 4821, 0), true);
		World.spawnObject(new WorldObject(10072, 10, 0, 3166, 4821, 0, 0), true);		
		
		//tears of guths
		World.spawnNPC(2021, new WorldTile(3224, 9513, 2), -1, true, true);
		World.spawnNPC(2022, new WorldTile(3216, 9523, 2), -1, true, true);
		World.spawnNPC(2021, new WorldTile(3223, 9526, 2), -1, true, true);
		World.spawnNPC(2021, new WorldTile(3224, 9513, 2), -1, true, true);
		World.spawnNPC(2021, new WorldTile(3218, 9154, 2), -1, true, true);
		World.spawnNPC(2022, new WorldTile(3223, 9511, 2), -1, true, true);
		World.spawnNPC(2022, new WorldTile(3221, 9520, 2), -1, true, true);
		World.spawnNPC(2021, new WorldTile(3232, 9520, 2), -1, true, true);
		World.spawnNPC(2021, new WorldTile(3233, 9512, 2), -1, true, true);
		World.spawnNPC(2022, new WorldTile(3239, 9515, 2), -1, true, true);
		World.spawnNPC(2022, new WorldTile(3236, 9507, 2), -1, true, true);
		World.spawnNPC(2021, new WorldTile(3232, 9508, 2), -1, true, true);
		World.spawnNPC(2022, new WorldTile(3233, 9504, 2), -1, true, true);
		World.spawnNPC(2022, new WorldTile(3234, 9516, 2), -1, true, true);
		World.spawnNPC(2021, new WorldTile(3238, 9519, 2), -1, true, true);
		World.spawnNPC(2021, new WorldTile(3231, 9509, 2), -1, true, true);
		World.spawnNPC(2022, new WorldTile(3231, 9515, 2), -1, true, true);
		World.spawnNPC(2021, new WorldTile(3236, 9513, 2), -1, true, true);
		World.spawnNPC(2021, new WorldTile(3226, 9524, 2), -1, true, true);
		World.spawnNPC(2022, new WorldTile(3227, 9518, 2), -1, true, true);
		World.spawnNPC(2022, new WorldTile(3233, 9524, 2), -1, true, true);
		
		World.spawnNPC(2021, new WorldTile(3236, 9525, 2), -1, true, true);
		World.spawnNPC(2021, new WorldTile(3227, 9528, 2), -1, true, true);
		World.spawnNPC(2021, new WorldTile(3218, 9528, 2), -1, true, true);
		
		World.spawnNPC(2021, new WorldTile(3222, 9495, 2), -1, true, true);
		World.spawnNPC(2021, new WorldTile(3224, 9504, 2), -1, true, true);
		World.spawnNPC(2021, new WorldTile(3231, 9498, 2), -1, true, true);
		World.spawnNPC(2021, new WorldTile(3220, 9493, 2), -1, true, true);
		
		World.spawnObject(new WorldObject(12309, 10, 3, 3219, 9622, 0), true);
		World.spawnObject(new WorldObject(45026, 10, 2, 2816, 10155, 0), true);
		//ISDAFAR
		//PURO PURO
		//Baby Imps (9)
		World.spawnNPC(1028, new WorldTile(2597, 4309, 0), -1, true, true);
		World.spawnNPC(1028, new WorldTile(2587, 4307, 0), -1, true, true);
		World.spawnNPC(1028, new WorldTile(2596, 4331, 0), -1, true, true);
		World.spawnNPC(1028, new WorldTile(2570, 4326, 0), -1, true, true);
		World.spawnNPC(1028, new WorldTile(2561, 4340, 0), -1, true, true);
		World.spawnNPC(1028, new WorldTile(2607, 4325, 0), -1, true, true);
		World.spawnNPC(1028, new WorldTile(2602, 4323, 0), -1, true, true);
		//World.spawnNPC(1028, new WorldTile(2593, 4329, 0), -1, true, true);
		World.spawnNPC(1028, new WorldTile(2607, 4345, 0), -1, true, true);
		World.spawnNPC(1028, new WorldTile(2593, 4329, 0), -1, true, true);
		World.spawnNPC(1028, new WorldTile(2607, 4345, 0), -1, true, true);
		//Young Imps(8)
		World.spawnNPC(1029, new WorldTile(2617, 4347, 0), -1, true, true);
		World.spawnNPC(1029, new WorldTile(2596, 4341, 0), -1, true, true);
		World.spawnNPC(1029, new WorldTile(2581, 4327, 0), -1, true, true);
		World.spawnNPC(1029, new WorldTile(2587, 4309, 0), -1, true, true);
		World.spawnNPC(1029, new WorldTile(2593, 4294, 0), -1, true, true);
		World.spawnNPC(1029, new WorldTile(2617, 4347, 0), -1, true, true);
		World.spawnNPC(1029, new WorldTile(2565, 4291, 0), -1, true, true);
		World.spawnNPC(1029, new WorldTile(2581, 4313, 0), -1, true, true);
		World.spawnNPC(1029, new WorldTile(2565, 4291, 0), -1, true, true);
		World.spawnNPC(1029, new WorldTile(2581, 4313, 0), -1, true, true);
		//Gourmet Imps(7)
		World.spawnNPC(1030, new WorldTile(2569, 4316, 0), -1, true, true);
		World.spawnNPC(1030, new WorldTile(2569, 4340, 0), -1, true, true);
		World.spawnNPC(1030, new WorldTile(2608, 4303, 0), -1, true, true);
		World.spawnNPC(1030, new WorldTile(2613, 4323, 0), -1, true, true);
		World.spawnNPC(1030, new WorldTile(2602, 4333, 0), -1, true, true);
		World.spawnNPC(1030, new WorldTile(2596, 4301, 0), -1, true, true);
		World.spawnNPC(1030, new WorldTile(2602, 4333, 0), -1, true, true);
		World.spawnNPC(1030, new WorldTile(2596, 4301, 0), -1, true, true);
		//Earth Imps(7)
		World.spawnNPC(1031, new WorldTile(2584, 4289, 0), -1, true, true);
		World.spawnNPC(1031, new WorldTile(2573, 4298, 0), -1, true, true);
		World.spawnNPC(1031, new WorldTile(2573, 4298, 0), -1, true, true);
		World.spawnNPC(1031, new WorldTile(2576, 4312, 0), -1, true, true);
		World.spawnNPC(1031, new WorldTile(2590, 4333, 0), -1, true, true);
		World.spawnNPC(1031, new WorldTile(2573, 4298, 0), -1, true, true);
		World.spawnNPC(1031, new WorldTile(2583, 4344, 0), -1, true, true);
		World.spawnNPC(1031, new WorldTile(2573, 4298, 0), -1, true, true);
		World.spawnNPC(1031, new WorldTile(2583, 4344, 0), -1, true, true);
		//Essence Imp(6)
		World.spawnNPC(1032, new WorldTile(2584, 4351, 0), -1, true, true);
		World.spawnNPC(1032, new WorldTile(2570, 4322, 0), -1, true, true);
		World.spawnNPC(1032, new WorldTile(2569, 4295, 0), -1, true, true);
		World.spawnNPC(1032, new WorldTile(2602, 4306, 0), -1, true, true);
		World.spawnNPC(1032, new WorldTile(2617, 4335, 0), -1, true, true);
		World.spawnNPC(1032, new WorldTile(2607, 4347, 0), -1, true, true);
		World.spawnNPC(1032, new WorldTile(2617, 4335, 0), -1, true, true);
		World.spawnNPC(1032, new WorldTile(2607, 4347, 0), -1, true, true);
		//Electic Imps(6)
		World.spawnNPC(1033, new WorldTile(2606, 4350, 0), -1, true, true);
		World.spawnNPC(1033, new WorldTile(2581, 4338, 0), -1, true, true);
		World.spawnNPC(1033, new WorldTile(2606, 4350, 0), -1, true, true);
		World.spawnNPC(1033, new WorldTile(2572, 4328, 0), -1, true, true);
		World.spawnNPC(1033, new WorldTile(2564, 4316, 0), -1, true, true);
		World.spawnNPC(1033, new WorldTile(2616, 4295, 0), -1, true, true);
		World.spawnNPC(1033, new WorldTile(2564, 4316, 0), -1, true, true);
		World.spawnNPC(1033, new WorldTile(2616, 4295, 0), -1, true, true);
		//Nature Imps(5)
		World.spawnNPC(1034, new WorldTile(2622, 4289, 0), -1, true, true);
		World.spawnNPC(1034, new WorldTile(2620, 4307, 0), -1, true, true);
		World.spawnNPC(1034, new WorldTile(2596, 4347, 0), -1, true, true);
		World.spawnNPC(1034, new WorldTile(2620, 4320, 0), -1, true, true);
		World.spawnNPC(1034, new WorldTile(2616, 4325, 0), -1, true, true);
		World.spawnNPC(1034, new WorldTile(2575, 4291, 0), -1, true, true);
		World.spawnNPC(1034, new WorldTile(2616, 4325, 0), -1, true, true);
		World.spawnNPC(1034, new WorldTile(2575, 4291, 0), -1, true, true);
		//Magpie Imps(5)
		World.spawnNPC(1035, new WorldTile(2610, 4291, 0), -1, true, true);
		World.spawnNPC(1035, new WorldTile(2602, 4297, 0), -1, true, true);
		World.spawnNPC(1035, new WorldTile(2613, 4315, 0), -1, true, true);
		World.spawnNPC(1035, new WorldTile(2562, 4327, 0), -1, true, true);
		World.spawnNPC(6053, new WorldTile(2560, 4332, 0), -1, true, true);
		World.spawnNPC(1035, new WorldTile(2562, 4327, 0), -1, true, true);
		World.spawnNPC(6053, new WorldTile(2560, 4332, 0), -1, true, true);
		//Ninja Imps(4)
		World.spawnNPC(6053, new WorldTile(2560, 4308, 0), -1, true, true);
		World.spawnNPC(6053, new WorldTile(2623, 4289, 0), -1, true, true);
		World.spawnNPC(6053, new WorldTile(2612, 4342, 0), -1, true, true);
		World.spawnNPC(6053, new WorldTile(2570, 4332, 0), -1, true, true);
		World.spawnNPC(6053, new WorldTile(2612, 4342, 0), -1, true, true);
		World.spawnNPC(6053, new WorldTile(2570, 4332, 0), -1, true, true);
		//Dragon Imps(3)
		World.spawnNPC(6054, new WorldTile(2565, 4351, 0), -1, true, true);
		World.spawnNPC(6054, new WorldTile(2623, 4332, 0), -1, true, true);
		World.spawnNPC(6054, new WorldTile(2607, 4333, 0), -1, true, true);
		World.spawnNPC(6054, new WorldTile(2584, 4297, 0), -1, true, true);
		World.spawnNPC(6054, new WorldTile(2607, 4333, 0), -1, true, true);
		World.spawnNPC(6054, new WorldTile(2584, 4297, 0), -1, true, true);
		//Runecrafting skill ALTARS + NPC's + Banks
		//World.spawnObject(new WorldObject(47120, 10, -1, 2844, 10204, 0), true); //zaros
		World.spawnObject(new WorldObject(6282, 10, -2, 2855, 10191, 0), true); //clanwars
		World.spawnObject(new WorldObject(66007, 10, 0, 2679, 2660, 0), true);
		World.spawnObject(new WorldObject(29740, 10, -1, 2911, 10175, 0), true); //MINECART 1
		World.spawnObject(new WorldObject(29740, 10, -2, 2909, 10173, 0), true); //MINECART 2
		World.spawnObject(new WorldObject(29740, 10, 0, 2909, 10171, 0), true); //MINECART 3
		World.spawnObject(new WorldObject(29740, 10, 0, 2911, 10169, 0), true); //MINECART 4
		World.spawnObject(new WorldObject(5849, 10, 0, 3229, 9526, 2), true); //snar1
		//World.spawnObject(new WorldObject(19333, 10, 0, 3229, 9526, 2), true); //snar2
		World.spawnObject(new WorldObject(19191, 10, 0, 3785, 2820, 0), true); // box
		World.spawnObject(new WorldObject(5849, 10, 0, 3785, 2820, 0), true); // box
		World.spawnObject(new WorldObject(5276, 10, 3, 3500, 3210, 0), true);// Burgh De Rott Bank 1
		World.spawnObject(new WorldObject(5276, 10, 2, 3499, 3210, 0), true);
		World.spawnObject(new WorldObject(5276, 10, 3, 3498, 3210, 0), true);
		World.spawnObject(new WorldObject(5276, 10, 2, 3497, 3210, 0), true);
		World.spawnObject(new WorldObject(5276, 10, 3, 3496, 3210, 0), true);
		World.spawnObject(new WorldObject(5276, 10, 2, 3495, 3210, 0), true);// Burgh De Rott Bank 6
		
		
		//home
		World.spawnObject(new WorldObject(9140, 10, 0, 2593, 3417, 0), true); //Info Board
		//World.spawnObject(new WorldObject(880, 10, 0, 2844, 10214, 0), true); //FOUNTAIN
		World.spawnObject(new WorldObject(6552, 10, 0, 2585, 3419, 0), true); // ZAROS
		//World.spawnNPC(6715, new WorldTile(2582, 3419, 0), 0, false, EntityDirection.SOUTH);//Estate Agent
		/*World.spawnObject(new WorldObject(1106, 10, 1, 2848, 10208, 0), true); // BENCH
		World.spawnObject(new WorldObject(1106, 10, 1, 2848, 10206, 0), true); // BENCH
		World.spawnObject(new WorldObject(1106, 10, 1, 2848, 10212, 0), true); // BENCH
		World.spawnObject(new WorldObject(1106, 10, 1, 2848, 10214, 0), true); // BENCH
		*/

	}
	

	public static void spawnObjects() {
	}
	
	

	/**
	 * The NPC classes.
	 */
	private static final Map<Integer, Class<?>> CUSTOM_NPCS = new HashMap<Integer, Class<?>>();

	public static void npcSpawn() {
		int size = 0;
		boolean ignore = false;
		try {
			for (String string : FileUtilities
					.readFile("data/npcs/spawns.txt")) {
				if (string.startsWith("//") || string.equals("")) {
					continue;
				}
				if (string.contains("/*")) {
					ignore = true;
					continue;
				}
				if (ignore) {
					if (string.contains("*/")) {
						ignore = false;
					}
					continue;
				}
				String[] spawn = string.split(" ");
				@SuppressWarnings("unused")
				int id = Integer.parseInt(spawn[0]), x = Integer
						.parseInt(spawn[1]), y = Integer.parseInt(spawn[2]), z = Integer
						.parseInt(spawn[3]), faceDir = Integer
						.parseInt(spawn[4]);
				NPC npc = null;
				Class<?> npcHandler = CUSTOM_NPCS.get(id);
				if (npcHandler == null) {
					npc = new NPC(id, new WorldTile(x, y, z), -1, true, false);
				} else {
					npc = (NPC) npcHandler.getConstructor(int.class)
							.newInstance(id);
				}
				if (npc != null) {
					WorldTile spawnLoc = new WorldTile(x, y, z);
					npc.setLocation(spawnLoc);
					World.spawnNPC(npc.getId(), spawnLoc, -1, true, false);
					size++;
				}
			}
		} catch (NumberFormatException e1) {
			e1.printStackTrace();
		} catch (IllegalArgumentException e1) {
			e1.printStackTrace();
		} catch (SecurityException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		} catch (InvocationTargetException e1) {
			e1.printStackTrace();
		} catch (NoSuchMethodException e1) {
			e1.printStackTrace();
		}
		System.err.println("Loaded " + size + " custom npc spawns!");
	}

}