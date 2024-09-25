package dev.nolij.nolijium.mixin.fabric;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.MeshData;
import com.mojang.blaze3d.vertex.Tesselator;
import dev.nolij.nolijium.impl.Nolijium;
import net.minecraft.client.Camera;
import net.minecraft.client.renderer.LevelRenderer;
import org.joml.Matrix4f;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = LevelRenderer.class, priority = 1100)
public class LevelRendererMixin {
	
	@WrapWithCondition(
		method = "renderLevel",
		at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/LevelRenderer;renderSky(Lorg/joml/Matrix4f;Lorg/joml/Matrix4f;FLnet/minecraft/client/Camera;ZLjava/lang/Runnable;)V")
	)
	public boolean nolijium$renderLevel$renderSky(LevelRenderer instance, Matrix4f matrix4f1, Matrix4f matrix4f2, float v, Camera camera, boolean b, Runnable runnable) {
		return !Nolijium.config.disableSky;
	}
	
	// fix crash if star count is too low
	@Inject(
		method = "drawStars",
		at = @At(
			value = "INVOKE",
			target = "Lcom/mojang/blaze3d/vertex/BufferBuilder;buildOrThrow()Lcom/mojang/blaze3d/vertex/MeshData;"
		)
	)
	public void nolijium$drawStars$buildOrThrow(Tesselator p_350542_, CallbackInfoReturnable<MeshData> cir, @Local BufferBuilder bufferBuilder) {
		for (int i = 0; i < 4; i++)
			bufferBuilder.addVertex(0, 0, 0);
	}
	
}