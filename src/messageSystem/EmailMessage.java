package messageSystem;

import java.util.ArrayList;
import java.util.Date;

public class EmailMessage extends Message implements IDigital {
    private String subject;
    private ArrayList<File> attachments;

    public EmailMessage(String sender, String content, Date sendDate, String messageId, String subject, ArrayList<File> attachments) {
        super(sender, content, sendDate, messageId);
        setSubject(subject);
        this.attachments = (attachments != null) ? new ArrayList<>(attachments) : new ArrayList<>();
    }

    // בנאי ללא תאריך וללא קבצים מצורפים - ללא שכפול קוד
    public EmailMessage(String sender, String content, String messageId, String subject) {
        this(sender, content, new Date(), messageId, subject, new ArrayList<>());
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        if (subject == null || subject.trim().isEmpty()) {
            throw new IllegalArgumentException("Email subject cannot be null or empty.");
        }
        this.subject = subject;
    }

    public ArrayList<File> getAttachments() {
        return new ArrayList<>(attachments);    
    }

    public void addAttachment(File file) {
        if (file == null) {
            throw new IllegalArgumentException("Cannot attach a null file.");
        }
        attachments.add(file);
    }

    public void removeAttachment(File file) throws AttachmentException {
        if (file == null || !attachments.contains(file)) {
            throw new AttachmentException("Attachment not found in email: " + (file != null ? file.getFileName() : "null"));
        }
        attachments.removeIf(f -> f.equals(file));
    }

    @Override
    public String printCommunicationMethod() {
        return "Sent via Email Server";
    }

    @Override
    public String generatePreview() {
        return "[Email] Subject: " + subject + " | From: " + sender;
    }

    @Override
    public String toString() {
        return "[EmailMessage] " + super.toString() + " | Subject: " + subject + " | Attachments: " + attachments;
    }
}
