package messageSystem;

import java.util.Date;

public class ChatMessage extends Message implements IDigital {
    private String platform;
    private boolean isRead;

    public ChatMessage(String sender, String content, Date sendDate, String messageId, String platform, boolean isRead) throws InvalidPlatformException {
        super(sender, content, sendDate, messageId);
        setPlatform(platform);
        this.isRead = isRead;
    }

    public ChatMessage(String sender, String content, String messageId, String platform) throws InvalidPlatformException {
        this(sender, content, new Date(), messageId, platform, false);
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) throws InvalidPlatformException {
        if (platform == null || platform.trim().isEmpty()) {
            throw new InvalidPlatformException("Chat platform cannot be empty.");
        }
        this.platform = platform;
    }

    public boolean isRead() {
        return isRead;
    }

    public void markAsRead() {
        this.isRead = true;
    }

    @Override
    public String printCommunicationMethod() {
        return "Sent via Instant Messaging (" + platform + ")";
    }

    @Override
    public String generatePreview() {
        String shortContent = content.length() > 15 ? content.substring(0, 15) + "..." : content;
        return "[Chat - " + platform + "] " + sender + ": " + shortContent;
    }

    @Override
    public String toString() {
        return "[ChatMessage] " + super.toString() + " | Platform: " + platform + " | Read: " + isRead;
    }
}
