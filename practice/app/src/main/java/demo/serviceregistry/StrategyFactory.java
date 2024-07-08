package demo.serviceregistry;

public class StrategyFactory {

    ServiceRegistryStrategy loadStrategy(Strategy strategy) {
       return switch (strategy.name()) {
            case "RANDOM" -> new RandomStrategy();
            default -> RoundrobinStrategy.getInstance();
        };
        }
}
