package notification.factory;

// PATTERN: Abstract Factory
public class EnterpriseNotificationFactory implements NotificationFactory {
    public Notifier createEmailNotifier() { return new EmailNotifier(); }
    public Notifier createSlackNotifier() { return new SlackNotifier(); }
    public Notifier createPagerDutyNotifier() { return new PagerDutyNotifier(); }
}
