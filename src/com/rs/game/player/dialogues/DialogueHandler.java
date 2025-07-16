package com.rs.game.player.dialogues;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import com.rs.utils.Logger;
import com.rs.utils.Utils;

public final class DialogueHandler {
    
    private static final HashMap<Object, Class<? extends Dialogue>> handledDialogues = new HashMap<Object, Class<? extends Dialogue>>();
    
    private DialogueHandler() {
    
    }
    
    public static final void reload() {
        handledDialogues.clear();
        init();
        // jut
    }
    
    @SuppressWarnings({"unchecked"})
    public static final void init() {
        String fileLoc = "bin/com/rs/game/player/dialogues/impl";
        String packageDir = "com.rs.game.player.dialogues.impl";
        try {
            List<Class> files = Utils.findClasses(new File(fileLoc) , packageDir);
            for (Class<Dialogue> c : files) {
                if (Dialogue.class.isAssignableFrom(c)) {
                    handledDialogues.put(""+c.getSimpleName()+"", (Class<Dialogue>) Class.forName(c.getCanonicalName()));
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    public static final Dialogue getDialogue(Object key) {
        if (key instanceof Dialogue)
            return (Dialogue) key;
        Class<? extends Dialogue> classD = handledDialogues.get(key);
        if (classD == null)
            return null;
        try {
            return classD.newInstance();
        } catch (Throwable e) {
            Logger.handle(e);
        }
        return null;
    }
}
