package mcjty.rftoolsdim.dimensions.world.mapgen;

import com.google.common.collect.Lists;
import mcjty.lib.compat.CompatMapGenStructure;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.structure.*;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Random;

public class MapGenJungleTemple extends CompatMapGenStructure {
    private List<Biome.SpawnListEntry> scatteredFeatureSpawnList;

    private int maxDistance;
    private int minDistance;
    private World w;

    public MapGenJungleTemple() {
        this.scatteredFeatureSpawnList = Lists.<Biome.SpawnListEntry>newArrayList();
        this.maxDistance = 32;
        this.minDistance = 8;
    }

    @Override
    public void generate(World worldIn, int x, int z, ChunkPrimer primer) {
        w = worldIn;
        super.generate(worldIn, x, z, primer);
    }

    @Override
    public String getStructureName() {
        return "RFTJugnleTemple";
    }

    @Override
    protected boolean canSpawnStructureAtCoords(int chunkX, int chunkZ) {
        int i = chunkX;
        int j = chunkZ;

        if (chunkX < 0) {
            chunkX -= this.maxDistance - 1;
        }

        if (chunkZ < 0) {
            chunkZ -= this.maxDistance - 1;
        }

        int k = chunkX / this.maxDistance;
        int l = chunkZ / this.maxDistance;
        Random random = this.w.setRandomSeed(k, l, 14357617);
        k = k * this.maxDistance;
        l = l * this.maxDistance;
        k = k + random.nextInt(this.maxDistance - this.minDistance);
        l = l + random.nextInt(this.maxDistance - this.minDistance);

        if (i == k && j == l) {
            return true;
        }

        return false;
    }

    @Override
    protected StructureStart getStructureStart(int chunkX, int chunkZ) {
        return new MapGenJungleTemple.Start(this.rand, chunkX, chunkZ);
    }

    @Override
    public BlockPos clGetClosestStrongholdPos(World worldIn, BlockPos position) {
        return null;
    }

    public boolean func_175798_a(BlockPos p_175798_1_) {
        StructureStart structurestart = this.getStructureAt(p_175798_1_);

        if (structurestart != null && structurestart instanceof MapGenScatteredFeature.Start && !structurestart.getComponents().isEmpty()) {
            StructureComponent structurecomponent = structurestart.getComponents().get(0);
            return structurecomponent instanceof ComponentScatteredFeaturePieces.SwampHut;
        } else {
            return false;
        }
    }

    public List<Biome.SpawnListEntry> getScatteredFeatureSpawnList() {
        return this.scatteredFeatureSpawnList;
    }

    public static class Start extends StructureStart {
        public Start() {
        }

        public Start(Random random, int chunkX, int chunkZ) {
            super(chunkX, chunkZ);

            this.components.add(new ComponentScatteredFeaturePieces.JunglePyramid(random, chunkX * 16, chunkZ * 16));

            this.updateBoundingBox();
        }
    }

}
