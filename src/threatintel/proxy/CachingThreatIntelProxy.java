package threatintel.proxy;
import shared.model.ReputationResult;
import threatintel.adapter.ThreatIntelProvider;
import threatintel.cache.ThreatIntelCache;

// PATTERN: Proxy
public class CachingThreatIntelProxy implements ThreatIntelProxy {
    private ThreatIntelProvider realProvider;
    private ThreatIntelCache cache = ThreatIntelCache.getInstance();
    public CachingThreatIntelProxy(ThreatIntelProvider realProvider) { this.realProvider = realProvider; }
    public ReputationResult checkReputation(String indicator) {
        if (cache.contains(indicator)) {
            System.out.println("[Proxy] Cached reputation found for indicator: " + indicator);
            return cache.get(indicator);
        }
        System.out.println("[Proxy] Cache miss. Querying external reputation adapter...");
        ReputationResult result = realProvider.checkReputation(indicator);
        cache.put(indicator, result);
        return result;
    }
}
