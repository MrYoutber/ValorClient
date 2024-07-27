package valor;

public class DiscordClient {

	private static final DiscordClient INSTANCE = new DiscordClient();
	public static final DiscordClient getInstance() {
		return INSTANCE;
	}
	
	private DiscordRP discordRP = new DiscordRP();
	
	public void init() {
		discordRP.start();
	}
	
	public void shutdown() {
		discordRP.shutdown();
	}
	
	public DiscordRP getDiscordRP() {
		return discordRP;
	}
	
}
