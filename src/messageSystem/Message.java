package messageSystem;
import java.util.ArrayList;
import java.util.Date;


public abstract class Message {
	
	protected String sender;
    protected String content;
    protected Date sendDate;
    protected String messageId; 


    public Message(String sender, String content, Date sendDate, String messageId) {
        setSender(sender);
        setContent(content);
        if (sendDate == null) {
            throw new IllegalArgumentException("Send date cannot be null.");
        }
        this.sendDate = sendDate;
        if (messageId == null || messageId.trim().isEmpty()) {
            throw new IllegalArgumentException("Message ID cannot be null or empty.");
        }
        this.messageId = messageId;
    }

    
    public Message(String sender, String content, String messageId) {
        this(sender, content, new Date(), messageId);
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        if (sender == null || sender.trim().isEmpty()) {
            throw new IllegalArgumentException("Sender cannot be null or empty.");
        }
        this.sender = sender;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        if (content == null || content.trim().isEmpty()) {
            throw new IllegalArgumentException("Content cannot be null or empty.");
        }
        this.content = content;
    }

    public Date getSendDate() {
        return sendDate;
    }

    public String getMessageId() {
        return messageId;
    }


    public boolean find(ArrayList<String> words) {
        if (words == null || words.isEmpty()) {
            return false;
        }
        String lowerContent = this.content.toLowerCase();
        for (String word : words) {
            if (word != null && !word.trim().isEmpty() && lowerContent.contains(word.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    
    public int getContentLength() {
        return this.content.length();
    }

    public abstract String generatePreview();

    @Override
    public String toString() {
        return "ID: " + messageId + " | From: " + sender + " | Date: " + sendDate + " | Content: \"" + content + "\"";
    }

}
