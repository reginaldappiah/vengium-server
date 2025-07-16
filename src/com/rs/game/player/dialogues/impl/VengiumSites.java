package com.rs.game.player.dialogues.impl;

import com.rs.Settings;
import com.rs.game.item.Item;
import com.rs.game.player.dialogues.Dialogue;

public class VengiumSites extends Dialogue {
	
	public static void launchURL(String url) {
        String osName = System.getProperty("os.name");
        try {
                if (osName.startsWith("Windows"))
                        Runtime.getRuntime().exec(
                                        "rundll32 url.dll,FileProtocolHandler " + url);
                else {
                        String[] browsers = { "firefox", "opera", "konqueror",
                                        "epiphany", "mozilla", "netscape" };
                        String browser = null;
                        for (int count = 0; count < browsers.length && browser == null; count++)
                                if (Runtime.getRuntime().exec(
                                                new String[] { "which", browsers[count] })
                                                .waitFor() == 0)
                                        browser = browsers[count];
                        Runtime.getRuntime().exec(new String[] { browser, url });
                }
        } catch (Exception e) {
                System.out.println( "Error in opening URL"
                                + ":\n" + e.getLocalizedMessage());
        }
}
	
	@Override
    public void start() {
        sendOptionsDialogue("Vengium Sites:", "<col=BC16F2>Vengium Homepage",
                "<col=BC16F2>Webstore Tab #1", "<col=BC16F2>Webstore Tab #2", "<col=BC16F2>RSMV of the Month (Coming soon)", "Nevermind...");
        stage = 1;
    }
 
    @Override
    public void run(int interfaceId, int componentId) {
         if (stage == 1) {
			if (componentId == OPTION_1) {
			player.getPackets().sendOpenURL("http://162.218.48.74/~vengiumr/");
			end();
			} 
			else if (componentId == OPTION_2) {
			player.getPackets().sendOpenURL("http://rsps-pay.com/store.php?id=1802&tab=1504");
			end();
			}
			else if (componentId == OPTION_3) {
			player.getPackets().sendOpenURL("http://rsps-pay.com/store.php?id=1802&tab=1505");
			end();
			}	
			else if (componentId == OPTION_4) {
			player.getPackets().sendGameMessage("<col=FF0000> Coming soon!");
			end();
			} 
			else if (componentId == OPTION_5) {
			end();
			}
		}
	}
    
    public void EmptyInventory() {
    	player.getInventory().reset();
    }
    public void ClearBank() {
    	for (Item item : player.getBank().getContainerCopy()) {
			if (item == null) {
				continue;
			}
			int[] slot = player.getBank().getItemSlot(item.getId());
			if (slot == null) {
				continue;
			}
			player.getBank().removeItem(slot, item.getAmount(), true, false);
		}
		player.getBank().collapse(0);
        player.getBank().collapse(1);
        player.getBank().collapse(2);
        player.getBank().collapse(3);
        player.getBank().collapse(4);
        player.getBank().collapse(5);
        player.getBank().collapse(6);
        player.getBank().collapse(7);
        player.getBank().collapse(8);
        player.getBank().collapse(9);
        player.closeInterfaces();
        player.getPackets().sendGameMessage("<col=00FF09>You have successfully cleared your bank!");;
    }
    
    @Override
    public void finish() {
        // TODO Auto-generated method stub
        
    }

}