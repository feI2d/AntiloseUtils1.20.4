package xyz.antilose.features;


import lombok.Getter;
import xyz.antilose.features.impl.*;

import java.util.ArrayList;

@Getter
public class ModuleManager {

    private ArrayList<Module> modules = new ArrayList<>();
    private AutoSprint autoSprint;
    private FullBright fullBright;
    private NoCrystalDelay noCrystalDelay;
    private NoJumpDelay noJumpDelay;
    private TapeMouse tapeMouse;
    public void registerModules() {
        this.getModules().add(autoSprint = new AutoSprint());
        this.getModules().add(fullBright = new FullBright());
        this.getModules().add(noJumpDelay = new NoJumpDelay());
        this.getModules().add(noCrystalDelay = new NoCrystalDelay());
        this.getModules().add(tapeMouse = new TapeMouse());
    }

}
