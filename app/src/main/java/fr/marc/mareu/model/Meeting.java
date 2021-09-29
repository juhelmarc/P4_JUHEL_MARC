package fr.marc.mareu.model;

import java.util.Date;
import java.util.List;

public class Meeting {

    private Date date;

    private Date endDate;

    private Room room;

    private String subjectMeeting;

    private List<User> userList;

    private Boolean isFiltered;

    //Créer une classe Room / pas de string
    //utiliser également User



    public Meeting (Date date, Date endDate, Room room, String subjectMeeting, List<User> userList, Boolean isFiltered) {
        this.date = date;
        this.endDate = endDate;
        this.room = room;
        this.subjectMeeting = subjectMeeting;
        this.userList = userList;
        this.isFiltered = isFiltered;

    }
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public List<User> getUser() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    public Room getRoom() {
        return room;
    }

    public void setPlace(Room room) {
        this.room = room;
    }

    public String getSubjectMeeting() {
        return subjectMeeting;
    }

    public void setSubjectMeeting(String subjectMeeting) {
        this.subjectMeeting = subjectMeeting;
    }

    public Boolean getFiltered() {
        return isFiltered;
    }

    public void setFiltered(Boolean filtered) {
        isFiltered = filtered;
    }

}
