package xyz.antilose.events.impl;

import lombok.AllArgsConstructor;
import lombok.Getter;
import net.minecraft.screen.slot.SlotActionType;

@AllArgsConstructor
@Getter
public class ClickSlotEvent {

    int syncId,slotId, button;
    SlotActionType actionType;
}
