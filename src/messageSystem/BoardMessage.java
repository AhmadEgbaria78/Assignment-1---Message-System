package messageSystem;
import java.util.Date;

public class BoardMessage extends Message {
    private PriorityType priority;
    private String location; 
    
    public BoardMessage(String sender, String content, Date sendDate, String messageId, PriorityType priority, String location) {
        super(sender, content, sendDate, messageId);
        setPriority(priority);
        setLocation(location);
    }

    public BoardMessage(String sender, String content, String messageId, String location) {
        this(sender, content, new Date(), messageId, PriorityType.REGULAR, location);
    }

    public PriorityType getPriority() {
        return priority;
    }

    public void setPriority(PriorityType priority) {
        if (priority == null) {
            throw new IllegalArgumentException("Priority cannot be null.");
        }
        this.priority = priority;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        if (location == null || location.trim().isEmpty()) {
            throw new IllegalArgumentException("Board location cannot be null or empty.");
        }
        this.location = location;
    }

    // מתודה נוספת לבחירתנו
    public boolean isUrgent() {
        return this.priority == PriorityType.URGENT;
    }

    @Override
    public String generatePreview() {
        String shortContent = content.length() > 15 ? content.substring(0, 15) + "..." : content;
        return "[Board] " + sender + ": " + shortContent;
    }

    @Override
    public String toString() {
        return "[BoardMessage] " + super.toString() + " | Priority: " + priority + " | Location: " + location;
    }
}
