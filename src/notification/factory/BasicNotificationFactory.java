package notification.factory;

// PATTERN: Abstract Factory
public class BasicNotificationFactory implements NotificationFactory {
    public Notifier createEmailNotifier() { return new EmailNotifier(); }
    public Notifier createSlackNotifier() { return new SlackNotifier(); }
    public Notifier createPagerDutyNotifier() { return new EmailNotifier(); }
}
