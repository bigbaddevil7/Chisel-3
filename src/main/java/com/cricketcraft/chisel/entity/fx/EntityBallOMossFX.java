package com.cricketcraft.chisel.entity.fx;

import com.cricketcraft.chisel.init.ChiselItems;
import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.VertexBuffer;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;
import org.lwjgl.opengl.GL11;

import java.util.Random;

public class EntityBallOMossFX extends EntityFX {
	public static Random rand = new Random();

	public EntityBallOMossFX(World world, double x, double y, double z) {
		super(world, x, y + 0.5, z, 0, 0, 0);

		particleScale = 0.5f + 0.5f * rand.nextFloat();

		particleMaxAge = (int) (rand.nextDouble() * 10.0) + 5;
		if (rand.nextInt(10) == 0)
			particleMaxAge += rand.nextDouble() * 40.0;

		xSpeed = (rand.nextDouble() - 0.5) * 0.7;
		ySpeed = (rand.nextDouble() * 0.5) * 0.7;
		zSpeed = (rand.nextDouble() - 0.5) * 0.7;
		particleGravity = 2.0f;

		this.setParticleTexture(Minecraft.getMinecraft().getRenderItem().getItemModelMesher().getParticleIcon(ChiselItems.ballOMoss));
	}

	@Override
	public int getFXLayer() {
		return 1;
	}

	@Override
	public void renderParticle(VertexBuffer renderer, Entity entity, float partialTicks, float rotX, float rotXZ, float rotZ, float rotYZ, float rotXY) {
		GL11.glDepthMask(false);
		GL11.glEnable(3042);
		GL11.glBlendFunc(770, 771);
		super.renderParticle(renderer, entity, partialTicks, rotX, rotXZ, rotZ, rotYZ, rotXY);
	}

	@Override
	public void onUpdate() {
		super.onUpdate();

		double remaining = particleMaxAge - particleAge;

		if (remaining < 5) {
			particleAlpha = (float) (remaining / 5.0);
		} else {
			particleAlpha = 1.0f;
		}
	}

}
