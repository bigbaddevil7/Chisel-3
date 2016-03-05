package com.cricketcraft.chisel.entity.fx;

import com.cricketcraft.chisel.block.stone.ChiselBlockHolystone;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.world.World;

public class EntityHolystoneFX extends EntityFX {

    float initialScale;
    float angleOffset;
    static final float fadetime = 20.0F;

    public EntityHolystoneFX(World world, ChiselBlockHolystone block, double x, double y, double z) {
        super(world, x, y, z);

        initialScale = 1.0F + 1.0F * rand.nextFloat();
        angleOffset = rand.nextFloat() * 360.0F;
        particleMaxAge = (int) (Math.random() * 10.0D) + 80;

        setPosition(x, y, z);
        prevPosX = posX;
        prevPosY = posY;
        prevPosZ = posZ;
        noClip = true;
    }

    @Override
    public int getFXLayer() {
        return 1;
    }

    @Override
    public void renderParticle(WorldRenderer worldRenderer, Entity entity, float partialTicks, float rotX, float rotXZ, float rotZ, float rotYZ, float rotXY) {
        particleScale = 0.25F + initialScale * (float) Math.sin((particleAge + angleOffset) / 180.0F);

        if(particleAge < fadetime)
            particleAlpha = particleAge / fadetime;
        else if(particleAge + fadetime >= particleMaxAge)
            particleAlpha = (particleMaxAge - particleAge) / fadetime;
        else
            particleAlpha = 1.0F;
        super.renderParticle(worldRenderer, entity, partialTicks, rotX, rotXZ, rotZ, rotYZ, rotXY);
    }

    @Override
    public void onUpdate() {
        if(particleAge++ >= particleMaxAge)
            setDead();
    }
}
