package dev.nolij.nolijium.mixin.fabric;

import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.LayeredDraw;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(Gui.class)
public interface GuiAccessor {
	
	@Accessor("layers")
	LayeredDraw getLayers(); 
	
}