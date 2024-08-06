package xyz.antilose.features;


import lombok.Getter;
import xyz.antilose.features.impl.*;

import java.util.ArrayList;

@Getter
public class ModuleManager {

    private ArrayList<Module> modules = new ArrayList<>();
    private NoCrystalDelay noCrystalDelay;
    private TapeMouse tapeMouse;
    public void registerModules() {
        this.getModules().add(new AutoSprint());
        this.getModules().add(new FullBright());
        this.getModules().add(new NoJumpDelay());
        this.getModules().add(noCrystalDelay = new NoCrystalDelay());
        this.getModules().add(tapeMouse = new TapeMouse());
    }

}
