package valor.optimizations;

import net.minecraft.client.multiplayer.ChunkProviderClient;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.ChunkProviderServer;

public class OptimizedChunkProvider extends ChunkProviderClient {
	private final ChunkProviderServer chunkProviderServer;

	public OptimizedChunkProvider(WorldClient worldClient, ChunkProviderServer chunkProviderServer) {
		super(worldClient);
		this.chunkProviderServer = chunkProviderServer;
	}

	@Override
	public Chunk provideChunk(int x, int z) {
		// Load chunk from server provider only if necessary
		Chunk chunk = super.provideChunk(x, z);
		if (chunk == null) {
			chunk = this.chunkProviderServer.provideChunk(x, z);
		}
		return chunk;
	}
}
