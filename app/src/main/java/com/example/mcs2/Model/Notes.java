package com.example.mcs2.Model;

public class Notes {
    int notesId;
    String notesTitle,notesDescription,notesDate;

    public Notes(int notesId, String notesTitle, String notesDescription, String notesDate) {
        this.notesId = notesId;
        this.notesTitle = notesTitle;
        this.notesDescription = notesDescription;
        this.notesDate = notesDate;
    }


    public int getNotesId() {
        return notesId;
    }

    public void setNotesId(int notesId) {
        this.notesId = notesId;
    }

    public String getNotesTitle() {
        return notesTitle;
    }

    public void setNotesTitle(String notesTitle) {
        this.notesTitle = notesTitle;
    }

    public String getNotesDescription() {
        return notesDescription;
    }

    public void setNotesDescription(String notesDescription) {
        this.notesDescription = notesDescription;
    }

    public String getNotesDate() {
        return notesDate;
    }

    public void setNotesDate(String notesDate) {
        this.notesDate = notesDate;
    }
}
